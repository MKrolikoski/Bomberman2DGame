package krolikowski.game.ui.objects.buttons;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import krolikowski.game.Game;
import krolikowski.game.Handler;

public class BackButtonTest
{

	@Test
	public void testPressButton()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		game.goToSettings();
		BackButton b = new BackButton(handler, 0, 0, 0, 0);
		b.pressButton();
		int result = -2;
		assertEquals(result, game.getGameStatus());
	}

}
