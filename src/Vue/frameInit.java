package Vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controleur.Controleur;
import Modele.Partie;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * frameInit est la classe de l'interface qui permet de rentrer les paramètres et de lancer une partie avec ceux-ci
 * @author grego
 *
 */
public class frameInit extends JFrame {

	/**
	 * pannel qui permet de placer les éléments graphiques
	 * @see JPanel
	 */
	private JPanel contentPane;
	/**
	 * Bouton qui permet de lancer la partie
	 * @see JButton
	 * @see Partie
	 */
	private JButton jbinit1;
	/**
	 * slider qui permet de choisir le nombre de Props dans la partie
	 * @see JSlider
	 */
    private JSlider sliderProps;
    /**
     * slider qui permet de choisir le nombre de Tricks dans la partie
     * @see JSlider
     */
	private JSlider sliderTricks;
	/**
	 * comboBox qui permet de chosir la variante
	 * @see Variante
	 */
	private JComboBox boxVar;
	/**
	 * comboBox qui permet de chosir l'extension
	 * @see Extension
	 */
	private JComboBox boxExt;
	/**
	 * label pour le choix du nombre de Tricks
	 * @see JLabel
	 */
	private JLabel lblTricks;
	/**
	 * label pour le choix du nombre de Props
	 * @see JLabel
	 */
	private JLabel lblProps;
	/**
	 * label pour le choix de l'extension
	 * @see JLabel
	 */
	private JLabel lblExt;
	/**
	 * label pour le choix de la variante
	 * @see JLabel
	 */
	private JLabel lblVar;
	/**
	 * label pour le choix du nombre de Joueurs
	 * @see JLabel
	 */
	private JLabel lblChoixDuNombre;
	/**
	 * slider pour le choix du nombre de joueurs
	 * @see JSlider
	 */
	private JSlider sliderNbJ;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameInit init = new frameInit();
					init.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frameInit() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	    
		//Bouton de lancement de la partie
	    jbinit1 = new JButton("Lancer");
	    jbinit1.setBounds(468, 287, 173, 69);
	    jbinit1.setVisible(true);
	    contentPane.setLayout(null);
	    contentPane.add(jbinit1);
	    
	    //ComboBox du choix de variante
	    String[] variante = {"Pas de variante","The risky bet", "Counter"};
	    boxVar = new JComboBox(variante);
	    boxVar.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    boxVar.setBounds(75, 81, 157, 32);
	    contentPane.add(boxVar);
	    
	    //Jslider du choix du nombre de Props
	    sliderProps = new JSlider();
	    sliderProps.setValue(7);
	    sliderProps.setSnapToTicks(true);
	    sliderProps.setMinorTickSpacing(1);
	    sliderProps.setMajorTickSpacing(2);
	    sliderProps.setPaintTicks(true);
	    sliderProps.setPaintLabels(true);
	    sliderProps.setMaximum(15);
	    sliderProps.setMinimum(7);
	    sliderProps.setBounds(448, 56, 200, 52);
	    contentPane.add(sliderProps);
	    
	    //Label du choix de la variante
	    lblVar = new JLabel("Choix de la variante");
	    lblVar.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblVar.setBounds(75, 36, 194, 32);
	    contentPane.add(lblVar);
	    
	    //Label du choix du nombre de Props
	    lblProps = new JLabel("Choix du nombre de Props");
	    lblProps.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblProps.setBounds(448, 25, 210, 30);
	    contentPane.add(lblProps);
	    
	    //Label du choix de l'extension 
	    lblExt = new JLabel("Choix des extensions");
	    lblExt.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblExt.setBounds(76, 142, 173, 37);
	    contentPane.add(lblExt);
	    
	  //ComboBox du choix de l'extension
	    String[] extension = {"Pas d'extension","The Hunter"};
	    boxExt = new JComboBox(extension);
	    boxExt.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    boxExt.setBounds(75, 192, 157, 29);
	    contentPane.add(boxExt);
	    
	    //Label du choix du nombre de Tricks
	    lblTricks = new JLabel("Choix du nombre de Tricks");
	    lblTricks.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblTricks.setBounds(441, 121, 200, 37);
	    contentPane.add(lblTricks);
	    
	  //JSlider du choix du nombre de Trick
	    sliderTricks = new JSlider();
	    sliderTricks.setValue(10);
	    sliderTricks.setPaintTicks(true);
	    sliderTricks.setSnapToTicks(true);
	    sliderTricks.setPaintLabels(true);
	    sliderTricks.setMajorTickSpacing(2);
	    sliderTricks.setMinorTickSpacing(1);
	    sliderTricks.setMinimum(10);
	    sliderTricks.setMaximum(20);
	    sliderTricks.setBounds(448, 169, 200, 45);
	    contentPane.add(sliderTricks);
	    
	    //Label du choix du nombre de joueurs
	    lblChoixDuNombre = new JLabel("Choix du nombre de joueurs r\u00E9els");
	    lblChoixDuNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    lblChoixDuNombre.setBounds(77, 262, 257, 45);
	    contentPane.add(lblChoixDuNombre);
	    
	  //JSlider du choix du nombre de joueurs
	    sliderNbJ = new JSlider();
	    sliderNbJ.setValue(1);
	    sliderNbJ.setSnapToTicks(true);
	    sliderNbJ.setPaintTicks(true);
	    sliderNbJ.setPaintLabels(true);
	    sliderNbJ.setMajorTickSpacing(1);
	    sliderNbJ.setMinimum(1);
	    sliderNbJ.setMaximum(3);
	    sliderNbJ.setBounds(87, 306, 200, 52);
	    contentPane.add(sliderNbJ);
	    
	    //Instanciation du contôleur de l'interface de choix des paramètres frameInit 
	    new Controleur(jbinit1,boxVar,boxExt,sliderProps,sliderTricks,sliderNbJ);
	    
	}
	
	/**
	 * Méthode qui permet d'obtenir le nombre de Props sélectionné par le joueur
	 * @return
	 */
	public int getProps() {
		return sliderProps.getValue();
	}
	
	/**
	 * Méthode qui permet d'obtenir le nombre de Tricks sélectionné par le joueur
	 * @return
	 */
	public int getTrick() {
		return sliderTricks.getValue();
	}
	
	/**
	 * Méthode qui permet d'obtenir la variante sélectionnée par le joueur
	 * @return
	 */
	public int getVar() {
		return boxVar.getSelectedIndex();
	}
	
	/**
	 * Méthode qui permet d'obtenir l'extension sélectionnée par le joueur
	 * @return
	 */
	public int getExt() {
		return boxExt.getSelectedIndex();
	}
}
