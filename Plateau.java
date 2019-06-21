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
	private int ligneScenar;
	private boolean choixEffectue;
	private ArrayList<Joueur> alGagnants;
	private boolean play;

	public Plateau(Controleur controleur) {
		this.ctrl = controleur;
		this.play = true;
		this.tourActuel = 0;
		this.ligneScenar = 0;
		this.choixEffectue = false;
		this.alGagnants = new ArrayList<Joueur>();
		this.alCase = new ArrayList<CaseHexa>();
		this.alJoueur = new ArrayList<Joueur>();
		this.alReserveCristaux = new ArrayList<Cristal>();
		this.scenar = new ArrayList<String>();
		this.init(this.ctrl.lireInt());
		Ordre.setTailleMax((int) this.tailleMax);
		Ordre.setCase(this.alCase);

		this.toursRestants = 4 * (this.alJoueur.size());
	}

	public void jeu() {
		while (!this.partieFinie()) {
			// Partie choix du joueur
			this.choixEffectue = false;
			while (!this.choixEffectue) {
				// Si on est en mode scenar, la variable scenar sera forcément non vide
				if (!scenar.isEmpty()) {

					while (!this.play) {
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					this.interpreterLigneScenar(this.ligneScenar++);
					this.ctrl.maj();
					this.ctrl.afficherPlateau();

				}
				// Ici, l'IHM va envoyer les informations qu'il faut pour changer
				// la valeur du booléen
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
			this.ctrl.maj();

			// Partie execution des instructions des robots

			this.executerInstructions();

			// Partie vérification d'approche de fin de partie et vérification
			// du spawn des cristaux
			if (this.alReserveCristaux.isEmpty()) {
				--this.toursRestants;
			} else
				this.verifCristaux();

			this.tourActuel++;
			this.ctrl.maj();
		}
	}

	public void jeuScenar() {

	}

	public void tourJoueur(String choix, int idRobot, String ordreString, int idOrdre1, int idOrdre2) {

		System.out.println("je suis dans le tour");
		System.out.println(choix);

		switch (choix) {
		case ("Ajouter"):
			this.choixEffectue = this.getJoueurCourant().ajouterOrdre(idRobot, ordreString, idOrdre1);
			break;
		case ("Permuter"):
			this.choixEffectue = this.getJoueurCourant().permuterOrdre(idRobot, idOrdre1, idOrdre2);
			break;
		case ("Retirer"):
			this.choixEffectue = this.getJoueurCourant().retirerOrdre(idRobot, idOrdre1);
			break;
		case ("Redemarrer"):
			this.choixEffectue = this.getJoueurCourant().redemarrer(idRobot);
			break;
		case ("Passer"):
			this.choixEffectue = true;
			break;
		default:
			this.choixEffectue = false;
			break;
		}
	}

	public void executerInstructions() {
		for (Robot r : getJoueurCourant().getRobots()) {
			for (Ordre o : r.getOrdres())
				if (o != null) {
					Ordre.setRobot(r);
					o.action();
					this.ctrl.maj();
					// this.ctrl.afficherPlateau();
				}
		}
	}

	// La liste contiendra toujours au moins 1 cristal quand cette méthode
	// est appelée. Verifie le nombre de cristaux en jeu (posés et détenus
	// par les robots)
	public void verifCristaux() {
		int cpt = 0;
		for (CaseHexa c : alCase) {
			if (c.getClass().getName().equals("Cristal"))
				cpt++;
			if (c.getClass().getName().equals("Robot")) {
				if (((Robot) (c)).getCristal() != null) {
					cpt++;
				}
			}
		}

		// Cas où il manque un cristal en jeu (il vient d'être gagné)
		if (cpt < this.alJoueur.size() + 3) {
			Cristal cristalTemp = this.alReserveCristaux.get(0);
			this.alReserveCristaux.remove(0);

			ArrayList<Integer[]> alPos = new ArrayList<Integer[]>();
			alPos.add(new Integer[] { this.tailleMax / 2, this.tailleMax / 2 });
			this.programmeRecursif(alPos, cristalTemp);
		}
	}

	// Méthode récursive qui s'occupe de poser le cristal le plus près du centre de
	// la map
	public ArrayList<Integer[]> programmeRecursif(ArrayList<Integer[]> alPos, Cristal cristal) {
		boolean posPrise;
		Integer[] coord = { -1, -1 };

		// Vérification si les valeurs de notre liste sont libres
		// La première de libre sera le spawn du cristal
		for (Integer[] pos : alPos) {
			posPrise = false;
			for (CaseHexa c : alCase) {
				if (c.getPosX() == pos[0] && c.getPosY() == pos[1]) {
					posPrise = true;
				} else {
					coord = pos;
				}
			}
			if (!posPrise) {
				cristal.setPosX(coord[0]);
				cristal.setPosY(coord[1]);
				this.alCase.add(cristal);
				return null;
			}
		}

		// On ajoute toutes les positions des cases adjacentes de l'ancienne liste,
		// en fonction de la position de la tuile
		ArrayList<Integer[]> newAlPos = new ArrayList<Integer[]>();
		for (Integer[] pos : alPos) {
			if (pos[0] < tailleMax / 2) {
				newAlPos.add(new Integer[] { pos[0] - 1, pos[1] });
				newAlPos.add(new Integer[] { pos[0], pos[1] + 1 });
				newAlPos.add(new Integer[] { pos[0] + 1, pos[1] + 1 });
				newAlPos.add(new Integer[] { pos[0] + 1, pos[1] });
				newAlPos.add(new Integer[] { pos[0], pos[1] - 1 });
				newAlPos.add(new Integer[] { pos[0] - 1, pos[1] - 1 });
			}
			if (pos[0] > tailleMax / 2) {
				newAlPos.add(new Integer[] { pos[0] - 1, pos[1] + 1 });
				newAlPos.add(new Integer[] { pos[0], pos[1] + 1 });
				newAlPos.add(new Integer[] { pos[0] + 1, pos[1] });
				newAlPos.add(new Integer[] { pos[0] + 1, pos[1] - 1 });
				newAlPos.add(new Integer[] { pos[0], pos[1] - 1 });
				newAlPos.add(new Integer[] { pos[0] - 1, pos[1] });
			}
			if (pos[0] == tailleMax / 2) {
				newAlPos.add(new Integer[] { pos[0] - 1, pos[1] });
				newAlPos.add(new Integer[] { pos[0], pos[1] + 1 });
				newAlPos.add(new Integer[] { pos[0] + 1, pos[1] });
				newAlPos.add(new Integer[] { pos[0] + 1, pos[1] - 1 });
				newAlPos.add(new Integer[] { pos[0], pos[1] - 1 });
				newAlPos.add(new Integer[] { pos[0] - 1, pos[1] - 1 });
			}
		}

		// On supprime toutes les anciennes positions
		for (int i = 0; i < newAlPos.size(); i++) {
			for (Integer[] ancPos : alPos) {
				if (newAlPos.get(i)[0] == ancPos[0] && newAlPos.get(i)[1] == ancPos[1]
						|| newAlPos.get(i)[0] == this.tailleMax / 2 && newAlPos.get(i)[1] == this.tailleMax/2) {
					newAlPos.remove(newAlPos.get(i));
				}
			}

			// Et ici, on supprime toutes les positions en double de la liste de positions
			for (int j = 0; j < newAlPos.size(); j++) {
				if (i != j)
					if (newAlPos.get(i)[0] == newAlPos.get(j)[0] && newAlPos.get(i)[1] == newAlPos.get(j)[1])
						newAlPos.remove(j);
			}
		}

		// Partie pour replacer les dernières coordonnées en fin de liste
		/*
		Integer[] coordTemp1 = newAlPos.get(1);
		newAlPos.remove(1);
		Integer[] coordTemp2 = newAlPos.get(2);
		newAlPos.remove(2);
		
		newAlPos.add(coordTemp1);
		newAlPos.add(coordTemp2);
		*/
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}

		this.programmeRecursif(newAlPos, cristal);
		return newAlPos;
	}

	public boolean partieFinie() {
		// REF_POINT est par défaut à 13, ce qui donne 11 points pour gagner
		// pour une partie à 2 joueurs, 10 pour 3 joueurs etc.
		for (Joueur j : alJoueur) {
			if (j.getPoints() >= Plateau.REF_POINT - alJoueur.size()) {
				this.alGagnants.add(j);
				return true;
			}
		}

		if (this.toursRestants == 0) {
			return true;
		}
		return false;
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

	public void interpreterLigneScenar(int ind) {
		int nbJCourant;
		if (ind >= 0 && ind < scenar.size()) {
			String ligne = scenar.get(ind);
			if (ligne.charAt(0) == 'J') {
				Joueur j = this.alJoueur.get(Integer.parseInt(String.valueOf(ligne.charAt(1))) - 1);
				nbJCourant = Integer.parseInt(String.valueOf(ligne.charAt(1))) - 1;
				if (ligne.charAt(4) == 'R') {
					Robot r = j.getRobots().get(Integer.parseInt(String.valueOf(ligne.charAt(5))) - 1);
					String action = "";
					for (int i = 8; ligne.charAt(i) != '('; i++) {
						action += ligne.charAt(i);
					}
					switch (action) {
					case "ajouterOrdre":
						this.tourJoueur("Ajouter", Integer.parseInt(String.valueOf(ligne.charAt(5))) - 1,
								ligne.substring(ligne.indexOf("(") + 1, ligne.indexOf(",")),
								Integer.parseInt(ligne.substring(ligne.indexOf(", ") + 2, ligne.indexOf(")"))) - 1, -1);
						break;
					case "retirerOrdre":
						this.tourJoueur("Retirer", Integer.parseInt(String.valueOf(ligne.charAt(5))) - 1, "",
								Integer.parseInt(ligne.substring(ligne.indexOf("(") + 1, ligne.indexOf(")"))) - 1, -1);
						break;
					case "permuterOrdre":
						this.tourJoueur("Permuter", Integer.parseInt(String.valueOf(ligne.charAt(5))) - 1, "",
								Integer.parseInt(ligne.substring(ligne.indexOf("(") + 1, ligne.indexOf(","))) - 1,
								Integer.parseInt(ligne.substring(ligne.indexOf(", ") + 2, ligne.indexOf(")"))) - 1);
						break;
					case "redemarrer":
						this.tourJoueur("Redemarrer", Integer.parseInt(String.valueOf(ligne.charAt(5))) - 1, "", -1,
								-1);
						break;

					case "passer":
						this.tourJoueur("Passer", -1, "", -1, -1);
						break;
					}
				}
			}
		}
	}

	public void chargerEtatScenar(int delta) {
		for (int i = 0; i < this.tourActuel + delta; i++) {
			this.interpreterLigneScenar(i);
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

	private ArrayList<Joueur> egaliteCristaux(int valeur) {
		for (int i = 0; i < this.alGagnants.size() - 1; ++i) {
			if (this.alGagnants.get(i).getPointsParCristaux(valeur) == this.alGagnants.get(i + 1)
					.getPointsParCristaux(valeur))
				continue;
			this.alGagnants.remove(i + 1);
		}
		return this.alGagnants;
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
							j.setBase(base);
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

	/***********************/
	/* Accesseurs */
	/***********************/

	public ArrayList<Joueur> getJoueurs() {
		return this.alJoueur;
	}

	public int getNbJoueur() {
		return this.alJoueur.size();
	}

	public int getTailleMax() {
		return this.tailleMax;
	}

	public ArrayList<CaseHexa> getCases() {
		return this.alCase;
	}

	public ArrayList<Cristal> getAlReserveCristaux() {
		return this.alReserveCristaux;
	}

	public Joueur getJoueurCourant() {
		return this.alJoueur.get(this.tourActuel % (this.alJoueur.size()));
	}

	public ArrayList<Joueur> getGagnant() {
		return this.alGagnants;
	}

	public void pause() {
		System.out.println(
				"pause---------------------------------------------------------------------------------------------");
		this.play = false;
	}

	public void play() {
		System.out.println(
				"play---------------------------------------------------------------------------------------------");
		this.play = true;
	}

	public int getTourActuel() {
		return this.tourActuel;
	}
}
