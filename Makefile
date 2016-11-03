all:
	javac -cp lib/*.jar src/waifu/Anime.java src/waifu/drivers/* -d bin/
	
clean:
	rm -r bin/*
