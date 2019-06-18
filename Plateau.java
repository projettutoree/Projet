import java.util.*;
import java.io.*;

public class Plateau {
	private ArrayList<CaseHexa> alCase;
	private ArrayList<Joueur> alJoueur;

	private int tailleMax;

	private int nbJCourant;
	private int nbRCourant;

	public Plateau() {
		this.alCase = new ArrayList<CaseHexa>();
		this.alJoueur = new ArrayList<Joueur>();
		Scanner sc = new Scanner(System.in);
		init(sc.nextInt());
		/* this.alJoueur = new ArrayList<Joueur>(); */
	}

	public int getJoueurCourant() {
		return this.nbJCourant;
	}

	public void changerJoueur() {
		this.nbJCourant = (this.nbJCourant++) % this.alJoueur.size();
	}

	/*
	 * public boolean checkDeplacement() { Robot roboTemp =
	 * this.alJoueur.get(this.nbJCourant).getRobots()[this.nbRCourant]; for
	 * (CaseHexa c : this.alCase) { if (c.getPosX() == coordsCaseSvt[0] &&
	 * c.getPosX() == coordsCaseSvt[1]) { if
	 * (c.getClass().getName().equals("Cristal")) return true; } } }
	 * 
	 * public boolean checkRamassage() { Robot roboTemp =
	 * this.alJoueur.get(this.nbJCourant).getRobots()[this.nbRCourant]; int[]
	 * coordsCaseSvt = this.getCaseSvt(roboTemp); for (CaseHexa c : this.alCase) {
	 * if (c.getPosX() == coordsCaseSvt[0] && c.getPosX() == coordsCaseSvt[1]) { if
	 * (c.getClass().getName().equals("Cristal")) return true; } } return false; }
	 * 
	 * public boolean estOOB(int[] coords) { if (coords[0] < 0 || coords[1] < 0)
	 * return true; if (coords[0] > this.tailleMax || coords[1] > this.tailleMax -
	 * (Math.abs(this.tailleMax / 2 - coords[0]))) return true; return false; }
	 * 
	 * public boolean checkDepot() { Robot roboTemp =
	 * this.alJoueur.get(this.nbJCourant).getRobots()[this.nbRCourant]; int[]
	 * coordsCaseSvt = this.getCaseSvt(roboTemp); for (CaseHexa c : this.alCase) {
	 * if (c.getPosX() == coordsCaseSvt[0] && c.getPosX() == coordsCaseSvt[1]) { if
	 * (!c.getClass().getName().equals("Base")) return false; } } return
	 * !(this.estOOB(coordsCaseSvt)); }
	 * 
	 * public void excuterInstructions() { Robot robotTemp =
	 * this.alJoueur.get(this.nbJCourant).getRobots()[this.nbRCourant]; String[]
	 * tabInstr = robotTemp.getInstructions(); for (int i = 0; i < 3; i++) { switch
	 * (tabInstr[i]) { case "avancer": if (this.checkDeplacement()) //
	 * this.deplacer(robotTemp); break; case "tournerSensHoraire":
	 * robotTemp.tourner(1); break; case "tournerSensAntiHoraire":
	 * robotTemp.tourner(-1); break; case "ramasser": if (this.checkRamassage()) //
	 * this.charger(robotTemp); break; case "deposer": if (this.checkDepot()) //
	 * this.decharger(robotTemp); break;
	 * 
	 * case "zap" : this.zap(); break;
	 * 
	 * default: break; } } }
	 * 
	 * private int[] getCaseSvt(Robot robot) { int[] tabCoord = { robot.getPosX(),
	 * robot.getPosY() }; switch (robot.getDir()) { case 0: tabCoord[0]--; break;
	 * case 1: tabCoord[1]++; break; case 2: tabCoord[0]++; tabCoord[1]++; break;
	 * case 3: tabCoord[0]++; break; case 4: tabCoord[1]--; break; case 5:
	 * tabCoord[0]--; tabCoord[1]--; break; default: break; } return tabCoord; }
	 */

	public String toString() {
		String s = "";
		boolean caseOccupee;

		s += "      ";
		for (int j = 0; j <= (Math.abs(this.tailleMax / 2)) / 2; j++) {
			s += "      ";
		}
		for (int j = 0; j <= this.tailleMax - (Math.abs(this.tailleMax / 2)); j++) {
			s += "_____ ";
		}
		s += "\n";

		for (int i = 0; i <= this.tailleMax; i++) {
			for (int k = 0; k < 3; k++) {
				if (i % 2 == 0)
					s += "   ";
				for (int j = 0; j <= (Math.abs(this.tailleMax / 2 - i)) / 2; j++) {
					s += "      ";
				}
				if (i < Math.abs(this.tailleMax / 2) && k == 2)
					s += "__";
				else
					s += "  ";
				if (k != 2) {
					for (int j = 0; j <= this.tailleMax - (Math.abs(this.tailleMax / 2 - i)); j++) {
						s += "|  ";
						CaseHexa objet = null;
						if (k == 1) {
							for (CaseHexa c : this.alCase) {
								if (c.getPosX() == i && c.getPosY() == j)
									objet = c;
							}
						}
						if (objet != null)
							s += objet.getClass().getName().charAt(0);
						else
							s += " ";
						s += "  ";
					}
				} else {
					for (int j = 0; j <= this.tailleMax - (Math.abs(this.tailleMax / 2 - i)); j++) {
						s += "|_____";
					}
				}
				s += "|";
				if (i < Math.abs(this.tailleMax / 2) && k == 2)
					s += "__";
				s += "\n";
			}
		}

		return s;
	}

	public void init(int nbJoueur) {
		try {
			Scanner sc = new Scanner(new FileReader("Regles/" + nbJoueur + "joueurs.txt"));
			String ligne = sc.nextLine();
			this.tailleMax = Integer.parseInt(ligne.substring(ligne.indexOf(":")));
			while (sc.hasNextLine()) {
				ligne = sc.nextLine();
				String[] information = ligne.split(":");
				// si l'identifient est un chiffre
				if (information[0].matches("[1-6]")) {

					char identifiant = information[0].charAt(0);
					Joueur j = new Joueur(Integer.parseInt(""+identifiant));

					for (int i = 1; i < information.length; i++) {
						information[i] = information[i].substring(1);
						if (identifiant == 'B') {
							Base base = new Base(
									Integer.parseInt(information[i].substring(0, information[i].indexOf(","))),
									Integer.parseInt(information[i].substring(information[i].indexOf(","))), j);
							this.alCase.add(base);
						} else if (identifiant == 'R') {
							int index = information[i].indexOf(",");
							int index2 = information[i].indexOf(",", index);
							Robot robot = new Robot(Integer.parseInt(information[i].substring(0, index)),Integer.parseInt(information[i].substring(index, index2)),Integer.parseInt(information[i].substring(index2)));
							this.alCase.add(robot);
							j.addRobot(robot);
						}
					}
				}
				if (information[0].matches("C")) {
					for (int i = 1; i < information.length; i++) {
						int index = information[i].indexOf(",");
						int index2 = information[i].indexOf(",", index);
						Cristal cristal = new Cristal(Integer.parseInt(information[i].substring(0, index)),
								Integer.parseInt(information[i].substring(index, index2)),
								Integer.parseInt(information[i].substring(index2)));
						this.alCase.add(cristal);
					}
				}
				System.out.println(sc.nextLine());
			}
			sc.close();

		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
		System.out.println(new Plateau());
	}
}
