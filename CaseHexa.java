public abstract class CaseHexa  {

	protected int     posX;
	protected int     posY;
	protected boolean poussable;

	protected CaseHexa(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public boolean getPoussable() {
		return this.poussable;
	}

}
