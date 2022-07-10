package com.appli;

// Import
import java.util.ArrayList;
import java.util.Map;
import java.time.LocalDate;
// Exception :
	// Exception lors d'ajout d'amis car un compte parent ne peut pas inviter un enfant
@SuppressWarnings("serial")
class ProblemeInvitationAmisException extends Exception{
	 public ProblemeInvitationAmisException(String message){
	     super(message);
	 }
}

@SuppressWarnings("serial")
class ProblemeSuppAmisException extends Exception{
	 public ProblemeSuppAmisException(String message){
	     super(message);
	 }
}

@SuppressWarnings("serial")
class ProblemeSuppAmisExitException extends Exception{
	 public ProblemeSuppAmisExitException(String message){
	     super(message);
	 }
}

@SuppressWarnings("serial")
class ProblemeParentEnfantException extends Exception{
	 public ProblemeParentEnfantException(String message){
	     super(message);
	 }
}




/**
 * @author gifhu
 *
 */
public class JHumainParent extends JHumain{
	// Variable de JHumain 
	private ArrayList<String> enfants = new ArrayList<String>();

	 	/** 
		*   Constructeur
		*   @param ps : pseudo que l'on souhaite
		*   @param em : email que l'on souhaite
		*   @param dN : date de naissance que l'on souhaite
		*   @param nbrJ : nombre Jeu max
		*   @param nbrA : nombre Ami max
		*   @param nbrP : nombre Parti Multijoueur max
		*   @param acqOffJeux : vrai si l'on peut acquérir et offrir des jeux
		*   @param invitA : vrai si l'on peut invité un enfant
		*   @param invitE : vrai si l'on peut invité un enfant
		*   @param R : le réseua social sur lequel on se trouve
		*   @param P : plateforme ajouté à notre compte
		*/ 
	public JHumainParent(String ps, String em, LocalDate dN, int nbrJ, int nbrA, int nbrP, boolean acqOffJeux, boolean invitA, boolean invitE, ReseauSocial R, String P) { //Caractéristiques d'un Enfant
		super(ps, em, dN, nbrJ, nbrA, nbrP, acqOffJeux, invitA, invitE, R, P);
	}
	
	
	
	
	
	// Get et Set :
	/**
	 * @param E : pseudo de l'enfant a ajouté
	 */
	public void setEnfants(String E) {
		enfants.add(E);
	}
	
	/**
	 * @return ArrayList liste des enfants
	 */
	public ArrayList<String> getEnfants() {
		return enfants;
	}
	
