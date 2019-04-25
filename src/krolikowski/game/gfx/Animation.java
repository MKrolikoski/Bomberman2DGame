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
	 * Konstruuje nowy obiekt Animation, przypisuj�c mu przekazan� szybko�� zmiany animacji, a tak�e tablic� z obrazami
	 * @param speed szybko��
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
	 * zmienia dan� klatk� animacji w zale�no�ci od ustalonej szybko�ci
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
	 * Zwraca obecn� klatk� animacji
	 * @return obraz obecnej klatki animacji
	 */
	public BufferedImage getCurrentFrame()
	{
		return frames[index];
	}

	/**
	 * Zwraca informacje, czy animacja wykona�a pe�ny cykl okre�lon� ilo�� razy
	 * @return true je�li animacja wykona�a pe�ny cykl okre�lon� ilo�� razy, false w przeciwnym wypadku
	 */
	public boolean didLoop()
	{
		return looped;
	}
	
	/**
	 * Ustawia ilo�� cykli do wykonania
	 * @param loopsLimit ilo�� cykli
	 */
	public void setLoopsLimit(int loopsLimit)
	{
		this.loopsLimit = loopsLimit;
	}
}
