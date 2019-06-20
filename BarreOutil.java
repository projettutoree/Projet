import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;

public class BarreOutil extends JToolBar
{
	
	public BarreOutil()
	{
		this.setFloatable(false);
		
		//On crée la barre d'outil et y ajoute les boutons.
		JButton button = null;
		button = makeButton("pause","Pause");
		this.add(button);
		button = makeButton("play","Jouer");
		this.add(button);
		button = makeButton("Fleche_gauche","Précédent");
		this.add(button);
		button = makeButton("Fleche_droite","Suivant");
		this.add(button);
	}

	//On crée un bouton, on lui donne une image et on renvoi ce bouton
	public JButton makeButton(String imgName, String altText)
	{
		String imgLocation = "./images/Outils/" + imgName + ".png";
		URL    imageURL    = BarreOutil.class.getResource(imgLocation);

		JButton button = new JButton();

		//On gère le cas ou l'on ne trouve pas l'image.
		if (imageURL != null) {button.setIcon(new ImageIcon(imageURL, altText));}
		else
		{
			button.setText(altText);
			System.err.println("Resource not found: " + imgLocation);
		}

		return button;
	}
}