import java.awt.*;
import javax.swing.*;

public class PanelReserve extends JPanel {

	private Controleur ctrl;

	public PanelReserve(Controleur ctrl) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.ctrl = ctrl;
		for (Cristal cristal : this.ctrl.getAlReserveCristaux()) {
			this.add(new JLabel(new ImageIcon(getLien(cristal.getValeur()))));
		}
	}

	public void maj() {
		this.removeAll();
		for (Cristal cristal : this.ctrl.getAlReserveCristaux()) {
			this.add(new JLabel(new ImageIcon(getLien(cristal.getValeur()))));
		}
		this.revalidate();
	}

	public String getLien(int i) {
		String s;
		switch (i) {
		case 2:
			s = "./images/test/cristalBleu.png";
			break;
		case 3:
			s = "./images/test/cristalVert.png";
			break;
		case 4:
			s = "./images/test/cristalRose.png";
			break;
		default:
			s = "./images/ordres/ordre_vide.png";
			break;

		}
		return s;

	}
}