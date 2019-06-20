import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelJoueur extends JPanel
{
	private static int numJoueur;
	
	private Color[] tabColor = { Color.RED, Color.BLUE, Color.GREEN, Color.PINK, Color.WHITE, Color.YELLOW };
	
	private JLabel nomJ;
	
	private JLabel[] actRobot1;
	private JLabel[] actRobot2;
	
	public PanelJoueur()
	{
		this.setLayout( new GridLayout(3, 1) );
		
		this.nomJ = new JLabel("Joueur " + (++numJoueur) );
		this.nomJ.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.setBackground(tabColor[numJoueur-1]);
		this.add( this.nomJ );
		
		this.actRobot1 = new JLabel[3];
		this.actRobot2 = new JLabel[3];
		
		for (int i = 0; i < 3; i++) 
		{
			this.actRobot1[i] = new JLabel( new ImageIcon("./images/ordres/ordre_vide.png") );
			this.actRobot2[i] = new JLabel( new ImageIcon("./images/ordres/ordre_vide.png") );
		}
				
		for (int i = 1; i < 3; i++) 
		{
			if (i == 1) 
			{
				JPanel pRob = new JPanel( new GridLayout(1, 4) );
				
				pRob.add( new JLabel("Robot " + i) );
				for (int j = 0; j < 3; j++) 
				{
					pRob.add(actRobot1[j]);
				}
				
				this.add(pRob);
			}
			else 
			{
				JPanel pRob = new JPanel( new GridLayout(1, 4) );
				
				pRob.add( new JLabel("Robot " + i) );
				for (int j = 0; j < 3; j++) 
				{
					pRob.add(actRobot2[j]);
				}
				
				this.add(pRob);
			}
		}
	}
	
	public void maj()
	{
		actRobot1[0].setIcon( new ImageIcon("./images/ordres/avancer1.png") );
		actRobot1[1].setIcon( new ImageIcon("./images/ordres/avancer2.png") );
		actRobot1[2].setIcon( new ImageIcon("./images/ordres/tuileGauche.png") );
		
		actRobot2[0].setIcon( new ImageIcon("./images/ordres/tuileDroite.png") );
		actRobot2[1].setIcon( new ImageIcon("./images/ordres/deposerCristal.png") );
		actRobot2[2].setIcon( new ImageIcon("./images/ordres/chargerCristal.png") );
	}
}