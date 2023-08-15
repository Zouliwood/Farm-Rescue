package L2POO.Game.Vue.GraphiqueBuilder;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class JPanelGraphiqueBuilder extends JPanel{
	
	/*#GraphiqueBuilder
	 * 
	 * Redifinition d'un JPanel
	 * afin d'y integre une image
	 * 
	 */
	
	private BufferedImage image;
	private Image img;
	
	public JPanelGraphiqueBuilder(String chemin){
		try {
			image = ImageIO.read(new File(chemin));
		} catch (IOException e) {
			System.out.println("Ressources abscente, contactez un adminstateur");
		}
		img = image;
	}
	
	@Override
	 public void paintComponent(Graphics g) {
		 g.drawImage(img, 0, 0, getWidth(), getHeight() ,null);
	  }
			 

}
