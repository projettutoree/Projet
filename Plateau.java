import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileReader;
import iut.algo.Clavier;

public class Plateau {
	private ArrayList<CaseHexa> alCase;
	private ArrayList<Joueur> alJoueur;

	private int tailleMax;


	private int nbJCourant = 0;
	private int nbRCourant = 0;

	public Plateau() {
		this.alCase = new ArrayList<CaseHexa>();
		this.alJoueur = new ArrayList<Joueur>();
		this.init(Clavier.lire_int());
		Ordre.setTailleMax(this.tailleMax);
		Ordre.setCase(this.alCase);
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
									Integer.parseInt(information[i].substring(index2 + 1)));
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
		this.nbJCourant = (this.nbJCourant++) % this.alJoueur.size();
	}

	public void executerInstructions(Robot robotZappe) {
		Joueur jCourant = this.alJoueur.get(0);
		for (Robot r : jCourant.getRobots()){
			Ordre.executer(r);
		}
	}

	private int[] getCaseSvt(int x, int y, int dir) {
		int[] tabCoord = { x, y };
		switch (dir) {
		case 0:
			if (tabCoord[0] > this.tailleMax / 2)
				tabCoord[1]++;
			tabCoord[0]--;
			break;
		case 1:
			tabCoord[1]++;
			break;
		case 2:
			if (tabCoord[0] < this.tailleMax / 2)
				tabCoord[1]++;
			tabCoord[0]++;
			// tabCoord[1]++;
			break;
		case 3:
			if (tabCoord[0] >= this.tailleMax / 2)
				tabCoord[1]--;
			tabCoord[0]++;
			break;
		case 4:
			tabCoord[1]--;
			break;
		case 5:
			if (tabCoord[0] <= this.tailleMax / 2)
				tabCoord[1]--;
			tabCoord[0]--;
			break;
		default:
			break;
		}
		return tabCoord;
	}

	public String toString() {
		String s = "";
		boolean caseOccupee;
		System.out.println(alCase);
		if ((this.tailleMax / 2) % 4 == 0)
			s += "   ";
		if ((this.tailleMax / 2) % 4 == 1)
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
				if (i % 2 != (this.tailleMax / 2) % 4)
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

	public ArrayList<Joueur> getJoueurs() {return this.alJoueur;}

	public static void main(String[] args) {
		Plateau p = new Plateau();
		System.out.println(p);
		Joueur j = p.getJoueurs().get(0);
		//j.ajouterOrdre(0,0,0);
		System.out.println(j.getRobots());
		p.executerInstructions(null);
		System.out.println(p);
	}
}
