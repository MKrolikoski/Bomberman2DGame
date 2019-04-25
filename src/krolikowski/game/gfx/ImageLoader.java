package krolikowski.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Klasa �aduj�ca obraz z podanej �cie�ki
 *
 */
public class ImageLoader
{
	/**
	 * Wczytuje i zwraca obraz z podanej �cie�ki
	 * @param path �cie�ka do obrazu
	 * @return obraz BufferedImage
	 */
	public static BufferedImage loadImage(String path)
	{
		try
		{
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
