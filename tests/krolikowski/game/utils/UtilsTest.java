package krolikowski.game.utils;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

import krolikowski.game.levels.Map;

public class UtilsTest
{

	/*
	1234
	abc
	 defg
	 
	567
	*/
	@Test
	public void testLoadFileAsString()
	{
		String result = "1234\nabc\n defg\n \n567\n";
		InputStream file = Map.class.getResourceAsStream("/tests/testFile.txt");
		String fromFile = Utils.loadFileAsString(file);
		assertEquals(result, fromFile);
	}

	@Test
	public void testParseInt()
	{
		int result = 2345;
		int expected = Utils.parseInt("2345");
		assertEquals(result, expected);
	}

}
