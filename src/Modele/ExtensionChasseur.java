package Modele;

/**
 * Extension chasseur implémente l'interface Extension
 * @author grego
 * @see Extension
 */
public class ExtensionChasseur implements Extension {

	/**
	 * Le nombre de Props de l'extension
	 */
	private int nbPropsExt;
	/**
	 * Le nombre de Trick de l'extension
	 */
	private int nbTrickExt;
	/**
	 * Le tableau contenant les Tricks de l'extension
	 */
	private Trick[] tabTrick;

	/**
	 * Constructeur de l'extension chasseur
	 */
	public ExtensionChasseur() {
		this.nbPropsExt = 2;
		this.nbTrickExt = 4;
		this.tabTrick = new Trick[4];
	}
	@Override
	/**
	 * Méthode surchargée qui créé un nouveau jeu de carte
	 */
	public void NewJeuDeCarte() {
		PropsType[] rabbit= {PropsType.Rabbit,PropsType.The_other_rabbit};
		PropsType[] carrot= {PropsType.Carrots};
		PropsType[] lettuce= {PropsType.Lettuce};
		PropsType[] other_rabbit= {PropsType.The_other_rabbit};
		PropsType[] hunter= {PropsType.Hunter};
		PropsType[] tomato= {PropsType.Tomato};
		
		this.tabTrick[0]=new Trick(hunter,rabbit,5,"The Good Hunter");
		this.tabTrick[1]=new Trick(hunter,lettuce,3,"The Bad Hunter");
		this.tabTrick[2]=new Trick(tomato,carrot,3,"The Other Vegetable Patch");
		this.tabTrick[3]=new Trick(tomato,other_rabbit,5,"The Red Rabbit");
	}
	
	/**
	 * 
	 */
	public int getNbProps() {
		return this.nbPropsExt;
	}
	
	/**
	 * 
	 */
	public int getNbTrick() {
		return this.nbTrickExt;
	}
	
	/**
	 * 
	 */
	public Trick getTrickExt(int i) {
		return this.tabTrick[i];
	}

}
