package krolikowski.game.ui;

import krolikowski.game.Handler;
import krolikowski.game.ui.objects.Pointer;
import krolikowski.game.ui.objects.buttons.ExitButton;
import krolikowski.game.ui.objects.buttons.JoinButton;
import krolikowski.game.ui.objects.buttons.SettingsButton;
import krolikowski.game.ui.objects.buttons.StartButton;

/**
 * UI dla Menu
 */
public class UIMenuManager extends UIManager
{
	/**
	 * Konstruuje nowy UIMenuManager, przypisuj¹c mu przekazany Handler
	 * @param handler Handler
	 */
	public UIMenuManager(Handler handler)
	{
		super(handler);
	}

	protected void addObjects()
	{
		addObject(new StartButton(handler, handler.getWidth()/2-25*handler.getGame().getScale(), handler.getHeight()-85*handler.getGame().getScale(), 48, 12));
		addObject(new JoinButton(handler, handler.getWidth()/2-25*handler.getGame().getScale(), handler.getHeight()-68*handler.getGame().getScale(), 45, 12));
		addObject(new SettingsButton(handler, handler.getWidth()/2-32*handler.getGame().getScale(), handler.getHeight()-50*handler.getGame().getScale(), 60, 12));
		addObject(new ExitButton(handler, handler.getWidth()/2-21*handler.getGame().getScale(), handler.getHeight()-30*handler.getGame().getScale(), 40, 12));
		addObject(new Pointer(handler, handler.getWidth()/2-48*handler.getGame().getScale(), getUIObjects(), handler.getInput()));
	}
}
