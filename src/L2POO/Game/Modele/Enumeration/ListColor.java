package L2POO.Game.Modele.Enumeration;

import java.util.Random;
import java.awt.Color;

public enum ListColor {

	YELLOW("[J] ", "bloc/jaune.png", "boost/jauneballon.png"),
	BLUE("[B] ", "bloc/bleu.png", "boost/bleuballon.png"),
	RED("[R] ", "bloc/rouge.png", "boost/rougeballon.png"),
	GREEN("[V] ", "bloc/vert.png", "boost/vertballon.png"), 
	MAGENTA("[M] ", "bloc/magenta.png", "boost/magentaballon.png"), 
	CYAN("[C] ", "bloc/cyan.png", "boost/cyanballon.png");
	 
	 
	private final String type;
	private final String cheminImage;
	private final String cheminImageBallon;

	ListColor(String type, String cheminImage, String cheminImageBallon) {
    	this.type = type;
    	this.cheminImage = cheminImage;
    	this.cheminImageBallon = cheminImageBallon;
    }
	
	
	public String getTypeAfficheChemin() {
		return cheminImage;
	}
	
	public String getTypeBoostAfficheChemin() {
		return cheminImageBallon;
	}
    public String getTypeAffiche() {
    	return type;
    }

	public static ListColor getRandomColor() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

	public String getCheminImage() {
		return cheminImage;
	}

	public String getCheminImageBallon() {
		return cheminImageBallon;
	}
}
