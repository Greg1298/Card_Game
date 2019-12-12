package Modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * JoueurReel est une classe qui h�rite de Joueur et permet de sp�cifier les m�thodes qui sont diff�rentes de JoueurVirtuel
 * @author grego
 * @see JoueurVirtuel
 */
public class JoueurReel extends Joueur {

	/**
	 * Contructeur de JoueurReel
	 */
	public JoueurReel() {
		super();
		
	}

	@Override
	/**
	 * M�thode qui permet de choisir le Trick que l'on va tenter de Perform
	 */
	public void choisirUnTrick(JeuDeCarte carte,int nbTrick) {
		if(!carte.deckEmpty()) {
	    int choisir=nbTrick;
	    if(choisir==2) 
		this.trickPerform = carte.getSecond();
	    if(choisir==1)
		this.trickPerform = carte.getTop();
		}
		else {
			this.trickPerform = carte.getTop();
		}
	}

	@Override
	/**
	 * M�thode qui permet de choisir le Joueur avec lequel on va �changer une Props
	 */
	public void echangerProps(ArrayList<Joueur> list,int my,int myTrick,int otherTrick) {
		int n1=my;
		//right=2;left=1;
		
		int choix=myTrick;
		int choose=otherTrick;
		//Si le joueur choisit d'�changer sa carte de gauche
		if(choix==1) {
			//Si le joueur choisit d'�changer sa carte gauche avec la carte de gauche du joueur choisi
			if(choose==1) {
				Props swapGauche=list.get(n1).getGauche();
				list.get(n1).setGauche(this.gauche);
				this.setGauche(swapGauche);
			} //Si le joueur choisit d'�changer sa carte gauche avec la carte de droite du joueur choisi
			else if (choose==2) {
				Props swapDroite=list.get(n1).getDroite();
				list.get(n1).setDroite(this.gauche);
				this.setGauche(swapDroite);
			}
		}
		//Si le joueur choisit d'�changer sa carte de droite
		if(choix==2) {
			//Si le joueur choisit d'�changer sa carte droite avec la carte de gauche du joueur choisi
			if(choose==1) {
				Props swapGauche=list.get(n1).getGauche();
		   	     list.get(n1).setGauche(this.droite);
		   	    this.setDroite(swapGauche);
			} //Si le joueur choisit d'�changer sa carte droite avec la carte de droite du joueur choisi
			else if (choose==2) {
				Props swapDroite=list.get(n1).getDroite();
		   	     list.get(n1).setDroite(this.droite);
		   	    this.setDroite(swapDroite);
			}
		}
		//On notifie les observer (notamment MonInterface)
		this.setChanged();
	    this.notifyObservers("swap");
	    list.get(n1).setChanged();
	    list.get(n1).notifyObservers("swap");
	    
	}
	
 @Override
 /**
  * M�thode qui permet de choisir quelle carte on va d�voiler apr�s avoir �chou�
  */
 public void setFaceUp(int nb) {
	 if(this.droite.isFace_up())this.gauche.setFace_up(true);
	 if(this.gauche.isFace_up())this.droite.setFace_up(true);
	 if((!this.droite.isFace_up())&&(!this.gauche.isFace_up())) {
		 int n=nb;
		 if(n==1)this.droite.setFace_up(true);
		 if(n==2)this.gauche.setFace_up(true);
	 }
	//On notifie les observer du changement (notamment MonInterface)
	 this.setChanged();
	 this.notifyObservers("faceUp");
	  
 }
}

