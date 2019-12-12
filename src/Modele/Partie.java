package Modele;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Partie est la classe qui s'occupe d'instancier toutes les autres classes du package Modele
 * @author grego
 *
 */
public class Partie extends Observable{
	/**
	 * Le jeu de carte qui est instancier avec les param�tres choisis par le joueur
	 */
	private JeuDeCarte carte;
	/**
	 * Un tableau de joueur
	 */
	private ArrayList<Joueur> joueur;
	/**
	 * Un bool�en qui est true si la partie est en cours
	 */
	private boolean partieEnCours;
	/**
	 * Les r�gles d�finies par le joueur
	 */
	private Regles rule;
	/**
	 * La variante choisie
	 */
	private NewRule var;
	/**
	 * L'extension choisie
	 */
	private Extension varExt;
	/**
	 * Le num�ro de l'extension
	 */
	private int numExt;
	/**
	 * Le tour de jeu
	 */
	private int turn;
	/**
	 * Le num�ro du joueur en train de jouer
	 */
	private int order;
	
	/**
	 * M�thode qui renvoie le nombre de joueur
	 * @return
	 */
	public int nbPlayer() {
		return this.joueur.size();
	}
	/**
	 * Constructeur de Partie
	 *  n extension; nbvar num�ro d'extesion ; n1:regle ;nbP:nb de joueurs; nbT:nb de trick ;nbpp: nb de props
	 * @param n
	 * @param nbvar
	 * @param n1
	 * @param nbP
	 * @param nbT
	 * @param nbpp
	 */
	public Partie(int n, int nbvar,int n1,int nbP,int nbT,int nbpp) {
		this.var=null;
		this.numExt=0;
		joueur=new ArrayList<Joueur>();
		partieEnCours=false;
		
		int regle=n1;
		if(regle==1) {
			this.rule=new Regles();
			this.carte=new JeuDeCarte(10,7);
		} 
		//Si r�gle = 2, on instancie la classe Regles avec son constructeur surcharg� qui prend en param�tre le nb de Joueurs, de Trick et de Props
		else if(regle==2) {
			int nbPlayer=nbP;
			int nbTrick=nbT;
			int nbProps=nbpp;
			this.rule=new Regles(nbPlayer,nbTrick,nbProps);
			this.choisirExtension(n);
			//Si aucune extension n'est choisie, on utilise le constructeur de jeu de carte de base
			if(this.rule.getExtension()==0) {
				this.carte=new JeuDeCarte(nbTrick,nbProps);
			} // Si une extension est choisie, on utilise le constructeur surcharg� qui prend en param�tres l'extension choisie et son num�ro
			else {
				this.carte=new JeuDeCarte(nbTrick,nbProps,varExt, numExt);
			}
			this.choisirVariante(nbvar);
		}
	turn=0;
	this.order=0;
	
	}
	/**
	 * M�thode qui renvoie la liste des joueurs
	 * @return joueur
	 */
   public ArrayList<Joueur> getJoueur() {
		return joueur;
	}
	
   public void ajouterJoueur(Joueur role) {
	   if(this.partieEnCours==false) {
		   this.joueur.add(role);
	   }
   }
   public void retirerJoueur(Joueur role) {
	   if(this.partieEnCours==false) {
		   this.joueur.remove(role);
	   }
   }
   /**
    * M�thode qui permet d'instancier les classes variante selon le choix de l'utilisateur
    * @param nbvar
    */
   public void choisirVariante(int nbvar) {
		int choisir=nbvar;
		if (choisir==1) {
			this.rule.setVariante(1);
			var=new Variante();
		} else if(choisir==2) {
			this.rule.setVariante(2);
			var=new VarianteContre();
		}
		 
	}
   
   /**
    * M�thode qui permet d'instancier les classes Extension selon le choix de l'utilisateur
    * @param n
    */
   public void choisirExtension(int n) {
		int choice=n;
		if (choice==1) {
			this.numExt = 1;
			this.varExt=new ExtensionChasseur();
			this.rule.setExtension(1);
			this.varExt.NewJeuDeCarte();
		}
   }
   
