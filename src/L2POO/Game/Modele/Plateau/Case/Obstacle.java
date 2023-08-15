package L2POO.Game.Modele.Plateau.Case;

import L2POO.Game.Modele.Enumeration.ListBloc;

public class Obstacle extends Case{

	public Obstacle() {
		super(ListBloc.OBSTACLE);
	}

	public String afficheConsole() {
		return "[X] ";
	}

	@Override
	public int points() {
		return 0;
	}
	
	public String afficheGraphique() {
		return "bloc/obstacle.png";
	}
}
