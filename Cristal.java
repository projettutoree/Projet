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

	public String toString() {
		String s = "";
		if(valeur == 2) s += "Bleu";
		if(valeur == 3) s += "Vert";
		if(valeur == 4) s += "Violet";

		return s;
	}
}
