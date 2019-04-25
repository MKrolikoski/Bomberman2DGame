package krolikowski.game.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import krolikowski.game.Game;
import krolikowski.game.Handler;
import krolikowski.game.entities.mobs.Player;
import krolikowski.game.entities.mobs.PlayerMP;

public class EntityManagerTest
{

	@Test
	public void testAddEntity()
	{
		Game game = new Game();
		game.init();
		EntityManager em = new EntityManager();
		for(int i = 0; i < 3; i++)
			em.addEntity(new Player(new Handler(game), 0, null));
		assertEquals(em.getEntities().size(), 3);
	}


	@Test
	public void testGetPlayerMP()
	{
		Game game = new Game();
		game.init();
		EntityManager em = new EntityManager();
		PlayerMP p = new PlayerMP(new Handler(game), null, 0, 0, null ,false);
		em.addEntity(p);
		assertEquals(p, em.getPlayerMP(p.getPlayerId()));
	}

	@Test
	public void testRemovePlayerMP()
	{
		Game game = new Game();
		game.init();
		EntityManager em = new EntityManager();
		PlayerMP p = new PlayerMP(new Handler(game), null, 0, 0, null ,false);
		em.addEntity(p);
		em.removePlayerMP(0);
		PlayerMP player = em.getPlayerMP(0);
		assertNull(player);
	}

	@Test
	public void testMovePlayer()
	{
		Game game = new Game();
		game.init();
		EntityManager em = new EntityManager();
		PlayerMP p = new PlayerMP(new Handler(game), null, 0, 0, null ,false);
		em.addEntity(p);
		em.movePlayer(0, 10, 12, 1, true);
		assertEquals(10, (int)p.getX());
		assertEquals(12, (int)p.getY());
		assertEquals(1, (int)p.getMovDir());
		assertTrue(p.isMoving());
	}

	@Test
	public void testGetPlayerReadyStatus()
	{
		Game game = new Game();
		game.init();
		EntityManager em = new EntityManager();
		em.addEntity(new PlayerMP(new Handler(game), null, 0, 0, false));
		assertEquals(false, em.getPlayerReadyStatus(0));
	}

	@Test
	public void testRemovePlayers()
	{
		Game game = new Game();
		game.init();
		EntityManager em = new EntityManager();
		for(int i = 0; i < 3; i++)
			em.addEntity(new Player(new Handler(game), 0, null));
		for(int i = 0; i < 3; i++)
			em.removePlayers();
		
		assertEquals(em.getEntities().size(), 0);
	}

	@Test
	public void testCreateNewPlayerHandlerInetAddressIntIntInputHandlerBoolean()
	{
		Game game = new Game();
		game.init();
		Handler h = new Handler(game);
		EntityManager em = new EntityManager();
		PlayerMP p = new PlayerMP(h, null, 0, 0, false);
		PlayerMP result = em.createNewPlayer(h, null, 0, 0, false);
		assertEquals(p.getHeight(), result.getHeight());
		assertEquals(p.getWidth(), result.getWidth());
		assertEquals((int)p.getX(), (int)result.getX());
		assertEquals((int)p.getY(), (int)result.getY());
		assertEquals(p.getPort(), result.getPort());
		assertEquals(p.getPlayerId(), result.getPlayerId());
		assertEquals(p.getIpAddress(), result.getIpAddress());
	}

	@Test
	public void testCreateNewPlayerHandlerInetAddressIntIntBoolean()
	{
		Game game = new Game();
		game.init();
		EntityManager em = new EntityManager();
		PlayerMP p = new PlayerMP(new Handler(game), null, 0, 0, null ,false);
		PlayerMP result = em.createNewPlayer(new Handler(game), null, 0, 0, null, false);
		assertEquals(p.getHeight(), result.getHeight());
		assertEquals(p.getWidth(), result.getWidth());
		assertEquals((int)p.getX(), (int)result.getX());
		assertEquals((int)p.getY(), (int)result.getY());
		assertEquals(p.getPort(), result.getPort());
		assertEquals(p.getPlayerId(), result.getPlayerId());
		assertEquals(p.getIpAddress(), result.getIpAddress());
	}

}
