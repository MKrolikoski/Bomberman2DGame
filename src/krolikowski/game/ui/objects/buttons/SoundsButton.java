package krolikowski.game.ui.objects.buttons;

import java.awt.Graphics;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Button odpowiedzialny aktywacj� lub dezaktywacj� d�wi�k�w w grze
 *
 */

public class SoundsButton extends UIButton
{

	/**
	 * Konstruuje nowy SoundsButton, przypisuj�c mu przekazany Handler, koordynaty x,y, szeroko��, wysoko�� oraz obiekt pobieraj�cy input
	 * @param handler Handler
	 * @param x po�o�enie x
	 * @param y po�o�enie y
	 * @param width szeroko��
	 * @param height wysoko��
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
	 * W��cza lub wy��cza d�wi�ki w grze
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
