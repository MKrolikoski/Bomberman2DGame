package krolikowski.game.ui.objects.buttons;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Button odpowiedzialny za wyjœcie z gry
 *
 */

public class ExitButton extends UIButton
{

	/**
	 * Konstruuje nowy ExitButton, przypisuj¹c mu przekazany Handler, koordynaty x,y, szerokoœæ oraz wysokoœæ
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @param width szerokoœæ
	 * @param height wysokoœæ
	 */
	public ExitButton(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height, Textures.getExit());
	}

	@Override
	public void tick()
	{

	}


	/**
	 * Zakañcza grê
	 */
	@Override
	public void pressButton()
	{
		handler.getGame().endGame();
	}

}
