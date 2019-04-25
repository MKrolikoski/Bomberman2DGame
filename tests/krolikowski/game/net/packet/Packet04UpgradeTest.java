package krolikowski.game.net.packet;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class Packet04UpgradeTest
{

	@Test
	public void testGetData()
	{
		try
		{
			Packet04Upgrade p = new Packet04Upgrade(231.3f, 21.54f, 3);
			byte[] data = p.getData();
			byte[] result;

			result = "04231.3:21.54:3".getBytes("UTF-8");
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
