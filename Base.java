public class Base extends CaseHexa {

	Joueur monJoueur;

	public Base(int posX, int posY, Joueur monJoueur) {
		super(posX, posY);
		this.monJoueur = monJoueur;
		this.poussable = false;
	}

	public Joueur getJoueur() {
		return this.monJoueur;
	}
}
