package waifu.drivers;

import waifu.Anime;

public interface AnimeDriver{
	Anime next();
	boolean hasNext();
}
