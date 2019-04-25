package krolikowski.game.ui.objects.buttons;

import static org.junit.Assert.*;

import org.junit.Test;

import krolikowski.game.Game;
import krolikowski.game.Handler;

public class SettingsButtonTest
{

	@Test
	public void testPressButton()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		game.goToMenu();
		SettingsButton b = new SettingsButton(handler, 0, 0, 0, 0);
		b.pressButton();
		int result = -3;
		assertEquals(result, game.getGameStatus());
	}

}
