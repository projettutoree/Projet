public class Robot extends CaseHexa {
	private String[] instructions;

	private int dir;

	private Cristal cristal;

	public Robot(int posX, int posY, int dir) {
		super(posX, posY);
		this.dir = dir;
		this.poussable = true;

		this.cristal = null;
		this.instructions = null;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public void tourner(int sens) {
		// %6 car modulo de Case hexagonale
		this.dir = (this.dir + sens) % 6;
	}

	public int getDir() {
		return this.dir;
	}

	public String[] getInstructions() {
		return this.instructions;
	}

	public void setInstruction(String[] instruction) {
		this.instructions = instructions;
	}

	public void charger(Cristal cristal) {
		this.cristal = cristal;
	}

	public boolean estCharge() {
		return this.cristal != null;
	}

	public Cristal getCristal() {
		return this.cristal;
	}

	public void decharger(Base base) {
		this.cristal = null;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posX = posY;
	}

	public String toString() {
		String s = "";
		s += "(" + posX + ";" + posY + ";" + dir + ")";
		return s;
	}
}
