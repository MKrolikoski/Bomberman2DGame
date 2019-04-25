package krolikowski.game.ui;

import krolikowski.game.Handler;
import krolikowski.game.ui.objects.Pointer;
import krolikowski.game.ui.objects.buttons.BackButton;
import krolikowski.game.ui.objects.buttons.MusicButton;
import krolikowski.game.ui.objects.buttons.SoundsButton;

/**
 * UI dla Settings
 */
public class UISettingsManager extends UIManager
{
	/**
	 * Konstruuje nowy UISettingsManager, przypisuj¹c mu przekazany Handler
	 * @param handler Handler
	 */
	public UISettingsManager(Handler handler)
	{
		super(handler);
	}

	protected void addObjects()
	{
		addObject(new SoundsButton(handler, handler.getWidth()/2-24*handler.getGame().getScale(), handler.getHeight()-85*handler.getGame().getScale(), 55, 12));
		addObject(new MusicButton(handler, handler.getWidth()/2-23*handler.getGame().getScale(), handler.getHeight()-57*handler.getGame().getScale(), 55, 12));
		addObject(new BackButton(handler, handler.getWidth()/2-21*handler.getGame().getScale(), handler.getHeight()-30*handler.getGame().getScale(), 50, 10));
		addObject(new Pointer(handler, handler.getWidth()/2-48*handler.getGame().getScale(), getUIObjects(), handler.getInput()));
	}
}
