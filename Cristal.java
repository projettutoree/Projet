public class Cristal extends CaseHexa {
	private int valeur;
	private String couleur;

	public Cristal(int posX, int posY, int valeur) {
		super(posX, posY);
		this.valeur = valeur;
		this.poussable = true;
	}

	public int getValeur() {
		return this.valeur;
	}

	public String getCouleur() {
		return this.couleur;
	}
}
