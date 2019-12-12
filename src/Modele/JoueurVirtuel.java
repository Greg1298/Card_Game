package Modele;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * JoueurVirtuel est la classe qui contient les méthodes à effectuer lors d'un tour de jeu d'un joueur virtuel
 * @author grego
 *
 */
public class JoueurVirtuel extends Joueur {
	/**
	 * Constructeur du joueur virtuel défini dans la classe Joueur
	 * @see Joueur
	 */
	public JoueurVirtuel() {
	super();
	}
		@Override
		/**
		 * Méthode qui permet de sélectionner un Trick à effectuer pour le joueur virtuel
		 */
		public void choisirUnTrick(JeuDeCarte carte,int nbTrick) {
		    this.trickPerform=carte.getTop();
	 }
	    @Override
	    /**
	     * Méthode qui permet au joueur virtuel d'échanger une carte avec un autre joueur
	     * @param list
	     * @param my
	     * @param myTrick
	     * @param otherTrick
	     * 
	     */
		public void echangerProps(ArrayList<Joueur> list,int my,int myTrick,int otherTrick) {
	    	 int n=this.ordre%3;
	    	 //Le joueur virtuel choisi toujours d'échanger sa carte de droite
	    	 Props swapDroite=list.get(n).getDroite();
	    	 list.get(n).setDroite(this.droite);
	    	 //Le joueur virtuel échange sa carte avec la carte droite d'un joueur
	    	 this.setDroite(swapDroite);
	    	this.setChanged();
	 	    this.notifyObservers("swap");
	 	    list.get(n).setChanged();
	 	    list.get(n).notifyObservers("swap");
	}
	    /**
	     * Méthode qui permet de dévoiler une props du joueur virtuel en cas d'échec
	     * @param nb qui n'est utiliser que pour les joueurs réels
	     */
	    public void setFaceUp(int nb) {
			if(this.droite.isFace_up()) {
				this.gauche.setFace_up(true);
			} else if((!this.droite.isFace_up())&&(!this.gauche.isFace_up())) {
		    	this.droite.setFace_up(true);
		    }
			this.setChanged();
			this.notifyObservers("faceUp");
	}

}