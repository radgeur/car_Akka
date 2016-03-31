package main;

import java.util.ArrayList;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class Appli {
	
	
	public static void main(String[] args) {
		
		final ActorSystem sys = ActorSystem.create("appli");
		
		ActorRef act1 = sys.actorOf(Props.create(Actor.class), "noeud1");
		ActorRef act2 = sys.actorOf(Props.create(Actor.class), "noeud2");
		ActorRef act3 = sys.actorOf(Props.create(Actor.class), "noeud3");
		ActorRef act4 = sys.actorOf(Props.create(Actor.class), "noeud4");
		ActorRef act5 = sys.actorOf(Props.create(Actor.class), "noeud5");
		ActorRef act6 = sys.actorOf(Props.create(Actor.class), "noeud6");
		ArrayList<ActorRef> l1 = new ArrayList<ActorRef>();
		
		l1.add(act2);
		l1.add(act5);
		ArrayList<ActorRef> l2 = new ArrayList<ActorRef>();
		l2.add(act3);
		l2.add(act4);
		ArrayList<ActorRef> l5 = new ArrayList<ActorRef>();
		l5.add(act6);
		act1.tell(new InitMsg(l1, null), null);
		act2.tell(new InitMsg(l2, act1), null);
		act3.tell(new InitMsg(null, act2), null);
		act4.tell(new InitMsg(null, act3), null);
		act5.tell(new InitMsg(l5, act1), null);
		act6.tell(new InitMsg(null, act5), null);
		act5.tell("test d'initialisation", null);	//Ligne de code à modifier afin de tester avec un autre noeud comme indiqué 
													//ci dessus avec les noeuds act1, act2, act3, act4, act5 ou act6
	}
}
