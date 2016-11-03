package waifu;

import java.util.List;

public class Anime{
	private String name;
	private String synopsis;
	private List<String> tags;
	private int nChapters;
	private int duration;
	private float score;
	
	public Anime(String name, String synopsis, List<String> tags, int nChapters, int duration, float score) {
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
}

