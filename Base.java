public class Base extends CaseHexa {

	Joueur monJoueur;

	public Base(int posX, int posY, Joueur monJoueur) {
		super(posX, posY);
		this.monJoueur = monJoueur;
		this.poussable = false;
	}

	public void gagnePoints(Cristal cristal) {
		this.monJoueur.gagnePoints(cristal.getValeur());
	}

	public int getPosX() {
 		return this.posX;
 	}

 	public int getPosY() {
 		return this.posY;
 	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posX = posY;
	}
}
