package L2POO.Game.Modele.Plateau.Case;

import L2POO.Game.Modele.Enumeration.ListBloc;
import L2POO.Game.Modele.Enumeration.ListBoost;

public abstract class Boost extends Case{

	private ListBoost boost;
	 
	public Boost(ListBoost boost) {
		super(ListBloc.BOOST);
		this.boost = boost;
	}
	 
	public ListBoost getBoost() {
		return this.boost; 
	}
	

	
}
