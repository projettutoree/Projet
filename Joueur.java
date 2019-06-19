import java.util.ArrayList;

public class Joueur {

	private static String[] couleurs     = { "ROUGE", "JAUNE", "VERT",
	                                       "BLEU", "VIOLET", "ROSE"};

	public  static String[] ordresString = { "AVANCER X1", "AVANCER X2" ,
                                               "TOURNER GAUCHE", "TOURNER DROITE",
						           "CHARGER", "DEPOSER", "ZAP"};

	private int identifiant;
	private int points;
	private boolean jokerDouble;
	private boolean hasModifieProg;
	private int[] etatOrdres;
	private ArrayList<Robot> alRobot;

	public Joueur(int identifiant) {
		this.identifiant = identifiant;

		this.points = 0;
		this.hasModifieProg = false;
		this.alRobot = new ArrayList<Robot>();

		// Les ID du tableau ordresString correspond aux ID du tableau
		// etatOrdres. Il a y donc 2 ZAP, 2 AVANCER X1 etc...
		this.etatOrdres = new int[]{2,1,3,3,2,2,2};

	}

	/**
	  * Phase d'ajout de l'ordre. On retire un ordre et le place sur le robot
	  * si possible. Si un ordre était déjà présent à l'emplacement indiqué,
	  * on redonne la tuile ordre qui était initialement implémentée sur le
	  * robot au joueur. Retourne true
	  * Sinon, retourne false;
	  */

	public boolean ajouterOrdre(int idRobot, int emplacementOrdre, int idOrdre) {
		// Phase d'ajout de l'ordre
		if(this.etatOrdres[emplacementOrdre] <= 0) {
			return false;
		}
		else {
			this.etatOrdres[emplacementOrdre]--;
			String ordreRetour = this.alRobot.get(idRobot).setOrdre(idOrdre, Joueur.ordresString[emplacementOrdre]);

			// On remet la tuile ordre au joueur s'il y en avait une
			if(ordreRetour != null) {
				this.etatOrdres[getEmplacementOrdre(ordreRetour)]++;
			}
			return true;
		}
	}

	/**
	  * Permutte deux tuiles d'ordre sur un robot s'il en existe au moins
	  * une sur les deux positions données. Retoune true
	  * Sinon, return false
	  */
	public boolean permutterOrdre(int idRobot, int idOrdre1, int idOrdre2) {
		String ordre1Temp = this.alRobot.get(idRobot).getOrdre(idOrdre1);
		String ordre2Temp = this.alRobot.get(idRobot).getOrdre(idOrdre2);
		if(ordre1Temp != null || ordre2Temp != null) {
			this.alRobot.get(idRobot).setOrdre(idOrdre1, ordre2Temp);
			this.alRobot.get(idRobot).setOrdre(idOrdre2, ordre1Temp);
			return true;
		}
		else
			return false;
	}

	/**
	  * Active la méthode retirerOrdre de Robot, et récupère un String
	  * Si celui-ci n'est pas null, il augmente son nombre d'ordres correspondant
	  * Retourne true
	  * Sinon, ne fait rien et retourne false
	  */
	public boolean retirerOrdre(int idRobot, int idOrdre) {
		String ordreTemp = this.alRobot.get(idRobot).retirerOrdre(idOrdre);
		if(ordreTemp != null) {
			this.etatOrdres[getEmplacementOrdre(ordreTemp)]++;
			return true;
		}
		return false;
	}

	public boolean redemarrer(int idRobot) {
		return this.alRobot.get(idRobot).redemarrer();
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

	/**
	  * Méthode afin de retrouver l'emplacement sous forme de int
	  * d'un ordre lorsque l'on connait son nom
	  */
	private int getEmplacementOrdre(String nomOrdre) {
		for(int i = 0; i < Joueur.ordresString.length; i++) {
			if(Joueur.ordresString[i].equals(nomOrdre))
				return i;
		}
		return -1;
	}
}
