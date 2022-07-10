package com.appli;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;


/**
 * Cette classe permet de tester le r�seau social comme si on �tait un client :
 * Il faut savoir que les emails et dates ne sont pas g�rer en profondeur
 * @author gifhu
 */
public class InterfaceClient {
	
    static final Scanner scan = new Scanner(System.in);
	private static Gold g1;
	private static Gold g2;
	private static Standard s1;
	
	/**
	 * Lancement du r�seau social
	 */
	public static void lancement() {
		System.out.println("Bienvenue dans ce nouveau r�seau social :");	
		System.out.println("Voici quelques informations des donn�es de ce r�seau social : \n");
	}

	/**
	 * Accueil du Reseau Social avec 3 choix pour l'utilisateur
	 * @param RS : Reseau Social utilis�
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 * @throws ProblemeMisEnCommunException : Exception probleme mise en commun d'infos
	 */
	public static void accueil(ReseauSocial RS) throws ProblemeAffichageException, ProblemeMisEnCommunException {
		String choix;
		do {

			System.out.println("1 -> Cr�ation d'un compte");	
			System.out.println("2 -> Se connecter");
			System.out.println("q -> Quitter le r�seau social");
			System.out.println("Veuillez saisir 1, 2 ou q : ");
			choix = scan.next();	
			while(choix.equals("1") == false && choix.equals("2") == false && choix.equals("q") == false) {
				System.err.println(choix + " n'est pas d�fini comme fonctionnalit�.");	
				System.out.println("1 -> Cr�ation d'un compte");	
				System.out.println("2 -> Se connecter");
				System.out.println("q -> Quitter le r�seau social");
				System.out.println("Veuillez saisir 1, 2 ou q : ");
				choix = scan.next();
			}
			if(choix.equals("1")) {
				creerCompte(RS);
			}
			else if(choix.equals("2")) {
				seConnecter(RS);
			}
		}while(choix.equals("q") == false);
		System.out.println("Merci d'avoir utilis� notre r�seau social. A tr�s bient�t !");
	}
	
	// 
	/**
	 * Permet de cr�er compte et affiche actions possibles(Date et Email ne sont pas forc�ment g�r�s en profondeur)
	 * @param RS : Reseau Social utilis�
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 * @throws ProblemeMisEnCommunException : Exception probleme mise en commun d'infos
	 */
	public static void creerCompte(ReseauSocial RS) throws ProblemeAffichageException, ProblemeMisEnCommunException {
		String choixType;
		System.out.println("Quelle type de profil voulez-vous �tre : Gold ou Standard ?");	
		choixType = scan.next();
		while(choixType.equals("Gold") == false && choixType.equals("Standard") == false) {
			System.err.println(choixType + " n'est pas un type de profil.");	
			System.out.println("Quelle type de profil voulez-vous �tre : Gold ou Standard ?");	
			choixType = scan.next();
		}
		String choixPseudo;
		System.out.println("Choisissez un pseudo : ");	
		choixPseudo = scan.next();
		while(RS.getListePseudo().contains(choixPseudo) || choixPseudo.equals("q") == true || choixPseudo.equals("null") == true) {
			if(RS.getListePseudo().contains(choixPseudo)) {
				System.err.println(choixPseudo + " : Pseudo d�j� utilis�.");
				System.out.println("Choisissez un autre pseudo : ");	
				choixPseudo = scan.next();
			}
			else {
				System.err.println(choixPseudo + " n'est pas un pseudo valide.");
				System.out.println("Choisissez un autre pseudo : ");	
				choixPseudo = scan.next();
			}
		}
		System.out.println("Saisissez votre date de naissance XXXX-mm-DD" + ": ");	
		String date = scan.next();
		LocalDate choixDate = LocalDate.parse(date);
	    String choixEmail;
		System.out.println("Saisissez votre mail : ");	
		choixEmail = scan.next();
		while(choixEmail.contains("@") == false || choixEmail.contains(".") == false) {
			System.err.println(choixEmail + " n'est pas un type d'email valide. L'email doit contenir les caract�res '@' et '.' pour �tre valide.");	
			System.out.println("Saisissez votre mail : ");	
			choixEmail = scan.next();
		} 
		String choixPlateforme;
		System.out.println(RS.getJeux().affichagePlateforme());
		System.out.println("Saisissez votre plateforme : ");	
		choixPlateforme = scan.next();
		while(RS.getJeux().existencePlateforme(choixPlateforme) == false) {
			System.err.println(choixPlateforme + " n'est pas un plateforme �ligible sur ce r�seau social.");	
			System.out.println(RS.getJeux().affichagePlateforme());
			System.out.println("Saisissez votre plateforme : ");	
			choixPlateforme = scan.next();
		}
		JHumainParent nouveauJoueur;
		// Soit le joueur est Gold soit Standard
		if(choixType.equals("Gold")){
			nouveauJoueur = new Gold(choixPseudo, choixEmail, choixDate, RS, choixPlateforme); 
		}
		else {
			nouveauJoueur = new Standard(choixPseudo, choixEmail, choixDate, RS, choixPlateforme); 
		}
		actionsCompte(RS, nouveauJoueur);
	}
	
