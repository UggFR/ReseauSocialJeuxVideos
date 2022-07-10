package com.appli;

// Import
import java.time.LocalDate;
// Exception :
	// Exception pour le constructeur enfant car profil utilis� non autoris� � cr�er compte enfant
@SuppressWarnings("serial")
class ProfilJoueurIncorrectPourCreerCompteEnfantException extends Exception {
    public ProfilJoueurIncorrectPourCreerCompteEnfantException(String message) {
        super(message);
    }
}

	//Exception pour le constructeur enfant car profil utilis� non autoris� � cr�er compte enfant
@SuppressWarnings("serial")
class ProblemeParentException extends Exception{
	 public ProblemeParentException(String message){
	     super(message);
	 }
}


/**
 * @author gifhu
 *
 */
public class Enfant extends JHumain{
	private String[] parents = new String[2];
	
	
	/** 
	*   Constructeur de l'enfant cr�� avec le profil d'un joueur Gold ou Standard
	*   @param pseu : pseudo de l'Enfant
	*   @param em : jeu de l'Enfant
	*   @param dN : date de naissance de l'Enfant
	*   @param R : r�seau social sur lequel est l'Enfant
	*   @param J : Joueur qui cr�� joueur Enfant
	*   @param P : plateforme de l'Enfant
	*/
	public Enfant(String pseu, String em, LocalDate dN, ReseauSocial R, Joueur J, String P) { 
		super(pseu, em, dN, 30, 10, 3, false, false, true, R, P); // Cr�ation du compte enfant
		
		try{
			// Retourner une exception si le profil Joueur utilis� pour cr�er le compte est un bot ou Enfant
			if(J instanceof Bot) {
	            throw new ProfilJoueurIncorrectPourCreerCompteEnfantException(this.affichagePseudoException() + "Profil Bot ne peut pas cr�er compte Enfant.");
			}
			if (J instanceof Enfant) {
	            throw new ProfilJoueurIncorrectPourCreerCompteEnfantException(this.affichagePseudoException() + "Profil Enfant ne peut pas cr�er compte Enfant.");
			}
			JHumainParent JHP = (JHumainParent)J; // Transtypage pour utiliser m�thode de JHumainParent (setEnfants)
			// Mise � jour Parents/Enfants et Amis pour chaque profil
			this.setParents(JHP.getPseudo(), null);
			JHP.setEnfants(this.getPseudo());
			JHP.setAmis(this.getPseudo());
			this.setAmis(JHP.getPseudo());
		}
		
		catch(ProfilJoueurIncorrectPourCreerCompteEnfantException e) {
			Joueur E   = getJoueur(pseu, R);
			JHumain JH = (JHumain)E;
			// V�rification d'une exception : 
			// Si l'on cr�� un enfant avec un pseudo d�j� existant -> le Joueur Enfant ne va pas �tre cr�� car exception du pseudo 
			// Cependant, si ce compte enfant est cr�� � partir d'un compte bot ou enfant, on ne doit pas supprimer le compte avec le pseudo car ce serait le mauvais Joueur
			if(JH.getDateDeNaissance().equals(dN) == true && JH.getEmail().equals(em) == true) { // On v�rifie donc si la date de naissance et l'email sont les m�mes pour supp le Joueur
				R.supprPseudoJoueur(pseu); // Compte enfant supprim� si le profil joueur utilis� pour cr�er est un compte enfant ou bot avec conditions
			}
			// Mise de toutes les variables � null car le joueur n'existe pas
			this.setPseudo(null);
			this.setAmisNull();
			this.setReseauSocial(null);
			this.setEmail(null);
			this.setDateDeNaissance(null);
			this.setPlateformeAvecJeuxNull();
			this.setHistoriquesPartiesNull();
			this.setParents(null, null);
		}
	}
	
	
	
	
	
	// Get et Set :
	
