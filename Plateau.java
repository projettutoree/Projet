import java.util.ArrayList;

public class Plateau
{
	private ArrayList<CaseHexa> alCase;
	private ArrayList<Joueur> alJoueur;

	private int ligMax;
	private int colMax;

	private int nbJCourant;
	private int nbRCourant;

	public Plateau()
	{
		this.alCase = new ArrayList<CaseHexa>();
		this.alJoueur = new ArrayList<Joueur>();

		this.nbJCourant = 1;
	}

	public int getJoueurCourant() { return this.nbJCourant; }
	public void changerJoueur() { ++this.nbJCourant%this.alJoueur.size();}

	public boolean checkDeplacement()
	{
		Robot roboTemp = this.alJoueur.get(this.nbJCourant).get(nbRCourant);
	}

	public boolean checkRamassage()
	{
		Robot roboTemp = this.alJoueur.get(this.nbJCourant).get(nbRCourant);
	}

	public boolean checkDepot()
	{
		Robot roboTemp      = this.alJoueur.get(this.nbJCourant).get(nbRCourant);
		int[] coordsCaseSvt = this.getCaseSvt(roboTemp);

		if ()

		for (CaseHexa c : this.alCase)
		{
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosX() == coordsCaseSvt[1])
			{
				if (!c.getClass().getName().equals("Base"))
					return false;
			}
		}

		return true;
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
	}
}
