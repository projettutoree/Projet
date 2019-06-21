import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Permuter extends JFrame implements ActionListener, MouseListener {

	private Controleur ctrl;

	private int numRobot;

	private ArrayList<JLabel> alLblRobot;

	private JLabel choixRobot1, choixRobot2;

	private JButton annuler;
	private JButton valider;

	private int indicePermutation;

	public Permuter(Controleur ctrl, int numRobot) {
		this.ctrl = ctrl;
		this.numRobot = numRobot;
		this.indicePermutation = 0;
		this.alLblRobot = new ArrayList<JLabel>();

		System.out.println(this.ctrl.getJoueurCourant());
		for (Ordre o : this.ctrl.getJoueurCourant().getRobots().get(this.numRobot).getOrdres()) {
			if (o != null)
				alLblRobot.add(new JLabel(new ImageIcon(getLien(o.getClass().getName()))));
			else
				alLblRobot.add(new JLabel(new ImageIcon(getLien("./images/ordres/ordre_vide.png"))));

		}
		this.setLayout(new BorderLayout());

		JPanel pnlRobot = new JPanel();
		JPanel pnlSouth = new JPanel();
		JPanel choix = new JPanel();
		this.annuler = new JButton("Annuler");
		this.valider = new JButton("Valider");
		choixRobot1 = new JLabel(new ImageIcon("./images/ordres/ordre_vide.png"));
		choixRobot2 = new JLabel(new ImageIcon("./images/ordres/ordre_vide.png"));

		for (JLabel lbl : alLblRobot) {
			pnlRobot.add(lbl);
			lbl.addMouseListener(this);
		}
		choix.add(new JLabel("1:"));
		choix.add(choixRobot1);
		choix.add(new JLabel("2:"));
		choix.add(choixRobot2);

		pnlSouth.add(this.annuler);
		pnlSouth.add(this.valider);
		this.add(pnlRobot, "North");
		this.add(choix, "Center");
		this.add(pnlSouth, "South");

		this.annuler.addActionListener(this);
		this.valider.addActionListener(this);
		this.setVisible(true);
		this.pack();

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == annuler)
			this.dispose();
		if (e.getSource() == valider) {
			int index1 = 0;
			int index2 = 0;
			for (int i = 0; i < alLblRobot.size(); i++) {
				if (alLblRobot.get(i).getIcon().equals(this.choixRobot1.getIcon()))
					index1 = i;
				if (alLblRobot.get(i).getIcon().equals(this.choixRobot2.getIcon()))
					index1 = i;
			}
			this.ctrl.distribuerInformation("Permuter", this.numRobot, "", index1, index2);
			this.dispose();
		}
	}

	public void mouseClicked(MouseEvent e) {

		for (JLabel pnl : alLblRobot) {
			if (e.getSource() == pnl) {
				if (this.indicePermutation == 0) {
					this.choixRobot1.setIcon(pnl.getIcon());

				}
				if (this.indicePermutation == 1) {
					this.choixRobot2.setIcon(pnl.getIcon());
				}
				this.indicePermutation++;
				this.indicePermutation %= 2;
			}

		}
	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

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

	public String getOrdre(String s) {
		switch (s) {
		case "./images/ordres/avancer1.png":
			s = "Avancer";
			break;
		case "./images/ordres/avancer2.png":
			s = "AvancerX2";
			break;
		case "./images/ordres/tuileGauche.png":
			s = "TournerSensAntiHoraire";
			break;
		case "./images/ordres/tuileDroite.png":
			s = "TournerSensHoraire";
			break;
		case "./images/ordres/deposerCristal.png":
			s = "Deposer";
			break;
		case "./images/ordres/chargerCristal.png":
			s = "Charger";
			break;
		}
		return s;
	}
}