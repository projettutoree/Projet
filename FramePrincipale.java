import java.awt.*;
import javax.swing.*;

public class FramePrincipale extends JFrame {
	private Controleur ctrl;

	private MenuBar menu;
	private BarreOutil outil;
	private PanelAnnonce pnlAnnonce;
	private PanelEnsJoueur pnlEnsJoueur;
	private PanelInstruction pnlInstru;
	private PanelPlateau pnlPlateau;

	private JPanel pnlNorth;

	private JScrollPane rightScrollPane;

	public FramePrincipale(Controleur ctrl) {
		this.ctrl = ctrl;

		this.setTitle("Twin Tin Bots");
		this.setSize(1000, 700);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.menu = new MenuBar();
		this.setJMenuBar(this.menu);

		this.outil = new BarreOutil();

		this.pnlEnsJoueur = new PanelEnsJoueur(6);
		this.pnlAnnonce = new PanelAnnonce(6);
		this.pnlInstru = new PanelInstruction();
		this.rightScrollPane = new JScrollPane(this.pnlEnsJoueur);
		this.pnlPlateau = new PanelPlateau(ctrl);

		this.pnlNorth = new JPanel(new GridLayout(2, 1));

		this.pnlNorth.add(this.outil);
		this.pnlNorth.add(this.pnlAnnonce);

		this.add(this.rightScrollPane, BorderLayout.EAST);
		this.add(this.pnlNorth, BorderLayout.NORTH);
		this.add(this.pnlInstru, BorderLayout.SOUTH);
		this.add(this.pnlPlateau);

		this.setVisible(true);
	}

	public void maj() {
		this.pnlAnnonce.maj();
		this.pnlEnsJoueur.maj();
	}
}