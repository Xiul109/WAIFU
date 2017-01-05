all:
	javac -cp lib/jade.jar:lib/jaunt.jar src/waifu/Anime.java src/waifu/drivers/* src/waifu/agents/* src/waifu/gui/* -d bin/
	
clean:
	rm -r bin/*

runInformationAgentMAL:
	java -cp lib/jade.jar:bin:lib/jaunt.jar jade.Boot -gui -agents "MALAgent:waifu.agents.InformationAgent(MALDriver)"

runInformationAgentAP:
	java -cp lib/jade.jar:bin:lib/jaunt.jar jade.Boot -gui -agents "APAgent:waifu.agents.InformationAgent(APDriver)"

runTestMainAgent:
	java -cp lib/jade.jar:bin:lib/jaunt.jar jade.Boot -gui -agents "APAgent:waifu.agents.InformationAgent(APDriver);MainAgent:waifu.agents.MainAgent(APAgent)"
