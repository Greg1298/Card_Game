package Modele;

/**
 * Interface de l'extension
 * @author grego
 *
 */
public interface Extension {
	public void NewJeuDeCarte();
	public int getNbProps();
	public int getNbTrick();
	public Trick getTrickExt(int i);
}
