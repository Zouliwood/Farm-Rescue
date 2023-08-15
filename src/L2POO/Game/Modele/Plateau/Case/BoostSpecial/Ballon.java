package L2POO.Game.Modele.Plateau.Case.BoostSpecial;

import java.awt.Color;


import L2POO.Game.Modele.Enumeration.ListBoost;
import L2POO.Game.Modele.Enumeration.ListColor;
import L2POO.Game.Modele.Plateau.Case.Boost;

public class Ballon extends Boost{
	
	private ListColor color; 
	
	public Ballon(ListColor color) {
		super(ListBoost.BALLON);
		this.color = color;
	}
	public String afficheConsole() {
		return "[" + color.getTypeAffiche().replace("[", "").replace("]", "").replace(" ","") + "*] ";
	}
	 
	public ListColor getColor() {
		return this.color;
	}
	@Override
	public int points() {
		return 360;
	}
	
	public Color afficheWindow() {
		return Color.WHITE;
	}

	@Override
	public String afficheGraphique() {
		return color.getCheminImageBallon();
	}
}
