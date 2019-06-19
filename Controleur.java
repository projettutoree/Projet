import iut.algo.*;
import java.util.*;

public class Controleur {

	private Plateau metier;
	private IHM ihm;

	public Controleur() {
		this.ihm = new IHM(this);
		this.metier = new Plateau(this);
	}

	public int lireInt() {
		return this.ihm.lireInt();
	}

	public String lireClavier() {
		return this.ihm.lireClavier();
	}

	public void afficherPlateau(ArrayList<CaseHexa> alCase, Robot rob, int tailleMax) {
		this.ihm.afficherPlateau(alCase, rob, tailleMax);
	}

	public static void main(String[] args) {
		new Controleur();
	}

}