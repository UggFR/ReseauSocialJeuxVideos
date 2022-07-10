package com.appli;

// Import 
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



// Exception pour les probl�mes dus aux pseudos
@SuppressWarnings("serial")
class ProblemePseudoException extends Exception {
	public ProblemePseudoException(String message) {
		super(message);
	}
}



/**
 * @author gifhu
 *
 */
public class Joueur {
	// Variables de Joueur
	private String pseudo;
	private int nbrMaxAmis;
	private int nbrMaxJeux;
	private int nbrMaxPartiesMulti;
	private boolean acquerirOuOffrirJeux;
	private boolean inviterAmis;
	private boolean inviterEnfants;
	private ArrayList<String> amis = new ArrayList<String>(); // Remplacer tous les arraylist par des map
	private ReseauSocial RS;
	//Scanner est ouvert ici et permet de faire toutes les prises d'entr�e dans les classe filles de cette classe
    static final Scanner scan = new Scanner(System.in);

	
    
    /** 
	*   Constructeur
	*   @param ps : pseudo qu'on veut avoir
	*   @param nbrJ : nombre Jeu max
	*   @param nbrA : nombre Ami max
	*   @param nbrP : nombre Parti Multijoueur max
	*   @param acqOffJeux : vrai si l'on peut acqu�rir et offrir des jeux
	*   @param invitA : vrai si l'on peut invit� un enfant
	*   @param invitE : vrai si l'on peut invit� un enfant
	*   @param R : le r�seua social sur lequel on se trouve
	*/
	public Joueur(String ps, int nbrJ, int nbrA, int nbrP, boolean acqOffJeux, boolean invitA, boolean invitE, ReseauSocial R){
		try {
			// Retourne une exception si le pseudo est d�j� utilis�
			if(existenceJoueur(ps,R) == true){
	            throw new ProblemePseudoException(ps + " : Pseudo d�j� utilis�.");
			}
			// Pseudo q et null non valide pour pouvoir les utiliser 
			else if(ps.equals("q") || ps.equals("null")) {
	            throw new ProblemePseudoException(ps + " n'est pas un pseudo valide.");
			}
			// Sinon on cr�� le Joueur
			pseudo               = ps;
			nbrMaxJeux           = nbrJ; 
			nbrMaxAmis           = nbrA;
			nbrMaxPartiesMulti   = nbrP;
			acquerirOuOffrirJeux = acqOffJeux;
			inviterAmis          = invitA;
			inviterEnfants       = invitE;
			RS                   = R;
			if(this instanceof Bot) {
				RS.addPseudoBot(ps, (Bot)this);
			}
			else {
				RS.addPseudoJoueur(ps, this);
			}
		}
		catch(ProblemePseudoException e) {
			// Cas o� le pseudo est d�j� utilis�
			String pseudoDemande = ps;
			// Boucle do while pour valider un pseudo unique pour ce profil
			do { 
				// On transmet l'exception selon le cas et demande � l'utilisateur d'�crire un nouveau pseudo
				if(pseudoDemande.equals("q") || pseudoDemande.equals("null")){
					System.err.println(pseudoDemande + " n'est pas un pseudo valide.");
				}
				else{
					System.err.println(ps + " : Pseudo d�j� utilis�.");
				}				
				System.out.println("Choisissez un autre pseudo :");
				pseudoDemande = scan.next();
			}while(existenceJoueur(pseudoDemande,R) == true || pseudoDemande.equals("q") || pseudoDemande.equals("null"));
			// Construire le profil avec le pseudo modifie
			pseudo               = pseudoDemande;
			nbrMaxJeux           = nbrJ; 
			nbrMaxAmis           = nbrA;
			nbrMaxPartiesMulti   = nbrP;
			acquerirOuOffrirJeux = acqOffJeux;
			inviterAmis          = invitA;
			inviterEnfants       = invitE;
			RS                   = R;
			if(this instanceof Bot) {
				RS.addPseudoBot(ps, (Bot)this);
			}
			else {
				RS.addPseudoJoueur(ps, this);
			}
		}
	}
	
	
	
	
	// Get et Set :	
	
	/**
	 * @return boolean vrai si on peut acqu�rir et offrir jeu
	 */
	public boolean getAcquerirOuOffrirJeux(){
		return acquerirOuOffrirJeux;
	}
	
	/**
	 * @return boolean vrai si on peut inviter ami
	 */
	public boolean getInviterAmis(){
		return inviterAmis;
	}
	
	/**
	 * @return boolean vrai si on peut inviter enfants
	 */
	public boolean getInviterEnfants(){
		return inviterEnfants;
	}
	
	/**
	 * @return String le pseudo du joueur
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * @param ps : le pseudo du joueur
	 */
	public void setPseudo(String ps) {
		pseudo = ps;
	}
	
	/**
	 * @return String exception sur le pseudo du joueur
	 */
	public String affichagePseudoException() {
		return "(" + pseudo +") ";
	}
	
	/**
	 * @return ArrayList liste des amis
	 */
	public ArrayList<String> getAmis() {
		return amis;
	}
	
	/**
	 * @param A : pseudo du nouvel ami
	 */
	public void setAmis(String A) {
		amis.add(A);
	}
	
	/**
	 * Mettre les amis � null
	 */
	public void setAmisNull() {
		amis = null;
	}
	
	/**
	 * @param pseudo : pseudo de l'ami que l'on veut supprimer
	 */
	public void supAmis(String pseudo) {
		amis.remove(pseudo);
	}
	
