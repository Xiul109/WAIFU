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
	public MALAnimeDriver(String tag, String url) throws ResponseException, NotFound{
		UserAgent ua= new UserAgent();
		ua.visit(url);
		List<Element> tagGroup = ua.doc.findFirst("<div class=\"genre-link\">").getChildElements();
		for (Element i:tagGroup)
			for(Element j:i.getChildElements())
				if (j.getChildElements().get(0).getText().contains(tag))
					url=j.getChildElements().get(0).getAt("href");
		ua.visit(url);
		animes=ua.doc.findFirst("<div class=\"seasonal-anime-list clearfix\">").getChildElements();
	}
	
	
	public Anime next(){
		
		String name="unknown";
		String synopsis="unknown";
		List<String> tags=new LinkedList<String>();
		int nChapters=-1;
		int duration=-1;
		float score=-1.0f;
		Anime ani=null;
		try {
			if (animes.size()>0){
				Element anime=animes.remove(0);
				name=anime.findFirst("<p class=\"title-text\">").getChildElements().get(0).getText();
				nChapters=Integer.parseInt(anime.findFirst("<div class=\"eps\">").getChildElements().get(0).getChildElements().get(0).getText().split(" ")[0]);
				synopsis=anime.findFirst("<div class=\"synopsis js-synopsis\">").getChildElements().get(0).getText();
				score=Float.parseFloat(anime.findFirst("<span class=\"score\">").getText().replaceAll("[^0-9.]",""));
				for (Element each_tag:anime.findEach("<span class=\"genre\">"))
					tags.add(each_tag.getChildElements().get(0).getText());
			
				ani=new Anime(name,synopsis,tags,nChapters,-1,score);
			}
		} catch (NotFound e) {}
		return ani;
	}
	
	public boolean hasNext(){
		return animes.size()>0;
	}
}
