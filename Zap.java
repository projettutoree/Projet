import iut.algo.Clavier;

public class Zap extends Ordre {

	public static Robot robotZappe(Robot robotTemp) {
		int[] coordsCaseSvt = Ordre.getCaseSvt(robotTemp.getPosX(), robotTemp.getPosY(), robotTemp.getDir());
		for (CaseHexa c : Ordre.alCase) {
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1]) {
				if (c.getClass().getName().equals("Robot"))
					return (Robot)c;
				else
					return null;
			}
		}
		int[] coordsCaseSvt2 = Ordre.getCaseSvt(coordsCaseSvt[0], coordsCaseSvt[1], robotTemp.getDir());
		for (CaseHexa c : Ordre.alCase) {
			if (c.getPosX() == coordsCaseSvt2[0] && c.getPosY() == coordsCaseSvt2[1]) {
				if (c.getClass().getName().equals("Robot"))
					return (Robot)c;
				else
					return null;
			}
		}
		return null;
	}

	public static void zap(Robot robotTemp) {
		Robot robotZappe = Zap.robotZappe(robotTemp);
		if (robotZappe != null) {
			int action = Clavier.lire_int();
			Ordre.action(robotZappe, action);
		}
	}

	public static void actionDebug() {

	}

}
