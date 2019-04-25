package krolikowski.game.entities.items;
import java.awt.Rectangle;

import krolikowski.game.Handler;
import krolikowski.game.entities.Entity;

/**
 * Klasa abstrakcyjna reprezentuj¹ca przedmiot w grze
 *
 */

public abstract class Item implements Entity
{
	/**
	 * standardowa szerokoœæ przedmiotu
	 */
	public static final int DEFAULT_WIDTH = 16;
	/**
	 * standardowa wysokoœæ przedmiotu
	 */
	public static final int DEFAULT_HEIGHT = 16;
	
	protected float x, y;
		
	protected int width;
	protected int height;
	
	protected Handler handler;
	
	protected Rectangle bounds;
	

	/**
	 * Konstruuje nowy Item, przypisuj¹c mu przekazany Handler, koordynaty x,y, wysokoœc i szerokoœæ
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³ozenie y
	 * @param width szerokoœæ
	 * @param height wysokoœæ
	 */
	public Item(Handler handler, float x, float y, int width, int height)
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
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	@Override
	public boolean checkEntityCollision(float xOffset, float yOffset)
	{
		for(Entity entity : handler.getMap().getEntityManager().getEntities())
		{
			if(entity == this)
				continue;
			if(entity.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}
	
	@Override
	public boolean collisionWithTile(int x, int y)
	{
		return handler.getMap().getTile(x, y).isSolid();
	}
	
	public float getY()
	{
		return y;
	}
	public float getX()
	{
		return x;
	}
	public int getHeight()
	{
		return height;
	}
	public int getWidth()
	{
		return width;
	}
	

}
