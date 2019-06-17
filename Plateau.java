import java.util.ArrayList;

public class Plateau
{
	/*private ArrayList<CaseHexa> alCase;
	private ArrayList<Joueur> alJoueur;*/

	private int numLigMax;
	private int numColMax;

	private int nbJCourant;
	private int nbRCourant;

	public Plateau()
	{
		/*this.alCase = new ArrayList<CaseHexa>();
		this.alJoueur = new ArrayList<Joueur>();*/

		this.numColMax = 10;
		this.numLigMax = 10;

		this.nbJCourant = 1;
	}

	/*public int getJoueurCourant() { return this.nbJCourant; }
	public void changerJoueur() { this.nbJCourant = (this.nbJCourant++)%this.alJoueur.size();}

	public boolean checkDeplacement()
	{
		Robot roboTemp = this.alJoueur.get(this.nbJCourant).get(nbRCourant);
	}

	public boolean checkRamassage()
	{
		Robot roboTemp = this.alJoueur.get(this.nbJCourant).get(nbRCourant);
		int[] coordsCaseSvt = this.getCaseSvt(roboTemp);

		for (CaseHexa c : this.alCase)
		{
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosX() == coordsCaseSvt[1])
			{
				if (c.getClass().getName().equals("Cristal"))
					return true;
			}
		}

		return false;
	}

	public boolean estOOB(int[] coords)
	{
		if (coords[0] < 0 || coords[1] < 0)
			return true;
		if (coords[0] > nbLigMax || coords[1] > this.numColMax - (Math.abs(this.numColMax/2-coords[0])))
			return true;
		return false;
	}

	public boolean checkDepot()
	{
		Robot roboTemp      = this.alJoueur.get(this.nbJCourant).get(nbRCourant);
		int[] coordsCaseSvt = this.getCaseSvt(roboTemp);

		for (CaseHexa c : this.alCase)
		{
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosX() == coordsCaseSvt[1])
			{
				if (!c.getClass().getName().equals("Base"))
					return false;
			}
		}

		return !(this.estOOB(coordsCaseSvt));
	}

	public void excuterInstructions()
	{
		String[] tabInstr = this.alJoueur.get(this.nbJCourant).get(nbRCourant).getInstructions();
		Robot    roboTemp = this.alJoueur.get(this.nbJCourant).get(nbRCourant);
		for (int i=0; i<3; i++)
		{
			switch (tabInstr[i])
			{
				case "avancer" :
					if (this.checkDeplacement())
						roboTemp.deplacer();
					break;
				case "tournerSensHoraire" :
					roboTemp.tourner(1);
					break;
				case "tournerSensAntiHoraire" :
					roboTemp.tourner(-1);
					break;
				case "ramasser" :
					if(this.checkRamassage())
						roboTemp.charger();
					break;
				case "deposer" :
					if(this.checkDepot())
						roboTemp.decharger();
					break;
				case "zap" :
					this.zap();
					break;
				default :
					break;
			}
		}
	}

	private int[] getCaseSvt(Robot robot)
	{
		int[] tabCoord = { robot.getPosX(), robot.getPosY() };

		switch (robot.getDir())
		{
			case 0 :
				tabCoord[0]--;
				break;
			case 1 :
				tabCoord[1]++;
				break;
			case 2 :
				tabCoord[0]++;
				tabCoord[1]++;
				break;
			case 3 :
				tabCoord[0]++;
				break;
			case 4 :
				tabCoord[1]--;
				break;
			case 5 :
				tabCoord[0]--;
				tabCoord[1]--;
				break;
			default :
				break;
		}

		return tabCoord;
	}*/

	public String toString()
	{
		String s = "";

		for (int i=0; i<=this.numLigMax; i++)
		{
			for (int k=0; k<3; k++)
			{
				if(i%2 == 0)
					s += " ";
				for (int j=0; j<(Math.abs(this.numColMax/2-i))/2; j++)
				{
					s += "   ";
				}
				if (i != 1)
				{
					for (int j=0; j<this.numColMax - (Math.abs(this.numColMax/2-i)); j++)
					{
						s += "|___| ";
					}
				}
				else
				{
					for (int j=0; j<this.numColMax - (Math.abs(this.numColMax/2-i)); j++)
					{
						s += "|  | ";
					}
				}
				s+="\n";
			}
			s += "\n";
		}

		return s;
	}

	public static void main (String[] args)
	{
		System.out.println(new Plateau());
	}
}
