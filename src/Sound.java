import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum Sound 
{
	COMPUTER_SCORED("assets/compscored.wav"),
	PLAYER_SCORED("assets/playerscored.wav"),
	WALL_HIT("assets/wallhit.wav"),
	PADDLE_HIT("assets/paddlehit.wav");
	
	// Nested class for specifying volume
	public static enum Volume
	{
		MUTE, LOW, MEDIUM, HIGH
	}
	
	public static Volume volume = Volume.LOW;
	   
	// Each sound effect has its own clip, loaded with its own sound file.
	private Clip clip;
	
	// Constructor to construct each element of the enum with its own sound file.
	Sound(String soundFileName)
	{
		try
		{
			File file = new File(soundFileName);
			
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
    public void play()
    {
        if (clip.isRunning())
                clip.stop();
            clip.setFramePosition(0);
            clip.start();
    }
	
}
