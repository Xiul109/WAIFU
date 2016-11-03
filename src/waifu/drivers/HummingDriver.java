package waifu.drivers;
import com.jaunt.JauntException;

public class HummingDriver implements Driver{
	private final String urlTags="https://hummingbird.me/anime/filter/";
	private final String urlAnime="https://hummingbird.me/anime/filter/?utf8=%E2%9C%93&y[]=Upcoming&y[]=2010s&y[]=2000s&y[]=1990s&y[]=1980s&y[]=1970s&y[]=Older&commit=Apply&g[]=";
	
	public TagDriver getTagDriver() {
		try{
			return new HummingTagDriver(urlTags);
		}
		catch(JauntException e){
			return null;
		}
	}
	
	public AnimeDriver getAnimeDriver(String tag){
		try{
			return new HummingAnimeDriver(tag,urlAnime);
		}
		catch(JauntException e){
			return null;
		}
	}
}
