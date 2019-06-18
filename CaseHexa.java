public abstract class CaseHexa  {

	protected int     posX;
	protected int     posY;
	protected boolean poussable;

	protected CaseHexa(int posX, int posY) {
		this.posX      = posX;
		this.posY      = posY;
		this.poussable = false;
	}

	public boolean estPoussable() {
		return this.poussable;
	}

	public void avancer(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public abstract int getPosX();
	public abstract int getPosY();

	public abstract void setPosX(int posX);
	public abstract void setPosY(int posY);
}
