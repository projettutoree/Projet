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

	public void afficherPlateau() {
		this.ihm.afficherPlateau(this.metier.getCases(), this.metier.getTailleMax());
	}

	public Plateau getPlateau() {return this.metier;}

	public static void main(String[] args) {
		Controleur c =  new Controleur();
		Plateau p = c.getPlateau();
		Joueur j = p.getJoueurs().get(0);
		//j.ajouterOrdre(0,0,0);
		/*j.ajouterOrdre(0, "AvancerX2", 0);
		j.ajouterOrdre(0, "Avancer", 1);
		j.ajouterOrdre(0, "Zap", 2);
		p.executerInstructions();*/
		c.afficherPlateau();
		System.out.println(p);
	}

}
