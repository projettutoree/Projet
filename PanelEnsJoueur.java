import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelEnsJoueur extends JPanel
{
	private PanelJoueur[] pnlJoueur;
	
	private int nbJoueur;
	
	public PanelEnsJoueur(int nbJoueur)
	{
		this.setSize(200, 700);
		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS) );
		
		this.nbJoueur = nbJoueur;
		this.pnlJoueur = new PanelJoueur[this.nbJoueur];
		
		for (int i = 0; i < this.nbJoueur; i++) 
		{
			this.pnlJoueur[i] = new PanelJoueur();
			this.add( pnlJoueur[i] );
		}
	}
	
	public void maj()
	{
		for (int i = 0; i < this.nbJoueur; i++) 
		{
			this.pnlJoueur[i].maj();
		}
	}
}