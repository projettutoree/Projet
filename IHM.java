import iut.algo.*;
import java.util.*;

public class IHM {

	private Controleur ctrl;
	
	private static int numJoueur;

	public IHM(Controleur ctrl) {
		ctrl = ctrl;
	}

	public int lireInt() {
		return Clavier.lire_int();
	}

	public String lireClavier() {
		return Clavier.lireString();
	}
	
	public int afficherDebut() {
		int nbJoueur = 0;
		Console.println("Bienvenue dans le jeu Twin Tin Bots" );
		Console.println("Combien de joueur vont-jouer ? [2-6]" );
		nbJoueur = this.lireInt();
		while (nbJoueur < 2 && nbJoueur > 6) 
		{
			Console.println("Erreur, veuillez choisir un nombre de joueur entre 2 et 6");
			nbJoueur = this.lireInt();
		}
		return nbJoueur;
	}
	
	public boolean demanderScenar() {
		boolean modeScenar;
		Console.println("Voulez-vous le mode scénario ? [T-F]" );
		modeScenar = Clavier.lire_boolean();
		return modeScenar;
	}
	
	public String demanderNomScenar() {
		String nomScenar;
		Console.println( "Comment s'appelle le fichier scénario ?" );
		nomScenar = this.lireClavier();
		return nomScenar;
	}
	
	public void demanderInformations() {
		Console.println( "Tour du joueur " + this.ctrl.getJoueurCourant() );
		Console.println( "Que voulez-vous faire ?" );
		Console.println( "Ajouter un ordre" );
		Console.println( "Permutter deux ordres" );
		Console.println( "Retirer un ordre" );
		Console.println( "Redemarrer un robot" );
		Console.println( "Passer" );
	}

	public void afficherPlateau(ArrayList<CaseHexa> alCase, int tailleMax) {
		boolean caseOccupee;
		if ((tailleMax / 2) % 4 == 0)
			Console.print( "   ");
		if ((tailleMax / 2) % 4 == 1)
			Console.print( "      ");
		for (int j = 0; j <= (Math.abs(tailleMax / 2)) / 2; j++) {
			Console.print( "      ");
		}
		for (int j = 0; j <= tailleMax - (Math.abs(tailleMax / 2)); j++) {
			Console.print( "_____ ");
		}
		Console.print( "\n");

		for (int i = 0; i <= tailleMax; i++) {
			for (int k = 0; k < 3; k++) {
				if (i % 2 != (tailleMax / 2) % 4)
					Console.print( "   ");
				for (int j = 0; j <= (Math.abs(tailleMax / 2 - i)) / 2; j++) {
					Console.print( "      ");
				}
				if (i < Math.abs(tailleMax / 2) && k == 2)
					Console.print( "__");
				else
					Console.print( "  ");
				if (k != 2) {
					for (int j = 0; j <= tailleMax - (Math.abs(tailleMax / 2 - i)); j++) {
						Console.print( "|  ");
						CaseHexa objet = null;
						if (k == 1) {
							for (CaseHexa c : alCase) {
								if (c.getPosX() == i && c.getPosY() == j)
									objet = c;
							}
						}
						if (objet != null) {
							if (objet.getJoueur() != null) {
								switch (objet.getJoueur().getId()) {
									case 1 : Console.couleurFont ( CouleurConsole.CYAN ); break;
									case 2 : Console.couleurFont ( CouleurConsole.ROUGE ); break;
									case 3 : Console.couleurFont ( CouleurConsole.BLEU ); break;
									case 4 : Console.couleurFont ( CouleurConsole.VERT ); break;
									case 5 : Console.couleurFont ( CouleurConsole.MAUVE ); break;
									case 6 : Console.couleurFont ( CouleurConsole.JAUNE ); break;
								}
							}
							if (objet.getClass().getName().equals("Robot"))
								Console.print( objet.toString());
							else
								Console.print( objet.getClass().getName().charAt(0));
							Console.normal();
						}
						else
							Console.print( " ");
						Console.print( "  ");
					}
				} else {
					for (int j = 0; j <= tailleMax - (Math.abs(tailleMax / 2 - i)); j++) {
						Console.print( "|_____");
					}
				}
				Console.print( "|");
				if (i < Math.abs(tailleMax / 2) && k == 2)
					Console.print( "__");
				Console.print( "\n");
			}
		}
	}

}
