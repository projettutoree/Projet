public class Base extends CaseHexa {

	Joueur monJoueur;

	public Base(int posX, int posY, Joueur monJoueur) {
		super(posX, posY);
		this.monJoueur = monJoueur;
	}

	public void gagnePoints(Cristal cristal) {
		this.monJoueur.gagnePoints(cristal.getValeur());
	}
}
