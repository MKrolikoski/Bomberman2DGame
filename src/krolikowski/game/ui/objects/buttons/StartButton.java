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
	 * Konstruuje nowy StartButton, przypisując mu przekazany Handler, koordynaty x,y, szerokość, wysokość oraz obiekt pobierający input
	 * @param handler Handler
	 * @param x położenie x
	 * @param y położenie y
	 * @param width szerokość
	 * @param height wysokość
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
