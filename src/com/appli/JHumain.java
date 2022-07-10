package com.appli;

//import java.text.ParseException;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
//import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;



@SuppressWarnings("serial")
class ProblemePlateformeException extends Exception {
	 public ProblemePlateformeException(String message) {
	     super(message);
 }
}

@SuppressWarnings("serial")
class ProblemeAccesException extends Exception {
	 public ProblemeAccesException(String message) {
	     super(message);
 }
}

@SuppressWarnings("serial")
class ProblemeLimiteException extends Exception {
	 public ProblemeLimiteException(String message) {
	     super(message);
}
}

@SuppressWarnings("serial")
class ProblemeMisEnCommunException extends Exception {
	 public ProblemeMisEnCommunException(String message) {
	     super(message);
}
}



/**
 * @author gifhu
 *
 */
public class JHumain extends Joueur{ 
	private String email;
	private LocalDate dateDeNaissance;
	private int nbrAmis;
	private int nbrJeux;
	private int nbrPartiesMulti;
	private Map<String, ArrayList<String>> PlateformeAvecJeux = new HashMap<String, ArrayList<String>>();
	private ArrayList<String>   historiquesParties = new ArrayList<String>();

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
	public JHumain(String ps, String em, LocalDate dN, int nbrJ, int nbrA, int nbrP, boolean acqOffJeux, boolean invitA, boolean invitE, ReseauSocial R, String P){
		super(ps, nbrJ, nbrA, nbrP, acqOffJeux, invitA, invitE, R);
		
		try {
			if(R.getJeux().existencePlateforme(P) == false) {
	            throw new ProblemePlateformeException("");
			}
			PlateformeAvecJeux.put(P, new ArrayList<String>());
		}
		catch(ProblemePlateformeException e){
			String PlateformeDemande = P;
			// Boucle do while pour valider un pseudo unique pour ce profil
			do { 
				//scan.next();
				// On transmet l'exception selon le cas et demande à l'utilisateur d'écrire un nouveau pseudo				
				System.err.println(this.getPseudo() + " : " + PlateformeDemande + " n'est pas une plateforme de ce réseau social.");
				String plateforme = R.getJeux().affichagePlateforme();
				String phrasePlateforme = "Veuillez choisir une plateforme parmi cette liste :" + plateforme.substring(51);
				System.out.println(phrasePlateforme);
				PlateformeDemande = scan.next();
			}while(R.getJeux().existencePlateforme(PlateformeDemande) == false);
			PlateformeAvecJeux.put(PlateformeDemande, new ArrayList<String>());
		}
		finally {
			email           = em;
			dateDeNaissance = dN;
			// Initialisation à 0 de toutes les caractéristiques à la création du compte
			nbrJeux         = 0;
			nbrAmis         = 0;
			nbrPartiesMulti = 0;
		}
	}
	
	// Get et Set
	/**
	 * @return String l'email du joueur
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param em : l'email du joueur
	 */
	public void setEmail(String em) {
		email = em;
	}
	
	/**
	 * @return String la date de Naissance du joueur
	 */
	public String getDateDeNaissance() {
		String dN = dateDeNaissance.toString();
		return dN;
	}
	
	/**
	 * @param dN : la date de naissance du joueur
	 */
	public void setDateDeNaissance(LocalDate dN) {
		dateDeNaissance = dN;
	}
	
	/**
	 * @return int le nombre d'amis du joueur
	 */
	public int getNbrAmis() {
		return nbrAmis;
	}
	
	@Override 
	public void setAmis(String A) {
		nbrAmis += 1; // Dès qu'on ajoute un ami, on augmente de 1 son nombre d'ami 
		super.setAmis(A);	
	}
	
	@Override 
	public void supAmis(String A) {
		nbrAmis -= 1; // Dès qu'on ajoute un ami, on augmente de 1 son nombre d'ami 
		super.supAmis(A);	
	}
	
	/**
	 * @return int le nombre de jeux du joueur
	 */
	public int getNbrJeux() {
		return nbrJeux;
	}
	
	/**
	 * @param nombre : ajouter un certains nombre de jeux au compteur de jeu du joueur
	 */
	public void setNbrJeux(int nombre) {
		nbrJeux += nombre;
	}
	
