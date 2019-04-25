package krolikowski.game.ui.objects.buttons;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Button odpowiedzialny za przejœcie do ustawieñ gry
 *
 */

public class SettingsButton extends UIButton
{

	/**
	 * Konstruuje nowy SettingsButton, przypisuj¹c mu przekazany Handler, koordynaty x,y, szerokoœæ oraz wysokoœæ
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @param width szerokoœæ
	 * @param height wysokoœæ
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
	 * Przechodzi do ustawieñ gry
	 */
	@Override
	public void pressButton()
	{
		handler.getGame().goToSettings();
	}

}
