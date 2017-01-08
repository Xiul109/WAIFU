package waifu;

public class ToSchedule{
	private Week freeTime;
	private Anime anime;
	
	public ToSchedule(Week freeTime, Anime anime){
		this.freeTime=freeTime;
		this.anime=anime;
	}
	
	public Week getFreeTime(){return freeTime;}
	
	public Anime getAnime(){return Anime;}
}
