package krolikowski.game.ui.objects.buttons;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Button odpowiedzialny za powr�t do Menu
 *
 */
public class BackButton extends UIButton
{

	/**
	 * Konstruuje nowy BackButton, przypisuj�c mu przekazany Handler, koordynaty x,y, szeroko�� oraz wysoko��
	 * @param handler Handler
	 * @param x po�o�enie x
	 * @param y po�o�enie y
	 * @param width szeroko��
	 * @param height wysoko��
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
