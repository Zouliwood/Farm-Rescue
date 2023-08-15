package L2POO.Game.Vue;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioSound {
	
	/*
	 * Cette classe permet de mettre une musique et 
	 * d'avoir plusieurs etat de volume
	 * 
	 */
    private AudioInputStream audioIn;
    private Clip clip;
	private int etatsVolume;
	
	AudioSound(){
		this.etatsVolume = 0;
		try {
			audioIn = AudioSystem.getAudioInputStream(new File("ressources/sound/backsound.wav"));
			clip = AudioSystem.getClip();

		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void resetEtat() {
		this.etatsVolume = 0;
	}
	public void upEtats() {
		this.etatsVolume++;
	}
	public int getEtat() {
		return this.etatsVolume;
	}
	
	void Stop() {
		baisseVolume(35f);
	}
	
	void baisseVolume(float volume) {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); 
		gainControl.setValue(-volume); 
	}
	
	void augmenteVolume() {
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); 
		gainControl.setValue(2f); 
	}
	
	void playSound() {
		try {
			clip.open(audioIn);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	    clip.start();
	    clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
}
