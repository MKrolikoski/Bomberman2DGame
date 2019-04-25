package krolikowski.game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import krolikowski.game.Game;

/**
 * Klasa reprezentuj¹ca podstawowe elementy, z których zbudowana jest mapa gry
 *
 */

public class Tile
{
	private static Tile[] tiles = new Tile[256];
	private static Tile borderTile = new BorderTile(0);
	private static Tile grassTile = new GrassTile(1);
	private static Tile boxTile = new BoxTile(2);
	private static Tile staticEntityTile = new StaticEntityTile(3);
	
	/**
	 * Domyœlna szerokoœæ tile'a
	 */
	protected final static int WIDTH = 16 * Game.SCALE;
	/**
	 * Domyœlna wysokoœæ tile'a
	 */
	protected final static int HEIGHT = 16 * Game.SCALE;
	
	protected BufferedImage texture;
	protected final int id;

	/**
	 * Konstruuje nowy Tile, przypisuj¹c mu przekazan¹ teksturê i id
	 * @param texture tekstura BufferedImage
	 * @param id id
	 */
	public Tile(BufferedImage texture, int id)
	{
		this.texture = texture;
		this.id = id;

		tiles[id] = this;
	}

	/**
	 * Update zmiennych
	 */
	public void tick()
	{
	}

	/**
	 * renderuje tile na okreœlonej pozycji
	 * @param g obiekt Graphics
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 */
	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, WIDTH, HEIGHT, null);
	}

	/**
	 * Zwraca informacje o przenikalnoœci tile'a
	 * @return true jeœli tile jest przenikalny, false w przeciwnym wypadku
	 */
	public boolean isSolid()
	{
		return false;
	}
	
	/**
	 * Zwraca informacje o zniszczalnoœci tile'a
	 * @return true jeœli tile jest zniszczalny, false w przeciwnym wypadku
	 */
	public boolean isDestructible()
	{
		return false;
	}

	/**
	 * Zwraca szerokoœæ tile'a
	 * @return szerokoœæ
	 */
	public static int getWidth()
	{
		return WIDTH;
	}

	/**
	 * Zwraca wysokoœæ tile'a
	 * @return wysokoœæ
	 */
	public static int getHeight()
	{
		return HEIGHT;
	}
	
	/**
	 * Zwraca tablice tile'ów
	 * @return tablica Tile[]
	 */
	public static Tile[] getTiles()
	{
		return tiles.clone();
	}

	/**
	 * Zwraca id tile'a
	 * @return id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Zwraca nieprzenikalny i niezniszczalny tile tworz¹cy obramowanie mapy
	 * @return BorderTile
	 */
	public static Tile getBorderTile()
	{
		return borderTile;
	}

	/**
	 * Zwraca przenikalny i niezniszczalny tile tworz¹cy powierzchniê po której poruszaj¹ siê gracze
	 * @return GrassTile
	 */
	public static Tile getGrassTile()
	{
		return grassTile;
	}

	/**
	 * Zwraca nieprzenikalny i zniszczalny tile, który w momencie zniszczenia mo¿e zespawnowaæ ulepszenie gracza
	 * @return BoxTile
	 */
	public static Tile getBoxTile()
	{
		return boxTile;
	}

	/**
	 * Zwraca nieprzenikalny i niezniszczalny tile, który reprezentuje statyczne elementy (np. drzewa) w grze
	 * @return StaticEntityTile
	 */
	public static Tile getStaticEntityTile()
	{
		return staticEntityTile;
	}


}
