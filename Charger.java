public class Charger extends Ordre {

	public static boolean checkRamassage(Robot robotTemp) {
		if (robotTemp.estCharge())
			return false;
		int[] coordsCaseSvt = Ordre.getCaseSvt(robotTemp.getPosX(), robotTemp.getPosY(), robotTemp.getDir());
		for (CaseHexa c : Ordre.alCase) {
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1]) {
				if (c.getClass().getName().equals("Cristal"))
					return true;
			}
		}
		return false;
	}

	public static Cristal getCristalDevant(Robot robotTemp) {
		int[] coordsCaseSvt = Ordre.getCaseSvt(robotTemp.getPosX(), robotTemp.getPosY(), robotTemp.getDir());
		for (CaseHexa c : Ordre.alCase)
		{
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1])
			{
				if (c.getClass().getName().equals("Cristal"))
					return (Cristal)Ordre.alCase.remove(Ordre.alCase.indexOf(c));
			}
		}
		return null;
	}

	public static void charger(Robot robotTemp) {
		if (Charger.checkRamassage(robotTemp))
			robotTemp.charger(Charger.getCristalDevant(robotTemp));
	}

	public static void actionDebug() {

	}

}
