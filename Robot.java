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

	public void avancer(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
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

	public String[] getInstrution() {
		return this.instructions;
	}

	public void setInstruction(String[] instruction) {
		this.instructions = instructions;
	}

	public void charger(Cristal cristal) {
		this.cristal = cristal;
	}

	public Cristal getCristal() {
		return this.cristal;
	}

	public void decharger() {
		this.cristal = null;
	}

	public String toString() {
		String s = "";
		s += "(" + posX + ";" + posY + ";" + dir + ")";
		return s;
	}
}
