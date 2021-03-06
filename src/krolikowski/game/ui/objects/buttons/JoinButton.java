package krolikowski.game.ui.objects.buttons;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Button odpowiedzialny za dołączenie do serwera
 *
 */

public class JoinButton extends UIButton
{

	/**
	 * Konstruuje nowy JoinButton, przypisując mu przekazany Handler, koordynaty x,y, szerokość oraz wysokość
	 * @param handler Handler
	 * @param x położenie x
	 * @param y położenie y
	 * @param width szerokość
	 * @param height wysokość
	 */
	public JoinButton(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height, Textures.getJoin());
	}

	@Override
	public void tick()
	{

	}

	/**
	 * Dołącza do istniejącego serwera
	 */
	@Override
	public void pressButton()
	{
		handler.getGame().joinGame();
	}

}
