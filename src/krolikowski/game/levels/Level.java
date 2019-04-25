package krolikowski.game.levels;

import java.awt.Graphics;

import krolikowski.game.tiles.Tile;

/**
 * interfejs œwiata gry
 *
 */

public interface Level
{
	/**
	 * update œwiata
	 */
	public void tick();
	/**
	 * renderuje œwiat
	 * @param g obiekt Graphics
	 */
	public void render(Graphics g);
	
	/**
	 * Zwraca tile na okreœlonej pozycji xy
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @return Tile
	 */
	public Tile getTile(int x, int y);

}
