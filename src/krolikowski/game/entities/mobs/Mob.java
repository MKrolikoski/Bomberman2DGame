package krolikowski.game.entities.mobs;

import java.awt.Rectangle;

import krolikowski.game.Handler;
import krolikowski.game.entities.Entity;
import krolikowski.game.entities.items.Bomb;
import krolikowski.game.entities.items.upgrades.Upgrade;
import krolikowski.game.tiles.Tile;

/**
 * Klasa abstrakcyjna reprezentuj¹ca moba w grze
 *
 */
public abstract class Mob implements Entity
{
	/**
	 * Domyœlna szybkoœæ
	 */
	public static final float DEFAULT_SPEED = 1.0f;
	/**
	 * Domyœlna szerokoœæ
	 */
	public static final int DEFAULT_WIDTH = 32;
	/**
	 * Domyœlna wysokoœæ
	 */
	public static final int DEFAULT_HEIGHT = 32;

	protected float x, y;
	protected float xMove, yMove;
	protected float speed;
	protected boolean isMoving;

	protected int width;
	protected int height;

	protected Handler handler;
	protected Rectangle bounds;

	/**
	 * Konstruuje nowy obiekt Mob, przypisuj¹c mu przekazany Hanadler, koordynaty x,y, szerokoœc i wysokoœæ
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @param width szerokoœæ 
	 * @param height wysokoœæ
	 */
	public Mob(Handler handler, float x, float y, int width, int height)
	{
		this.handler = handler;
		this.x = x;
		this.y = y;
		xMove = 0;
		yMove = 0;
		speed = DEFAULT_SPEED * handler.getGame().getScale();
		this.width = width * handler.getGame().getScale();
		this.height = height * handler.getGame().getScale();

		bounds = new Rectangle(0, 0, width, height);
	}

	/**
	 * metoda odpowiedzialna za zmianê pozycji moba, która zachodzi jeœli nie wystêpuje kolizja z innymi elementami gry
	 */
	public void move()
	{
		if (!checkEntityCollision(xMove, 0f))
			moveX();
		if (!checkEntityCollision(0f, yMove))
			moveY();
		if (xMove != 0 || yMove != 0)
		{
			isMoving = true;
		} else
			isMoving = false;
	}

	/**
	 * zmienia po³o¿enie x o odpowiedni¹ wartoœæ xMove, jeœli w nowej lokalizacji nie wystepowaæ bêdzie kolizja z ¿adnym innym obiektem typu solid
	 */
	protected void moveX()
	{
		if (xMove > 0) // right
		{
			int xTemp = (int) (x + xMove + bounds.x + bounds.width) / Tile.getWidth();
			if ((!collisionWithTile(xTemp, (int) ((y + bounds.y) / Tile.getHeight())))
					&& (!collisionWithTile(xTemp, (int) ((y + bounds.y + bounds.height) / Tile.getHeight()))))
				x += xMove;
			else
			{
				x = xTemp * Tile.getWidth() - bounds.x - bounds.width - 1;
			}
		} else if (xMove < 0) // left
		{
			int xTemp = (int) (x + xMove + bounds.x) / Tile.getWidth();
			if ((!collisionWithTile(xTemp, (int) ((y + bounds.y) / Tile.getHeight())))
					&& (!collisionWithTile(xTemp, (int) ((y + bounds.y + bounds.height) / Tile.getHeight()))))
				x += xMove;
			else
			{
				x = xTemp * Tile.getWidth() + Tile.getWidth() - bounds.x;
			}
		}
	}

	/**
	 * zmienia po³o¿enie y o odpowiedni¹ wartoœæ yMove, jeœli w nowej lokalizacji nie wystepowaæ bêdzie kolizja z ¿adnym innym obiektem typu solid
	 */
	protected void moveY()
	{
		if (yMove > 0) // down
		{
			int yTemp = (int) (y + bounds.y + bounds.height + yMove) / Tile.getHeight();
			if ((!collisionWithTile((int) (x + bounds.x) / Tile.getWidth(), yTemp))
					&& (!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.getWidth(), yTemp)))
				y += yMove;
			else
			{
				y = yTemp * Tile.getHeight() - bounds.y - bounds.height - 1;
			}
		} else if (yMove < 0) // up
		{
			int yTemp = (int) (y + bounds.y + yMove) / Tile.getHeight();
			if ((!collisionWithTile((int) (x + bounds.x) / Tile.getWidth(), yTemp))
					&& (!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.getWidth(), yTemp)))
				y += yMove;
			else
			{
				y = yTemp * Tile.getHeight() + Tile.getHeight() - bounds.y;
			}
		}
	}


	@Override
	public boolean collisionWithTile(int x, int y)
	{
		return handler.getMap().getTile(x, y).isSolid();
	}

	@Override
	public Rectangle getCollisionBounds(float xOffset, float yOffset)
	{
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width,
				bounds.height);
	}

	/**
	 * Sprawdza kolizjê z innymi bytami, w momencie kolizji z bomb¹, mob umiera
	 */
	@Override
	public boolean checkEntityCollision(float xOffset, float yOffset)
	{
		try
		{
			for(int i = 0; i < handler.getMap().getEntityManager().getEntities().size(); ++i)
			{
				Entity entity = handler.getMap().getEntityManager().getEntities().get(i);
				if (entity == this || entity instanceof Player || entity instanceof Upgrade)
					continue;
				if (entity instanceof Bomb)
				{
					Bomb b = (Bomb) entity;
					if (b.isInside(this))
						return false;
				}
				if (entity.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
					return true;
			}
		} catch (Exception e)
		{
			System.out.println("Error: Mob - checkeEntityCollision()");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Zwraca szybkoœæ moba
	 * @return szybkoœæ moba
	 */
	public float getSpeed()
	{
		return speed;
	}

	/**
	 * Ustawia szybkoœæ moba
	 * @param speed szybkoœæ moba
	 */
	public void setSpeed(float speed)
	{
		this.speed = speed;
	}

	public float getY()
	{
		return y;
	}

	/**
	 * Ustawia po³o¿enie y
	 * @param y po³o¿enie y
	 */
	public void setY(float y)
	{
		this.y = y;
	}

	public float getX()
	{
		return x;
	}

	/**
	 * Ustawia po³o¿enie x
	 * @param x po³o¿enie x
	 */
	public void setX(float x)
	{
		this.x = x;
	}

	public int getHeight()
	{
		return height;
	}

	public int getWidth()
	{
		return width;
	}

	public boolean isMoving()
	{
		return isMoving;
	}

	/**
	 * Ustawia czy mob jest w fazie ruchu czy nie
	 * @param moving parametr okreœlaj¹cy czy mob siê porusza
	 */
	public void setMoving(boolean moving)
	{
		isMoving = moving;
	}

}
