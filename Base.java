import java.util.ArrayList;

public class Base extends CaseHexa {

	Joueur monJoueur;
	ArrayList<Cristal> alCristaux;

	public Base(int posX, int posY, Joueur monJoueur) {
		super(posX, posY);
		this.monJoueur = monJoueur;
		this.alCristaux = new ArrayList<Cristal>();
		this.poussable = false;
	}

	public void recoitCristal(Cristal cristal) {
		this.alCristaux.add(cristal);
		this.monJoueur.gagnePoint(cristal.getValeur());
	}

	// Méthode qui retourne le total de cristaux de valeur 2 et 3
	// qui sont dans la base
	public int calculerTypeCristaux() {
		int nb =  0;
		for(Cristal c : alCristaux) {
			if(c.getValeur() <= 3)
				nb++;
		}
		return nb;
	}

	public ArrayList<Cristal> getCristaux() {
		return this.alCristaux;
	}

	public Joueur getJoueur() {
		return this.monJoueur;
	}
}
