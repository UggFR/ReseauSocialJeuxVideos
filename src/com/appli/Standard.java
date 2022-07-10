package com.appli;

import java.time.LocalDate;

/**
 * @author gifhu
 *
 */
public class Standard extends JHumainParent{
	
	/** 
	*   Constructeur avec caract�ristiques d'un joueur Standard
	*   @param pseudo : pseudo du Standard
	*   @param email : jeu du Standard
	*   @param dateNaissance : date de naissance du Standard
	*   @param R : r�seau social sur lequel est le Standard
	*   @param Plateforme : plateforme du Standard
	*/
	public Standard(String pseudo, String email, LocalDate dateNaissance, ReseauSocial R, String Plateforme) { //Caract�ristiques d'un Standard
		super(pseudo, email, dateNaissance, 50, 100, 5, true, true, false, R, Plateforme);
	}
}
