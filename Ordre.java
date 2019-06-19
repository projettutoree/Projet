import java.util.ArrayList;

public abstract class Ordre {
	private static ArrayList<CaseHexa> alCase;
	private static Robot robot;

	public static void executer(Robot robot) {
		Ordre.robot = robot;
		action();
	}

	public static void action() {}

	public static setCase(ArrayList<CaseHexa> alCase){
		this.alCase = alCase;
	}
}