	/**
	 * @return int le nombre de parties multijoueurs du joueur
	 */
	public int getNbrPartiesMulti() {
		return nbrPartiesMulti;
	}
	
	/**
	 * @return Map liste avec les plateformes et ses jeux d'un joueur
	 */
	public Map<String, ArrayList<String>> getPlateformeAvecJeux(){
		return PlateformeAvecJeux;
	}
	
	/**
	 * @param P : liste des plateformes et jeux d'un joueur
	 */
	public void setPlateformeAvecJeux(Map<String, ArrayList<String>> P){
		PlateformeAvecJeux = P;
	}
	
	/**
	 * @return ArrayList la liste des parties multijoueurs d'un joueur
	 */
	public ArrayList<String>   getHistoriquesParties(){
		return historiquesParties;
	}
	 
	/**
	 * Mis à null des plateformes avec jeux
	 */
	public void setPlateformeAvecJeuxNull() {
		PlateformeAvecJeux = null;
	}

	
	/**
	 * Mis à null des historiques de parties
	 */
	public void setHistoriquesPartiesNull() {
		historiquesParties = null;
	}
	
	/** 
	*   Retourne les plateformes du joueur
	*   @return plateforme : une liste de plateforme possédé
	*/
	public String getPlateforme() {
		String plateforme = "[";
		Collection<String> namePlat = PlateformeAvecJeux.keySet();
        for (String name : namePlat) {
        	plateforme += name + ", ";
        }
		plateforme = plateforme.substring(0, plateforme.length()-2) + "]"; // On retire la virgule et l'espace en trop de fin
		return plateforme;
	}
	
	/** 
	*   Ajoute une plateforme à un joueur
	*   @param P : plateforme à ajouter
	*/
	public void ajoutPlateforme(String P) {
		//Un enfant peut ajouter une plateforme
		try{
			if(this.getReseauSocial().getJeux().existencePlateforme(P) == false) {
	            throw new ProblemePlateformeException(this.affichagePseudoException() + P + " n'est pas une plateforme de ce réseau social.");
			}
			else if(this.PlateformeAvecJeux.containsKey(P)) {
	            throw new ProblemePlateformeException(this.affichagePseudoException() + "Vous possédez déjà cette plateforme.");
			}
			PlateformeAvecJeux.put(P, new ArrayList<String>());
		}
		catch(ProblemePlateformeException e) {
			System.err.println(e);
		}
	}
	
	/** 
	*   Affiche les jeux du joueur
	*   @return jeux : liste des jeux du joueur
	*/
	public String affichageJeux() {
		String jeux = this.affichagePseudoException() + "Affichage de vos jeux selon la plateforme => \n";
		ArrayList<String> listeJeux = new ArrayList<String>();
		for(Map.Entry<String, ArrayList<String>> name : PlateformeAvecJeux.entrySet()) {
			jeux += "\t";
			jeux += name.getKey() + " : [";
			listeJeux = name.getValue();
			if(listeJeux.isEmpty()) {
				jeux += "] \n";
			}
			else {
				for(String j : listeJeux) {
					jeux += j + "/ ";
				}
				jeux = jeux.substring(0, jeux.length()-2);
				jeux += "] \n";
			}
		}
		return jeux;
	}
	
	/** 
	*   Affiche les plateformes en commun avec un joueur
	*   @param joueur : joueur dont on veut connaître les plateformes en commun
	*   @return plateformeAmis ou null si aucune plateforme en commun
	*/
	public String affichagePlateformeEnCommunAvecAmis(JHumain joueur) {
		String plateformeAmis = "[";
		Map<String, String> plateformeRS = this.getReseauSocial().getJeux().getListePlateformePublisher();
		for(Map.Entry<String, String> name : plateformeRS.entrySet()) {
			if((this.getPlateforme().contains(name.getKey()+",") || this.getPlateforme().contains(name.getKey()+"]")) && (joueur.getPlateforme().contains(name.getKey()+",") || joueur.getPlateforme().contains(name.getKey()+"]"))) {
				plateformeAmis += name.getKey() + ", ";
			}	
		}
		if(plateformeAmis.length() > 1) {
			plateformeAmis = plateformeAmis.substring(0, plateformeAmis.length()-2); 
		}
		plateformeAmis += "]"; // On retire la virgule et l'espace en trop de fin
		try {
			if(plateformeAmis.equals("[]")){
				throw new ProblemeMisEnCommunException(" Vous n'avez aucune plateforme en commun avec votre ami " + joueur.getPseudo() + ".");
			}
			return plateformeAmis;
		}
		catch(ProblemeMisEnCommunException e) {
			System.err.println(e);
			return null;
		}
	}
	
