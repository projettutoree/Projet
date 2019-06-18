public class Cristal extends CaseHexa
{
	private int valeur;
	private String couleur;

	private final String[] tabCouleur = { "Bleu", "Vert", "Rouge" };

	public Cristal(int posX, int posY, int valeur)
	{
		super(posX, posY);
		this.valeur = valeur;
		this.setCouleur();
		this.poussable = true;
	}

	public int getValeur() {
		return this.valeur;
	}

	public void setCouleur() {
		this.couleur = this.tabCouleur[this.valeur-2];
	}

	public String getCouleur() {
		return this.couleur;
	}

    public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}
}
