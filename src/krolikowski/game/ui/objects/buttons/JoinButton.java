package krolikowski.game.ui.objects.buttons;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Button odpowiedzialny za do³¹czenie do serwera
 *
 */

public class JoinButton extends UIButton
{

	/**
	 * Konstruuje nowy JoinButton, przypisuj¹c mu przekazany Handler, koordynaty x,y, szerokoœæ oraz wysokoœæ
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @param width szerokoœæ
	 * @param height wysokoœæ
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
	 * Do³¹cza do istniej¹cego serwera
	 */
	@Override
	public void pressButton()
	{
		handler.getGame().joinGame();
	}

}
