package Modele;

/**
 * Regles est la classe qui prend tous les param�tres s�lectionn�s par le joueur
 * @author grego
 *
 */
public class Regles {
	/**
	 * Le num�ro de variante
	 * @see Variante
	 */
	private int variante;
	/**
	 * Le num�ro d'extension
	 * @see Extension
	 */
	private int extension;
	/**
	 * Le nombre de joueur
	 */
	private int nbJoueur;
	/**
	 * Le nombre de Trick
	 * @see Trick
	 */
	private int nbCarteTrick;
	/**
	 * Le nombre de Props
	 * @see Props
	 */
	private int nbCarteProps;
	
	/**
	 * Constructeur de Regles
	 */
	public Regles() {
		this.nbJoueur = 3;
		this.nbCarteTrick = 10;
		this.nbCarteProps = 7;
		this.variante=0;
		this.extension=0;
	}
	
	/**
	 * getter qui retrourne le num�ro de  variante
	 * @return
	 */
    public int getVariante() {
		return variante;
	}
    
    /**
     * setter qui change la variante
     * @param variante
     */
	public void setVariante(int variante) {
		this.variante = variante;
	}
	
	/**
	 * 
	 * getter qui retrourne le num�ro d'extension
	 * @return
	 */
	public int getExtension() {
		return extension;
	}
	
	/**
	 * setter qui change l'extension
	 * @param extension
	 */
	public void setExtension(int extension) {
		this.extension = extension;
	}
	
	/**
	 * Constructeur surcharg� de Regles
	 * @param nbJoueur
	 * @param nbCarteTrick
	 * @param nbCarteProps
	 */
	public Regles(int nbJoueur, int nbCarteTrick, int nbCarteProps) {
		this.nbJoueur = nbJoueur;
		this.nbCarteTrick = nbCarteTrick;
		this.nbCarteProps = nbCarteProps;
		if(this.nbCarteProps<2*this.nbJoueur+1) {this.nbCarteProps=2*this.nbJoueur+1;}
		if(this.nbCarteTrick<10) {this.nbCarteTrick=10;}
		this.variante=0;
		this.extension=0;
	}

	/**
	 * getter qui renvoit le nombre de joueur
	 * @return
	 */
	public int getNbJoueur() {
		return nbJoueur;
	}

	/**
	 * getter qui renvoit le nombre de Trick
	 * @return
	 */
    public int getNbCarteTrick() {
		return nbCarteTrick;
	}
    
    /**
     * getter qui renvoit le nombre de Props
     * @return
     */
	public int getNbCarteProps() {
		return nbCarteProps;
	}
   
	/**
	 * M�thdode qui renvoit un bool�en true si une variante est activ�e
	 * @return
	 */
	public  boolean estVariante(){
	    boolean key=false;
	    if(this.variante!=0)
	    	key=true;
	    return key;
	}
}
