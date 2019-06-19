import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelJoueur extends JPanel
{
	private JScrollPane scrBar;
	private JPanel pnl;
	
	public PanelJoueur()
	{
		this.setSize(200, 700);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS ));
		
		
		for (int i = 0; i < 15; i++) {
			Panel panel = new Panel( new GridLayout(3, 1) );
			panel.add(new Label("Joueur " + i));
			panel.add(new Label("Test"));
			panel.add(new Label("Test 2"));
			this.add(panel);
		}
	}
}