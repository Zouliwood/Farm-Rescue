package L2POO.Game.Vue;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

import L2POO.Game.Controleur.ControleurEvent;
import L2POO.Game.Modele.Joueur.Joueur;
import L2POO.Game.Modele.Plateau.Case.Case;

public class VueConsole{

	ControleurEvent event;
	Scanner sc;

	public VueConsole() {
		try {
			event = new ControleurEvent();
		} catch (Exception e) { System.out.println("Probleme de chargement des niveaux"); }
		sc = new Scanner(System.in);
		demanderMenu();
	}
	
	/**
	 * Ferme le scanner.
	 */
	public void close() { sc.close(); }
	
	/**
	 * Permets de gerer le menu d'edition pour permettre a l'utilisateur de creer ses niveaux.
	 */
	public void MenuEdition() { /* TODO Auto-generated method stub */ }
	
	/**
	 * Cree un joueur puis lui donne le choix de creer un niveau ou bien de jouer a niveaux serialises.
	 */
	public void demanderMenu() {
		event.createPlayer(demanderPseudo());
		demanderTypeJeu();
	}

	/**
	 * Demande le pseudo d'un utilisateur.
	 */
	public String demanderPseudo() {
		return demandePhrase("\nQuel est votre pseudo en jeu :");
	}
	
	/**
	 * Permets d'enregistrer le message de l'utilisateur apres avoir ecrit une chaine de caractere.
	 * @param request Question a poser a l'utilisateur
	 * @return Entree de l'utilisateur sous forme de chaine de caractere
	 */
	public String demandePhrase(String ... request) {
		for(String s: request)System.out.println(s);
		return sc.next().replace(" ", "");
	}
	
	/**
	 * Demande un niveau a l'utilisateur.
	 */
	public int demanderNiveau() {
		return demandeNombre("\nChosisiez un niveau :");
	}
	
	/**
	 * Permets  d'enregistrer le message de l'utilisateur apres avoir ecrit un entier.
	 * @param Request, question a poser a l'utilisateur.
	 * @return Entree de l'utilisateur sous forme d'un entier.
	 */
	public int demandeNombre(String ... request) {
		for(String s: request)System.out.println(s);
		return sc.nextInt();
	} 

	/**
	 * Demande a l'utilisateur de jouer un coup dans le plateau.
	 */
	public int[] demanderCoup() {
		String rep = demandePhrase("\nOu voulez vous jouer ? x:y (exemple 1:2 ou 4:6)");
		if(!rep.matches("^[0-9]:[0-9]$")) {
			System.out.println("Mauvaise syntaxe > PositionX:PositionY ");
			return demanderCoup();
		}
		String[] separation = rep.split(":");
		int[] convertToInt = new int[separation.length];
		for(int i = 0; i < separation.length; i++) convertToInt[i] = Integer.valueOf(separation[i]);
		return convertToInt;
	}

	/**
	 * Demande a l'utilisateur s'il souhaite creer un niveau ou bien encore jouer sur un plateau serialise.
	 */
	public void demanderTypeJeu() {
		boolean inputOk = false;
		while (!inputOk) {
			try {
				System.out.println(
						"\nChoissisez un mode de jeu\n"
						+ " (1)Jeu [defaut]\n"
						+ " (2)Mode aleatoire\n"
						+ " (3)Quitter");
				int jouerCreer = sc.nextInt();
				switch (jouerCreer) {
				case 1: MenuLevel(event.getJoueur(), event.getArrayPlateau().size()); break;
				case 2: event.playAleatoire(); partieEnCoursFin(); break;
				case 3: System.out.println("Fin de jeu"); System.exit(0); break;
				default:
					System.out.println("\nCommande non reconnue:\n"
							+ "Lancement du mode par defaut");
					MenuLevel(event.getJoueur(), event.getArrayPlateau().size());  break;
				}
				inputOk = true;
			} catch (Exception e) {
				System.err.println("\nSaisie invalide");
				sc.nextLine();
			}
		}
	}
	
	/**
	 * Permets de lancer une partie et par la suite de nous retourner le menu de selection du mode de jeu.
	 */
	public void partieEnCoursFin() {
		affichage();
		while (event.getPlateau().canplay()==0) menuPartie();
		etatFinPartie();
		if(event.getPlateau().getSetting().getNiveau() == event.getJoueur().getLastWin())event.getJoueur().niveauDebloque();
		if (event.getPlateau().canplay()!=-1) event.getJoueur().retireVie();
		else event.getJoueur().addPoints(event.getPlateau().getSetting().getPoints()); //verifier la serialisation
		afficheProfil();
		demanderTypeJeu();
	}
	
	/**
	 * Liste les differents niveaux serialises.
	 * @param j Demande un joueur en parametre pour lui donner acces aux differentes maps qu'il a debloquees.
	 * @param nombremap Donne le nombre de serialiser disponible.
	 */
	public void MenuLevel(Joueur j, int nombremap) {
		afficheProfil();
		System.out.println("### Menu des niveaux : ###");
		for(int i = 1; i <= nombremap; i++) {
			if(i <= j.getLastWin()) {
				System.out.println(" -> Niveau " + i + " debloque");
			}else {
				System.out.println("-> Niveau " + i +" bloque");
			}	 
		}
		int niveau = demanderNiveau()-1;
		event.playToPlateau(niveau);
		while (!event.selectionNiveauValide(niveau)) {
			niveau = demanderNiveau()-1;
			event.playToPlateau(niveau);
		}
		partieEnCoursFin();
	}
	
