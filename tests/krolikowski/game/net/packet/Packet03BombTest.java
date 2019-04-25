package krolikowski.game.net.packet;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class Packet03BombTest
{

	@Test
	public void testGetData()
	{
		try
		{
			Packet03Bomb p = new Packet03Bomb(2, 43.7f, 23);
			byte[] data = p.getData();
			byte[] result;

			result = "032:43.7:23.0".getBytes("UTF-8");
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
