package krolikowski.game.tiles;
import krolikowski.game.gfx.Textures;

/**
 * Nieprzenikalny i zniszczalny Tile, reprezentuj¹cy skrzyniê
 *
 */
public class BoxTile extends Tile
{
	/**
	 * Konstruuje nowy BoxTile i przypisuje mu przekazane id
	 * @param id id
	 */
	public BoxTile(int id)
	{
		super(Textures.getGrass(), id);
	}
	
	@Override
	public boolean isSolid()
	{
		return true;
	}
	@Override
	public boolean isDestructible()
	{
		return true;
	}
}