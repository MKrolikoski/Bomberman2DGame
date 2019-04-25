package krolikowski.game;

/**
 * Interfejs gry
 */

public interface GameInterface
{
	/**
	 * inicjalizacja zmiennych
	 */
	void init();

	/**
	 * update swiata i zmiennych
	 */
	void tick();

	/**
	 * wyswietla elementy na ekranie
	 */
	void render();
}
