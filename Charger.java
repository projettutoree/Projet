public class Charger extends Ordre {

	public static boolean checkRamassage() {
		if (Charger.robot.estCharge())
			return false;
		int[] coordsCaseSvt = Ordre.getCaseSvt(Charger.robot.getPosX(), Charger.robot.getPosY(), Charger.robot.getDir());
		for (CaseHexa c : Ordre.alCase) {
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1]) {
				if (c.getClass().getName().equals("Cristal"))
					return true;
				if (c.getClass().getName().equals("Robot")) {
					Robot r = (Robot) c;
					if (r.getCristal() != null)
						r.deposer(Charger.robot);
				}
			}
		}
		return false;
	}

	public static Cristal getCristalDevant() {
		int[] coordsCaseSvt = Ordre.getCaseSvt(Charger.robot.getPosX(), Charger.robot.getPosY(), Charger.robot.getDir());
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

	public void action() {
		if (Charger.checkRamassage())
			Charger.robot.charger(Charger.getCristalDevant());
	}

	public static void actionDebug() {

	}

}
