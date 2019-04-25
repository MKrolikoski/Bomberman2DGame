package krolikowski.game.levels;

import java.awt.Graphics;

import krolikowski.game.Handler;
import krolikowski.game.entities.EntityManager;
import krolikowski.game.entities.statics.Box;
import krolikowski.game.entities.statics.Tree;
import krolikowski.game.tiles.Tile;
import krolikowski.game.utils.Utils;

/**
 * Klasa odpowiedzialna utworzenie mapy i jej update
 *
 */
public class Map implements Level
{
	private Handler handler;
	private int width, height;
	private int[][] tiles;

	private EntityManager entityManager;

	/**
	 * Konstruuje nowy obiekt Map, przypisuj¹c mu przekazany Handler i String determinuj¹cy wygl¹d tworzonej mapy
	 * @param handler Handler
	 * @param fileInput String z informacjami o mapie
	 */
	public Map(Handler handler, String fileInput)
	{
		this.handler = handler;
		entityManager = new EntityManager();
		loadMap(fileInput);
		init();
	}
	
	private void init()
	{
		entityManager.addEntity(handler.getGame().getPlayer());	
	}

	private void loadMap(String fileInput)
	{
		//String fileInput = Utils.loadFileAsString(path);
		String[] tokens = fileInput.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		tiles = new int[width][height];

		for (int y = 0; y < height; ++y)
			for (int x = 0; x < width; ++x)
			{
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
				if(tiles[x][y] == 2)	//BOX TILE
				{
					entityManager.addEntity(new Box(handler, Tile.getWidth()*x, Tile.getHeight()*y));
				}
				if(tiles[x][y] == 3) //tree
				{
					entityManager.addEntity(new Tree(handler, Tile.getWidth()*x, Tile.getHeight()*(y-1)+Tile.getHeight()/5));
				}
			}
	}

	/**
	 * Update mapy
	 */
	@Override
	public void tick()
	{
		entityManager.tick();
	}

	/**
	 * Renderuje mapê
	 */
	@Override
	public void render(Graphics g)
	{
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				getTile(x, y).render(g, x * Tile.getWidth(), y * Tile.getHeight());
			}
		}
		entityManager.render(g);
	}

	@Override
	public Tile getTile(int x, int y)
	{
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.getBorderTile();

		Tile t = Tile.getTiles()[tiles[x][y]];
		if (t == null)
			return Tile.getBorderTile();
		return t;
	}

	/**
	 * Zwraca mened¿er bytów
	 * @return mened¿er bytów
	 */
	public EntityManager getEntityManager()
	{
		return entityManager;
	}
	
	/**
	 * Zmienia typ tile'a na podanej pozycji na tile o okreœlonym Id
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @param id Id nowego tile'a
	 */
	public void changeTileType(int x, int y, int id)
	{
		tiles[x][y] = id;
	}
}
