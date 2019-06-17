public class Cristal extends CaseHexa {
	private int valeur;
	private String couleur;

	public Cristal(int posX, int posY, int valeur, String couleur) {
		super(posX, posY);
		this.valeur = valeur;
		this.couleur = couleur;
		this.poussable = true;
	}

	public int getValeur() {
		return this.valeur;
	}

	public String getCouleur() {
		return this.couleur;
	}
}
