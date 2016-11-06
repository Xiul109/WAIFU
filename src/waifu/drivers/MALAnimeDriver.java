package waifu.drivers;

import waifu.Anime;

import java.util.LinkedList;
import java.util.List;

import com.jaunt.Element;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;

public class MALAnimeDriver implements AnimeDriver{
	private List<Element> animes;
	private Element pageAc;
	private boolean hasNextPageOptions;
	private UserAgent ua= new UserAgent();
	
	public MALAnimeDriver(String tag, String url) throws ResponseException, NotFound{
		ua.visit(url);
		List<Element> tagGroup = ua.doc.findFirst("<div class=\"genre-link\">").getChildElements();
		for (Element i:tagGroup)
			for(Element j:i.getChildElements())
				if (j.getElement(0).getText().contains(tag))
					url=j.getElement(0).getAt("href");
		ua.visit(url);
		animes=ua.doc.findFirst("<div class=\"seasonal-anime-list clearfix\">").getChildElements();
		hasNextPageOptions=true;
		try{
			pageAc=ua.doc.findFirst("<div class=\"pagination ac\">").getFirst("<a class=\"link current\"");
		}catch (NotFound e){
			hasNextPageOptions=false;
		}
	}
	
	private Anime getAnime()throws NotFound, ResponseException{
		String name="unknown";
		String synopsis="unknown";
		List<String> tags=new LinkedList<String>();
		int nChapters=-1;
		int duration=-1;
		float score=-1.0f;
		String animeUrl="";
		UserAgent uaInfo=new UserAgent();
		String score_aux="";
		List<Element> info;
		String nChapters_aux="";
		
		Element anime=animes.remove(0);
		if (animes.size()==0&&hasNextPage()&&hasNextPageOptions){
			String url=pageAc.nextSiblingElement().getAt("href");
			ua.visit(url);
			pageAc=ua.doc.findFirst("<div class=\"pagination ac\">").getFirst("<a class=\"link current\"");
			animes=ua.doc.findFirst("<div class=\"seasonal-anime-list clearfix\">").getChildElements();
		} 

		name=anime.findFirst("<p class=\"title-text\">").getElement(0).getText();
		
		synopsis=anime.findFirst("<div class=\"synopsis js-synopsis\">").getElement(0).getText();
		synopsis=synopsis.replaceAll("&#039;","\'").replaceAll("&quot;","\"");
		
		for (Element each_tag:anime.findEach("<span class=\"genre\">"))
			tags.add(each_tag.getElement(0).getText());
		
		score_aux=anime.findFirst("<span class=\"score\">").getText().replaceAll("\\s+","");
		score = score_aux.equals("N/A") ? -1.0f:Float.parseFloat(score_aux.replaceAll("[^0-9.]",""));
		
		animeUrl=anime.findFirst("<p class=\"title-text\">").getElement(0).getAt("href");
		uaInfo.visit(animeUrl);
		info=uaInfo.doc.findFirst("<div class=\"js-scrollfix-bottom\"").getChildElements();
		
		nChapters_aux=anime.findFirst("<div class=\"eps\">").getElement(0).getElement(0).getText();
		nChapters_aux=nChapters_aux.replaceAll("\\s+","");
		nChapters=nChapters_aux.contains("?") ?-1:cleanNumbers(nChapters_aux);
		
		for (Element labels:info){
			for(Element label: labels.getChildElements()){
				if (label.getText().equals("Duration:"))
					duration=animeDuration(label.getParent().getText());
			}
		}
		
		return new Anime(name,synopsis,tags,nChapters,duration,score);
	}
	
	private boolean hasNextPage(){
		return (hasNextPageOptions&&pageAc.getSiblingIndex()!=pageAc.getParent().getChildElements().size()-1);
	}
	
	private int animeDuration(String text){
		int hr=0;
		int min=0;
		int total=0;
		if (text.contains("hr.")){
			hr=cleanNumbers(text.split(".hr")[0]);
			min=cleanNumbers(text.split(".hr")[1]);
		}else if (!text.contains("hr.")){
			min=cleanNumbers(text.split("min.")[0]);
		}
		total=(hr*60)+min;
		if (total==0) total=-1;
		return total;
	}
	
	private int cleanNumbers(String numbers){
		int number=-1;
		numbers=numbers.replaceAll("\\s+","");
		if (!numbers.equals("Unknown")&&!numbers.equals("None"))
			number=Integer.parseInt(numbers.replaceAll("[^0-9]",""));
		return number;
	}
	
	public Anime next(){
		try {
			if (animes.size()>0){
				return getAnime();
			} else return null;
		} catch (NotFound | ResponseException e) {}
		return null;
	}
	
	public boolean hasNext(){
		return animes.size()>0;
	}
}
