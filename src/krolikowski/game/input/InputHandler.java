package krolikowski.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import krolikowski.game.Handler;

/**
 * Klasa osb³uguj¹ca pobierany input gracza
 *
 */

public class InputHandler implements KeyListener
{

	/**
	 * Konstruuje InputHandler i dodaje go do frame'a uzyskanego poprzez przekazany Handler
	 * @param handler Handler
	 */
	public InputHandler(Handler handler)
	{
		handler.getGame().getDisplay().getFrame().addKeyListener(this);
	}

	/**
	 * Klasa wewnêtrzna reprezentuj¹ca klawisz na klawiaturze
	 *
	 */
	public static class Key
	{
		private boolean pressed = false;

		/**
		 * Zwraca informacjê, czy klawisz jest wciœniêty
		 * @return true jeœli jest wciœniêty, false w przeciwnym wypadku
		 */
		public boolean isPressed()
		{
			return pressed;
		}

		/**
		 * Ustawia status wciœniêcia klawisza
		 * @param isPressed status klawisza
		 */
		public void toggle(boolean isPressed)
		{
			pressed = isPressed;
		}
	}

	private Key up = new Key();
	private Key down = new Key();
	private Key left = new Key();
	private Key right = new Key();
	private Key space = new Key();
	private Key enter = new Key();
	private Key backspace = new Key();
	private Key escape = new Key();

	/**
	 * wciska klawisz
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		toggleKey(e.getKeyCode(), true);
	}

	/**
	 * uwalnia klawisz
	 */
	@Override
	public void keyReleased(KeyEvent e)
	{
		toggleKey(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}


	/**
	 * ustawia status klawisza
	 * @param keyCode kod klawisza
	 * @param isPressed status klawisza
	 */
	public void toggleKey(int keyCode, boolean isPressed)
	{
		if (keyCode == KeyEvent.VK_UP)
		{
			up.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_DOWN)
		{
			down.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_LEFT)
		{
			left.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_RIGHT)
		{
			right.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_SPACE)
		{
			space.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_ENTER)
		{
			enter.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_BACK_SPACE)
		{
			backspace.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_ESCAPE)
		{
			escape.toggle(isPressed);
		}
	}
	
	/**
	 * Zwraca informacjê, czy strza³ka w górê jest wciœniêta, czy nie
	 * @return true jeœli strza³ka do góry jest wciœniêta, false w przeciwnym wypadku
	 */
	public boolean isUp()
	{
		return up.isPressed();
	}
	/**
	 * Zwraca informacjê, czy strza³ka w dó³ jest wciœniêta, czy nie
	 * @return true jeœli strza³ka w dó³ jest wciœniêta, false w przeciwnym wypadku
	 */
	public boolean isDown()
	{
		return down.isPressed();
	}
	/**
	 * Zwraca informacjê, czy strza³ka w lewo jest wciœniêta, czy nie
	 * @return true jeœli strza³ka w lewo jest wciœniêta, false w przeciwnym wypadku
	 */
	public boolean isLeft()
	{
		return left.isPressed();
	}
	/**
	 * Zwraca informacjê, czy strza³ka w prawo jest wciœniêta, czy nie
	 * @return true jeœli strza³ka w prawo jest wciœniêta, false w przeciwnym wypadku
	 */
	public boolean isRight()
	{
		return right.isPressed();
	}
	/**
	 * Zwraca informacjê, czy spacja jest wciœniêta, czy nie
	 * @return true jeœli spacja jest wciœniêta, false w przeciwnym wypadku
	 */
	public boolean isSpace()
	{
		return space.isPressed();
	}
	/**
	 * Zwraca informacjê, czy enter jest wciœniêty, czy nie
	 * @return true jeœli enter jest wciœniêty, false w przeciwnym wypadku
	 */
	public boolean isEnter()
	{
		return enter.isPressed();
	}

	/**
	 * Zwraca informacjê, czy backspace jest wciœniêty, czy nie
	 * @return true jeœli backspace jest wciœniêty, false w przeciwnym wypadku
	 */
	public boolean isBackspace()
	{
		return backspace.isPressed();
	}

	/**
	 * Zwraca informacjê, czy escape jest wciœniêty, czy nie
	 * @return true jeœli escape jest wciœniêty, false w przeciwnym wypadku
	 */
	public boolean isEscape()
	{
		return escape.isPressed();
	}
}
