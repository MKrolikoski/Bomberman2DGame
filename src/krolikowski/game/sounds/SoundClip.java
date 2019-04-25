package krolikowski.game.sounds;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Klasa odpowiedzialna za muzyk� i d�wi�ki w grze
 *
 */

public class SoundClip
{
	private InputStream file;
	private Clip clip;
	private boolean playing;

	/**
	 * Konstruuje nowy SoundClip, przypisuj�c mu przekazany InputStream z d�wi�kiem
	 * @param file InputStream z d�wi�kiem
	 */
	public SoundClip(InputStream file)
	{
		this.file = file;
		createClip();
		changeVolume(-25f);
		playing = false;
	}
	
	/**
	 * Konstruuje nowy SoundClip, przypisuj�c mu przekazan� �cie�k� do d�wi�ku
	 * @param path �cie�ka do d�wi�ku
	 */
	public SoundClip(String path)
	{
		file = SoundClip.class.getResourceAsStream(path);
		createClip();
		changeVolume(-25f);
		playing = false;
	}

	private void changeVolume(float d)
	{
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(d);
	}

	private void createClip()
	{
		try
		{
			InputStream bufferedFile = new BufferedInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(bufferedFile));
		} catch (UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * odtwarza klip jeden raz
	 */
	public void playSound()
	{
		playing = true;
		resetSound();
		clip.loop(0);
		clip.start();
	}

	/**
	 * zap�tla klip
	 */
	public void loopSound()
	{
		playing = true;
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	/**
	 * zatrzymuje klip
	 */
	public void stopSound()
	{
		playing = false;
		clip.stop();
	}

	/**
	 * resetuje klip
	 */
	public void resetSound()
	{
		clip.setFramePosition(0);
	}

	/**
	 * Zwraca informacje, czy klip jest odtwarzany
	 * @return true je�li klip jest odtwarzany, false w przeciwnym wypadku
	 */
	public boolean isPlaying()
	{
		return playing;
	}

	/**
	 * Zamyka klip
	 */
	public void remove()
	{
		try
		{
			file.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
