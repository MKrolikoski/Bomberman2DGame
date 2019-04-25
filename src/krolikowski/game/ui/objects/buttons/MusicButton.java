package krolikowski.game.ui.objects.buttons;

import java.awt.Graphics;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Button odpowiedzialny aktywacj� lub dezaktywacj� muzyki w grze
 *
 */

public class MusicButton extends UIButton
{

	/**
	 * Konstruuje nowy MusicButton, przypisuj�c mu przekazany Handler, koordynaty x,y, szeroko�� oraz wysoko��
	 * @param handler Handler
	 * @param x po�o�enie x
	 * @param y po�o�enie y
	 * @param width szeroko��
	 * @param height wysoko��
	 */
	public MusicButton(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height, Textures.getMusic());
	}

	@Override
	public void tick()
	{

	}
	
	public void render(Graphics g)
	{
		if(!isHighlighted())
		{
			if(handler.getGame().isMusicOn())
				g.drawImage(buttonImage[0], (int)x, (int)y, width, height, null);
			else
				g.drawImage(buttonImage[2], (int)x, (int)y, width, height, null);
		}
		else
		{
			if(handler.getGame().isMusicOn())
				g.drawImage(buttonImage[1], (int)x, (int)y, width, height, null);
			else
				g.drawImage(buttonImage[3], (int)x, (int)y, width, height, null);
		}
	}

	/**
	 * W��cza lub wy��cza muzyk� w grze
	 */
	@Override
	public void pressButton()
	{
		if(handler.getGame().isMusicOn())
			handler.getGame().setMusicOff();
		else
			handler.getGame().setMusicOn();
	}

}
