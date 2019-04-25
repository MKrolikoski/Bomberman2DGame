package krolikowski.game.entities.items.upgrades;

import java.util.Iterator;

import krolikowski.game.Handler;
import krolikowski.game.entities.Entity;
import krolikowski.game.entities.items.Item;
import krolikowski.game.entities.mobs.Player;

/**
 * Klasa abstrakcyjna reprezentuj¹ca ulepszenia, które gracz mo¿e podnieœæ
 *
 */
public abstract class Upgrade extends Item
{
	/**
	 * Konstruuje nowy Upgrade, przypisuj¹c mu przekazany Handler i koordynaty x,y
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 */
	public Upgrade(Handler handler, float x, float y)
	{
		super(handler, x, y, 12, 12);
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = width;
		bounds.height = height;
	}

	/**
	 * W momencie kolizji z graczem ulepszenie zostaje podniesione
	 */
	public boolean checkEntityCollision(float xOffset, float yOffset)
	{
		try
		{
			for (Iterator<Entity> it = handler.getMap().getEntityManager().getEntities().iterator(); it.hasNext();) 
			{ 
			    Entity entity = it.next();    
			    if (!(entity instanceof Player))
					continue;
				if (entity.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				{
					upgradePlayer((Player) entity);
					return true;
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * zwiêksza statystyki gracza w zale¿noœci od rodzaju ulepszenia
	 * @param p gracz
	 */
	protected abstract void upgradePlayer(Player p);
}
