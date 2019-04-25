package krolikowski.game.net.packet;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class Packet11PlayerDiedTest
{

	@Test
	public void testGetData()
	{
		try
		{
			Packet11PlayerDied p = new Packet11PlayerDied(4);
			byte[] data = p.getData();
			byte[] result;

			result = "114".getBytes("UTF-8");
			boolean equal = true;
			if (data.length == result.length)
			{
				for (int i = 0; i < result.length; ++i)
				{
					if (result[i] != data[i])
						equal = false;
				}
			} else
			{
				equal = false;
			}
			assertTrue(equal);
		} catch (UnsupportedEncodingException e)
		{
			fail("Exception caught");
		}
	}

}