	/**
	 * Creer compte enfant � partir d'un parent et affiche actions possibles
	 * @param RS : Reseau Social utilis�
	 * @param jParent : Parent qui cr�er le compte  
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 * @throws ProblemeMisEnCommunException : Exception probleme mise en commun d'infos
	 */
	public static void creerCompteEnfant(ReseauSocial RS, JHumainParent jParent) throws ProblemeAffichageException, ProblemeMisEnCommunException {
		String choixPseudo;
		System.out.println("Choisissez un pseudo : ");	
		choixPseudo = scan.next();
		while(RS.getListePseudo().contains(choixPseudo) || choixPseudo.equals("q") == true || choixPseudo.equals("null") == true) {
			if(RS.getListePseudo().contains(choixPseudo)) {
				System.err.println(choixPseudo + " : Pseudo d�j� utilis�.");
				System.out.println("Choisissez un autre pseudo : ");	
				choixPseudo = scan.next();
			}
			else {
				System.err.println(choixPseudo + " n'est pas un pseudo valide.");
				System.out.println("Choisissez un autre pseudo : ");	
				choixPseudo = scan.next();
			}
		}
		System.out.println("Saisissez votre date de naissance XXXX-mm-DD" + ": ");	
		LocalDate choixDate = LocalDate.parse(scan.next());
	    String choixEmail;
		System.out.println("Saisissez votre mail : ");	
		choixEmail = scan.next();
		while(choixEmail.contains("@") == false || choixEmail.contains(".") == false) {
			System.err.println(choixEmail + " n'est pas un type d'email valide. L'email doit contenir les caract�res '@' et '.' pour �tre valide.");	
			System.out.println("Saisissez votre mail : ");	
			choixEmail = scan.next();
		} 
		String choixPlateforme;
		System.out.println(RS.getJeux().affichagePlateforme());
		System.out.println("Saisissez votre plateforme : ");	
		choixPlateforme = scan.next();
		while(RS.getJeux().existencePlateforme(choixPlateforme) == false) {
			System.err.println(choixPlateforme + " n'est pas un plateforme �ligible sur ce r�seau social.");	
			System.out.println(RS.getJeux().affichagePlateforme());
			System.out.println("Saisissez votre plateforme : ");	
			choixPlateforme = scan.next();
		}
		Enfant nouveauJoueur;
		nouveauJoueur = new Enfant(choixPseudo, choixEmail, choixDate, RS, jParent, choixPlateforme); 
		if(nouveauJoueur.getPseudo().equals("")) {	
		}
	}

	/**
	 * Connection � un compte gr�ce � un pseudo et affichage actions possibles
	 * @param RS : Reseau Social utilis�
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 * @throws ProblemeMisEnCommunException : Exception probleme mise en commun d'infos
	 */
	public static void seConnecter(ReseauSocial RS) throws ProblemeAffichageException, ProblemeMisEnCommunException {
		String choixPseudo;
		System.out.println("Saisissez un pseudo pour vous connecter � votre compte : ");	
		choixPseudo = scan.next();
		while(RS.getListePseudo().contains(choixPseudo) == false) {
				System.err.println(choixPseudo + " : Pseudo inexistant.");
				System.out.println("Saisissez un pseudo pour vous connecter � votre compte : ");	
				choixPseudo = scan.next();
		}
		Joueur joueur = RS.getListePseudoJoueur().get(choixPseudo);
		if(joueur instanceof Enfant) {
			Enfant joueurConnecte = (Enfant)joueur;
			actionsCompte(RS, joueurConnecte);
		}
		else {
			JHumainParent joueurConnecte = (JHumainParent)joueur;
			actionsCompte(RS, joueurConnecte);
		}
	}
	
	
	
	
	
	
	/**
	 * Actions compte possible pour JHumainParent
	 * @param RS : Reseau Social utilis�
	 * @param joueurConnecte : Joueur connect� qui acc�de aux actions de son compte   (JHumainParent)
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 * @throws ProblemeMisEnCommunException : Exception probleme mise en commun d'infos
	 */
	public static void actionsCompte(ReseauSocial RS, JHumainParent joueurConnecte) throws ProblemeAffichageException, ProblemeMisEnCommunException  {
		String choix;
		do {
			System.out.println("Compte : Vous �tes connect� en tant que " + joueurConnecte.getPseudo());	
			System.out.println("1 -> Affichage des d�tails du profil");	
			System.out.println("2 -> Amis");
			System.out.println("3 -> Jeux et plateforme");
			System.out.println("4 -> Enfants");
			System.out.println("5 -> Jouer en multijoueur");
			System.out.println("6 -> Informations sur le r�seau social");
			System.out.println("q -> Se d�connecter");
			System.out.println("Veuillez saisir 1, 2, 3, 4, 5, 6 ou q : ");
			choix = scan.next();	
			while(choix.equals("1") == false && choix.equals("2") == false && choix.equals("3") == false && choix.equals("4") == false && choix.equals("5") == false && choix.equals("6") == false && choix.equals("q") == false) {
				System.err.println(choix + " n'est pas d�fini comme fonctionnalit�.");	
				System.out.println("Compte : Vous �tes connect� en tant que " + joueurConnecte.getPseudo());	
				System.out.println("1 -> Affichage des d�tails du profil");	
				System.out.println("2 -> Amis");
				System.out.println("3 -> Jeux et plateforme");
				System.out.println("4 -> Enfants");
				System.out.println("5 -> Jouer en multijoueur");
				System.out.println("6 -> Informations sur le r�seau social");
				System.out.println("q -> Se d�connecter");
				System.out.println("Veuillez saisir 1, 2, 3, 4, 5, 6 ou q : ");
				choix = scan.next();
			}
			if(choix.equals("1")) {
				affichageDetails(RS, joueurConnecte);
			}
			else if(choix.equals("2")) {
				amis(RS, joueurConnecte);
			}
			else if(choix.equals("3")) {
				jeuxPlateforme(RS, joueurConnecte);
			}
			else if(choix.equals("4")) {
				enfant(RS, joueurConnecte);
			}
			else if(choix.equals("5")) {
				jouerEnMultijoueur(RS, joueurConnecte);
			}
			else if(choix.equals("6")) {
				infosRS(RS, joueurConnecte);
			}
		}while(choix.equals("q") == false);
		System.out.println("Le compte " + joueurConnecte.getPseudo() + " vient de se d�connecter.");
	}
	
	
	/**
	 * Actions compte possible pour Enfant
	 * @param RS : Reseau Social utilis�
	 * @param joueurConnecte : Joueur connect� qui acc�de aux actions de son compte  (Enfant)
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 * @throws ProblemeMisEnCommunException : Exception probleme mise en commun d'infos
	 */
	public static void actionsCompte(ReseauSocial RS, Enfant joueurConnecte) throws ProblemeAffichageException, ProblemeMisEnCommunException  {
		String choix;
		do {
			System.out.println("Compte : Vous �tes connect� en tant que " + joueurConnecte.getPseudo());	
			System.out.println("1 -> Affichage des d�tails du profil");	
			System.out.println("2 -> Amis");
			System.out.println("3 -> Jeux et plateforme");
			System.out.println("4 -> Jouer en multijoueur");
			System.out.println("5 -> Informations sur le r�seau social");
			System.out.println("q -> Se d�connecter");
			System.out.println("Veuillez saisir 1, 2, 3, 4, 5 ou q : ");
			choix = scan.next();	
			while(choix.equals("1") == false && choix.equals("2") == false && choix.equals("3") == false && choix.equals("4") == false && choix.equals("5") == false && choix.equals("q") == false) {
				System.err.println(choix + " n'est pas d�fini comme fonctionnalit�.");	
				System.out.println("1 -> Affichage des d�tails du profil");	
				System.out.println("2 -> Amis");
				System.out.println("3 -> Jeux et plateforme");
				System.out.println("4 -> Jouer en multijoueur");
				System.out.println("5 -> Informations sur le r�seau social");
				System.out.println("q -> Se d�connecter");
				System.out.println("Veuillez saisir 1, 2, 3, 4, 5 ou q : ");
				choix = scan.next();
			}
			if(choix.equals("1")) {
				affichageDetails(RS, joueurConnecte);
			}
			else if(choix.equals("2")) {
				amis(RS, joueurConnecte);
			}
			else if(choix.equals("3")) {
				jeuxPlateforme(RS, joueurConnecte);
			}
			else if(choix.equals("4")) {
				jouerEnMultijoueur(RS, joueurConnecte);
			}
			else if(choix.equals("5")) {
				infosRS(RS, joueurConnecte);
			}
		}while(choix.equals("q") == false);
		System.out.println("Le compte " + joueurConnecte.getPseudo() + " vient de se d�connecter.");
	}
	
	
	
	
	/**
	 * Affiche les d�tails que l'utilisateur souhaite savoir
	 * @param RS : Reseau Social utilis�
	 * @param joueurConnecte : Joueur connecte voulant acc�der � ses informations  (JHumainParent)
	 */
	public static void affichageDetails(ReseauSocial RS, JHumainParent joueurConnecte) {
		String choix;
		do {
			System.out.println("Compte : Vous �tes connect� en tant que " + joueurConnecte.getPseudo());	
			System.out.println("1 -> Affichage profil");	
			System.out.println("2 -> Affichage jeux");
			System.out.println("3 -> Affichage plateforme");
			System.out.println("4 -> Affichage amis");
			System.out.println("5 -> Affichage enfants");
			System.out.println("q -> Revenir accueil compte");
			System.out.println("Veuillez saisir 1, 2, 3, 4, 5 ou q : ");
			choix = scan.next();	
			while(choix.equals("1") == false && choix.equals("2") == false && choix.equals("3") == false && choix.equals("4") == false && choix.equals("5") == false && choix.equals("q") == false) {
				System.err.println(choix + " n'est pas d�fini comme fonctionnalit�.");	
				System.out.println("1 -> Affichage profil");	
				System.out.println("2 -> Affichage jeux");
				System.out.println("3 -> Affichage plateforme");
				System.out.println("4 -> Affichage amis");
				System.out.println("5 -> Affichage enfants");
				System.out.println("q -> Revenir accueil compte");
				System.out.println("Veuillez saisir 1, 2, 3, 4, 5 ou q : ");
				choix = scan.next();
			}
			if(choix.equals("1")) {
				affichageProfil(RS, joueurConnecte);
			}
			else if(choix.equals("2")) {
				affichageJeux(RS, joueurConnecte);
			}
			else if(choix.equals("3")) {
				affichagePlateforme(RS, joueurConnecte);
			}
			else if(choix.equals("4")) {
				affichageAmis(RS, joueurConnecte);
			}
			else if(choix.equals("5")) {
				affichageEnfant(RS, joueurConnecte);
			}
		}while(choix.equals("q") == false);
	}
	
