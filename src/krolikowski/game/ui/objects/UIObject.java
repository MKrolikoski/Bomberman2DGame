package krolikowski.game.ui.objects;

import java.awt.Graphics;

import krolikowski.game.Handler;

/**
 * Klasa abstrakcyjna reprezentująca obiekt UI
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
	 * Konstruuje nowy UIObject, przypisując mu przekazany Handler, koordynaty x,y oraz szerokość i wysokość
	 * @param handler Handler
	 * @param x położenie x
	 * @param y połozenie y
	 * @param width szerokość
	 * @param height wysokość
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
	 * Zwraca położenie x obiektu
	 * @return położenie x
	 */
	public float getX()
	{
		return x;
	}

	/**
	 * Zwraca położenie y obiektu
	 * @return położenie y
	 */
	public float getY()
	{
		return y;
	}

	/**
	 * Zwraca szerokość obiektu
	 * @return szerokość
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Zwraca wysokość obiektu
	 * @return wysokość
	 */
	public int getHeight()
	{
		return height;
	}
	

}
