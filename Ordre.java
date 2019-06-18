import java.util.ArrayList;

public class Ordre {

	private static ArrayList<CaseHexa> alCase;

	public static void executerProgramme(ArrayList<CaseHexa> alCase,
	                                     ArrayList<Robot> alRobot   ) {

		Ordre.alCase = alCase;

		for(Robot r : alRobot) {
			for(String ordre : r.getOrdres() ) {
				switch(ordre.toUpperCase()) {
					case "AVANCER X1" :
						Ordre.avancer(r);
						break;
					case "AVANCER X2" :
						Ordre.avancer(r);
						Ordre.avancer(r);
						break;
					case "TOURNER SENS ANTI HORAIRE" :
						r.tourner(-1);
						break;
					case "TOURNER SENS HORAIRE" :
						r.tourner(1);
						break;
					case "CHARGER" :
						Ordre.charger(r);
						break;
					case "DEPOSER" :
						Ordre.deposer(r);
						break;
					case "ZAP" :
						Ordre.zap(r);
						break;
				}
			}
		}
	}

	private static void avancer(Robot Robot) {

	}

	private static void charger(Robot r) {

	}

	private static void deposer(Robot r) {

	}

	private static void zap(Robot r) {

	}
}
