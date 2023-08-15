package L2POO.Game.Modele.Plateau.Case.BoostSpecial;

import L2POO.Game.Modele.Enumeration.ListBoost;
import L2POO.Game.Modele.Plateau.Case.Boost;

public class Bombe extends Boost{
		
	public Bombe() {
		super(ListBoost.BOMBE);
	}
	@Override
	public int points() {return 360;}
	@Override
	public String afficheGraphique() {return "boost/bombe.png";}
	public String afficheConsole() {return "[+] ";}
	

}