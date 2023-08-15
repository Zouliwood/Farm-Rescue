package L2POO.Game.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import L2POO.Game.Controleur.ControleurEvent;
import L2POO.Game.Modele.Joueur.Joueur;
import L2POO.Game.Modele.Plateau.Case.Case;
import L2POO.Game.Vue.GraphiqueBuilder.FontGraphiqueBuilder;
import L2POO.Game.Vue.GraphiqueBuilder.JButtonDynamiqueChangeGraphiqueBuilder;
import L2POO.Game.Vue.GraphiqueBuilder.JButtonGraphiqueBuilder;
import L2POO.Game.Vue.GraphiqueBuilder.JButtonStyleGraphiqueBuilder;
import L2POO.Game.Vue.GraphiqueBuilder.JLabelGraphiqueBuilder;
import L2POO.Game.Vue.GraphiqueBuilder.JPanelGraphiqueBuilder;
import L2POO.Game.Vue.GraphiqueBuilder.JTextFieldGraphiqueBuilder;

public class VueFenetre extends JFrame {
	
	
	private ControleurEvent event;
	private Font font;

	//Panel
	private JDialog information;
	private JPanel touslesmenus;
	private MenuPrincpal depart;
	private MenuNiveau selection;
	private MenuPartie enjeu; 
	private AudioSound audio;
//	CardLayout card;  
	 
	 
	public VueFenetre() throws Exception, IOException{
		
		this.setTitle("Menu Niveau - FarmRescue");
		this.setSize(1280, 720);
		this.setPreferredSize(new Dimension(1280,720));
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("ressources/icon.jpg").getImage());
		this.setVisible(true);
		audio = new AudioSound();
		event = new ControleurEvent();	 
		FontGraphiqueBuilder builderFont = new FontGraphiqueBuilder(30f);
		font = builderFont.getFont();
		
		depart = new MenuPrincpal();
		touslesmenus = new JPanel();
		
