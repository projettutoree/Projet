import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;
import java.io.FileReader;
import iut.algo.Clavier;

public class Plateau {
	private static final int REF_POINT = 13;

	private ArrayList<CaseHexa> alCase;
	private ArrayList<Joueur> alJoueur;
	private ArrayList<Cristal> alReserveCristaux;

	private Controleur ctrl;
	private int tailleMax;
	private int toursRestants;
	private ArrayList<Joueur> alGagnants;

	private int nbJCourant = 0;
	private int nbRCourant = 0;

	public Plateau(Controleur ctrl) {
		this.ctrl = ctrl;
		this.toursRestants = 3;
		this.alGagnants = null;

		this.alCase = new ArrayList<CaseHexa>();
		this.alJoueur = new ArrayList<Joueur>();

		this.init(Clavier.lire_int());
		Ordre.setTailleMax(this.tailleMax);
		Ordre.setCase(this.alCase);
	}

	public void jeu() {
		while(!partieFinie()) {

		}
		//this.setGagnant();
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
				if(information[0].matches("R")) {
					for(int i = 1; i < information.length; i++) {
						int index = information[i].indexOf(",");
						Cristal cristal = new Cristal(Integer.parseInt(information[i].substring(0,index)),
						                              -1,-1);
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
		this.nbJCourant = (this.nbJCourant++) % this.alJoueur.size();
	}

	public void executerInstructions() {
		Joueur jCourant = this.alJoueur.get(0);
		for (Robot r : jCourant.getRobots()) {
			for (Ordre o : r.getOrdres())
				if (o != null)
				{
					Ordre.setRobot(r);
					o.action();
				}
		}
	}

	public boolean partieFinie() {
		// REF_POINT est par défaut à 13, ce qui donne 11 points pour gagner
		// pour une partie à 2 joueurs, 10 pour 3 joueurs etc.
		for(Joueur j : alJoueur) {
			if(j.getPoints() == Plateau.REF_POINT - alJoueur.size()) {
				this.alGagnants.add(j);
				return true;
			}
		}

		if(this.toursRestants == 0)
			return true;

		return false;
	}

	public void setGagnant() {
		if(toursRestants == 0) {
			// Tri des joueurs en fonction de leurs points
			Collections.sort(this.alJoueur);
			this.alGagnants.add(this.alJoueur.get(0));

			// On crée une liste de joueur qui sont au même nombre de points
			for(int i = 0; i < this.alJoueur.size() -1; i++) {
				if(this.alJoueur.get(i).compareTo(this.alJoueur.get(i+1)) == 0)
				{
					this.alGagnants.add(this.alJoueur.get(i+1));
				}
				else
					break;
			}

			// Dans le cas où il y a une égalité entre cpt joueurs
			// Vérification des cristaux de valeur 2
			this.alGagnants = egaliteCristaux(2);

			// Vérification des cristaux de valeur 3
			this.alGagnants = egaliteCristaux(3);

			//Vérification au niveau des types de cristaux dans la base
			for(int i = 0; i < this.alGagnants.size() -1; i++) {
				if(this.alGagnants.get(i)  .getTypeCristaux() !=
				   this.alGagnants.get(i+1).getTypeCristaux())
					this.alGagnants.remove(i+1);
				}
		}
	}

	public ArrayList<Joueur> getGagnant() {
		return this.alGagnants;
	}

	/**
	  * Compare les joueurs en fonction de la valeur des cristaux qu'ils possèdent
	  * Retire le joueur de la liste s'il a moins de cristaux qu'un autre joueur
	  */
	private ArrayList<Joueur> egaliteCristaux(int valeur) {
		for(int i = 0; i < this.alGagnants.size() -1; i++) {
			if(this.alGagnants.get(i)  .getValeurCristaux(3) !=
			   this.alGagnants.get(i+1).getValeurCristaux(3))
				this.alGagnants.remove(i+1);
			}
		return this.alGagnants;
	}

	public String toString() {
		String s = "";
		boolean caseOccupee;
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

	// public static void main(String[] args) {
	// 	Plateau p = new Plateau(ctrl);
	// 	System.out.println(p);
	// 	Joueur j = p.getJoueurs().get(0);
	// 	//j.ajouterOrdre(0,0,0);
	// 	j.ajouterOrdre(0, "AvancerX2", 0);
	// 	j.ajouterOrdre(0, "Avancer", 1);
	// 	j.ajouterOrdre(0, "Zap", 2);
	// 	p.executerInstructions();
	// 	System.out.println(p);
	// }
}
