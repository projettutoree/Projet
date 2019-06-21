import javax.lang.model.util.ElementScanner6;

public class Deposer extends Ordre {

	public static boolean checkDepot() {
		if (!Deposer.robot.estCharge())
			return false;
		int[] coordsCaseSvt = Ordre.getCaseSvt(Deposer.robot.getPosX(), Deposer.robot.getPosY(),
				Deposer.robot.getDir());
		for (CaseHexa c : Ordre.alCase) {
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1]) {
				if (c.getClass().getName().equals("Base"))
					return true;
				else if (c.getClass().getName().equals("Robot")) {
					Robot r = (Robot) c;
					if (r.getCristal() == null)
						Deposer.robot.deposer(r);
					return false;
				} else
					return false;


			}
		}
		return !(Ordre.estOOB(coordsCaseSvt));
	}

	public static Base getBaseDevant() {
		int[] coordsCaseSvt = Ordre.getCaseSvt(Deposer.robot.getPosX(), Deposer.robot.getPosY(),
				Deposer.robot.getDir());
		for (CaseHexa c : Ordre.alCase) {
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1]) {
				if (c.getClass().getName().equals("Base"))
					return (Base) c;
			}
		}
		return null;
	}

	public void action() {
		if (Deposer.checkDepot()) {
			Base b = Deposer.getBaseDevant();
			if (b == null) {
				Ordre.alCase.add(Deposer.robot.getCristal());
				int[] coordsCaseSvt = Ordre.getCaseSvt(Deposer.robot.getPosX(), Deposer.robot.getPosY(),
						Deposer.robot.getDir());
				Ordre.alCase.get(Ordre.alCase.size() - 1).setPosX(coordsCaseSvt[0]);
				Ordre.alCase.get(Ordre.alCase.size() - 1).setPosY(coordsCaseSvt[1]);
			}
			Deposer.robot.deposer(b);
		}
	}

	public static void actionDebug() {

	}

}
