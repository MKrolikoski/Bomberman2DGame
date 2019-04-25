package krolikowski.game.net.packet;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class Packet05JoinRequestTest
{

	@Test
	public void testGetData()
	{
		try
		{
			Packet05JoinRequest p = new Packet05JoinRequest(12);
			byte[] data = p.getData();
			byte[] result;

			result = "0512".getBytes("UTF-8");
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
