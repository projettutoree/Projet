import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelEnsJoueur extends JPanel {
	private PanelJoueur[] pnlJoueur;

	private int nbJoueur;
	private Controleur ctrl;

	public PanelEnsJoueur(Controleur ctrl) {
		this.ctrl = ctrl;

		this.setSize(400, 900);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.nbJoueur = this.ctrl.getNbJoueur();
		this.pnlJoueur = new PanelJoueur[this.nbJoueur];

		for (int i = 0; i < this.nbJoueur; i++) {
			this.pnlJoueur[i] = new PanelJoueur(ctrl);
			this.add(pnlJoueur[i]);
		}
	}

	public void maj() {
		for (int i = 0; i < this.nbJoueur; i++) {
			this.pnlJoueur[i].maj();
		}
		
	}
}