public class Cristal extends caseHexa {
	private int    valeur;
	private String couleur;

	public Cristal(int valeur, String couleur) {
		this.valeur  = valeur;
		this.couleur = couleur;
	}

	public int    getValeur()  { return this.valeur;  }
	public String getCouleur() { return this.couleur; }
}
