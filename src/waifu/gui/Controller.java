package waifu.gui;
import waifu.Anime;
import waifu.Week;

public interface Controller{
	public void askTags();
	public void askAnimes(String tag, int number);
	public void askSchedule(Week freeTime,Anime anime);
}
