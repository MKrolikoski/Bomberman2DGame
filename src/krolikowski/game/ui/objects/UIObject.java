package krolikowski.game.ui.objects;

import java.awt.Graphics;

import krolikowski.game.Handler;

/**
 * Klasa abstrakcyjna reprezentuj¹ca obiekt UI
 *
 */
public abstract class UIObject
{	
	protected static final int DEFAULT_WIDTH = 12;
	protected static final int DEFAULT_HEIGHT = 12;
	protected float x, y;
	protected int width, height;
	protected Handler handler;
	protected static long timer, lastTime;

	/**
	 * Konstruuje nowy UIObject, przypisuj¹c mu przekazany Handler, koordynaty x,y oraz szerokoœæ i wysokoœæ
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³ozenie y
	 * @param width szerokoœæ
	 * @param height wysokoœæ
	 */
	public UIObject(Handler handler, float x, float y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width*handler.getGame().getScale();
		this.height = height*handler.getGame().getScale();
		this.handler = handler;
	}
	
	/**
	 * Update zmiennych
	 */
	public abstract void tick();
	
	/**
	 * Renderuje obiekt
	 * @param g obiekt Graphics
	 */
	public abstract void render(Graphics g);

	/**
	 * Zwraca po³o¿enie x obiektu
	 * @return po³o¿enie x
	 */
	public float getX()
	{
		return x;
	}

	/**
	 * Zwraca po³o¿enie y obiektu
	 * @return po³o¿enie y
	 */
	public float getY()
	{
		return y;
	}

	/**
	 * Zwraca szerokoœæ obiektu
	 * @return szerokoœæ
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Zwraca wysokoœæ obiektu
	 * @return wysokoœæ
	 */
	public int getHeight()
	{
		return height;
	}
	

}
