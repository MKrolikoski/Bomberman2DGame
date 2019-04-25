package krolikowski.game.states;

import java.awt.Graphics;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;
import krolikowski.game.sounds.SoundClip;
import krolikowski.game.ui.UIManager;
import krolikowski.game.ui.UIMenuManager;

/**
 * Klasa reprezentuj¹ca menu gry
 *
 */
public class MenuState extends State
{
	
	private UIManager uiManager;
	
	/**
	 * Konstruuje nowy MenuState, przypisuj¹c mu przekazany Handler
	 * @param handler Handler
	 */
	public MenuState(Handler handler)
	{
		super(handler);
		uiManager = new UIMenuManager(handler);
		handler.getGame().setMenuMusic(new SoundClip("/sounds/menu_music.wav"));
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
		if(handler.getGame().getGameMusic() != null && handler.getGame().getGameMusic().isPlaying())
		{
			handler.getGame().getGameMusic().resetSound();
			handler.getGame().getGameMusic().stopSound();
		}
		
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Textures.getMenuBackground(), 0, 0, handler.getWidth(), handler.getHeight(), null);
	   uiManager.render(g);
	}
	

}
