import java.awt.*;
import javax.swing.*;

public class PanelAnnonce extends JPanel 
{
	private int numJoueur;
	private JLabel annonceJoueur;
	
	private int nbJoueur;
	
	public PanelAnnonce(int nbJoueur)
	{
		this.nbJoueur = nbJoueur;
		
		this.numJoueur = 1;
		this.annonceJoueur = new JLabel("Tour du joueur " + this.numJoueur);
		
		this.add(this.annonceJoueur);
	}
	
	public void maj()
	{
		int tmp = (this.numJoueur++)%this.nbJoueur;
		if (tmp == 0) 
		{
			tmp = 1;
		}
		this.annonceJoueur.setText("Tour du joueur " + tmp);
	}
}