import java.awt.*;
import javax.swing.*;

public class PanelAnnonce extends JPanel {
	private int numJoueur;
	private JLabel annonceJoueur;
	private Controleur ctrl;

	private int nbJoueur;

	public PanelAnnonce(Controleur ctrl) {
		this.ctrl = ctrl;
		this.nbJoueur = this.ctrl.getNbJoueur();

		this.numJoueur = this.ctrl.getJoueurCourant().getId();
		this.annonceJoueur = new JLabel("Tour du joueur " + this.numJoueur);

		this.add(this.annonceJoueur);
	}

	public void maj() {
		this.numJoueur = this.ctrl.getJoueurCourant().getId();
		this.annonceJoueur.setText("Tour du joueur " + this.numJoueur);
	}
}