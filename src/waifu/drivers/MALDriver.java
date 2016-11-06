package waifu.drivers;
import com.jaunt.JauntException;

public class MALDriver implements Driver{
	private final String urlTags="https://myanimelist.net/anime.php";
	private final String urlAnime="https://myanimelist.net/anime.php";
	
	public TagDriver getTagDriver() {
		try{
			return new MALTagDriver(urlTags);
		}
		catch(JauntException e){
			return null;
		}
	}
	
	public AnimeDriver getAnimeDriver(String tag){
		try{
			return new MALAnimeDriver(tag,urlAnime);
		}
		catch(JauntException e){
			return null;
		}
	}
}
