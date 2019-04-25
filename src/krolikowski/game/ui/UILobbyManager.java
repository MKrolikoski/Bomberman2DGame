package krolikowski.game.ui;

import krolikowski.game.Handler;
import krolikowski.game.ui.objects.PlayerStatus;
import krolikowski.game.ui.objects.buttons.ReadyButton;

/**
 * UI dla Lobby
 *
 */
public class UILobbyManager extends UIManager
{

	/**
	 * Konstruuje nowy UILobbyManager, przypisuj¹c mu przekazany Handler
	 * @param handler Handler
	 */
	public UILobbyManager(Handler handler)
	{
		super(handler);
	}

	protected void addObjects()
	{
		addObject(new ReadyButton(handler, handler.getWidth()-100*handler.getGame().getScale(), handler.getHeight()-35*handler.getGame().getScale(), 48, 12, handler.getInput()));
		addObject(new PlayerStatus(handler, handler.getGame().getPlayerId()));
	}
}
