import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelEnsJoueur extends JPanel
{
	//private Controleur ctrl;

	private PanelJoueur[] pnlJoueur;

	public PanelEnsJoueur()
	{
		//this.ctrl = ctrl;

		this.setSize(200, 700);
		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );

		this.pnlJoueur = new PanelJoueur[6];

		for (int i = 0; i < 6; i++)
		{
			this.pnlJoueur[i] = new PanelJoueur();
			this.add( pnlJoueur[i] );
		}
	}

	public void maj()
	{
		for (int i = 0; i < 6; i++)
		{
			//this.pnlJoueur[i].maj();
		}
	}
}
//this.ctrl.getNbJoueurs()
