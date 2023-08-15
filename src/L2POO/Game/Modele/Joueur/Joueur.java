package L2POO.Game.Modele.Joueur;

import java.io.IOException;
import java.io.Serializable;

import L2POO.Game.Modele.SaveObject;

public class Joueur implements Serializable{
	
	/*
	 Class Joueur avec des attributs pour 
	 le joueur
	*/ 
	private String pseudo;
	private int nbrPoints, vie; 
	private int nivGagne = 1;

	public Joueur(String pseudo){
		this.pseudo = pseudo;
		this.nbrPoints = 0;
		this.vie = 5;
	} 

	public void setPseudo(String pseudo) { this.pseudo = pseudo; }

	public String getPseudo() { return pseudo; }
	
	public void addPoints(int points) { this.nbrPoints += points; }

	public void removePoints(int points) { this.nbrPoints -= points; }

	public int getPoints() { return nbrPoints; }

	public void niveauDebloque() { ++nivGagne; }

	public void retireVie() { this.vie--; }

	public void ajoutVie() { this.vie++; }

	public int getLastWin() { return nivGagne; }

	public void miseajour() throws IOException { SaveObject.serialisation("joueurs/" + pseudo +".db", this); }

	public int getVie() { return this.vie; }
	
}
