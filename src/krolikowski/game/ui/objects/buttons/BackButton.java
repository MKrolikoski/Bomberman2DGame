package krolikowski.game.ui.objects.buttons;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Button odpowiedzialny za powrót do Menu
 *
 */
public class BackButton extends UIButton
{

	/**
	 * Konstruuje nowy BackButton, przypisuj¹c mu przekazany Handler, koordynaty x,y, szerokoœæ oraz wysokoœæ
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @param width szerokoœæ
	 * @param height wysokoœæ
	 */
	public BackButton(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height, Textures.getBack());
	}

	@Override
	public void tick()
	{

	}


	/**
	 * Przechodzi do menu
	 */
	@Override
	public void pressButton()
	{
		handler.getGame().goToMenu();
	}

}
