import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuBar extends JMenuBar implements ActionListener
{
	//Fichier
	private JMenuItem normal;
	private JMenuItem scenar;
		
	//Fichier
	private JMenuItem quitter;
	private JMenuItem nouveau;

	public MenuBar()
	{
		//Choix du mode de jeu
		JMenu mode = new JMenu ("Mode de jeu");

		//Normal
		this.normal = new JMenuItem("Normal");
		mode.add(this.normal);
		this.normal.addActionListener(this);
		
		//Scenario
		this.scenar = new JMenuItem("Scenario");
		mode.add(this.scenar);
		this.scenar.addActionListener(this);
		
		this.add(mode);
		
		//Menu Fichier
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
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.quitter)
			System.exit(0);
	}

}