public abstract class CaseHexa {

	protected int posX;
	protected int posY;
	protected boolean poussable;

	protected CaseHexa(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.poussable = false;
	}

	public boolean estPoussable() {
		return this.poussable;
	}

	public void avancer(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
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
		this.posY = posY;
	}

	public Joueur getJoueur() {return null;}

	public String toString() {
		return this.getClass().getName() + ":(" + posX + "," + posY + ")";
	}
}