	/**
	 * Affiche les d�tails que l'utilisateur souhaite savoir
	 * @param RS : Reseau Social utilis�
	 * @param joueurConnecte : Joueur connecte voulant acc�der � ses informations  (Enfant)
	 */
	public static void affichageDetails(ReseauSocial RS, Enfant joueurConnecte) {
		String choix;
		do {
			System.out.println("Compte : Vous �tes connect� en tant que " + joueurConnecte.getPseudo());	
			System.out.println("1 -> Affichage profil");	
			System.out.println("2 -> Affichage jeux");
			System.out.println("3 -> Affichage plateforme");
			System.out.println("4 -> Affichage amis");
			System.out.println("q -> Revenir accueil compte");
			System.out.println("Veuillez saisir 1, 2, 3, 4 ou q : ");
			choix = scan.next();	
			while(choix.equals("1") == false && choix.equals("2") == false && choix.equals("3") == false && choix.equals("4") == false && choix.equals("q") == false) {
				System.err.println(choix + " n'est pas d�fini comme fonctionnalit�.");	
				System.out.println("1 -> Affichage profil");	
				System.out.println("2 -> Affichage jeux");
				System.out.println("3 -> Affichage plateforme");
				System.out.println("4 -> Affichage amis");
				System.out.println("q -> Revenir accueil compte");
				System.out.println("Veuillez saisir 1, 2, 3, 4 ou q : ");
				choix = scan.next();
			}
			if(choix.equals("1")) {
				affichageProfil(RS, joueurConnecte);
			}
			else if(choix.equals("2")) {
				affichageJeux(RS, joueurConnecte);
			}
			else if(choix.equals("3")) {
				affichagePlateforme(RS, joueurConnecte);
			}
			else if(choix.equals("4")) {
				affichageAmis(RS, joueurConnecte);
			}
		}while(choix.equals("q") == false);
	}
	
	/**
	 * @param RS : r�seau social sur lequel on est 
	 * @param joueurConnecte : joueur dont on veut afficher le profil
	 */
	public static void affichageProfil(ReseauSocial RS, JHumain joueurConnecte) {
		System.out.println(joueurConnecte.toString());
	}
	
	/**
	 * @param RS : r�seau social sur lequel on est 
	 * @param joueurConnecte : joueur dont on veut afficher les jeux
	 */
	public static void affichageJeux(ReseauSocial RS, JHumain joueurConnecte) {
		System.out.println(joueurConnecte.affichageJeux());
	}
	
