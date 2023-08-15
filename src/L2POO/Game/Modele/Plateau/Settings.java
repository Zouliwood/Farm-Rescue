package L2POO.Game.Modele.Plateau;

import java.io.Serializable;

public class Settings implements Serializable, Cloneable{

	/*
	 * Class comprenant les informations du plateau
	 * pour limiter le nombre de parametres dans un plateau
	 * 
	 * nbSauve : Les poules a sauver
	 * notvisibilite: Partie non visible du tableau
	 * 
	 * deplacement: Le nombre de deplacement par plateau
	 * colonne, ligne: Les colonnes et lignes respectives du plateau
	 * niveau : niveau du plateau
	 */
	private int nbSauve, deplacement, points;
	private final int ligne, colonne, notvisibilite, niveau;
	
	public Settings(int ligne, int colonne, int deplacement, int niveau) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.notvisibilite = 0; // visibilite de map entiere
		this.deplacement = deplacement;
		this.nbSauve = 0;
		this.points = 0;
		this.niveau = niveau;
	}
	
	public Settings(int ligne, int colonne, int deplacement, int notvisibilite, int niveau) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.notvisibilite = notvisibilite;
		this.deplacement = deplacement;
		this.nbSauve = 0;
		this.points = 0;
		this.niveau = niveau;
	}
	
	public int getColonne() {return colonne;}
	public int getLigne() {return ligne;}
	public int getSauve() {return nbSauve;}
	public int getDeplacement() {return deplacement;}
	public void grandirSauve() { nbSauve++;}
	public boolean notAllSauve() {return nbSauve > 0;}
	public void petsSauve() {nbSauve--;}
	public void deplacementDiminue() {this.deplacement--;}
	public boolean peuxBouger() {return deplacement >= 0;}
	public void addPoints(int points) {this.points += points;}
	public void removePoints(int points) {this.points -= points;}
	public int getPoints() {return points;}
	@Override
	public Settings clone() {Settings s = new Settings(ligne, colonne, deplacement, notvisibilite, niveau);s.addPoints(points);return s;}
	public int getNiveau() {return this.niveau;}
	public int getNotVisibilite() {return this.notvisibilite;}

}
