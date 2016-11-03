package waifu.drivers;

public class HummingAnimeDriver implements Driver{
	private final String urlTags="https://hummingbird.me/anime/filter/";
	private final String urlAnime=https:"//hummingbird.me/anime/filter/?utf8=%E2%9C%93&y[]=Upcoming&y[]=2010s&y[]=2000s&y[]=1990s&y[]=1980s&y[]=1970s&y[]=Older&commit=Apply&g[]=";
	
	public TagDriver getTagDriver(){
		return new HummingTagDriver(urlTags);
	}
	
	public AnimeDriver getAnimeDriver(String tag){
		return new HummingAnimeDriver(tag,urlAnime);
	}
}
