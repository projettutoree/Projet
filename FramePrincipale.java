import java.awt.*;
import javax.swing.*;

public class FramePrincipale extends JFrame
{
	//private Controleur ctrl;
	
	private MenuBar menu;
	private PanelAnnonce pnlAnnonce;
	
	public FramePrincipale()
	{
		//this.ctrl = ctrl;
		
		this.setTitle("Twin Tin Bots");
		this.setSize(1000, 1000);
		this.setLayout( new BorderLayout() );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.menu = new MenuBar();
		this.setJMenuBar(this.menu);
		
		this.pnlAnnonce = new PanelAnnonce();
		this.add(this.pnlAnnonce, BorderLayout.NORTH);
		
		this.setVisible(true);
	}
	
	public void maj()
	{
		this.pnlAnnonce.maj();
	}
	
	public static void main(String[] args) 
	{
		FramePrincipale test = new FramePrincipale();
		
		for (int i = 0; i < 8; i++) {
			test.maj();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				
			}
		}
	}
}