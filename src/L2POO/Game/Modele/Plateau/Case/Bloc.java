package L2POO.Game.Modele.Plateau.Case;

import java.util.Random;


import L2POO.Game.Modele.Enumeration.ListBloc;
import L2POO.Game.Modele.Enumeration.ListColor;

public class Bloc extends Case{
	

	private ListColor color;
	
	public Bloc(ListColor color) {
		super(ListBloc.BLOC);
		this.color = color;
	} 
	
	public ListColor getColor() {
		return this.color;
	}
	
	public void setColor(ListColor color) {
		this.color = color;
	}
	
	public boolean isStatusColor(ListColor state){
		return color == state;
	}

	
	@Override
	public int points() {	
		return 5*(new Random().nextInt(5)+1);
	}
		 
	public String afficheConsole() { 
		return color.getTypeAffiche();
	}
	public String afficheGraphique() {
		return color.getTypeAfficheChemin();
	}

	
	

}
