
package waifu.agents;

import waifu.Anime;
import java.io.Serializable;
import java.util.LinkedList;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import jade.proto.AchieveREInitiator;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import java.io.IOException;

public class AggregatorAgent extends Agent{
	protected AID[] informationAgents;
	protected LinkedList<String> tags;
	protected LinkedList<Anime> animes;
	protected ACLMessage mainMessage;

	public void setup(){
			Object[] args = getArguments();
		if(args!=null){
			tags=new LinkedList<String>();
			animes=new LinkedList<Anime>();
			informationAgents=new AID[args.length];
			for(int i=0;i<args.length;i++){
				informationAgents[i]=new AID((String) args[i],false);
			}
			consoleMessage("Se han recibido: "+args.length+" argumentos");
			MessageTemplate template=AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_QUERY);

			addBehaviour(new CheckQuerys(this,template));
		}
		else errorM();
	}

	public void takedown(){
	consoleMessage("Aggregator end.");
	}

	private void errorM(){
		consoleMessage("Se a producido un error.");
		doDelete();
	}

	public synchronized void addAnime(LinkedList<Anime> otherAnimes){
		for(Anime anime:otherAnimes){
			if(!this.animes.contains(anime)){
				this.animes.add(anime);
			}
			else{
				Anime indexAnime=this.animes.get(this.animes.indexOf(anime));
				this.animes.add(anime.combine(indexAnime));
			}
		}
	}
	public synchronized void addTag(LinkedList<String> otherTags){
		consoleMessage("Aniadiendo tags a variables globales....");
		for(String tag:otherTags){
			if(!this.tags.contains(tag)){ 
				this.tags.add(tag);
				consoleMessage("O... un tag nuevo."+tag);
			}else{
				consoleMessage("Este tag ya estaba."+tag);
			}
		}
	}
		private void consoleMessage(String message){
		System.out.println(getLocalName()+": "+message);
	}

	class CheckQuerys extends AchieveREResponder{
 				private int behavioursToEnd;
				public CheckQuerys(Agent agent, MessageTemplate mt){
					super(agent,mt);
					behavioursToEnd=informationAgents.length;
				}
				public synchronized void decrementBTE(){
					behavioursToEnd--;
					consoleMessage("Un comportamiento menos por acabar.Quedan"+behavioursToEnd);
				}
				public synchronized void resetBTE(){
					behavioursToEnd=informationAgents.length;
					consoleMessage("Reseteando BTE a "+behavioursToEnd);
				}
				public synchronized int getBTE(){
					return behavioursToEnd;
				}
				private ACLMessage agree(ACLMessage request){
					consoleMessage("Query "+request.getContent()+" by "+request.getSender()+" has been accepted\n Collecting data...");
					ACLMessage agree= request.createReply();
					agree.setPerformative(ACLMessage.AGREE);
					return agree;
				}
        protected ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException {
					consoleMessage("Got a message from "+request.getSender());
 					if(request.getPerformative()==ACLMessage.QUERY_REF){
		        ACLMessage mensaje = new ACLMessage(ACLMessage.QUERY_REF);
						mensaje.setProtocol(FIPANames.InteractionProtocol.FIPA_QUERY);

						mainMessage=request;
						//
						if(request.getContent().equals(MessageType.TAG)){
				 				mensaje.setContent(MessageType.TAG);
								consoleMessage("mesaje tipo tag");
								for(int i=0;i<informationAgents.length;i++){
									ACLMessage aux=(ACLMessage)mensaje.clone();
									aux.addReceiver(informationAgents[i]);
									myAgent.addBehaviour(new AskForTags(myAgent,aux,this));
								}
						}else{
							 String[] param=request.getContent().split(",");
							 if( param[0].equals(MessageType.ANIME)){
									consoleMessage("mesaje tipo anime");
					 				mensaje.setContent(request.getContent());
									for(int i=0;i<informationAgents.length;i++){
										ACLMessage aux=(ACLMessage)mensaje.clone();
										aux.addReceiver(informationAgents[i]);
										myAgent.addBehaviour(new AskForAnimes(myAgent,aux,this));
									}
							 }else throw new NotUnderstoodException("Can't understand message.");
						}
					return agree(request);
					}else throw new RefuseException("Wrong performative");
		
        }
	}

	class AskForTags extends Ask{

		public AskForTags(Agent agente, ACLMessage mensaje,CheckQuerys master){
			super(agente,mensaje,master);
			consoleMessage("Behaviour for tags started...");
		}

    protected void handleInform(ACLMessage inform) {
			LinkedList<String> informTags=null;
			try{
				consoleMessage("Prepare tags inform...");
				informTags=(LinkedList<String>) inform.getContentObject();
			}catch(Exception e){
				//throw new FailureException(e.getMessage());
				consoleMessage("fallo al preparar los tags: "+e.getMessage());
			}
			addTag(informTags);
			consoleMessage("Aniadidos "+informTags.size()+" tags, por: "+inform.getSender());
    }

		public int onEnd(){
			consoleMessage("finalizada la recolección... Y avisando a master de mi fin.");
			master.decrementBTE();
			if(master.getBTE()==0) {
				master.resetBTE();
				ACLMessage inform = mainMessage.createReply();
				try{
					if(tags.size()>0){
		          inform.setPerformative(ACLMessage.INFORM);
							consoleMessage("Enviando "+tags.size()+" tags...");
							Serializable serializedTags=tags;
							inform.setContentObject(serializedTags);

					}else	inform.setPerformative(ACLMessage.FAILURE);
					
				}catch(Exception e){
					inform.setPerformative(ACLMessage.FAILURE);
					consoleMessage("fallo al preparar los tags: "+e.getMessage());
				}
				myAgent.send(inform);
			}
			return super.onEnd();
		}
	}

	class AskForAnimes extends Ask{
		public AskForAnimes(Agent agente, ACLMessage mensaje,CheckQuerys master){
			super(agente,mensaje,master);
		}

    protected void handleInform(ACLMessage inform){
			try{
				LinkedList<Anime> informAnimes=(LinkedList<Anime>) inform.getContentObject();
				addAnime(informAnimes);
			}catch(Exception e){
				consoleMessage("fallo al preparar los animes: "+e.getMessage());
			}
    }


		public int onEnd(){
			consoleMessage("finalizada la recolección... Y avisando a master de mi fin.");
			master.decrementBTE();
			if(master.getBTE()==0) {
				master.resetBTE();
				ACLMessage inform = mainMessage.createReply();
				try{
					if(animes.size()>0){
		          inform.setPerformative(ACLMessage.INFORM);
							consoleMessage("Enviando"+animes.size()+" animes...");
							Serializable serializedAnimes=animes;
							inform.setContentObject(serializedAnimes);
							
						}else{
							inform.setPerformative(ACLMessage.FAILURE);
						}
				}catch(Exception e){
						inform.setPerformative(ACLMessage.FAILURE);
					 consoleMessage("fallo al enviar el mensaje con animes:"+e.getMessage());
				}
			myAgent.send(inform);
			}
			return super.onEnd();
		}
	}


	class Ask extends AchieveREInitiator{
		    protected CheckQuerys master;

				public Ask(Agent agente, ACLMessage mensaje,CheckQuerys master) {
            super(agente, mensaje);
					this.master=master;
        }
 
        protected void handleAgree(ACLMessage agree) {
            consoleMessage(" Peticción acceptada, esperando respuesta....");
        }
 
        protected void handleRefuse(ACLMessage refuse) {
            consoleMessage(refuse.getSender().getLocalName()+" ha rechazado nuestra petición.");
        }
 
        protected void handleNotUnderstood(ACLMessage notUnderstood) {
            consoleMessage(notUnderstood.getSender().getLocalName()+" no entiende el mensaje.");
        }
 
        protected void handleFailure(ACLMessage fail) {
            consoleMessage(fail.getSender().getLocalName()+" ha fallado.");
        }
	}
}
