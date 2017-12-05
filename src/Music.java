import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum Music
{
	GAME_THEME("assets/game.wav"),	// Game theme
	MENU_THEME("assets/menu.wav");	// Menu theme
	
	// Nested class for specifying volume
	public static enum Volume
	{
		MUTE, LOW, MEDIUM, HIGH
	}
	
	public static Volume volume = Volume.HIGH;
	   
	// Each sound effect has its own clip, loaded with its own sound file.
	private Clip clip;
	
	// Constructor to construct each element of the enum with its own sound file.
	Music(String musicFileName)
	{
		try
		{
			File file = new File(musicFileName);
			
			// Set up an audio input stream piped from the sound file.
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
	        
	        // Get a clip resource.
	        clip = AudioSystem.getClip();
	        
	        // Open audio clip and load samples from the audio input stream.
	        clip.open(audioInputStream);
		}
		catch (UnsupportedAudioFileException e)
	    {
			e.printStackTrace();
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    } 
	    catch (LineUnavailableException e)
		{
	        e.printStackTrace();
	    }
	}
	
	// Play or Re-play the sound effect from the beginning, by rewinding.
	public void play(Boolean loop)
	{
		if(volume != Volume.MUTE)
		{
			if(clip.isRunning())
				clip.stop();	// Stop the player if it is still running
			clip.setFramePosition(0);	// Rewind to the beginning
			clip.start();	// Start playing
	        if(loop)	//Loop if loop parameter is true
		    	clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	public void stop()	//stop playing and rewind to be played again from the beginning
	{
		clip.stop();
		clip.setFramePosition(0);
	}
	
	public void mute()	//don't play sounds(Mute Sound is selected from Options menu)
	{
		volume = Volume.MUTE;
	}
}