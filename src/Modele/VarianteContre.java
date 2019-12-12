package Modele;

/**
 * Classe VarianteContre qui implémente l'interface NewRule
 * @author grego
 * @see Variante
 *
 */
public class VarianteContre implements NewRule {
	private boolean flagRight;
    private boolean flagLeft;
    private boolean flag;
    
    /**
     * Constructeur de la classe VarianteContre
     */
	public VarianteContre() {
	   this.flagRight= false;
	   this.flagLeft=false;
	   this.flag=false;
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

	@Override
	/**
	 * Constructeur Surchargé de la classe VarianteContre
	 */
	public void NewJouer(Partie p,int ordre,int[] choix,int nb) { 
		    this.flagRight= false;
		    this.flagLeft=false;
		    this.flag=false;
			int n1 = choix[0];
				int n2=choix[1];
				switch(n2) {
				case 1:
					if(p.getJoueur().get(n1).getGauche().getName()==PropsType.Rabbit)this.flagLeft=true;
					break;
				case 2:
					if(p.getJoueur().get(n1).getGauche().getName()==PropsType.The_other_rabbit)this.flagLeft=true;	
					break;	
				case 3:
					if(p.getJoueur().get(n1).getGauche().getName()==PropsType.Hat)this.flagLeft=true;	
					break;	
				case 4:
					if(p.getJoueur().get(n1).getGauche().getName()==PropsType.Carrots)this.flagLeft=true;	
					break;	
				case 5:
					if(p.getJoueur().get(n1).getGauche().getName()==PropsType.Lettuce)this.flagLeft=true;	
					break;	
				default:
					break;
				}
				int n3=choix[2];
				switch(n3) {
				case 1:
					if(p.getJoueur().get(n1).getDroite().getName()==PropsType.Rabbit)this.flagRight=true;
					break;
				case 2:
					if(p.getJoueur().get(n1).getDroite().getName()==PropsType.The_other_rabbit)this.flagRight=true;
					break;	
				case 3:
					if(p.getJoueur().get(n1).getDroite().getName()==PropsType.Hat)this.flagRight=true;	
					break;	
				case 4:
					if(p.getJoueur().get(n1).getDroite().getName()==PropsType.Carrots)this.flagRight=true;break;
				case 5:
					if(p.getJoueur().get(n1).getDroite().getName()==PropsType.Lettuce)this.flagRight=true;	
					break;	
				default:
					break;
				}
				
				if(this.flagLeft&&this.flagRight) {
					this.flag=true;
					int score=p.getJoueur().get(ordre).getScore();
				     p.getJoueur().get(ordre).setScore(score+3);
				}else {
					p.getJoueur().get(ordre).setFaceUp(nb);
					this.flag=false;
				}
           
			
		}

	/**
	 * Méthode qui retourne un booléen qui permet de vérifier que les conditions soient respectées
	 * @return
	 */
	public boolean isFlagRight() {
		return flagRight;
	}

	/**
	 * Méthode de type setter qui change le flag
	 * @param flagRight
	 */
	public void setFlagRight(boolean flagRight) {
		this.flagRight = flagRight;
	}

	/**
	 * Méthode qui retourne un booléen qui permet de vérifier que les conditions soient respectées
	 * @return
	 */
	public boolean isFlagLeft() {
		return flagLeft;
	}

	/**
	 * Méthode de type setter qui change le flag
	 * @param flagLeft
	 */
	public void setFlagLeft(boolean flagLeft) {
		this.flagLeft = flagLeft;
	}

	

}

