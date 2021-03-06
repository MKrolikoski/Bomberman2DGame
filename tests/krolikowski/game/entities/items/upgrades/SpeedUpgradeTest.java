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

public class SpeedUpgradeTest
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
		SpeedUpgrade su = new SpeedUpgrade(handler, 0, 0);
		handler.getMap().getEntityManager().addEntity(su);

		Player player = new Player(handler, 0, null);
		su.upgradePlayer(player);
		int exptectedSpeed = (int)(1 * handler.getGame().getScale() + 0.2 * handler.getGame().getScale());
		assertEquals(exptectedSpeed, (int)player.getSpeed());
	}

}
