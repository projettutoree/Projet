public class Joueur {

	private static String couleurs = {"ROUGE", "JAUNE", "VERT",
	                                  "BLEU", "VIOLET", "ROSE"};

	private String nom;
	private int points;
	private boolean hasModifieProg;
	private Robot[] tabRobot;

	public Joueur(String nom) {
		this.nom = nom;

		this.points = 0;
		this.hasModifieProg = false;
		this.tabRobot = new Robot[2];
	}

	// Méthode appelée une seule fois, lors de l'initialisation
	// de la partie.
	public void setRobots(Robot robot1, Robot robot2) {
		this.tabRobot[0] = robot1;
		this.tabRobot[1] = robot2;
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
