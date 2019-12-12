package Modele;

/**
 * Observer est une interface utiliser dans le cadre du patron de conception MVC
 * Il permet impl�menter par les interfaces du package Vue
 * @author grego
 *
 */
public interface Observer {
	/**
	 * M�thode abstraite qui permet de faire des actions quand des classes observables notifie les observer
	 * @param arg
	 * @param o
	 */
	public void update(Observable arg,Object o);
}