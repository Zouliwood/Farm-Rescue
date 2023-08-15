package L2POO.Game.Modele.Plateau.Case;


import java.io.Serializable;
import L2POO.Game.Modele.Enumeration.ListBloc;

public abstract class Case implements Serializable{

	private ListBloc b;
	
	Case(ListBloc b){
		this.b = b;
	} 
	
	public abstract int points();
	public abstract String afficheConsole();
	public abstract String afficheGraphique();
	
	public ListBloc getType() {return this.b; }
	public void setType(ListBloc courant) {this.b = courant;}	
	public boolean isStatusBloc(ListBloc state){return b == state;}

}
