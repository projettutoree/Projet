import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameNbJoueur extends JFrame implements ActionListener
{
	private List lstNbJ;
	private JButton choix;
	
	private int nbJoueur;
	
	public FrameNbJoueur()
	{
		this.setTitle("Nombre de joueur");
		this.setSize(200, 150);
		this.setLocation(550, 300);
		this.setLayout( new BorderLayout() );
		
		this.lstNbJ = new List(5);
		for (int i = 2; i < 7; i++) 
		{
			this.lstNbJ.add(i + " joueurs");
		}
		
		this.choix = new JButton("Choix");
		
		this.nbJoueur = 0;
		
		this.choix.addActionListener(this);
		this.lstNbJ.addActionListener(this);
		
		this.add(this.choix, BorderLayout.SOUTH);
		this.add(this.lstNbJ);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.choix)
		{
			String nbJ = this.lstNbJ.getSelectedItem();
			
			if (nbJ == null) {
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null, "Veuillez choisir un nombre de joueur", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else {
				this.nbJoueur = Integer.parseInt( "" + nbJ.charAt(0) );
				this.dispose();
			}
		}
	}
	
	public int getNbJoueurs() {return this.nbJoueur;}
}