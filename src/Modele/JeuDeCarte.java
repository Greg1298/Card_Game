package Modele;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * JeuDeCarte est la classe qui instancie le jeu de carte qui sera utilisé dans la classe Partie
 * @author grego
 *
 */
public class JeuDeCarte extends Observable{
	
	/**
	 * Le nombre de Trick sélectionné par le joueur
	 * @see Partie
	 */
	private int nbTrick;
	/**
	 * Le nombre de Props sélectionné par le joueur
	 * @see Partie
	 */
	private int nbProps;
	/**
	 * Un tableau trickDeck contenant les cartes Trick du jeu cachées
	 * @see Partie
	 */
	public	LinkedList<Trick> trickDeck=new LinkedList<Trick>();
	/**
	 * Un tableau trickPile contenant les cartes Trick du jeu visibles
	 * @see Partie
	 */
	public LinkedList<Trick> trickPile=new LinkedList<Trick>();
	/**
	 * Un tableau propsPile contenant les cartes Props du jeu
	 * @see Partie
	 */
	public ArrayList<Props> propsPile=new ArrayList<Props>();
	/**
	 * Le nombre d'échec à la tentative du OtherHatTrick
	 * @see Partie
	 */
	private int nbErreur;
	/**
	 * Une key utilisée dans Partie
	 * @see Partie
	 */
	private int key;
	/**
	 * Une extension correspondant à un ajout de nouvelles cartes Props et Trick
	 * @see Partie
	 * @see Extension
	 */
	private Extension extension;
	
	/**
	 * Constructeur de JeuDeCarte si les règles choisies sont celles de base (pas d'extension ni de variante)
	 * @param nbTrick
	 * @param nbProps
	 */
	public JeuDeCarte(int nbTrick,int nbProps) {
		//par defaut 7 carte prop
		this.nbTrick=nbTrick;
		this.nbProps=nbProps;
		//Ajoute chaque Props à la ProsPile
		propsPile.add(new Props(PropsType.Hat));
		propsPile.add(new Props(PropsType.Lettuce));
		propsPile.add(new Props(PropsType.Rabbit));
		propsPile.add(new Props(PropsType.The_other_rabbit));
		propsPile.add(new Props(PropsType.Carrots));
		propsPile.add(new Props(PropsType.Carrots));
		propsPile.add(new Props(PropsType.Carrots));
		//On rajoute des Props aléatoirement jusqu'à ce que le nb de Props défini par le joueur soit atteint
		for(int i=7;i<this.nbProps;i++) {
			 Random rnd=new Random();
			 int n1=Math.abs(rnd.nextInt()%(4));
			 propsPile.add(new Props(PropsType.values()[n1]));
		}
		PropsType[] rabbit= {PropsType.Rabbit,PropsType.The_other_rabbit};
		PropsType[] vegetable= {PropsType.Lettuce,PropsType.Carrots};
		PropsType[] carrot= {PropsType.Carrots};
		PropsType[] lettuce= {PropsType.Lettuce};
		PropsType[] hat= {PropsType.Hat};
		PropsType[] the_rabbit= {PropsType.Rabbit};
		PropsType[] other_rabbit= {PropsType.The_other_rabbit};
		//On créé un tableau de Trick à l'aide des PropsType définis précédement
		Trick[] tab=new Trick[9];
		tab[0]=new Trick(hat,the_rabbit,5,"The Hat Trick");
		tab[1]=new Trick(carrot,lettuce,3,"The Vegetable Patch");
		tab[2]=new Trick(rabbit,vegetable,1,"The Hungry Rabbit");
		tab[3]=new Trick(carrot,carrot,2,"The Bunch of Carrots");
	    tab[4]=new Trick(the_rabbit,other_rabbit,5,"The Pair of Rabbits");
	    tab[5]=new Trick(rabbit,lettuce,4,"The Rabbit That Didn't Like Carrots");
	    tab[6]=new Trick(hat,vegetable,2,"The Vegetable Hat Trick");
	    tab[7]=new Trick(hat,carrot,3,"The Carrot Hat Trick");
	    tab[8]=new Trick(hat,rabbit,4,"The Sligther Easier Hat Trick");
	    //On ajoute ces Trick dans le TrickDeck
	    for(int i=0;i<9;i++) {
	    	this.trickDeck.add(tab[i]);
	    }
		//On rajoute des Trick jusqu'à atteindre le nombre défini par le joueur
	    for(int i=9;i<this.nbTrick-1;i++) {
	    	Random rnd=new Random();
			int n1=Math.abs(rnd.nextInt()%(8));
			this.trickDeck.add(tab[n1]);
	    }
	    
		this.nbErreur=0;this.key=0;
		
	}
	
