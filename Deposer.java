public class Deposer extends Ordre {

	public static boolean checkDepot(Robot robotTemp) {
		if (!robotTemp.estCharge())
			return false;
		int[] coordsCaseSvt = Ordre.getCaseSvt(robotTemp.getPosX(), robotTemp.getPosY(), robotTemp.getDir());
		for (CaseHexa c : Ordre.alCase) {
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1]) {
				if (!c.getClass().getName().equals("Base"))
					return false;
				else
					return true;
			}
		}
		return !(Ordre.estOOB(coordsCaseSvt));
	}

	public static Base getBaseDevant(Robot robotTemp) {
		int[] coordsCaseSvt = Ordre.getCaseSvt(robotTemp.getPosX(), robotTemp.getPosY(), robotTemp.getDir());
		for (CaseHexa c : Ordre.alCase) {
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1]) {
				if (c.getClass().getName().equals("Base"))
					return (Base) Ordre.alCase.remove(Ordre.alCase.indexOf(c));
			}
		}
		return null;
	}

	public static void deposer(Robot robotTemp) {
		if (Deposer.checkDepot(robotTemp)) {
			Base b = Deposer.getBaseDevant(robotTemp);
			if (b == null) {
				Ordre.alCase.add(robotTemp.getCristal());
				int[] coordsCaseSvt = Ordre.getCaseSvt(robotTemp.getPosX(), robotTemp.getPosY(), robotTemp.getDir());
				Ordre.alCase.get(Ordre.alCase.size() - 1).setPosX(coordsCaseSvt[0]);
				Ordre.alCase.get(Ordre.alCase.size() - 1).setPosY(coordsCaseSvt[1]);
				System.out.println(coordsCaseSvt[0]);
				System.out.println(coordsCaseSvt[1]);
				robotTemp.deposer();
			}
			robotTemp.deposer();
		}
	}

	public static void actionDebug() {

	}

}
