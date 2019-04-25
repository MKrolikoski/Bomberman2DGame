package krolikowski.game;


import krolikowski.game.input.InputHandler;
import krolikowski.game.levels.Map;
import krolikowski.game.ui.UIManager;

/**
 * Handler gry, dzi�ki kt�remu klasy mog� uzyska� dost�p do sk�adowych klasy Game i Map
 */

public class Handler
{
	private Game game;
	private Map map;
	
	/**
	 * Konstruuje nowy Handler i przypisuje do niego przekazany obiekt klasy Game
	 * @param game obiekt klasy Game
	 */
	public Handler(Game game)
	{
		this.game = game;
	}
	
	/**
	 * Zwraca obiekt klasy Game
	 * @return obiekt klasy Game
	 */
	public Game getGame() 
	{
		return game;
	}
	
	
	/**
	 * Zwraca obiekt obs�uguj�cy input gracza
	 * @return obiekt pobieraj�cy input
	 */
	public InputHandler getInput()
	{
		return game.getInput();
	}
	
	/**
	 * Zwraca szeroko�� ekranu
	 * @return szeroko�� ekranu
	 */
	public int getWidth()
	{
		return game.getWidth();
	}
	
	/**
	 * Zwraca wysoko��
	 * @return wysoko�� ekranu
	 */
	public int getHeight()
	{
		return game.getHeight();
	}

	/**
	 * Zwraca map� gry
	 * @return mapa gry
	 */
	public synchronized Map getMap()
	{
			return map;
	}

	/**
	 * Ustawia map� gry
	 * @param map mapa gry
	 */
	public synchronized void setMap(Map map)
	{
		this.map = map;
	}
	
	/**
	 * Zwraca mened�er interfejsu stanu Lobby
	 * @return mened�er interfejsu
	 */
	public UIManager getLobbyManager()
	{
		return game.getLobbyManager();
	}
	
}
