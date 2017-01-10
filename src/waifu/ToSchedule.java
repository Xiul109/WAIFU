package waifu;


import java.io.Serializable;

public class ToSchedule implements Serializable{

	private Week freeTime;
	private Anime anime;
	
	public ToSchedule(Week freeTime, Anime anime){
		this.freeTime=freeTime;
		this.anime=anime;
	}
	
	public Week getFreeTime(){return freeTime;}
	
	public Anime getAnime(){return anime;}
}
