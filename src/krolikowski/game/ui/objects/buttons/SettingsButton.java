package krolikowski.game.ui.objects.buttons;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Button odpowiedzialny za przej�cie do ustawie� gry
 *
 */

public class SettingsButton extends UIButton
{

	/**
	 * Konstruuje nowy SettingsButton, przypisuj�c mu przekazany Handler, koordynaty x,y, szeroko�� oraz wysoko��
	 * @param handler Handler
	 * @param x po�o�enie x
	 * @param y po�o�enie y
	 * @param width szeroko��
	 * @param height wysoko��
	 */
	public SettingsButton(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height, Textures.getSettings());
	}

	@Override
	public void tick()
	{

	}

	/**
	 * Przechodzi do ustawie� gry
	 */
	@Override
	public void pressButton()
	{
		handler.getGame().goToSettings();
	}

}
