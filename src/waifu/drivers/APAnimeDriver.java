package waifu.drivers;

import waifu.Anime;

import java.util.LinkedList;
import java.util.List;

import com.jaunt.Element;
import com.jaunt.JNode;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.SearchException;
import com.jaunt.UserAgent;

public class APAnimeDriver implements AnimeDriver{
	boolean end;
	String nextUrl;
	List<String> urls;
	
	UserAgent ua;
	public APAnimeDriver(String tag, String url) throws ResponseException, NotFound{
		end=false;
		nextUrl=url+tag.toLowerCase();
		ua=new UserAgent();
		urls=new LinkedList<String>();
	}
	
	private String nextUrl() throws ResponseException, NotFound{
		if(urls.isEmpty()){
			ua.visit(nextUrl);
			for(Element e:ua.doc.findFirst("<ul class=\"cardDeck pure-g cd-narrow\"").getChildElements())
				urls.add(e.getChildElements().get(0).getAt("href"));
			try {
				nextUrl=ua.doc.findFirst("<li class=\"next\"").getChildElements().get(0).getAt("href");
			} catch (SearchException e){
				end=true;
			}
		}
		String url=urls.get(0);
		urls.remove(0);
		return url;
	}
	
	private Anime getAnimeInfo(String url) throws ResponseException, NotFound{
		UserAgent ua=new UserAgent();
		ua.visit(url);
		
		List<String> tags= new LinkedList<String>();
		
		String name= ua.doc.findFirst("<h1 itemprop=\"name\"").innerText();
		String synopsis=ua.doc.findFirst("<p>").innerText();
		int nChapters=0,duration=0;
		String[] chapters=ua.doc.findFirst("<span class=\"type\">").innerText().replaceAll("\\(|\\)", "").split(" ");
			if(chapters.length==6){
				nChapters=Integer.parseInt(chapters[1]);
				duration=Integer.parseInt(chapters[4]);
			}
			else if(chapters.length==3){
				nChapters=Integer.parseInt(chapters[1]);
				duration=chapters[0].equals("Movie")?120:22;
			}
		float score=Float.parseFloat(ua.doc.findFirst("<meta itemprop=\"ratingValue\">").getAtString("content").split("/")[4]);
		
		return new Anime(name, synopsis , tags, nChapters, duration, score);
	}
	
	public Anime next(){
		try {
			return end?null:getAnimeInfo(nextUrl());
		} catch (NotFound e) {
			return null;
		} catch (ResponseException e) {
			return null;
		}
	}
	
	public boolean hasNext(){
		return !end;
	}
}

