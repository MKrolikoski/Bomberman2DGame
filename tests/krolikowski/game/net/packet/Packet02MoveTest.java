package krolikowski.game.net.packet;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class Packet02MoveTest
{

	@Test
	public void testGetData()
	{
		try
		{
			Packet02Move p = new Packet02Move(13, 14, 15, 17, false);
			byte[] data = p.getData();
			byte[] result;

			result = "0213:14.0:15.0:17:0".getBytes("UTF-8");
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
