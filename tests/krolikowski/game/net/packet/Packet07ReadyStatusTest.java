package krolikowski.game.net.packet;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class Packet07ReadyStatusTest
{

	@Test
	public void testGetData()
	{
		try
		{
			Packet07ReadyStatus p = new Packet07ReadyStatus(5, true);
			byte[] data = p.getData();
			byte[] result;

			result = "075:1".getBytes("UTF-8");
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
