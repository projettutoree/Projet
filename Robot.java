public class Robot extends CaseHexa {

	private static final int NB_ORDRES = 3;

	private int dir;
	private Joueur monJoueur;
	private Ordre[] ordres;
	private Cristal cristal;

	public Robot(int posX, int posY, int dir, Joueur joueur) {
		super(posX, posY);
		this.dir = dir;
		this.monJoueur = joueur;
		this.poussable = true;
		this.ordres = new Ordre[Robot.NB_ORDRES];
	}

	public void avancer(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public void tourner(int sens) {
		// %6 car modulo de Case hexagonale
		this.dir = (this.dir + sens + 6) % 6;
	}

	// public void setOrdres(String[] ordres) {
	// this.ordres = ordres;
	// }

	// Retourne un String ordre au cas où on permutte deux ordres
	public Ordre setOrdre(int idOrdre, Ordre ordre) {
		Ordre ancienOrdre = this.ordres[idOrdre];
		this.ordres[idOrdre] = ordre;
		return ancienOrdre;
	}

	/**
	 * Retire et renvoi l'ordre de l'emplacement s'il n'est pas null, et le place à
	 * null Renvoie null sinon
	 */
	public Ordre retirerOrdre(int idOrdre) {
		if (this.ordres[idOrdre] != null) {
			Ordre retour = this.ordres[idOrdre];
			this.ordres[idOrdre] = null;
			return retour;
		} else
			return null;
	}

	public Ordre[] redemarrer() {
		Ordre[] ordresRetour = new Ordre[Robot.NB_ORDRES];
		for (int i = 0; i < Robot.NB_ORDRES; i++) {
			ordresRetour[i] = ordres[i];
			ordres[i] = null;
		}
		return ordresRetour;
	}

	public void charger(Cristal cristal) {
		this.cristal = cristal;
	}

	public void deposer(Base b) {
		if (b != null) {
			b.recoitCristal(this.cristal);
		}
		this.cristal = null;
	}

	public void deposer(Robot r) {
		if (r != null) {
			r.charger(this.cristal);
			this.cristal = null;
		}
	}

	/***********************/
	/* Accesseurs */
	/***********************/

	public int getDir() {
		return this.dir;
	}

	public Ordre[] getOrdres() {
		return this.ordres;
	}

	public Ordre getOrdre(int idOrdre) {
		return this.ordres[idOrdre];
	}

	public boolean estCharge() {
		return this.cristal != null;
	}

	public Cristal getCristal() {
		return this.cristal;
	}

	public Joueur getJoueur() {
		return this.monJoueur;
	}

	public String toString() {
		String s = "R";
		/*
		 * s += this.getClass().getName() + ":(" + posX + ";" + posY + ";" + dir + ")";
		 * s += "\n"; if (ordres[0] != null) s += ordres[0].getClass().getName() + " ";
		 * if (ordres[1] != null) s += ordres[1].getClass().getName() + " "; if
		 * (ordres[2] != null) s += ordres[2].getClass().getName();
		 */
		return s;
	}
}
