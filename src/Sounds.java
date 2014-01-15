/*
 * Sounds.java permite controlar los efectos de sonido del juego 
 * Author: Jassael Ruiz
 * Version: 1.0
 */

import java.applet.*;
import java.net.URL;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Sounds {
	AudioClip audio;
	URL url;
	String songs[] = { "intro", "bullet", "bomb", "power", "explosion", "game",
			"grito", "risa", "winner" };
	HashMap<String, AudioClip> sounds;

	public Sounds() {
		// class constructor, initialize all the audio clips
		sounds = new HashMap<String, AudioClip>();
		for (String name : songs)
			sounds.put(name, getAudioClip("sounds/" + name + ".wav"));
	}

	private AudioClip getAudioClip(String name) {
		// return the audio clip
		try {
			url = getClass().getClassLoader().getResource(name);
			audio = Applet.newAudioClip(url);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo cargar el sonido",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return audio;
	}

	public void play(final String name) {
		// play the audio clip
		new Thread(new Runnable() {

			@Override
			public void run() {
				audio = sounds.get(name);
				audio.play();
			}
		}).start();
	}

	public void stop(String name) {
		// stop the audio clip
		audio = sounds.get(name);
		audio.stop();
	}

	public void loop(final String name) {
		// loop the audio clip
		new Thread(new Runnable() {

			@Override
			public void run() {
				audio = sounds.get(name);
				audio.loop();
			}
		}).start();
	}
}
