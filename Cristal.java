public class Cristal extends CaseHexa {
	private int valeur;

	public Cristal(int posX, int posY, int valeur) {
		super(posX, posY);
		this.valeur = valeur;
		this.poussable = true;
	}

	public int getValeur() {
		return this.valeur;
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
