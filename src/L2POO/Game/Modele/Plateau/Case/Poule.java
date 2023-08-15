package L2POO.Game.Modele.Plateau.Case;

import L2POO.Game.Modele.Enumeration.ListBloc;

public class Poule extends Case{

	
	public Poule() {		
		super(ListBloc.POULE);
	}

	@Override
	public int points() {
		return 150;
	}
	
	public String afficheConsole() {
		return "[$] ";
	}
	
	public String afficheGraphique() {
		return "bloc/poule.png";
	}
}