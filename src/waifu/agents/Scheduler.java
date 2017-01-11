package waifu.agents;
import waifu.Anime;
import waifu.Week;
import waifu.ToSchedule;
import java.util.LinkedList;
import java.io.Serializable;
import java.io.IOException;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.*;
import jade.proto.AchieveREResponder;

public class Scheduler extends Agent{
	protected Week weekScheduled;

	public void setup(){
			weekScheduled=new Week();
			MessageTemplate template=AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_QUERY);
			addBehaviour(new CheckQuerys(this,template));
	}
	public void takedown(){
	consoleMessage("Scheduler end.");
	}

	private void consoleMessage(String message){
		System.out.println(getLocalName()+": "+message);
	}

	class CheckQuerys extends AchieveREResponder{

		public CheckQuerys(Agent agent, MessageTemplate template){
			super(agent,template);
		}

		private ACLMessage agree(ACLMessage request){
			consoleMessage("Query '"+request.getContent()+"' by "+request.getSender()+" has been accepted\n Collecting data...");
			ACLMessage agree= request.createReply();
			agree.setPerformative(ACLMessage.AGREE);
			return agree;
		}

	protected ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException{
		if(request.getPerformative()==ACLMessage.QUERY_REF){
			ToSchedule toSchedule=null;
			try{
				toSchedule=(ToSchedule) request.getContentObject();
			}catch(UnreadableException e){
				consoleMessage(e.getMessage());
			}
			if(toSchedule!=null){
				myAgent.addBehaviour(new SchedulerBehaviour(myAgent,this,toSchedule.getFreeTime(),toSchedule.getAnime()));

			}else throw new NotUnderstoodException("Can't understand message.");
			block();
			return agree(request);
		}else throw new RefuseException("Wrong performative");		
	}


	protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException{
			
			ACLMessage reply= request.createReply();
			reply.setPerformative(ACLMessage.INFORM);
			try{
				consoleMessage("Sending data...");
				reply.setContentObject(weekScheduled);
			}catch(Exception e){
				consoleMessage("An error has occured collecting the data");
				throw new FailureException(e.getMessage());
			}
			return reply;
	}
}
	class SchedulerBehaviour extends OneShotBehaviour{
		private Week week;
		private Anime anime;
		private Behaviour toUnlock;
		
		private void consoleMessage(String message){
		System.out.println(getLocalName()+": "+message);
		}

		public SchedulerBehaviour(Agent agent,Behaviour toUnlock,Week week,Anime anime){
			super(agent);
			this.week=week;
			this.anime=anime;
			this.toUnlock=toUnlock;
		}

		public void action(){
			int chapterAsigned=0;

			int nChapter=anime.getNChapters();
			int i=0;
			consoleMessage("Recibo: "+week.toString());
			while(i<7 && i<nChapter && chapterAsigned<nChapter){
				int chapterForThisDay=week.getDay(i)/anime.getDuration();
				chapterForThisDay=(nChapter-chapterAsigned)<chapterForThisDay? nChapter-chapterAsigned:chapterForThisDay ;
				chapterAsigned+=chapterForThisDay;

				consoleMessage("Para el dia: "+week.getDay(i)+" se asigna "+nChapter+" capitulos");
				consoleMessage("Para el dia: "+i+" se asigna "+chapterAsigned+" capitulos");
				week.setDay(i,chapterForThisDay);
				if(chapterAsigned==0)week.setDay(i,0);
				i++;
			}
			while (i<7){//Set 0 rest of days.
				week.setDay(i,0);
				i++;
			}
			weekScheduled=week;
			consoleMessage("Mando: "+week.toString());
		}
		
		public int onEnd(){
			toUnlock.restart();
			return super.onEnd();
		}
	}
}
