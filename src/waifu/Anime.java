package waifu;

import java.util.List;
import java.io.Serializable;

public class Anime implements Serializable{
	private String name;
	private String synopsis;
	private List<String> tags;
	private int nChapters;
	private int duration;
	private float score;
	
	public Anime(String name, String synopsis, List<String> tags, int nChapters, int duration, float score){
		this.name = name;
		this.synopsis = synopsis;
		this.tags = tags;
		this.nChapters = nChapters;
		this.duration = duration;
		this.score = score;
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
	@Override
	public String toString() {
		return "Anime [name=" + name + ", synopsis=" + synopsis + ", tags=" + tags + ", nChapters=" + nChapters
				+ ", duration=" + duration + ", score=" + score + "]";
	}
	public Anime combine(Anime other){
		
		synopsis=other.getSynopsis().length()<this.synopsis.length() ? this.synopsis :  other.getSynopsis();
		for(String tag:other.getTags()){
			if(! tags.contains(tag)) tags.add(tag);
		}

		return new Anime(other.getName(),synopsis,this.tags,other.getNChapters(),other.getDuration(),(other.getScore()+this.score)/2);
	}
	public boolean equals(Object other){
			return other instanceof Anime ? name.equalsIgnoreCase(((Anime) other).getName()) : false;
	}
}




