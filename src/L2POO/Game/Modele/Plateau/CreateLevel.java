package L2POO.Game.Modele.Plateau;

import java.io.IOException;

import L2POO.Game.Modele.SaveObject;
import L2POO.Game.Modele.Enumeration.ListColor;
import L2POO.Game.Modele.Plateau.Case.Bloc;
import L2POO.Game.Modele.Plateau.Case.Obstacle;
import L2POO.Game.Modele.Plateau.Case.Poule;
import L2POO.Game.Modele.Plateau.Case.BoostSpecial.Ballon;
import L2POO.Game.Modele.Plateau.Case.BoostSpecial.Bombe;
import L2POO.Game.Modele.Plateau.Case.BoostSpecial.Rocket;

public class CreateLevel {
	 
	
	/*
	 * Cette classe ne sera plus utilisée.
	 * Elle nous a simplement permit de generer les niveaux.
	 * 
	 * Nous prenons le choix de la laisser pour vous montrer
	 * comment nous avons procédé pour créer nos niveaux.
	 */
	public static Plateau createLevel_1(String name) throws IOException {
		Plateau p = new Plateau(new Settings(5,5,5, 1));
		p.setObjectAtLocation(0,0, new Poule());
		p.setSquareObjectAtLocation(0,1, new Bloc(ListColor.YELLOW));
		p.setSquareObjectAtLocation(0,3, new Bloc(ListColor.RED));
		p.setSquareObjectAtLocation(3,1, new Bloc(ListColor.YELLOW));
		p.setSquareObjectAtLocation(3,3, new Bloc(ListColor.RED));
		p.setLineYAtLocation(2,2, new Bloc(ListColor.BLUE), 5);
		SaveObject.serialisation("niveaux/separation/" + name + ".db");
		return p;
	}
	
	   public static Plateau createLevel_2(String name) throws IOException {
	        Plateau p = new Plateau(new Settings(5,5,5, 2));
	        p.setObjectAtLocation(0,1, new Poule());
	        p.setObjectAtLocation(3,2, new Poule());
	        p.setLineXAtLocation(1,4, new Bloc(ListColor.GREEN), 4);
	        p.setObjectAtLocation(0,3, new Bloc(ListColor.GREEN));
	        p.setLineXAtLocation(1,3, new Bloc(ListColor.BLUE), 2);
	        p.setLineXAtLocation(3,3, new Bloc(ListColor.RED), 2);
	        p.setObjectAtLocation(4,2, new Bloc(ListColor.RED));
	        p.setObjectAtLocation(0,2, new Bloc(ListColor.RED));
	        p.setLineYAtLocation(1,1, new Bloc(ListColor.BLUE), 2);
	        p.setLineYAtLocation(1,1, new Bloc(ListColor.BLUE), 2);
	        p.setLineYAtLocation(2,1, new Bloc(ListColor.YELLOW), 2);
	        p.setLineXAtLocation(3,1, new Bloc(ListColor.YELLOW), 2);
	        p.setObjectAtLocation(4,0, new Bloc(ListColor.YELLOW));
	        p.setObjectAtLocation(0,4, new Rocket());
	        SaveObject.serialisation("niveaux/separation/" + name + ".db");
	        return p;
	    }     
	
