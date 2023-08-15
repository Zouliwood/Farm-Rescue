package L2POO.Game.Modele.Plateau;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.undo.AbstractUndoableEdit;

import L2POO.Game.Modele.Enumeration.ListBloc;
import L2POO.Game.Modele.Enumeration.ListBoost;
import L2POO.Game.Modele.Enumeration.ListColor;
import L2POO.Game.Modele.Plateau.Case.Bloc;
import L2POO.Game.Modele.Plateau.Case.Boost;
import L2POO.Game.Modele.Plateau.Case.Case;
import L2POO.Game.Modele.Plateau.Case.Poule;
import L2POO.Game.Modele.Plateau.Case.Vide;
import L2POO.Game.Modele.Plateau.Case.BoostSpecial.Ballon;
import L2POO.Game.Modele.Plateau.Case.BoostSpecial.Bombe;
import L2POO.Game.Modele.Plateau.Case.BoostSpecial.Rocket;


public class Plateau extends AbstractUndoableEdit implements Serializable, Cloneable{

	private ArrayList<ArrayList<Case>> plateau;
	private Settings settings;
	private Plateau memoire;

	public Plateau(Settings settings) {
		this.settings = settings;
		plateau = new ArrayList<ArrayList<Case>>();
		initialisation();
		this.memoire = this;
	}

	public Settings getSetting() { return this.settings; }
	public ArrayList<ArrayList<Case>> getTableau(){ return this.plateau; }
	public void undo() {super.undo();}
	public void setClone(Plateau p) {this.memoire = p;};
	public Plateau getClone() {return memoire; };

	/**
	 *Initialisation du plateau avec des blocs vides.
	 */
	public void initialisation() {
		for(int x = 0; x < settings.getColonne(); x++) {
			ArrayList<Case> tab = new ArrayList<Case>();
			for(int y = 0; y < settings.getLigne(); y++) {
				tab.add(new Vide());
			}
			plateau.add(tab);
		}	
	}
	
	/**
	 *Initialisation du plateau avec des blocs 
	 *generer aleatoirement avec une logique 
	 *de jeu.
	 */
	public void initialisationRandom() {
		Random rand = new Random();
		int blocrandom = 0; // 70bloc - 20boost 10 poule
		int typeblocrandom = 0; // 25*4
		int typeboostrandom = 0; // 10 10 10
		for(int x = 0; x < settings.getColonne(); x++) {
			for(int y = 0; y < settings.getLigne(); y++) {
				if(plateau.get(x).get(y) instanceof Vide) {
				  blocrandom =rand.nextInt(100);				
					if(blocrandom > 30) {
						Bloc bloc = new Bloc(ListColor.getRandomColor());
						typeblocrandom = rand.nextInt(100);
						if(typeblocrandom > 0 && typeblocrandom < 25 ) {
							setSquareObjectAtLocation(x,y, bloc);
						}else if(typeblocrandom > 25 &&  typeblocrandom < 50){
							setLineYAtLocation(x,y, bloc, 3);
						}else if(typeblocrandom > 50 && typeblocrandom < 75) {
							setLineXAtLocation(x,y, bloc, 3);
						}else {
							setObjectAtLocation(x,y, new Bloc(ListColor.getRandomColor()));
						}
					}else if(blocrandom > 0 && blocrandom < 15) {
						typeboostrandom =rand.nextInt(30);
						//boost
						if(typeboostrandom > 0 && typeboostrandom < 10) {
							setObjectAtLocation(x,y, new Bombe());
						}else if (typeboostrandom > 10 && typeboostrandom < 20) {
							setObjectAtLocation(x,y, new Rocket());
						}else {
							setObjectAtLocation(x,y, new Ballon(ListColor.getRandomColor()));
						}
					}else {
						//poule
						if(y < 3) { 
							setObjectAtLocation(x,y, new Poule());
						}else {
							setObjectAtLocation(x,y, new Bloc(ListColor.getRandomColor()));
						}
					}
				}
			}
		}
		//Minimum
		setObjectAtLocation(0,0, new Poule());
	}
	
	
	/**
	 * Verifie la validite d'un point dans le tableau.
	 * @param point Point en parametre a verifier.
	 * @return Retourne un boolean indiquant si le point existe.
	 */
	public boolean estValide(Point point){
		return point.getX() < settings.getColonne() && point.getY() < settings.getLigne() && point.getX() >= 0 && point.getY() >= 0;
	}
	
	
	/**
	 * Cette fonction tranforme un bloc color en bloc vide
	 * et donne un nombre de points au plateau.
	 * @param int x Coordonnee X du point a modifier.
	 * @param int y Coordonnee Y du point a modifier.
	 */
	public void supprimer(int x, int y) {
		if((plateau.get(x).get(y)) != null) {
			settings.addPoints(plateau.get(x).get(y).points());
			plateau.get(x).set(y, new Vide());
		}	
	}
	
