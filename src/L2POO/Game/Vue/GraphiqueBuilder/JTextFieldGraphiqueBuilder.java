package L2POO.Game.Vue.GraphiqueBuilder;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class JTextFieldGraphiqueBuilder extends JTextField{
	
	/*#GraphiqueBuilder
	 * 
	 * Redefinition d'un JTextField
	 * pour donner une style 
	 * 
	 */
	
	 private FontGraphiqueBuilder font;
	 
	 public JTextFieldGraphiqueBuilder() throws FontFormatException, IOException{
		 super();		
		 font = new FontGraphiqueBuilder(50f);
		 setFont(font.getFont());
		 setHorizontalAlignment(JTextField.CENTER);
		 setBorder(BorderFactory.createLineBorder(new Color(5,5,5,95), 5, true));
		 
	 }
	    @Override
	    protected void paintComponent(Graphics pG) {
	        super.paintComponent(pG);
	        Graphics2D g = (Graphics2D) pG;
	        g.setColor(Color.BLACK);
	        g.setStroke(new BasicStroke());
	        repaint();
	    }
}
