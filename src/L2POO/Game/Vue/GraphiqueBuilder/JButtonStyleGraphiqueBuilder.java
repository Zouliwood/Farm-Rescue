package L2POO.Game.Vue.GraphiqueBuilder;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;

public class JButtonStyleGraphiqueBuilder extends JButton implements MouseInputListener{
	
	/*#GraphiqueBuilder
	 * 
	 * Redefinition d'un JButton avec plus de style et 
	 * de l'animation
	 * 
	 */
	
	private Color origine;
	
	public JButtonStyleGraphiqueBuilder(String text, Color color, Font font){
		setText(text);
		setForeground(color);
		setBackground(new Color (195,195,186));
		setFont(font);
		setOpaque(false);
		setBorder(new EmptyBorder(5, 15, 5, 15));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		repaint();
        addMouseListener(this);
        addMouseMotionListener(this);
        origine = getBackground();
	}

    @Override
    public void paint(Graphics g) {
        paintBackground(g, this, this.getModel().isRollover() ? 10 : 0);
        super.paint(g);
    }

    private void paintBackground(Graphics g, JButton c, int yOffset) {
        Dimension size = c.getSize();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(c.getBackground());
        g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
        g.setColor(c.getBackground());
        g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
    }
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		repaint();
		// TODO Auto-generated method stub
		setBackground(new Color(85,85,85,90));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
		setBackground(origine);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
