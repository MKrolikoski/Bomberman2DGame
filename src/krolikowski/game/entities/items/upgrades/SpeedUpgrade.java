package krolikowski.game.entities.items.upgrades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import krolikowski.game.Handler;
import krolikowski.game.entities.mobs.Player;
import krolikowski.game.gfx.Textures;

/**
 * Klasa reprezentująca ulepszenie zwiększające szybkość gracza
 *
 */

public class SpeedUpgrade extends Upgrade
{
	private BufferedImage texture;

	/**
	 * Konstruuje nowy SpeedUpgrade, przypisując mu przekazany Handler i koordynaty x,y
	 * @param handler Handler
	 * @param x położenie x
	 * @param y połozenie y
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
