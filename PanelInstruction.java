import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;

public class PanelInstruction extends JPanel {
	// private Arraylist<JLabel> alLbl;
	private Controleur ctrl;

	public PanelInstruction(Controleur ctrl) {
		this.ctrl = ctrl;
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		maj();
	}

	public void maj() {
		this.removeAll();
		String s = "";
		// this.alLbl = new ArrayList<JLabel>();
		Joueur joueur = this.ctrl.getJoueurCourant();

		for (int i = 0; i < joueur.getAlOrdre().size(); i++) {
			Ordre o = joueur.getAlOrdre().get(i);
			if (o == null)
				s = "./images/ordres/ordre_vide.png";
			else
				s = getLien(o.getClass().getName());

			this.add(new JLabel(new ImageIcon(s)));

		}

		this.revalidate();
	}

	public String getLien(String s) {
		switch (s) {
		case "Avancer":
			s = "./images/ordres/avancer1.png";
			break;
		case "AvancerX2":
			s = "./images/ordres/avancer2.png";
			break;
		case "TournerSensAntiHoraire":
			s = "./images/ordres/tuileGauche.png";
			break;
		case "TournerSensHoraire":
			s = "./images/ordres/tuileDroite.png";
			break;
		case "Deposer":
			s = "./images/ordres/deposerCristal.png";
			break;
		case "Charger":
			s = "./images/ordres/chargerCristal.png";
			break;
		}
		return s;
	}
}