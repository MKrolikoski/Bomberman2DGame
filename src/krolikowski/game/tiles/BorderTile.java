package krolikowski.game.tiles;

import krolikowski.game.gfx.Textures;

/**
 * Nieprzenikalny i niezniszczalny Tile, tworz¹cy obramowanie mapy
 *
 */
public class BorderTile extends Tile
{
	/**
	 * Konstruuje nowy BorderTile i przypisuje mu przekazane id
	 * @param id id
	 */
	public BorderTile(int id)
	{
		super(Textures.getBorder(), id);
	}
	
	@Override
	public boolean isSolid()
	{
		return true;
	}
}
