package Modele;
import java.util.ArrayList;

/**
 * Joueur est une classe abstraite qui va servir aux classe JoueurReel et JoueurVirtuel
 * @author grego
 *
 */
public abstract class Joueur extends Observable{
	/**
	 * L'ordre repr�sente le joueur qui est en train de jouer
	 */
	 protected int ordre;
	 /**
	  * Le score du Joueur
	  */
	 protected int score;
	 /**
	  * Le nombre de fois que l'on �choue � faire TheOtherHatTrick
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
	  * Un tableau des Trick r�ussis par le joueur
	  */
	 protected ArrayList<Trick> trickSucceed;
	 /**
	  * Le score du Joueur
	  */
	 protected Trick trickPerform;
	 /**
	  * Le nombre de joueur instanci�
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
	  * M�thode abstraite du choix de Trick red�finie dans Joueur R�el et Joueur Virtuel
	  * @param carte
	  * @param nbTrick
	  */
	 public abstract void choisirUnTrick(JeuDeCarte carte,int nbTrick);//top return true other false
	 
	 /**
	  * M�thode abstraite de l'�change de Props red�finie dans Joueur R�el et Joueur Virtuel
	  * @param list
	  * @param my
	  * @param myTrick
	  * @param otherTrick
	  */
	 public abstract void echangerProps(ArrayList<Joueur> list,int my,int myTrick,int otherTrick);
	 
	 /**
	  * M�thode qui renvoit la Props droite d'un joueur
	  * @return
	  */
	 public Props getDroite() {
		return droite;
	}
	 
	 /**
	  * M�thode qui modifie la Props droite d'un joueur
	  * @param droite
	  */
	public void setDroite(Props droite) {
		this.droite = droite;
	}
	
	/**
	 * M�thode qui renvoit la Props gauche d'un joueur
	 * @return
	 */
	public Props getGauche() {
		return gauche;
	}
	
	/**
	 * M�thode qui modifie la Props droite d'un joueur
	 * @param gauche
	 */
	public void setGauche(Props gauche) {
		this.gauche = gauche;
	}
	
	/**
	 * M�thode qui permet de tester la r�ussite ou non du Trick
	 * @param carte
	 * @param nb
	 */
	public void performTrick(JeuDeCarte carte,int nb) {
		boolean success=this.trickPerform.performerSucceed(this.gauche, this.droite);
		//En cas de succ�s :
		if(success==true) {   
			//Le score est mis � jour
		    this.score+=this.trickPerform.getPoint();
		    //Le Trick r�ussit est ajouter dans le tableau trickSucceed
			this.trickSucceed.add(this.trickPerform);
			carte.retirerTrick();
			//melanger avec la 7�me Props 
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
		else{   //On d�voile une carte
			this.setFaceUp(nb);
			this.setChanged();
			this.notifyObservers("fail");
		}
	}
	
	/**
	 * M�thode qui renvoit l'ordre du joueur en cours
	 * @return
	 */
	public int getOrdre() {
		return ordre;
	}
	
	@Override
	/**
	 * M�thode qui affiche les informations sur un joueur (nb de point, cartes Props, ordre)
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
	 * M�thode qui renvoit le score du joueur
	 * @return
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * M�thode qui permet de modifier le score du joueur
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	} 
	
	/**
	 * M�thode abstraite pour d�voiler des cartes Props
	 * @param nb
	 */
	public abstract void setFaceUp(int nb);
	
	/**
	 * M�thode qui permet d'obtenir le Trick r�ussi
	 * @return
	 */
	public Trick  getCardSuccess() {
		return this.trickSucceed.get(0);
	}
}
