package Modele;
import java.util.ArrayList;

/**
 * Joueur est une classe abstraite qui va servir aux classe JoueurReel et JoueurVirtuel
 * @author grego
 *
 */
public abstract class Joueur extends Observable{
	/**
	 * L'ordre représente le joueur qui est en train de jouer
	 */
	 protected int ordre;
	 /**
	  * Le score du Joueur
	  */
	 protected int score;
	 /**
	  * Le nombre de fois que l'on échoue à faire TheOtherHatTrick
	  */
	 protected int nbFail;
	 /**
	  * La Props droite du joueur
	  */
	 protected Props droite;
	 /**
	  * La Props gauche de joueur
	  */
	 protected Props gauche;
	 /**
	  * Un tableau des Trick réussis par le joueur
	  */
	 protected ArrayList<Trick> trickSucceed;
	 /**
	  * Le score du Joueur
	  */
	 protected Trick trickPerform;
	 /**
	  * Le nombre de joueur instancié
	  */
	 protected static int instanceCount=0;
	 
	/**
	 * Constructeur de la classe Joueur
	 */
	 public Joueur() {
		 instanceCount++;
	     this.ordre = instanceCount;
		this.score=0;this.nbFail=0;
		this.trickSucceed=new ArrayList<Trick>();
	}
	 
	 /**
	  * Méthode abstraite du choix de Trick redéfinie dans Joueur Réel et Joueur Virtuel
	  * @param carte
	  * @param nbTrick
	  */
	 public abstract void choisirUnTrick(JeuDeCarte carte,int nbTrick);//top return true other false
	 
	 /**
	  * Méthode abstraite de l'échange de Props redéfinie dans Joueur Réel et Joueur Virtuel
	  * @param list
	  * @param my
	  * @param myTrick
	  * @param otherTrick
	  */
	 public abstract void echangerProps(ArrayList<Joueur> list,int my,int myTrick,int otherTrick);
	 
	 /**
	  * Méthode qui renvoit la Props droite d'un joueur
	  * @return
	  */
	 public Props getDroite() {
		return droite;
	}
	 
	 /**
	  * Méthode qui modifie la Props droite d'un joueur
	  * @param droite
	  */
	public void setDroite(Props droite) {
		this.droite = droite;
	}
	
	/**
	 * Méthode qui renvoit la Props gauche d'un joueur
	 * @return
	 */
	public Props getGauche() {
		return gauche;
	}
	
	/**
	 * Méthode qui modifie la Props droite d'un joueur
	 * @param gauche
	 */
	public void setGauche(Props gauche) {
		this.gauche = gauche;
	}
	
	/**
	 * Méthode qui permet de tester la réussite ou non du Trick
	 * @param carte
	 * @param nb
	 */
	public void performTrick(JeuDeCarte carte,int nb) {
		boolean success=this.trickPerform.performerSucceed(this.gauche, this.droite);
		//En cas de succès :
		if(success==true) {   
			//Le score est mis à jour
		    this.score+=this.trickPerform.getPoint();
		    //Le Trick réussit est ajouter dans le tableau trickSucceed
			this.trickSucceed.add(this.trickPerform);
			carte.retirerTrick();
			//melanger avec la 7ème Props 
			carte.getPropsPile().add(droite);
			carte.getPropsPile().add(gauche);
			carte.melangerProps();
			this.droite=carte.getPropsPile().remove(0);
			this.gauche=carte.getPropsPile().remove(0);
			this.droite.setFace_up(false);
			this.gauche.setFace_up(false);
			//Si la pile est vide, on ajoute un Trick
			carte.ajouterTrick();
			this.setChanged();
			this.notifyObservers("success");
		}
		else{   //On dévoile une carte
			this.setFaceUp(nb);
			this.setChanged();
			this.notifyObservers("fail");
		}
	}
	
	/**
	 * Méthode qui renvoit l'ordre du joueur en cours
	 * @return
	 */
	public int getOrdre() {
		return ordre;
	}
	
	@Override
	/**
	 * Méthode qui affiche les informations sur un joueur (nb de point, cartes Props, ordre)
	 */
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append("Joueur "+this.ordre+" [");
		if(this.gauche.isFace_up())sb.append("gauche:"+this.gauche);
		if(this.droite.isFace_up())sb.append("droite:"+this.droite);
		sb.append(" score "+this.score);
		sb.append("]");
		return sb.toString();
		}
	
	/**
	 * Méthode qui renvoit le score du joueur
	 * @return
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Méthode qui permet de modifier le score du joueur
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	} 
	
	/**
	 * Méthode abstraite pour dévoiler des cartes Props
	 * @param nb
	 */
	public abstract void setFaceUp(int nb);
	
	/**
	 * Méthode qui permet d'obtenir le Trick réussi
	 * @return
	 */
	public Trick  getCardSuccess() {
		return this.trickSucceed.get(0);
	}
}
