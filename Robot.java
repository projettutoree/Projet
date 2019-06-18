public class Robot extends CaseHexa {

	private int dir;
	private String[] ordres;
	private Cristal cristal;

	public Robot(int posX, int posY, int dir) {
		super(posX, posY);
		this.dir = dir;
		this.poussable = true;

		this.cristal = null;
		this.ordres = null;
	}

	public void avancer(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public void tourner(int sens) {
		// %6 car modulo de Case hexagonale
		this.dir = (this.dir + sens) % 6;
	}

	public void setOrdres(String[] ordres) {
		this.ordres = ordres;
	}

	// Retourne un String ordre au cas où on permutte deux ordres
	public void setOrdre(int idOrdre, String ordreString) {
		String ancienOrdre = this.ordres[idOrdre];
		this.ordres[idOrdre] = ordreString;
		return ancienOrdre;
	}

	/**
	  * Retire et renvoi l'ordre de l'emplacement s'il n'est pas null,
	  * et le place à null
	  * Renvoie null sinon
	  */
	public String retirerOrdre(int idOrdre) {
		if(this.ordres[idOrdre] != null) {
			String retour = this.ordres[idOrdre];
			this.ordres[idOrdre] = null;
			return retour;
		}
		else
			return null;
	}

	public boolean redemarrer() {
		for(String o : ordres) {
			o = null;
		}
		return true;
	}

	public void charger(Cristal cristal) {
		this.cristal = cristal;
	}

	public void decharger() {
		this.cristal = null;
	}


	/***********************/
	/*      Accesseurs     */
	/***********************/

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public int getDir() {
		return this.dir;
	}

	public String[] getOrdres() {
		return this.ordres;
	}

	public String getOrdre(int idOrdre) {
		return this.ordres[idOrdre];
	}

	public Cristal getCristal() {
		return this.cristal;
	}

	public String toString() {
		String s = "";
		s += "(" + posX + ";" + posY + ";" + dir + ")";
		return s;
	}
}
