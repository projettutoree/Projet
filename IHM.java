import iut.algo.*;
import java.util.*;

public class IHM {

	private Controleur ctrl;

	public IHM(Controleur ctrl) {
		ctrl = ctrl;
	}

	public int lireInt() {
		return Clavier.lire_int();
	}

	public String lireClavier() {
		return Clavier.lireString();
	}

	public void afficherPlateau(ArrayList<CaseHexa> alCase,Robot rob,int tailleMax) {
		String s = "";
		boolean caseOccupee;
		System.out.println(alCase);
		s += rob.toString() + "\n";
		if ((tailleMax / 2) % 4 == 0)
			s += "   ";
		if ((tailleMax / 2) % 4 == 1)
			s += "      ";
		for (int j = 0; j <= (Math.abs(tailleMax / 2)) / 2; j++) {
			s += "      ";
		}
		for (int j = 0; j <= tailleMax - (Math.abs(tailleMax / 2)); j++) {
			s += "_____ ";
		}
		s += "\n";

		for (int i = 0; i <= tailleMax; i++) {
			for (int k = 0; k < 3; k++) {
				if (i % 2 != (tailleMax / 2) % 4)
					s += "   ";
				for (int j = 0; j <= (Math.abs(tailleMax / 2 - i)) / 2; j++) {
					s += "      ";
				}
				if (i < Math.abs(tailleMax / 2) && k == 2)
					s += "__";
				else
					s += "  ";
				if (k != 2) {
					for (int j = 0; j <= tailleMax - (Math.abs(tailleMax / 2 - i)); j++) {
						s += "|  ";
						CaseHexa objet = null;
						if (k == 1) {
							for (CaseHexa c : alCase) {
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
					for (int j = 0; j <= tailleMax - (Math.abs(tailleMax / 2 - i)); j++) {
						s += "|_____";
					}
				}
				s += "|";
				if (i < Math.abs(tailleMax / 2) && k == 2)
					s += "__";
				s += "\n";
			}
		}
		System.out.println(s);
	}

}