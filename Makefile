all:
	javac -cp lib/jade.jar:lib/jaunt.jar src/waifu/*.java src/waifu/drivers/* src/waifu/agents/* src/waifu/gui/* -d bin/
	
clean:
	rm -r bin/*

runInformationAgentMAL:
	java -cp lib/jade.jar:bin:lib/jaunt.jar jade.Boot -gui -agents "MALAgent:waifu.agents.InformationAgent(MALDriver)"

runInformationAgentAP:
	java -cp lib/jade.jar:bin:lib/jaunt.jar jade.Boot -gui -agents "APAgent:waifu.agents.InformationAgent(APDriver)"

runTestMainAgent:
	java -cp lib/jade.jar:bin:lib/jaunt.jar jade.Boot -gui -agents "APAgent:waifu.agents.InformationAgent(APDriver);MainAgent:waifu.agents.MainAgent(APAgent)"

runTest:
	java -cp lib/jade.jar:bin:lib/jaunt.jar jade.Boot -gui -agents "APAgent:waifu.agents.InformationAgent(APDriver);MALAgent:waifu.agents.InformationAgent(MALDriver);AggregatorAgent:waifu.agents.AggregatorAgent(APAgent,MALAgent);SchedulerAgent:waifu.agents.Scheduler;MainAgent:waifu.agents.MainAgent(AggregatorAgent,SchedulerAgent)"
