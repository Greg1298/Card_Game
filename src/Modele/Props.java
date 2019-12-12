package Modele;

/**
 * Props est la classe qui donne les attributs et les m�thodes appliquable aux Props
 * @author grego
 *
 */
public class Props {
	/**
	 * PropsType auquel le props appartient
	 */
	private PropsType name;
	/**
	 * Bool�en true si la carte est visible
	 */
	private boolean face_up;
	
	/**
	 * Constructeur de la classe Props
	 * @param name
	 */
	public Props(PropsType name) {
		this.name = name;
		this.face_up=false;
		}
	
	/**
	 * getter qui renvoit le PropsType auxquel le props appartient
	 * @return
	 */
	public PropsType getName() {
		return name;
	}

	@Override
	/**
	 * M�thode d'affichage d'un Props
	 */
	public String toString() {
		return "Props [name=" + name + "]";
	}
	/**
	 * M�thode qui renvoit un bool�en true si la carte est visible
	 * @return
	 */
	public boolean isFace_up() {
		return face_up;
	}
	/**
	 * setter du bool�en true quand la carte est visible
	 * @param face_up
	 */
	public void setFace_up(boolean face_up) {
		this.face_up = face_up;
	}
}

