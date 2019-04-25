package krolikowski.game.gfx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;

import org.junit.Test;

public class AnimationTest
{

	@Test
	public void testGetCurrentFrame()
	{
		BufferedImage image[] = new BufferedImage[2];
		image[0] = ImageLoader.loadImage("/backgrounds/background.png");
		image[1] = ImageLoader.loadImage("/buttons/arrow.png");
		Animation a = new Animation(0, image);
		for(int i = 0; i < 2; i++)
			a.tick();
		assertEquals(image[0], a.getCurrentFrame());
	}

	@Test
	public void testDidLoop()
	{
		BufferedImage image[] = new BufferedImage[2];
		image[0] = ImageLoader.loadImage("/backgrounds/background.png");
		image[1] = ImageLoader.loadImage("/buttons/arrow.png");
		Animation a = new Animation(0, image);
		a.setLoopsLimit(2);
		for(int i = 0; i < 4; i++)
			a.tick();
		assertTrue(a.didLoop());
	}
}
