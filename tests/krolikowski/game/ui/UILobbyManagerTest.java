package krolikowski.game.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import krolikowski.game.Game;
import krolikowski.game.Handler;
import krolikowski.game.ui.objects.UIObject;
import krolikowski.game.ui.objects.buttons.ReadyButton;

public class UILobbyManagerTest
{

	@Test
	public void testAddObject()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		UILobbyManager uiLobbyManager = new UILobbyManager(handler);
		int uiLobbyObjectsSizeBefore = uiLobbyManager.getUIObjects().size();

		int uiLobbyObjectsSizeResult = uiLobbyObjectsSizeBefore;
		for (int i = 0; i < 5; ++i)
		{
			uiLobbyManager.addObject(new ReadyButton(handler, 0, 0, 0, 0, null));
			uiLobbyObjectsSizeResult++;
		}
		int uiLobbyObjectsSizeAfter = uiLobbyManager.getUIObjects().size();
		assertEquals(uiLobbyObjectsSizeResult, uiLobbyObjectsSizeAfter);
	}

	@Test
	public void testRemoveObjectUIObject()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		UILobbyManager uiLobbyManager = new UILobbyManager(handler);
		UIObject object = new ReadyButton(handler, 0, 0, 0, 0, null);
		int size = uiLobbyManager.getUIObjects().size();
		uiLobbyManager.addObject(object);
		uiLobbyManager.removeObject(object);
		assertEquals(size, uiLobbyManager.getUIObjects().size());
	}

	@Test
	public void testRemoveObjectInt()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		UILobbyManager uiLobbyManager = new UILobbyManager(handler);
		uiLobbyManager.addObject(new ReadyButton(handler, 0, 0, 0, 0, null));
		for (int i = uiLobbyManager.getUIObjects().size()-1; i >= 0; --i)
		{
			uiLobbyManager.removeObject(i);
		}
		assertEquals(0, uiLobbyManager.getUIObjects().size());

	}

}
