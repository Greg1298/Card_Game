package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

import Modele.Joueur;
import Modele.JoueurReel;
import Modele.JoueurVirtuel;
import Modele.Partie;
import Vue.MonInterface;
import Modele.Variante;
import Vue.VueTexte;
import Vue.frameInit;

/**
 * Controleur va permettre d'instancier les 2 contrôleurs que l'on utilise dans notre modèle MVC :
 * celui de l'interface principale et celui de la sélection des parmaètres
 * @author grego
 *
 */
public class Controleur {
		
	/**
	 * Le bouton qui permet de distribuer les cartes
	 * @see Partie
	 * @see JButton
	 */
	private JButton jb;
	/**
	 * Le frame de l'interface graphique principale
	 * @see MonInterface
	 * @see JFrame
	 */
	private JFrame jf;
	/**
	 * La partie avec laquelle on joue
	 * @see Partie
	 */
	private Partie p;
	/**
	 * Le bouton qui permet changer de tour
	 * @see MonInterface
	 * @see JButton
	 */
	private JButton jb2;
	/**
	 * Le bouton qui permet de lancer une variante
	 * @see Variante
	 * @see JButton
	 */
	private JButton variante;
	/**
	 * Le bouton qui permet de choisir le trick
	 * @see Trick
	 * @see JButton
	 */
	private JButton choisir;
	private JLabel note;
	/**
	 * Le bouton qui permet d'échanger des Props
	 * @see Props
	 * @see JButton
	 */
	private JButton swap;
	/**
	 * Le bouton qui permet de perforer un Trick
	 * @see Trick
	 * @see JButton
	 */
	private JButton perform;
	/**
	 * Le bouton qui permet de lancer la partie après avoir choisi les paramètres
	 * @see frameInit
	 * @see JButton
	 */
	private JButton jbinit1;
	
