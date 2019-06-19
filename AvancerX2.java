public class AvancerX2 extends Ordre {

	public void action() {
		Avancer a = new Avancer();
		a.setRobot(this.robot);
		a.action();
		a.action();
	}

	public void actionDebug() {

	}

}
