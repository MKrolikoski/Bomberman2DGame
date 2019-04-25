package krolikowski.game.net.packet;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class Packet00LoginTest
{

	@Test
	public void testGetData()
	{
		try
		{
			Packet00Login p = new Packet00Login(23, true);
			byte[] data = p.getData();
			byte[] result;

			result = "0023:1".getBytes("UTF-8");
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
