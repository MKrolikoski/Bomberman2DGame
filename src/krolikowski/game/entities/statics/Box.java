package krolikowski.game.entities.statics;

import java.awt.Graphics;
import java.util.Random;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;
import krolikowski.game.net.packet.Packet04Upgrade;
import krolikowski.game.tiles.Tile;

/**
 * klasa reprezentuj¹ca zniszczaln¹ skrzyniê
 *
 */
public class Box extends StaticEntity
{
	private boolean destroyed;

	/**
	 * Konstruuje nowy Box, przypisuj¹c mu przekazany Handler oraz koordynaty x,y
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 */
	public Box(Handler handler, float x, float y)
	{
		super(handler, x, y, Tile.getWidth() / handler.getGame().getScale(), Tile.getHeight() / handler.getGame().getScale());
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = width;
		bounds.height = height;

		destroyed = false;
	}

	@Override
	public void tick()
	{
		if (destroyed)
		{
			spawnUpgrade();
			handler.getMap().getEntityManager().removeEntity(this);
		}

	}

	private void spawnUpgrade()
	{
		if (handler.getGame().getPlayerId() == 1) //if player is HOST
		{
			int upgrade = new Random().nextInt(10);
			Packet04Upgrade packet;
			switch (upgrade)
			{
			case 1:
				packet = new Packet04Upgrade(x/handler.getGame().getScale(), y/handler.getGame().getScale(), 1);
				packet.writeData(handler.getGame().getSocketClient());
				break;
			case 2:
				packet = new Packet04Upgrade(x/handler.getGame().getScale(), y/handler.getGame().getScale(), 2);
				packet.writeData(handler.getGame().getSocketClient());
				break;
			case 3:
				packet = new Packet04Upgrade(x/handler.getGame().getScale(), y/handler.getGame().getScale(), 3);
				packet.writeData(handler.getGame().getSocketClient());
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Textures.getBox(), (int) x, (int) y, width, height, null);
	}

	@Override
	public boolean checkEntityCollision(float xOffset, float yOffset)
	{
		return false;
	}

	@Override
	public boolean collisionWithTile(int x, int y)
	{
		return false;
	}

	/**
	 * niszczy skrzyniê
	 */
	public void destroy()
	{
		if (!destroyed)
		{
			destroyed = true;
		}
	}
}