	/** 
	*   Affiche les jeux en commun avec un joueu
	*   @param joueur : joueur dont on veut connaître les jeux en commun
	*   @param plateforme : plateforme dont on veut connaître les plateformes en commun
	*   @return jeuxAmis ou null si aucun jeu en commun
	*/
	public String affichageJeuxEnCommunAvecAmisSelonPlateforme(JHumain joueur, String plateforme) {
		String jeuxAmis = "[";
		Map<String,ArrayList<String>> JeuxSelonPlateforme = this.getPlateformeAvecJeux();
		for(Map.Entry<String, ArrayList<String>> name : JeuxSelonPlateforme.entrySet()) {
			if(name.getKey().equals(plateforme)) {
				if(name.getValue() == null) {
					// On fait rien si la liste de jeux est vide
				}
				else {
					ArrayList<String> jeuxDeLaPlateforme = name.getValue();
					String plateformeJeuxAmi = joueur.affichageJeux();
					String[] jeuxPlateformeAmi = plateformeJeuxAmi.split("\n");
					String jeuxDeLaBonnePlateforme = "";
					for(int i = 0; i<jeuxPlateformeAmi.length ;i++) {
						if(jeuxPlateformeAmi[i].contains(plateforme)) {
							jeuxDeLaBonnePlateforme = jeuxPlateformeAmi[i];
						}
					}
					for(String nomJeu : jeuxDeLaPlateforme) {
						if(jeuxDeLaBonnePlateforme.contains(nomJeu)) {
							jeuxAmis += nomJeu + ", ";
						}	
					}
				}
			}
		}
		if(jeuxAmis.length() > 1) {
			jeuxAmis = jeuxAmis.substring(0, jeuxAmis.length()-2); 
		}
		jeuxAmis += "]"; // On retire la virgule et l'espace en trop de fin
		try {
			if(jeuxAmis.equals("[]")){
				throw new ProblemeMisEnCommunException(" Vous n'avez aucun jeu en commun avec votre ami " + joueur.getPseudo() + " sur la plateforme " + plateforme + ".");
			}
			return jeuxAmis;
		}
		catch(ProblemeMisEnCommunException e) {
			System.err.println(e);
			return null;
		}
	}
	
	/** 
	*   Ajoute un jeu à une plateforme possédé 
	*   @param Jeu : jeu à ajouter
	*   @param Plateforme : plateforme dont on veut ajouter le jeu
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	*/
	public void ajoutJeux(String Jeu, String Plateforme) throws ProblemeAffichageException {
		try{
			if(this instanceof Enfant) {
	            throw new ProblemeAccesException(this.affichagePseudoException() + "Vous êtes un enfant. Demandez à vos tuteurs de vous offrir un jeu.");
			}
			else if(this instanceof Standard) {
				if(nbrJeux >= this.getNbrMaxJeux()) {
		            throw new ProblemeLimiteException(this.affichagePseudoException() + "Vous avez atteint votre nombre maximal de jeu.");
				}
			}
			else if(PlateformeAvecJeux.containsKey(Plateforme) == false) {
	            throw new ProblemePlateformeException(this.affichagePseudoException() + "Vous ne possédez pas cette plateforme.");
			}
			else if(this.getReseauSocial().getJeux().existenceJeuxSurPlateforme(Jeu, Plateforme) == false) {
	            throw new ProblemePlateformeException(this.affichagePseudoException() + Jeu + " n'existe pas sur la plateforme " + Plateforme +  ".");
			}
			ArrayList<String> jeuxSurCettePlateforme = new ArrayList<String>();
			String nomPlateforme = "";
			for(Map.Entry<String, ArrayList<String>> name : PlateformeAvecJeux.entrySet()) {
				nomPlateforme = name.getKey();
				if(nomPlateforme.equals(Plateforme)) {
					jeuxSurCettePlateforme = name.getValue();
					if(jeuxSurCettePlateforme == null) {
						jeuxSurCettePlateforme = new ArrayList<String>();
						jeuxSurCettePlateforme.add(Jeu);
					}
					else {
						System.out.println(jeuxSurCettePlateforme);
						jeuxSurCettePlateforme.add(Jeu);
					}
				}
			}
			PlateformeAvecJeux.replace(Plateforme, jeuxSurCettePlateforme);
			nbrJeux += 1;
			System.out.println(Jeu + " a été ajouté à vos jeux de " + Plateforme + ".");
		}
		catch(ProblemeAccesException e) {
			System.err.println(e);
		}
		catch(ProblemeLimiteException f) {
			System.err.println(f);
		}
		catch(ProblemePlateformeException i){
			System.err.println(i);
		}
	}
	
