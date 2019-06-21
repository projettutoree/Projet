import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelJoueur extends JPanel {
	private static int nbJoueur;

	private Color[] tabColor = { Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.PINK };

	private JLabel nomJ;
	private int numJoueur;
	private Controleur ctrl;

	private JLabel[][] actRobot;

	public PanelJoueur(Controleur ctrl) {
		this.ctrl = ctrl;
		this.setLayout(new GridLayout(3, 1));
		this.numJoueur = (++nbJoueur);
		this.nomJ = new JLabel("Joueur " + this.numJoueur);
		this.nomJ.setHorizontalAlignment(SwingConstants.CENTER);

		this.setBackground(tabColor[numJoueur - 1]);
		this.add(this.nomJ);

		this.actRobot = new JLabel[2][3];

		for (int i = 0; i < 2; i++) {

			for (int j = 0; j < 3; j++) {
				this.actRobot[i][j] = new JLabel(new ImageIcon("./images/ordres/ordre_vide.png"));
			}
		}

		for (int i = 0; i < 2; i++) {
			JPanel pRob = new JPanel(new GridLayout(1, 4));
			pRob.add(new JLabel("Robot " + (i + 1)));
			for (int j = 0; j < 3; j++) {
				pRob.add(this.actRobot[i][j]);
			}
			this.add(pRob);
		}
	}

	public void maj() {
		Joueur joueur = this.ctrl.getJoueurs().get(this.numJoueur - 1);
		this.nomJ.setText(
				"Joueur " + this.numJoueur + " : " + this.ctrl.getJoueurs().get(this.numJoueur - 1).getPoints());
		for (int i = 0; i < joueur.getRobots().size(); i++) {
			Robot r = joueur.getRobots().get(i);
			for (int j = 0; j < r.getOrdres().length; j++) {
				Ordre o = r.getOrdres()[j];
				String s;
				if (o == null)
					s = "./images/ordres/ordre_vide.png";
				else
					s = getLien(o.getClass().getName());
				this.actRobot[i][j].setIcon(new ImageIcon(s));

			}
		}
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