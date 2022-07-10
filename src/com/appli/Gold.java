package com.appli;

import java.time.LocalDate;

/**
 * @author gifhu
 *
 */
public class Gold extends JHumainParent{
	
	/** 
	*   Constructeur avec caractéristiques d'un joueur Gold
	*   @param pseudo : pseudo du Gold
	*   @param email : jeu du Gold
	*   @param dateNaissance : date de naissance du Gold
	*   @param R : réseau social sur lequel est le Gold
	*   @param Plateforme : plateforme du Gold
	*/
	public Gold(String pseudo, String email, LocalDate dateNaissance,  ReseauSocial R, String Plateforme) { //Caractéristiques d'un Gold
		super(pseudo, email, dateNaissance, Integer.MAX_VALUE, Integer.MAX_VALUE, 10, true, true, false, R, Plateforme); //Integer.MAX_VALUE équivaut à infini car nombre très grand
	}
}
