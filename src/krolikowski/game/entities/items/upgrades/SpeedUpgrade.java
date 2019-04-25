package krolikowski.game.entities.items.upgrades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import krolikowski.game.Handler;
import krolikowski.game.entities.mobs.Player;
import krolikowski.game.gfx.Textures;

/**
 * Klasa reprezentuj¹ca ulepszenie zwiêkszaj¹ce szybkoœæ gracza
 *
 */

public class SpeedUpgrade extends Upgrade
{
	private BufferedImage texture;

	/**
	 * Konstruuje nowy SpeedUpgrade, przypisuj¹c mu przekazany Handler i koordynaty x,y
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³ozenie y
	 */
	public SpeedUpgrade(Handler handler, float x, float y)
	{
		super(handler, x, y);
		texture = Textures.getSpeedUpgrade();
	}

	@Override
	public void tick()
	{
		checkEntityCollision(0f, 0f);
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(texture, (int)x, (int)y, width, height, null);
	}


	@Override
	protected void upgradePlayer(Player p)
	{
		p.addSpeed();
		handler.getMap().getEntityManager().removeEntity(this);
	}


}