   /**
    * M�thode qui permet d'initialiser la partie selon le nombre de joueur
    * @param nb
    */
   public void Initialiser(int nb) {
	   //nb est nb r�el
	   int nbPlayer=nb;
	   for(int i=0;i<nbPlayer;i++) {
		   this.ajouterJoueur(new JoueurReel());
	   }
	   for(int i=nbPlayer;i<this.rule.getNbJoueur();i++) {
		   this.ajouterJoueur(new JoueurVirtuel());
	   }
	   this.turn=1;
	   this.order=0;
   }
   /**
    * M�thode qui permet de distribuer les cartes
    */
   public void distribuerCarte() {
	   carte.melangerProps();
	   carte.melangerTrick();
	   Iterator<Joueur> it=joueur.iterator();
		while(it.hasNext())
		{   Joueur e=it.next();
			e.setDroite(carte.getPropsPile().remove(0));
			e.setGauche(carte.getPropsPile().remove(0));
		}
		this.setChanged();
		this.notifyObservers("distribuer carte");
		
   }
   
   /**
    * M�thode de type getter qui retourne le jeu de carte
    * @return carte
    */
   public JeuDeCarte getCarte() {
	return carte;
}

   /**
    * M�thode qui change les valeurs de l'ordre de la partie et le num�ro de tour lors du changement de tour
    */
	public void next() {
		
	    if(this.order==this.nbPlayer()) {
	        this.turn++;
	        this.setChanged();
	        this.notifyObservers("new turn");
	      }
		   int max=this.nbPlayer()+1;
		   int n=this.order+1;
		   if(n>3)n=1;
		   this.order=n;
		   this.setChanged();
		   this.notifyObservers("next");
	}
	
	/**
	 * M�thode de type getter qui retourne la Variante
	 * @return var
	 */
	  public NewRule getVar() {
		return var;
	}
	
	  /**
	   * M�thode de type getter qui retourne le tour de jeu
	   * @return turn
	   */
	public int getTurn() {
		return turn;
	}
	/**
	 * M�thode de type setter pour chnager le tour de jeu
	 * @param turn
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	/**
	 * M�thode de type getter qui retourne Les r�gles
	 * @return rule
	 */
	public Regles getRule() {
		return rule;
	}
	
	/**
	 * M�thode de type getter qui renvoit l'ordre de jeu en cours
	 * @return order
	 */
	public int getOrder() {
		return order;
	}
	
	/**
	 * M�thode qui renvoit un bool�en true si la partie est termin�e
	 * @return
	 */
	public boolean estTermine(){
		  if(carte.getKey()==1)
		   return true;
		  if(carte.getNbErreur()>=this.nbPlayer())
		   return true;
		  return false;
	  }
	
	/**
	 * M�thode qui permet le calcul des points
	 */
	  public void calculerPoint() {
		  //the one who fail to play the other hat rabbit -3
		  //the one who have hat or the other rabbit -3
		if(carte.getNbErreur()==3) {
			  Iterator<Joueur> it=joueur.iterator();
			  while(it.hasNext()) {
				  Joueur e=(Joueur) it.next();
				  e.setScore(e.getScore()-3);
				  if(e.gauche.getName()==PropsType.Hat&&e.droite.getName()==PropsType.The_other_rabbit)
					e.setScore(e.getScore()-3);
			  }
		  }
	  }
	  
	  /**
	   * M�thode qui retourne le num�ro du joueur qui vient de gagner la partie
	   * @return int
	   */
	  public int joueurGagnant() {
		  int numeroJoueur=0;
		  
		  int pointMax=joueur.get(0).getScore();
		  for(int i=1;i<joueur.size();i++){
			  if(joueur.get(i).getScore()>pointMax)
				  numeroJoueur=i;
		  }
		  return numeroJoueur+1;
	  }
	  
	  /**
	   * M�thode toString pour l'affichage de la partie
	   */
	  public String toString() {
		  StringBuffer sb=new StringBuffer();
		  for(int i=0;i<joueur.size();i++){
			 sb.append(joueur.get(i).toString());
			 sb.append("\n");
		  }
		  return sb.toString();
		}
}
