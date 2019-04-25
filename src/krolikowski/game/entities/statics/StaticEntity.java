package krolikowski.game.entities.statics;

import java.awt.Rectangle;

import krolikowski.game.Handler;
import krolikowski.game.entities.Entity;

/**
 * klasa abstrakcyjna reprezentuj¹ca statyczny byt
 *
 */
public abstract class StaticEntity implements Entity
{
	protected Handler handler;
	protected float x;
	protected float y;
	protected int width;
	protected int height;
	protected Rectangle bounds;
	
	/**
	 * Konstruuje nowy obiekt StaticEntity, przypisuj¹c mu przekazany Handler, koordynaty x,y, szerokoœæ i wysokoœæ
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @param width szerokoœæ
	 * @param height wyoskoœæ
	 */
	public StaticEntity(Handler handler, float x, float y, int width, int height)
	{
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width*handler.getGame().getScale();
		this.height = height*handler.getGame().getScale();
		
		bounds = new Rectangle(0, 0, width, height);
	}
	
	@Override
	public Rectangle getCollisionBounds(float xOffset, float yOffset)
	{
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width,
				bounds.height);
	}
	
	@Override
	public float getY()
	{
		return y;
	}

	@Override
	public float getX()
	{
		return x;
	}

	@Override
	public int getHeight()
	{
		return height;
	}

	@Override
	public int getWidth()
	{
		return width;
	}

}
