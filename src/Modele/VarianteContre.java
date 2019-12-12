package Modele;

/**
 * Classe VarianteContre qui impl�mente l'interface NewRule
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
	 * M�thode qui retourne un bool�en qui permet de v�rifier que les conditions soient respect�es
	 * @return flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * M�thode de type setter qui change le flag
	 * @param flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	/**
	 * Constructeur Surcharg� de la classe VarianteContre
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
	 * M�thode qui retourne un bool�en qui permet de v�rifier que les conditions soient respect�es
	 * @return
	 */
	public boolean isFlagRight() {
		return flagRight;
	}

	/**
	 * M�thode de type setter qui change le flag
	 * @param flagRight
	 */
	public void setFlagRight(boolean flagRight) {
		this.flagRight = flagRight;
	}

	/**
	 * M�thode qui retourne un bool�en qui permet de v�rifier que les conditions soient respect�es
	 * @return
	 */
	public boolean isFlagLeft() {
		return flagLeft;
	}

	/**
	 * M�thode de type setter qui change le flag
	 * @param flagLeft
	 */
	public void setFlagLeft(boolean flagLeft) {
		this.flagLeft = flagLeft;
	}

	

}

