package krolikowski.game.levels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import krolikowski.game.Game;
import krolikowski.game.Handler;
import krolikowski.game.tiles.Tile;
import krolikowski.game.utils.Utils;

public class MapTest
{

	@Test
	public void testGetTile()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		InputStream file = Map.class.getResourceAsStream("/maps/grassMap.txt");
		String inputFile = Utils.loadFileAsString(file);
		Map map = new Map(handler, inputFile);
		try
		{
			file.close();
		} catch (IOException e)
		{
			fail("Closing file");
		}
		handler.setMap(map);
		
		Tile result1 = Tile.getBorderTile();
		
		Tile tile1 = handler.getMap().getTile(-2, -2);
		
		assertEquals(result1, tile1);
		
		Tile result2 = Tile.getGrassTile();
		Tile tile2 = handler.getMap().getTile(1, 1);
		
		assertEquals(result2, tile2);
	}

	@Test
	public void testChangeTileType()
	{
		Game game = new Game();
		game.init();
		Handler handler = new Handler(game);
		InputStream file = Map.class.getResourceAsStream("/maps/grassMap.txt");
		String inputFile = Utils.loadFileAsString(file);
		Map map = new Map(handler, inputFile);
		try
		{
			file.close();
		} catch (IOException e)
		{
			fail("Closing file");
		}
		handler.setMap(map);
		
		Tile resultBeforeChange = Tile.getGrassTile();
		Tile tile = handler.getMap().getTile(1, 1);
		
		assertEquals(resultBeforeChange, tile);
		
		handler.getMap().changeTileType(1, 1, 2);
		Tile resultAfterChange = Tile.getBoxTile();
		tile = handler.getMap().getTile(1, 1);

		assertEquals(resultAfterChange, tile);
		
	}

}
