package Modele;

/**
 * Trick est la classe possédant les attributs et les méthodes des Tricks du Jeu de carte 
 * @author grego
 *
 */
public class Trick {
	/**
	 * Le PropsType de la gauche du Trick
	 */
	protected PropsType[] gauche;
	/**
	 * Le PropsType de la droite du Trick
	 */
	protected PropsType[] droit;
	/**
	 * Le nombre de point du Trick
	 */
	protected int point;
	/**
	 * Un booléen true si le Trick est Visible
	 */
	protected boolean face_up;
	/**
	 * Le nom du Trick
	 */
	protected String name;
	
	/**
	 * getter qui renvoit le nom du trick
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter qui renvoit le nombre de point
	 * @return
	 */
	public int getPoint() {
		return point;
	}


	/**
	 * Constructeur de la classe Trick
	 * @param gauche
	 * 					Props gauche du Trick
	 * @param droit
	 * 					Props droit du Trick
	 * @param point
	 * 					Nombre de point du Trick
	 * @param name
	 * 					Nom du Trick
	 */
	public Trick(PropsType[] gauche, PropsType[] droit, int point, String name) {
		
		this.gauche = gauche;
		this.droit = droit;
		this.point = point;
		this.name = name;
		this.face_up=false;
	}
	
	/**
	 * Méthode qui renvoit un booléen true si la carte est visible
	 * @return
	 */
	public boolean isFace_up() {
		return face_up;
	}


	/**
	 * setter qui change la valeur du booléen true quand le Trick est visible
	 * @param face_up
	 */
	public void setFace_up(boolean face_up) {
		this.face_up = face_up;
	}


	@Override
	/**
	 * Méthode qui affiche les informations propres au Trick
	 */
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append("Trick [point=" + point + ", name=" + name + " gauche:");
		for(int i=0;i<this.gauche.length;i++) {
			sb.append(gauche[i]+" ");
		}
		sb.append("droite:");
		for(int i=0;i<this.droit.length;i++) {
			sb.append(droit[i]);
		}
		sb.append(" ]");
		return sb.toString();
	}


	/**
	 * Méthode qui renvoit un booléen si le Trick a été succeed par un joueur
	 * @param gauche
	 * 					Props gauche du joueur
	 * @param droit
	 * 					Props droit du joueur
	 * @return
	 */
	public boolean performerSucceed(Props gauche,Props droit) {
		
		for(int i=0;i<this.gauche.length;i++) {
			for(int j=0;j<this.droit.length;j++) {
				
				if(this.gauche[i]==gauche.getName()&&this.droit[j]==droit.getName() || this.gauche[i]==droit.getName()&&this.droit[j]==gauche.getName())
				 return true;
			}
		}

		return false;
		
	}
}