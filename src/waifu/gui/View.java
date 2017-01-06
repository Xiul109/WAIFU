package waifu.gui;

import java.util.List;
import waifu.Anime;

public interface View{
	public void giveTags(List<String> tags);
	public void giveAnimes(List<Anime> animes);
	public void notifyError(String error);
}