	/**
	 * @param RS : r�seau social sur lequel on est 
	 * @param joueurConnecte : joueur dont on veut afficher les plateformes
	 */
	public static void affichagePlateforme(ReseauSocial RS, JHumain joueurConnecte) {
		System.out.println("Voici vos plateformes : " + joueurConnecte.getPlateforme());
	}
	
	/**
	 * @param RS : r�seau social sur lequel on est 
	 * @param joueurConnecte : joueur dont on veut afficher les amis
	 */
	public static void affichageAmis(ReseauSocial RS, JHumain joueurConnecte) {
		System.out.println("Voici vos amis : " + joueurConnecte.getAmis());
	}
	
	/**
	 * @param RS : r�seau social sur lequel on est 
	 * @param joueurConnecte : joueur dont on veut afficher les enfants
	 */
	public static void affichageEnfant(ReseauSocial RS, JHumainParent joueurConnecte) {
		System.out.println("Voici vos enfants : " + joueurConnecte.getEnfants());
	}


	
	
	
	
	/**
	 * Affiche les possibilit�s sur les amis
	 * @param RS : Reseau Social utilis�
	 * @param joueurConnecte : Joueur connecte voulant acc�der � ses amis  (JHumainParent)
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 */
	public static void amis(ReseauSocial RS, JHumainParent joueurConnecte) throws ProblemeAffichageException {
		String choix;
		do {
			System.out.println("Compte : Vous �tes connect� en tant que " + joueurConnecte.getPseudo());	
			System.out.println("1 -> Affichage amis");	
			System.out.println("2 -> Inviter un ami");
			System.out.println("3 -> Supprimer un ami");
			System.out.println("4 -> Offrir jeu � un ami");
			System.out.println("5 -> Affichage jeux en commun avec un ami");
			System.out.println("6 -> Affichage plateforme en commun avec un ami");
			System.out.println("q -> Revenir accueil compte");
			System.out.println("Veuillez saisir 1, 2, 3, 4, 5, 6 ou q : ");
			choix = scan.next();	
			while(choix.equals("1") == false && choix.equals("2") == false && choix.equals("3") == false && choix.equals("4") == false && choix.equals("5") == false && choix.equals("6") == false && choix.equals("q") == false) {
				System.err.println(choix + " n'est pas d�fini comme fonctionnalit�.");	
				System.out.println("1 -> Affichage amis");	
				System.out.println("2 -> Inviter un ami");
				System.out.println("3 -> Supprimer un ami");
				System.out.println("4 -> Offrir jeu � un ami");
				System.out.println("5 -> Affichage jeux en commun avec un ami");
				System.out.println("6 -> Affichage plateforme en commun avec un ami");
				System.out.println("q -> Revenir accueil compte");
				System.out.println("Veuillez saisir 1, 2, 3, 4, 5, 6 ou q : ");
				choix = scan.next();
			}
			if(choix.equals("1")) {
				affichageAmis(RS, joueurConnecte);
			}
			else if(choix.equals("2")) {
				joueurConnecte.invitAmis();
			}
			else if(choix.equals("3")) {
				if(joueurConnecte.getAmis().isEmpty()) {
					System.err.println("Vous n'avez pas encore ajout� d'ami.");
				}
				else {
					joueurConnecte.supprAmis();
				}
			}
			else if(choix.equals("4")) {
				if(joueurConnecte.getAmis().isEmpty()) {
					System.err.println("Vous n'avez pas encore ajout� d'ami.");
				}
				else {
					System.out.println("Voici la liste de vos amis : ");
					System.out.println(joueurConnecte.getAmis());
					System.out.println("Choisissez un ami pour offrir votre jeu : ");
					choix = scan.next();	
					while(joueurConnecte.getAmis().contains(choix) == false) {
						System.err.println(choix + " n'est pas un de vos amis.");
						System.out.println("Choisissez un ami pour offrir votre jeu : ");
						System.out.println(joueurConnecte.getAmis());
						choix = scan.next();
					}
					JHumain ami = (JHumain)joueurConnecte.getJoueur(choix, RS);
					joueurConnecte.offrirJeux(ami);
				}
			}
			else if(choix.equals("5")) {
				affichageJeuEnCommun(RS, joueurConnecte);
			}
			else if(choix.equals("6")) {
				affichagePlateformeEnCommun(RS, joueurConnecte);
			}
		}while(choix.equals("q") == false);
	}

	/**
	 * Affiche les possibilit�s sur les amis
	 * @param RS : Reseau Social utilis�
	 * @param joueurConnecte : Joueur connecte voulant acc�der � ses amis  (Enfant)
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 */
	public static void amis(ReseauSocial RS, Enfant joueurConnecte) throws ProblemeAffichageException {
		String choix;
		do {
			System.out.println("Compte : Vous �tes connect� en tant que " + joueurConnecte.getPseudo());	
			System.out.println("1 -> Affichage amis");	
			System.out.println("2 -> Inviter un ami");
			System.out.println("3 -> Supprimer un ami");
			System.out.println("4 -> Affichage jeux en commun avec un ami");
			System.out.println("5 -> Affichage plateforme en commun avec un ami");
			System.out.println("q -> Revenir accueil compte");
			System.out.println("Veuillez saisir 1, 2, 3, 4, 5 ou q : ");
			choix = scan.next();	
			while(choix.equals("1") == false && choix.equals("2") == false && choix.equals("3") == false && choix.equals("4") == false && choix.equals("5") == false && choix.equals("q") == false) {
				System.err.println(choix + " n'est pas d�fini comme fonctionnalit�.");	
				System.out.println("1 -> Affichage amis");	
				System.out.println("2 -> Inviter un ami");
				System.out.println("3 -> Supprimer un ami");
				System.out.println("4 -> Affichage jeux en commun avec un ami");
				System.out.println("5 -> Affichage plateforme en commun avec un ami");
				System.out.println("q -> Revenir accueil compte");
				System.out.println("Veuillez saisir 1, 2, 3, 4, 5 ou q : ");
				choix = scan.next();
			}
			if(choix.equals("1")) {
				affichageAmis(RS, joueurConnecte);
			}
			else if(choix.equals("2")) {
				joueurConnecte.invitAmis();
			}
			else if(choix.equals("3")) {
				if(joueurConnecte.getAmis().isEmpty()) {
					System.err.println("Vous n'avez pas encore ajout� d'ami.");
				}
				else {
					joueurConnecte.supprAmis();
				}
			}
			else if(choix.equals("4")) {
				affichageJeuEnCommun(RS, joueurConnecte);
			}
			else if(choix.equals("5")) {
				affichagePlateformeEnCommun(RS, joueurConnecte);
			}
		}while(choix.equals("q") == false);
	}
	