	/** 
	*   Retourne l'affichage d'un JHumainParent
	*   @return affichage : toString un JHumainParent
	*/
	@Override
	public String toString() {
		String affichage = "";
		if(this instanceof Gold) {
			affichage += "(Gold) pseudo : " + this.getPseudo() + " (" + this.getDateDeNaissance() + ") => " 
		                 + System.getProperty("line.separator") + "	Amis : " + this.getNbrAmis() 
			             + System.getProperty("line.separator") + "	Jeux : " + this.getNbrJeux() 
			             + System.getProperty("line.separator") + "	Plateforme : " + this.getPlateforme()
            			 + System.getProperty("line.separator") + "	Historiques Parties : " + this.getHistoriquesParties();

		}
		else if(this instanceof Standard) {
			affichage += "(Standard) pseudo : " + this.getPseudo() + " (" + this.getDateDeNaissance() + ") => " 
					    + System.getProperty("line.separator") + "	Amis : " + this.getNbrAmis() 
					    + System.getProperty("line.separator") + "	Jeux : " + this.getNbrJeux() 
					    + System.getProperty("line.separator") + "	Plateforme : " + this.getPlateforme()
			            + System.getProperty("line.separator") + "	Historiques Parties : " + this.getHistoriquesParties();
		} 
		return affichage;
	}
	
	
	
	
	/** 
	*   Constructeur de l'enfant créé avec le profil d'un joueur Gold ou Standard
	*   @param pseu : pseudo de l'Enfant
	*   @param em : jeu de l'Enfant
	*   @param dN : date de naissance de l'Enfant
	*   @param R : réseau social sur lequel est l'Enfant
	*   @param J : Joueur qui créé joueur Enfant
	*   @param P : plateforme de l'Enfant
	*/
	Enfant creerProfilEnfant(String pseu, String em, LocalDate dN, ReseauSocial R, String P) { // Exception si la date de naissance est supérieur à 18 ans + si pseudo existe pas
		Enfant e = new Enfant(pseu, em, dN, R, this, P);
		return e;
	}
		
	
	/** 
	*   Invite un ami à partir de son pseudo
	*   @param pseudo : pseudo que l'on souhaite ajouter en ami
	*/
	// Invite un ami
	void invitAmis(String pseudo) { //verif si ils sont pas deja amis
		// Vérifie si le joueur existe ou pas
		Joueur J = getJoueur(pseudo, this.getReseauSocial());
		// On cherche à savoir quel est le type de joueur : Bot, Gold, Standard, Enfant
		// Pour cela on regarde la caractéristique nombre max partie multijoueur qui dépend pour chaque profil
		try {
			if(J == null) { // Problème car aucun profil a ce pseudo donc exception de get Joueur
		    } 
			// Si le joueur est déjà dans sa liste d'ami
			else if(this.dejaAmis(pseudo) == true) {
				throw new ProblemeInvitationAmisException(this.affichagePseudoException() + "Vous êtes déjà amis avec le joueur " + pseudo + ".");
			}
			// Si le joueur est un enfant, un adulte ne peut pas être son ami
			else if(J instanceof Enfant) {
				throw new ProblemeInvitationAmisException(this.affichagePseudoException() + "Ce joueur est un enfant. Impossible d'être ami avec ce joueur.");
			}
			// Problème du nombre d'ami max si un joueur est Standard
			else if(this instanceof Standard) {
				// Si nombre max amis => exception
				if(this.getNbrMaxAmis() <= this.getNbrAmis()) {
					throw new ProblemeInvitationAmisException(this.affichagePseudoException() + "Vous avez déjà " + this.getNbrMaxAmis() + " amis. Veuillez supprimer un ami.");
				}
				// Sinon invitation valide
				else {
					this.setAmis(pseudo);
					J.setAmis(this.getPseudo());
					System.out.println(this.getPseudo() + " et " + J.getPseudo() + " sont maintenant amis.");
				}
			}
			else if(J instanceof Standard) {		
			    Standard JH = (Standard)J;  // Transtypage car le joueur est un Standard car seul JHumain avec restriction nombre d'amis
				// Si nombre max amis => exception
			    if(JH.getNbrMaxAmis() <= JH.getNbrAmis()) {
					throw new ProblemeInvitationAmisException(this.affichagePseudoException() + "Le joueur " + JH.getPseudo() + " a atteint sa limite du nombre d'amis");
				}
				// Sinon invitation valide
			    else {
					this.setAmis(pseudo);
					J.setAmis(this.getPseudo());
					System.out.println(this.getPseudo() + " et " + J.getPseudo() + " sont maintenant amis.");
				}
			}
			// Invitation joueur Bot ou Gold 
			else {
				this.setAmis(pseudo);
				J.setAmis(this.getPseudo());
				System.out.println(this.getPseudo() + " et " + J.getPseudo() + " sont maintenant amis.");
			}
		}
		catch(ProblemeInvitationAmisException e){
			System.err.println(e);
		}
		
	}
	
