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
	 * Konstruuje nowy StartButton, przypisuj¹c mu przekazany Handler, koordynaty x,y, szerokoœæ, wysokoœæ oraz obiekt pobieraj¹cy input
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @param width szerokoœæ
	 * @param height wysokoœæ
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