	/** 
	*   Ajoute un jeu à une plateforme possédé
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	*/
	public void ajoutJeux() throws ProblemeAffichageException {
		String jeu;
		try{
			if(this instanceof Enfant) {
	            throw new ProblemeAccesException(this.affichagePseudoException() + "Vous êtes un enfant. Demandez à vos tuteurs de vous offrir un jeu.");
			}
			else if(this instanceof Standard) {
				if(nbrJeux >= this.getNbrMaxJeux()) {
		            throw new ProblemeLimiteException(this.affichagePseudoException() + "Vous avez atteint votre nombre maximal de jeu.");
				}
			}
			String plateforme;
			System.out.println(this.getPlateforme());
			System.out.println("Sur quelle plateforme voulez-vous ajouter le jeu ?");
			plateforme = scan.next();
			if(PlateformeAvecJeux.containsKey(plateforme) == false){
				do {
					System.err.println(plateforme + " ne fait pas partie de vos plateformes.");
					System.out.println(this.getPlateforme());
					System.out.println("Sur quelle plateforme voulez-vous ajouter le jeu ?");
					plateforme = scan.next();
				}while(PlateformeAvecJeux.containsKey(plateforme) == false);
			}
			this.getReseauSocial().getJeux().affichageJeuxParPlateforme(plateforme);
			System.out.println("Quel jeu voulez-vous ajouter sur " + plateforme + " ?");
			scan.nextLine();
			jeu = scan.nextLine();
			if(this.getReseauSocial().getJeux().existenceJeuxSurPlateforme(jeu, plateforme) == false){
				do {
					System.err.println(jeu + " n'est pas un jeu de " + plateforme + ".");
					this.getReseauSocial().getJeux().affichageJeuxParPlateforme(plateforme);
					System.out.println("Quel jeu voulez-vous ajouter sur " + plateforme + " ?");
					jeu = scan.nextLine();
				}while(this.getReseauSocial().getJeux().existenceJeuxSurPlateforme(jeu, plateforme) == false);
			}
			ArrayList<String> jeuxSurCettePlateforme = new ArrayList<String>();
			String nomPlateforme;
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
			nbrJeux += 1;
			System.out.println(jeu + " a été ajouté à vos jeux de " + plateforme + ".");
		}
		catch(ProblemeAccesException e) {
			System.err.println(e);
		}
		catch(ProblemeLimiteException f) {
			System.err.println(f);
		}
	}
	
