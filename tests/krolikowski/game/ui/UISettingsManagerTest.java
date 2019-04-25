package krolikowski.game.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import krolikowski.game.Game;
import krolikowski.game.Handler;
import krolikowski.game.ui.objects.UIObject;
import krolikowski.game.ui.objects.buttons.SettingsButton;

public class UISettingsManagerTest
{

	@Test
	public void testAddObject()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		UISettingsManager uiSettingsManager = new UISettingsManager(handler);
		int uiSettingsObjectsSizeBefore = uiSettingsManager.getUIObjects().size();

		int uiSettingsObjectsSizeResult = uiSettingsObjectsSizeBefore;
		for (int i = 0; i < 5; ++i)
		{
			uiSettingsManager.addObject(new SettingsButton(handler, 0, 0, 0, 0));
			uiSettingsObjectsSizeResult++;
		}
		int uiSettingsObjectsSizeAfter = uiSettingsManager.getUIObjects().size();
		assertEquals(uiSettingsObjectsSizeResult, uiSettingsObjectsSizeAfter);
	}

	@Test
	public void testRemoveObjectUIObject()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		UISettingsManager uiSettingsManager = new UISettingsManager(handler);
		UIObject object = new SettingsButton(handler, 0, 0, 0, 0);
		int size = uiSettingsManager.getUIObjects().size();
		uiSettingsManager.addObject(object);
		uiSettingsManager.removeObject(object);
		assertEquals(size, uiSettingsManager.getUIObjects().size());
	}

	@Test
	public void testRemoveObjectInt()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		UISettingsManager uiSettingsManager = new UISettingsManager(handler);
		uiSettingsManager.addObject(new SettingsButton(handler, 0, 0, 0, 0));
		for (int i = uiSettingsManager.getUIObjects().size()-1; i >= 0; --i)
		{
			uiSettingsManager.removeObject(i);
		}
		assertEquals(0, uiSettingsManager.getUIObjects().size());

	}

}
