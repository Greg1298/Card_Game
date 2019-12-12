package Vue;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

import Controleur.Controleur;
import Modele.Joueur;
import Modele.JoueurReel;
import Modele.JoueurVirtuel;
import Modele.Partie;
import Modele.Props;
import Modele.Trick;
import Modele.Observable;
import Modele.Observer;

/**
 * VueTexte est l'interface concurrente à MonInterface qui implémente Observer
 * @author grego
 *
 */
public class VueTexte implements Observer, Runnable {

	/**
	 * String pour quitter
	 */
    public static String QUITTER = "Quit";
    /**
	 * String pour initialiser
	 */
    public static String INITIALISER = "I";
    /**
	 * String pour changer de tour
	 */
    public static String NEXT = "N";
    /**
	 * String pour la variante
	 */
    public static String VARIANTE = "V";
    /**
	 * String pour le choix de du trick
	 */
    public static String CHOISIR = "C";
    /**
	 * String pour échanger un Props
	 */
    public static String ECHANGER = "E";
    /**
	 * String pour performer un Trick
	 */
    public static String PERFORM = "P";
    /**
	 * String pour Prompt
	 */
    public static String PROMPT = ">";

    /**
	 * Partie en cours
	 */
    private Partie game;

    /**
	 * Constructeur de la vueTexte
	 * @param p
	 * 					La partie en cours
	 */
    public VueTexte(Partie p) {
    	
		
		this.game=p;
		
		this.game.getCarte().addObserver(this);
		this.game.getJoueur().get(0).addObserver(this);
		this.game.getJoueur().get(1).addObserver(this);
		this.game.getJoueur().get(2).addObserver(this);
		this.game.addObserver(this);
		
	Thread t = new Thread(this);
	t.start();
    }

