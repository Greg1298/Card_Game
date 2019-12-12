package Modele;

/**
 * Variante est une classe qui possède les méthode à appliquer en cas de tentative d'un joueur d'effectuer celle-ci
 * @author grego
 *
 */
public class Variante implements NewRule {
	private boolean flagRight;
    private boolean flagLeft;
    private boolean flag;
   
	public Variante() {
	   this.flagRight= false;
	   this.flagLeft=false;
	   this.flag=false;
	}

	@Override
	/**
     * Constructeur de la classe Variante
     * @param p
     * 					La partie
     * @param ordre
     * 					L'ordre du joueur en train de jouer
     * @param choix
     * 					Un tableau pour sauvegarder les choix du joueur qui fait la variante
     * @param nb
     * 					Un entier qui est utilisé dans l'autre variante
     */
	public void NewJouer(Partie p,int ordre,int[] choix,int nb) {
		this.flagRight= false;
		this.flagLeft=false;
		this.flag=false;
		int count=0;
		int n2 =choix[0];
		if(n2==1) {
			for(int i=1;i<=p.nbPlayer();i++) {
				int num=1;
				if(i!=ordre) {
					int n=choix[num];
					num++;
					switch(n) {
					case 1:
						if(p.getJoueur().get(i-1).getGauche().getName()==PropsType.Rabbit)this.flagLeft=true;
						break;
					case 2:
						if(p.getJoueur().get(i-1).getGauche().getName()==PropsType.The_other_rabbit)this.flagLeft=true;	
						break;	
					case 3:
						if(p.getJoueur().get(i-1).getGauche().getName()==PropsType.Hat)this.flagLeft=true;	
						break;	
					case 4:
						if(p.getJoueur().get(i-1).getGauche().getName()==PropsType.Carrots)this.flagLeft=true;	
						break;	
					case 5:
						if(p.getJoueur().get(i-1).getGauche().getName()==PropsType.Lettuce)this.flagLeft=true;	
						break;	
					default:
						break;
					}
					int n1=choix[num];num++;
					switch(n1) {
					case 1:
						if(p.getJoueur().get(i-1).getGauche().getName()==PropsType.Rabbit)this.flagRight=true;
						break;
					case 2:
						if(p.getJoueur().get(i-1).getGauche().getName()==PropsType.The_other_rabbit)this.flagRight=true;
						break;	
					case 3:
						if(p.getJoueur().get(i-1).getGauche().getName()==PropsType.Hat)this.flagRight=true;	
						break;	
					case 4:
						if(p.getJoueur().get(i-1).getGauche().getName()==PropsType.Carrots)this.flagRight=true;
					case 5:
						if(p.getJoueur().get(i-1).getGauche().getName()==PropsType.Lettuce)this.flagRight=true;	
						break;	
					default:
						break;
					}
					if(this.flagLeft&&this.flagRight)count++;
				}
			}
			if(count==2) {
				 this.flag=true;
			     int score=p.getJoueur().get(ordre).getScore();
			     p.getJoueur().get(ordre).setScore(score+3);
			}else {
				int score=p.getJoueur().get(ordre).getScore();
				p.getJoueur().get(ordre).setScore(score-5);
				
				
			}
			}
		
	}

	/**
	 * Méthode qui retourne un booléen qui permet de vérifier que les conditions soient respectées
	 * @return flag
	 */
	public boolean isFlagRight() {
		return flagRight;
	}

	/**
	 * Méthode de type setter qui change le flag
	 * @param flag
	 */
	public void setFlagRight(boolean flagRight) {
		this.flagRight = flagRight;
	}

	/**
	 * Méthode qui retourne un booléen qui permet de vérifier que les conditions soient respectées
	 * @return flag
	 */
	public boolean isFlagLeft() {
		return flagLeft;
	}

	/**
	 * Méthode de type setter qui change le flag
	 * @param flag
	 */
	public void setFlagLeft(boolean flagLeft) {
		this.flagLeft = flagLeft;
	}

	/**
	 * Méthode qui retourne un booléen qui permet de vérifier que les conditions soient respectées
	 * @return flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * Méthode de type setter qui change le flag
	 * @param flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}