import iut.algo.Clavier;

public class Zap extends Ordre {

	public static Robot robotZappe() {
		int[] coordsCaseSvt = Ordre.getCaseSvt(Zap.robot.getPosX(), Zap.robot.getPosY(), Zap.robot.getDir());
		for (CaseHexa c : Ordre.alCase) {
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1]) {
				if (c.getClass().getName().equals("Robot"))
					return (Robot)c;
				else
					return null;
			}
		}
		int[] coordsCaseSvt2 = Ordre.getCaseSvt(coordsCaseSvt[0], coordsCaseSvt[1], Zap.robot.getDir());
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

	public void action() {
		Robot robotZappe = Zap.robotZappe();
		if (robotZappe != null) {
			int action = Clavier.lire_int();
			switch (action) {
				case 0 :
					Avancer a = new Avancer();
					a.setRobot(robotZappe);
					a.action();
					break;
				case 2 :
					TournerSensHoraire b = new TournerSensHoraire();
					b.setRobot(robotZappe);
					b.action();
					break;
				case 3 :
					TournerSensAntiHoraire c = new TournerSensAntiHoraire();
					c.setRobot(robotZappe);
					c.action();
					break;
				case 4 :
					Charger d = new Charger();
					d.setRobot(robotZappe);
					d.action();
					break;
				case 5 :
					Deposer e = new Deposer();
					e.setRobot(robotZappe);
					e.action();
					break;
			}
		}
	}

	public static void actionDebug() {

	}

}
