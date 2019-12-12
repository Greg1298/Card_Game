package Vue;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controleur.Controleur;
import Modele.JeuDeCarte;
import Modele.Joueur;
import Modele.JoueurReel;
import Modele.JoueurVirtuel;
import Modele.Observable;
import Modele.Observer;
import Modele.Partie;
import Modele.Props;
import Modele.Trick;
import Vue.frameInit;

import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JInternalFrame;

/**
 * MonInterface est la classe qui servira à créer l'interface graphique principale
 * Cette interface implémente Observer dans le cadre du modèle MVC
 * @author grego
 * @see Controleur
 */
public class MonInterface implements Observer {
	/**
	 * La partie sur laquelle s'appuie l'interface graphique
	 * @see Partie
	 */
    private Partie game;
    /**
	 * Le frame de l'interface graphique principale
	 * @see JFrame
	 */
	private JFrame frame;
	/**
	 * Le frame de l'interface graphique de la sélection des paramètres
	 * @see Partie
	 */
	private JFrame frameInit;
	/**
	 * Le pannel sur lequel repose les éléments graphiques de l'interface principale
	 * @see Jpanel
	 */
	private JPanel jp1;
	/**
	 * Le pannel sur lequel repose les éléments graphiques de la sélection des paramètres
	 * @see Jpanel
	 */
	private JPanel jp2;
	/**
	 * Le bouton qui permet de lancer une variante
	 * @see Variante
	 * @see JButton
	 */
	private JButton jb1;
	/**
	 * Le bouton qui permet de lancer la création de la Partie
	 * @see JButton
	 */
	private JButton jbinit1;
	/**
	 * Le bouton qui permet de lancer l'échange de Trick
	 * @see JButton
	 */
    private JButton jb2;
    /**
	 * Le bouton qui permet de lancer l'échange de Trick
	 * @see JButton
	 */
    private JButton jb3;
    /**
	 * Le bouton qui permet de lancer la méthode performTrick()
	 * @see Partie
	 */
    private JButton jb4;
    /**
	 * Le label pour la carte gauche du joueur 1
	 * @see JLabel
	 */
    private JLabel lb1;
    /**
	 * Le label pour la carte droite du joueur 1
	 * @see JLabel
	 */
    private JLabel lb2;
    /**
	 * Le label pour la carte gauche du joueur 2
	 * @see JLabel
	 */
    private JLabel lb3;
    /**
	 * Le label pour la carte droite du joueur 2
	 * @see JLabel
	 */
    private JLabel lb4;
    /**
	 * Le label pour la carte gauche du joueur 3
	 * @see JLabel
	 */
    private JLabel lb5;
    /**
	 * Le label pour la carte droite du joueur 3
	 * @see JLabel
	 */
    private JLabel lb6;
    /**
	 * Le label pour la dernière carte Props succeed du joueur 1
	 * @see JLabel
	 */
    private JLabel lb7;
    /**
	 * Le label pour la dernière carte Props succeed du joueur 2
	 * @see JLabel
	 */
    private JLabel lb8;
    /**
	 * Le label pour la dernière carte Props succeed du joueur 3
	 * @see JLabel
	 */
    private JLabel lb9;
    /**
     * Le label pour le score du joueur 1
	 * @see JLabel
	 */
    private JLabel score1;
    /**
	 * Le label pour le score du joueur 2
	 * @see JLabel
	 */
    private JLabel score2;
    /**
	 * Le label pour le score du joueur 3
	 * @see JLabel
	 */
    private JLabel score3;
    /**
	 * Le label pour le trickPile
	 * @see JLabel
	 */
    private JLabel trickPile;
    /**
	 * Le label pour le trickDeck
	 * @see JLabel
	 */
    private JLabel trickDeck;
    /**
	 * Le label pour la 7ème Props
	 * @see JLabel
	 */
    private JLabel seventhProp;
    /**
	 * Le label pour le tour de jeu
	 * @see JLabel
	 */
    private JLabel turn;
    /**
	 * Le bouton qui permet d'initialiser le JeuDeCarte
	 * @see JeuDeCarte
	 * * @see JButton
	 */
    private JButton initial;
    /**
	 * Le bouton qui permet de changer de tour
	 * @see Partie
	 * @see JButton
	 */
    private JButton next;
    /**
	 * Le label qui notifie des informations aux joueurs
	 * @see JLabel
	 */
    private JLabel notice;

	
    /**Constructeur de l'interface graphique qui créé les éléments graphiques et instancie un contrôleur sur ces éléments
     * 
     * @param p
     */
	public MonInterface(Partie p) {
		initialize(p);
		new Controleur(initial,next,jb1,jb2,jb3,jb4,notice,frame,game);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * Méthode qui créé les éléments graphiques de l'application
	 * @param p
	 */
	private void initialize(Partie p) {
		this.game=p;
		this.game.getCarte().addObserver(this);
		this.game.getJoueur().get(0).addObserver(this);
		this.game.getJoueur().get(1).addObserver(this);
		this.game.getJoueur().get(2).addObserver(this);
		this.game.addObserver(this);
		frame = new JFrame();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	    jp1 = new JPanel(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    
	    jb1 = new JButton("performer variante");
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 0;
	    c.gridy = 0;
	    c.gridheight = 1;
	    c.insets = new Insets(10,10,10,10);
	    jp1.add(jb1,c);
	   
	    
	    jb2 = new JButton("choisir un trick");
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 0;
	    c.gridy = 1;
	    c.gridheight = 1;
	    c.insets = new Insets(10,10,10,10);
	    jp1.add(jb2,c);
	    
	    jb3 = new JButton("echanger votre props");
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 0;
	    c.gridy = 2;
	    c.gridheight = 1;
	    c.insets = new Insets(10,10,10,10);
	    jp1.add(jb3,c);
	    
	    jb4 = new JButton("performer un trick");    
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 0;
	    c.gridy = 3;
	    c.gridheight = 1;
	    c.insets = new Insets(10,10,10,10);
	    jp1.add(jb4,c);
	    
	    frame.getContentPane().add(jp1);
	    
	    
	    ImageIcon imageIcon = new ImageIcon(new ImageIcon("Images\\CarteRetournee.png").getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT));
	    
	    
	    lb1 = new JLabel("left-player1");
	    String path1="Images\\CarteRetournee.png";
	    lb1.setIcon(imageIcon);
	    c.fill = GridBagConstraints.VERTICAL;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 1;
	    c.gridy = 0;
	    c.gridheight = 3;
	    c.insets = new Insets(10,10,10,10);
	    lb1.setHorizontalTextPosition(SwingConstants.CENTER);
	    lb1.setVerticalTextPosition(SwingConstants.TOP);
	    jp1.add(lb1,c);
	    
	    lb2 = new JLabel("right-player1");
	    lb2.setIcon(imageIcon);
	    lb2.setHorizontalTextPosition(SwingConstants.CENTER);
	    lb2.setVerticalTextPosition(SwingConstants.TOP);
	    c.fill = GridBagConstraints.VERTICAL;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 2;
	    c.gridy = 0;
	    c.gridheight = 3;
	    c.insets = new Insets(10,10,10,10);
	    jp1.add(lb2,c);
	    
	    lb3 = new JLabel("left-player2");
	    lb3.setIcon(imageIcon);
	    c.fill = GridBagConstraints.VERTICAL;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 1;
	    c.gridy = 3;
	    c.gridheight = 3;
	    c.insets = new Insets(10,10,10,10);
	    lb3.setHorizontalTextPosition(SwingConstants.CENTER);
	    lb3.setVerticalTextPosition(SwingConstants.TOP);
	    jp1.add(lb3,c);
	  
	    lb4 = new JLabel("right-player2");
	    lb4.setIcon(imageIcon);
	    lb4.setHorizontalTextPosition(SwingConstants.CENTER);
	    lb4.setVerticalTextPosition(SwingConstants.TOP);
	    c.fill = GridBagConstraints.VERTICAL;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 2;
	    c.gridy = 3;
	    c.gridheight = 3;
	    c.insets = new Insets(10,10,10,10);
	    jp1.add(lb4,c);
	    
	    lb5 = new JLabel("left-player3");
	    lb5.setIcon(imageIcon);
	    c.fill = GridBagConstraints.VERTICAL;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 1;
	    c.gridy = 7;
	    c.gridheight = 3;
	    c.insets = new Insets(10,10,10,10);
	    lb5.setHorizontalTextPosition(SwingConstants.CENTER);
	    lb5.setVerticalTextPosition(SwingConstants.TOP);
	    jp1.add(lb5,c);

	    lb6 = new JLabel("right-player3");
	    lb6.setIcon(imageIcon);
	    lb6.setHorizontalTextPosition(SwingConstants.CENTER);
	    lb6.setVerticalTextPosition(SwingConstants.TOP);
	    c.fill = GridBagConstraints.VERTICAL;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 2;
	    c.gridy = 7;
	    c.gridheight = 3;
	    c.insets = new Insets(10,10,10,10);
	    jp1.add(lb6,c);
	    
	    lb7 = new JLabel("successTrick");
	    lb7.setVerticalTextPosition(SwingConstants.TOP);
	    lb7.setHorizontalTextPosition(SwingConstants.CENTER);
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 3;
	    c.gridy = 0;
	    c.gridheight = 3;
	    jp1.add(lb7,c);
	    
	    lb8 = new JLabel("successTrick-player2");
	    lb8.setVerticalTextPosition(SwingConstants.TOP);
	    lb8.setHorizontalTextPosition(SwingConstants.CENTER);
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 3;
	    c.gridy = 3;
	    c.gridheight = 3;
	    jp1.add(lb8,c);
	    
	    lb9 = new JLabel("successTrick-player3");
	    lb9.setVerticalTextPosition(SwingConstants.TOP);
	    lb9.setHorizontalTextPosition(SwingConstants.CENTER);
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 3;
	    c.gridy = 6;
	    c.gridheight = 3;
	    jp1.add(lb9,c);
	    
	    score1 = new JLabel("player 1 score : 0");
	    score1.setFont(new Font("dialog", 1, 20));
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 0;
	    c.gridy = 4;
	    c.gridheight = 1;
	    jp1.add(score1,c);
	    
	    score2 = new JLabel("player 2 score : 0");
	    score2.setFont(new Font("dialog", 1, 20));
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 0;
	    c.gridy = 5;
	    c.gridheight = 1;
	    jp1.add(score2,c);
	    
	    score3 = new JLabel("player 3 score : 0");
	    score3.setFont(new Font("dialog", 1, 20));
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 0;
	    c.gridy = 6;
	    c.gridheight = 1;
	    jp1.add(score3,c);
	    
	    trickPile = new JLabel("TrickPile");
	    trickPile.setFont(new Font("dialog", 1, 20));
	    trickPile.setHorizontalTextPosition(SwingConstants.CENTER);
	    trickPile.setVerticalTextPosition(SwingConstants.TOP);
	    trickPile.setIcon(new ImageIcon(new ImageIcon("Images\\The Bad Hunter.png").getImage().getScaledInstance(280, 210, Image.SCALE_DEFAULT)));
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 4;
	    c.gridy = 0;
	    c.gridheight = 3;
	    jp1.add(trickPile,c);
	    
	    trickDeck = new JLabel("trickDeck");
	    trickDeck.setFont(new Font("dialog", 1, 20));
	    trickDeck.setHorizontalTextPosition(SwingConstants.CENTER);
	    trickDeck.setVerticalTextPosition(SwingConstants.TOP);
	    trickDeck.setIcon(new ImageIcon(new ImageIcon("Images\\The Bad Hunter.png").getImage().getScaledInstance(280, 210, Image.SCALE_DEFAULT)));
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 5;
	    c.gridy = 0;
	    c.gridheight = 3;
	    jp1.add(trickDeck,c);
	    
	    seventhProp = new JLabel("7th prop card");
	    seventhProp.setFont(new Font("dialog", 1, 20));
	    seventhProp.setHorizontalTextPosition(SwingConstants.CENTER);
	    seventhProp.setVerticalTextPosition(SwingConstants.TOP);
	    seventhProp.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 4;
	    c.gridy = 3;
	    c.gridheight = 4;

	    jp1.add(seventhProp,c);
	    
	    initial = new JButton("Initialiser");
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 0;
	    c.gridy = 7;
	    c.gridheight = 1;
	    c.insets = new Insets(10,10,10,10);
	    jp1.add(initial,c);
	    
	    next = new JButton("Next");
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 2;
	    c.gridx = 0;
	    c.gridy = 8;
	    c.gridheight = 1;
	    c.insets = new Insets(10,10,10,10);
	    jp1.add(next,c);
	    
	    turn = new JLabel("turn 1 player 1");
	    turn.setFont(new Font("dialog", 1, 20));
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 4;
	    c.gridy = 7;
	    c.gridheight = 1;
	    c.insets = new Insets(10,10,10,10);
	    jp1.add(turn,c);
	    
	    notice = new JLabel("Notice board");
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1;
	    c.weighty = 1;
	    c.gridx = 4;
	    c.gridy = 8;
	    c.gridheight = 1;
	    c.insets = new Insets(10,10,10,10);
		notice.setFont(new Font("dialog", 1, 20));
		jp1.add(notice,c);

	    
		
	}
	
	/**
	 * Méthode qui permet de retourner la frame de l'interface
	 * @return Jframe
	 */
	public JFrame getFrame() {
		return frame;
	}
	/**
	 * Méthode Setteur de la frame
	 * @param frame
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Méthode qui va chercher les images des Tricks
	 * @param e
	 * @return String
	 */
	public String getTrickPath(Trick e) {
		String name=e.getName();
		String path;
		switch(name) {
		case "The Hat Trick":path= "Images\\The Hat Trick.png";break;
		case "The Vegetable Patch":path= "Images\\The Vegetable Patch.png";break;
		case "The Hungry Rabbit":path= "Images\\The Hungry Rabbit.png";break;
		case "The Bunch of Carrots":path= "Images\\The Bunch of Carrot.png";break;
		case "The Pair of Rabbits":path= "Images\\The Pair of Rabbit.png";break;
		case "The Rabbit That Didn't Like Carrots":path= "Images\\The Rabbit That Didn't Like Carrots.png";break;
		case "The Vegetable Hat Trick":path= "Images\\The Vegetable Hat Trick.png";break;
		case "The Carrot Hat Trick":path= "Images\\The Carrot Hat Trick.png";break;
		case "The Sligther Easier Hat Trick":path= "Images\\The Sligther Easier Hat Trick.png";break;
		case "The Other Hat Trick":path= "Images\\The Other Hat Trick.png";break;
		default:path="Images\\The Bad Hunter.png"; 
		
		}
		
		return path;
	}
	
	/**
	 * Méthode qui va chercher les images des Props
	 * @param e
	 * @return
	 */
	public String getPropsPath(Props e) {
		String name=e.getName().toString();
		
		if(!e.isFace_up())name="invisible";
		String path;
		switch(name) {
		case "Rabbit":path="Images\\Rabbit.png";break;
		case "The_other_rabbit":path="Images\\The_other_rabbit.png";break;
		case "Carrots":path="Images\\Carrots.png";break;
		case "Hat":path="Images\\Hat.png";break;
		case "Lettuce":path="Images\\Lettuce.png";break;
		case "Hunter":path="Images\\Hunter.png";break;
		case "Tomato":path="Images\\Tomato.png";break;
		default:path="Images\\CarteRetournee.png";
		}
		
		return path;
	}

	@Override
	/**
	 * Méthode qui va être appelée par les méthodes des classes du Package Modèle afin de mettre à jour l'interface graphique
	 * @param arg
	 * @param o
	 */
	public void update(Observable arg, Object o) {
		// TODO Auto-generated method stub
		if(arg instanceof JeuDeCarte) {
			//Mise à jour des cartes modifiées lors de l'appel de la méthode getSecond() qui choisi la carte du TrickDeck (face cachée) 
			if(o=="getSecond") {
				Trick t=((JeuDeCarte) arg).getTop();
				String path=this.getTrickPath(t);
				this.trickPile.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(280, 210, Image.SCALE_DEFAULT)));
				
				if(game.getCarte().deckEmpty()==false) {
				Trick tr=((JeuDeCarte) arg).getJustSecond();
				String path1=this.getTrickPath(tr);
				this.trickDeck.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(280, 210, Image.SCALE_DEFAULT)));
				}
				
			}
		}
		if(arg instanceof JoueurReel | arg instanceof JoueurVirtuel) {
			//Mise à jour des cartes modifiées lors du succès de la méthode PerformerTrick()
			if(o=="success") {
				//On différencie 3 cas qui sont les 3 joueurs pouvant réussir un Trick
				int num=((Joueur) arg).getOrdre();
				switch(num) {
				case 1: {Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
				        Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
				        this.lb1.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
				        this.lb2.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
				        Trick trickSuccess=((Joueur) arg).getCardSuccess();String path3=this.getTrickPath(trickSuccess);
				        this.lb7.setIcon(new ImageIcon(new ImageIcon(path3).getImage().getScaledInstance(280, 210, Image.SCALE_DEFAULT)));
				        Trick pile=game.getCarte().getTop();String path4=this.getTrickPath(pile);
				        this.trickPile.setIcon(new ImageIcon(new ImageIcon(path4).getImage().getScaledInstance(280, 210, Image.SCALE_DEFAULT)));
				        int score=((Joueur) arg).getScore();String text="player 1 score : "+score;
				        this.score1.setText(text);
				        
				        }
				        break;
				case 2:{
					    Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
		               Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
		               this.lb3.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
		               this.lb4.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
		               Trick trickSuccess=((Joueur) arg).getCardSuccess();String path3=this.getTrickPath(trickSuccess);
		               this.lb8.setIcon(new ImageIcon(new ImageIcon(path3).getImage().getScaledInstance(280, 210, Image.SCALE_DEFAULT)));
		               int score=((Joueur) arg).getScore();String text="player 2 score : "+score;
		               this.score2.setText(text);
		               Trick pile=game.getCarte().getTop();String path4=this.getTrickPath(pile);
				       this.trickPile.setIcon(new ImageIcon(new ImageIcon(path4).getImage().getScaledInstance(280, 210, Image.SCALE_DEFAULT)));
				       }
		               break;
				case 3:{
					   Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
	                   Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
	                   this.lb5.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
	                   this.lb6.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
	                   Trick trickSuccess=((Joueur) arg).getCardSuccess();String path3=this.getTrickPath(trickSuccess);
	                   this.lb9.setIcon(new ImageIcon(new ImageIcon(path3).getImage().getScaledInstance(280, 210, Image.SCALE_DEFAULT)));
	                   int score=((Joueur) arg).getScore();String text="player 3 score : "+score;
	                   this.score3.setText(text);
	                   Trick pile=game.getCarte().getTop();String path4=this.getTrickPath(pile);
				       this.trickPile.setIcon(new ImageIcon(new ImageIcon(path4).getImage().getScaledInstance(280, 210, Image.SCALE_DEFAULT)));
				       }
	                   break;
					
				}
				String text="Joueur "+((Joueur) arg).getOrdre()+" success";
				JOptionPane.showMessageDialog(this.frame,text,"perform result",JOptionPane.INFORMATION_MESSAGE);
			}
			//Mise à jour du score du joueur ayant perdu 
			if(o=="fail") {
				int num=((Joueur) arg).getOrdre();
				switch(num) {
				case 1: {
				        int score=((Joueur) arg).getScore();String text="player 1 score : "+score;
				        this.score1.setText(text);
				        
				        }
				        break;
				case 2:{
					    
		               int score=((Joueur) arg).getScore();String text="player 2 score : "+score;
		               this.score2.setText(text);
				       }
		               break;
				case 3:{
					   
	                   int score=((Joueur) arg).getScore();String text="player 3 score : "+score;
	                   this.score3.setText(text);
				       }
	                   break;
					
				}
				String text="Joueur "+((Joueur) arg).getOrdre()+" fail";
				JOptionPane.showMessageDialog(this.frame,text,"perform result",JOptionPane.INFORMATION_MESSAGE);
			}
			//Mise à jour du score en cas de succès de la variante the Risky Bet
			if(o=="success variante") {
				int num=((Joueur) arg).getOrdre();
				switch(num) {
				case 1: {
				        int score=((Joueur) arg).getScore();String text="player 1 score : "+score;
				        this.score1.setText(text);
				        
				        }
				        break;
				case 2:{
					    
		               int score=((Joueur) arg).getScore();String text="player 2 score : "+score;
		               this.score2.setText(text);
				       }
		               break;
				case 3:{
					   
	                   int score=((Joueur) arg).getScore();String text="player 3 score : "+score;
	                   this.score3.setText(text);
				       }
	                   break;
					
				}
		}
		if(arg instanceof JoueurReel) {
			//Mise à jour des cartes lors de l'appel de la méthode echangerProps()
			int num=((JoueurReel) arg).getOrdre();
			if(o=="swap") {
				
				switch(num) {
				case 1: {
					    
					    Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
				        Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
				        this.lb1.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
				        this.lb2.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
				        }
				        break;
				case 2:{
					   
					   Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
		               Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
		               this.lb3.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
		               this.lb4.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
		               
				       }
		               break;
				case 3:{
					
			        
					   Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
					
	                   Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
	                   
	                   this.lb5.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
	                   this.lb6.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
	                   
				       }
	                   break;
					
					
				}
				
			}
			//Mise à jour des cartes lors de l'appel de la méthode setFaceUp() qui permet de mettre les cartes en position visible lorsqu'un joueur perd
			if(o=="faceUp") {
				
				switch(num) {
				case 1: {Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
				        Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
				        this.lb1.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
				        this.lb2.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
				        }
				        break;
				case 2:{
					    Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
		               Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
		               this.lb3.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
		               this.lb4.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
				       }
		               break;
				case 3:{
					   Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
	                   Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
	                   this.lb5.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
	                   this.lb6.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
				       }
	                   break;
					
				}
				
			}
			
		}
		}
		//Mise à jour des cartes lors de l'appel de la méthode echangerProps() pour un joueur virtuel
		if(arg instanceof JoueurVirtuel) {
			
			if(o=="swap") {
				int num=((Joueur) arg).getOrdre();
				switch(num) {
				case 1: {
					
					Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
					
				        Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
				        
				        this.lb1.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
				        this.lb2.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
				       
				        }
				        break;
				case 2:{
					  
					    Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
					   
		               Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
		               
		               this.lb3.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
		               this.lb4.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
		               
				       }
		               break;
				case 3:{
					   Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
	                   Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
	                   this.lb5.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
	                   this.lb6.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
	                  
				       }
	                   break;
					
				}
			}
			//Mise à jour des cartes lors de l'appel de la méthode setFaceUp() qui permet de mettre les cartes en position visible lorsqu'un joueur virtuel perd
			if(o=="faceUp") {
				
				int num=((Joueur) arg).getOrdre();
				switch(num) {
				case 1: {Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
				        Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
				        
				        this.lb1.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
				        this.lb2.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
				        }
				        break;
				case 2:{
					    Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
		               Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
		               this.lb3.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
		               this.lb4.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
		               
				       }
		               break;
				case 3:{
					   Props left=((Joueur) arg).getGauche();String path1=this.getPropsPath(left);
	                   Props right=((Joueur) arg).getDroite();String path2=this.getPropsPath(right);
	                   this.lb5.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
	                   this.lb6.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
	                   
				       }
	                   break;
					
				}
			}
			
		}
		if(arg instanceof Partie) {
			//Mise à jour des cartes Props lors de l'initialisation de la partie
			if(o=="distribuer carte") {
				//joueur
				Joueur player1=((Partie) arg).getJoueur().get(0);
				Joueur player2=((Partie) arg).getJoueur().get(1);
				Joueur player3=((Partie) arg).getJoueur().get(2);
				Props left=player1.getGauche();String path1=this.getPropsPath(left);
                Props right=player1.getDroite();String path2=this.getPropsPath(right);
                this.lb1.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
                this.lb2.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
                Props left1=player2.getGauche();String path3=this.getPropsPath(left1);
                Props right1=player2.getDroite();String path4=this.getPropsPath(right1);
                this.lb3.setIcon(new ImageIcon(new ImageIcon(path3).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
                this.lb4.setIcon(new ImageIcon(new ImageIcon(path4).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
                Props left2=player3.getGauche();String path5=this.getPropsPath(left2);
                Props right2=player3.getDroite();String path6=this.getPropsPath(right2);
                this.lb5.setIcon(new ImageIcon(new ImageIcon(path5).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
                this.lb6.setIcon(new ImageIcon(new ImageIcon(path6).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
                //prop
                Props seven=((Partie) arg).getCarte().getPropsPile().get(0);
                String pathSeven = this.getPropsPath(seven);
                this.seventhProp.setIcon(new ImageIcon(new ImageIcon(pathSeven).getImage().getScaledInstance(180, 250, Image.SCALE_DEFAULT)));
                //trickpile
                Trick e = ((Partie) arg).getCarte().getTop();
                String pathPile=this.getTrickPath(e);
             
                this.trickPile.setIcon(new ImageIcon(new ImageIcon(pathPile).getImage().getScaledInstance(280, 210, Image.SCALE_DEFAULT)));
                //trickDeck
                Trick t = ((Partie) arg).getCarte().getSecond();
                String pathDeck=this.getTrickPath(t);
             
                this.trickPile.setIcon(new ImageIcon(new ImageIcon(pathDeck).getImage().getScaledInstance(280, 210, Image.SCALE_DEFAULT)));
                //notice
                String notice="<html><h3 style='text-align:center;color:blue;'>notice:</h3>now it is player 1 <br />"+" type: "+((Partie)arg).getJoueur().get(0).getClass()+"</html>";
               
                this.notice.setText(notice);
			}
			//Mise à jour éléments d'information du jeu (notice et ordre des joueurs)
			if(o=="next") {
				int order=((Partie) arg).getOrder();
				String text="turn "+((Partie) arg).getTurn()+" player "+order;
				this.turn.setText(text);
				String notice="<html><h3 style='text-align:center;color:blue;'>notice:</h3>"
						+ "Le joueur "+((Partie)arg).getOrder()+"<br /> est de type: "+((Partie)arg).getJoueur().get(order-1).getClass()+"</html>";
				this.notice.setText(notice);
			}
			//Mise à jour des éléments d'information du jeu en cas de changement de tour
			if(o=="new turn") {
				int order=((Partie) arg).getOrder();
				String text="turn "+((Partie) arg).getTurn()+" player "+order;
				this.turn.setText(text);
			}
		}
	}
}