	/**
	 * Constructeur du controleur de MonInterface (l'interface de jeu principale)
	 * Ecoute les actions sur les boutons Initialiser, Next, Choisir Variante, Choisir Trick, Echanger Props et Performer Trick
	 * @param button
	 * @param nextTurn
	 * @param variante
	 * @param choisir
	 * @param swap
	 * @param perform
	 * @param notice
	 * @param jf
	 * @param partie
	 */
	public Controleur(JButton button,JButton nextTurn,JButton variante ,JButton choisir,JButton swap,JButton perform,JLabel notice,JFrame jf,Partie partie) {
		this.jb = button;
		this.jf = jf;
		this.p = partie;
		this.jb2=nextTurn;
		this.note=notice;
		this.variante=variante;
		this.choisir=choisir;
		this.swap=swap;
		this.perform=perform;
		
		//Méthode qui écoute le bouton Initialiser de l'interface MonInterface
		jb.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		p.distribuerCarte();
	    		
	    	}
	    });
		//Méthode qui écoute le bouton Next de l'interface MonInterface qui passe le tour
		jb2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		p.next();
	    		try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	    		int index=p.getOrder()-1;
	    		Joueur e=p.getJoueur().get(index);
	    		if(!p.estTermine()) {
	    		//Si le prochain joueur est un joueur virtuel, on effectue ses actions (choisir un Trick, échanger un Props et Performer le Trick 
	    		if(e instanceof JoueurVirtuel) {
	    			//step 1 : Chosir un Trick
	    			e.choisirUnTrick(p.getCarte(), 0);
	    			//step 2 :Afficher les Props
	    			String cardLeft=e.getGauche().getName().toString();
	    			String cardRight=e.getDroite().getName().toString();
	    			String msg="<html><h3>player "+e.getOrdre()+"</h3>left "+cardLeft+"<br />"+"right "+cardRight+"<html>";
	    			JOptionPane.showMessageDialog(jf, msg, "prop card",JOptionPane.INFORMATION_MESSAGE);
	    			//step 3 : échanger les props
	    			e.echangerProps(p.getJoueur(),0,0,0);
	    			//step 4 : Afficher les nouveaux Props
	    			String cardLeft1=e.getGauche().getName().toString();
	    			String cardRight1=e.getDroite().getName().toString();
	    			String msg1="<html><h3>player "+e.getOrdre()+"</h3>left "+cardLeft1+"<br />"+"right "+cardRight1+"<html>";
	    			JOptionPane.showMessageDialog(jf, msg1, "prop card",JOptionPane.INFORMATION_MESSAGE);
	    			//step 5 : Performer le Trick 
	    			e.performTrick(p.getCarte(), 0);
	    			//Si la partie est terminée, on calcule les point et affiche le vainqueur
	    			if(p.estTermine()) {
	    				String msg4="the jeux est termine";
	    				JOptionPane.showMessageDialog(jf, msg4);
	    				p.calculerPoint();
	    				String msg2="congratulation!\n+player "+p.joueurGagnant()+" a gagne";
	    				JOptionPane.showMessageDialog(jf, msg2);
	    			}
	    			
	    		}
	    		//Si le prochain joueur est un joueur réel, on l'affiche dans la notice
	    		if(e instanceof JoueurReel) {
	    			String notices="<html><h3 style='text-align:center;color:blue;'>notice:</h3> A vous de jouer !</html>";
	    			note.setText(notices);
	    		}
	    	}
	    	}
	    });
		//Méthode qui écoute le bouton Choisir Variante de l'interface MonInterface
		variante.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		//Si la Partie n'a pas de variante, on l'affiche 
	    		if(p.getRule().getVariante()==0) {
	    			String msg="the game doesn't add variant";
	    			JOptionPane.showMessageDialog(jf, msg,"warning",JOptionPane.WARNING_MESSAGE);
	    		}
	    		//Si la variante The Risky bet est choisie
	    		if(p.getRule().getVariante()==1) {
	    			int[] choix=new int[5];
	    			String msg="Do you want to guess all the players'cards?";
	    			int result=JOptionPane.showConfirmDialog(jf, "Do you want to guess all the cards?");
	    			if(result==0) {
	    				choix[0]=1;
	    				int player=p.getOrder();
	    				int min=player-1;
	    				int max=player+1;
	    				if(min<1)min=3;
	    				if(max>3)max=1;
	    				if(min>max) {
	    					int swap=min;
	    					min=max;
	    					max=swap;
	    				}
	    				String option1="Quel type de carte pensez-vous que le joueur "+min+" poss¨¨de ?";
	    				String option2="Quel type de carte pensez-vous que le joueur "+max+" poss¨¨de ?";
	    				Object[] options=new Object[]{"Rabbit","The_other_rabbit","Hat","Carrots","Lettuce"};
	    				choix[1]=JOptionPane.showOptionDialog(jf, option1,"Left card", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
	    				choix[2]=JOptionPane.showOptionDialog(jf, option1,"Right card", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
	    				choix[3]=JOptionPane.showOptionDialog(jf, option2,"Left card", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
	    				choix[4]=JOptionPane.showOptionDialog(jf, option2,"Right card", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
	    				p.getVar().NewJouer(p,player,choix,0);
	    				if(p.getVar().isFlag()) {
	    					JOptionPane.showMessageDialog(jf, "success","result",JOptionPane.INFORMATION_MESSAGE);
	    					p.getJoueur().get(player-1).setChanged();
	        				p.getJoueur().get(player-1).notifyObservers("success variante");
	    				}else {
	    					JOptionPane.showMessageDialog(jf, "fail","result",JOptionPane.INFORMATION_MESSAGE);
	    					p.getJoueur().get(player-1).setChanged();
	        				p.getJoueur().get(player-1).notifyObservers("fail");
	    				}
	    				
	    			}else {
	    				String msg1="Thank you for your choice.";
	        			JOptionPane.showMessageDialog(jf, msg1,"Option1",JOptionPane.INFORMATION_MESSAGE);
	    			}
	    		}
	    		//Si la variante Counter est choisie
	    		if(p.getRule().getVariante()==2) {
	    			int[] choix=new int[3];
	    			int player=p.getOrder();
	    			//On affecte à min et max l'ordre des 2 autres joueurs.
					int min=player-1;
					int max=player+1;
					if(min<1)min=3;
					if(max>3)max=1;
					if(min>max) {
						int swap=min;
						min=max;
						max=swap;
					}
					String q1="Avec quel joueur voulez-vous devenir  une carte ?";
					Object[] options=new Object[] {min,max};
					int result=JOptionPane.showOptionDialog(jf, q1,"Variant Contre", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
	    			if(result==0)choix[0]=min-1;
	    			if(result==1)choix[0]=max-1;
	    			String q2="Quel type de carte pensez-vous que le joueur "+choix[0]+1+" poss¨¨de ?(left card)";
					String q3="Quel type de carte pensez-vous que le joueur "+choix[0]+1+" poss¨¨de ?(right card)";
					Object[] options2=new Object[]{"Rabbit","The_other_rabbit","Hat","Carrots","Lettuce"};
					choix[1]=JOptionPane.showOptionDialog(jf, q2,"Left card", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE,null,options2,options2[0])+1;
					choix[2]=JOptionPane.showOptionDialog(jf, q3,"Right card", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE,null,options2,options2[0])+1;
					System.out.println(choix[1]);
					String q4="if you fail,you want to set which card face up?";
					Object[] options3=new Object[] {"right","left"};
					int nb=JOptionPane.showOptionDialog(jf, q4,"face up", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE,null,options3,options3[0])+1;
					p.getVar().NewJouer(p,player-1,choix,nb);
					if(p.getVar().isFlag()) {
						JOptionPane.showMessageDialog(jf, "success","result",JOptionPane.INFORMATION_MESSAGE);
						p.getJoueur().get(player-1).setChanged();
	    				p.getJoueur().get(player-1).notifyObservers("success variante");
					}else {
						JOptionPane.showMessageDialog(jf, "fail","result",JOptionPane.INFORMATION_MESSAGE);
						p.getJoueur().get(player-1).setChanged();
	    				p.getJoueur().get(player-1).notifyObservers("fail");
					}
	    		}
	    	}
	    });
		
		//Méthode qui écoute le bouton Choisir Trick de l'interface MonInterface
		this.choisir.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int index=p.getOrder()-1;
	    		Joueur e1=p.getJoueur().get(index);
	    		String cardLeft=e1.getGauche().getName().toString();
				String cardRight=e1.getDroite().getName().toString();
				String msg="<html><h3>player "+e1.getOrdre()+"</h3>left "+cardLeft+"<br />"+"right "+cardRight+"<html>";
				JOptionPane.showMessageDialog(jf, msg, "prop card",JOptionPane.INFORMATION_MESSAGE);
	    		String q1="Voulez-vous choisir le trick du haut de la pile?";
	    		int res=JOptionPane.showConfirmDialog(jf, q1);
	    		if(res==1) {
	    			e1.choisirUnTrick(p.getCarte(), 2);
	    		}else {
	    			e1.choisirUnTrick(p.getCarte(), 1);
	    		}
	    	}
	    });
		//Méthode qui écoute le bouton Echanger de l'interface MonInterface
		this.swap.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int varType=p.getRule().getVariante();
	    		//Dans le cas où le joueur a perdu sa variante Counter, il ne peut plus échanger de cartes Props
	    		if(varType==2&&(!p.getVar().isFlag())){
	    			JOptionPane.showMessageDialog(jf, "you can not swap the card this turn");
	    		}
	    		int player=p.getOrder();
				int min=player-1;
				int max=player+1;
				if(min<1)min=3;
				if(max>3)max=1;
				if(min>max) {
					int swap=min;
					min=max;
					max=swap;
				}
				int index=p.getOrder()-1;
	    		Joueur e1=p.getJoueur().get(index);
				String cardLeft=e1.getGauche().getName().toString();
				String cardRight=e1.getDroite().getName().toString();
				//On affiche les cartes du joueur pour qu'il choisisse en conséquence.
				String msg="<html><h3>player "+e1.getOrdre()+"</h3>left "+cardLeft+"<br />"+"right "+cardRight+"<html>";
				JOptionPane.showMessageDialog(jf, msg, "prop card",JOptionPane.INFORMATION_MESSAGE);
				String q1="Avec quel joueur voulez-vous ¨échanger une carte ?";
				Object[] options=new Object[] {min,max};
				
				int choix=min-1;
				int result=JOptionPane.showOptionDialog(jf, q1,"swap", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE,null,options,options[0]);
				if(result==0)choix=min-1;
				if(result==1)choix=max-1;
				System.out.println("result="+result);
				String q4="for your card, you want to swap which one?";
				Object[] options3=new Object[] {"left","right"};
				int nbMy=JOptionPane.showOptionDialog(jf, q4,"my card", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE,null,options3,options3[0])+1;
				String q5="for other card, you want to swap which one?";
				int nbOther=JOptionPane.showOptionDialog(jf, q5,"other card", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE,null,options3,options3[0])+1;
				
				//On appelle la méthode échanger Props de la classe JoueurReel
				p.getJoueur().get(player-1).echangerProps(p.getJoueur(), choix, nbMy, nbOther);
				String cardLeft1=e1.getGauche().getName().toString();
				String cardRight1=e1.getDroite().getName().toString();
				String msg1="<html><h3>player "+e1.getOrdre()+"</h3>left "+cardLeft1+"<br />"+"right "+cardRight1+"<html>";
				JOptionPane.showMessageDialog(jf, msg1, "prop card",JOptionPane.INFORMATION_MESSAGE);
	    	}
	    });
		//Méthode qui écoute le bouton Performer de l'interface MonInterface
		this.perform.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		String q4="if you fail,you want to set which card face up?";
				Object[] options3=new Object[] {"right","left"};
				int nb=JOptionPane.showOptionDialog(jf, q4,"face up", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE,null,options3,options3[0])+1;
				int index=p.getOrder()-1;
				p.getJoueur().get(index).performTrick(p.getCarte(), nb);
				if(p.estTermine()) {
					String msg="the jeux est termine";
					JOptionPane.showMessageDialog(jf, msg);
					p.calculerPoint();
					String msg2="congratulation!\n+player "+p.joueurGagnant()+" a gagne";
					JOptionPane.showMessageDialog(jf, msg2);
				}
	    	}
	    });
	}
	
	/**
	 * Constructeur du contrôleur de l'interface frameInit (qui permet de rentrer les paramètres de la partie)
	 * Ecoute le bouton "Lancer" qui instancie une nouvelle partie, l'initialise et créé les interfaces de jeu (Graphique et Texte)
	 * @param jbinit1 
	 * 					Le bouton pour lancer la partie
	 * @param boxVar
	 * 					Le numéro de la variante sélectionnée
	 * @param boxExt
	 * 					Le numéro de l'extension sélectionnée
	 * @param sliderProps
	 * 					Le nombre de Props sélectionné
	 * @param sliderTricks
	 * 					Le nombre de Trick sélectionné
	 * @param sliderNbJ
	 * 					Le nombre de Joueur réel sélectionné
	 */
	public Controleur(JButton jbinit1, JComboBox<String> boxVar,JComboBox<String> boxExt,JSlider sliderProps,JSlider sliderTricks, JSlider sliderNbJ) {
		this.jbinit1=jbinit1;
		this.jbinit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int nbVar = boxVar.getSelectedIndex();
				int nbExt = boxExt.getSelectedIndex();
				int nbProps = sliderProps.getValue();
				int nbTricks = sliderTricks.getValue();
				int nbJ = sliderNbJ.getValue();
				Partie p = new Partie(nbExt,nbVar,2,3,nbTricks,nbProps);
				
				p.Initialiser(nbJ);
				MonInterface monInter= new MonInterface(p);
				monInter.getFrame().setVisible(true);
				
			
				VueTexte vueTexte= new VueTexte(p);
				
			}
		});
		
	}
	
	
}
