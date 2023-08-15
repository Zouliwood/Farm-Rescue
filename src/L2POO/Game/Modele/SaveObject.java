package L2POO.Game.Modele;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveObject {

	
	/**
	 * Cette fonction renvoie une list avec les objects deserialises.
	 * @param chemin Deserialisation du fichier.
	 * @return Retourne un objet deserialise.
	 * @throws Exception Souleve une exception en cas de probleme lors de la deserialisation du fichier.
	 */
	public static ArrayList<Object> deserialisation(String chemin)throws Exception {
		FileInputStream fis = new FileInputStream(chemin);
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<Object> objetSer = new ArrayList<Object>();
		while(fis.available() > 0) objetSer.add(ois.readObject());
		ois.close(); 
		return objetSer;
	}
	
	/**
	 * Cette fonction serialiser un object en parametre dans un fichier
	 * @param chemin Serialisation du fichier.
	 * @param b Enregistre les informations entrees en parametre.
	 * @throws IOException Souleve une exception s'il est impossible d'enregistrer les informations entrees en parametre.
	 */
	public static void serialisation(String chemin, Object ... b) throws IOException{
		FileOutputStream fos = new FileOutputStream(chemin);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		for(Object object :b) 
			oos.writeObject(object); 
		oos.close();
	}
	
	/**
	 * Permets de sauvegarder la progression du joueur.
	 * @param pseudo Enregistre le pseudo du joueur.
	 * @param j Enregistre les informations du joueur.
	 * @throws IOException Souleve une exception s'il est impossible d'enregistrer l'utilisateur.
	 */
	public static void saveJoueur(String pseudo, Object j) throws IOException{
		FileOutputStream fos = new FileOutputStream("joueurs/" + pseudo + ".db");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(j);
		oos.close();
	}


	
}
