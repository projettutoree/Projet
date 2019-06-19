import java.util.ArrayList;

public abstract class Ordre {

	public static ArrayList<CaseHexa> alCase;
	public static int                 tailleMax;

	public static void executer(Robot robot) {
		action(robot);
	}

	public static void setCase(ArrayList<CaseHexa> alCase){
		Ordre.alCase = alCase;
	}

	public static void setTailleMax(int tailleMax){
		Ordre.tailleMax = tailleMax;
	}

	public static void action(Robot rob) {

		for(String ordre : rob.getOrdres() ) {
			switch(ordre.toUpperCase()) {
				case "AVANCER X1" :
					Avancer.avancer(rob);
					break;
				case "AVANCER X2" :
					Avancer.avancer(rob);
					Avancer.avancer(rob);
					break;
				case "TOURNER GAUCHE" :
					rob.tourner(-1);
					break;
				case "TOURNER DROITE" :
					rob.tourner(1);
					break;
				case "CHARGER" :
					Charger.charger(rob);
					break;
				case "DEPOSER" :
					Deposer.deposer(rob);
					break;
				case "ZAP" :
					Zap.zap(rob);
					break;
			}
		}
	}

	public static void action(Robot rob, int numOrdre) {
		String ordre = Joueur.ordresString[numOrdre];
		switch(ordre.toUpperCase()) {
			case "AVANCER X1" :
				Avancer.avancer(rob);
				break;
			case "TOURNER GAUCHE" :
				rob.tourner(-1);
				break;
			case "TOURNER DROITE" :
				rob.tourner(1);
				break;
			case "CHARGER" :
				Charger.charger(rob);
				break;
			case "DEPOSER" :
				Deposer.deposer(rob);
				break;
		}
	}

	public static boolean estOOB(int[] coords) {
		if (coords[0] < 0 || coords[1] < 0)
			return true;
		if (coords[0] > Ordre.tailleMax || coords[1] > Ordre.tailleMax - (Math.abs(Ordre.tailleMax / 2 - coords[0])))
			return true;
		return false;
	}

	public static int[] getCaseSvt(int x, int y, int dir) {
		int[] tabCoord = { x, y };
		switch (dir) {
		case 0:
			if (tabCoord[0] > Ordre.tailleMax / 2)
				tabCoord[1]++;
			tabCoord[0]--;
			break;
		case 1:
			tabCoord[1]++;
			break;
		case 2:
			if (tabCoord[0] < Ordre.tailleMax / 2)
				tabCoord[1]++;
			tabCoord[0]++;
			// tabCoord[1]++;
			break;
		case 3:
			if (tabCoord[0] >= Ordre.tailleMax / 2)
				tabCoord[1]--;
			tabCoord[0]++;
			break;
		case 4:
			tabCoord[1]--;
			break;
		case 5:
			if (tabCoord[0] <= Ordre.tailleMax / 2)
				tabCoord[1]--;
			tabCoord[0]--;
			break;
		default:
			break;
		}
		return tabCoord;
	}
}
