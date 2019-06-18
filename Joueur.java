import java.util.ArrayList;

public class Joueur {

	private static String[] couleurs = {"ROUGE", "JAUNE", "VERT", "BLEU", "VIOLET", "ROSE"};

	private int identifiant;
	private int points;
	private boolean hasModifieProg;
	private ArrayList<Robot> alRobot;

	public Joueur(int identifiant) {
		this.identifiant = identifiant;

		this.points = 0;
		this.hasModifieProg = false;
		this.alRobot = new ArrayList<Robot>();
	}

	// Méthode appelée une seule fois, lors de l'initialisation
	// de la partie.
	public void addRobot(Robot r) {
		this.alRobot.add(r);
	}

	public void gagnePoints(int points) {
		this.points += points;
	}

	public ArrayList<Robot> getRobots() {
		return this.alRobot;
	}

	public int getPoints() {
		return this.points;
	}
}
