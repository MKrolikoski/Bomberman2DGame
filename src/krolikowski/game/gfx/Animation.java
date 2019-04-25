package krolikowski.game.gfx;

import java.awt.image.BufferedImage;

/**
 * Klasa odpowiedzialna za animacje
 *
 */
public class Animation
{
	private int speed;
	private int index;
	private long lastTime;
	private long timer;
	private BufferedImage[] frames;
	private int loopsLimit;
	private boolean looped;
	private int loopNumber;
	
	/**
	 * Konstruuje nowy obiekt Animation, przypisuj¹c mu przekazan¹ szybkoœæ zmiany animacji, a tak¿e tablicê z obrazami
	 * @param speed szybkoœæ
	 * @param frames tablica z obrazami
	 */
	public Animation(int speed, BufferedImage[] frames)
	{
		this.speed = speed;
		this.frames = frames.clone();
		index = 0;
		lastTime = System.currentTimeMillis();
		timer = 0;
		looped = false;
		loopsLimit = 1;
		loopNumber = 1;
	}
	
	/**
	 * zmienia dan¹ klatkê animacji w zale¿noœci od ustalonej szybkoœci
	 */
	public void tick()
	{
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
				
		if(timer >= speed)
		{
			timer = 0;
			if(index + 1 == frames.length)
			{
				if(loopNumber == loopsLimit)
					looped = true;
				else
					loopNumber++;
			}
			index = (index + 1) % (frames.length);
		}
	}
	
	/**
	 * Zwraca obecn¹ klatkê animacji
	 * @return obraz obecnej klatki animacji
	 */
	public BufferedImage getCurrentFrame()
	{
		return frames[index];
	}

	/**
	 * Zwraca informacje, czy animacja wykona³a pe³ny cykl okreœlon¹ iloœæ razy
	 * @return true jeœli animacja wykona³a pe³ny cykl okreœlon¹ iloœæ razy, false w przeciwnym wypadku
	 */
	public boolean didLoop()
	{
		return looped;
	}
	
	/**
	 * Ustawia iloœæ cykli do wykonania
	 * @param loopsLimit iloœæ cykli
	 */
	public void setLoopsLimit(int loopsLimit)
	{
		this.loopsLimit = loopsLimit;
	}
}