	/**
	 * Affiche jeu en Commun avec un joueur
	 * @param RS : Reseau Social utilis�
	 * @param joueurConnecte : Joueur connect� qui veut conna�tre jeu en commun avec un ami
	 */
	public static void affichageJeuEnCommun(ReseauSocial RS, JHumain joueurConnecte) {
		JHumain choixJoueur;
		String choixString;
		ArrayList<String> amis = joueurConnecte.getAmis();
		if(amis.isEmpty()) {
			System.err.println("Vous n'avez pas encore ajout� d'ami.");
		}
		else {
			affichageAmis(RS, joueurConnecte);
			System.out.println("Choisissez un ami pour r�aliser la fonctionnalit� : ");
			choixString = scan.next();
			while(joueurConnecte.getAmis().contains(choixString) == false) {
				System.err.println(choixString + " ne fait pas parti de vos amis.");
				affichageAmis(RS, joueurConnecte);			
				System.out.println("Choisissez un ami pour r�aliser la fonctionnalit� : ");
				choixString = scan.next();
			}
			choixJoueur = (JHumain)joueurConnecte.getJoueur(choixString, RS);
			String plateforme;
			if(joueurConnecte.affichagePlateformeEnCommunAvecAmis(choixJoueur) == null) {
			}
			else {
				System.out.println(joueurConnecte.affichagePlateformeEnCommunAvecAmis(choixJoueur));
				System.out.println("Voici les plateformes en commun avec le joueur " + choixJoueur.getPseudo());
				System.out.println("Choisissez une plateforme pour r�aliser la fonctionnalit� : ");
				plateforme = scan.next();
				while(joueurConnecte.getPlateforme().contains(plateforme) == false) {
					System.err.println(plateforme + " n'est pas une plateforme en commun avec " + joueurConnecte + ".");
					System.out.println("Choisissez un plateforme pour r�aliser la fonctionnalit� : ");
					plateforme = scan.next();
				}
				choixJoueur = (JHumainParent)joueurConnecte.getJoueur(choixString, RS);
				System.out.println("Voici vos jeux en commun avec " + joueurConnecte.getPseudo() + " sur la plateforme " + plateforme + " : ");
				System.out.println(joueurConnecte.affichageJeuxEnCommunAvecAmisSelonPlateforme(choixJoueur, plateforme));
			}
		}
	}
	
	/**
	 * Affiche jeu en Commun avec un joueur
	 * @param RS : Reseau Social utilis�
	 * @param joueurConnecte : Joueur connect� qui veut conna�tre plateforme en commun avec un ami
	 */
	public static void affichagePlateformeEnCommun(ReseauSocial RS, JHumain joueurConnecte) {
		JHumainParent choixJoueur;
		String choixString;
		ArrayList<String> amis = joueurConnecte.getAmis();
		if(amis.isEmpty()) {
			System.err.println("Vous n'avez pas encore ajout� d'ami.");
		}
		else {
			affichageAmis(RS, joueurConnecte);
			System.out.println("Choisissez un ami pour r�aliser la fonctionnalit� : ");
			choixString = scan.next();
			while(joueurConnecte.getAmis().contains(choixString) == false) {
				System.err.println(choixString + " ne fait pas parti de vos amis.");
				affichageAmis(RS, joueurConnecte);
				System.out.println("Choisissez un ami pour r�aliser la fonctionnalit� : ");
				choixString = scan.next();
			}
			choixJoueur = (JHumainParent)joueurConnecte.getJoueur(choixString, RS);
			if(joueurConnecte.affichagePlateformeEnCommunAvecAmis(choixJoueur) == null) {
			}
			else {
				System.out.println("Voici vos plateformes en communs avec " + choixJoueur.getPseudo());
				System.out.println(joueurConnecte.affichagePlateformeEnCommunAvecAmis(choixJoueur));
			}
		}
	}
	
	
	
	
	
	/**
	 * Affichage Jeux et Plateforme de JHumainParent
	 * @param RS : Reseau Social utilis�
	 * @param joueurConnecte : Joueur connecte voulant acc�der � ses plateformes (JHumainParent)
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 */
	public static void jeuxPlateforme(ReseauSocial RS, JHumainParent joueurConnecte) throws ProblemeAffichageException {
		String choix;
		do {
			System.out.println("Compte : Vous �tes connect� en tant que " + joueurConnecte.getPseudo());	
			System.out.println("1 -> Affichage plateforme");	
			System.out.println("2 -> Affichage jeux");
			System.out.println("3 -> Ajout plateforme ");
			System.out.println("4 -> Ajout jeux");
			System.out.println("5 -> Ajout selon cat�gorie");
			System.out.println("6 -> Supprimer Jeux");
			System.out.println("q -> Revenir accueil compte");
			System.out.println("Veuillez saisir 1, 2, 3, 4, 5, 6 ou q : ");
			choix = scan.next();	
			while(choix.equals("1") == false && choix.equals("2") == false && choix.equals("3") == false && choix.equals("4") == false && choix.equals("5") == false && choix.equals("6") == false && choix.equals("q") == false) {
				System.err.println(choix + " n'est pas d�fini comme fonctionnalit�.");	
				System.out.println("1 -> Affichage plateforme");	
				System.out.println("2 -> Affichage jeux");
				System.out.println("3 -> Ajout plateforme ");
				System.out.println("4 -> Ajout jeux");
				System.out.println("5 -> Ajout selon cat�gorie");
				System.out.println("6 -> Supprimer Jeux");
				System.out.println("q -> Revenir accueil compte");
				System.out.println("Veuillez saisir 1, 2, 3, 4, 5, 6 ou q : ");
				choix = scan.next();
			}
			if(choix.equals("1")) {			
				System.out.println("Voici la liste de vos plateforme : " +joueurConnecte.getPlateforme());
			}
			else if(choix.equals("2")) {
				System.out.println(joueurConnecte.affichageJeux());
			}
			else if(choix.equals("3")) {
				String plateformeRS = RS.getJeux().affichagePlateforme();
				System.out.println(plateformeRS);
				System.out.println("Quelle plateforme voulez vous ajouter ?");
				choix = scan.next();
				while(plateformeRS.contains(choix) == false) {
					System.err.println(choix + " n'est pas une plateforme de ce r�seau social.");
					System.out.println(plateformeRS);
					System.out.println("Quelle plateforme voulez vous ajouter ?");
					choix = scan.next();
				}
				joueurConnecte.ajoutPlateforme(choix);
			}
			else if(choix.equals("4")) {
				joueurConnecte.ajoutJeux();
			}
			else if(choix.equals("5")) {
				joueurConnecte.ajoutJeuxSelonCategorie();
			}
			else if(choix.equals("6")) {
				if(joueurConnecte.getNbrJeux() == 0) {
					System.err.println("Votre collection de jeu est vide.");
				}
				else {
					joueurConnecte.supprJeux();
				}
			}
		}while(choix.equals("q") == false);
	}
	
