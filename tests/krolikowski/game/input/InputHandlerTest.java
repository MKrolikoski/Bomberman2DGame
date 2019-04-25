package krolikowski.game.input;

import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;

import org.junit.Test;

import krolikowski.game.Game;
import krolikowski.game.Handler;

public class InputHandlerTest
{

	

	@Test
	public void testToggleKey()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		InputHandler inputHandler = new InputHandler(handler);
		inputHandler.toggleKey(KeyEvent.VK_ENTER, true);
		assertTrue(inputHandler.isEnter());
	}

	@Test
	public void testIsUp()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		InputHandler inputHandler = new InputHandler(handler);
		inputHandler.toggleKey(KeyEvent.VK_UP, true);
		assertTrue(inputHandler.isUp());
	}

	@Test
	public void testIsDown()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		InputHandler inputHandler = new InputHandler(handler);
		inputHandler.toggleKey(KeyEvent.VK_DOWN, true);
		assertTrue(inputHandler.isDown());
	}

	@Test
	public void testIsLeft()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		InputHandler inputHandler = new InputHandler(handler);
		inputHandler.toggleKey(KeyEvent.VK_LEFT, true);
		assertTrue(inputHandler.isLeft());
	}

	@Test
	public void testIsRight()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		InputHandler inputHandler = new InputHandler(handler);
		inputHandler.toggleKey(KeyEvent.VK_RIGHT, true);
		assertTrue(inputHandler.isRight());
	}

	@Test
	public void testIsSpace()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		InputHandler inputHandler = new InputHandler(handler);
		inputHandler.toggleKey(KeyEvent.VK_SPACE, true);
		assertTrue(inputHandler.isSpace());
	}

	@Test
	public void testIsEnter()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		InputHandler inputHandler = new InputHandler(handler);
		inputHandler.toggleKey(KeyEvent.VK_ENTER, true);
		assertTrue(inputHandler.isEnter());
	}

	@Test
	public void testIsBackspace()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		InputHandler inputHandler = new InputHandler(handler);
		inputHandler.toggleKey(KeyEvent.VK_BACK_SPACE, true);
		assertTrue(inputHandler.isBackspace());
	}

	@Test
	public void testIsEscape()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		InputHandler inputHandler = new InputHandler(handler);
		inputHandler.toggleKey(KeyEvent.VK_ESCAPE, true);
		assertTrue(inputHandler.isEscape());
	}

}