	/**
	 * Cette fonction verifie les voisins en position impaire (haut, bas, gauche, droite) d'un bloc et y fait appel puis le supprime.
	 * @param point_x Coordonnee X du point a supprimer.
	 * @param point_y Coordonnee Y du point a supprimer.
	 * @param color Couleur entree en parametre.
	 * @param voisinSup Indique si des blocs ont ete supprimer au prealable avant la suppression du bloc appele.
	 */
	public void clique(int point_x, int point_y, ListColor color, boolean voisinSup) {
        if(estValide(new Point(point_x, point_y)) && voisinSup) supprimer(point_x, point_y);
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if ((1==Math.abs(x) && y==0) || (x==0 && 1==Math.abs(y))) {
                    if(estValide(new Point(point_x+x, point_y+y))) {
                        if((plateau.get(point_x+x).get(point_y+y).getType() == ListBloc.BLOC)){
                            if(((Bloc) plateau.get(point_x+x).get(point_y+y)).isStatusColor(color)){
                            	clique(point_x+x, point_y+y, color, true);
                            }
                        }
                    }
                }
            }
        }
    }
	
	
	/**
	 * Verifie le bloc selectionne et realise une action en fonction de celui-ci.
	 * @param point_x Coordonnee X du point avec lequel on va interagir.
	 * @param point_y Coordonnee Y du point avec lequel on va interagir.
	 */
	public void selection(int point_x, int point_y) {
		Case select = this.plateau.get(point_x).get(point_y);
			if(select.isStatusBloc(ListBloc.BLOC)) {
				if(asNeighbour(point_x, point_y, ((Bloc) select).getColor()) != 0)settings.deplacementDiminue();
				clique(point_x, point_y,((Bloc) getTableau().get(point_x).get(point_y)).getColor(), false);
			}else if(select.isStatusBloc(ListBloc.BOOST)) {
				 System.out.println("Bonus active");
				if(((Boost) select).getBoost() == ListBoost.ROCKET) {
					 	rocket(point_x, point_y);
				}else if(((Boost) select).getBoost() == ListBoost.BALLON) {
					 	ballon( (((Ballon) select)).getColor());
				}else if(((Boost) select).getBoost() == ListBoost.BOMBE) {
						bombe(point_x, point_y);
				}else {
					System.out.println("> Cet outil n'existe pas");	//cas impossible mais pour la securite
				}
				 System.out.println("> Vous avez utilise un boost ! ");
				 supprimer(point_x, point_y);
				 settings.deplacementDiminue();
			}else {
				System.out.println("> Cette action n'est pas possible !");
			}
			 
		}
	
	
	/**
	 * Renvoie le nombre de voisin aux positions impair (gauche, droit, haut bas).
	 * @param point_x Coordonnee X du point avec lequel on va regarder son nombre de voisins.
	 * @param point_y Coordonnee Y du point avec lequel on va regarder son nombre de voisins.
	 * @param color Couleur du point avec lequel on va regarder son nombre de voisins.
	 * @return int Retourne le nombre de voisin de même couleur.
	 */
	 public int asNeighbour(int point_x, int point_y, ListColor color) {    
	        int cmp = 0; 
	        for (int x = -1; x <= 1; x++) {
	            for (int y = -1; y <= 1; y++) {
	                if ((1==Math.abs(x) && y==0) || (x==0 && 1==Math.abs(y))) {
	                    if(estValide(new Point(point_x+x, point_y+y))) {
	                        if((plateau.get(point_x+x).get(point_y+y).getType() == ListBloc.BLOC)){
	                            if(((Bloc) plateau.get(point_x+x).get(point_y+y)).isStatusColor(color)){
	                                cmp++;
	                            }
	                        }
	                    }    
	                }
	            }
	        }
	        return cmp;
	    }
	 
	/**
	 * Verifie s'il est encore possible de jouer.
	 * @return Retourne un entier indiquant le statut de la partie.
	 */
	public int canplay() {
		if(settings.peuxBouger()) {
			if(settings.notAllSauve()) {
				for(int x = 0; x < this.plateau.size(); x++) {
					for(int y = 0+getSetting().getNotVisibilite(); y < this.plateau.get(x).size(); y++) {
							if(check_game(new Point(x,y)) || this.plateau.get(x).get(y).isStatusBloc(ListBloc.BOOST)) {
								return 0; //encore jouable
						}
					}
				}
			}else return -1; //Victoire
		}else return -2; //manque de deplacement
		return -3;  //fin de partie	
	}
		
		/**
		 * Cette fonction regarde un point et ses coups jouable aux alentours.
		 * @param p Regarde un point et nous dit s'il est jouable.
		 * @return boolean Retourne true s'il est toujours possible de jouer.
		 */
		public boolean check_game(Point p) {
			int pointligne  = (int) p.getX();
			int pointcolonne  = (int) p.getY();
			Case b = plateau.get(pointligne).get(pointcolonne);

			if(b.isStatusBloc(ListBloc.BLOC)){
				if(asNeighbour(pointligne, pointcolonne,((Bloc)b).getColor()) != 0)return true;
			}
			return false;
		}
		
			
	/*Boost */
	/**
	 * Methode pour supprimer une rocket et les elements aux alentours.
	 * @param posx Coordonnee X de la rocket.
	 * @param posy Coordonnee Y de la rocket.
	 */
	public void rocket(int posx, int posy) {
		for(int y = 0; y <= posy; y++ ) {
			if(plateau.get(posx).get(y) instanceof Bloc) {
					supprimer(posx,y);
				}
		}
	}
	
	/**
	 * Methode pour supprimer un ballon et les elements aux alentours.
	 * @param color Couleur du ballon.
	 */
	public void ballon(ListColor color) {
		for(int x = 0; x < this.plateau.size(); x++) {
			for(int y = 0+getSetting().getNotVisibilite(); y < this.plateau.get(x).size(); y++) {
				if(plateau.get(x).get(y) instanceof Bloc) if(((Bloc) plateau.get(x).get(y)).getColor() == color) supprimer(x,y);		
				if(plateau.get(x).get(y) instanceof Ballon && (((Ballon) plateau.get(x).get(y)).getColor() == color)) supprimer(x,y);
			}
		}
	}
	
		
	/**
	 * Methode pour supprimer une bombe et les elements aux alentours.
	 * @param posx Coordonnee X de la bombe.
	 * @param posy Coordonnee Y de la bombe.
	 */
	public void bombe(int posx, int posy) {
		for(int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if(estValide(new Point(posx+x, posy+y))) {
					if(plateau.get(posx+x).get(posy+y) instanceof Bloc) {
						supprimer(posx+x,posy+y);	
					}
				}
			}
		}	
	}
	
	/*Boost */


	/**
	 * Verifie s'il existe des poules dans la ligne du bas et les sauve.
	 */
	public void checkSave() {
		for(int x = 0; x < this.plateau.size(); x++) {
			if(this.plateau.get(x).get(this.plateau.get(x).size()-1).isStatusBloc(ListBloc.POULE)) {
				settings.petsSauve();
				this.plateau.get(x).set(this.plateau.get(x).size()-1, new Vide());
			}
		}
	}
	
    /**
     * Propose une action a realiser au joueur s'il est encore possible de jouer.
     * @return Retourne un point jouable s'il en existe un.
     */
    public Point solutionBot() {
        Point p=null;
    	int max = 0;
        for (int x = settings.getColonne()-1; x >= 0; x--) {
            for (int y = settings.getLigne()-1; y >=0+getSetting().getNotVisibilite() ; y--) {
            	if (plateau.get(x).get(y) instanceof Boost) return new Point(x, y);
	                if (plateau.get(x).get(y) instanceof Bloc) {
	                    if (asNeighbour(x, y, ((Bloc)plateau.get(x).get(y)).getColor()) > max) {
	                    	max = asNeighbour(x, y, ((Bloc)plateau.get(x).get(y)).getColor());
	                    	p = new Point(x,y);
	                    }
	                }
	           }
        }
        return p;
    }
	
	/**
	 * Fait intervenir la gravite dans le jeu lors de la rencontre avec un obstacle.
	 */
	public void toFall() {     
		for(int x = 0; x < settings.getColonne(); x++) toFallColonne(x);
		for(int x = 0; x < settings.getColonne(); x++) {
			for(int y = 0; y < settings.getColonne(); y++) {
				if(y+1 < settings.getColonne()) {
					if(plateau.get(x).get(y).isStatusBloc(ListBloc.OBSTACLE)) {
		    			  complete(x, y);
					}
				}
			}
		}
    }

	/**
	 * Appel les colonnes du plateau pour faire remonter les blocs vides.
	 * @param x Numero de la colonne a trier.
	 */
	public void toFallColonne(int x) { compare(plateau.get(x)); }
	
	/**
	 * Echange la position de deux blocs lors de la rencontre avec un obstacle.
	 * @param x Coordonne X des points a echange.
	 * @param y Coordonne Y des points a echange.
	 */
	public void complete(int x, int y) {
	   if(plateau.get(x+1) != null) {
		   echangeLOrR(x+1, x, y);
		   toFallColonne(x+1);
	   }else {
		   echangeLOrR(x-1, x, y);
		   toFallColonne(x-1);
	   }
	   toFallColonne(x);
   }
	
	/**
	 * Si le bloc courant est vide on l'echange avec celui d'au-dessus 
	 * puis on repete l'operation jusqu'a ce que le bloc vide rencontre un obstacle 
	 * ou la fin du plateau.
	 * @param list Prends en parametre une colonne.
	 * @return Retourne une colonne triee.
	 */
	public ArrayList<Case> compare(ArrayList<Case> list) {
		int taille = list.size();
		while(taille-- > 0) {
	        for(int i = list.size()-1; i >= 0 ; i--) { 	
	            if(list.get(i).isStatusBloc(ListBloc.VIDE)) {
	            	if(!(i> 0 && list.get(i-1).isStatusBloc(ListBloc.OBSTACLE))) {
	            		echangeSup(list, i); 
	            	}
	            }
	        }
		}
		return list;
    }
	
	/**
	 * Prends en parametre une colonne et la position d'un element 
	 * et l'echange avec celui du dessus.
	 * @param list Prends en parametre une colonne.
	 * @param i Prends en parametre la position d'un element dans la liste.
	 */
	public void echangeSup(ArrayList<Case> list, int i) {
		if((i-1) >= 0 && !(list.get(i-1).isStatusBloc(ListBloc.VIDE))) {
			Case tmp = list.get(i);
	    	list.set(i, list.get(i-1));
	    	list.set(i-1, tmp);
		}
	}

	/**
	 * Echange deux points dans la même lige mais avec une colonne differente.
	 * @param xa Coordonnee du point a dans la ligne.
	 * @param xb Coordonnee du point b dans la ligne.
	 * @param y Coordonnee du point a et b dans la colonne.
	 */
	public void echangeLOrR(int xa, int xb, int y) {
		Case tmp = plateau.get(xa).get(y);
        plateau.get(xa).set(xb, plateau.get(xb).get(y));
        plateau.get(xb).set(xb, tmp);
	}
	
	/**
	 *	Fait une copie du plateau courant pour permettre a l'utilisateur 
	 *  de jouer sans modifier le plateau deserialise.
	 */
	@Override
	public Plateau clone() {
		Plateau clone = new Plateau(settings.clone());
		for(int x = 0; x < settings.getColonne(); x++) {
			for(int y = 0; y < settings.getLigne(); y++) {
				clone.setObjectAtLocation(x,y, plateau.get(x).get(y));
			}
		}
		return clone;
	}

	
	/**
	 * Verifie qu'un obstacle present dans le plateau 
	 * possede bien un bloc jouable au-dessus de lui pour faire tomber les blocs si necessaire.
	 */
	public void obstacleCheck() {
	    for (int i = 0; i < plateau.size()*plateau.get(0).size(); i++) {
            for (int y = plateau.get(0).size()-1; y >0; y--) {
            	for (int x = plateau.size()-1; x >=0; x--) {
            		if (plateau.get(x).get(y).isStatusBloc(ListBloc.OBSTACLE) &&
            			!plateau.get(x).get(y-1).isStatusBloc(ListBloc.OBSTACLE)) {
            			obstacleFall(new Point(x, y-1));
            		}
            	}
            }
	    }
	}
	
	/**
	 * Prends en parametre un obstacle pour faire tomber les blocs au-dessus de lui.
	 * @param p Prends en parametre un obstacle pour faire tomber a sa gauche ou sa droite les blocs au-dessus de lui.
	 */
	public void obstacleFall(Point p) {
		toFall();
	   	checkSave();
	    int left=0, right=0;
	    
	    for (int i = (int) p.getX()+1; i < settings.getColonne(); i++) {
	        if (plateau.get(i).get((int) (p.getY()+1)).isStatusBloc(ListBloc.OBSTACLE)) right++;
	        else{
	        	if(plateau.get(i).get((int) (p.getY()+1)).isStatusBloc(ListBloc.VIDE)) right++;
	        	else right = 0;
	        	break;
	        }
	        if (i==settings.getColonne()-1 && plateau.get(i).get((int) (p.getY()+1)).isStatusBloc(ListBloc.OBSTACLE)) {
				right=0;
			}
	    }
	    
	    for (int i = (int) p.getX()-1; i >= 0; i--) {
	        if (plateau.get(i).get((int) (p.getY()+1)).isStatusBloc(ListBloc.OBSTACLE)) left++;
	        else{
	        	if(plateau.get(i).get((int) (p.getY()+1)).isStatusBloc(ListBloc.VIDE)) left++;
	        	else left = 0;
	        	break;
	        }
	        if (i==0 && plateau.get(i).get((int) (p.getY()+1)).isStatusBloc(ListBloc.OBSTACLE)) {
				left=0;
			}
	    }
	    
	    /*ne switch pas si la colonne a cote est non vide*/
	    for (int i = (int) p.getX()-1; i > p.getX()-left; i--) {
			if (!plateau.get(i).get((int) p.getY()).isStatusBloc(ListBloc.VIDE)) {
				left=0;
			}
		}
	    
	    for (int i = (int) p.getX()+1; i < p.getX()+right; i++) {
			if (!plateau.get(i).get((int) p.getY()).isStatusBloc(ListBloc.VIDE)) {
				right=0;
			}
		}
	    
	    int droitOuGauche = 0;
	    if (left+right == 0) return;
	    else if (left!=0 && (left<=right || right==0)) droitOuGauche = -left;
	    else if (right!=0 && (right<=left || left==0)) droitOuGauche = right;
    	plateau.get((int) (p.getX()+droitOuGauche)).set((int) (p.getY()+1), plateau.get((int) p.getX()).get((int) p.getY()));
    	plateau.get((int) p.getX()).set((int) p.getY(), new Vide());
   	}
	
	/**
     * Compte le nombre de case dans une colonne avant de rencontrer un obstacle ou bien la fin du tableau.
     * @param x Prends en parametre la colonne que l'on souhaite analyser.
     * @return Retourne le nombre d'elements que l'on peut echanger au plus avant de rencontrer un conflit.
     */
    public int countSwitchVtoH(int x) {
    	int nObstacle=0;
    	for (int i = settings.getLigne()-1; i >= 0; i--) {
            if (!plateau.get(x).get(i).isStatusBloc(ListBloc.OBSTACLE)) nObstacle++;
            else break;
        }
    	return nObstacle;
	}
  
	
    /**
     * Decale une colonne contenant des blocs vers la gauche si celle-ci est vide (echange de place une colonne vide avec une colonne contenant des elements).
     * @return Appel recursif de la fonction puis retourne false des qu'il n'y a plus de changement a faire entre les colonnes.
     */
    public boolean switchVtoH() {
        toFall();
        boolean flag=false;
        for (int x = 0; x < settings.getColonne()-1; x++) {
            if (plateau.get(x).get(settings.getLigne()-1).isStatusBloc(ListBloc.VIDE) && 
                    (plateau.get(x+1).get(settings.getLigne()-1).isStatusBloc(ListBloc.BLOC)||plateau.get(x+1).get(settings.getLigne()-1).isStatusBloc(ListBloc.BOOST))) {
                for (int i = settings.getLigne()-1; i >= settings.getLigne()-Math.min(countSwitchVtoH(x), countSwitchVtoH(x+1)); i--) {
                    plateau.get(x).set(i, plateau.get(x+1).get(i));
                    plateau.get(x+1).set(i, new Vide());
                }
                flag=true;
            }
        }
        return flag ? switchVtoH():flag;
    }
    
	//******** Maker  map *********
	/**
	 * Simplifie la creation du plateau a serialise (sous forme d'un unique point).
	 * @param x Coordonnee X du point a placer.
	 * @param y Coordonnee Y du point a placer.
	 * @param caseobject Plateau a initialiser
	 */
	public void setObjectAtLocation(int x, int y, Case caseobject) {
		if(estValide(new Point(x,y))) {
			if(caseobject instanceof Poule)settings.grandirSauve();
			plateau.get(x).set(y, caseobject);
		}
	}
	
	/**
	 * Simplifie la creation du plateau a serialise (sous forme d'un carre).
	 * @param x Coordonnee X du point a placer.
	 * @param y Coordonnee Y du point a placer.
	 * @param caseobject Plateau a initialiser
	 */
	public void setSquareObjectAtLocation(int x, int y, Case caseobject) {
		if(estValide(new Point(x,y)) 
			&& estValide(new Point(x+1,y+1)) 
			&& estValide(new Point(x,y+1))
			&& estValide(new Point(x+1,y))){
				plateau.get(x).set(y, caseobject);
				plateau.get(x+1).set(y+1, caseobject);
				plateau.get(x+1).set(y, caseobject);
				plateau.get(x).set(y+1, caseobject);
		}
	}
	
	/**
	 * Simplifie la creation du plateau a serialise (sous forme d'une ligne).
	 * @param x Coordonnee X du point a placer.
	 * @param y Coordonnee Y du point a placer.
	 * @param caseobject Plateau a initialiser
	 */
	public void setLineXAtLocation(int x, int y, Case caseobject, int n) {
		for(int i = 0; i < n; i++) if(estValide(new Point(x+i,y)))plateau.get(x+i).set(y, caseobject);
	}
		
	/**
	 * Simplifie la creation du plateau a serialise (sous forme d'une colonne).
	 * @param x Coordonnee X du point a placer.
	 * @param y Coordonnee Y du point a placer.
	 * @param caseobject Plateau a initialiser
	 */
	public void setLineYAtLocation(int x, int y, Case caseobject, int n) {
		for(int i = 0; i < n; i++) if(estValide(new Point(x,y+i)))plateau.get(x).set(y+i, caseobject);
		
	}
	
	// Maker map
    	
}
