package L2POO.Game.Vue.GraphiqueBuilder;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JButtonDynamiqueChangeGraphiqueBuilder  extends JButton {
	
	/*#GraphiqueBuilder
	 * 
	 * Redefinition d'un JButton pour applique plusieurs images
	 * 
	 */
	
	
	private Image scaledImg;
	
	
	public JButtonDynamiqueChangeGraphiqueBuilder(String chemin){
		ImageIcon icon = new ImageIcon(chemin);
		scaledImg = icon.getImage();  
		icon = new ImageIcon(scaledImg);
		setIcon(icon);
		setPreferredSize(new Dimension(icon.getIconWidth() -5, icon.getIconHeight() -5));
		setMaximumSize(new Dimension(icon.getIconWidth()-5, icon.getIconHeight() -5));
		setBorder(BorderFactory.createEmptyBorder());
		setContentAreaFilled(false);
		setBorderPainted(false);
		setOpaque(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	} 

	public void changeImage(String chemin) {
		ImageIcon icon = new ImageIcon(chemin);
		scaledImg = icon.getImage();  
		icon = new ImageIcon(scaledImg);
		setIcon(icon);
		repaint();
	}


}
