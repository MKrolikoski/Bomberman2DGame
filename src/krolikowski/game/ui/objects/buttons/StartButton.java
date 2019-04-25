package krolikowski.game.ui.objects.buttons;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Button odpowiedzialny za utworzenie serwera
 *
 */

public class StartButton extends UIButton
{

	/**
	 * Konstruuje nowy StartButton, przypisuj�c mu przekazany Handler, koordynaty x,y, szeroko��, wysoko�� oraz obiekt pobieraj�cy input
	 * @param handler Handler
	 * @param x po�o�enie x
	 * @param y po�o�enie y
	 * @param width szeroko��
	 * @param height wysoko��
	 */
	public StartButton(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height, Textures.getStart());
	}

	@Override
	public void tick()
	{

	}

	/**
	 * Tworzy serwer
	 */
	@Override
	public void pressButton()
	{
		handler.getGame().createServer();
	}

}
