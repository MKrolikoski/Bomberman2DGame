package krolikowski.game.entities.items.upgrades;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import krolikowski.game.Game;
import krolikowski.game.Handler;
import krolikowski.game.entities.mobs.Player;
import krolikowski.game.levels.Map;
import krolikowski.game.utils.Utils;

public class PowerUpgradeTest
{

	@Test
	public void testUpgrade()
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
		PowerUpgrade pu = new PowerUpgrade(handler, 0, 0);
		handler.getMap().getEntityManager().addEntity(pu);
		
		Player player = new Player(handler, 0, null);
		pu.upgradePlayer(player);
		assertEquals(2, player.getBombPower());
	}

}
