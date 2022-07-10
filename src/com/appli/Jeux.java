package com.appli;


// Import
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Integer;
import java.util.HashMap;
import java.util.Map;
//import java.util.Set;
import java.util.Collection;
import java.util.ArrayList;



//Exception
@SuppressWarnings("serial")
class ProblemeAffichageException extends Exception {
	public ProblemeAffichageException(String message) {
        super(message);
    }
}





/**
 * un jeu a une IA si il est sorti l'ann�e 1990 et apr�s
 * @author gifhu
 */
public class Jeux {
	// Arrondir les d�cimaux � deux chiffres apr�s la virgule
	java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
	
	
	// Variables de Jeux
	private Map<Integer, String[]>         listeJeux                 = new HashMap<Integer, String[]>();
	private Map<String, String>            listePlateformePublisher  = new HashMap<String, String>();
	private Map<String, ArrayList<String>> listePlateformeCategorie  = new HashMap<String, ArrayList<String>>();

	/** 
	*   Constructeur
	*/
	Jeux(){		
	}
	
	/** 
	*   M�thode permettant d'ins�rer les donn�es d'un fichier dans des maps pour pouvoir utiliser les donn�es
	*   @param pathToFile : chemin vers le fichier
	*/
	public void readFile(String pathToFile){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(pathToFile)));
            String ligne;
            while((ligne = reader.readLine()) != null){
            	// On veut skip les lignes qui ne commencent pas par des nombres
            	if(ligne.charAt(3) == ('R')) { // Trois premi�res lettres de la premi�re ligne sont ﻿
            	}
            	else {
            		String info    = "";
                	String[] words = ligne.split("\t"); // S�parer une ligne en un tableau gr�ce aux tabulations
                    int num        = Integer.parseInt(words[0]); // Convertir la premi�re colonne du fichier en int
                	// Recr�er le String avec le tableau word sans la premi�re colonne
                    info           = words[1];
                	for(int i=2;i<words.length;i++) {
                		info += "\t" + words[i];
                	}
                	
                	String[] info_tab = info.split("\t"); // Res�parer le String en un tableau sans la premi�re colonne
                	String[] infoTabBot = new String[info_tab.length +1];
                	for(int i = 0;i<info_tab.length;i++) {
                		infoTabBot[i] = info_tab[i];
                	}
                	infoTabBot[info_tab.length] = "";
                	// Ajout des plateformes avec Publisher dans la map en sachant qu'une plateforme est cr�� par un seul Publisher :
                	//	- si la map contient d�j� la cl�, ne rien faire
                	//	- sinon ajouter la cl�-valeur
                	if(listePlateformePublisher.containsKey(words[2])) { 
                	}
                	else {
                        listePlateformePublisher.put(words[2], words[5]);
                	}
                	// Ajout des cat�gories pr�sentes sur chaque plateforme : 
                	// 	- si la map contient d�j� la cl�, remplacer la cl�-valeur en ajoutant la cat�gorie au valeur
                	//	- sinon ajouter la cl�-valeur
                	if(listePlateformeCategorie.containsKey(words[2])) {
                		ArrayList<String> cate = listePlateformeCategorie.get(words[2]);
                        cate.add(words[4]);//.add(words[4]);
                        listePlateformeCategorie.replace(words[2], cate);
                	}
                	else {
                		ArrayList<String> cateList = new ArrayList<String>();
                		cateList.add(words[4]);
                        listePlateformeCategorie.put(words[2], cateList);
                	}
                	// Ajout num�ro et tableau � la map liste Jeux
                    listeJeux.put(num, infoTabBot);
            	}  
            }
            // Affichage pour utilisateur
        	System.out.println("Vous avez charg� " + listeJeux.size() + " jeux avec succ�s.");
        	System.out.println(this.affichagePlateforme());
        	System.out.println(this.affichageCategorie());
        } catch (Exception ex){
            System.err.println("Erreur d'ouverture.");
        }
    }
	
	
	
	
	
	// Get et Set:
	
		// Get listePlateformeCategorie
	/**
	 * @return Map du num�ro des jeux avec leur informations dans un tableau
	 */
	public Map<Integer, String[]> getlisteJeux() {
		return listeJeux;
	}
	
	/**
	 * @return Map de la plateforme avec leurs cat�gories
	 */
	public Map<String, ArrayList<String>> getlistePlateformeCategorie() {
		return listePlateformeCategorie;
	}
	
	/**
	 * @return Map es plateformes avec leur �diteur
	 */
	public Map<String, String> getListePlateformePublisher() {
		return listePlateformePublisher;
	}
	
	
	// V�rification existence :
	
	/** 
	*   V�rifier si la plateforme existe dans le r�seau social
	*   @param plateforme : pseudo de la plateforme � v�rifier
	*   @return boolean vrai si la plateforme existe sur le r�seau social
	*/
	public boolean existencePlateforme(String plateforme) { // Il faut juste v�rifier si la map contient en cl� la valeur de la plateforme
		if(listePlateformePublisher.containsKey(plateforme)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/** 
	*   V�rifie si la cat�gorie existe dans le r�seau social (une ct�gorie peut �tre sp�cifique � une plateforme)
	*   @param categorie : pseudo de la cat�gorie � v�rifier	
	*   @return boolean vrai si la categorie existe sur le r�seau social
	*/
	public boolean existenceCategorie(String categorie) { // Il faut v�rifier si dans chaque valeur de chaque cl� de la map, la cat�gorie existe ou pas 
		Collection<ArrayList<String>> cate = listePlateformeCategorie.values();
		for (ArrayList<String> name : cate) {
        	if(name.contains(categorie)) {
        		return true;
        	}
        }
		return false;
	}
	
	/** 
	*   V�rifie si un jeu existe sur une plateforme sp�cifique
	*   @param jeu : jeu � v�rifier l'existence 
	*   @param plateforme : plateforme o� l'on veut v�rifier l'existence
	*   @return boolean vrai si jeu existe sur la plateforme sur le r�seau social
	* @throws ProblemeAffichageException : Exception probleme d'affichage
	*/
	public boolean existenceJeuxSurPlateforme(String jeu, String plateforme) throws ProblemeAffichageException{
		String rechercheJeu = this.affichageJeux(jeu); // Stocker l'affichage des informations d'un jeu
		if(rechercheJeu == null) { // Si l'information est nul le jeu n'existe pas
			return false;
		}
		else {
			// Sinon comme l'affichage des infos d'un jeu affiche toutes les plateformes sur lequel il existe :
			// On regarde si l'affichage contient la plateforme en ajoutant une virgule ou point car chaque plateforme est suivi d'une virgule ou point dans l'affichage
			if(rechercheJeu.contains(plateforme + ".") || rechercheJeu.contains(plateforme + ",")) { 
				//Si on prend pas en compte la virgule ou le point, PS peut etre valid� alors que la plateforme est PS3, PS4, PSV...
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	
	
	
	// Affichage :
	
	/** 
	*   Affichage Jeux gr�ce au nom du jeu
	*   @param nomJeu : jeu que l'on veut afficher
	*   @return String affichage des infos d'un jeu
	*/
	public String affichageJeux(String nomJeu)  {
		String info = "";
		try {
			Collection<String[]> nb = listeJeux.values();
			String[] jeu      = null;
			String plateforme = "";
			// Affichage des plateformes ainsi que le total des nombre de ventes (toutes plateformes confondues)
			double nbVentes   = 0.0;
            for (String[] name : nb) {
            	if(name[0].equals(nomJeu)) {
            		nbVentes += Double.parseDouble(name[name.length - 2]);
            		plateforme += name[1] + ", ";
            		jeu = name;
            	}
            }
			if(jeu == null) {
	            throw new ProblemeAffichageException(nomJeu + " n'est pas un jeu de la base de donn�es.");
			}
			// Affichage infos sp�cifiques + plateforme + ventes
			info  = jeu[0] + " est un jeu de " + jeu[3] + " sorti par " + jeu[4] + " en " + jeu[2] + " avec un total de ventes de "
			+ df.format(nbVentes) + " Millions. Ce jeu est disponible sur " + plateforme;
			info  = info.substring(0, info.length()-2) + "."; // On retire la virgule et l'espace en trop de fin
	    	return info;			
		}
		catch(Exception e){
			System.err.println(e);
			return null;
		}		
	}
	
	
	/** 
	*   Affichage Jeux gr�ce � son num�ro d'identifiant dans la base de donn�es
	*   @param idJeu : num�ro d'un jeu que l'on veut afficher
	*   @return String affichage des infos d'un jeu
	*/
	public String affichageJeux(Integer idJeu) {
		try {
			// Id du jeu inexistant : nombre compris entre 1 et 16559
			if(!listeJeux.containsKey(idJeu)) {
	            throw new ProblemeAffichageException(idJeu + " n'est pas un identifiant de la base de donn�es. Les identifiants de la base de donn�es sont entre 1 et " + listeJeux.size() + ".");
			}
			// Affichage des infos sp�cifiques du jeu � partir du num�ro identifiant dans la map
			String info   = "";
			String[] jeu  = listeJeux.get(idJeu);
			String nomJeu = jeu[0];
			String plateforme = "";
			info = jeu[0] + " est un jeu de " + jeu[3] + " sorti par " + jeu[4] + " en " + jeu[2]; 
			Collection<String[]> nb = listeJeux.values();
			double nbVentes = 0.0;
			// Affichage des plateformes ainsi que le total des nombre de ventes (toutes plateformes confondues)
            for (String[] name : nb) {
            	if(name[0].equals(nomJeu)) {
            		nbVentes += Double.parseDouble(name[name.length - 2]);
            		plateforme += name[1] + ", ";
            	}
            }
            info += " avec un total de ventes de " + df.format(nbVentes) + " Millions. Ce jeu est disponible sur " + plateforme; 
            info = info.substring(0, info.length()-2) + "."; // On retire la virgule et l'espace en trop de fin
	    	return info;			
		}
		catch(Exception e){
			System.err.println(e);
			return null;
		}
	}
	
	/** 
	*   Affichage plateforme de ce r�seau social
	*   @return String affichage des plateformes du r�seau social
	*/
	public String affichagePlateforme(){	
		String plateforme = "Ce r�seau social prend en charge ces plateformes : \n[";
		Collection<String> namePlat = listePlateformePublisher.keySet();
		// Affichage des plateformes
        for (String name : namePlat) {
        	plateforme += name + ", ";
        }
		plateforme = plateforme.substring(0, plateforme.length()-2) + "]"; // On retire la virgule et l'espace en trop de fin
		return plateforme;
	}
	
	/** 
	*	Affichage cat�gorie de ce r�seau social
	*	@return String affichage des cat�gories du r�seau social
	*/
	public String affichageCategorie(){	
		String categorie = "Ce r�seau social prend en charge ces categories : \n[";
		Collection<ArrayList<String>> cate = listePlateformeCategorie.values();
		// Affichage cat�gories en v�rifiant qu'on n'a pas d�j� ajouter la cat�gorie
        for (ArrayList<String> listCate : cate) {
        	for(String name : listCate) {
            	if(categorie.contains(name)) {
            	}
            	else {
                	categorie += name + ", ";
            	}
        	}
        }
		categorie = categorie.substring(0, categorie.length()-2) + "]" ; // On retire la virgule et l'espace en trop de fin
		return categorie;
	}
	
	/** 
	*   Affichage de tous les jeux d'une plateforme
	*   @param plateformeAffichageJeux : plateforme dont on souhaite afficher les jeux
	*/
	public void affichageJeuxParPlateforme(String plateformeAffichageJeux){
		try {
			if(listePlateformePublisher.containsKey(plateformeAffichageJeux)) {
				String plateforme = "Voici les jeux pr�sents sur la plateforme " + plateformeAffichageJeux + " : \n[";
				Collection<String[]> namePlat = listeJeux.values();
				// Affichage Jeux de la plateforme en v�rifiant que chaque jeu est dans la plateforme ou non
		        for (String[] name : namePlat) {
		        	if(name[1].equals(plateformeAffichageJeux)) {
		            	plateforme += name[0] + "/ ";
		        	}
		        }
				plateforme = plateforme.substring(0, plateforme.length()-2); // On retire la virgule et l'espace en trop de fin
		        plateforme += "]" ;
				System.out.println(plateforme);
			}
			// Plateforme n'appartient pas � ce r�seau social
			else {
	            throw new ProblemeAffichageException(plateformeAffichageJeux + " n'est pas une plateforme de ce r�seau social.");
			}
		}
		catch(ProblemeAffichageException e) {
			System.err.println(e);
		}
	}
	
	/** 
	*   Affichage de tous les jeux d'une categorie
	*   @param categorieAffichageJeux : categorie dont on souhaite afficher les jeux
	*/
	public void affichageJeuxParCategorie(String categorieAffichageJeux){
		try {
			if(this.existenceCategorie(categorieAffichageJeux)) {
				String categorie = "Voici les jeux de la cat�gorie " + categorieAffichageJeux + " : \n[";
				Collection<String[]> namePlat = listeJeux.values();
				// Affichage Jeux de la cat�gorie en v�rifiant que chaque jeu est de cette cat�gorie ou non
		        for (String[] name : namePlat) {
		        	if(name[3].equals(categorieAffichageJeux)) {
		            	categorie += name[0] + "/ ";
		        	}
		        }
				categorie = categorie.substring(0, categorie.length()-2); // On retire la virgule et l'espace en trop de fin
		        categorie += "]" ;
				System.out.println(categorie);
			}
			// Cat�gorie n'appartient pas � ce r�seau social
			else {
	            throw new ProblemeAffichageException(categorieAffichageJeux + " n'est pas une cat�gorie de ce r�seau social.");
			}
		}
		catch(ProblemeAffichageException e) {
			System.err.println(e);
		}
	}
}
