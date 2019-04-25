package krolikowski.game.states;

import java.awt.Graphics;

import krolikowski.game.Handler;

/**
 * Klasa abstrakcyjna reprezentuj¹ca stan gry
 *
 */
public abstract class State
{
	private static State currentState = null;
	protected Handler handler;
	
	public State(Handler handler)
	{
		this.handler = handler;
	}
	/**
	 * Ustawia stan gry
	 * @param state stan gry
	 */
	public static void setState(State state)
	{
		currentState = state;
	}
	/**
	 * Zwraca obecny stan gry
	 * @return stan gry
	 */
	public static State getState()
	{
		return currentState;
	}
	/**
	 * update stanu gry
	 */
	public abstract void tick();
	/**
	 * render elementów wchodz¹cych w sk³ad stanu gry
	 * @param g obiekt Graphics
	 */
	public abstract void render(Graphics g);
}
