package krolikowski.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import krolikowski.game.Handler;

/**
 * Klasa osb�uguj�ca pobierany input gracza
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
	 * Klasa wewn�trzna reprezentuj�ca klawisz na klawiaturze
	 *
	 */
	public static class Key
	{
		private boolean pressed = false;

		/**
		 * Zwraca informacj�, czy klawisz jest wci�ni�ty
		 * @return true je�li jest wci�ni�ty, false w przeciwnym wypadku
		 */
		public boolean isPressed()
		{
			return pressed;
		}

		/**
		 * Ustawia status wci�ni�cia klawisza
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
	 * Zwraca informacj�, czy strza�ka w g�r� jest wci�ni�ta, czy nie
	 * @return true je�li strza�ka do g�ry jest wci�ni�ta, false w przeciwnym wypadku
	 */
	public boolean isUp()
	{
		return up.isPressed();
	}
	/**
	 * Zwraca informacj�, czy strza�ka w d� jest wci�ni�ta, czy nie
	 * @return true je�li strza�ka w d� jest wci�ni�ta, false w przeciwnym wypadku
	 */
	public boolean isDown()
	{
		return down.isPressed();
	}
	/**
	 * Zwraca informacj�, czy strza�ka w lewo jest wci�ni�ta, czy nie
	 * @return true je�li strza�ka w lewo jest wci�ni�ta, false w przeciwnym wypadku
	 */
	public boolean isLeft()
	{
		return left.isPressed();
	}
	/**
	 * Zwraca informacj�, czy strza�ka w prawo jest wci�ni�ta, czy nie
	 * @return true je�li strza�ka w prawo jest wci�ni�ta, false w przeciwnym wypadku
	 */
	public boolean isRight()
	{
		return right.isPressed();
	}
	/**
	 * Zwraca informacj�, czy spacja jest wci�ni�ta, czy nie
	 * @return true je�li spacja jest wci�ni�ta, false w przeciwnym wypadku
	 */
	public boolean isSpace()
	{
		return space.isPressed();
	}
	/**
	 * Zwraca informacj�, czy enter jest wci�ni�ty, czy nie
	 * @return true je�li enter jest wci�ni�ty, false w przeciwnym wypadku
	 */
	public boolean isEnter()
	{
		return enter.isPressed();
	}

	/**
	 * Zwraca informacj�, czy backspace jest wci�ni�ty, czy nie
	 * @return true je�li backspace jest wci�ni�ty, false w przeciwnym wypadku
	 */
	public boolean isBackspace()
	{
		return backspace.isPressed();
	}

	/**
	 * Zwraca informacj�, czy escape jest wci�ni�ty, czy nie
	 * @return true je�li escape jest wci�ni�ty, false w przeciwnym wypadku
	 */
	public boolean isEscape()
	{
		return escape.isPressed();
	}
}