	/**
	 * @return int le nombre d'amis maximum
	 */
	public int getNbrMaxAmis() {
		return nbrMaxAmis;
	}
	
	/**
	 * @param Amis : le nombres d'amis max que l'on peut avoir
	 */
	public void setNbrMaxAmis(int Amis) {
		nbrMaxAmis = Amis;
	}
	
	/**
	 * @return int le nombre de jeux maximum
	 */
	public int getNbrMaxJeux() {
		return nbrMaxJeux;
	}
	
	/**
	 * @return int le nombre de parties multijoueurs maximum
	 */
	public int getNbrMaxPartiesMulti() {
		return nbrMaxPartiesMulti;
	}
	
	/**
	 * @return ReseauSocial le r�seau social sur lequel on est
	 */
	public ReseauSocial getReseauSocial() {
		return RS;
	}
	
	/**
	 * @param R : indique sur quel r�seau social on est
	 */
	public void setReseauSocial(ReseauSocial R) {
		RS = R;
	}
	
	/** 
	* Retourne le Joueur � partir de son pseudo avec une exception si le joueur n'existe pas (retourne null)
	* @param pseudo : pseudo dont on souhaite obtenir le Joueur
	* @param RS	: R�seau Social
	* @return Joueur : le Joueur � parti du pseudo
	*/
	Joueur getJoueur(String pseudo, ReseauSocial RS) {
		try {
			// Retourne une exception si le pseudo donc le Joueur n'existe pas
			if(existenceJoueur(pseudo, RS)== false) {
	            throw new ProblemePseudoException(this.affichagePseudoException() + "Joueur avec le pseudo " + pseudo + " n'existe pas.");
			}
			Joueur J = null; //mis � null comme initialisation mais va �tre obligatoirement modifi� car cette fonction est appel� si le joueur existe
			Map<String, Joueur> pseuJoueur       = RS.getListePseudoJoueur();
			Set<Map.Entry<String,Joueur>> joueur = pseuJoueur.entrySet();
			// Recherche du joueur
			for (Map.Entry<String,Joueur> ps : joueur) {
				if(ps.getKey().equals(pseudo)) {
					J = ps.getValue();
				}
			}
			return J;
		}
		// Retourne null si le joueur n'existe pas
		catch(ProblemePseudoException e){
			System.out.println(e);
			return null;
		}
	}

	/** 
	* Retourne le Bot � partir de son pseudo avec une exception si le joueur n'existe pas (retourne null)
	* @param pseudo : pseudo dont on souhaite obtenir le Bot 
	* @param RS	: R�seau Social
	* @return Bot : le Joueur � partir du pseudo
	*/
	Bot getBot(String pseudo, ReseauSocial RS) {
		// Retourne une exception si le pseudo donc le Joueur n'existe pas
		if(existenceBot(pseudo, RS)== false) {
			return null;
			}
			Bot J = null; //mis � null comme initialisation mais va �tre obligatoirement modifi� car cette fonction est appel� si le joueur existe
			Map<String, Bot> pseuJoueur       = RS.getListePseudoBot();
			Set<Map.Entry<String,Bot>> joueur = pseuJoueur.entrySet();
			// Recherche du joueur
			for (Map.Entry<String,Bot> ps : joueur) {
				if(ps.getKey().equals(pseudo)) {
					J = ps.getValue();
			}
		}
		return J;
	}
	
	
	
	
	

	/** 
	* V�rifie l'existence d'un joueur � partir de son pseudo
	* @param pseudo : pseudo dont on veut v�rifier existence 
	* @param RS	: R�seau Social
	* @return boolean : vrai si joueur avec ce pseudo existe
	*/
	boolean existenceJoueur(String pseudo, ReseauSocial RS) {
		// V�rifie si la map est vide -> si oui : le joueur n'existe pas
		if(RS.listePseudoJoueurMapVide()) {
			return false;
		}
		else {
			Map<String, Joueur> pseuJoueur = RS.getListePseudoJoueur(); 
			for (Map.Entry<String,Joueur> ps : pseuJoueur.entrySet()) { // Permet de regarder dans la map
				if(ps.getKey().equals(pseudo) == true) { // Si le pseudo est une cl� de la map alors un joueur avec ce pseudo existe
					return true;
				}
			}
			// Le pseudo n'est pas pr�sent dans la map
			return false;
		}
	}
	
	/** 
	* V�rifie l'existence d'un bot � partir de son pseudo
	* @param pseudo : pseudo dont on veut v�rifier existence bot
	* @param RS	: R�seau Social
	* @return boolean : vrai si joueur avec ce pseudo existe
	*/
	boolean existenceBot(String pseudo, ReseauSocial RS) {
		// V�rifie si la map est vide -> si oui : le joueur n'existe pas
		if(RS.listePseudoJoueurMapVide()) {
			return false;
		}
		else {
			Map<String, Bot> pseuJoueur = RS.getListePseudoBot(); 
			for (Map.Entry<String,Bot> ps : pseuJoueur.entrySet()) { // Permet de regarder dans la map
				if(ps.getKey().equals(pseudo) == true) { // Si le pseudo est une cl� de la map alors un joueur avec ce pseudo existe
					return true;
				}
			}
			// Le pseudo n'est pas pr�sent dans la map
			return false;
		}
	}
	
	
	
	/** 
	*   Retourne vrai si le joueur avec ce pseudo est d�j� son ami
	*   @param pathToFile : chemin vers le fichier
	*   @return boolean : vrai si d�j� ami
	*/
	boolean dejaAmis(String pseudo) {
		if(amis.contains(pseudo) == true){
			return true;
		}
		return false;
	}

		
}

