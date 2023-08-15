package L2POO.Game.Vue;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import L2POO.Game.Vue.GraphiqueBuilder.JButtonGraphiqueBuilder;
import L2POO.Game.Vue.GraphiqueBuilder.JPanelGraphiqueBuilder;

public class SousMenu extends JDialog implements MouseMotionListener {
	
	/*
	 * JDialog personnalise
	 * 
	 */
	
	private JPanel p;
	private JButton b;
	
	SousMenu(JFrame frame){
		this.setLocationRelativeTo(frame);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("ressources/icon.jpg").getImage());
		this.setUndecorated(true);
		
		p = new JPanelGraphiqueBuilder("ressources/menuinformations.jpg");
	    p.setBorder(new EmptyBorder(5, 5, 5, 5));
		b = new JButtonGraphiqueBuilder("ressources/buttons/ok.png");
		p.setLayout(new BorderLayout());
		p.add(b, BorderLayout.SOUTH);
		b.addActionListener((e) -> {
			this.dispose();
		});
		frame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
		setContentPane(p);
		
		this.setVisible(true);
		this.setSize(600,600);
		this.setModal(true);
		this.setModalityType(ModalityType.APPLICATION_MODAL);

	    addMouseMotionListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	    e.translatePoint(e.getComponent().getLocation().x, e.getComponent().getLocation().y);
	    setLocation(e.getXOnScreen(), e.getYOnScreen());
	    repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

}
