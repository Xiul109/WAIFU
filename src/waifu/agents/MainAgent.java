package waifu.agents;

import waifu.gui.Controller;
import waifu.gui.View;
import waifu.gui.MainGUI;
import waifu.*;

import java.util.List;

import jade.proto.AchieveREInitiator;
import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.*;
import jade.domain.FIPANames;

public class MainAgent extends Agent implements Controller{
	View view;
	AID aggregator,scheduler;
	boolean error=false;
	public void setup(){
		if(getArguments()!=null && getArguments().length==2){
			aggregator=new AID((String) getArguments()[0],false);
			scheduler=new AID((String) getArguments()[1],false);
		}else
			errorM();
		view=new MainGUI(this);
	}
	
	public void askTags(){
		ACLMessage message=new ACLMessage(ACLMessage.QUERY_REF);
		message.addReceiver(aggregator);
		message.setContent(MessageType.TAG);
		message.setProtocol(FIPANames.InteractionProtocol.FIPA_QUERY);
		addBehaviour(new QueryTags(this,message));
	}
	public void askAnimes(String tag,int number){
		ACLMessage message=new ACLMessage(ACLMessage.QUERY_REF);
		message.addReceiver(aggregator);
		message.setContent(MessageType.ANIME+","+tag+","+number);
		message.setProtocol(FIPANames.InteractionProtocol.FIPA_QUERY);
		addBehaviour(new QueryAnimes(this,message));
	}
	
	public void askSchedule(Week freeTime, Anime anime){
		try{
			ACLMessage message=new ACLMessage(ACLMessage.QUERY_REF);
			message.addReceiver(scheduler);
			message.setContentObject(new ToSchedule(freeTime,anime));
			message.setProtocol(FIPANames.InteractionProtocol.FIPA_QUERY);
			addBehaviour(new QuerySchedule(this,message));
		}catch(Exception e){
			view.notifyError(e.getMessage());
		}
	}
	
	protected void takeDown(){
		consoleMessage(error?"Wrong arguments: The Agent takes only 1 argument and it must be the name of the AggregatorAgent":"Agent deleted");
	}
	
	public void askClose(){
		doDelete();
	}
	
	private void errorM(){
		error=true;
		doDelete();
	}
	
	private void consoleMessage(String message){
		System.out.println(getLocalName()+": "+message);
	}
	
	class QueryTags extends AchieveREInitiator{
		public QueryTags(Agent agent,ACLMessage message){
			super(agent,message);
			consoleMessage("Asking AggregatorAgent for tags");
		}
		protected void handleAgree(ACLMessage agree){
			consoleMessage("The AggregatorAgent has accepted, waiting for the response");
		}
		protected void handleInform(ACLMessage inform){
			consoleMessage("The message from AggregatorAgent has arrived");
			try{
				List<String> tags=(List<String>) inform.getContentObject();
				consoleMessage("recibidos: "+tags.size()+" tags");
				view.giveTags(tags);
			}catch(UnreadableException e){
				consoleMessage(e.getMessage());
			}
		}
		protected void handleRefuse(ACLMessage refuse){
			view.notifyError("The AggregatorAgent has rejected");
			consoleMessage("The AggregatorAgent has rejected");
		}
		protected void handleNotUnderstood(ACLMessage nu){
			view.notifyError("The AggregatorAgent doesn't understand the message");
			consoleMessage("The AggregatorAgent doesn't understand the message");
		}
		protected void handleFailure(ACLMessage failure){
			view.notifyError("The AggregatorAgent had a problem collecting the data");
			consoleMessage("The AggregatorAgent had a problem collecting the data");
		}
	}

	class QueryAnimes extends AchieveREInitiator{
		public QueryAnimes(Agent agent,ACLMessage message){
			super(agent,message);
			consoleMessage("Asking AggregatorAgent for animes");
		}
		protected void handleAgree(ACLMessage agree){
			consoleMessage("The AggregatorAgent has accepted, waiting for the response");
		}
		protected void handleInform(ACLMessage inform){
			consoleMessage("The message from AggregatorAgent has arrived");
			try{
				List<Anime> animes=(List<Anime>) inform.getContentObject();
				view.giveAnimes(animes);
			}catch(UnreadableException e){
				consoleMessage(e.getMessage());
			}
		}
		protected void handleRefuse(ACLMessage refuse){
			view.notifyError("The AggregatorAgent doesn't understand the message");
			consoleMessage("The AggregatorAgent has rejected");
		}
		protected void handleNotUnderstood(ACLMessage nu){
			view.notifyError("The AggregatorAgent doesn't understand the message");
			consoleMessage("The AggregatorAgent doesn't understand the message");
		}
		protected void handleFailure(ACLMessage failure){
			view.notifyError("The AggregatorAgent had a problem collecting the data");
			consoleMessage("The AggregatorAgent had a problem collecting the data");
		}
	}
	
	class QuerySchedule extends AchieveREInitiator{
		public QuerySchedule(Agent agent,ACLMessage message){
			super(agent,message);
			consoleMessage("Asking SchedulerAgent for schedule");
		}
		protected void handleAgree(ACLMessage agree){
			consoleMessage("The SchedulerAgent has accepted, waiting for the response");
		}
		protected void handleInform(ACLMessage inform){
			consoleMessage("The message from SchedulerAgent has arrived");
			try{
				Week schedule=(Week) inform.getContentObject();
				view.giveSchedule(schedule);
			}catch(UnreadableException e){
				consoleMessage(e.getMessage());
			}
		}
		protected void handleRefuse(ACLMessage refuse){
			view.notifyError("The SchedulerrAgent doesn't understand the message");
			consoleMessage("The SchedulerAgent has rejected");
		}
		protected void handleNotUnderstood(ACLMessage nu){
			view.notifyError("The SchedulerAgent doesn't understand the message");
			consoleMessage("The SchedulerAgent doesn't understand the message");
		}
		protected void handleFailure(ACLMessage failure){
			view.notifyError("The SchedulerAgent had a problem collecting the data");
			consoleMessage("The SchedulerAgent had a problem collecting the data");
		}
	}
}