	    public static Plateau createLevel_3(String name) throws IOException {
	        Plateau p = new Plateau(new Settings(6,4,5, 3));
	        p.setSquareObjectAtLocation(0, 4, new Bloc(ListColor.GREEN));
	        p.setSquareObjectAtLocation(2, 4, new Bloc(ListColor.YELLOW));
	        p.setLineYAtLocation(0,0, new Bloc(ListColor.RED), 4);
	        p.setObjectAtLocation(3,0, new Poule());
	        p.setObjectAtLocation(3,1, new Bloc(ListColor.GREEN));
	        p.setObjectAtLocation(3,3, new Bloc(ListColor.RED));
	        p.setObjectAtLocation(2,3, new Bloc(ListColor.GREEN));
	        p.setObjectAtLocation(1,2, new Bloc(ListColor.GREEN));
	        p.setObjectAtLocation(1,1, new Bloc(ListColor.YELLOW));
	        p.setObjectAtLocation(2,1, new Bloc(ListColor.RED));
	        p.setObjectAtLocation(1,3, new Bloc(ListColor.MAGENTA));
	        p.setObjectAtLocation(2,2, new Bombe());//changer en bombe
	        p.setObjectAtLocation(3,2, new Bloc(ListColor.MAGENTA));
	        SaveObject.serialisation("niveaux/separation/" + name + ".db");
	        return p;
	    }
    
    
	    public static Plateau createLevel_4(String name) throws IOException {
	        Plateau p = new Plateau(new Settings(9,5,15,3, 4));
	        p.setLineYAtLocation(0, 0, new Bloc(ListColor.CYAN), 3);
	        p.setObjectAtLocation(1, 2, new Bloc(ListColor.CYAN));
	        p.setLineXAtLocation(3, 3, new Bloc(ListColor.BLUE), 2);
	        p.setLineXAtLocation(3, 6, new Bloc(ListColor.BLUE), 2);
	        p.setObjectAtLocation(3, 7, new Bloc(ListColor.BLUE));
	        p.setLineXAtLocation(3, 0, new Bloc(ListColor.RED), 2);
	        p.setObjectAtLocation(4, 1, new Bloc(ListColor.RED));
	        p.setObjectAtLocation(0, 3, new Bloc(ListColor.RED));
	        p.setLineXAtLocation(2, 8, new Bloc(ListColor.RED), 3);
	        p.setObjectAtLocation(4, 7, new Bloc(ListColor.RED));
	        p.setObjectAtLocation(2, 7, new Bloc(ListColor.RED));
	        p.setObjectAtLocation(0, 7, new Bloc(ListColor.RED));
	        p.setLineXAtLocation(3, 5, new Bloc(ListColor.YELLOW), 2);
	        p.setLineXAtLocation(3, 2, new Bloc(ListColor.YELLOW), 2);
	        p.setObjectAtLocation(3, 1, new Bloc(ListColor.YELLOW));
	        p.setLineYAtLocation(2, 3, new Bloc(ListColor.YELLOW), 2);
	        p.setLineXAtLocation(1, 0, new Bloc(ListColor.MAGENTA), 2);
	        p.setObjectAtLocation(1, 1, new Bloc(ListColor.MAGENTA));
	        p.setObjectAtLocation(2, 2, new Bloc(ListColor.MAGENTA));
	        p.setLineXAtLocation(3, 4, new Obstacle(), 2);
	        p.setLineYAtLocation(1, 6, new Obstacle(), 3);
	        p.setLineYAtLocation(0, 4, new Bloc(ListColor.GREEN), 2);
	        p.setLineXAtLocation(1, 5, new Bloc(ListColor.MAGENTA), 2);
	        p.setObjectAtLocation(2, 6, new Bloc(ListColor.MAGENTA));
	        p.setObjectAtLocation(1, 4, new Bloc(ListColor.MAGENTA));
	        p.setObjectAtLocation(0, 8, new Rocket());
	        p.setObjectAtLocation(2, 1, new Poule());
	        p.setObjectAtLocation(1, 3, new Poule());
	        p.setObjectAtLocation(0, 6, new Poule());
	        SaveObject.serialisation("niveaux/separation/" + name + ".db");
	        return p;
	    } 
	    
