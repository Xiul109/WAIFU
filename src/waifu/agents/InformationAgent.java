package waifu.agents;

import waifu.drivers.*;
import waifu.Anime;

import java.util.LinkedList;
import java.io.Serializable;
import java.io.IOException;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;

public class InformationAgent extends Agent{
	private final String HUMMING="HummingDriver";
	private final String MAL="MALDriver";
	
	private Driver driver;
	private boolean error=false;
	
	private void errorM(){
		error=true;
		doDelete();
	}
	
	private void consoleMessage(String message){
		System.out.println(getLocalName()+": "+message);
	}
	
	private void argComprobations(){
		if(getArguments()!=null && getArguments().length==1){
			String driverType=getArguments()[0].toString();
			if(driverType.equals(HUMMING))
				driver=new HummingDriver();
			//TO DO: ADD ELSE IF FOR MALDriver
			else
				errorM();
		}
		else
			errorM();
	}
	
	protected void setup(){
		argComprobations();
		if(!error){
			consoleMessage("Agent started properly");
			MessageTemplate template=AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_QUERY);
			addBehaviour(new CheckQuerys(this,template));
		}
	}
	
	protected void takeDown(){
		consoleMessage(error?"Wrong arguments: The Agent takes only 1 argument and it must be '"+HUMMING+"' or '"+MAL+"'.":"Agent deleted");
	}
	
	//Comunication Behaviour
	class CheckQuerys extends AchieveREResponder{
		private final int TAG_MODE=1, ANIME_MODE=2;
		private int mode=0;
		private Buffer buffer;
		public CheckQuerys(Agent agent, MessageTemplate template){
			super(agent,template);
			buffer=new Buffer();
		}
		
		private ACLMessage agree(ACLMessage request){
			consoleMessage("Query '"+request.getContent()+"' by "+request.getSender()+" has been accepted");
			ACLMessage agree= request.createReply();
			agree.setPerformative(ACLMessage.AGREE);
			return agree;
		}
		
		protected ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException{
			if(request.getPerformative()==ACLMessage.QUERY_REF){
				if(request.getContent().equals(MessageType.TAG)){
					mode=TAG_MODE;
					myAgent.addBehaviour(new TagRetrievingBehaviour(buffer,this));
				}
				else{
					String[] param=request.getContent().split("\\p{Space}+");
					if(param.length==3 && param[0].equals(MessageType.ANIME)){
						String tag=param[1];
						int count;
						try{
							count=Integer.parseInt(param[2]);
						}catch(NumberFormatException e){
							throw new NotUnderstoodException("Can't understand message");
						}
						mode=ANIME_MODE;
						myAgent.addBehaviour(new AnimeRetrievingBehaviour(buffer, this, count, tag));
					}
					else 
						throw new NotUnderstoodException("Can't understand message");
				}
				block();
				return agree(request);
			}
			else{
				throw new RefuseException("Wrong performative");
			}
		}
		
		private ACLMessage createReply(ACLMessage request, Serializable object) throws FailureException{
			ACLMessage reply= request.createReply();
			reply.setPerformative(ACLMessage.INFORM_REF);
			try{
				reply.setContentObject(object);
			}catch(Exception e){
				throw new FailureException(e.getMessage());
			}
			return reply;
		}
		
		protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException{
			try{
				return createReply(request,buffer.getObject());
			}catch(Exception e){
				throw new FailureException(e.getMessage());
			}
		}
		
		//Tag Retrieving Behaviour
		class TagRetrievingBehaviour extends Behaviour{
			Buffer buffer;
			Behaviour toUnlock;
			TagDriver tagD;
			LinkedList<String> tags;
			public TagRetrievingBehaviour(Buffer buff,Behaviour toUnlock){
				buffer=buff;
				this.toUnlock=toUnlock;
				tagD=driver.getTagDriver();
				tags=new LinkedList<String>();
			}
			public void action(){
				tags.add(tagD.next());
			}
			
			public boolean done(){
				return !tagD.hasNext();
			}
			
			public int onEnd(){
				buffer.setObject(tags);
				toUnlock.restart();
				return super.onEnd();
			}
		}
		
		//Anime Retrieving Behaviour
		class AnimeRetrievingBehaviour extends Behaviour{
			Buffer buffer;
			Behaviour toUnlock;
			AnimeDriver aniD;
			int count;
			LinkedList<Anime> tags;
			public AnimeRetrievingBehaviour(Buffer buff,Behaviour toUnlock, int count, String tag){
				buffer=buff;
				this.toUnlock=toUnlock;
				aniD=driver.getAnimeDriver(tag);
				tags=new LinkedList<Anime>();
				this.count=count;
			}
			public void action(){
				tags.add(aniD.next());
				count--;
			}
			
			public boolean done(){
				return !aniD.hasNext() || count<=0;
			}
			
			public int onEnd(){
				buffer.setObject(tags);
				toUnlock.restart();
				return super.onEnd();
			}
		}
	}
}
