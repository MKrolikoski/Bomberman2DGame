package krolikowski.game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import krolikowski.game.Game;

/**
 * Klasa reprezentuj�ca podstawowe elementy, z kt�rych zbudowana jest mapa gry
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
	 * Domy�lna szeroko�� tile'a
	 */
	protected final static int WIDTH = 16 * Game.SCALE;
	/**
	 * Domy�lna wysoko�� tile'a
	 */
	protected final static int HEIGHT = 16 * Game.SCALE;
	
	protected BufferedImage texture;
	protected final int id;

	/**
	 * Konstruuje nowy Tile, przypisuj�c mu przekazan� tekstur� i id
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
	 * renderuje tile na okre�lonej pozycji
	 * @param g obiekt Graphics
	 * @param x po�o�enie x
	 * @param y po�o�enie y
	 */
	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, WIDTH, HEIGHT, null);
	}

	/**
	 * Zwraca informacje o przenikalno�ci tile'a
	 * @return true je�li tile jest przenikalny, false w przeciwnym wypadku
	 */
	public boolean isSolid()
	{
		return false;
	}
	
	/**
	 * Zwraca informacje o zniszczalno�ci tile'a
	 * @return true je�li tile jest zniszczalny, false w przeciwnym wypadku
	 */
	public boolean isDestructible()
	{
		return false;
	}

	/**
	 * Zwraca szeroko�� tile'a
	 * @return szeroko��
	 */
	public static int getWidth()
	{
		return WIDTH;
	}

	/**
	 * Zwraca wysoko�� tile'a
	 * @return wysoko��
	 */
	public static int getHeight()
	{
		return HEIGHT;
	}
	
	/**
	 * Zwraca tablice tile'�w
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
	 * Zwraca nieprzenikalny i niezniszczalny tile tworz�cy obramowanie mapy
	 * @return BorderTile
	 */
	public static Tile getBorderTile()
	{
		return borderTile;
	}

	/**
	 * Zwraca przenikalny i niezniszczalny tile tworz�cy powierzchni� po kt�rej poruszaj� si� gracze
	 * @return GrassTile
	 */
	public static Tile getGrassTile()
	{
		return grassTile;
	}

	/**
	 * Zwraca nieprzenikalny i zniszczalny tile, kt�ry w momencie zniszczenia mo�e zespawnowa� ulepszenie gracza
	 * @return BoxTile
	 */
	public static Tile getBoxTile()
	{
		return boxTile;
	}

	/**
	 * Zwraca nieprzenikalny i niezniszczalny tile, kt�ry reprezentuje statyczne elementy (np. drzewa) w grze
	 * @return StaticEntityTile
	 */
	public static Tile getStaticEntityTile()
	{
		return staticEntityTile;
	}


}
