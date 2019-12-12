package Modele;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * JoueurVirtuel est la classe qui contient les m�thodes � effectuer lors d'un tour de jeu d'un joueur virtuel
 * @author grego
 *
 */
public class JoueurVirtuel extends Joueur {
	/**
	 * Constructeur du joueur virtuel d�fini dans la classe Joueur
	 * @see Joueur
	 */
	public JoueurVirtuel() {
	super();
	}
		@Override
		/**
		 * M�thode qui permet de s�lectionner un Trick � effectuer pour le joueur virtuel
		 */
		public void choisirUnTrick(JeuDeCarte carte,int nbTrick) {
		    this.trickPerform=carte.getTop();
	 }
	    @Override
	    /**
	     * M�thode qui permet au joueur virtuel d'�changer une carte avec un autre joueur
	     * @param list
	     * @param my
	     * @param myTrick
	     * @param otherTrick
	     * 
	     */
		public void echangerProps(ArrayList<Joueur> list,int my,int myTrick,int otherTrick) {
	    	 int n=this.ordre%3;
	    	 //Le joueur virtuel choisi toujours d'�changer sa carte de droite
	    	 Props swapDroite=list.get(n).getDroite();
	    	 list.get(n).setDroite(this.droite);
	    	 //Le joueur virtuel �change sa carte avec la carte droite d'un joueur
	    	 this.setDroite(swapDroite);
	    	this.setChanged();
	 	    this.notifyObservers("swap");
	 	    list.get(n).setChanged();
	 	    list.get(n).notifyObservers("swap");
	}
	    /**
	     * M�thode qui permet de d�voiler une props du joueur virtuel en cas d'�chec
	     * @param nb qui n'est utiliser que pour les joueurs r�els
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