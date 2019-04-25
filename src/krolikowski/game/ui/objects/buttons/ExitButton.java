package krolikowski.game.ui.objects.buttons;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Button odpowiedzialny za wyj�cie z gry
 *
 */

public class ExitButton extends UIButton
{

	/**
	 * Konstruuje nowy ExitButton, przypisuj�c mu przekazany Handler, koordynaty x,y, szeroko�� oraz wysoko��
	 * @param handler Handler
	 * @param x po�o�enie x
	 * @param y po�o�enie y
	 * @param width szeroko��
	 * @param height wysoko��
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
	 * Zaka�cza gr�
	 */
	@Override
	public void pressButton()
	{
		handler.getGame().endGame();
	}

}
