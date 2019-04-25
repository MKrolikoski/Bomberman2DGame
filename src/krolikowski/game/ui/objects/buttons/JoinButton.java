package krolikowski.game.ui.objects.buttons;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Button odpowiedzialny za do��czenie do serwera
 *
 */

public class JoinButton extends UIButton
{

	/**
	 * Konstruuje nowy JoinButton, przypisuj�c mu przekazany Handler, koordynaty x,y, szeroko�� oraz wysoko��
	 * @param handler Handler
	 * @param x po�o�enie x
	 * @param y po�o�enie y
	 * @param width szeroko��
	 * @param height wysoko��
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
	 * Do��cza do istniej�cego serwera
	 */
	@Override
	public void pressButton()
	{
		handler.getGame().joinGame();
	}

}
