package L2POO.Game.Modele.Plateau.Case.BoostSpecial;

import L2POO.Game.Modele.Enumeration.ListBoost;
import L2POO.Game.Modele.Plateau.Case.Boost;

public class Rocket extends Boost{

	public Rocket() {
		super(ListBoost.ROCKET);
	}

	@Override
	public int points() {
		return 40*5;
	}

	@Override
	public String afficheConsole() {
		return "[^] ";
	}

	@Override
	public String afficheGraphique() {
		return "boost/fusee.png";
	}


 
}