		setContentPane(touslesmenus);
		setLayout(new BorderLayout());
		touslesmenus.add(depart);
		audio.playSound();

	}
	/*
	 * Cette fonction permet de faire une transition
	 * entre deux panels
	 * 
	 */
	public void switchPanel(JPanel panel) {
		touslesmenus.removeAll();
		touslesmenus.add(panel, BorderLayout.CENTER);
		touslesmenus.updateUI();
	}

	
	public class MenuPrincpal extends JPanel {
		 	
    private JPanel menuprincipal;
    private JPanel menuOutils;
    private JLabelGraphiqueBuilder messageerreur;
	
    private JButton bouttonjouer;
    private JButtonDynamiqueChangeGraphiqueBuilder sound;
    private JButton bouttonparametres;
    private JPanel message;
    private JTextField textpseudo;
 
    public MenuPrincpal() throws IOException, FontFormatException{ 
    	menuOutils = new JPanel();
        message = new JPanel();
        menuprincipal =  new JPanelGraphiqueBuilder("ressources/menuprincipal.jpg");
        messageerreur = new JLabelGraphiqueBuilder();
        bouttonjouer = new JButtonGraphiqueBuilder("ressources/img.png");
        bouttonparametres = new JButtonGraphiqueBuilder("ressources/img2.png");
        sound = new JButtonDynamiqueChangeGraphiqueBuilder("ressources/buttons/soundmax.png");
        textpseudo = new JTextFieldGraphiqueBuilder();
        
		/* GroupLayout*/
        GroupLayout LayoutPrincpal = new GroupLayout(menuOutils);
        menuOutils.setLayout(LayoutPrincpal);
        menuOutils.setPreferredSize(new Dimension(1280,720));
        menuOutils.setOpaque(false);
        
		/*X*/ 
        LayoutPrincpal.setHorizontalGroup(
        		LayoutPrincpal.createParallelGroup(GroupLayout.Alignment.LEADING)
        			.addGroup(LayoutPrincpal.createSequentialGroup().addGap(325).addComponent(messageerreur))
        			.addGroup(LayoutPrincpal.createSequentialGroup().addGap(455).addComponent(false, textpseudo, 320, 320, 320))
        			.addGroup(LayoutPrincpal.createSequentialGroup().addGap(450).addComponent(bouttonjouer))
        			.addGroup(LayoutPrincpal.createSequentialGroup().addGap(460).addComponent(bouttonparametres))
        			.addGroup(LayoutPrincpal.createSequentialGroup().addGap(1130).addComponent(sound))
        		);
        /* Y */
        LayoutPrincpal.setVerticalGroup(
        		LayoutPrincpal.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(LayoutPrincpal.createSequentialGroup().addGap(275).addComponent(messageerreur))					
					.addGroup(LayoutPrincpal.createSequentialGroup().addGap(310).addComponent(false, textpseudo, 100, 100, 100))
        			.addGroup(LayoutPrincpal.createSequentialGroup().addGap(420).addComponent(bouttonjouer))
        			.addGroup(LayoutPrincpal.createSequentialGroup().addGap(560).addComponent(bouttonparametres))
        			.addGroup(LayoutPrincpal.createSequentialGroup().addGap(560).addComponent(sound))
        		);
        menuprincipal.add(menuOutils);
        add(menuprincipal);
        listerners();
    }
    
    /*
     * Fonction qui contient tous les events
     * 
     */
    public void listerners() {
    	
    	sound.addActionListener((e) -> {
			if(audio.getEtat() == 0) {
				audio.upEtats();
				audio.baisseVolume(10f);
				sound.changeImage("ressources/buttons/soundmin.png");
			}else if(audio.getEtat() == 1) {
				audio.upEtats();
				audio.Stop();
				sound.changeImage("ressources/buttons/soundcut.png");
			}else {
				audio.augmenteVolume();
				audio.resetEtat();
				sound.changeImage("ressources/buttons/soundmax.png");
			}
    	});
    	
    	bouttonparametres.addActionListener((e) -> {
    			new SousMenu(VueFenetre.this);    		
    	});
    	
    	bouttonjouer.addActionListener((e) -> {
			String pseudo = depart.getPseudo().getText();

			if(pseudo.isEmpty()) {
				depart.addMessage("Erreur impossible text inexistant", Color.RED);
				resetTextFiled(pseudo);
				repaint();
				return;
			}
			if(pseudo.length() > 12 || pseudo.length() <= 3) {
				depart.addMessage("Erreur text trop long ou trop court", Color.RED);
				resetTextFiled(pseudo);
				repaint();
				return;
			} 
			if(!pseudo.chars().allMatch(Character::isLetter)) {// expression lamda qui verifie que c'est bien des lettres
				depart.addMessage("Erreur seulement des lettres", Color.RED);
				resetTextFiled(pseudo);
				repaint();
				return;
			}
			
			event.createPlayer(pseudo); 
			resetTextFiled(pseudo);
			
			try {
				selection = new MenuNiveau(event.getJoueur());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			switchPanel(selection);
		}); 
    }

    
	public void resetTextFiled(String pseudo) {
		getPseudo().setText("");
		pseudo = "";
	}
	public JTextField getPseudo() {
		return textpseudo;
	}
	
	public void addMessage(String text, Color color) {
		messageerreur.disparitionprogressif(1, text, color, font);
	}

	}
	 
	
	/*
	 * Classe interne MenuNiveau
	 * qui créer des JPanel avec des niveaux
	 * 
	 */
	public class MenuNiveau extends JPanel{
		

	private JPanelGraphiqueBuilder menuprincipal;

	private JPanel menuNiveau;
	private JPanel menuSousMenu;
    
	private JLabelGraphiqueBuilder message; 
	private JLabel points; 
	private JLabel pseudo;
	private JLabel vie; 
    
	private JButton bouttonretour;

	private List<JButton> listniveau;
	private JButton envie, niveau1, niveau2, niveau3, niveau4, niveau5, niveau6, niveauAleatoire;

	public MenuNiveau(Joueur joueur) throws IOException{ 
		
		pseudo = new JLabelGraphiqueBuilder("", font, Color.white);
		points = new JLabelGraphiqueBuilder("", font, Color.white);
		vie = new JLabelGraphiqueBuilder("", font, Color.white);
		message = new JLabelGraphiqueBuilder("", font, Color.black);
		miseajour();
		//Mettre dans une fonction
		niveau1 = new JButtonStyleGraphiqueBuilder("Niveau 1", Color.white, font);
		niveau2 = new JButtonStyleGraphiqueBuilder("Niveau 2", Color.white, font);
		niveau3 = new JButtonStyleGraphiqueBuilder("Niveau 3", Color.white, font);
		niveau4 = new JButtonStyleGraphiqueBuilder("Niveau 4", Color.white, font);
		niveau5 = new JButtonStyleGraphiqueBuilder("Niveau 5", Color.white, font);
		niveau6 = new JButtonStyleGraphiqueBuilder("Niveau 6", Color.white, font);
		niveauAleatoire = new JButtonGraphiqueBuilder("ressources/buttons/modealea.png");
//		edition = new JButtonGraphiqueBuilder("ressources/buttons/editer.png");
		envie = new JButtonGraphiqueBuilder("ressources/buttons/vieplus.png");
		listniveau = new ArrayList<>();
		addListButton();
		miseajourNiveau();
		
		menuNiveau = new JPanel();
		menuprincipal =  new JPanelGraphiqueBuilder("ressources/selectionniveau.gif");
		bouttonretour = new JButtonGraphiqueBuilder("ressources/retour.png");
		menuSousMenu = new JPanelGraphiqueBuilder("ressources/sousmenu.png");
        
			/* GroupLayout pour les niveaux*/
	    menuNiveau.setLayout(new GridLayout(2,3, 25, 25));
	    menuNiveau.add(niveau1);
	    menuNiveau.add(niveau2);
	    menuNiveau.add(niveau3);
	    menuNiveau.add(niveau4);
	    menuNiveau.add(niveau5);
	    menuNiveau.add(niveau6);
	    menuNiveau.setOpaque(false);
		
		/* GroupLayout pour menuSousMenu*/
        GroupLayout LayoutMenuSous = new GroupLayout(menuSousMenu);
        menuSousMenu.setLayout(LayoutMenuSous);
        

		/*MenuPrincpal X*/
        LayoutMenuSous.setHorizontalGroup(
        		LayoutMenuSous
        		.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(LayoutMenuSous.createSequentialGroup().addGap(40).addComponent(pseudo))
        		.addGroup(LayoutMenuSous.createSequentialGroup().addGap(100).addComponent(points))        				
        		.addGroup(LayoutMenuSous.createSequentialGroup().addGap(320).addComponent(vie))        				
        		);
        
        
        /*MenuPrincpal  Y */
        LayoutMenuSous.setVerticalGroup(
        		LayoutMenuSous.createParallelGroup(GroupLayout.Alignment.LEADING)
        			.addGroup(LayoutMenuSous.createSequentialGroup().addGap(30).addComponent(pseudo))
					.addGroup(LayoutMenuSous.createSequentialGroup().addGap(110).addComponent(points))
	        		.addGroup(LayoutMenuSous.createSequentialGroup().addGap(110).addComponent(vie))        				
        		);
			/* GroupLayout pour MenuPrincpal*/
	        GroupLayout LayoutMenuPrincipal = new GroupLayout(menuprincipal);
	        menuprincipal.setLayout(LayoutMenuPrincipal);
			/*MenuPrincpal X*/
	        LayoutMenuPrincipal.setHorizontalGroup(
	        		LayoutMenuPrincipal.createParallelGroup(GroupLayout.Alignment.LEADING)
	        		.addGroup(LayoutMenuPrincipal.createSequentialGroup().addGap(0).addComponent(bouttonretour))
	        		.addGroup(LayoutMenuPrincipal.createSequentialGroup().addGap(300).addComponent(message))
	        		.addGroup(LayoutMenuPrincipal.createSequentialGroup().addGap(0).addComponent(menuSousMenu, 450, 450, 450))
	        		.addGroup(LayoutMenuPrincipal.createSequentialGroup().addGap(235).addComponent(menuNiveau, 800,800,800))
	        		.addGroup(LayoutMenuPrincipal.createSequentialGroup().addGap(900).addComponent(niveauAleatoire))
//	        		.addGroup(LayoutMenuPrincipal.createSequentialGroup().addGap(625).addComponent(edition))
	        		.addGroup(LayoutMenuPrincipal.createSequentialGroup().addGap(450).addComponent(envie)));
	        /*MenuPrincpal  Y */
	        LayoutMenuPrincipal.setVerticalGroup(
	        		LayoutMenuPrincipal.createParallelGroup(GroupLayout.Alignment.LEADING)
	        		.addGroup(LayoutMenuPrincipal.createSequentialGroup().addGap(0).addComponent(bouttonretour))
	        		.addGroup(LayoutMenuPrincipal.createSequentialGroup().addGap(80).addComponent(message))
	        		.addGroup(LayoutMenuPrincipal.createSequentialGroup().addGap(480).addComponent(menuSousMenu, 200, 200, 200))
	        		.addGroup(LayoutMenuPrincipal.createSequentialGroup().addGap(135).addComponent(menuNiveau,400,400,400))
	        		.addGroup(LayoutMenuPrincipal.createSequentialGroup().addGap(560).addComponent(niveauAleatoire))
//	        		.addGroup(LayoutMenuPrincipal.createSequentialGroup().addGap(560).addComponent(edition))
	        		.addGroup(LayoutMenuPrincipal.createSequentialGroup().addGap(560).addComponent(envie)));
	        
		setLayout(new BorderLayout());
		add(menuprincipal);

		envie.addActionListener((e) -> {
			if(event.getJoueur().getPoints() >= 100) {
				event.getJoueur().ajoutVie();
				event.getJoueur().removePoints(100);
				message.disparitionprogressif(3, "Vous avez achete 1 vie pour jouer",  new Color(68,68,69), font);
				miseajour();
			}else {
				message.disparitionprogressif(3, "Vous n'avez pas assez de points 100min", new Color(139,0,0), font);
			}
			});
		bouttonretour.addActionListener((e) -> {switchPanel(depart);});

		niveauAleatoire.addActionListener((e) -> {event.playAleatoire();switchPanel(new MenuPartie());});
		
		niveau1.addActionListener((e) -> {checkPlay(0); });
		niveau2.addActionListener((e) -> { checkPlay(1);});
		niveau3.addActionListener((e) -> { checkPlay(2);});
		niveau4.addActionListener((e) -> { checkPlay(3);});
		niveau5.addActionListener((e) -> { checkPlay(4);});
		niveau6.addActionListener((e) -> { checkPlay(5);});

	}
	
	/*
	 * Verifie si le nombre de vie du joueur est superieur a 1
	 * 
	 */
	public void checkPlay(int niveau) {
		if(event.getJoueur().getVie() >= 1) {
			event.getJoueur().retireVie();
			event.playToPlateau(niveau); 
			switchPanel(new MenuPartie());
		}else {
			message.disparitionprogressif(3, "Vous n'avez plus de vie ! Essayez le mode aleatoire ", new Color(139,0,0), font);
		}
	}
	
	private void addListButton() {
		listniveau.add(niveau1);
		listniveau.add(niveau2);
		listniveau.add(niveau3);
		listniveau.add(niveau4);
		listniveau.add(niveau5);
		listniveau.add(niveau6);
	}
	
	/*
	 * Met a jour les informations du joueur
	 * 
	 */
	public void miseajour() {
		this.pseudo.setText(event.getJoueur().getPseudo());
		this.points.setText(String.valueOf(event.getJoueur().getPoints()));
		this.vie.setText(String.valueOf(event.getJoueur().getVie()));
	}
	
	public void miseajourNiveau() {
		for(int i = 0; i < event.getJoueur().getLastWin(); i++) {
			for(int pos = 0; pos < listniveau.size(); pos++) {
				if(pos <= i ) {
			 		listniveau.get(pos).setEnabled(true);
					listniveau.get(pos).setText( "Niveau "); 		
				}else {
					listniveau.get(pos).setEnabled(false);
					listniveau.get(pos).setText( "Bloque"); 
				}

			}
		} 
	}
	
	}

	/*
	 * Classe interne MenuPartie
	 * qui créer des JPanel pour pouvoir jouer
	 * 
	 */
	public class MenuPartie extends JPanelGraphiqueBuilder{
		
		private boolean robot;
		private Timer timerRobot = new Timer();

		private JPanel plateau, boiteJeu, statistique;
		
		private JLabelGraphiqueBuilder messageJeu;
		private JLabel petsSauve, deplacements, points;
		
		private JPanel buttonPanel;
		private JButtonGraphiqueBuilder buttonRecommencer, buttonCoupArriere, buttonIndice, buttonRobot, buttonRetour;
 
		public MenuPartie() {
			super("ressources/menujeu.jpg");
			statistique = new JPanelGraphiqueBuilder("ressources/menuStatsJeu.png");
			
			plateau = new JPanel();
			buttonPanel = new JPanel();
			boiteJeu = new JPanelGraphiqueBuilder("ressources/fondenjeu.png");
			buttonRecommencer = new JButtonGraphiqueBuilder("ressources/buttons/recommencer.png");
			buttonCoupArriere = new JButtonGraphiqueBuilder("ressources/buttons/retourcoup.png");
			buttonIndice = new JButtonGraphiqueBuilder("ressources/buttons/indice.png");
			buttonRobot = new JButtonGraphiqueBuilder("ressources/buttons/robot.png");
			buttonRetour = new JButtonGraphiqueBuilder("ressources/buttons/retour.png");
			if(event.getPlateau().getSetting().getNiveau() == -1) buttonRecommencer.setVisible(false);
			plateau.setOpaque(false);
			plateau.setBorder(BorderFactory.createEmptyBorder(5,125,5,125)); 
			boiteJeu.setBorder(BorderFactory.createEmptyBorder(30,30,30,0)); 
		
			if(event.getJoueur() == null || event.getPlateau() == null) {
				petsSauve = new JLabelGraphiqueBuilder("Erreur", font, Color.red);
				points = new JLabelGraphiqueBuilder("Erreur", font, Color.red);
				messageJeu = new JLabelGraphiqueBuilder("Erreur aucun plateau chargee", font, Color.red);            
			}else miseajourPanel();
					
			
            GroupLayout LayoutStats = new GroupLayout(statistique);
            statistique.setLayout(LayoutStats);
            
            LayoutStats.setHorizontalGroup(
            		LayoutStats
                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(LayoutStats.createSequentialGroup().addGap(280).addComponent(petsSauve))
                    .addGroup(LayoutStats.createSequentialGroup().addGap(380).addComponent(deplacements))
                    .addGroup(LayoutStats.createSequentialGroup().addGap(210).addComponent(points))
            		);
    
            /*MenuPrincpal  Y */
            LayoutStats.setVerticalGroup(
            		LayoutStats.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(LayoutStats.createSequentialGroup().addGap(30).addComponent(petsSauve))
                    .addGroup(LayoutStats.createSequentialGroup().addGap(110).addComponent(deplacements))
                    .addGroup(LayoutStats.createSequentialGroup().addGap(240).addComponent(points))
            		);

            
            GroupLayout LayoutBouttons = new GroupLayout(buttonPanel);
            buttonPanel.setLayout(LayoutBouttons);
            buttonPanel.setOpaque(false);
            
            LayoutBouttons.setHorizontalGroup(
            		LayoutBouttons
                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(LayoutBouttons.createSequentialGroup().addGap(100).addComponent(buttonRecommencer))
                    .addGroup(LayoutBouttons.createSequentialGroup().addGap(340).addComponent(buttonCoupArriere))
                    .addGroup(LayoutBouttons.createSequentialGroup().addGap(650).addComponent(buttonIndice))
                    .addGroup(LayoutBouttons.createSequentialGroup().addGap(950).addComponent(buttonRobot))
                    .addGroup(LayoutBouttons.createSequentialGroup().addGap(0).addComponent(buttonRetour))
            		);
    
            /*MenuPrincpal  Y */
            LayoutBouttons.setVerticalGroup(
            		LayoutBouttons.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(LayoutBouttons.createSequentialGroup().addGap(0).addComponent(buttonRecommencer))
                    .addGroup(LayoutBouttons.createSequentialGroup().addGap(200).addComponent(buttonCoupArriere))
                    .addGroup(LayoutBouttons.createSequentialGroup().addGap(200).addComponent(buttonIndice))
                    .addGroup(LayoutBouttons.createSequentialGroup().addGap(200).addComponent(buttonRobot))
                    .addGroup(LayoutBouttons.createSequentialGroup().addGap(180).addComponent(buttonRetour))
            		);

            GroupLayout LayoutJeuText = new GroupLayout(this);
            setLayout(LayoutJeuText);
            
            LayoutJeuText.setHorizontalGroup(
                    LayoutJeuText
                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(LayoutJeuText.createSequentialGroup().addGap(20).addComponent(statistique,500,500,500))
                    .addGroup(LayoutJeuText.createSequentialGroup().addGap(670).addComponent(boiteJeu,600,600,600))
                    .addGroup(LayoutJeuText.createSequentialGroup().addGap(10).addComponent(buttonPanel,1260,1260,1260))
            		);
    
            /*MenuPrincpal  Y */
            LayoutJeuText.setVerticalGroup(
                    LayoutJeuText.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(LayoutJeuText.createSequentialGroup().addGap(0).addComponent(statistique,350,350,350))
                    .addGroup(LayoutJeuText.createSequentialGroup().addGap(20).addComponent(boiteJeu, 500,500,500))
                    .addGroup(LayoutJeuText.createSequentialGroup().addGap(350).addComponent(buttonPanel, 330,330,330))

            		);
            
            boiteJeu.setLayout(new BorderLayout());            
            boiteJeu.add(messageJeu, BorderLayout.NORTH);
            boiteJeu.add(plateau, BorderLayout.CENTER);
			revalidate();
			listernersEvent();
			}
		
	    /*
	     * Fonction qui contient tous les events
	     * 
	     */
		public void listernersEvent() {
			if(robot)return;

				buttonRetour.addActionListener((e) -> {
					try {
						switchPanel(new MenuNiveau(event.getJoueur()));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				});
			
			
			buttonRecommencer.addActionListener((e) -> {
  				event.playToPlateau(event.getPlateau().getSetting().getNiveau() -1);
	  			miseajourAll();
			});

			buttonCoupArriere.addActionListener((e) -> {
				if(event.getPlateau().canplay() != -1) {
				if(event.peutRetour()) event.retourCoup();
				else {
					messageJeu.transitionText(3, "Action deja effectuee ");
					miseajourMessage();
				}
				miseajourAll();
				}else messageJeu.transitionText(1, "Partie terminee !");
			});
			
			buttonIndice.addActionListener((e) -> {
				if(event.getPlateau().canplay() != -1) {
					
				if(event.getPlateau().getSetting().getPoints() >= 20) {
					Point p = event.getPlateau().solutionBot();
					if(p == null) {
						messageJeu.transitionText(1, "Plus aucun coup jouable");
						return;
					}
					event.getPlateau().getSetting().removePoints(20);
					messageJeu.transitionText(1, "Vous pouvez jouer en x:" + (int) p.getX() + " y:" + ((int) p.getY() - event.getPlateau().getSetting().getNotVisibilite()));
		  			miseajourAll();
				
		  			for(Component TypeElment: plateau.getComponents()) 
						if( ((ElementCase) TypeElment).getXElement() == p.getX() && ((ElementCase) TypeElment).getYElement() == p.getY()) 
							((ElementCase) TypeElment).setBorder(BorderFactory.createLineBorder(Color.orange, 3));
					
				}else messageJeu.transitionText(1, "Pas assez de points");
			}else  messageJeu.transitionText(1, "Partie terminee !");
			}); 
			 
			
			buttonRobot.addActionListener((e) -> { 		
				timerRobot.schedule(new TimerTask() {
					
					@Override
					public void run() {
			  			if(event.getPlateau().canplay() == 0) {
								try {
									Point p  = event.getPlateau().solutionBot();
									if(p == null) {messageJeu.transitionText(1, "Plus aucun coup jouable");return;}
									event.play((int) p.getX(), (int) p.getY());
						  			miseajourAll();
								} catch (Exception error) {
									error.printStackTrace();
								}
			  			}else if(event.getPlateau().canplay() == -1)  { 
					  			messageJeu.transitionText(1, "Victoire ! Niveau debloque");
					  			try {
									event.victoire();
								} catch (Exception e) {
									System.out.println("Erreur ControleurEvent");
								}
					  			if(event.getPlateau().getSetting().getNiveau() == event.getJoueur().getLastWin())event.getJoueur().niveauDebloque();
				  				updateUI();
				  				cancel();
			  			
			  			} else { 
					  			messageJeu.transitionText(1,"Defaite ! Ressayer");
					  			miseajourAll();
				  				cancel();
			  			}
					}
				}, 100, 1*1000);
				this.robot = true;
			});
			revalidate();
		}

		public void setPoints(int point) {
			int pointtransform = Integer.valueOf(points.getText());
			pointtransform = point;
			this.points.setText(String.valueOf(pointtransform));
		} 
		 	
		
		/* Mise a jour des panels */
		
		public void miseajourAll() {
			miseajour();
			miseajourStatistique();
			updateUI();
		}
		
		public void miseajour() { 
			plateau.removeAll();
			int temp = 0;
			for(int y = event.getPlateau().getSetting().getNotVisibilite(); y < event.getPlateau().getSetting().getLigne(); y++){
				for(ArrayList<Case> a : event.getPlateau().getTableau()) {
					plateau.add(new ElementCase(temp,y));
					temp++;
				}
				temp= 0;
			}
			updateUI();
		}
		
		public void miseajourStatistique() {
			setPoints(event.getPlateau().getSetting().getPoints());
			miseajourPets(String.valueOf(event.getPlateau().getSetting().getSauve()));
			miseajourDeplacement(String.valueOf(event.getPlateau().getSetting().getDeplacement()));
		}
		
		public void miseajourPets(String pet) {
			petsSauve.setText(pet);
		}
		public void miseajourDeplacement(String deplacement) {
			deplacements.setText(deplacement);
		}
		public void miseajourMessage() {
			messageJeu.disparitionprogressif(1);
		}
		
		
		public void miseajourPanel() {
	        messageJeu = new JLabelGraphiqueBuilder("Niveau " + (event.getPlateau().getSetting().getNiveau() == -1 ? " Aleatoire" :  (event.getPlateau().getSetting().getNiveau() + " Bonne partie")), font, Color.white);            
            deplacements = new JLabelGraphiqueBuilder(String.valueOf(event.getPlateau().getSetting().getDeplacement()),font, Color.white );
	        petsSauve = new JLabelGraphiqueBuilder(String.valueOf(event.getPlateau().getSetting().getSauve()),font, Color.white );
            points = new JLabelGraphiqueBuilder("0",font, Color.white );
			plateau.setLayout(new GridLayout(event.getPlateau().getSetting().getLigne()-event.getPlateau().getSetting().getNotVisibilite(),event.getPlateau().getSetting().getColonne()));
			miseajour();
			updateUI();
			revalidate();		
		} 
		
		/* Mise a jour des panels */

		/*
		 * Classe interne qui represente une Case
		 * on genere donc autant de fois ElementCase qu'il
		 * y'a de case dans un plateau
		 * 
		 */
		private class ElementCase extends JPanel implements MouseInputListener{
			private int x, y;
			private boolean hover;
			private BufferedImage image;	
			
			ElementCase(int x, int y){
				this.y = y;
				this.x = x;
				try {
					image = ImageIO.read(new File("ressources/blocs/" + event.getPlateau().getTableau().get(x).get(y).afficheGraphique()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				setOpaque(false);
				addMouseListener(this);
		        addMouseMotionListener(this);
		        repaint();
			}
			
			public int getXElement() {return x;}
			public int getYElement() {return y;}

		    public void paintComponent(Graphics g){
		        super.paintComponent(g);
		        g.drawImage((Image)image,0,0, 60, 60, this);
		        if(hover) {
		            g.setColor(new Color(250,250,250,75));
		            g.fillRoundRect(0, 0, image.getWidth(this)-30, image.getHeight(this)-35, 5,5);
		        }else g.setColor(new Color(0,0,0,0));
		    }
		    
			@Override
			public void mouseClicked(MouseEvent ev) {}
			@Override
			public void mouseEntered(MouseEvent eventMouse) {hover = true;repaint();}
			@Override
			public void mouseExited(MouseEvent eventMouse) {hover = false;repaint();}
			@Override
			public void mousePressed(MouseEvent eventMouse) {
				if(event.getPlateau() == null)return;
				try {
					 event.play(x, y);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent eventMouse) {
				if(event.getPlateau() == null)return;
				if(event.getPlateau().canplay() == 0) {miseajourAll();return;}
				if(event.getPlateau().canplay() == -1)  { 
					if(event.getPlateau().getSetting().getNiveau() == -1) messageJeu.transitionText(1, "Victoire ! ");
	  				else messageJeu.transitionText(1, "Victoire ! Niveau debloque");
					try {
						event.victoire();
					} catch (Exception e) {
						e.printStackTrace();
					}
	  			} else messageJeu.transitionText(1,"Defaite ! Ressayer");
				miseajourAll();
			}
			@Override
			public void mouseDragged(MouseEvent event) {}
			@Override
			public void mouseMoved(MouseEvent event) {}
		}
	}

	
	
	/*
	 * 
	 * Volonte de creer un outil de edition de niveaux pour 
	 * avoir des niveaux customises 
	 * Cependant par manque de temps nous ne pouvons pas rendre
	 * un travail inacheve. Nous avons decide de le retirer
	 * 
	 * 
	 */
	/* 
	public class MenuEdition extends JPanel{
		
		private JPanel ensemble;
		private JComboBox<Plateau> list;
		private JButton creer, editer, tester;
		
		public MenuEdition() {
			ensemble = new JPanel();
			list = new JComboBox(donneDisponible());
			creer = new JButton("Creer");
			editer = new JButton("Editer");
			tester = new JButton("tester");
			
			ensemble.add(list);
			ensemble.add(creer);
			ensemble.add(editer);
			ensemble.add(tester);
			ensemble.setLayout(new GridLayout(4,1,20,20));
			add(ensemble, BorderLayout.CENTER);
			
			listerners();
			
		}

		public String[] donneDisponible() {
			String[] list =  new File("niveaux/separation").list();
			for(int i = 0; i < list.length; i++) list[0]= list[0].replace(".db", "");
			return list;
		}
		
		public void listerners() {
			creer.addActionListener((e) -> {
				switchPanel(new Editor());
			});
			editer.addActionListener((e) -> {
				switchPanel(new Editor(String.valueOf(list.getSelectedItem())));
			});
			tester.addActionListener((e) -> {
				
			});
			
		}
		

		private class Editor extends JPanel{
			
		private JPanel rangement, creation, plateau, selection;
		private JButton valider;
		private JTextFieldGraphiqueBuilder messagebox;

		Editor(){
			creation = new JPanel();
			plateau = new JPanel();
			selection = new JPanel();
			rangement =  new JPanel();
			valider = new JButton("Valider");
			try {
				messagebox = new JTextFieldGraphiqueBuilder();
			} catch (FontFormatException | IOException e) {
			}
			
			creation.setBackground(Color.black);
			plateau.setBackground(Color.blue);
			selection.setBackground(Color.RED);
			rangement.setLayout(new BorderLayout());
			rangement.add(creation, BorderLayout.WEST);
			rangement.add(plateau, BorderLayout.CENTER);
			rangement.add(selection, BorderLayout.WEST);
			rangement.add(valider, BorderLayout.SOUTH);
			rangement.add(messagebox, BorderLayout.EAST);

			event.createEmptyPlateau();
			miseajour();
			add(rangement);
			listernersEvent();
			}
		
		public void listernersEvent() {
			valider.addActionListener((e) -> {
				if(messagebox.getText().length() < 0 ) return;

				try {
					
					SaveObject.serialisation("niveaux/serparation/"+ messagebox.getText(), event.getPlateau().getClone());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		}
		
	
		Editor(String map){
			creation = new JPanel();
			plateau = new JPanel();
			selection = new JPanel();
			rangement =  new JPanel();
			rangement.setLayout(new BorderLayout());
			rangement.add(creation, BorderLayout.WEST);
			rangement.add(plateau, BorderLayout.CENTER);
			rangement.add(selection, BorderLayout.WEST);
			event.plateauDeserialise(map);
			miseajour();
		}
			public void miseajour() { 
				plateau.removeAll();
				plateau.setLayout(new GridLayout(event.getPlateau().getSetting().getColonne(), event.getPlateau().getSetting().getLigne()));
				for(int y = event.getPlateau().getSetting().getNotVisibilite(); y < event.getPlateau().getSetting().getLigne(); y++){
					for(int x = 0; x < event.getPlateau().getSetting().getColonne(); x++) {
						plateau.add(new JButtonGraphiqueBuilder("ressources/blocs/" + event.getPlateau().getTableau().get(x).get(y).afficheGraphique()));
					}
				}
				plateau.updateUI();
			}
		}
	}

	
	*/

	}
	


