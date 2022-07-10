package com.appli;

import java.time.LocalDate;

/**
 * @author gifhu
 *
 */
public class Standard extends JHumainParent{
	
	/** 
	*   Constructeur avec caractéristiques d'un joueur Standard
	*   @param pseudo : pseudo du Standard
	*   @param email : jeu du Standard
	*   @param dateNaissance : date de naissance du Standard
	*   @param R : réseau social sur lequel est le Standard
	*   @param Plateforme : plateforme du Standard
	*/
	public Standard(String pseudo, String email, LocalDate dateNaissance, ReseauSocial R, String Plateforme) { //Caractéristiques d'un Standard
		super(pseudo, email, dateNaissance, 50, 100, 5, true, true, false, R, Plateforme);
	}
}
