import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BarreOutil extends JToolBar implements ActionListener {

	private Controleur ctrl;

	private JButton pause;
	private JButton play;
	private JButton precedent;
	private JButton suivant;

	private JButton ajouter;
	private JButton retirer;
	private JButton permuter;
	private JButton clear;
	private JButton passer;

	private List list;

	public BarreOutil(Controleur ctrl) {
		this.ctrl = ctrl;
		this.setFloatable(false);
		this.setLayout(new GridLayout(1, 2));

		JPanel pnlGauche = new JPanel();
		JPanel pnlDroit = new JPanel();

		// On crée la barre d'outil et y ajoute les boutons.
		JButton button = null;
		this.pause = makeButton("pause", "Pause");
		this.play = makeButton("play", "Jouer");
		this.precedent = makeButton("Fleche_gauche", "Précédent");
		this.suivant = makeButton("Fleche_droite", "Suivant");

		this.ajouter = new JButton("Ajouter");
		this.retirer = new JButton("Retirer");
		this.permuter = new JButton("Permuter");
		this.clear = new JButton("Clear");
		this.passer = new JButton("Passer");

		this.list = new List(1, false);
		this.list.add("Robot 1");
		this.list.add("Robot 2");
		this.list.select(0);

		pnlGauche.add(this.pause);
		pnlGauche.add(this.play);
		pnlGauche.add(this.precedent);
		pnlGauche.add(this.suivant);

		pnlDroit.add(this.list);
		pnlDroit.add(this.ajouter);
		pnlDroit.add(this.retirer);
		pnlDroit.add(this.permuter);
		pnlDroit.add(this.clear);
		pnlDroit.add(this.passer);

		this.pause.addActionListener(this);
		this.play.addActionListener(this);
		this.suivant.addActionListener(this);

		this.clear.addActionListener(this);
		this.passer.addActionListener(this);
		this.ajouter.addActionListener(this);
		this.retirer.addActionListener(this);
		this.permuter.addActionListener(this);

		this.add(pnlGauche);
		this.add(pnlDroit);

	}

	// On crée un bouton, on lui donne une image et on renvoi ce bouton
	public JButton makeButton(String imgName, String altText) {
		String imgLocation = "./images/Outils/" + imgName + ".png";
		// URL imageURL = BarreOutil.class.getResource(imgLocation);

		JButton button = new JButton();
		button.setIcon(new ImageIcon(imgLocation, altText));

		return button;
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.pause)
			this.ctrl.pause();

		if (e.getSource() == this.play)
			this.ctrl.play();
		if (e.getSource() == this.suivant) {
			this.ctrl.play();
			try {
				Thread.sleep(10);
			} catch (Exception a) {
				// TODO: handle exception
			}
			this.ctrl.pause();
		}

		int idRobot = 0;

		if (this.list.getSelectedItem().equals("Robot 2")) {
			idRobot = 1;
		}
		String s;

		if (e.getSource() == this.clear)
			this.ctrl.distribuerInformation("Redemarrer", idRobot, "", -1, -1);
		if (e.getSource() == this.passer)
			this.ctrl.distribuerInformation("Passer", idRobot, "", -1, -1);

		if (e.getSource() == this.ajouter) {
			new Ajouter(this.ctrl, idRobot);
		}
		if (e.getSource() == this.retirer) {
			new Retirer(this.ctrl, idRobot);
		}
		if (e.getSource() == this.permuter) {
			new Permuter(this.ctrl, idRobot);
		}

	}

}