	/**
	 * Affichage Jeux et Plateforme de Enfant
	 * @param RS : Reseau Social utilis�
	 * @param joueurConnecte : Joueur connecte voulant acc�der � ses plateformes  (Enfant)
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 */
	public static void jeuxPlateforme(ReseauSocial RS, Enfant joueurConnecte) throws ProblemeAffichageException {
		String choix;
		do {
			System.out.println("Compte : Vous �tes connect� en tant que " + joueurConnecte.getPseudo());	
			System.out.println("1 -> Affichage plateforme");	
			System.out.println("2 -> Affichage jeux");
			System.out.println("3 -> Ajout plateforme ");
			System.out.println("4 -> Supprimer Jeux");
			System.out.println("q -> Revenir accueil compte");
			System.out.println("Veuillez saisir 1, 2, 3, 4 ou q : ");
			choix = scan.next();	
			while(choix.equals("1") == false && choix.equals("2") == false && choix.equals("3") == false && choix.equals("4") == false && choix.equals("q") == false) {
				System.err.println(choix + " n'est pas d�fini comme fonctionnalit�.");	
				System.out.println("1 -> Affichage plateforme");	
				System.out.println("2 -> Affichage jeux");
				System.out.println("3 -> Ajout plateforme ");
				System.out.println("4 -> Supprimer Jeux");
				System.out.println("q -> Revenir accueil compte");
				System.out.println("Veuillez saisir 1, 2, 3, 4 ou q : ");
				choix = scan.next();
			}
			if(choix.equals("1")) {			
				System.out.println("Voici la liste de vos plateforme : " + joueurConnecte.getPlateforme());
			}
			else if(choix.equals("2")) {
				System.out.println(joueurConnecte.affichageJeux());
			}
			else if(choix.equals("3")) {
				String plateformeRS = RS.getJeux().affichagePlateforme();
				System.out.println(plateformeRS);
				System.out.println("Quelle plateforme voulez vous ajouter ?");
				choix = scan.next();
				while(plateformeRS.contains(choix) == false) {
					System.err.println(choix + " n'est pas une plateforme de ce r�seau social.");
					System.out.println(plateformeRS);
					System.out.println("Quelle plateforme voulez vous ajouter ?");
					choix = scan.next();
				}
				joueurConnecte.ajoutPlateforme(choix);
			}
			else if(choix.equals("4")) {
				if(joueurConnecte.getNbrJeux() == 0) {
					System.err.println("Votre collection de jeu est vide.");
				}
				else {
					joueurConnecte.supprJeux();
				}
			}
		}while(choix.equals("q") == false);
	}
	
