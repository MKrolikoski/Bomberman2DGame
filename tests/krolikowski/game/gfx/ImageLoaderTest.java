package krolikowski.game.gfx;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImageLoaderTest
{

	@Test
	public void testLoadImage()
	{
		try
		{
			BufferedImage result = ImageIO.read(new File("res/backgrounds/background.png"));
			BufferedImage image = ImageLoader.loadImage("/backgrounds/background.png");
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
		} catch (IOException e)
		{
			fail("Exception caught");
		}
		
		
	}

}
