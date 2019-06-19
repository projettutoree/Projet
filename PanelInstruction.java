import java.awt.*;
import javax.swing.*;

public class PanelInstruction extends JPanel
{
	private JLabel[] tabInstru;
	
	public PanelInstruction()
	{
		this.setLayout( new FlowLayout(FlowLayout.CENTER, 20, 0) );
		
		this.tabInstru = new JLabel[6];
		for (int i = 0; i < 6; i++) 
		{
			this.tabInstru[i] = new JLabel();
		}
		
		this.initialise();
		
		for (int i = 0; i < 6; i++) 
		{
			this.add(this.tabInstru[i]);
		}
	}
	
	private void initialise()
	{
		this.tabInstru[0].setIcon( new ImageIcon("./images/ordres/avancer1.png") );
		this.tabInstru[1].setIcon( new ImageIcon("./images/ordres/avancer2.png") );
		this.tabInstru[2].setIcon( new ImageIcon("./images/ordres/tuileGauche.png") );
		this.tabInstru[3].setIcon( new ImageIcon("./images/ordres/tuileDroite.png") );
		this.tabInstru[4].setIcon( new ImageIcon("./images/ordres/deposerCristal.png") );
		this.tabInstru[5].setIcon( new ImageIcon("./images/ordres/chargerCristal.png") );
	}
}