	/**
	 * Constructeur de JeuDeCarte si les règles ont été modifiées (différents nb de Tricks, de Props et/ou une extension)
	 * @param nbTrick
	 * 					qui correspond au nombre de cartes Tricks sélectionnées par le joueur
	 * @param nbProps
	 * 					qui correspond au nombre de cartes Props sélectionnées par le joueur
	 * @param ext 
	 * 					qui correspond à l'extension sélectionnée par le joueur
	 * @param numExt 
	 * 					qui correspond au numéro de l'extension sélectionnée par le joueur
	 */
	public JeuDeCarte(int nbTrick,int nbProps,Extension ext,int numExt ) {
		//par defaut 7 carte prop
		this.nbTrick=nbTrick;
		this.nbProps=nbProps;
		this.extension=ext;
		//Ajoute chaque Props à la ProsPile
		for(PropsType it:PropsType.values())
		{
			this.propsPile.add(new Props(it));
		}
		propsPile.add(new Props(PropsType.Carrots));
		propsPile.add(new Props(PropsType.Carrots));
		//Ajoute les Props liés aux extensions
		for(int i=7+this.extension.getNbProps();i<this.nbProps;i++) {
			 Random rnd=new Random();
			 int n1=Math.abs(rnd.nextInt()%(4+this.extension.getNbProps()));
			 propsPile.add(new Props(PropsType.values()[n1]));
		}
		PropsType[] rabbit= {PropsType.Rabbit,PropsType.The_other_rabbit};
		PropsType[] vegetable= {PropsType.Lettuce,PropsType.Carrots};
		PropsType[] carrot= {PropsType.Carrots};
		PropsType[] lettuce= {PropsType.Lettuce};
		PropsType[] hat= {PropsType.Hat};
		PropsType[] the_rabbit= {PropsType.Rabbit};
		PropsType[] other_rabbit= {PropsType.The_other_rabbit};
		PropsType[] hunter= {PropsType.Hunter};
		PropsType[] tomato= {PropsType.Tomato};
		int nb=9+this.extension.getNbTrick();
		//On créé un tableau de Trick à l'aide des PropsType définis précédement
		Trick[] tab=new Trick[nbTrick];
		tab[0]=new Trick(hat,the_rabbit,5,"The Hat Trick");
		tab[1]=new Trick(carrot,lettuce,3,"The Vegetable Patch");
		tab[2]=new Trick(rabbit,vegetable,1,"The Hungry Rabbit");
		tab[3]=new Trick(carrot,carrot,2,"The Bunch of Carrots");
	    tab[4]=new Trick(the_rabbit,other_rabbit,5,"The Pair of Rabbits");
	    tab[5]=new Trick(rabbit,lettuce,4,"The Rabbit That Didn't Like Carrots");
	    tab[6]=new Trick(hat,vegetable,2,"The Vegetable Hat Trick");
	    tab[7]=new Trick(hat,carrot,3,"The Carrot Hat Trick");
	    tab[8]=new Trick(hat,rabbit,4,"The Sligther Easier Hat Trick");
		
	    //Instancie les Trick liés aux extensions
	    for(int i=9;i<nb;i++) {
	    	int j=0;
	    	tab[i] = this.extension.getTrickExt(j);
	    	j++;
	    }
	    
	    //Ajoute ces Trick au TrickDeck
	    for(int i=0;i<nb;i++) {
	    	this.trickDeck.add(tab[i]);
	    }
		//Ajoute des Trick aléatoirement jusqu'à atteidre le nombre défini par le joueur
	    for(int i=nb;i<this.nbTrick-1;i++) {
	    	Random rnd=new Random();
			int n1=Math.abs(rnd.nextInt()%(8+this.extension.getNbTrick()));
			this.trickDeck.add(tab[n1]);
	    }
	    
		this.nbErreur=0;this.key=0;
		
	}
	
