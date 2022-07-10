package com.appli;

import java.util.ArrayList;
// Import 
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



/**
 * On ne peut pas appeler une personne q pour cause de fonctionnalités et null pour problèmes avec l'indicateur null

 * @author gifhu
 */
public class ReseauSocial {

	
	// Variables de Reseau Social
	private Map<String, Joueur> listePseudoJoueur = new HashMap<String, Joueur>(); 	// Map stockant pseudo (clé) avec l'adresse du profil du joueur (valeur)
	private Map<String, Bot> listePseudoBot    = new HashMap<String, Bot>(); 	// Map stockant pseudo (clé) avec l'adresse du profil du joueur (valeur)
	private Jeux JeuDuRS;
	
	/**
	*   Constructeur
	*   @param J : jeux pour contruire la classe
	*/  
	public ReseauSocial(Jeux J) {
		JeuDuRS = J;
	}
	
	
	
	// Get et Set :

	/**
	 * Retourne la map des pseudos et leur profil de joueurs
	 *   @return Map des pseudos avec profil joueur
	 */
	public Map<String, Joueur> getListePseudoJoueur() {
		return listePseudoJoueur;
	}
	
	/**
	 *   @return Map des pseudos avec profil bot
	 */
	public Map<String, Bot> getListePseudoBot() {
		return listePseudoBot;
	}
	
	
	/** 
	*   Retourne la liste des pseudos majeurs du réseau social 
	*   @return listePseudoMajeur de la liste Majeur
	*/  
	public String getPseudoMajeur(){
		Map<String, Joueur> listePseudoJoueurHumain = listePseudoJoueur;
		ArrayList<String> listeJoueurMajeur = new ArrayList<String>();
		for(Map.Entry<String, Joueur> joueur : listePseudoJoueurHumain.entrySet()) {
			if(joueur.getValue() instanceof JHumainParent) {
				listeJoueurMajeur.add(joueur.getValue().getPseudo());
			}
		}
		String listePseudoMajeur = "[";
		for(String key: listeJoueurMajeur) {
			listePseudoMajeur += key + ",";
		}
		// Cas où il n'y a pas encore de pseudo
		if(listePseudoMajeur.length() > 1) {
			listePseudoMajeur  = listePseudoMajeur.substring(0, listePseudoMajeur.length()-1) + "]"; 	// Permet d'enlever la virgule en trop à l'affichage des pseudos
		}
		else {
			listePseudoMajeur  += "]"; 	// Permet d'enlever la virgule en trop à l'affichage des pseudos
		}
		return listePseudoMajeur;
	}
	


	/** 
	*   Retourne la liste des pseudos du réseau social sous forme de String
	*   @return listePseudo de la liste Pseudo
	*/	
	public String getListePseudo() {
		String listePseudo = "[";
	    Set<String> keys   = listePseudoJoueur.keySet(); // Permet d'obtenir toutes les clés de la map
		for(String key: keys) {
			listePseudo += key + ",";
		}
		// Cas où il n'y a pas encore de pseudo
		if(listePseudo.length() > 1) {
			listePseudo  = listePseudo.substring(0, listePseudo.length()-1) + "]"; 	// Permet d'enlever la virgule en trop à l'affichage des pseudos
		}
		else {
			listePseudo  += "]"; 	// Permet d'enlever la virgule en trop à l'affichage des pseudos
		}
		return listePseudo;
	}
	
	/** 
	*   Retourne un Objet Jeu avec tous les jeux du réseau social
	*   @return Jeux de la liste Pseudo
	*/		
	public Jeux getJeux(){
		return JeuDuRS;
	}
	
	/** 
	*   Ajoute un pseudo et son profil
	*   @param pseudo : nom du joueur
	*   @param J : Objet joueur
	*/
	public void addPseudoJoueur(String pseudo, Joueur J) {
	    listePseudoJoueur.put(pseudo, J);
	}
	
	/** 
	*   Ajoute un pseudo et son profil Joueur
	*   @param pseudo : nom du bot
	*   @param bot : Objet bot
	*/
	public void addPseudoBot(String pseudo, Bot bot) {
	    listePseudoBot.put(pseudo, bot);
	}
		
	/** 
	*   Ajoute un pseudo et son profil Bot
	*   @param pseudo : pseudo du joueur à supprimer
	*/
	public void supprPseudoJoueur(String pseudo) {
		listePseudoJoueur.remove(pseudo);
	}
	
	
	
	
	/** 
	*   Vérifie si la liste des pseudos/joueurs est vide ou pas
	*   @return boolean si la map est vide
	*/
	public boolean listePseudoJoueurMapVide() {
		if(listePseudoJoueur.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
