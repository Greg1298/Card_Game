package Modele;

/**
 * Newrule est l'interface que les variantes impémentent
 * @author grego
 *
 */
public interface NewRule {
	/**
	 * Méthode abstraite qui rajoute des règles dans les variantes
	 * @param p
	 * @param ordre
	 * @param choix
	 * @param nb
	 */
	public void NewJouer(Partie p,int ordre,int[] choix,int nb);
	/**
	 * Méthode abstraite qui permet de vérifier que des conditions sont réunies
	 * @return
	 */
    public boolean isFlag();
}
