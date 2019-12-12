package Modele;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Observable est une classe qui permet de mettre en place le patron de conception MVC
 * Les objets qui doivent �tre observ�s par un controleur h�rite de cette classe
 * @author grego
 *
 */
public class Observable {
	
	/**
	 * Tableau d'observer
	 */
	private HashSet<Observer> observers;
	
	/**
	 * Bool�en qui permet de savoir si un objet a �t� modifi�
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
	 * m�thode pour ajouter un observer
	 * @param o
	 */
	public void addObserver(Observer o) {
			observers.add(o);
	}
	
	/**
	 * m�thode pour supprimer un observer
	 * @param o
	 */
	public void deleteObserver(Observer o) {
		observers.remove(o);
	}
	
	/**
	 * m�thode pour notifier les observer
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
	 * m�thode pour d�finir un changement
	 */
	public void setChanged() {
		hasChanged=true;
	}
	
	/**
	 * M�thode pour effacer les changements
	 */
	public void clearChanged() {
		hasChanged=false;
	}

}
