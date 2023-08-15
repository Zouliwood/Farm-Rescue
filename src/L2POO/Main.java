package L2POO;

import java.awt.EventQueue;
import java.util.Scanner;

import L2POO.Game.Modele.Plateau.CreateLevel;
import L2POO.Game.Vue.VueConsole;
import L2POO.Game.Vue.VueFenetre;

public class Main {

	/**
     * Permets de lancer le jeu et proposent deux types de vues.
     * @param args L'utilisateur peut lancer le jeu en ayant prealablement selectionne le type de vue.
     * @throws Exception 
     */
    public static void main(String[] args){
        if(args.length > 0) {
            try {
                CreateLevel.createAllLevel();
                switch (args[0].toLowerCase()) {
                case "vueconsole": new VueConsole(); break;
                case "vuefenetre":
        			EventQueue.invokeLater(new Runnable() {
        		        public void run() {
        					try {
        						new VueFenetre();
        					} catch (Exception e) {
        						e.printStackTrace();
        					}

        		        }
        			});
        			break;
                default:
                    System.out.println("Commande non reconnu:\n"
                            + "Lancement du mode par defaut");
           			EventQueue.invokeLater(new Runnable() {
        		        public void run() {
        					try {
        						new VueFenetre();
        					} catch (Exception e) {
        						e.printStackTrace();
        					}

        		        }
        			});
        			break;
                }
            } catch (Exception e) { System.out.println("Erreur lors du lancement du jeu"); }
        }else {
            try {
                CreateLevel.createAllLevel();
                Scanner interaction = new Scanner(System.in);  
                System.out.println(
                        "Choissisez un moyen d'interraction:\n"
                        + " (1)Console\n"
                        + " (2)Graphique [defaut]\n"
                        + " (3)Quitter");

                switch (interaction.nextLine()) {
                case "1": new VueConsole(); break;
                
                case "2":
        			EventQueue.invokeLater(new Runnable() {
        		        public void run() {
        					try {
        						new VueFenetre();
        					} catch (Exception e) {
        						e.printStackTrace();
        					}

        		        }
        			});
        			break;
                case "3": System.out.println("Fin de jeu"); System.exit(0); break;
                default:
                    System.out.println("Commande non reconnu:\n"
                            + "Lancement du mode par defaut");
           			EventQueue.invokeLater(new Runnable() {
        		        public void run() {
        					try {
        						new VueFenetre();
        					} catch (Exception e) {
        						e.printStackTrace();
        					}

        		        }
        			});
        			break;
                }
                interaction.close();
            } catch (Exception e) { 
                e.printStackTrace();
                System.out.println("Erreur lors du lancement du jeu"); 
            } 
        }
    }
  
}
  