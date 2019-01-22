package com.game.baer;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;

public class AudioPlayer
{
	Clip clip;
	
	public AudioPlayer()
	{	
	}

	
	public void play(String dateiname)
	{
		try 
		{
	    	
			File soundFile = new File(dateiname);
	        AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
	        AudioFormat format = ais.getFormat();
	        DataLine.Info info = new DataLine.Info(Clip.class, format, ((int) ais.getFrameLength() * format.getFrameSize()));
	        clip = (Clip) AudioSystem.getLine(info);
	        clip.open(ais);
	            
	        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue(1.0F);
	        clip.start();
	    } 
		catch (Exception e) 
		{
			 JOptionPane.showMessageDialog(null,"a","Titel", JOptionPane.PLAIN_MESSAGE);
	    }
		

	}

}
