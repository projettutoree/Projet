import iut.algo.Clavier;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class Plateau {
	private static final int REF_POINT = 13;
	private ArrayList<CaseHexa> alCase;
	private ArrayList<Joueur> alJoueur;
	private ArrayList<Cristal> alReserveCristaux;
	private ArrayList<String> scenar;
	private Controleur ctrl;
	private int tailleMax;
	private int toursRestants;
	private int tourActuel;
	private ArrayList<Joueur> alGagnants;
	private int nbJCourant = 0;
	private int nbRCourant = 0;

	public Plateau(Controleur controleur) {
		this.ctrl = controleur;
		this.toursRestants = 4;
		this.tourActuel = 0;
		this.alGagnants = null;
		this.alCase = new ArrayList();
		this.alJoueur = new ArrayList();
		this.alReserveCristaux = new ArrayList();
		this.init(Clavier.lire_int());
		Ordre.setTailleMax((int) this.tailleMax);
		Ordre.setCase(this.alCase);
	}

	public int getNbJoueur() {
		return this.alJoueur.size();
	}

	public void jeu() {
		while (!this.partieFinie()) {
			this.executerInstructions();
			if (this.alReserveCristaux.isEmpty()) {
				--this.toursRestants;
			}
			this.tourActuel = this.tourActuel++ % this.alJoueur.size();
		}
	}

	public int getTailleMax() {
		return this.tailleMax;
	}

	public ArrayList<CaseHexa> getCases() {
		return this.alCase;
	}

	public void init(int nbJoueur) {
		try {
			Scanner sc = new Scanner(new FileReader("Regles/" + nbJoueur + "joueurs.txt"));
			String ligne = sc.nextLine();
			this.tailleMax = Integer.parseInt(ligne.substring(ligne.indexOf(":") + 1));
			while (sc.hasNextLine()) {
				ligne = sc.nextLine();
				String[] information = ligne.split(":");
				// si l'identifient est un chiffre
				if (information[0].matches("[1-6]")) {

					char identifiant = information[0].charAt(0);
					Joueur j = new Joueur(Integer.parseInt("" + identifiant));
					this.alJoueur.add(j);

					for (int i = 1; i < information.length; i++) {

						identifiant = information[i].charAt(0);
						information[i] = information[i].substring(1);
						if (identifiant == 'B') {
							Base base = new Base(
									Integer.parseInt(information[i].substring(0, information[i].indexOf(","))),
									Integer.parseInt(information[i].substring(information[i].indexOf(",") + 1)), j);
							this.alCase.add(base);
						} else if (identifiant == 'R') {
							int index = information[i].indexOf(",");
							int index2 = information[i].indexOf(",", index + 1);
							Robot robot = new Robot(Integer.parseInt(information[i].substring(0, index)),
									Integer.parseInt(information[i].substring(index + 1, index2)),
									Integer.parseInt(information[i].substring(index2 + 1)), j);
							this.alCase.add(robot);
							j.addRobot(robot);
						}
					}
				}
				if (information[0].matches("C")) {
					for (int i = 1; i < information.length; i++) {
						int index = information[i].indexOf(",");
						int index2 = information[i].indexOf(",", index + 1);
						Cristal cristal = new Cristal(Integer.parseInt(information[i].substring(0, index)),
								Integer.parseInt(information[i].substring(index + 1, index2)),
								Integer.parseInt(information[i].substring(index2 + 1)));
						this.alCase.add(cristal);
					}
				}
				if (information[0].matches("R")) {
					for (int i = 1; i < information.length; i++) {
						Cristal cristal = new Cristal(-1, -1, Integer.parseInt(information[i].substring(0, 1)));
						this.alReserveCristaux.add(cristal);
					}
				}
			}
			sc.close();

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	public int getJoueurCourant() {
		return this.nbJCourant;
	}

	public void changerJoueur() {
		this.nbJCourant = this.nbJCourant++ % this.alJoueur.size();
	}

	public boolean traduction(String[][] informations) {
		Joueur joueur = this.alJoueur.get(this.tourActuel);
		boolean reset = true;
		boolean noModif = true;
		int cptAncien = 0;
		int cptNouveau = 0;

		// Première boucle pour détecter un éventuel redémarrage ou un tour
		// sans aucune modification
		for (int i = 0; i < informations.length; i++) {
			for (int j = 1; i < informations[i].length; j++) {
				if (informations[i][j] != null) {
					reset = false;
					cptNouveau++;
					if (!informations[i][j].equals(joueur.getRobots().get(i).getOrdre(j - 1).getClass().getName())) {
						noModif = false;
					}
				}
				if (joueur.getRobots().get(i).getOrdre(j - 1).getClass().getName() != null) {
					cptAncien++;
				}
			}
			if (reset) {
				return joueur.redemarrer(i);
			}
			if (noModif)
				return true;
		}

		// Deuxième boucle pour détecter un éventuel retrait ou ajout d'ordre
		// L'ajout se fait sur une case vide du robot
		for (int i = 0; i < informations.length; i++) {
			for (int j = 0; j < informations.length; j++) {
				if (informations[i][j] == null
						&& joueur.getRobots().get(i).getOrdre(j - 1).getClass().getName() != null) {
					return joueur.retirerOrdre(i, j - 1);
				}
				if (informations[i][j] != null
						&& joueur.getRobots().get(i).getOrdre(j - 1).getClass().getName() == null) {
					return joueur.ajouterOrdre(i, informations[i][j], j - 1);
				}
			}
		}

		// Troisième boucle pour détecter un éventuel ajout d'ordre
		// Dans ce cas, un ordre est déjà présent sur le robot à l'indice donné
		for (int i = 0; i < joueur.getRobots().size(); i++) {
			for (int j = 1; j < informations[i].length; j++) {
				if (joueur.getRobots().get(i).getOrdre(j - 1).getClass().getName().equals(informations[i][j]))
					return joueur.ajouterOrdre(i, informations[i][j], j - 1);
			}
		}

		// Quatrième boucle pour détecter l'eventuelle permutation de deux ordres
		int id1 = -1;
		int id2 = -2;

		for (int i = 0; i < joueur.getRobots().size(); i++) {
			for (int j = 1; j < informations[i].length; j++) {
				if (joueur.getRobots().get(i).getOrdre(j - 1).getClass().getName().equals(informations[i][j]))
					if (id1 == -1)
						id1 = j;
					else if (id2 == -1)
						id2 = j;
			}
			if (id1 != -1 && id2 != -1)
				return joueur.permuterOrdre(i, id1, id2);
		}
		return false;
	}

	public void executerInstructions() {
		Joueur jCourant = this.alJoueur.get(0);
		for (Robot r : jCourant.getRobots()) {
			for (Ordre o : r.getOrdres())
				if (o != null) {
					Ordre.setRobot(r);
					o.action();
					this.ctrl.afficherPlateau();
					try {
						Thread.sleep(500);
					} catch (Exception e) {
					}
				}
		}
		this.changerJoueur();
	}

	public void chargerScenar(String fichier) {
		scenar = new ArrayList<String>();
		try {
			Scanner sc = new Scanner(new FileReader(fichier));
			String ligne;
			while (sc.hasNextLine()) {
				scenar.add(sc.nextLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean partieFinie() {
		// REF_POINT est par défaut à 13, ce qui donne 11 points pour gagner
		// pour une partie à 2 joueurs, 10 pour 3 joueurs etc.
		for (Joueur j : alJoueur) {
			if (j.getPoints() == Plateau.REF_POINT - alJoueur.size()) {
				this.alGagnants.add(j);
				return true;
			}
		}

		if (this.toursRestants == 0)
			return true;

		return false;
	}

	public void interpreterLigneScenar(int ind) {
		if (ind >= 0 && ind < scenar.size()) {
			String ligne = scenar.get(ind);
			if (ligne.charAt(0) == 'J') {
				Joueur j = this.alJoueur.get(Integer.parseInt(String.valueOf(ligne.charAt(1))) - 1);
				this.nbJCourant = Integer.parseInt(String.valueOf(ligne.charAt(1))) - 1;
				if (ligne.charAt(4) == 'R') {
					Robot r = j.getRobots().get(Integer.parseInt(String.valueOf(ligne.charAt(5))) - 1);
					String action = "";
					for (int i = 8; ligne.charAt(i) != '('; i++) {
						action += ligne.charAt(i);
					}
					switch (action) {
					case "ajouterOrdre":
						j.ajouterOrdre(Integer.parseInt(String.valueOf(ligne.charAt(5))) - 1,
								ligne.substring(ligne.indexOf("(") + 1, ligne.indexOf(",")),
								Integer.parseInt(ligne.substring(ligne.indexOf(", ") + 2, ligne.indexOf(")"))) - 1);
						break;
					case "retirerOrdre":
						j.retirerOrdre(Integer.parseInt(String.valueOf(ligne.charAt(5))) - 1,
								Integer.parseInt(ligne.substring(ligne.indexOf("(") + 1, ligne.indexOf(")"))) - 1);
						break;
					case "permuterOrdre":
						j.permuterOrdre(Integer.parseInt(String.valueOf(ligne.charAt(5))) - 1,
								Integer.parseInt(ligne.substring(ligne.indexOf("(") + 1, ligne.indexOf(","))) - 1,
								Integer.parseInt(ligne.substring(ligne.indexOf(", ") + 2, ligne.indexOf(")"))) - 1);
						break;
					case "redemarrer":
						j.redemarrer(Integer.parseInt(String.valueOf(ligne.charAt(5))) - 1);
						break;
					}
				}
				if (ligne.charAt(4) == 'e') {
					this.executerInstructions();
				}
			}
		}
	}

	public void setGagnant() {
		if (toursRestants == 0) {
			// Tri des joueurs en fonction de leurs points
			Collections.sort(this.alJoueur);
			this.alGagnants.add(this.alJoueur.get(0));

			// On crée une liste de joueur qui sont au même nombre de points
			for (int i = 0; i < this.alJoueur.size() - 1; i++) {
				if (this.alJoueur.get(i).compareTo(this.alJoueur.get(i + 1)) == 0) {
					this.alGagnants.add(this.alJoueur.get(i + 1));
				} else
					break;
			}

			// Dans le cas où il y a une égalité entre cpt joueurs
			// Vérification des cristaux de valeur 2
			this.alGagnants = egaliteCristaux(2);

			// Vérification des cristaux de valeur 3
			this.alGagnants = egaliteCristaux(3);

			// Vérification au niveau des types de cristaux dans la base
			for (int i = 0; i < this.alGagnants.size() - 1; i++) {
				if (this.alGagnants.get(i).getTypeCristaux() != this.alGagnants.get(i + 1).getTypeCristaux())
					this.alGagnants.remove(i + 1);
			}
		}
	}

	public ArrayList<Joueur> getGagnant() {
		return this.alGagnants;
	}

	private ArrayList<Joueur> egaliteCristaux(int n) {
		for (int i = 0; i < this.alGagnants.size() - 1; ++i) {
			if (this.alGagnants.get(i).getValeurCristaux(3) == this.alGagnants.get(i + 1).getValeurCristaux(3))
				continue;
			this.alGagnants.remove(i + 1);
		}
		return this.alGagnants;
	}

	public ArrayList<Joueur> getJoueurs() {
		return this.alJoueur;
	}
}