	/** 
	*   Ajoute un jeu sur une plateforme possédé en demandant la catégorie du jeu
	 * @throws ProblemeAffichageException : Exception probleme d'affichage
	*/
	public void ajoutJeuxSelonCategorie() throws ProblemeAffichageException {
		try{
			if(this instanceof Enfant) {
	            throw new ProblemeAccesException(this.affichagePseudoException() + "Vous êtes un enfant. Demandez à vos tuteurs de vous offrir un jeu.");
			}
			else if(this instanceof Standard) {
				if(nbrJeux >= this.getNbrMaxJeux()) {
		            throw new ProblemeLimiteException(this.affichagePseudoException() + "Vous avez atteint votre nombre maximal de jeu.");
				}
			}
			String plateforme;
			System.out.println(this.getPlateforme());
			System.out.println("Sur quelle plateforme voulez-vous ajouter le jeu ?");
			plateforme = scan.next();
			if(PlateformeAvecJeux.containsKey(plateforme) == false){
				do {
					System.err.println(plateforme + " ne fait pas partie de vos plateformes.");
					System.out.println(this.getPlateforme());
					System.out.println("Sur quelle plateforme voulez-vous ajouter le jeu ?");
					plateforme = scan.next();
				}while(PlateformeAvecJeux.containsKey(plateforme) == false);
			}
			
			ArrayList<String> listeCate = this.getReseauSocial().getJeux().getlistePlateformeCategorie().get(plateforme);
			String nameCate = "[";
			for (int i = 0; i < listeCate.size(); i++) {
				if(nameCate.contains(listeCate.get(i) + ",") == false) {
					nameCate += listeCate.get(i) + ", ";
				}
	        }
			String cate;
			nameCate = nameCate.substring(0, nameCate.length()-2) + "]";
			System.out.println(nameCate);
			System.out.println("De quelle categorie voulez-vous ajouter un jeu ?");
			cate = scan.next();
			if(nameCate.contains(cate) == false){
				do {
					System.err.println(plateforme + " ne fait pas partie des categories de jeu de la " + plateforme + ".");
					System.out.println(this.getPlateforme());
					System.out.println("De quelle categorie voulez-vous ajouter un jeu ?");
					plateforme = scan.next();
				}while(nameCate.contains(cate) == false);
			}
			String jeu;
			this.getReseauSocial().getJeux().affichageJeuxParPlateforme(plateforme);
			System.out.println("Quel jeu voulez-vous ajouter sur " + plateforme + " ?");
			scan.nextLine();
			jeu = scan.nextLine();
			
			if(this.getReseauSocial().getJeux().existenceJeuxSurPlateforme(jeu, plateforme) == false){
				do {
					System.err.println(jeu + " n'est pas un jeu de " + plateforme + ".");
					this.getReseauSocial().getJeux().affichageJeuxParPlateforme(plateforme);
					System.out.println("Quel jeu voulez-vous ajouter sur " + plateforme + " ?");
					scan.nextLine();
					jeu = scan.nextLine();
				}while(this.getReseauSocial().getJeux().existenceJeuxSurPlateforme(jeu, plateforme) == false);
			}
			ArrayList<String> jeuxSurCettePlateforme = new ArrayList<String>();
			String nomPlateforme;
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
			nbrJeux += 1;
			System.out.println(jeu + " a été ajouté à vos jeux de " + plateforme + ".");
		}
		catch(ProblemeAccesException e) {
			System.err.println(e);
		}
		catch(ProblemeLimiteException f) {
			System.err.println(f);
		}
	}
	
	/** 
	*   Supprime un jeu d'une console possédé
	*/
	public void supprJeux() {
			String plateforme;
			System.out.println(this.getPlateforme());
			System.out.println("Sur quelle plateforme voulez-vous supprimer un jeu ?");
			plateforme = scan.next();
			if(PlateformeAvecJeux.containsKey(plateforme) == false){
				do {
					System.err.println(plateforme + " ne fait pas partie de vos plateformes.");
					System.out.println(this.getPlateforme());
					System.out.println("Sur quelle plateforme voulez-vous supprimer un jeu ?");
					plateforme = scan.next();
				}while(PlateformeAvecJeux.containsKey(plateforme) == false);
			}
			String jeu;
			ArrayList<String> jeuxSurPlateforme = this.PlateformeAvecJeux.get(plateforme);
			if(jeuxSurPlateforme.isEmpty()){
				System.err.println("Vous n'avez pas de jeu sur cette plateforme.");
			}
			else {
				System.out.println(jeuxSurPlateforme);
				System.out.println("Quel jeu voulez-vous supprimer sur " + plateforme + " ?");
				scan.nextLine();
				jeu = scan.nextLine();
				
				if(jeuxSurPlateforme.contains(jeu) == false){
					do {
						System.err.println("Vous ne possédez pas " + jeu + " sur " + plateforme + ".");
						System.out.println(jeuxSurPlateforme);
						System.out.println("Quel jeu voulez-vous supprimer sur " + plateforme + " ?");
						jeu = scan.nextLine();
					}while(jeuxSurPlateforme.contains(jeu) == false);
				}
				ArrayList<String> jeuxSurCettePlateforme = new ArrayList<String>();
				String nomPlateforme;
				for(Map.Entry<String, ArrayList<String>> name : PlateformeAvecJeux.entrySet()) {
					nomPlateforme = name.getKey();
					if(nomPlateforme.equals(plateforme)) {
						jeuxSurCettePlateforme = name.getValue();
					}	
				}
				jeuxSurCettePlateforme.remove(jeu);
				PlateformeAvecJeux.replace(plateforme,jeuxSurCettePlateforme);
				nbrJeux -= 1;
				System.out.println(jeu + " a été supprimé à vos jeux de " + plateforme + ".");		
			}
	}
	
