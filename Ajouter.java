import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Ajouter extends JFrame implements ActionListener, MouseListener {

	private Controleur ctrl;

	private int numRobot;

	private ArrayList<JLabel> alLblJoueur;
	private ArrayList<JLabel> alLblRobot;

	private JLabel choixJoueur, choixRobot;

	private JButton annuler;
	private JButton valider;

	public Ajouter(Controleur ctrl, int numRobot) {
		this.ctrl = ctrl;
		this.numRobot = numRobot;
		this.alLblJoueur = new ArrayList<JLabel>();
		this.alLblRobot = new ArrayList<JLabel>();


		for (Ordre o : this.ctrl.getJoueurCourant().getRobots().get(this.numRobot).getOrdres()) {
			if (o != null)
				alLblRobot.add(new JLabel(new ImageIcon(getLien(o.getClass().getName()))));
			else
				alLblRobot.add(new JLabel(new ImageIcon(getLien("./images/ordres/ordre_vide.png"))));
		}

		for (Ordre o : this.ctrl.getJoueurCourant().getAlOrdre()) {
			if (o != null)
				alLblJoueur.add(new JLabel(new ImageIcon(getLien(o.getClass().getName()))));
			else
				alLblJoueur.add(new JLabel(new ImageIcon(getLien("./images/ordres/ordre_vide.png"))));
		}

		this.setLayout(new BorderLayout());

		JPanel pnlRobot = new JPanel();
		JPanel pnlJoueur = new JPanel();
		JPanel pnlSouth = new JPanel();
		JPanel pnlCenter = new JPanel(new GridLayout(2, 1));
		JPanel choix = new JPanel();
		this.annuler = new JButton("Annuler");
		this.valider = new JButton("Valider");
		choixJoueur = new JLabel(new ImageIcon("./images/ordres/ordre_vide.png"));
		choixRobot = new JLabel(new ImageIcon("./images/ordres/ordre_vide.png"));
		for (JLabel lbl : alLblJoueur) {
			pnlJoueur.add(lbl);
			lbl.addMouseListener(this);
		}
		for (JLabel lbl : alLblRobot) {
			pnlRobot.add(lbl);
			lbl.addMouseListener(this);
		}
		choix.add(new JLabel("Joueur choix :"));
		choix.add(choixJoueur);
		choix.add(new JLabel("Place choisie:"));
		choix.add(choixRobot);

		pnlSouth.add(this.annuler);
		pnlSouth.add(this.valider);
		this.add(pnlRobot, "North");
		pnlCenter.add(pnlJoueur, "Center");
		pnlCenter.add(choix);
		this.add(pnlCenter);
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
			int index = 0;
			for (int i = 0; i < alLblRobot.size(); i++) {
				if (alLblRobot.get(i).getIcon().equals(this.choixRobot.getIcon()))
					index = i;
			}
			this.ctrl.distribuerInformation("Ajouter", this.numRobot, getOrdre(choixJoueur.getIcon().toString()), index,
					-1);
			this.dispose();
		}
	}

	public void mouseClicked(MouseEvent e) {
		for (JLabel pnl : alLblJoueur) {
			if (e.getSource() == pnl)
				this.choixJoueur.setIcon(pnl.getIcon());

		}
		for (JLabel pnl : alLblRobot) {
			if (e.getSource() == pnl)
				this.choixRobot.setIcon(pnl.getIcon());

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