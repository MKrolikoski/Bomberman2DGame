package krolikowski.game;


import krolikowski.game.input.InputHandler;
import krolikowski.game.levels.Map;
import krolikowski.game.ui.UIManager;

/**
 * Handler gry, dziêki któremu klasy mog¹ uzyskaæ dostêp do sk³adowych klasy Game i Map
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
	 * Zwraca obiekt obs³uguj¹cy input gracza
	 * @return obiekt pobieraj¹cy input
	 */
	public InputHandler getInput()
	{
		return game.getInput();
	}
	
	/**
	 * Zwraca szerokoœæ ekranu
	 * @return szerokoœæ ekranu
	 */
	public int getWidth()
	{
		return game.getWidth();
	}
	
	/**
	 * Zwraca wysokoœæ
	 * @return wysokoœæ ekranu
	 */
	public int getHeight()
	{
		return game.getHeight();
	}

	/**
	 * Zwraca mapê gry
	 * @return mapa gry
	 */
	public synchronized Map getMap()
	{
			return map;
	}

	/**
	 * Ustawia mapê gry
	 * @param map mapa gry
	 */
	public synchronized void setMap(Map map)
	{
		this.map = map;
	}
	
	/**
	 * Zwraca mened¿er interfejsu stanu Lobby
	 * @return mened¿er interfejsu
	 */
	public UIManager getLobbyManager()
	{
		return game.getLobbyManager();
	}
	
}
