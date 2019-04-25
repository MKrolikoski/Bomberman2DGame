package krolikowski.game.ui.objects;

import java.awt.Graphics;

import krolikowski.game.Handler;

/**
 * Klasa abstrakcyjna reprezentuj�ca obiekt UI
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
	 * Konstruuje nowy UIObject, przypisuj�c mu przekazany Handler, koordynaty x,y oraz szeroko�� i wysoko��
	 * @param handler Handler
	 * @param x po�o�enie x
	 * @param y po�ozenie y
	 * @param width szeroko��
	 * @param height wysoko��
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
	 * Zwraca po�o�enie x obiektu
	 * @return po�o�enie x
	 */
	public float getX()
	{
		return x;
	}

	/**
	 * Zwraca po�o�enie y obiektu
	 * @return po�o�enie y
	 */
	public float getY()
	{
		return y;
	}

	/**
	 * Zwraca szeroko�� obiektu
	 * @return szeroko��
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * Zwraca wysoko�� obiektu
	 * @return wysoko��
	 */
	public int getHeight()
	{
		return height;
	}
	

}
