package Modele;

/**
 * TheOtherHatTrick est une classe qui gère les méthodes quand TheOtherHatTrick est joué
 * @author grego
 *
 */
public class TheOtherHatTrick extends Trick {
		
	/**
	 * Jeu de carte de la partie
	 */
	private JeuDeCarte key;
	
	/**
	 * Constructeur de TheOtherHatTrick
	 * @param gauche
	 * 					Le props gauche du joueur qui tente de performer le Trick
	 * @param droit
	 * 					Le props droit du joueur qui tente de performer le Trick
	 * @param point
	 * 					Le nopbre de point du joueur qui tente de performer le Trick
	 * @param name
	 * 					Le nom du joueur qui tente de performer le Trick
	 * @param key
	 * 					Le jeu de carte de la partie
	 */
	public TheOtherHatTrick(PropsType[] gauche, PropsType[] droit, int point, String name,JeuDeCarte key) {
		super(gauche, droit, point, name);
		this.key=key;
	}
	@Override
	/**
	 * méthode qui renvoit true si le joueur a succeed TheOtherHatTrick
	 */
	public boolean performerSucceed(Props gauche,Props droit) {
		for(int i=0;i<this.gauche.length;i++) {
			for(int j=0;j<this.droit.length;j++) {
				
				if(this.gauche[i]==gauche.getName()&&this.droit[j]==droit.getName())
				 { int n=key.getKey();
					key.setKey(n+1);
					return true;
				 }
			}
		}
		int n=key.getNbErreur();
	    key.setNbErreur(n+1);
		return false;
		
	}
}