		public static Plateau createLevel_5(String name) throws IOException {
			Plateau p = new Plateau(new Settings(10,5,15, 5, 5));
			p.setLineXAtLocation(0, 5, new Bloc(ListColor.GREEN), 5);
			p.setLineXAtLocation(0, 4, new Bloc(ListColor.YELLOW), 2);
			p.setLineXAtLocation(3, 4, new Bloc(ListColor.YELLOW), 2);
			p.setObjectAtLocation(0, 6, new Bloc(ListColor.GREEN));
			p.setObjectAtLocation(2, 6, new Bloc(ListColor.GREEN));
			p.setObjectAtLocation(4, 6, new Bloc(ListColor.GREEN));
			p.setObjectAtLocation(1, 7, new Bloc(ListColor.GREEN));
			p.setObjectAtLocation(3, 7, new Bloc(ListColor.GREEN));
			p.setObjectAtLocation(5, 7, new Bloc(ListColor.GREEN));
			p.setObjectAtLocation(3, 7, new Bloc(ListColor.GREEN));
			p.setObjectAtLocation(1, 6, new Bloc(ListColor.BLUE));
			p.setObjectAtLocation(3, 6, new Bloc(ListColor.BLUE));
			p.setObjectAtLocation(0, 8, new Bloc(ListColor.BLUE));
			p.setObjectAtLocation(2, 8, new Bloc(ListColor.BLUE));
			p.setObjectAtLocation(4, 8, new Bloc(ListColor.BLUE));
			p.setObjectAtLocation(1, 8, new Bloc(ListColor.RED));
			p.setObjectAtLocation(3, 8, new Bloc(ListColor.RED));
			p.setObjectAtLocation(5, 8, new Bloc(ListColor.RED));
			p.setObjectAtLocation(0, 9, new Bloc(ListColor.RED));
			p.setObjectAtLocation(2, 9, new Bloc(ListColor.RED));
			p.setObjectAtLocation(4, 9, new Bloc(ListColor.RED));
			p.setObjectAtLocation(1, 9, new Bloc(ListColor.BLUE));
			p.setObjectAtLocation(3, 9, new Bloc(ListColor.BLUE));
			p.setObjectAtLocation(5, 9, new Bloc(ListColor.BLUE));
			p.setLineXAtLocation(0, 0, new Bloc(ListColor.MAGENTA), 3);
			p.setLineXAtLocation(0, 2, new Bloc(ListColor.MAGENTA), 2);
			p.setObjectAtLocation(2, 1, new Bloc(ListColor.MAGENTA));
			p.setObjectAtLocation(0, 3, new Bloc(ListColor.MAGENTA));
			p.setLineXAtLocation(2, 2, new Bloc(ListColor.CYAN), 2);
			p.setObjectAtLocation(2, 3, new Bloc(ListColor.CYAN));
			p.setObjectAtLocation(2, 3, new Bloc(ListColor.CYAN));
			p.setObjectAtLocation(1, 3, new Bloc(ListColor.YELLOW));
			p.setObjectAtLocation(3, 3, new Bloc(ListColor.YELLOW));
			p.setLineYAtLocation(4, 1, new Bloc(ListColor.RED), 3);
			p.setLineYAtLocation(3, 0, new Bloc(ListColor.RED), 2);
			p.setObjectAtLocation(2, 4, new Bloc(ListColor.RED));
			p.setObjectAtLocation(0, 1, new Bloc(ListColor.RED));			
			p.setObjectAtLocation(0, 7, new Ballon(ListColor.BLUE));

			p.setObjectAtLocation(2, 7, new Bloc(ListColor.BLUE));
			p.setObjectAtLocation(4, 7, new Bloc(ListColor.BLUE));

			p.setObjectAtLocation(1, 1, new Poule());
			p.setObjectAtLocation(4, 0, new Poule());
			SaveObject.serialisation("niveaux/separation/" + name + ".db");
			return p;
		}
		
