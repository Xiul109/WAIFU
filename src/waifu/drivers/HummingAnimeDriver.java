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

public class HummingAnimeDriver implements AnimeDriver{
	boolean end;
	String nextUrl;
	List<String> urls;
	
	UserAgent ua;
	public HummingAnimeDriver(String tag, String url) throws ResponseException, NotFound{
		end=false;
		nextUrl=url+tag.toLowerCase();
		ua=new UserAgent();
		urls=new LinkedList<String>();
	}
	
	private String nextUrl() throws ResponseException, NotFound{
		if(urls.isEmpty()){
			ua.visit(nextUrl);
			for(Element e:ua.doc.findEvery("<li class=\"large-2 columns single-wrapper\">"))
				urls.add(e.getChildElements().get(0).getAt("href"));
			try {
				nextUrl=ua.doc.findFirst("<a rel=\"next\">").getAt("href");
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
		String texto=ua.doc.getChildElements().get(0).getChildElements().get(1).getChildElements().get(3).getText()
				.split("\n")[2];
		
		ua.openJSON(texto.substring("  window.preloadData = [".length(), texto.length()-2));
		List<String> tags= new LinkedList<String>();
		for(JNode jn:ua.json.findFirst("genres"))
			tags.add(jn.toString());
		
		String name= ua.json.findFirst("canonical_title").toString();
		String synopsis=ua.json.findFirst("synopsis").toString();
		JNode nEpisodes= ua.json.findFirst("episode_count"),
		duration=ua.json.findFirst("episode_length"),
		score=ua.json.findFirst("bayesian_rating");
		
		return new Anime(name.equals("null")?"unknown":name.toString(), synopsis.equals("null")?"unknown":synopsis,tags,
				nEpisodes.toString().equals("null")?-1:nEpisodes.toInt(), duration.toString().equals("null")?-1:duration.toInt(),
				score.toString().equals("null")?-1:2.0f*score.toFloat());
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

