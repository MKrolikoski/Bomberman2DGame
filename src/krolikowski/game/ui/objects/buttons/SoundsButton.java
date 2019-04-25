package krolikowski.game.ui.objects.buttons;

import java.awt.Graphics;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Button odpowiedzialny aktywacjê lub dezaktywacjê dŸwiêków w grze
 *
 */

public class SoundsButton extends UIButton
{

	/**
	 * Konstruuje nowy SoundsButton, przypisuj¹c mu przekazany Handler, koordynaty x,y, szerokoœæ, wysokoœæ oraz obiekt pobieraj¹cy input
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @param width szerokoœæ
	 * @param height wysokoœæ
	 */
	public SoundsButton(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height, Textures.getSounds());
	}

	@Override
	public void tick()
	{

	}
	
	@Override
	public void render(Graphics g)
	{
		if(!isHighlighted())
		{
			if(handler.getGame().areSoundsOn())
				g.drawImage(buttonImage[0], (int)x, (int)y, width, height, null);
			else
				g.drawImage(buttonImage[2], (int)x, (int)y, width, height, null);
		}
		else
		{
			if(handler.getGame().areSoundsOn())
				g.drawImage(buttonImage[1], (int)x, (int)y, width, height, null);
			else
				g.drawImage(buttonImage[3], (int)x, (int)y, width, height, null);
		}
	}

	/**
	 * W³¹cza lub wy³¹cza dŸwiêki w grze
	 */
	@Override
	public void pressButton()
	{
		if(handler.getGame().areSoundsOn())
			handler.getGame().setSoundsOff();
		else
			handler.getGame().setSoundsOn();
	}

}
