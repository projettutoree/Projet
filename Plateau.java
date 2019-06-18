import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import iut.algo.Clavier;

public class Plateau
{
	private ArrayList<CaseHexa> alCase;
	private ArrayList<Joueur> alJoueur;
	private Robot rob;

	private int tailleMax;

	private int nbJCourant;
	private int nbRCourant = 0;

	public Plateau() {
		this.alCase = new ArrayList<CaseHexa>();
		this.alJoueur = new ArrayList<Joueur>();
		this.init(Clavier.lire_int());
		/* this.alJoueur = new ArrayList<Joueur>(); */
		this.rob = new Robot(3,5,1);
		this.alCase.add(this.rob);
	}

	public void init(int nbJoueur) {
		try {
			Scanner sc = new Scanner(new FileReader("Regles/" + nbJoueur + "joueurs.txt"));
			String ligne = sc.nextLine();
			this.tailleMax = Integer.parseInt(ligne.substring(ligne.indexOf(":") + 1));
			while (sc.hasNextLine()) {
				ligne = sc.nextLine();
				String[] information = ligne.split(":");
				// si l'identifient est un chiffre
				if (information[0].matches("[1-6]")) {

					char identifiant = information[0].charAt(0);
					Joueur j = new Joueur(Integer.parseInt("" + identifiant));

					for (int i = 1; i < information.length; i++) {

						identifiant = information[i].charAt(0);
						information[i] = information[i].substring(1);
						if (identifiant == 'B') {
							Base base = new Base(Integer.parseInt(information[i].substring(0, information[i].indexOf(","))), Integer.parseInt(information[i].substring(information[i].indexOf(",") + 1)), j);
							this.alCase.add(base);
						} else if (identifiant == 'R') {
							int index = information[i].indexOf(",");
							int index2 = information[i].indexOf(",", index+1 );
							Robot robot = new Robot(Integer.parseInt(information[i].substring(0, index)), Integer.parseInt(information[i].substring(index+1, index2)), Integer.parseInt(information[i].substring(index2+1)));
							this.alCase.add(robot);
							j.addRobot(robot);
						}
					}
				}
				if (information[0].matches("C")) {
					for (int i = 1; i < information.length; i++) {
						int index = information[i].indexOf(",");
						int index2 = information[i].indexOf(",", index+1);
						Cristal cristal = new Cristal(Integer.parseInt(information[i].substring(0, index)), Integer.parseInt(information[i].substring(index+1, index2)), Integer.parseInt(information[i].substring(index2+1)));
						this.alCase.add(cristal);
					}
				}
			}
			sc.close();

		}
		catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	public int getJoueurCourant() { return this.nbJCourant; }
	public void changerJoueur() { this.nbJCourant = (this.nbJCourant++)%this.alJoueur.size();}

	public boolean checkDeplacement(CaseHexa objet, int dir, boolean objetPousse){
		int[] coordsCaseSvt = this.getCaseSvt(objet.getPosX(), objet.getPosY(), dir);
		CaseHexa temp       = null;
		for (CaseHexa c : this.alCase){
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1]){
				temp = c;
			}
		}
		if (temp != null){
			if (objetPousse)
				return false;
			if (!temp.estPoussable())
				return false;
			return this.checkDeplacement(temp, dir, true);
		}
		if (objetPousse){
			if (!this.estOOB(coordsCaseSvt))
				this.deplacer(objet, dir);
		}
		return !(this.estOOB(coordsCaseSvt));
	}

	public void deplacer(CaseHexa objet, int dir){
		int[] coordsCaseSvt = this.getCaseSvt(objet.getPosX(), objet.getPosY(), dir);
		objet.avancer(coordsCaseSvt[0], coordsCaseSvt[1]);
	}

	public boolean checkRamassage(Robot robotTemp)
	{
		if (robotTemp.estCharge())
			return false;
		int[] coordsCaseSvt = this.getCaseSvt(robotTemp.getPosX(), robotTemp.getPosY(), robotTemp.getDir());
		for (CaseHexa c : this.alCase)
		{
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1])
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
		if (coords[0] > this.tailleMax || coords[1] > this.tailleMax - (Math.abs(this.tailleMax/2-coords[0])))
			return true;
		return false;
	}

	public boolean checkDepot(Robot robotTemp)
	{
		if (!robotTemp.estCharge())
			return false;
		int[] coordsCaseSvt = this.getCaseSvt(robotTemp.getPosX(), robotTemp.getPosY(), robotTemp.getDir());
		for (CaseHexa c : this.alCase)
		{
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1])
			{
				if (!c.getClass().getName().equals("Base"))
					return false;
				else
					return true;
			}
		}
		return !(this.estOOB(coordsCaseSvt));
	}

	public Cristal getCristalDevant(Robot robotTemp) {
		int[] coordsCaseSvt = this.getCaseSvt(robotTemp.getPosX(), robotTemp.getPosY(), robotTemp.getDir());
		for (CaseHexa c : this.alCase)
		{
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1])
			{
				if (c.getClass().getName().equals("Cristal"))
					return (Cristal)this.alCase.remove(this.alCase.indexOf(c));
			}
		}
		return null;
	}

	public Base getBaseDevant(Robot robotTemp) {
		int[] coordsCaseSvt = this.getCaseSvt(robotTemp.getPosX(), robotTemp.getPosY(), robotTemp.getDir());
		for (CaseHexa c : this.alCase)
		{
			if (c.getPosX() == coordsCaseSvt[0] && c.getPosY() == coordsCaseSvt[1])
			{
				if (c.getClass().getName().equals("Base"))
					return (Base)this.alCase.remove(this.alCase.indexOf(c));
			}
		}
		return null;
	}

	public void deposer(Robot robotTemp) {
		Base b = this.getBaseDevant(robotTemp);
		if (b == null) {
			this.alCase.add(robotTemp.getCristal());
			int[] coordsCaseSvt = this.getCaseSvt(robotTemp.getPosX(), robotTemp.getPosY(), robotTemp.getDir());
			this.alCase.get(this.alCase.size()-1).setPosX(coordsCaseSvt[0]);
			this.alCase.get(this.alCase.size()-1).setPosY(coordsCaseSvt[1]);
			System.out.println(coordsCaseSvt[0]);
			System.out.println(coordsCaseSvt[1]);
			robotTemp.deposer();
		}
		robotTemp.deposer();
	}

	public void executerInstructions(String s)
	{
		Robot    robotTemp = this.rob;//this.alJoueur.get(this.nbJCourant).getRobots()[this.nbRCourant];
		//String[] tabInstr  = robotTemp.getInstructions();
		String[] tabInstr = {s, "", ""};
		for (int i=0; i<3; i++)
		{
			switch (tabInstr[i])
			{
				case "avancer" :
					if (this.checkDeplacement(robotTemp, robotTemp.getDir(), false))
						this.deplacer(rob, rob.getDir());
					break;
				case "tournerSensHoraire" :
					robotTemp.tourner(1);
					break;
				case "tournerSensAntiHoraire" :
					robotTemp.tourner(-1);
					break;
				case "ramasser" :
					if(this.checkRamassage(robotTemp))
						robotTemp.charger(this.getCristalDevant(robotTemp));
					break;
				case "deposer" :
					if(this.checkDepot(robotTemp))
						this.deposer(robotTemp);
					break;
				/*case "zap" :
					this.zap();
					break;*/
				default :
					break;
			}
		}
	}
	private int[] getCaseSvt(int x, int y, int dir)
	{
		int[] tabCoord = { x, y };
		switch (dir)
		{
			case 0 :
				if (tabCoord[0] > this.tailleMax/2)
					tabCoord[1]++;
				tabCoord[0]--;
				break;
			case 1 :
				tabCoord[1]++;
				break;
			case 2 :
				if (tabCoord[0] < this.tailleMax/2)
					tabCoord[1]++;
				tabCoord[0]++;
				//tabCoord[1]++;
				break;
			case 3 :
				if (tabCoord[0] >= this.tailleMax/2)
					tabCoord[1]--;
				tabCoord[0]++;
				break;
			case 4 :
				tabCoord[1]--;
				break;
			case 5 :
				if (tabCoord[0] <= this.tailleMax/2)
					tabCoord[1]--;
				tabCoord[0]--;
				break;
			default :
				break;
		}
		return tabCoord;
	}

    public String toString()
	{
		String s = "";
        	boolean caseOccupee;

		if ((this.tailleMax/2)%4 == 0)
			s += "   ";
		if ((this.tailleMax/2)%4 == 1)
			s += "      ";
		for (int j=0; j<=(Math.abs(this.tailleMax/2))/2; j++)
		{
		  s += "      ";
		}
		for (int j=0; j<=this.tailleMax - (Math.abs(this.tailleMax/2)); j++)
		{
		  s += "_____ ";
		}
		s += "\n";

		for (int i=0; i<=this.tailleMax; i++)
		{
		  for (int k=0; k<3; k++)
		  {
			if(i%2 != (this.tailleMax/2)%4)
				s += "   ";
		     for (int j=0; j<=(Math.abs(this.tailleMax/2-i))/2; j++)
			{
				s += "      ";
			}
		     if (i < Math.abs(this.tailleMax/2) && k==2)
		          s += "__";
		     else
		     	s += "  ";
		     if (k!=2)
		     {
		          for (int j=0; j<=this.tailleMax - (Math.abs(this.tailleMax/2-i)); j++)
				{
					s += "|  ";
		              CaseHexa objet = null;
		              if (k == 1)
		              {
		                  	for (CaseHexa c : this.alCase)
		                  	{
		                      	if (c.getPosX() == i && c.getPosY() == j)
		                          	objet = c;
					   	}
		              }
		              if (objet != null)
		                  	s += objet.getClass().getName().charAt(0);
		              else
		                  	s += " ";
		              		s += "  ";
					}
		      }
		      else
		      {
		          for (int j=0; j<=this.tailleMax - (Math.abs(this.tailleMax/2-i)); j++)
				{
					s += "|_____";
				}
		      }
				s+="|";
		      if (i < Math.abs(this.tailleMax/2) && k==2)
		          s += "__";
		      s+="\n";
		  	}
		}

		return s;
	}

	public void depRobot(String s){
		this.executerInstructions(s);
	}

	public static void main (String[] args)
	{
		Plateau p = new Plateau();
		while (true) {
			System.out.println(p);
			p.depRobot(Clavier.lireString());
		}
	}
}
