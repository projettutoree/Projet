
import javax.swing.SwingUtilities;
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
	private PanelReserve pnlReserve;

	private JPanel pnlNorth;

	private JScrollPane rightScrollPane;

	public FramePrincipale(Controleur ctrl) {
		this.ctrl = ctrl;

		this.setTitle("Twin Tin Bots");
		this.setSize(1200, 900);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.menu = new MenuBar();
		this.setJMenuBar(this.menu);

		this.outil = new BarreOutil(ctrl);

		this.pnlEnsJoueur = new PanelEnsJoueur(ctrl);
		this.pnlAnnonce = new PanelAnnonce(ctrl);
		this.pnlInstru = new PanelInstruction(ctrl);
		this.rightScrollPane = new JScrollPane(this.pnlEnsJoueur);
		this.pnlPlateau = new PanelPlateau(ctrl);
		this.pnlReserve = new PanelReserve(ctrl);

		this.pnlNorth = new JPanel(new GridLayout(2, 1));

		this.pnlNorth.add(this.outil);
		this.pnlNorth.add(this.pnlAnnonce);

		this.add(this.rightScrollPane, BorderLayout.EAST);
		this.add(this.pnlNorth, BorderLayout.NORTH);
		this.add(this.pnlInstru, BorderLayout.SOUTH);
		this.add(this.pnlReserve, BorderLayout.WEST);
		this.add(this.pnlPlateau);

		this.setVisible(true);
	}

	public void maj() {
		// SwingUtilities.updateComponentTreeUI(this);
		this.pnlAnnonce.maj();
		this.pnlEnsJoueur.maj();
		this.pnlPlateau.maj();
		this.pnlInstru.maj();
		this.pnlReserve.maj();
		this.revalidate();
	}
}