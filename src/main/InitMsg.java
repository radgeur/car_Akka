package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;

/*
 * La classe qui permet d'initialiser les différents acteurs
 */
public class InitMsg implements Serializable{
	
	//ATTRIBUTS
	private List<ActorRef> sons = new ArrayList<ActorRef>();
	private ActorRef parent;
	
	//CONSTRUCTEUR
	/**
	 * Création des différents attributs en fonctions des listes passées en paramètre
	 * @param s La liste des fils déstinés à l'acteur
	 * @param p Le parent destiné à l'acteur
	 */
	public InitMsg(List<ActorRef> s, ActorRef p){
		if(s != null)
			sons = s;
		parent = p;
	}
	
	//METHODES
	/**
	 * Renvoie la liste des fils
	 * @return la liste des fils
	 */
	public List<ActorRef> getSons(){
		return sons;
	}
	
	/**
	 * Renvoie le parent
	 * @return le parent
	 */
	public ActorRef getParent(){
		return parent;
	}
	
}
