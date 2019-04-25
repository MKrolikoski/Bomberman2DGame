package krolikowski.game.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import krolikowski.game.Game;
import krolikowski.game.Handler;
import krolikowski.game.ui.objects.UIObject;
import krolikowski.game.ui.objects.buttons.StartButton;

public class UIMenuManagerTest
{

	@Test
	public void testAddObject()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		UIMenuManager uiMenuManager = new UIMenuManager(handler);
		int uiMenuObjectsSizeBefore = uiMenuManager.getUIObjects().size();

		int uiMenuObjectsSizeResult = uiMenuObjectsSizeBefore;
		for (int i = 0; i < 5; ++i)
		{
			uiMenuManager.addObject(new StartButton(handler, 0, 0, 0, 0));
			uiMenuObjectsSizeResult++;
		}
		int uiMenuObjectsSizeAfter = uiMenuManager.getUIObjects().size();
		assertEquals(uiMenuObjectsSizeResult, uiMenuObjectsSizeAfter);
	}

	@Test
	public void testRemoveObjectUIObject()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		UIMenuManager uiMenuManager = new UIMenuManager(handler);
		UIObject object = new StartButton(handler, 0, 0, 0, 0);
		int size = uiMenuManager.getUIObjects().size();
		uiMenuManager.addObject(object);
		uiMenuManager.removeObject(object);
		assertEquals(size, uiMenuManager.getUIObjects().size());
	}

	@Test
	public void testRemoveObjectInt()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		UIMenuManager uiMenuManager = new UIMenuManager(handler);
		uiMenuManager.addObject(new StartButton(handler, 0, 0, 0, 0));
		for (int i = uiMenuManager.getUIObjects().size()-1; i >= 0; --i)
		{
			uiMenuManager.removeObject(i);
		}
		assertEquals(0, uiMenuManager.getUIObjects().size());

	}

}