	/**
	 * Affichage des enfants du joueur connecte (JHumainParent)
	 * @param RS : Reseau Social utilis�
	 * @param joueurConnecte : Joueur connecte voulant acc�der � ses enfants  (JHumainParent)
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 * @throws ProblemeMisEnCommunException : Exception probleme mise en commun d'infos
	 */
	public static void enfant(ReseauSocial RS, JHumainParent joueurConnecte) throws ProblemeAffichageException, ProblemeMisEnCommunException {
		String choix;
		do {
			System.out.println("1 -> Affichage liste enfants");	
			System.out.println("2 -> Cr�er Compte enfant");
			System.out.println("3 -> Ajouter deuxi�me parent � un enfant");
			System.out.println("4 -> Offrir jeu � un enfant");
			System.out.println("5 -> Affichage d�tails d'un enfant");
			System.out.println("q -> Revenir accueil compte");
			System.out.println("Veuillez saisir 1, 2, 3, 4, 5 ou q : ");
			choix = scan.next();	
			while(choix.equals("1") == false && choix.equals("2") == false && choix.equals("3") == false && choix.equals("4") == false && choix.equals("5") == false && choix.equals("q") == false) {
				System.err.println(choix + " n'est pas d�fini comme fonctionnalit�.");	
				System.out.println("1 -> Affichage liste enfants");	
				System.out.println("2 -> Cr�er Compte enfant");
				System.out.println("3 -> Ajouter deuxi�me parent � un enfant");
				System.out.println("4 -> Offrir jeu � un enfant");
				System.out.println("5 -> Affichage d�tails d'un enfant");
				System.out.println("q -> Revenir accueil compte");
				System.out.println("Veuillez saisir 1, 2, 3, 4, 5 ou q : ");
				choix = scan.next();	
			}
			if(choix.equals("1")) {
				System.out.println("Voici vos enfants : ");
				System.out.println(joueurConnecte.getEnfants());		
			}
			else if(choix.equals("2")) {
				creerCompteEnfant(RS, joueurConnecte);
			}
			else if(choix.equals("3")) {
				Enfant enfant;
				if(joueurConnecte.getEnfants().isEmpty()) {
					System.err.println("Vous n'avez pas encore ajout� d'enfant.");
				}
				else {
					System.out.println("Voici la liste de vos enfants : ");
					System.out.println(joueurConnecte.getEnfants());
					System.out.println("Choisissez un enfant pour offrir votre jeu : ");
					choix = scan.next();	
					while(joueurConnecte.getEnfants().contains(choix) == false) {
						System.err.println(choix + " n'est pas un de vos enfants.");
						System.out.println("Voici la liste de vos enfants : ");
						System.out.println(joueurConnecte.getEnfants());
						System.out.println("Choisissez un enfant pour offrir votre jeu : ");
						choix = scan.next();
					}
					enfant = (Enfant)joueurConnecte.getJoueur(choix, RS);
					String choixParent;
					// Enlever le nom du joueur qui est en train d'inviter un ami
					String listePseudo = joueurConnecte.getReseauSocial().getPseudoMajeur();
					if(listePseudo.contains("[" + joueurConnecte.getPseudo()+",")){
						listePseudo = listePseudo.replace(joueurConnecte.getPseudo() + ",", "");
					}
					else if(listePseudo.contains(joueurConnecte.getPseudo()+",")){
						listePseudo = listePseudo.replace(joueurConnecte.getPseudo() + ",", ",");
					}
					else if(listePseudo.contains(joueurConnecte.getPseudo()+"]")){
						listePseudo = listePseudo.replace(joueurConnecte.getPseudo() + "]", "]");
					}
					System.out.println("Voici la liste des joueurs qui peuvent devenir le deuxi�me parent de " + enfant.getPseudo() + " : ");
					System.out.println(listePseudo);
					System.out.println("Choisissez le deuxi�me parent : ");
					choixParent = scan.next();
					if(listePseudo.contains(choixParent) == false) {
						System.err.println(choixParent + " n'est pas �ligible � �tre un parent de " + enfant.getPseudo() + ".");
						System.out.println("Voici la liste des joueurs qui peuvent devenir le deuxi�me parent de " + enfant.getPseudo() + " : ");
						System.out.println(listePseudo);
						System.out.println("Choisissez le deuxi�me parent : ");
						choixParent = scan.next();
					}
					enfant.setDeuxi�meParent(choixParent);
				}
				
			}
			else if(choix.equals("4")) {
				if(joueurConnecte.getEnfants().isEmpty()) {
					System.err.println("Vous n'avez pas encore ajouter d'enfant.");
				}
				else {
					System.out.println("Voici la liste de vos enfants : ");
					System.out.println(joueurConnecte.getEnfants());
					System.out.println("Choisissez un enfant pour offrir votre jeu : ");
					choix = scan.next();	
					while(joueurConnecte.getEnfants().contains(choix) == false) {
						System.err.println(choix + " n'est pas un de vos enfants.");
						System.out.println("Choisissez un enfant pour offrir votre jeu : ");
						System.out.println(RS.getJeux().affichageCategorie());
						choix = scan.next();
					}
					JHumain nomEnfant = (JHumain)joueurConnecte.getJoueur(choix, RS);
					joueurConnecte.offrirJeux(nomEnfant);
				}
			}
			else if(choix.equals("5")) {
				if(joueurConnecte.getEnfants().isEmpty()) {
					System.err.println("Vous n'avez pas encore ajouter d'enfant.");
				}
				else {
					System.out.println("Voici vos enfants : ");
					System.out.println(joueurConnecte.getEnfants());
					System.out.println("Choisissez un enfant pour afficher son profil : ");
					choix = scan.next();	
					while(joueurConnecte.getEnfants().contains(choix) == false) {
						System.err.println(choix + " n'est pas un de vos enfants.");
						System.out.println("Voici vos enfants : ");
						System.out.println(joueurConnecte.getEnfants());
						System.out.println("Choisissez un enfant pour afficher son profil : ");
						//System.out.println(RS.getJeux().affichageCategorie());
						choix = scan.next();
					}
					JHumain nomEnfant = (JHumain)joueurConnecte.getJoueur(choix, RS);
					System.out.println(nomEnfant.toString());
				}
			}
		}while(choix.equals("q") == false);
	}
	
	
	/**
	 * Affichage jouer en multijoueur pour un joueur JHumainParent
	 * @param RS : Reseau Social utilis�
	 * @param joueurConnecte : Joueur connecte voulant jouer en multijoueur  (JHumainParent)
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 * @throws ProblemeMisEnCommunException : Exception probleme mise en commun d'infos
	 */
	public static void jouerEnMultijoueur(ReseauSocial RS, JHumainParent joueurConnecte) throws ProblemeAffichageException, ProblemeMisEnCommunException {
		String choix;
		do {
			System.out.println("1 -> Jouer en Multijoueur avec un ami");	
			System.out.println("2 -> Jouer en Multijoueur avec un bot");
			System.out.println("q -> Revenir accueil compte");
			System.out.println("Veuillez saisir 1, 2 ou q : ");
			choix = scan.next();	
			while(choix.equals("1") == false && choix.equals("2") == false && choix.equals("q") == false) {
				System.err.println(choix + " n'est pas d�fini comme fonctionnalit�.");	
				System.out.println("1 -> Jouer en Multijoueur avec un ami");	
				System.out.println("2 -> Jouer en Multijoueur avec un bot");
				System.out.println("q -> Revenir accueil compte");
				System.out.println("Veuillez saisir 1, 2 ou q : ");
				choix = scan.next();
			}
			if(choix.equals("1")) {
				if(joueurConnecte.getAmis().isEmpty()) {
					System.err.println("Vous n'avez pas encore ajout� d'ami. Essayer de jouer avec un bot");
				}
				else {
					joueurConnecte.jeuMultijoueurAvecAmi();
				}
			}
			else if(choix.equals("2")) {
				joueurConnecte.jeuMultijoueurAvecBot();
			}
		}while(choix.equals("q") == false);
	}
	
	
	/**
	 * Affichage jouer en multijoueur pour un joueur Enfant
	 * @param RS : Reseau Social utilis�
	 * @param joueurConnecte : Joueur connecte voulant jouer en multijoueur  (Enfant)
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 * @throws ProblemeMisEnCommunException : Exception probleme mise en commun d'infos
	 */
	public static void jouerEnMultijoueur(ReseauSocial RS, Enfant joueurConnecte) throws ProblemeAffichageException, ProblemeMisEnCommunException {
		String choix;
		do {
			System.out.println("1 -> Jouer en Multijoueur avec un ami");	
			System.out.println("q -> Revenir accueil compte");
			System.out.println("Veuillez saisir 1 ou q : ");
			choix = scan.next();	
			while(choix.equals("1") == false && choix.equals("q") == false) {
				System.err.println(choix + " n'est pas d�fini comme fonctionnalit�.");	
				System.out.println("1 -> Jouer en Multijoueur avec un ami");	
				System.out.println("q -> Revenir accueil compte");
				System.out.println("Veuillez saisir 1 ou q : ");
				choix = scan.next();
			}
			if(choix.equals("1")) {
				if(joueurConnecte.getAmis().isEmpty()) {
					System.err.println("Vous n'avez pas encore ajout� d'ami. Essayer de jouer avec un bot");
				}
				else {
					joueurConnecte.jeuMultijoueurAvecAmi();
				}
			}
		}while(choix.equals("q") == false);
	}
	
	
	/**
	 * Affichage info du reseau social
	 * @param RS : Reseau Social utilis�
	 * @param joueurConnecte : Joueur connect� qui cherche � acc�der aux informations du R�seau Social  (Enfant)
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 * @throws ProblemeMisEnCommunException : Exception probleme mise en commun d'infos
	 */		    
	public static void infosRS(ReseauSocial RS, JHumain joueurConnecte) throws ProblemeAffichageException, ProblemeMisEnCommunException {
	String choix;
		do {
			System.out.println("1 -> Affichage plateforme");	
			System.out.println("2 -> Affichage jeux par cat�gorie");
			System.out.println("3 -> Affichage jeux par plateforme");
			System.out.println("4 -> Affichage d�tails d'un jeu par son nom");
			System.out.println("5 -> Affichage d�tails d'un jeu par son identifiant");
			System.out.println("q -> Revenir accueil compte");
			System.out.println("Veuillez saisir 1, 2, 3, 4, 5 ou q : ");
			choix = scan.next();	
			while(choix.equals("1") == false && choix.equals("2") == false && choix.equals("3") == false && choix.equals("4") == false && choix.equals("5") == false && choix.equals("q") == false) {
				System.err.println(choix + " n'est pas d�fini comme fonctionnalit�.");	
				System.out.println("1 -> Affichage plateforme");	
				System.out.println("2 -> Affichage jeux par cat�gorie");
				System.out.println("3 -> Affichage jeux par plateforme");
				System.out.println("4 -> Affichage d�tails d'un jeu par son nom");
				System.out.println("5 -> Affichage d�tails d'un jeu par son identifiant");
				System.out.println("q -> Revenir accueil compte");
				System.out.println("Veuillez saisir 1, 2, 3, 4, 5 ou q : ");
				choix = scan.next();
			}
			if(choix.equals("1")){
				System.out.println(RS.getJeux().affichagePlateforme());
			}
			else if(choix.equals("2")) {
				System.out.println(RS.getJeux().affichageCategorie());
				System.out.println("Choisissez la cat�gorie de jeu dont vous voulez conna�tre les jeux : ");
				choix = scan.next();	
				while(RS.getJeux().affichageCategorie().contains(choix) == false) {
					System.err.println(choix + " n'est pas une cat�gorie de jeu de ce r�seau social");
					System.out.println("Choisissez la cat�gorie de jeu dont vous voulez conna�tre les jeux : ");
					System.out.println(RS.getJeux().affichageCategorie());
					choix = scan.next();
				}
				RS.getJeux().affichageJeuxParCategorie(choix);
			}
			else if(choix.equals("3")) {
				System.out.println(RS.getJeux().affichagePlateforme());
				System.out.println("Choisissez la plateforme dont vous voulez conna�tre les jeux : ");
				choix = scan.next();	
				while(RS.getJeux().affichagePlateforme().contains(choix) == false) {
					System.err.println(choix + " n'est pas une plateforme de ce r�seau social");
					System.out.println(RS.getJeux().affichagePlateforme());
					System.out.println("Choisissez la plateforme dont vous voulez conna�tre les jeux : ");
					choix = scan.next();
				}
				RS.getJeux().affichageJeuxParPlateforme(choix);
			}
			else if(choix.equals("4")) {
				String choixJeu;
				System.out.println("Affichez les d�tails d'un jeu gr�ce � son nom : "); 
				scan.nextLine();
				choixJeu = scan.nextLine();// Permet d'avoir la possibilit� d'une saisie clavier
				System.out.println(RS.getJeux().affichageJeux(choixJeu));
			}
			else if(choix.equals("5")) {
				int choixJeu;
				System.out.println("Affichez les d�tails d'un jeu gr�ce � son identifiant : ");
				choixJeu = scan.nextInt();// Permet d'avoir la possibilit� d'une saisie clavier
				System.out.println(RS.getJeux().affichageJeux(choixJeu));
			}
		}while(choix.equals("q") == false);
	}

	
	
	/**
	 * Main pour g�rer interface avec le client
	 * @param args : arguments pass�s en param�tre
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	 * @throws ProblemeMisEnCommunException : Exception probleme mise en commun d'infos
	 */
	public static void main(String[] args) throws ProblemeAffichageException, ProblemeMisEnCommunException {
			lancement();
			Jeux Jeu = new Jeux();
			Jeu.readFile("jeux_final.tsv"); 
			ReseauSocial R = new ReseauSocial(Jeu);
			System.out.println("\n \n");
			
			g1 = new Gold("ya", "ya@g.com", LocalDate.parse("2000-03-22") , R, "PS3");
			g2 = new Gold("yo", "ya@g.com", LocalDate.parse("2000-04-21") , R, "PSV");
			s1 = new Standard("yazerty", "ya@g.com", LocalDate.parse("2002-02-20") , R, "PS4");
			accueil(R);
			if(g1.getPseudo().equals("") && g2.getPseudo().equals("") && s1.getPseudo().equals("")) {
				//Pas de warning
			}
	}

}