	/** 
	*   Inviter un ami en faisant toutes les vérifications nécessaires
	*/
	void invitAmis() { 
		// Vérifie si le joueur existe ou pas
		String choixString;
		// Enlever le nom du joueur qui est en train d'inviter un ami
		String listePseudo = this.getReseauSocial().getListePseudo();
		if(listePseudo.contains("[" + this.getPseudo()+ "]")){
			listePseudo = listePseudo.replace(this.getPseudo(), "");
		}
		else if(listePseudo.contains("[" + this.getPseudo()+",")){
			listePseudo = listePseudo.replace(this.getPseudo() + ",", "");
		}
		else if(listePseudo.contains(this.getPseudo()+"]")){
			listePseudo = listePseudo.replace("," + this.getPseudo() + "]", "]");
		}
		else if(listePseudo.contains(this.getPseudo()+",")){
			listePseudo = listePseudo.replace("," + this.getPseudo() + ",", ",");
		}
		
		
		if(listePseudo.equals("[]")) {
			System.err.println("Il n'y pas d'autres joueurs présents sur ce réseau social");
		}else {
			System.out.println("Voici les joueurs présents sur ce réseau social : ");
			System.out.println(listePseudo);
			System.out.println("Choisissez un ami à inviter sur ce réseau social : ");
			choixString = scan.next();
			while(this.getReseauSocial().getListePseudo().contains(choixString) == false) {
				System.err.println(choixString + " n'est pas un joueur de ce réseau social.");
				System.out.println(listePseudo);
				System.out.println("Choisissez un ami à inviter sur ce réseau social : ");
				choixString = scan.next();
			}
			Joueur J = getJoueur(choixString, this.getReseauSocial());
			// On cherche à savoir quel est le type de joueur : Bot, Gold, Standard, Enfant
			// Pour cela on regarde la caractéristique nombre max partie multijoueur qui dépend pour chaque profil
			try {
				if(J == null) { // Problème car aucun profil a ce pseudo donc exception de get Joueur
			    } 
				// Si le joueur est déjà dans sa liste d'ami
				else if(this.dejaAmis(choixString) == true) {
					throw new ProblemeInvitationAmisException(this.affichagePseudoException() + "Vous êtes déjà amis avec ce joueur " + choixString + ".");
				}
				// Si le joueur est un enfant, un adulte ne peut pas être son ami
				else if(J instanceof Enfant) {
					throw new ProblemeInvitationAmisException(this.affichagePseudoException() + "Ce joueur est un enfant. Impossible d'être ami avec ce joueur.");
				}
				else if(this instanceof Enfant && J instanceof JHumainParent){
					throw new ProblemeInvitationAmisException(this.affichagePseudoException() + "Ce joueur est un compte Gold ou Standard. Impossible d'être ami avec ce joueur.");
				}
				// Problème du nombre d'ami max si un joueur est Standard
				else if(this instanceof Standard) {
					// Si nombre max amis => exception
					if(this.getNbrMaxAmis() <= this.getNbrAmis()) {
						throw new ProblemeInvitationAmisException(this.affichagePseudoException() + "Vous avez déjà " + this.getNbrMaxAmis() + " amis. Veuillez supprimer un ami.");
					}
					// Sinon invitation valide
					else {
						this.setAmis(choixString);
						J.setAmis(this.getPseudo());
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
						this.setAmis(choixString);
						J.setAmis(this.getPseudo());
					}
				}
				// Invitation joueur Bot ou Gold 
				else {
					this.setAmis(choixString);
					J.setAmis(this.getPseudo());
				}
			}
			catch(ProblemeInvitationAmisException e){
				System.err.println(e);
			}
		}
	}
	
