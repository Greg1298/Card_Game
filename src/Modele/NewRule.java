package Modele;

/**
 * Newrule est l'interface que les variantes imp�mentent
 * @author grego
 *
 */
public interface NewRule {
	/**
	 * M�thode abstraite qui rajoute des r�gles dans les variantes
	 * @param p
	 * @param ordre
	 * @param choix
	 * @param nb
	 */
	public void NewJouer(Partie p,int ordre,int[] choix,int nb);
	/**
	 * M�thode abstraite qui permet de v�rifier que des conditions sont r�unies
	 * @return
	 */
    public boolean isFlag();
}