	/**
	 * Permets le bon deroulement de la partie en proposant les differents menus lors de la session de jeu.
	 */
	public void menuPartie() {
		System.out.println(
				"Choissisez un mode de jeu\n"
				+ " (1)Jouer [defaut]\n"
				+ " (2)bot\n"
				+ " (3)indice");
		int interaction=0;
		boolean inputOk = false;
		while (!inputOk) {
			try {
				interaction=sc.nextInt();
				inputOk = true;
			} catch (Exception e) {
				System.err.println("\nSaisie invalide");
				System.out.println(
						"Choissisez un mode de jeu\n"
						+ " (1)Jouer [defaut]\n"
						+ " (2)bot\n"
						+ " (3)indice");
				sc.nextLine();
			}
		}
		switch (interaction) {
		case 1: 
			int coup[]=demanderCoup();
			try { 
				event.play(coup[0], coup[1]+event.getPlateau().getSetting().getNotVisibilite());//adapt
			} catch (Exception e) { 
				System.out.println("> Impossible d'enregisterer votre partie");
			}
			affichage();
			break;
		case 2:
			while (event.getPlateau().canplay() == 0) {
				try {
					Point bot = event.getPlateau().solutionBot();
					event.play((int) bot.getX(), (int) bot.getY());
					Thread.sleep(500);
					affichage();
				} catch (Exception e) { 
					e.printStackTrace();
					System.out.println("Mr.LeRobot est malade"); }
			}
			break;
		case 3: 
			try {
				Point bot = event.getPlateau().solutionBot();
				System.out.println("\n>Vous Pouvez jouer en : x="+(int) bot.getX()+", y="+((int) bot.getY() - event.getPlateau().getSetting().getNotVisibilite()));
				affichage();
			} catch (Exception e) { System.out.println("Mr.LeRobot est malade"); }
			break;
		default:
			System.out.println("\n>Commande non reconnu:\n"
					+ "Lancement du mode par defaut"); 
			int coupd[]=demanderCoup();
			try { 
				event.play(coupd[0], coupd[1]+event.getPlateau().getSetting().getNotVisibilite());//adapt
			} catch (Exception e) { 
				System.out.println(">Impossible d'enregisterer votre partie");
			}
			affichage();
			break;
		}
	}
	
    public void affichage() {
        int position = 0; // +getSetting().getNotVisibilite()
        System.out.println("\nPlateau [Deplacement :" + event.getPlateau().getSetting().getDeplacement() +", Poule :" + event.getPlateau().getSetting().getSauve() +", Points :" + event.getPlateau().getSetting().getPoints() + "]" );
        for(int y = event.getPlateau().getSetting().getNotVisibilite(); y < event.getPlateau().getSetting().getLigne(); y++){
            System.out.print(" ");
            System.out.print("    "+position + " :");
            position++;

            for(ArrayList<Case> a : event.getPlateau().getTableau()) {
                System.out.print((a.get(y)).afficheConsole());
                System.out.print("\u001b[0m");
            }
            System.out.println("");            
        }
        System.out.print("      ");
        for(int y = 0; y < event.getPlateau().getSetting().getColonne(); y++)System.out.print("  "+y+ " ");
        System.out.println();
    }
    
	/**
	 * Affiche l'etat de la partie.
	 */
	public void etatFinPartie() {
		switch (event.getPlateau().canplay()) {
		case -1: System.out.println("Victoire"); try{event.victoire();}catch(Exception e){} break;
		case -2: System.out.println("Plus de deplacement disponible"); break;
		default: System.out.println("Defaite"); break;
		}
	
	}
	
	/*
	 * Permet de creer un niveau 
	 */
	
	public void makerNiveau() {
		boolean inputOk = false;
		while (!inputOk) {
			try {
				System.out.println(
						"\nNom du niveau\n"
						+ " (1)Jeu [defaut]\n"
						+ " (2)Mode aleatoire\n"
						+ " (3)Edition\n"
						+ " (4)Quitter");
				int jouerCreer = sc.nextInt();
				switch (jouerCreer) {
				case 1: MenuLevel(event.getJoueur(), event.getArrayPlateau().size()); break;
				case 2: event.playAleatoire(); partieEnCoursFin(); break;
				case 3: MenuEdition(); break;
				case 4: System.out.println("Fin de jeu"); System.exit(0); break;
				default:
					System.out.println("\nCommande non reconnue:\n"
							+ "Lancement du mode par defaut");
					MenuLevel(event.getJoueur(), event.getArrayPlateau().size());  break;
				}
				inputOk = true;
			} catch (Exception e) {
				System.err.println("\nSaisie invalide");
				sc.nextLine();
			}
		}
	}
	
	public String demanderNomMap() {
		return demandePhrase("\nQuel est le nom de votre map:");
	}
	
	
	/**
	 * Donne le nombre de points de vie et le score du joueur.
	 */
	public void afficheProfil() {
		System.out.println();
		System.out.println("#### Profil ####");
		System.out.println("> Nom: " + event.getJoueur().getPseudo());
		System.out.println("> Point: " + event.getJoueur().getPoints());
		System.out.println("> Vie: " + event.getJoueur().getVie());
		System.out.println("> Niveau maximal joue: " + event.getJoueur().getLastWin());
		System.out.println("#### Profil ####");
		System.out.println();


	}
	
}
