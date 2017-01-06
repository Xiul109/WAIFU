package waifu.drivers;
import com.jaunt.JauntException;

public class APDriver implements Driver{
	private final String urlTags="http://www.anime-planet.com/anime/all";
	private final String urlAnime="http://www.anime-planet.com/anime/tags/";
	
	public TagDriver getTagDriver() {
		try{
			return new APTagDriver(urlTags);
		}
		catch(JauntException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public AnimeDriver getAnimeDriver(String tag){
		try{
			return new APAnimeDriver(tag,urlAnime);
		}
		catch(JauntException e){
			return null;
		}
	}
}
