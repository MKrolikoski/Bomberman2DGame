package krolikowski.game.entities.items.upgrades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import krolikowski.game.Handler;
import krolikowski.game.entities.mobs.Player;
import krolikowski.game.gfx.Textures;

/**
 * Klasa reprezentuj�ca ulepszenie zwi�kszaj�ce ilo�� bomb gracza
 *
 */

public class BombUpgrade extends Upgrade
{
	private BufferedImage texture;

	/**
	 * Konstruuje nowy BombUpgrade, przypisuj�c mu przekazany Handler i koordynaty x,y
	 * @param handler Handler
	 * @param x po�o�enie x
	 * @param y po�ozenie y
	 */
	public BombUpgrade(Handler handler, float x, float y)
	{
		super(handler, x, y);
		texture = Textures.getBombUpgrade();
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
		p.addBomb();
		handler.getMap().getEntityManager().removeEntity(this);
	}


}
