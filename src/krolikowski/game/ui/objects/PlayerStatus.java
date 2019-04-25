package krolikowski.game.ui.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Obiekt przedstawiaj¹cy portret gracza o danym ID i wyœwietlaj¹cy informacje o jego statusie gotowoœci
 *
 */
public class PlayerStatus extends UIObject
{

	private BufferedImage[] image;
	private int playerId;

	/**
	 * Konstruuje nowy PlayerStatus, przypisuj¹c mu przekazany Handler i id gracza
	 * @param handler Handler
	 * @param playerId id
	 */
	public PlayerStatus(Handler handler, int playerId)
	{
		super(handler, 0, handler.getHeight()-100*handler.getGame().getScale(), 64, 64);

		this.playerId = playerId;
		init();
	}

	private void init()
	{
		switch (playerId)
		{
		default:
		case 1:
			image = Textures.getPlayer1ready();
			x = handler.getWidth()/2-100*handler.getGame().getScale();
			break;
		case 2:
			image = Textures.getPlayer2ready();
			x = handler.getWidth()/2-50*handler.getGame().getScale();
			break;
		case 3:
			image = Textures.getPlayer3ready();
			x = handler.getWidth()/(float)2;
			break;
		case 4:
			image = Textures.getPlayer4ready();
			x = handler.getWidth()/2+50*handler.getGame().getScale();
			break;
		}

	}

	@Override
	public void tick()
	{
	}

	@Override
	public void render(Graphics g)
	{
		if (!handler.getMap().getEntityManager().getPlayerReadyStatus(playerId))
		{
			g.drawImage(image[0], (int) x, (int) y, width, height, null);
		} else
		{
			g.drawImage(image[1], (int) x, (int) y, width, height, null);
		}
	}
	
	/**
	 * Zwraca id gracza
	 * @return Id
	 */
	public int getPlayerId()
	{
		return playerId;
	}
}