	/** 
	*   Permet de jouer avec un ami à un jeu
	*   @throws ProblemeMisEnCommunException : Exception probleme mise en commun d'infos
	*/
	public void jeuMultijoueurAvecAmi() throws ProblemeMisEnCommunException {
	    Date date = new Date(System.currentTimeMillis());
	    String dateString = new SimpleDateFormat("dd-MM-yyyy").format(date);
		SimpleDateFormat datePartie = new SimpleDateFormat(dateString);
		String ami;
		System.out.println(this.getAmis());
		System.out.println("Avec quel amis voulez vous jouer en multijoueur ?");
		ami = scan.next();
		if(this.dejaAmis(ami) == false){
			do {
				System.err.println(ami + " ne fait pas partie de vos amis.");
				System.out.println(this.getAmis());
				System.out.println("Avec quel amis voulez vous jouer en multijoueur ?");
				ami = scan.next();
			}while(this.dejaAmis(ami) == false);
		} 
		Joueur joueurMulti = this.getJoueur(ami, getReseauSocial());
		try {
			if(this.getNbrPartiesMulti() >= this.getNbrMaxPartiesMulti()) {
				throw new ProblemeLimiteException("Vous avez atteint votre nombre maximal de parties multijoueurs de votre journée.");
			}
			if(joueurMulti instanceof Bot) {
			}
			else {
				JHumain joueurMultiHumain = (JHumain)joueurMulti;
				if(joueurMultiHumain.getNbrPartiesMulti() >= joueurMultiHumain.getNbrMaxPartiesMulti()) {
					throw new ProblemeLimiteException("Votre ami " + joueurMultiHumain.getPseudo()+ "a atteint son nombre maximal de parties multijoueurs de votre journée.");
				}

			}
			JHumain joueurMultiHumain = (JHumain)joueurMulti;
			if(joueurMultiHumain == null) {
				System.exit(0);
			}
			if(this.affichagePlateformeEnCommunAvecAmis(joueurMultiHumain) == null) {
			}
			else {
				String plateformeAmis = this.affichagePlateformeEnCommunAvecAmis(joueurMultiHumain);
				String plateforme;
				System.out.println(plateformeAmis);
				System.out.println("Sur quelle plateforme voulez-vous jouer avec votre ami " + joueurMultiHumain.getPseudo() + " en multijoueur ?");
				plateforme = scan.next();
				if(plateformeAmis.contains(plateforme) == false){
					do {
						System.err.println("La plateforme " + plateforme + " ne fait pas partie de vos plateformes en communs avec " + joueurMultiHumain.getPseudo() + ".");
						System.out.println(plateformeAmis);
						System.out.println("Sur quelle plateforme voulez-vous jouer avec votre ami " + joueurMultiHumain.getPseudo() + " en multijoueur ?");
						plateforme = scan.next();
					}while(plateformeAmis.contains(plateforme) == false);
				} 
				
				String jeuAmis = this.affichageJeuxEnCommunAvecAmisSelonPlateforme(joueurMultiHumain, plateforme);
				if(jeuAmis == null) {
				}
				else {
					String jeu;
					System.out.println(jeuAmis);
					System.out.println("A quel jeu voulez-vous en multijoueur avec votre ami " + joueurMultiHumain.getPseudo() + " ?");
					scan.nextLine();
					jeu = scan.nextLine();
					if(jeuAmis.contains(jeu) == false){
						do {
							System.err.println("Le jeu " + jeu + " ne fait pas partie de vos jeux en communs avec " + joueurMultiHumain.getPseudo() + " sur la plateforme " + plateforme + ".");
							System.out.println(jeuAmis);
							System.out.println("A quel jeu voulez-vous en multijoueur avec votre ami " + joueurMultiHumain.getPseudo() + " ?");
							scan.nextLine();
							jeu = scan.nextLine();
						}while(jeuAmis.contains(jeu) == false);
					}
					nbrPartiesMulti += 1;
					historiquesParties.add(datePartie + " : joué avec " + joueurMulti.getPseudo() + " à " + jeu + " sur la " + plateforme);
					System.out.println("Votre partie de " + jeu + " sur " + plateforme + " avec votre ami " + joueurMulti.getPseudo() + " a été enregistré avec succès.");
				
				}
			}
		}
		catch(ProblemeLimiteException e){
			System.err.println(e);
		}
	}
	
