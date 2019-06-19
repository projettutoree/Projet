import java.util.ArrayList;

public abstract class Ordre {
	private static ArrayList<CaseHexa> alCase;
	private static Robot robot;

	public static void executer(Robot robot) {
		Ordre.robot = robot;
		action();
	}

	public static void setCase(ArrayList<CaseHexa> alCase) {
		Ordre.alCase = alCase;
	}

	public static void action() {}
}
