package main;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActorWithStash;


public class Actor extends UntypedActorWithStash{
	
	//ATTRIBUTS
	private List<ActorRef> sons;
	private ActorRef parent; //non utilisé pour la première partie
	private boolean initialized;
	private boolean visited;
	
	public Actor(){
		sons = new ArrayList<ActorRef>();
		parent = null;
		initialized = false;
		visited = false;
	}
	
	
	public void onReceive(Object message){
		if(message instanceof String){
			String msg = (String) message;
			if(msg.startsWith("test")){
				if(initialized && !visited){
					System.out.println(self().path().name() + " Je reçois un message");
					for(ActorRef act : sons)
						act.tell(message, null);
					if(parent != null)
						parent.tell(message, null);
					visited = true;
				}
				else
					stash(); //message mis de coté en attendant l'initialisation
			}
			return;
		}
		if(message instanceof InitMsg){
			InitMsg init = (InitMsg) message;
			sons = init.getSons();
			parent = init.getParent();
			initialized = true;
			unstashAll(); //Récupération des messages mis de côte
			System.out.println(self().path().name() + " Initialisation réussie Ø/");
			return;
		}
		unhandled(message);
	}
}
