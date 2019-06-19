import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuBar extends JMenuBar implements ActionListener
{
	//Fichier
	private JMenuItem quitter;
	private JMenuItem nouveau;

	//A propos de
	private JMenuItem aProposDe;

	public MenuBar()
	{
		//Déclaration du menu Fichier
		JMenu fichier = new JMenu ("Fichier");

		//Nouveau
		this.nouveau = new JMenuItem("Nouveau");
		fichier.add(this.nouveau);
		this.nouveau.addActionListener(this);
		this.add(fichier);
		
		//Quitter
		this.quitter = new JMenuItem("Quitter");
		fichier.add(this.quitter);
		this.quitter.addActionListener(this);
		this.add(fichier);

		//Déclaration du menu a propos
		this.aProposDe = new JMenuItem ("A propos de ...");
		this.add(this.aProposDe);
		this.aProposDe.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.quitter)
			System.exit(0);
	}
}