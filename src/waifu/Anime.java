package waifu;
import java.util.List;

public class Anime{
	private String name;
	private String synopsis;
	private List<String> tags;
	private int nChapters;
	private int duration;
	private float score;
	public Anime(String n, String sin, List<String> tgs, int nC, int d, float sc){
		name=n;
		synopsis=sin;
		tags=tgs;
		nChapters=nC;
		duration=d;
		score=sc;
	}
	
	public String getName(){
		return name;
	}
	
	public String getSynopsis(){
		return synopsis;
	}
	
	public List<String> getTags(){
		return tags;
	}
	
	public int getNChapters(){
		return nChapters;
	}
	public int getDuration(){
		return duration;
	}
	public float getScore(){
		return score;
	}
}
