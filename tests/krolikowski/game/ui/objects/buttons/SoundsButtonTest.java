package krolikowski.game.ui.objects.buttons;

import static org.junit.Assert.*;

import org.junit.Test;

import krolikowski.game.Game;
import krolikowski.game.Handler;

public class SoundsButtonTest
{

	@Test
	public void testPressButton()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		game.goToSettings();
		SoundsButton b = new SoundsButton(handler, 0, 0, 0, 0);
		b.pressButton();
		assertFalse(game.areSoundsOn());
	}

}