		// Set les deux parents d'un enfant pour le constructeur Enfant (private protected) : 
		// 1 -> le parent qui cr�� ; 2 -> un parent mis � null 
	/** 
	*   Set les deux parents d'un enfant pour le constructeur Enfant (private protected) : 
	*   1 -> le parent qui cr�� ; 2 -> un parent mis � null 
	*   @param P1 : pseudo du premier parent
	*   @param P2 : pseudo du dexui�me parent
	*/
	void setParents(String P1, String P2) {
		parents[0] = P1;
		parents[1] = P2;
	}
	
		// Set le deuxi�me parent qui est normalement � null
	/** 
	*   Set le deuxi�me parent qui est normalement � null
	*   @param P2 : le pseudo du deuxi�me parent
	*/
	public void setDeuxi�meParent(String P2) {
		try {
			Joueur J = getJoueur(P2, this.getReseauSocial()); 
			if(parents[0].equals(P2)) {
	            throw new ProblemeParentException(this.affichagePseudoException() + P2 + " fait d�j� parti de vos parents.");
			}
			else if(J instanceof Bot || J instanceof Enfant) {
	            throw new ProblemeParentException(this.affichagePseudoException() + P2 + " ne peut pas �tre l'un de vos parents. (Joueur Bot ou Enfant)");
			}
			JHumainParent JHP = (JHumainParent)J; // Transtypage pour utiliser m�thode de JHumainParent (setEnfants)
			parents[1] = P2; //Regarder si c un JHumainParent
			JHP.setEnfants(this.getPseudo());
			JHP.setAmis(this.getPseudo());
			this.setAmis(P2);
		}
		catch(ProblemeParentException e){
			System.err.println(e);
		}
	}
	
		// Retourne la liste des parents
	/** 
	*   V�rifie si la liste des pseudos/joueurs est vide ou pas
	*   @return boolean si la map est vide
	*/
	public String getParents() {
		String p;
		if(parents[1] == null) {
			p = "[" + parents[0] + "]"; 
		}
		else {
			p = "[" + parents[0]+ ", " + parents[1] + "]"; 
		}
		return p;
	}
	
	/** 
	*   Retourne l'affichage d'un enfant
	*   @return affichage : toString un enfant
	*/
	@Override
	public String toString() {
		String affichage;
		// Probl�me de cr�ation de compte (cr�� par Enfant ou Bot) : constructeur a donc mis ses attributs � null
		if(this.getPseudo() == null) {
			affichage = "Cet enfant n'existe pas. Son inscription ne respectait pas nos r�gles : ce compte doit �tre cr�� � l'aide d'un compte d'une personne majeure.";
		}
		else {
			affichage = "(Enfant) pseudo : " + this.getPseudo() + " => " + this.getNbrPartiesMulti() + " parties multijoueurs.";
		}
		return affichage;
	}
	
	
	/** 
	*   Ajoute ami en faisant les v�rifications n�cessaires du compte enfant � partir d'un pseudo
	*   @param pseudo : pseudo du Joueur que l'on veut inviter en ami
	*/
	// Ajoute ami en faisant les v�rifications n�cessaires du compte enfant
	void invitAmis(String pseudo) { //verif si ils sont pas deja amis
		// V�rifie si le joueur existe ou pas
		Joueur J = getJoueur(pseudo, this.getReseauSocial());
		// On cherche � savoir quel est le type de joueur : Bot, Gold, Standard, Enfant
		// Pour cela on regarde la caract�ristique nombre max partie multijoueur qui d�pend pour chaque profil
		try {
			if(J == null) { // Probl�me car aucun profil a ce pseudo donc exception de get Joueur
		    } 
			// Si le joueur est un enfant, un adulte ne peut pas �tre son ami
			else if(this.dejaAmis(pseudo) == true) {
				throw new ProblemeInvitationAmisException(this.affichagePseudoException() + "Vous �tes d�j� amis avec le joueur " + pseudo + ".");
			}
			else if(J instanceof Enfant) {
				//throw new ProblemeInvitationAmisException("Ce joueur est un enfant. Impossible d'�tre ami avec ce joueur.");
				Enfant E = (Enfant)J;  // Transtypage car le joueur est un Standard car seul JHumain avec restriction nombre d'amis

				if(this.getNbrMaxAmis() <= this.getNbrAmis()) {
					throw new ProblemeInvitationAmisException(this.affichagePseudoException() + "Vous avez d�j� " + this.getNbrMaxAmis() + " amis. Veuillez supprimer un ami.");
				}
				// Sinon invitation valide
				// Si nombre max amis => exception
				else if(E.getNbrMaxAmis() <= E.getNbrAmis()) {
					throw new ProblemeInvitationAmisException(this.affichagePseudoException() + "Le joueur " + E.getPseudo() + " a atteint sa limite du nombre d'amis");
				}
				else {
					this.setAmis(pseudo);
					J.setAmis(this.getPseudo());
					System.out.println(this.getPseudo() + " et " + E.getPseudo() + " sont maintenant amis.");
				}
			}
			// Probl�me d'invitation si joueur invit� est un Bot, Gold ou Standard
			else{
				throw new ProblemeInvitationAmisException(this.affichagePseudoException() + "Vous ne pouvez pas ajouter ce type de joueur. Invitez des joueurs Enfants.");
			}
		}
		catch(ProblemeInvitationAmisException e){
			System.err.println(e);
		}
	}
	

