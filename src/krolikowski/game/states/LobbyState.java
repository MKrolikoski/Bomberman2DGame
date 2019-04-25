package krolikowski.game.states;

import java.awt.Graphics;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;
import krolikowski.game.net.packet.Packet06EnterLobby;
import krolikowski.game.ui.UILobbyManager;
import krolikowski.game.ui.UIManager;

/**
 * Klasa reprezentuj¹ca stan gry Lobby
 *
 */
public class LobbyState extends State
{

	private UIManager uiManager;
	
	/**
	 * Konstruuje nowy LobbyState, przypisuj¹c mu przekazany Handler
	 * @param handler Handler
	 */
	public LobbyState(Handler handler)
	{
		super(handler);
		uiManager = new UILobbyManager(handler);
		Packet06EnterLobby packet = new Packet06EnterLobby(handler.getGame().getPlayerId());
		packet.writeData(handler.getGame().getSocketClient());
	}

	@Override
	public void tick()
	{
		uiManager.tick();
		if (handler.getGame().isMusicOn() && !handler.getGame().getMenuMusic().isPlaying())
		{
			handler.getGame().getMenuMusic().loopSound();
		}
		if (!handler.getGame().isMusicOn() && handler.getGame().getMenuMusic().isPlaying())
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

	/**
	 * Zwraca mened¿er interfejsu Lobby
	 * @return mened¿er interfejsu
	 */
	public UIManager getLobbyManager()
	{
		return uiManager;
	}

}
