import java.util.ArrayList;

public class Joueur {

	private static String couleurs = {"ROUGE", "JAUNE", "VERT",
	                                  "BLEU", "VIOLET", "ROSE"};

	private String nom;
	private int points;
	private boolean hasModifieProg;
	private ArrayList<Robot> alRobot;

	public Joueur(String nom) {
		this.nom = nom;

		this.points = 0;
		this.hasModifieProg = false;
		this.alRobot = new ArrayList<Robot>();
	}

	public void permutterOrdre(int idRobot, int ordre1, int ordre2) {

	}

	// Méthode appelée une seule fois, lors de l'initialisation
	// de la partie.
	public void setRobots(Robot robot) {
		this.alRobot.add(robot);
	}

	public void gagnePoints(int points) {
		this.points += points;
	}

	public Robot[] getRobots() {
		return this.tabRobot;
	}

	public int getPoints() {
		return this.points;
	}
}
