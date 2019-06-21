import java.util.ArrayList;

public class Joueur implements Comparable<Joueur> {

	// Les ID du tableau ordresString correspond aux ID du tableau
	// ORDRES_STRING. Il a y donc 2 Avancer, 1 AvancerX2 etc...
	private static final int[] ORDRES_MAX = { 2, 1, 3, 3, 2, 2, 2 };

	public static final String[] ORDRES_STRING = { "Avancer", "AvancerX2", "TournerSensAntiHoraire",
			"TournerSensHoraire", "Charger", "Deposer", "Zap" };

	private static final String[] COULEURS = { "Rouge", "Jaune", "Vert", "Bleu", "Violet", "Rose" };

	private int identifiant;
	private int points;
	// private boolean jokerDouble;
	private boolean hasModifieProg;
	private Base base;
	private ArrayList<Robot> alRobot;
	private ArrayList<Ordre> alOrdre;

	private String couleur;

	public Joueur(int identifiant) {
		this.identifiant = identifiant;
		this.points = 0;
		this.hasModifieProg = false;
		this.alRobot = new ArrayList<Robot>();
		this.alOrdre = new ArrayList<Ordre>();
		this.couleur = Joueur.COULEURS[this.identifiant - 1];

		this.initOrdres();
	}

	public String getCouleur() {
		return this.couleur;
	}

	/**
	 * Phase d'ajout de l'ordre. On retire un ordre et le place sur le robot si
	 * possible. Si un ordre était déjà présent à l'emplacement indiqué, on redonne
	 * la tuile ordre qui était initialement implémentée sur le robot au joueur.
	 * Retourne true Sinon, retourne false;
	 */
	public boolean ajouterOrdre(int idRobot, String ordre, int idOrdre) {
		// Phase d'ajout de l'ordre
		for (Ordre o : alOrdre) {
			if (o.getClass().getName().equals(ordre)) {
				Ordre ordreRetour = this.alRobot.get(idRobot).setOrdre(idOrdre, o);
				alOrdre.remove(o);

				// On remet la tuile ordre au joueur s'il y en avait une dans le robot
				if (ordreRetour != null)
					alOrdre.add(ordreRetour);

				return true;
			}
		}
		return false;
	}

	/**
	 * Permute deux tuiles d'ordre sur un robot s'il en existe au moins une sur les
	 * deux positions données et que les deux positions sont différentes, retourne true
	 * Sinon, return false
	 */
	public boolean permuterOrdre(int idRobot, int idOrdre1, int idOrdre2) {
		Ordre ordre1Temp = this.alRobot.get(idRobot).getOrdre(idOrdre1);
		Ordre ordre2Temp = this.alRobot.get(idRobot).getOrdre(idOrdre2);
		if ((ordre1Temp != null || ordre2Temp != null) &&
		     idOrdre1 != idOrdre2) {
			this.alRobot.get(idRobot).setOrdre(idOrdre1, ordre2Temp);
			this.alRobot.get(idRobot).setOrdre(idOrdre2, ordre1Temp);
			return true;
		} else
			return false;
	}

	/**
	 * Active la méthode retirerOrdre de Robot, et récupère un String Si celui-ci
	 * n'est pas null, il augmente son nombre d'ordres correspondant Retourne true
	 * Sinon, ne fait rien et retourne false
	 */
	public boolean retirerOrdre(int idRobot, int idOrdre) {
		Ordre ordreTemp = this.alRobot.get(idRobot).retirerOrdre(idOrdre);
		if (ordreTemp != null) {
			alOrdre.add(ordreTemp);
			return true;
		}
		return false;
	}

	/**
	 * Retire toutes les tuiles ordres du robot, et les replace dans le stock de
	 * tuiles du joueur. Return true s'il y avait au moins une tuile présente dans
	 * le robot. Return false sinon
	 */
	public boolean redemarrer(int idRobot) {
		Boolean aRedemarre = false;

		for (Ordre o : this.alRobot.get(idRobot).redemarrer()) {
			if (o != null) {
				alOrdre.add(o);
				aRedemarre = true;
			}
		}
		return aRedemarre;
	}

	public void initOrdres() {
		String s;
		for (int i = 0; i < Joueur.ORDRES_MAX.length; i++) {
			for (int j = 0; j < Joueur.ORDRES_MAX[i]; j++) {
				s = Joueur.ORDRES_STRING[i];
				switch (s) {
				case "Avancer":
					this.alOrdre.add(new Avancer());
					break;

				case "AvancerX2":
					this.alOrdre.add(new AvancerX2());
					break;

				case "TournerSensAntiHoraire":
					this.alOrdre.add(new TournerSensAntiHoraire());
					break;

				case "TournerSensHoraire":
					this.alOrdre.add(new TournerSensHoraire());
					break;

				case "Charger":
					this.alOrdre.add(new Charger());
					break;

				case "Deposer":
					this.alOrdre.add(new Deposer());
					break;

				case "Zap":
					this.alOrdre.add(new Zap());
					break;
				}
			}
		}
	}

	public void setBase(Base base) {
		this.base = base;
	}

	// Méthode appelée une seule fois, lors de l'initialisation
	// de la partie.
	public void addRobot(Robot r) {
		this.alRobot.add(r);
	}

	public void gagnePoint(int points) {
		this.points += points;
	}

	public int calculerPoints() {
		int ptsTotaux = this.points;
		for (Robot r : alRobot) {
			if (r.getCristal() != null)
				ptsTotaux += r.getCristal().getValeur();
		}
		return ptsTotaux;
	}

	public int getId() {
		return this.identifiant;
	}

	public ArrayList<Robot> getRobots() {
		return this.alRobot;
	}

	// Ne tient pas compte des robots qui possède un cristal
	public int getPoints() {
		return this.points;
	}

	public int getTypeCristaux() {
		return this.base.calculerTypeCristaux();
	}

	public int getPointsParCristaux(int valeur) {
		int points = 0;
		for (Cristal c : this.base.getCristaux()) {
			if (c.getValeur() == valeur)
				points += valeur;
		}
		return points;
	}
	public ArrayList<Ordre> getAlOrdre(){
		return this.alOrdre;
	}

	// Négatif = moins de points que l'autreJoueur
	// Positif = plus de points que l'autreJoueur
	public int compareTo(Joueur autreJoueur) {
		return this.calculerPoints() - autreJoueur.calculerPoints();
	}
}
