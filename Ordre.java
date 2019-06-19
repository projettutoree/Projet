import java.util.ArrayList;

public class Ordre {

	protected static ArrayList<CaseHexa> alCase;
	protected static int                 tailleMax;
	protected static Robot               robot;

	public void action() {}
	public static void setRobot(Robot robot){
		Ordre.robot = robot;
	}


	public static void setCase(ArrayList<CaseHexa> alCase){
		Ordre.alCase = alCase;
	}

	public static void setTailleMax(int tailleMax){
		Ordre.tailleMax = tailleMax;
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
