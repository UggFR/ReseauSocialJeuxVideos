package com.appli;

/**
 * @author gifhu
 *
 */
public class Bot extends Joueur{
	
	private String nomJeu; // Pas besoin de tableau juste nom du jeu car un bot par jeu

	/** 
	*   Constructeur avec caractéristiques d'un joueur Bot
	*   @param pseudo : pseudo du bot
	*   @param Jeu : jeu du bot
	*   @param R : réseau social sur lequel est le bot
	*/
	public Bot(String pseudo, String Jeu, ReseauSocial R){ //Caractéristiques d'un bot
		super(pseudo, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, false, false, false, R); //Integer.MAX_VALUE équivaut à infini car nombre très grand
		nomJeu = Jeu;
	}
	
	/**
	 * @return nomJeu : nom des Jeux
	 */
	public String getNomJeu() {
		return nomJeu;
	}
	
	/** 
	*   Retourne l'affichage d'un bot
	*   @return affichage : toString un bot
	*/
	@Override
	public String toString() {
		String affichage = "(Bot) pseudo : " + this.getPseudo() + " => jeu : " + this.getNomJeu();
		return affichage;
	}
}
