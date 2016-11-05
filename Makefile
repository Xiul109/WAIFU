all:
	javac -cp lib/jade.jar:lib/jaunt.jar src/waifu/Anime.java src/waifu/drivers/* src/waifu/agents/* -d bin/
	
clean:
	rm -r bin/*
