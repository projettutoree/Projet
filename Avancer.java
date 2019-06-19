public class Avancer extends Ordre {

	public void action() {
		if (Avancer.checkDeplacement(Avancer.robot, Avancer.robot.getDir(), false))
			Avancer.deplacer(Avancer.robot, Avancer.robot.getDir());
	}

	public static boolean checkDeplacement(CaseHexa objet, int dir, boolean objetPousse) {
		int[] coordsCaseSvt = Ordre.getCaseSvt(objet.getPosX(), objet.getPosY(), dir);
		CaseHexa temp = null;
		for (CaseHexa c : Ordre.alCase) {
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1]) {
				temp = c;
			}
		}
		if (temp != null) {
			if (objetPousse)
				return false;
			if (!temp.estPoussable())
				return false;
			return Avancer.checkDeplacement(temp, dir, true);
		}
		if (objetPousse) {
			if (!Ordre.estOOB(coordsCaseSvt))
				Avancer.deplacer(objet, dir);
		}
		return !(Ordre.estOOB(coordsCaseSvt));
	}

	public static void deplacer(CaseHexa objet, int dir) {
		int[] coordsCaseSvt = Ordre.getCaseSvt(objet.getPosX(), objet.getPosY(), dir);
		objet.avancer(coordsCaseSvt[0], coordsCaseSvt[1]);
	}

	public static void actionDebug() {

	}

}