		public static Plateau createLevel_6(String name) throws IOException {
			Plateau p = new Plateau(new Settings(12,5,25, 4, 6));
			p.setLineYAtLocation(0, 8, new Obstacle(), 4);
			p.setLineYAtLocation(4, 8, new Obstacle(), 4);
			p.setObjectAtLocation(1, 11, new Bloc(ListColor.YELLOW));
			p.setObjectAtLocation(3, 11, new Bloc(ListColor.YELLOW));
			p.setObjectAtLocation(1, 9, new Bloc(ListColor.YELLOW));
			p.setObjectAtLocation(3, 9, new Bloc(ListColor.YELLOW));
			p.setObjectAtLocation(1, 10, new Bloc(ListColor.RED));
			p.setObjectAtLocation(3, 10, new Bloc(ListColor.RED));
			p.setObjectAtLocation(2, 11, new Bloc(ListColor.RED));
			p.setObjectAtLocation(2, 9, new Bloc(ListColor.RED));
			p.setObjectAtLocation(2, 10, new Ballon(ListColor.BLUE));
			p.setLineXAtLocation(1, 5, new Obstacle(), 3);
			p.setObjectAtLocation(2, 7, new Rocket());
			p.setLineXAtLocation(0, 7, new Bloc(ListColor.GREEN), 2);
			p.setLineXAtLocation(0, 4, new Bloc(ListColor.GREEN), 2);
			p.setObjectAtLocation(0, 5, new Bloc(ListColor.GREEN));
			p.setLineXAtLocation(2, 6, new Bloc(ListColor.BLUE), 2);
			p.setObjectAtLocation(3, 7, new Bloc(ListColor.BLUE));
			p.setObjectAtLocation(4, 2, new Bloc(ListColor.CYAN));
			p.setObjectAtLocation(4, 5, new Bloc(ListColor.CYAN));
			p.setLineYAtLocation(4, 3, new Bloc(ListColor.BLUE), 2);
			p.setObjectAtLocation(3, 3, new Bloc(ListColor.BLUE));
			p.setObjectAtLocation(4, 7, new Bloc(ListColor.MAGENTA));
			p.setObjectAtLocation(3, 8, new Bloc(ListColor.MAGENTA));
			p.setObjectAtLocation(0, 6, new Bloc(ListColor.MAGENTA));
			p.setObjectAtLocation(2, 4, new Bloc(ListColor.MAGENTA));
			p.setObjectAtLocation(3, 4, new Bloc(ListColor.RED));
			p.setObjectAtLocation(2, 3, new Bloc(ListColor.MAGENTA));
			p.setLineYAtLocation(0, 0, new Bloc(ListColor.MAGENTA), 2);
			p.setLineXAtLocation(2, 2, new Bloc(ListColor.GREEN), 2);
			p.setObjectAtLocation(0, 2, new Bloc(ListColor.GREEN));
			p.setLineXAtLocation(0, 3, new Bloc(ListColor.RED), 2);
			p.setLineXAtLocation(1, 1, new Bloc(ListColor.RED), 2);
			p.setLineXAtLocation(3, 0, new Bloc(ListColor.YELLOW), 2);
			p.setObjectAtLocation(3, 1, new Bloc(ListColor.YELLOW));
			p.setObjectAtLocation(1, 0, new Bloc(ListColor.RED));
			p.setObjectAtLocation(2, 0, new Poule());
			p.setObjectAtLocation(1, 2, new Poule());
			p.setObjectAtLocation(4, 1, new Poule());
			p.setObjectAtLocation(4, 6, new Poule());
			p.setObjectAtLocation(1, 6, new Bloc(ListColor.RED));
			p.setLineXAtLocation(1, 8, new Bloc(ListColor.RED),2);
			p.setObjectAtLocation(3, 4, new Bloc(ListColor.RED));
			SaveObject.serialisation("niveaux/separation/" + name + ".db");
			return p;
		}
	 
	
		public static void createAllLevel() throws IOException {
			Plateau p1 = createLevel_1("niveau1");
			Plateau p2 = createLevel_2("niveau2");
			Plateau p3 = createLevel_3("niveau3");
			Plateau p4 = createLevel_4("niveau4");
			Plateau p5 = createLevel_5("niveau5");
			Plateau p6 = createLevel_6("niveau6");
			SaveObject.serialisation("niveaux/complet/plateauall.db", p1,p2,p3,p4,p5,p6);
		}
		
}
