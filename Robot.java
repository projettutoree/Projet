public class Robot extends CaseHexa {

	private int numero;
	private String[] instructions;

	private int dir;

	public Robot(int posX, int posY, int numero) {
		this.numero = numero;
		this.posX = posX;
		this.posY = posY;

	}

	public void deplacer(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public int getNumero() {
		return this.numero;
	}

	public void tourner(int sens) {
		// %6 car modulo de Case hexagonale
		this.dir = (this.dir + sens)%6;
	}
	
	public String[] getInstrution() {
		return this.instructions;
	}
	public void setInstruction(String[] instruction) {
		this.instructions = instructions;
	}
}