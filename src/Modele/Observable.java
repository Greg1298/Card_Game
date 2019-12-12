package Modele;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Observable est une classe qui permet de mettre en place le patron de conception MVC
 * Les objets qui doivent être observés par un controleur hérite de cette classe
 * @author grego
 *
 */
public class Observable {
	
	/**
	 * Tableau d'observer
	 */
	private HashSet<Observer> observers;
	
	/**
	 * Booléen qui permet de savoir si un objet a été modifié
	 */
	private boolean hasChanged;
	
	/**
	 * Constructeur de la classe Observable 
	 */
	public Observable() {
		observers=new HashSet<Observer>();
		hasChanged=false;
	}
	
	/**
	 * méthode pour ajouter un observer
	 * @param o
	 */
	public void addObserver(Observer o) {
			observers.add(o);
	}
	
	/**
	 * méthode pour supprimer un observer
	 * @param o
	 */
	public void deleteObserver(Observer o) {
		observers.remove(o);
	}
	
	/**
	 * méthode pour notifier les observer
	 * @param o
	 */
	public void notifyObservers(Object o) {
		if(hasChanged==true) {
			for(Iterator<Observer> it=observers.iterator();it.hasNext();) {
				((Observer) it.next()).update(this,o);
			}
			hasChanged=false;
		}
	}
	
	/**
	 * méthode pour définir un changement
	 */
	public void setChanged() {
		hasChanged=true;
	}
	
	/**
	 * Méthode pour effacer les changements
	 */
	public void clearChanged() {
		hasChanged=false;
	}

}
