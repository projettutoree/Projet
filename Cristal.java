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
}
