package krolikowski.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import krolikowski.game.sounds.SoundClip;

public class GameTest
{

	@Test
	public void testInit()
	{
		Game game = new Game();
		game.init();
		assertNotNull(game.getDisplay());
		assertNotNull(game.getInput());
		assertNotNull(game.getMyIp());
		assertEquals(-2, game.getGameStatus());
	}
	
	@Test
	public void testSetGameStatus()
	{
		Game game = new Game();
		game.setGameStatus(3);
		assertEquals(3, game.getGameStatus());
	}

	@Test
	public void testGetGameStatus()
	{
		Game game = new Game();
		game.setGameStatus(3);
		assertEquals(3, game.getGameStatus());
	}

	@Test
	public void testIsMusicOn()
	{
		Game game = new Game();
		game.setMusicOn();
		assertEquals(true, game.isMusicOn());
	}

	@Test
	public void testSetMusicOn()
	{
		Game game = new Game();
		game.setMusicOn();
		assertEquals(true, game.isMusicOn());
	}

	@Test
	public void testSetMusicOff()
	{
		Game game = new Game();
		game.setMusicOff();
		assertEquals(false, game.isMusicOn());
	}

	@Test
	public void testAreSoundsOn()
	{
		Game game = new Game();
		game.setSoundsOn();
		assertEquals(true, game.areSoundsOn());
	}

	@Test
	public void testSetSoundsOn()
	{
		Game game = new Game();
		game.setSoundsOn();
		assertEquals(true, game.areSoundsOn());
	}

	@Test
	public void testSetSoundsOff()
	{
		Game game = new Game();
		game.setSoundsOff();
		assertEquals(false, game.areSoundsOn());
	}

	@Test
	public void testGetMenuMusic()
	{
		Game game = new Game();
		SoundClip clip = new SoundClip(SoundClip.class.getResourceAsStream("/sounds/menu_music.wav"));
		game.setMenuMusic(clip);
		assertEquals(clip ,game.getMenuMusic());
	}

	@Test
	public void testSetMenuMusic()
	{
		Game game = new Game();
		SoundClip clip = new SoundClip(SoundClip.class.getResourceAsStream("/sounds/menu_music.wav"));
		game.setMenuMusic(clip);
		assertEquals(clip ,game.getMenuMusic());
	}

	@Test
	public void testGetGameMusic()
	{
		Game game = new Game();
		SoundClip clip = new SoundClip(SoundClip.class.getResourceAsStream("/sounds/menu_music.wav"));
		game.setGameMusic(clip);
		assertEquals(clip ,game.getGameMusic());
	}

	@Test
	public void testSetGameMusic()
	{
		Game game = new Game();
		SoundClip clip = new SoundClip(SoundClip.class.getResourceAsStream("/sounds/menu_music.wav"));
		game.setGameMusic(clip);
		assertEquals(clip ,game.getGameMusic());
	}

}