	/** 
	*   Supprime ami en affichant les amis puis demande pseudo de l'ami à supprimer
	*/
	void supprAmis() { 
		// Affiche la liste des amis
		System.out.println("Amis : " + this.getAmis());
		// Demande et récupère pseudo de l'ami à supprimer
		//String amiSuppr;
		//Scanner scan1 = new Scanner(System.in);
		System.out.println("Pseudo de l'ami à supprimer (q => quitter) : "); // Quitter le mode supp avec "q"
		String amiSuppr = scan.nextLine();
		try {
			// Indique que si le joueur veut quitter le mode suppression ami : appuyer sur q (donc pas d'ami avec comme pseudo "q")
			if(amiSuppr.equals("q")) {
			}
			// Regarde si le joueur est dans les amis ou pas
			else if(this.dejaAmis(amiSuppr) == true) {
				Joueur J = getJoueur(amiSuppr, this.getReseauSocial());
				// Si le joueur est un enfant alors c'est obligatoirement son enfant donc impossible de le supprimer des amis (hypothèse)
				if(J instanceof Enfant) {
					throw new ProblemeSuppAmisExitException(this.affichagePseudoException() + "Vous ne pouvez pas supprimé votre enfant de vos amis.");
				}
				// Sinon tout va bien on supprime l'ami
				else {
					J.supAmis(this.getPseudo());
					this.supAmis(amiSuppr);
				}
			}
			// Sinon retourne exception car pseudo n'est pas dans les amis
			else {
				throw new ProblemeSuppAmisException(this.affichagePseudoException() + "Aucun ami trouvé sous le pseudo : ");
			}
		}
		catch(ProblemeSuppAmisException e){
			try {
				do { 
					// On transmet l'exception et demande à l'utilisateur d'écrire un nouveau pseudo
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
					// Pas possible de supprimer son enfant
					if(J instanceof Enfant) {
						throw new ProblemeSuppAmisExitException(this.affichagePseudoException() + "Vous ne pouvez pas supprimé votre enfant de vos amis.");
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
			//scan1.close();
		}
	}
	
	/** 
	*   Offrir jeu à des autres joueurs Gold ou Standard et à ses enfants à partir JHumain
	*   @param joueur : joueur à qui l'on veut offrir jeu
	*/
	// Offrir jeu à des autres joueurs Gold ou Standarad et à ses enfants
	void offrirJeux(JHumain joueur) throws ProblemeAffichageException { //Pas en compte Bot
		try{
			// Vérification que le compte Enfant fait partie de ses enfants
			if(joueur instanceof Enfant) {
			    Enfant enfant = (Enfant)joueur;  // Transtypage explicite
				if(enfant.getParents().contains(this.getPseudo()) == false) {
					throw new ProblemeParentEnfantException(this.affichagePseudoException() + "Vous ne pouvez pas offrir de jeu à un compte Enfant sauf si c'est votre enfant.");
				}
			}
			// Vérification que le compte à qui on offre n'a pas atteint son nombre max de jeu
			else if(joueur instanceof Standard  || joueur instanceof Enfant) {
				if(this.getNbrJeux() >= this.getNbrMaxJeux()) {
		            throw new ProblemeLimiteException(this.affichagePseudoException() + joueur.getPseudo() +" a atteint votre nombre maximal de jeu.");
				}
			}
			// Demande la plateforme du jeu
			String plateforme;
			System.out.println(joueur.getPlateforme());
			System.out.println("Sur quelle plateforme voulez-vous offrir le jeu ?");
			plateforme = scan.next();
			Map<String, ArrayList<String>> PlateformeAvecJeux = joueur.getPlateformeAvecJeux();
			// Vérifie que le compte qui reçoit le jeu possède cette plateforme
			if(PlateformeAvecJeux.containsKey(plateforme) == false){
				do {
					System.err.println(plateforme + " ne fait pas partie des plateformes de " + joueur.getPseudo() + ".");
					System.out.println(joueur.getPlateforme());
					System.out.println("Sur quelle plateforme voulez-vous ajouter le jeu ?");
					plateforme = scan.next();
				}while(PlateformeAvecJeux.containsKey(plateforme) == false);
			}
			// Demande le jeu à offrir
			String jeu;
			joueur.getReseauSocial().getJeux().affichageJeuxParPlateforme(plateforme);
			System.out.println("Quel jeu voulez-vous ajouter sur " + plateforme + " ?");
			scan.nextLine();
			jeu = scan.nextLine();
		
			if(joueur.getReseauSocial().getJeux().existenceJeuxSurPlateforme(jeu, plateforme) == false){
				do {
					System.err.println(jeu + " n'est pas un jeu de " + plateforme + ".");
					joueur.getReseauSocial().getJeux().affichageJeuxParPlateforme(plateforme);
					System.out.println("Quel jeu voulez-vous ajouter sur " + plateforme + " ?");
					jeu = scan.nextLine();
				}while(joueur.getReseauSocial().getJeux().existenceJeuxSurPlateforme(jeu, plateforme) == false);
			}
			ArrayList<String> jeuxSurCettePlateforme = new ArrayList<String>();
			String nomPlateforme;
			// Ajoute le jeu dans la collection de l'ami en récupérant la liste de jeu de la plateforme 
			// puis ajoutant le jeu à cette liste et enfin remplace la liste du jeu de la plateforme par la nouvelle liste
			for(Map.Entry<String, ArrayList<String>> name : PlateformeAvecJeux.entrySet()) {
				nomPlateforme = name.getKey();
				if(nomPlateforme.equals(plateforme)) {
					jeuxSurCettePlateforme = name.getValue();
					if(jeuxSurCettePlateforme == null) {
						jeuxSurCettePlateforme = new ArrayList<String>();
						jeuxSurCettePlateforme.add(jeu);
					}
					else {
						jeuxSurCettePlateforme.add(jeu);
					}
				}
			}
			PlateformeAvecJeux.replace(plateforme, jeuxSurCettePlateforme);
			joueur.setPlateformeAvecJeux(PlateformeAvecJeux);
			joueur.setNbrJeux(1);
			System.out.println(jeu + " a été ajouté aux jeux de " + joueur.getPseudo() + " sur " + plateforme + ".");
		}	
		// Catch les problèmes
		catch(ProblemeParentEnfantException e) {
			System.err.println(e);
		}
		catch(ProblemeLimiteException f) {
			System.err.println(f);
		}
	}
	
	/** 
	*   Offrir jeu à des autres joueurs Gold ou Standard et à ses enfants
	*/
		void offrirJeux() throws ProblemeAffichageException { //Pas en compte Bot
			JHumain joueur;
			String choixString;
			System.out.println("Voici vos amis : ");
			System.out.println(this.getAmis());
			System.out.println("Choisissez un ami pour réaliser la fonctionnalité : ");
			choixString = scan.next();
			while(this.getAmis().contains(choixString) == false) {
				System.err.println(choixString + " ne fait pas parti de vos amis.");
				System.out.println("Voici vos amis : ");
				System.out.println(this.getAmis());			
				System.out.println("Choisissez un ami pour réaliser la fonctionnalité : ");
				choixString = scan.next();
			}
			joueur = (JHumain)this.getJoueur(choixString, this.getReseauSocial());

			try{
				// Vérification que le compte Enfant fait partie de ses enfants
				if(joueur instanceof Enfant) {
				    Enfant enfant = (Enfant)joueur;  // Transtypage explicite
					if(enfant.getParents().contains(this.getPseudo()) == false) {
						throw new ProblemeParentEnfantException(this.affichagePseudoException() + "Vous ne pouvez pas offrir de jeu à un compte Enfant sauf si c'est votre enfant.");
					}
				}
				// Vérification que le compte à qui on offre n'a pas atteint son nombre max de jeu
				else if(joueur instanceof Standard  || joueur instanceof Enfant) {
					if(this.getNbrJeux() >= this.getNbrMaxJeux()) {
			            throw new ProblemeLimiteException(this.affichagePseudoException() + joueur.getPseudo() +" a atteint votre nombre maximal de jeu.");
					}
				}
				// Demande la plateforme du jeu
				String plateforme;
				System.out.println("Voici les plateformes de votre ami " + joueur.getPseudo() + " : ");
				System.out.println(joueur.getPlateforme());
				System.out.println("Sur quelle plateforme voulez-vous offrir le jeu ?");
				plateforme = scan.next();
				Map<String, ArrayList<String>> PlateformeAvecJeux = joueur.getPlateformeAvecJeux();
				// Vérifie que le compte qui reçoit le jeu possède cette plateforme
				if(PlateformeAvecJeux.containsKey(plateforme) == false){
					do {
						System.out.println("Voici les plateformes de votre ami " + joueur.getPseudo() + " : ");
						System.err.println(plateforme + " ne fait pas partie des plateformes de " + joueur.getPseudo() + ".");
						System.out.println(joueur.getPlateforme());
						System.out.println("Sur quelle plateforme voulez-vous ajouter le jeu ?");
						plateforme = scan.next();
					}while(PlateformeAvecJeux.containsKey(plateforme) == false);
				}
				// Demande le jeu à offrir
				String jeu;
				joueur.getReseauSocial().getJeux().affichageJeuxParPlateforme(plateforme);
				System.out.println("Quel jeu voulez-vous ajouter sur " + plateforme + " ?");
				jeu = scan.nextLine();
			
				if(joueur.getReseauSocial().getJeux().existenceJeuxSurPlateforme(jeu, plateforme) == false){
					do {
						System.err.println(jeu + " n'est pas un jeu de " + plateforme + ".");
						joueur.getReseauSocial().getJeux().affichageJeuxParPlateforme(plateforme);
						System.out.println("Quel jeu voulez-vous ajouter sur " + plateforme + " ?");
						jeu = scan.nextLine();
					}while(joueur.getReseauSocial().getJeux().existenceJeuxSurPlateforme(jeu, plateforme) == false);
				}
				ArrayList<String> jeuxSurCettePlateforme = new ArrayList<String>();
				String nomPlateforme;
				// Ajoute le jeu dans la collection de l'ami en récupérant la liste de jeu de la plateforme 
				// puis ajoutant le jeu à cette liste et enfin remplace la liste du jeu de la plateforme par la nouvelle liste
				for(Map.Entry<String, ArrayList<String>> name : PlateformeAvecJeux.entrySet()) {
					nomPlateforme = name.getKey();
					if(nomPlateforme.equals(plateforme)) {
						jeuxSurCettePlateforme = name.getValue();
						if(jeuxSurCettePlateforme == null) {
							jeuxSurCettePlateforme = new ArrayList<String>();
							jeuxSurCettePlateforme.add(jeu);
						}
						else {
							System.out.println(jeuxSurCettePlateforme);
							jeuxSurCettePlateforme.add(jeu);
						}
					}
				}
				PlateformeAvecJeux.replace(plateforme, jeuxSurCettePlateforme);
				joueur.setPlateformeAvecJeux(PlateformeAvecJeux);
				joueur.setNbrJeux(1);
				System.out.println(jeu + " a été ajouté aux jeux de " + joueur.getPseudo() + " sur " + plateforme + ".");
			}	
			// Catch les problèmes
			catch(ProblemeParentEnfantException e) {
				System.err.println(e);
			}
			catch(ProblemeLimiteException f) {
				System.err.println(f);
			}
		}
}
