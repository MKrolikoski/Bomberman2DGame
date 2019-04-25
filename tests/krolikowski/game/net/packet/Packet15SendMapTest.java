package krolikowski.game.net.packet;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import krolikowski.game.levels.Map;
import krolikowski.game.utils.Utils;

public class Packet15SendMapTest
{

	@Test
	public void testGetData()
	{
		try
		{
			InputStream file = Map.class.getResourceAsStream("/maps/grassMap.txt");
			String inputFile = Utils.loadFileAsString(file);
			Packet15SendMap p = new Packet15SendMap(inputFile);
			byte[] data = p.getData();
			byte[] result;

			result = ("15"+inputFile).getBytes("UTF-8");
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
