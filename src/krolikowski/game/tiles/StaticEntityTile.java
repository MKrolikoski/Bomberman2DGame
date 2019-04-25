package krolikowski.game.tiles;

import krolikowski.game.gfx.Textures;

/**
 * Nieprzenikalny i niezniszczalny Tile, reprezentuj¹cy statyczny byt
 *
 */
public class StaticEntityTile extends Tile
{
	/**
	 * Konstruuje nowy StaticEntityTile i przypisuje mu przekazane id
	 * @param id id
	 */
	public StaticEntityTile(int id)
	{
		super(Textures.getGrass(), id);
	}
	
	@Override
	public boolean isSolid()
	{
		return true;
	}
}
