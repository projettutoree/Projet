import java.awt.*;
import javax.swing.*;

public class PanelAnnonce extends JPanel 
{
	//private Controleur ctrl;
	
	private int numJoueur;
	private JLabel annonceJoueur;
	
	public PanelAnnonce()
	{
		//this.ctrl = ctrl;
		
		this.numJoueur = 1;
		this.annonceJoueur = new JLabel("Tour du joueur " + this.numJoueur);
		
		this.add(this.annonceJoueur);
	}
	
	public void maj()
	{
		// int tmp = this.ctrl.getNumJCourant();
		int tmp = (this.numJoueur++)%7;
		if (tmp == 0) 
		{
			tmp = 1;
		}
		this.annonceJoueur.setText("Tour du joueur " + tmp);
	}
}