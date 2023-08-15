package L2POO.Game.Vue.GraphiqueBuilder;

import java.awt.Color;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class JLabelGraphiqueBuilder extends JLabel {
	
	/* #GraphiqueBuilder
	 * 
	 * Redefintion d'un JLabel afin 
	 * de lui ajouter des animations de disparition progressif avec un
	 * timer
	 * 
	 */
	
	private Timer temps = new Timer(); // pour supprimer au bout de 5secondes d'affichages
	private int opacite = 200;
	
	
	public JLabelGraphiqueBuilder(String text, Font font, Color color){
		super(text);
		setFont(font);
		setForeground(color);
	}
	
	public JLabelGraphiqueBuilder() {
		super("");
	} 
	 
	public void disparitionprogressif(int seconde, String text, Color color, Font font) {
		if(opacite < 200) return; // deja en cours de disparition;
		setText(text);
		setForeground(color);
		setFont(font);
		temps.schedule(new TimerTask() {
			@Override
			public void run() {
				opacite -= 10;
				if(opacite <= 0) {
					cancel();
					setText("");
					opacite = 200;
				}
				setForeground(new Color(getForeground().getRed(), getForeground().getGreen(), getForeground().getBlue(), opacite));
			}
		}, seconde*1000, 70);
	}
	//surcharge
	public void disparitionprogressif(int seconde) {
		if(opacite < 200)return;
		
		temps.schedule(new TimerTask() {
			@Override
			public void run() {
				opacite -= 10;
				if(opacite <= 0) {
					cancel();
					setText("");
					opacite = 200;
				}
				setForeground(new Color(getForeground().getRed(), getForeground().getGreen(), getForeground().getBlue(), opacite));
			}
		}, seconde*1000, 70);
	}
	
	public void transitionText(int seconde, String text) {
		
		temps.schedule(new TimerTask() {
			@Override
			public void run() {
				opacite -= 10;
				if(opacite <= 0) {
					cancel();
					setText(text);
					opacite = 0;
				}
				setForeground(new Color(getForeground().getRed(), getForeground().getGreen(), getForeground().getBlue(), opacite));
			}
		}, 1, seconde*10);
		
		opacite = 0;
		
		temps.schedule(new TimerTask() {
			@Override
			public void run() {
				opacite += 10;
				if(opacite >= 200) {
					cancel();
					opacite = 200;
				}
				setForeground(new Color(getForeground().getRed(), getForeground().getGreen(), getForeground().getBlue(), opacite));
			}
		}, 1, seconde*10);
	}
}
