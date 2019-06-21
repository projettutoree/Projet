import iut.algo.*;
import java.util.*;

public class Controleur {

	private Plateau metier;
	private IHM ihm;
	private FramePrincipale ihm2;

	public Controleur() {
		this.ihm = new IHM(this);
		this.metier = new Plateau(this);
		this.ihm2 = new FramePrincipale(this);
	}

	public int lireInt() {
		return this.ihm.lireInt();
	}

	public int getNbJoueur() {
		return this.metier.getNbJoueur();
	}

	public String lireClavier() {
		return this.ihm.lireClavier();
	}

	public void afficherPlateau() {
		this.ihm.afficherPlateau(this.metier.getCases(), this.metier.getTailleMax());
	}

	public ArrayList<CaseHexa> getCases() {
		return this.metier.getCases();
	}

	public ArrayList<Joueur> getJoueurs() {
		return this.metier.getJoueurs();
	}

	public Plateau getPlateau() {
		return this.metier;
	}

	public int getTailleMax() {
		return this.metier.getTailleMax();
	}

	public void maj() {
		this.ihm2.maj();
	}

	public ArrayList<Cristal> getAlReserveCristaux() {
		return this.metier.getAlReserveCristaux();
	}

	public Joueur getJoueurCourant() {
		return this.metier.getJoueurCourant();
	}

	public void distribuerInformation(String choix, int idRobot, String ordreString, int idOrdre1, int idOrdre2) {
		this.metier.tourJoueur(choix, idRobot, ordreString, idOrdre1, idOrdre2);
	}

	public IHM getIhm() {
		return this.ihm;
	}

	public void pause() {
		this.metier.pause();
	}

	public void play() {
		this.metier.play();
	}

	public void chargerEtatScenar() {
		// Faut tout reset
	}

	/*
	 * public static void main(String[] args) { Controleur c = new Controleur();
	 * Plateau p = c.getPlateau(); Joueur j = p.getJoueurs().get(0);
	 * j.ajouterOrdre(0,0,0);
	 * 
	 * j.ajouterOrdre(0, "AvancerX2", 0); j.ajouterOrdre(0, "Avancer", 1);
	 * j.ajouterOrdre(0, "Zap", 2); p.executerInstructions();
	 * 
	 * c.afficherPlateau(); }
	 */
	public static void main(String[] args) {
		// DANS L'IHM CUI
		// DEMANDER LE NB DE JOUEURS
		// SI C'EST EN SCENAR
		// SI C'EST EN SCENAR, LE NOM DU SCENAR

		Controleur c = new Controleur();
		Plateau p = c.getPlateau();
		Joueur j = p.getJoueurs().get(0);

		// c.afficherPlateau();
		if (!c.getIhm().demanderScenar())
			p.jeu();
		else {

			p.chargerScenar("test2Joueurs.data");
			c.afficherPlateau();
			p.jeu();
			c.maj();
			c.afficherPlateau();
		}

		// System.out.println(j);
		/*
		 * j.ajouterOrdre(0, "TournerSensAntiHoraire", 0); j.ajouterOrdre(0, "Avancer",
		 * 1); j.ajouterOrdre(0, "Charger", 2); p.executerInstructions();
		 * c.afficherPlateau(); j.retirerOrdre(0,0); j.retirerOrdre(0,1);
		 * j.retirerOrdre(0,2); j.ajouterOrdre(0, "TournerSensHoraire", 0);
		 * j.ajouterOrdre(0, "TournerSensHoraire", 1); j.ajouterOrdre(0, "Avancer", 2);
		 * p.executerInstructions(); c.afficherPlateau();
		 * System.out.println(p.getJoueurs().get(0));
		 * System.out.println(p.getJoueurs().get(2)); j.retirerOrdre(0,0);
		 * j.retirerOrdre(0,1); j.retirerOrdre(0,2); j.ajouterOrdre(0,
		 * "TournerSensHoraire", 0); j.ajouterOrdre(0, "Deposer", 1);
		 * p.executerInstructions(); c.afficherPlateau();
		 * System.out.println(p.getJoueurs().get(0));
		 * System.out.println(p.getJoueurs().get(2));
		 */
	}

}
