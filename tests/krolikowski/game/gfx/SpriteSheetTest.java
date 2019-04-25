package krolikowski.game.gfx;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;

import org.junit.Test;

public class SpriteSheetTest
{

	@Test
	public void testGetSprite()
	{
		BufferedImage result = ImageLoader.loadImage("/textures/entities_sprite_sheet.png");
		SpriteSheet spritesheet = new SpriteSheet(ImageLoader.loadImage("/textures/entities_sprite_sheet.png"));
		BufferedImage image = spritesheet.getSprite(1, 1, 256, 256);
		boolean equal = true;
		DataBuffer dbA = result.getData().getDataBuffer();
		int sizeA = dbA.getSize();
		DataBuffer dbB = image.getData().getDataBuffer();
		int sizeB = dbB.getSize();
		if (sizeA == sizeB)
		{
			for (int i = 0; i < sizeA; i++)
			{
				if (dbA.getElem(i) != dbB.getElem(i))
				{
					equal = false;
				}
			}
		} else
		{
			equal = false;
		}
		assertTrue(equal);
	}

}
