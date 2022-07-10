package com.appli;

import java.time.LocalDate;


/**
 * Cette classe de Test était l'interface que j'ai au début développé (cette classe n'est pas a testé remplacé par l'interface)
 * @author gifhu
 */
public class Test{
	
	/**
	 * @param args : args
	 */
	public static void main(String[] args) {
		
		// Création (importation) des jeux et du réseau social à partir des jeux
		Jeux Jeu = new Jeux();
		Jeu.readFile("jeux_final.tsv"); 
		ReseauSocial R = new ReseauSocial(Jeu);
		System.out.println("\n \n");
		
		//Test Réseau Social
		System.out.println("	Test Réseau Social :");
		System.out.println("Est ce que la map est vide ? " + R.listePseudoJoueurMapVide());
        System.out.println("Voici la liste des pseudos de ce réseau social : " + R.getListePseudo());
        System.out.println("Voici la map des pseudos avec leur profil de ce réseau social : " + R.getListePseudoJoueur() + "\n \n \n");
        
        //Test Jeux
		System.out.println("	Test Jeux :");
		System.out.println(Jeu.affichagePlateforme());
		System.out.println(Jeu.affichageCategorie()+ "\n");
		System.out.println(Jeu.affichageJeux(17000));
		System.out.println(Jeu.affichageJeux(15304));
		System.out.println(Jeu.affichageJeux("Call of Duty"));
		System.out.println(Jeu.affichageJeux("Call of Duty: Black Ops II"));
		//System.out.println(Jeu.existenceJeuxSurPlateforme("Call of Duty: Black Opsio II", "PS6"));
		System.out.println("Est ce que la catégorie Sports existe ? " + Jeu.existenceCategorie("Sports"));
		System.out.println("Est ce que la catégorie RP existe ? " + Jeu.existenceCategorie("RP") + "\n");
		Jeu.affichageJeuxParPlateforme("2600");
		Jeu.affichageJeuxParPlateforme("PS9");
		System.out.println("\n");
		Jeu.affichageJeuxParCategorie("Strategy");
		Jeu.affichageJeuxParCategorie("RP");
		//System.out.println("Est ce que le jeu Robotics Notes existe sur PS3 ? " + Jeu.existenceJeuxSurPlateforme("Robotics Notes", "PS3"));		
		//System.out.println("Est ce que le jeu Robotics Notes existe sur PS ? " + Jeu.existenceJeuxSurPlateforme("Robotics Notes", "PS"));
		
		
		
		Gold G1 = new Gold("ya", "ya@g.com", LocalDate.parse("22-03-2000") , R, "PS3");
		//System.out.println(R.getListePseudoJoueur());
		Gold G2 = new Gold("yo", "ya@g.com", LocalDate.parse("22-03-2000") , R, "PSV");
		Standard S1 = new Standard("yazerty", "ya@g.com", LocalDate.parse("22-03-2000") , R, "PS4");
		//System.out.println(R.getListePseudoJoueur());
		Bot B1 = new Bot("yazaae", "MK8", R);
		//System.out.println(R.getListePseudoJoueur());
		Enfant E1 = new Enfant("yaa", "ya@g.com", LocalDate.parse("22-03-2010") , R, G1, "PS3");
		//System.out.println(R.getListePseudoJoueur());
		//Standard S3 = new Standard("yuo", "ya@g.com", "22/03/2000" , R);
		Enfant E2 = new Enfant("yaa", "yaa@g.com", LocalDate.parse("22-03-2015") , R, G1, "LOM");
		Enfant E3 = new Enfant("yaaee", "yaa@g.com", LocalDate.parse("22-03-2012") , R, E2, "GB");
		System.out.println(R.getListePseudo());
		System.out.println(E2.getParents());
		System.out.println(E2.toString());
		G1.invitAmis("yaaasasa");
		S1.invitAmis("ya");
		E1.invitAmis("null");
		E1.setDeuxièmeParent("yazerty");
		//Enfant E4 = G2.creerProfilEnfant("aaa", "aa@gmail.com", LocalDate.parse("22-03-2010"), R, "DS");
		System.out.println(R.getListePseudo());
		System.out.println(E1.getParents());
		System.out.println(E1.toString());
		System.out.println(E3.toString());
		System.out.println(S1.toString());
		System.out.println(G1.getAmis());
		System.out.println(B1.toString());
		System.out.println(E1.getAmis());
		G1.supprAmis();
		G1.ajoutPlateforme("PSV");
		System.out.println(G1.toString());
		System.out.println(E1.getAmis());
		System.out.println(R.getListePseudo());
		System.out.println(G1.getEnfants());
		System.out.println(G2.getEnfants());
		System.out.println(G2.getPseudo());

			
		//G1.ajoutJeux("Robotics Notes", "PS3");
		//G1.offrirJeux(E1);
		//G1.ajoutJeux();
		//G1.supprJeux();
		//G1.offrirJeux(E2);
		//G1.offrirJeux(E4);
		//G1.offrirJeux(S1);
		//G1.ajoutJeux("Grand Theft Auto V", "PS3");
		System.out.println(G1.toString());
		System.out.println(G1.affichageJeux());
		System.out.println(E2.affichageJeux());
		System.out.println(S1.affichageJeux());
		System.out.println(G1.getPlateforme());
		//G1.jeuMultijoueurAvecAmi();
		System.out.println(G1.getHistoriquesParties());
	}

}