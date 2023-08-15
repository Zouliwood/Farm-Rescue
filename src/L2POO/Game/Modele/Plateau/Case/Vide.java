package L2POO.Game.Modele.Plateau.Case;

import L2POO.Game.Modele.Enumeration.ListBloc;

public class Vide extends Case{

	public Vide() {
		super(ListBloc.VIDE);
	}

	@Override
	public int points() {
		return 0;
	}
		
	public String afficheConsole() {
		return "[ ] ";
	}
	
	public String afficheGraphique() {
		return "bloc/vide.png";
	}
}
