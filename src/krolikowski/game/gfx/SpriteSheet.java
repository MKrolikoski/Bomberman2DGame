package krolikowski.game.gfx;

import java.awt.image.BufferedImage;

/**
 * Klasa przechowuj�ca spritesheet zawieraj�cy obrazy obiekt�w 
 *
 */

public class SpriteSheet
{
	private BufferedImage spritesheet;
	
	/**
	 * Konstruuje nowy SpriteSheet i przypisuje mu przekazany obraz
	 * @param spritesheet spritesheet
	 */
	public SpriteSheet(BufferedImage spritesheet)
	{
		this.spritesheet = spritesheet;
	}
	
	/**
	 * Zwraca obraz znajduj�cy si� na podanym obszarze spritesheet'a
	 * @param col kolumna
	 * @param row wiersz
	 * @param width szeroko��
	 * @param height wysoko��
	 * @return obraz BufferedImage
	 */
	public BufferedImage getSprite(int col, int row, int width, int height)
	{
		return spritesheet.getSubimage((col*32)-32, (row*32)-32, width, height);
	}

}