	/** 
	* Supprime ami en affichant les amis puis demande pseudo de l'ami � supprimer
	*/
	void supprAmis() { 
		// Affiche la liste des amis
		System.out.println("Amis : " + this.getAmis());
		// Demande et r�cup�re pseudo de l'ami � supprimer
		String amiSuppr;
		System.out.println("Pseudo de l'ami � supprimer (q => quitter) : "); // Quitter le mode supp avec "q"
		scan.nextLine();
		amiSuppr = scan.nextLine();
		try {
			// Indique que si le joueur veut quitter le mode suppression ami : appuyer sur q (donc pas d'ami avec comme pseudo "q")
			if(amiSuppr.equals("q")) {
			}
			// Regarde si le joueur est dans les amis ou pas
			else if(this.dejaAmis(amiSuppr) == true) {
				Joueur J = getJoueur(amiSuppr, this.getReseauSocial());
				// Si le joueur est un JHumainParent alors c'est obligatoirement son parent donc impossible de le supprimer des amis (hypoth�se)
				if(J instanceof JHumainParent) {
					throw new ProblemeSuppAmisExitException(this.affichagePseudoException() + "Vous ne pouvez pas supprim� votre parent de vos amis.");
				}
				// Sinon tout va bien on supprime l'ami
				else {
					J.supAmis(this.getPseudo());
					this.supAmis(amiSuppr);
				}
			}
			// Sinon retourne exception car pseudo n'est pas dans les amis
			else {
				throw new ProblemeSuppAmisException(this.affichagePseudoException() + "Aucun ami trouv� sous le pseudo : ");
			}
		}
		catch(ProblemeSuppAmisException e){
			try {
				do { 
					// On transmet l'exception et demande � l'utilisateur d'�crire un nouveau pseudo
					System.out.println(e + amiSuppr + ".");
					System.out.println("Amis : " + this.getAmis());
					System.out.println("Choisissez un autre pseudo (q => quitter) :");
					amiSuppr = scan.nextLine();
				// Continue la boucle si le pseudo n'appartient pas aux amis et n'est pas "q"
				}while(this.dejaAmis(amiSuppr) == false && amiSuppr.equals("q") == false);
				// Quitte le mode
				if(amiSuppr.equals("q")) {
				}
				else {
					Joueur J = getJoueur(amiSuppr, this.getReseauSocial());
					// Pas possible de supprimer son parent
					if(J instanceof JHumainParent) {
						throw new ProblemeSuppAmisExitException(this.affichagePseudoException() + "Vous ne pouvez pas supprim� votre parent de vos amis.");
					}		
					// Sinon tout va bien on supprime l'ami
					else {
						J.supAmis(this.getPseudo());
						this.supAmis(amiSuppr);
					}
				}
			}
			catch(ProblemeSuppAmisExitException l){
				System.err.println(l);
			}
		}
		catch(ProblemeSuppAmisExitException e){
			System.err.println(e);
		}
		finally {
			// Fermer le Writer
			//scan.close();
		}
	}
}
