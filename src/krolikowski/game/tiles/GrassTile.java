package krolikowski.game.tiles;

import krolikowski.game.gfx.Textures;

/**
 * Przenikalny i niezniszczalny Tile, reprezentuj�cy traw�
 *
 */
public class GrassTile extends Tile
{
	/**
	 * Konstruuje nowy GrassTile i przypisuje mu przekazane id
	 * @param id id
	 */
	public GrassTile(int id)
	{
		super(Textures.getGrass(), id);
	}
}