    /**
	 * Méthode pour lancer un Thread
	 */
    public void run() {

	String saisie = null;
	boolean quitter = false;

	System.out.println("Taper " +VueTexte.INITIALISER + " pour initailiser le jeu ; " + VueTexte.NEXT + " pour passer finir son tour ; " + VueTexte.VARIANTE +
			" pour jouer la variante ; " + VueTexte.CHOISIR + " pour choisir le trick ; " + VueTexte.ECHANGER + "\n" 
			+ " pour échanger une carte; " + VueTexte.PERFORM + " pour perform ; "+ VueTexte.QUITTER + " pour quitter.");

	do {
	    saisie = this.lireChaine();

	    if (saisie != null) {
		if (saisie.equals(VueTexte.VARIANTE) == true) {

    		if(this.game.getRule().getVariante()==0) {
    			System.out.println("Le jeu n'a pas de variante.");
    		}
    		if(this.game.getRule().getVariante()==1) {
    			int[] choix=new int[5];
    			System.out.println("Voulez-vous essayer de deviner les cartes de tous les joueurs?");
    			System.out.println("Entrer 1 pour oui et 2 pour non.");
    			Scanner in=new Scanner(System.in);
    			int choice = in.nextInt();
    			if(choice==1) {
    				choix[0]=1;
    				int player=this.game.getOrder();
    				int min=player-1;
    				int max=player+1;
    				if(min<1)min=3;
    				if(max>3)max=1;
    				if(min>max) {
    					int swap=min;
    					min=max;
    					max=swap;
    				}
    				System.out.println("Quel type de carte pensez-vous que le joueur "+ min +" possède à sa gauche ?");
    				System.out.println("Entrer 1 pour Rabbit.");
    				System.out.println("Entrer 2 pour The_other_rabbit.");
    				System.out.println("Entrer 3 pour Hat.");
    				System.out.println("Entrer 4 pour Carrots.");
    				System.out.println("Entrer 5 pour Lettuce.");
    				Scanner sc=new Scanner(System.in);
        			choix[1] = sc.nextInt();
        			
    				System.out.println("Quel type de carte pensez-vous que le joueur "+min+" possède à sa droite ?");
    				System.out.println("Entrer 1 pour Rabbit.");
    				System.out.println("Entrer 2 pour The_other_rabbit.");
    				System.out.println("Entrer 3 pour Hat.");
    				System.out.println("Entrer 4 pour Carrots.");
    				System.out.println("Entrer 5 pour Lettuce.");
    				Scanner sc2=new Scanner(System.in);
    				choix[2] = sc2.nextInt();
        			
        			System.out.println("Quel type de carte pensez-vous que le joueur "+max+" possède à sa gauche ?");
        			System.out.println("Entrer 1 pour Rabbit.");
    				System.out.println("Entrer 2 pour The_other_rabbit.");
    				System.out.println("Entrer 3 pour Hat.");
    				System.out.println("Entrer 4 pour Carrots.");
    				System.out.println("Entrer 5 pour Lettuce.");
    				Scanner sc3=new Scanner(System.in);
    				choix[3] = sc3.nextInt();
        			
        			System.out.println("Quel type de carte pensez-vous que le joueur "+max+" possède à sa droite?");
        			System.out.println("Entrer 1 pour Rabbit.");
    				System.out.println("Entrer 2 pour The_other_rabbit.");
    				System.out.println("Entrer 3 pour Hat.");
    				System.out.println("Entrer 4 pour Carrots.");
    				System.out.println("Entrer 5 pour Lettuce.");
    				Scanner sc4=new Scanner(System.in);
    				choix[4] = sc4.nextInt();
        			
    				this.game.getVar().NewJouer(this.game,player,choix,0);
    				if(this.game.getVar().isFlag()) {
    					System.out.println("You succeed !");
    					this.game.getJoueur().get(player).setChanged();
        				this.game.getJoueur().get(player).notifyObservers("success");
    				}else {
    					System.out.println("You failed !");
    					this.game.getJoueur().get(player).setChanged();
    					this.game.getJoueur().get(player).notifyObservers("fail");
    				}
    				
    			}else {
    				System.out.println("Thank you for your choice !");
    			}
    		}
    		if(this.game.getRule().getVariante()==2) {
    			int[] choix=new int[3];
    			int player=this.game.getOrder();
				int min=player-1;
				int max=player+1;
				if(min<1)min=3;
				if(max>3)max=1;
				if(min>max) {
					int swap=min;
					min=max;
					max=swap;
				}
				System.out.println("Avec quel joueur voulez-vous deviner une carte ?");
				System.out.println("Entrer 1 pour le joueur " + min);
				System.out.println("Entrer 2 pour le joueur " + max);
				Scanner scan=new Scanner(System.in);
    			int playerChoice = scan.nextInt();
    			if(playerChoice==1)choix[0]=min-1;
    			if(playerChoice==2)choix[0]=max-1;
    			System.out.println("Quel type de carte pensez-vous que le joueur "+choix[0]+1+" possède ?");
				System.out.println("Entrer 1 pour Rabbit.");
				System.out.println("Entrer 2 pour The_other_rabbit.");
				System.out.println("Entrer 3 pour Hat.");
				System.out.println("Entrer 4 pour Carrots.");
				System.out.println("Entrer 5 pour Lettuce.");
				Scanner scan1=new Scanner(System.in);
    			choix[1] = scan1.nextInt();
    			
				System.out.println("Quel type de carte pensez-vous que le joueur "+choix[0]+1+" possède à sa droite ?");
				System.out.println("Entrer 1 pour Rabbit.");
				System.out.println("Entrer 2 pour The_other_rabbit.");
				System.out.println("Entrer 3 pour Hat.");
				System.out.println("Entrer 4 pour Carrots.");
				System.out.println("Entrer 5 pour Lettuce.");
				Scanner scan2=new Scanner(System.in);
				choix[2] = scan2.nextInt();
				
				System.out.println("if you fail,you want to set which card face up?");
				System.out.println("Enter 1 for left and 2 for right.");
				Scanner scan3=new Scanner(System.in);
				int nb = scan3.nextInt();
				this.game.getVar().NewJouer(this.game,player,choix,nb);
				if(this.game.getVar().isFlag()) {
					System.out.println("You succeed !");
				}else {
					System.out.println("You failed !");
				}
    		}
    	
		} else if (saisie.equals(VueTexte.INITIALISER) == true) {
			this.game.distribuerCarte();
			//ordre 0 au debut
		} else if (saisie.equals(VueTexte.CHOISIR) == true) {
    		int index=this.game.getOrder()-1;//***
    		Joueur e1=this.game.getJoueur().get(index);
    		String cardLeft=e1.getGauche().getName().toString();
			String cardRight=e1.getDroite().getName().toString();
			System.out.println("Le joueur " + e1.getOrdre() + " possède le " + cardLeft + " à sa gauche et le " + cardRight + " à sa droite.");
    		System.out.println("Voulez-vous choisir le trick du haut de la pile?");
    		System.out.println("Entrer 1 pour oui et 2 pour non.");
    		Scanner sc0=new Scanner(System.in);
			int choice = sc0.nextInt();
    		if(choice==1) {
    			e1.choisirUnTrick(this.game.getCarte(), 2);
    		}else {
    			e1.choisirUnTrick(this.game.getCarte(), 1);
    		}
    	} else if (saisie.equals(VueTexte.NEXT) == true) {
			this.game.next();
			
			int index=this.game.getOrder()-1;
    		Joueur e=this.game.getJoueur().get(index);
    		if(!this.game.estTermine()) {
    		if(e instanceof JoueurVirtuel) {
    			int[] e1= {1};
    			//step1
    			e.choisirUnTrick(this.game.getCarte(), 0);
    			//step2
    			//Connaître ses cartes
    			String cardLeft=e.getGauche().getName().toString();
    			String cardRight=e.getDroite().getName().toString();
    			int number = e.getOrdre();
    			System.out.println("Le joueur " + number + " possède le " + cardLeft + " à sa gauche et le " + cardRight + " à sa droite.");
    			//echanger props
    			e.echangerProps(this.game.getJoueur(),0,0,0);
    			//Montrer ses props
    			String cardLeft1=e.getGauche().getName().toString();
    			String cardRight1=e.getDroite().getName().toString();
    			System.out.println("Le joueur " + number + " possède le " + cardLeft1 + " à sa gauche et le " + cardRight1 + " à sa droite.");
    			e.performTrick(this.game.getCarte(), 0);
    			if(this.game.estTermine()) {
    				System.out.println("Le jeu est terminé !");
    				this.game.calculerPoint();
    				System.out.println("Bravo ! \n Le joueur " + this.game.joueurGagnant() + "a gagné");
    			}
    		}
    	}
			
		} else if (saisie.equals(VueTexte.ECHANGER) == true) {
			
	    		int varType=this.game.getRule().getVariante();
	    		if(varType==2&&(!this.game.getVar().isFlag())){
	    			System.out.println("Vous ne pouvez pas échanger de carte ce tour.");
	    		}
	    		int player=this.game.getOrder();
				int min=player-1;
				int max=player+1;
				if(min<1)min=3;
				if(max>3)max=1;
				if(min>max) {
					int swap=min;
					min=max;
					max=swap;
				}
				int index=this.game.getOrder()-1;
	    		Joueur e1=this.game.getJoueur().get(index);
				String cardLeft=e1.getGauche().getName().toString();
				String cardRight=e1.getDroite().getName().toString();
				System.out.println("Le joueur " + e1.getOrdre() + " possède le " + cardLeft + " à sa gauche et le " + cardRight + " à sa droite.");
				System.out.println("Avec quel joueur voulez-vous ¨échanger une carte ?");
				System.out.println("Entrer 1 pour le joueur " + min);
				System.out.println("Entrer 2 pour le joueur " + max);
				Scanner scanC=new Scanner(System.in);
    			int playerChoice = scanC.nextInt();
    			int choix=min-1;
    			if(playerChoice==1)choix=min-1;
    			if(playerChoice==2)choix=max-1;
				
    			System.out.println("Laquelle de vos cartes voulez-vous échanger ?");
    			System.out.println("Entrer 1 pour la gauche et 2 pour la droite.");
    			Scanner scEx=new Scanner(System.in);
    			int nbMy = scEx.nextInt();
    			
    			System.out.println("Laquelle de ses cartes voulez-vous échanger ?");
    			System.out.println("Entrer 1 pour la gauche et 2 pour la droite.");
    			Scanner scEx1=new Scanner(System.in);
    			int nbOther = scEx1.nextInt();
				
				this.game.getJoueur().get(player).echangerProps(this.game.getJoueur(), choix, nbMy, nbOther);
				String cardLeft1=e1.getGauche().getName().toString();
				String cardRight1=e1.getDroite().getName().toString();
				System.out.println("Le joueur " + e1.getOrdre() + "possède le " + cardLeft + " à sa gauche et le " + cardRight + " à sa droite.");
	    	
	    } else if (saisie.equals(VueTexte.PERFORM) == true) {
	    	System.out.println("Si vous perdez, quelle carte voulez-vous révéler ?");
			System.out.println("Entrer 1 pour la gauche et 2 pour la droite.");
			Scanner scPe=new Scanner(System.in);
			int nb = scPe.nextInt();
			int index=this.game.getOrder()-1;
			this.game.getJoueur().get(index).performTrick(this.game.getCarte(), nb);
			if(this.game.estTermine()) {
				System.out.println("Le jeu est terminé.");
				this.game.calculerPoint();
				System.out.println("Bravo! \n le joueur " + this.game.joueurGagnant() + " a gagne");
			}
    	}  else if (saisie.equals(VueTexte.QUITTER) == true) {
		    quitter = true;	
		} else {
		    System.out.println("Commande non reconnue...");
		}		
	    }
	} while (quitter == false);
	System.exit(0);
    }

    /**
	 * Méthode qui permet de lire les caractères rentrer par le joueur
	 */
    private String lireChaine() {
	BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
	String resultat = null;
	try {
	    System.out.print(VueTexte.PROMPT);
	    resultat = br.readLine();
	} catch (IOException e) {
	    System.err.println(e.getMessage());
	}
	return resultat;	
    }

    @Override
    /**
	 * Méthode qui va être appelée par les méthodes des classes du Package Modèle afin de mettre à jour l'interface graphique
	 */
    public void update(Observable arg, Object o) {
    	if(arg instanceof Joueur) {
			if(o=="success") {
				int player1 = this.game.getOrder();
				int index=this.game.getOrder()-1;
				System.out.println("Bravo ! Le joueur " + player1 + " a réussit son Trick !");
				System.out.println("Le joueur " + player1 + " a maintenant " +this.game.getJoueur().get(index).getScore() );
				
			}
			if(o=="fail") {
				int player1 = this.game.getOrder();
				System.out.println("Raté ! Le joueur " + player1 + " n'a pas réussit son Trick !");
			}
		}
    }



}
