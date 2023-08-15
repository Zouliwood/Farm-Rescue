package L2POO.Game.Controleur;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.undo.UndoManager;

import L2POO.Game.Modele.SaveObject;
import L2POO.Game.Modele.Joueur.Joueur;
import L2POO.Game.Modele.Plateau.Plateau;
import L2POO.Game.Modele.Plateau.Settings;

public class ControleurEvent {
		
		private ArrayList<Object> arrayPlateau;
		private UndoManager undoManager = new UndoManager();
		private Joueur joueur;
		private Plateau plateau;	
		
		public ControleurEvent() throws Exception{
			arrayPlateau = SaveObject.deserialisation("niveaux/complet/plateauall.db");
		}
		
		public Plateau getPlateau(){ return plateau; }

		public Joueur getJoueur() { return joueur; }

		public ArrayList<Object> getArrayPlateau() { return arrayPlateau; }

		/**
		 * Permets de configurer le joueur en selectionnant un utilisateur deja creer ou d'en creer un nouveau.
		 * @param pseudo Rentre le pseudo choisi par l'utilisateur.
		 */
		public void createPlayer(String pseudo) {
			Joueur j = new Joueur(pseudo);
			File file = new File("joueurs/" + pseudo + ".db");
			if(file.exists()) {
				try { 
					j = (Joueur) SaveObject.deserialisation(file.getPath()).get(0);
				} catch (Exception e) { e.printStackTrace(); }
			}else {
				try {
					file.createNewFile();
					j = new Joueur(pseudo);
					SaveObject.serialisation(file.getPath(), j);
				} catch (IOException e) { e.printStackTrace(); }
			}
			this.joueur = j;
		}
		
		/**
		 * Charge le niveau entre en parametre par l'utilisateur.
		 * @param niveau Permets a l'utilisateur de selection le niveau qu'il souhaite lancer.
		 */
		public void playToPlateau(int niveau) {
			if(niveau > getArrayPlateau().size())return;
			this.plateau = ((Plateau) getArrayPlateau().get(niveau)).clone();
			plateau.setClone(plateau.clone());
		}
		/**
		 * Creer un plateau et l'initialise avec des blocs aleatoires avec une methode comprise
		 * dans plateau
		 */
		public void playAleatoire() {
			this.plateau = new Plateau(new Settings(15, 5, 30, 8, -1));
			plateau.initialisationRandom();
			plateau.toFall();
			undoManager.addEdit(plateau);
		}
		
		/**
		 * Permets a l'utilisateur de jouer sur un plateau en selectionnant un bloc et le notifie en cas de victoire ou de defaite.
		 * @param x Point de coordonnee X d'un bloc.
		 * @param y Point de coordonnee Y d'un bloc.
		 * @return Retourne un entier indiquant si la partie est terminee ou non mais egalement gagne ou perdu.
		 * @throws Exception Souleve une exception s'il est impossible de mettre a jour les informations du joueur.
		 */
		public int play(int x, int y) throws Exception {
			plateau.setClone(plateau.clone());
			if(plateau.canplay() == 0) {
				plateau.selection(x, y);
				plateau.toFall();
				plateau.obstacleCheck();
				plateau.checkSave();
				plateau.switchVtoH();
				plateau.toFall();
				return 0;
			}else if(plateau.canplay() == -1) {
				System.out.println("Vous avez gagne la partie ! +" + plateau.getSetting().getPoints());
				victoire();
				return -1;
			}else{
				if(joueur.getVie() >= 1) joueur.retireVie();
				return -2;
			} 
		}
		
		/*
		 * Verification de la victoire 
		 * 
		 */

		public void victoire() throws Exception {
			joueur.addPoints(plateau.getSetting().getPoints());
			if(plateau.getSetting().getNiveau() == joueur.getLastWin()) joueur.niveauDebloque();
			joueur.miseajour();
		}
		
		/*Tranforme le plateau present en plateau de retour*/
		
		public void retourCoup() {
			if(peutRetour()) {
				this.plateau = plateau.getClone();
			}
		}
		
		/*Verifie si le nombre de deplacement est different car s'il y'a une modification les deplacements seront differents*/
		public boolean peutRetour() {
			return plateau.getSetting().getDeplacement() != plateau.getClone().getSetting().getDeplacement();
		}
		
		
		/**
		 * Permets de verifier si le niveau selectionne par l'utilisateur est valide et debloque.
		 * @param selection Teste la validite du niveau selectionnee par l'utilisateur.
		 * @return Retourne un boolean indiquant si le joueur peut acceder au niveau selectionne.
		 */
		public boolean selectionNiveauValide(int selection){
			if (!(selection>=0 && selection<joueur.getLastWin())) {
				System.out.println("Impossible de selectionner ce niveau, dernier niveau debloque :"+joueur.getLastWin());
			}
			return (selection>=0 && selection<joueur.getLastWin());
		}
		
		/**
		 * Permets derevenir sur un plateau precedent
		 */
		public void undo() {
			if(undoManager.canUndo()) {
				undoManager.undo();
				this.plateau.undo();
			}
		}
		
		/*
		 * Permet de deserialiser une map de l'integrer directement dans une plateau
		 * 
		 */
		public void plateauDeserialise(String name) {
			try {
				this.plateau = (Plateau) SaveObject.deserialisation("niveaux/separation/"+name + ".db").get(0);
			} catch (Exception e) {
				System.out.println("Erreur la map n'a pas chargee");
			}
		}
		/*
		 * Permet de serialiser un plateau et d'y ajouter un nom;
		 * 
		 */
		public void plateauSerialise(String name, Plateau p) {
			try {
				SaveObject.serialisation("niveaux/separation/"+name + ".db", p);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Erreur la map n'a pas ete enrengistre");
			}
		}
		
		/*
		 * Permet de creer un plateau vide
		 * 
		 */
		public void createEmptyPlateau() {
			this.plateau = new Plateau(new Settings(5,5,30,-1));
			this.plateau.initialisation();
		}
}
