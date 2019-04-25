package krolikowski.game.states;

import java.awt.Graphics;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;
import krolikowski.game.ui.UIManager;
import krolikowski.game.ui.UISettingsManager;

/**
 * Klasa reprezentuj¹ca ustawienia gry
 *
 */
public class SettingsState extends State
{
	private UIManager uiManager;
	
	/**
	 * Konstruuje nowy SettingsState, przypisuj¹c mu przekazany Handler
	 * @param handler Handler
	 */
	public SettingsState(Handler handler)
	{
		super(handler);
		uiManager = new UISettingsManager(handler);
	}
	
	@Override
	public void tick()
	{
		uiManager.tick();
		if(handler.getGame().isMusicOn() && !handler.getGame().getMenuMusic().isPlaying())
		{
			handler.getGame().getMenuMusic().loopSound();
		}
		if(!handler.getGame().isMusicOn() && handler.getGame().getMenuMusic().isPlaying())
		{
			handler.getGame().getMenuMusic().stopSound();
		}
	}

	@Override
	public void render(Graphics g)
	{
	   g.drawImage(Textures.getMenuBackground(), 0, 0, handler.getWidth(), handler.getHeight(), null);
	   uiManager.render(g);
	}

}