	/** 
	*   Permet de jouer avec un bot à un jeu	 
	* @throws ProblemeMisEnCommunException : Exception probleme mise en commun d'infos
	*/
	public void jeuMultijoueurAvecBot() throws ProblemeMisEnCommunException {
		Date date = new Date(System.currentTimeMillis());
	    String dateString = new SimpleDateFormat("dd-MM-yyyy").format(date);
		SimpleDateFormat datePartie = new SimpleDateFormat(dateString);
		String plateforme;
		System.out.println("Voici vos plateforme " + this.getPlateforme());
		System.out.println("Sur quelle plateforme voulez-vous jouer en multijoueur avec un bot ?");
		plateforme = scan.next();
		if(this.getPlateforme().contains(plateforme) == false){
			do {
				System.err.println("Vous ne possédez pas la plateforme " + plateforme + ".");
				System.out.println("Voici vos plateforme " + this.getPlateforme());
				System.out.println("Sur quelle plateforme voulez-vous jouer en multijoueur avec un bot ?");
				plateforme = scan.next();
			}while(this.getPlateforme().contains(plateforme) == false);
		} 
		ArrayList<String>  jeuxSelonPlateforme = new ArrayList<String>();
		for(Map.Entry<String, ArrayList<String>> name : this.getPlateformeAvecJeux().entrySet()) {
			if(name.getKey().equals(plateforme)) {
				jeuxSelonPlateforme = name.getValue();
			}
				
		}
		if(jeuxSelonPlateforme.isEmpty()) {
			System.err.println("Vous n'avez pas encore ajouté de jeu sur votre " + plateforme + ".");
		}		
		else {
			System.out.println("Voici vos jeux sur la plateforme " + plateforme + " : " + jeuxSelonPlateforme);
			System.out.println("A quel jeu voulez-vous en multijoueur avec un bot ?");
			String jeu;
			scan.nextLine();
			jeu = scan.nextLine();
			if(jeuxSelonPlateforme.contains(jeu) == false){
				do {
					System.err.println("Le jeu " + jeu + " ne fait pas partie de vos jeux sur la plateforme " + plateforme + ".");
					System.out.println("Voici vos jeux sur la plateforme " + plateforme + " : " + jeuxSelonPlateforme);
					System.out.println("A quel jeu voulez-vous en multijoueur avec un bot ?");
					scan.nextLine();
					jeu = scan.nextLine();
				}while(jeuxSelonPlateforme.contains(jeu) == false);
			}
			
			try {
				Map<Integer, String[]> listeJeux = this.getReseauSocial().getJeux().getlisteJeux();
				String[] jeuChoisi = new String[listeJeux.get(1).length];
				for(String[] name : listeJeux.values()) {
					if(name[0].equals(jeu)) {
						jeuChoisi = name;
						break;
					}
				}
				//Début IA année 90
				Integer anneeSortie = new Integer(jeuChoisi[2]);
				Bot joueurBot;
				if(anneeSortie < 1990) {
					throw new ProblemeLimiteException("Ce jeu ne possède pas d'intelligence artificielle.");
				}
				if(jeuChoisi[jeuChoisi.length-1].equals("")) {
					joueurBot = new Bot("Bot"+jeu,jeu,this.getReseauSocial());
				}
				else {
					joueurBot = this.getReseauSocial().getListePseudoBot().get(jeuChoisi[jeuChoisi.length-1]);
				}
				
				nbrPartiesMulti += 1;
				historiquesParties.add(datePartie + " : joué avec " + joueurBot.getPseudo() + " à " + jeu + " sur la " + plateforme);
				System.out.println("Votre partie de " + jeu + " sur " + plateforme + " avec votre ami " + joueurBot.getPseudo() + " a été enregistré avec succès.");
			}
			catch(ProblemeLimiteException e){
				System.err.println(e);
			}
		}
	}		
}