	/**
	 * Méthode de type Getter qui renvoit la key 
	 * @return la key
	 */
	public int getKey() {
		return key;
	}
	
	/**
	 * Méthode de setter de la key
	 * @param key
	 */
	public void setKey(int key) {
		this.key = key;
	}
	/**
	 * Méthode qui permet de mélanger le jeu de carte lors de l'initialisation
	 */
	public void melangerTrick() {   
	   for(int i=0;i<this.trickDeck.size();i++) {
		   Random rnd=new Random();
		   int n1=Math.abs(rnd.nextInt()%(this.nbTrick-2));
		   Trick trickInsert=this.trickDeck.get(n1);
		   this.trickDeck.remove(n1);
		   this.trickDeck.add(trickInsert);
	   }
		PropsType[] other_rabbit= {PropsType.The_other_rabbit};PropsType[] hat= {PropsType.Hat};
		this.trickDeck.add(new TheOtherHatTrick(hat,other_rabbit,6,"The Other Hat Trick",this));
		Trick e=this.trickDeck.removeFirst();
		this.trickPile.add(e);
		e.setFace_up(true);
	}
	
	/**
	 * Méthode getter du nombre de tentatives échouées du OtherHatTrick
	 * @return le nombre de tentative échouées du OtherHatTrick
	 */
	public int getNbErreur() {
		return nbErreur;
	}
	
	/**
	 * Méthode Setter du nombre de tentatives échouées du OtherHatTrick
	 * @param nbErreur
	 */
	public void setNbErreur(int nbErreur) {
		this.nbErreur = nbErreur;
	}
	
	/**
	 * Méthode qui permet de mélanger les Props lors de l'initialisation
	 */
	public void melangerProps()
	{
		for(int i=0;i<this.propsPile.size();i++) {
		    Random rnd=new Random();
		   
		    
		    int n1=Math.abs(rnd.nextInt()%(this.propsPile.size()-1));
	
		    Props propInsert=this.propsPile.get(n1);
		    this.propsPile.remove(n1);
		    this.propsPile.add(propInsert);
		}
	}
	
	/**
	 * Méthode qui permet de retourner le Trick en haut de la TrickPile
	 * @return
	 */
	public Trick getTop(){
		Trick e=this.trickPile.getLast();
	    return e;	
	}
	
	/**
	 * Méthode qui retourne un booléen true si le TrickDeck est vide
	 * @return
	 */
	public boolean deckEmpty() {
		return this.trickDeck.isEmpty();
	}
	
	/**
	 * Méthode qui retourne le Trick en haut du TrickDeck
	 * @return
	 */
	public Trick getSecond()
	{   if(!this.trickDeck.isEmpty()) {
		Trick e=this.trickDeck.removeFirst();
	    this.trickPile.add(e);
	    this.setChanged();
	    this.notifyObservers("getSecond");
	    return e;	
	    }
	  else return null; 
	}
	
	/**
	 * Méthode qui permet d'obtenir le premier Trick en haut du TrickDeck sans la modifier (pour l'affichage des icônes lors de l'initialisation)
	 * @return
	 */
	public Trick getJustSecond() {
		Trick e = this.trickDeck.getFirst();
		return e;
	}
	
	/**
	 * Méthode qui permet d'ajouter un Trick au TrickPile
	 */
	public void ajouterTrick() {
		if(this.trickPile.isEmpty())
		this.getSecond();
	}
	
	/**
	 * Méthode qui permet de retirer un Trick au TrickPile
	 * @return
	 */
	public Trick retirerTrick() {
		return this.trickPile.removeLast();
	}
	
	/**
	 * Méthode qui permet d'obtenir la PropsPile
	 * @return
	 */
	public ArrayList<Props> getPropsPile() {
		return propsPile;
	}
	
}