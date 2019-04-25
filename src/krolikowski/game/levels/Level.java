package krolikowski.game.levels;

import java.awt.Graphics;

import krolikowski.game.tiles.Tile;

/**
 * interfejs �wiata gry
 *
 */

public interface Level
{
	/**
	 * update �wiata
	 */
	public void tick();
	/**
	 * renderuje �wiat
	 * @param g obiekt Graphics
	 */
	public void render(Graphics g);
	
	/**
	 * Zwraca tile na okre�lonej pozycji xy
	 * @param x po�o�enie x
	 * @param y po�o�enie y
	 * @return Tile
	 */
	public Tile getTile(int x, int y);

}
