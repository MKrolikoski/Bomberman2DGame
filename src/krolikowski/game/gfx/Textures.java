package krolikowski.game.gfx;

import java.awt.image.BufferedImage;

/**
 * Klasa przechowuj¹ca wszystkie modele obiektów w grze
 *
 */

public class Textures
{
	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;
	
	private static BufferedImage[] player1_down, player1_up, player1_left, player1_right, player1_idle, player1_win;
	private static BufferedImage[] player2_down, player2_up, player2_left, player2_right, player2_idle, player2_win;
	private static BufferedImage[] player3_down, player3_up, player3_left, player3_right, player3_idle, player3_win;
	private static BufferedImage[] player4_down, player4_up, player4_left, player4_right, player4_idle, player4_win;
	private static BufferedImage[] player_death, bomb, flame;
	private static BufferedImage[] draw, win, lose, exit, start, settings, sounds, music, back, join, ready, player1ready, player2ready, player3ready, player4ready;
	private static BufferedImage grass, border, stone;
	private static BufferedImage bombUpgrade, powerUpgrade, speedUpgrade;
	private static BufferedImage box, tree;
	private static BufferedImage menuBackground;
	
	/**
	 * inicjalizacja wszystkich obrazów
	 */
	public static void init()
	{
		SpriteSheet entities_spritesheet = new SpriteSheet(ImageLoader.loadImage("/textures/entities_sprite_sheet.png"));
		SpriteSheet player2_spritesheet = new SpriteSheet(ImageLoader.loadImage("/textures/player_2_sprite_sheet.png"));
		SpriteSheet player3_spritesheet = new SpriteSheet(ImageLoader.loadImage("/textures/player_3_sprite_sheet.png"));
		SpriteSheet player4_spritesheet = new SpriteSheet(ImageLoader.loadImage("/textures/player_4_sprite_sheet.png"));
		
		SpriteSheet text_spritesheet = new SpriteSheet(ImageLoader.loadImage("/textures/text_sprite_sheet.png"));
		
		menuBackground = ImageLoader.loadImage("/backgrounds/background.png");
		
		player1_down = new BufferedImage[8];
		player1_down[0] = entities_spritesheet.getSprite(1, 1, WIDTH, HEIGHT);
		player1_down[1] = entities_spritesheet.getSprite(2, 1, WIDTH, HEIGHT);
		player1_down[2] = entities_spritesheet.getSprite(3, 1, WIDTH, HEIGHT);
		player1_down[3] = entities_spritesheet.getSprite(4, 1, WIDTH, HEIGHT);
		player1_down[4] = entities_spritesheet.getSprite(5, 1, WIDTH, HEIGHT);
		player1_down[5] = entities_spritesheet.getSprite(6, 1, WIDTH, HEIGHT);
		player1_down[6] = entities_spritesheet.getSprite(7, 1, WIDTH, HEIGHT);
		player1_down[7] = entities_spritesheet.getSprite(8, 1, WIDTH, HEIGHT);
		
		player1_right = new BufferedImage[8];
		player1_right[0] = entities_spritesheet.getSprite(1, 2, WIDTH, HEIGHT);
		player1_right[1] = entities_spritesheet.getSprite(2, 2, WIDTH, HEIGHT);
		player1_right[2] = entities_spritesheet.getSprite(3, 2, WIDTH, HEIGHT);
		player1_right[3] = entities_spritesheet.getSprite(4, 2, WIDTH, HEIGHT);
		player1_right[4] = entities_spritesheet.getSprite(5, 2, WIDTH, HEIGHT);
		player1_right[5] = entities_spritesheet.getSprite(6, 2, WIDTH, HEIGHT);
		player1_right[6] = entities_spritesheet.getSprite(7, 2, WIDTH, HEIGHT);
		player1_right[7] = entities_spritesheet.getSprite(8, 2, WIDTH, HEIGHT);
		
		player1_left = new BufferedImage[8];
		player1_left[0] = entities_spritesheet.getSprite(1, 3, WIDTH, HEIGHT);
		player1_left[1] = entities_spritesheet.getSprite(2, 3, WIDTH, HEIGHT);
		player1_left[2] = entities_spritesheet.getSprite(3, 3, WIDTH, HEIGHT);
		player1_left[3] = entities_spritesheet.getSprite(4, 3, WIDTH, HEIGHT);
		player1_left[4] = entities_spritesheet.getSprite(5, 3, WIDTH, HEIGHT);
		player1_left[5] = entities_spritesheet.getSprite(6, 3, WIDTH, HEIGHT);
		player1_left[6] = entities_spritesheet.getSprite(7, 3, WIDTH, HEIGHT);
		player1_left[7] = entities_spritesheet.getSprite(8, 3, WIDTH, HEIGHT);
		
		player1_up = new BufferedImage[8];
		player1_up[0] = entities_spritesheet.getSprite(1, 4, WIDTH, HEIGHT);
		player1_up[1] = entities_spritesheet.getSprite(2, 4, WIDTH, HEIGHT);
		player1_up[2] = entities_spritesheet.getSprite(3, 4, WIDTH, HEIGHT);
		player1_up[3] = entities_spritesheet.getSprite(4, 4, WIDTH, HEIGHT);
		player1_up[4] = entities_spritesheet.getSprite(5, 4, WIDTH, HEIGHT);
		player1_up[5] = entities_spritesheet.getSprite(6, 4, WIDTH, HEIGHT);
		player1_up[6] = entities_spritesheet.getSprite(7, 4, WIDTH, HEIGHT);
		player1_up[7] = entities_spritesheet.getSprite(8, 4, WIDTH, HEIGHT);
		
		player1_idle = new BufferedImage[6];
		player1_idle[0] = entities_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player1_idle[1] = entities_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player1_idle[2] = entities_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player1_idle[3] = entities_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player1_idle[4] = entities_spritesheet.getSprite(2, 5, WIDTH, HEIGHT);
		player1_idle[5] = entities_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		
		player1_win = new BufferedImage[5];
		player1_win[0] = entities_spritesheet.getSprite(5, 6, WIDTH, HEIGHT);
		player1_win[1] = entities_spritesheet.getSprite(6, 6, WIDTH, HEIGHT);
		player1_win[2] = entities_spritesheet.getSprite(7, 6, WIDTH, HEIGHT);
		player1_win[3] = entities_spritesheet.getSprite(6, 6, WIDTH, HEIGHT);
		player1_win[4] = entities_spritesheet.getSprite(5, 6, WIDTH, HEIGHT);
		
		player2_down = new BufferedImage[8];
		player2_down[0] = player2_spritesheet.getSprite(1, 1, WIDTH, HEIGHT);
		player2_down[1] = player2_spritesheet.getSprite(2, 1, WIDTH, HEIGHT);
		player2_down[2] = player2_spritesheet.getSprite(3, 1, WIDTH, HEIGHT);
		player2_down[3] = player2_spritesheet.getSprite(4, 1, WIDTH, HEIGHT);
		player2_down[4] = player2_spritesheet.getSprite(5, 1, WIDTH, HEIGHT);
		player2_down[5] = player2_spritesheet.getSprite(6, 1, WIDTH, HEIGHT);
		player2_down[6] = player2_spritesheet.getSprite(7, 1, WIDTH, HEIGHT);
		player2_down[7] = player2_spritesheet.getSprite(8, 1, WIDTH, HEIGHT);
		
		player2_right = new BufferedImage[8];
		player2_right[0] = player2_spritesheet.getSprite(1, 2, WIDTH, HEIGHT);
		player2_right[1] = player2_spritesheet.getSprite(2, 2, WIDTH, HEIGHT);
		player2_right[2] = player2_spritesheet.getSprite(3, 2, WIDTH, HEIGHT);
		player2_right[3] = player2_spritesheet.getSprite(4, 2, WIDTH, HEIGHT);
		player2_right[4] = player2_spritesheet.getSprite(5, 2, WIDTH, HEIGHT);
		player2_right[5] = player2_spritesheet.getSprite(6, 2, WIDTH, HEIGHT);
		player2_right[6] = player2_spritesheet.getSprite(7, 2, WIDTH, HEIGHT);
		player2_right[7] = player2_spritesheet.getSprite(8, 2, WIDTH, HEIGHT);
		
		player2_left = new BufferedImage[8];
		player2_left[0] = player2_spritesheet.getSprite(1, 3, WIDTH, HEIGHT);
		player2_left[1] = player2_spritesheet.getSprite(2, 3, WIDTH, HEIGHT);
		player2_left[2] = player2_spritesheet.getSprite(3, 3, WIDTH, HEIGHT);
		player2_left[3] = player2_spritesheet.getSprite(4, 3, WIDTH, HEIGHT);
		player2_left[4] = player2_spritesheet.getSprite(5, 3, WIDTH, HEIGHT);
		player2_left[5] = player2_spritesheet.getSprite(6, 3, WIDTH, HEIGHT);
		player2_left[6] = player2_spritesheet.getSprite(7, 3, WIDTH, HEIGHT);
		player2_left[7] = player2_spritesheet.getSprite(8, 3, WIDTH, HEIGHT);
		
		player2_up = new BufferedImage[8];
		player2_up[0] = player2_spritesheet.getSprite(1, 4, WIDTH, HEIGHT);
		player2_up[1] = player2_spritesheet.getSprite(2, 4, WIDTH, HEIGHT);
		player2_up[2] = player2_spritesheet.getSprite(3, 4, WIDTH, HEIGHT);
		player2_up[3] = player2_spritesheet.getSprite(4, 4, WIDTH, HEIGHT);
		player2_up[4] = player2_spritesheet.getSprite(5, 4, WIDTH, HEIGHT);
		player2_up[5] = player2_spritesheet.getSprite(6, 4, WIDTH, HEIGHT);
		player2_up[6] = player2_spritesheet.getSprite(7, 4, WIDTH, HEIGHT);
		player2_up[7] = player2_spritesheet.getSprite(8, 4, WIDTH, HEIGHT);
		
		player2_idle = new BufferedImage[6];
		player2_idle[0] = player2_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player2_idle[1] = player2_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player2_idle[2] = player2_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player2_idle[3] = player2_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player2_idle[4] = player2_spritesheet.getSprite(2, 5, WIDTH, HEIGHT);
		player2_idle[5] = player2_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		
		player2_win = new BufferedImage[5];
		player2_win[0] = player2_spritesheet.getSprite(3, 5, WIDTH, HEIGHT);
		player2_win[1] = player2_spritesheet.getSprite(4, 5, WIDTH, HEIGHT);
		player2_win[2] = player2_spritesheet.getSprite(5, 5, WIDTH, HEIGHT);
		player2_win[3] = player2_spritesheet.getSprite(4, 5, WIDTH, HEIGHT);
		player2_win[4] = player2_spritesheet.getSprite(3, 5, WIDTH, HEIGHT);
		
		player3_down = new BufferedImage[8];
		player3_down[0] = player3_spritesheet.getSprite(1, 1, WIDTH, HEIGHT);
		player3_down[1] = player3_spritesheet.getSprite(2, 1, WIDTH, HEIGHT);
		player3_down[2] = player3_spritesheet.getSprite(3, 1, WIDTH, HEIGHT);
		player3_down[3] = player3_spritesheet.getSprite(4, 1, WIDTH, HEIGHT);
		player3_down[4] = player3_spritesheet.getSprite(5, 1, WIDTH, HEIGHT);
		player3_down[5] = player3_spritesheet.getSprite(6, 1, WIDTH, HEIGHT);
		player3_down[6] = player3_spritesheet.getSprite(7, 1, WIDTH, HEIGHT);
		player3_down[7] = player3_spritesheet.getSprite(8, 1, WIDTH, HEIGHT);
		
		player3_right = new BufferedImage[8];
		player3_right[0] = player3_spritesheet.getSprite(1, 2, WIDTH, HEIGHT);
		player3_right[1] = player3_spritesheet.getSprite(2, 2, WIDTH, HEIGHT);
		player3_right[2] = player3_spritesheet.getSprite(3, 2, WIDTH, HEIGHT);
		player3_right[3] = player3_spritesheet.getSprite(4, 2, WIDTH, HEIGHT);
		player3_right[4] = player3_spritesheet.getSprite(5, 2, WIDTH, HEIGHT);
		player3_right[5] = player3_spritesheet.getSprite(6, 2, WIDTH, HEIGHT);
		player3_right[6] = player3_spritesheet.getSprite(7, 2, WIDTH, HEIGHT);
		player3_right[7] = player3_spritesheet.getSprite(8, 2, WIDTH, HEIGHT);
		
		player3_left = new BufferedImage[8];
		player3_left[0] = player3_spritesheet.getSprite(1, 3, WIDTH, HEIGHT);
		player3_left[1] = player3_spritesheet.getSprite(2, 3, WIDTH, HEIGHT);
		player3_left[2] = player3_spritesheet.getSprite(3, 3, WIDTH, HEIGHT);
		player3_left[3] = player3_spritesheet.getSprite(4, 3, WIDTH, HEIGHT);
		player3_left[4] = player3_spritesheet.getSprite(5, 3, WIDTH, HEIGHT);
		player3_left[5] = player3_spritesheet.getSprite(6, 3, WIDTH, HEIGHT);
		player3_left[6] = player3_spritesheet.getSprite(7, 3, WIDTH, HEIGHT);
		player3_left[7] = player3_spritesheet.getSprite(8, 3, WIDTH, HEIGHT);
		
		player3_up = new BufferedImage[8];
		player3_up[0] = player3_spritesheet.getSprite(1, 4, WIDTH, HEIGHT);
		player3_up[1] = player3_spritesheet.getSprite(2, 4, WIDTH, HEIGHT);
		player3_up[2] = player3_spritesheet.getSprite(3, 4, WIDTH, HEIGHT);
		player3_up[3] = player3_spritesheet.getSprite(4, 4, WIDTH, HEIGHT);
		player3_up[4] = player3_spritesheet.getSprite(5, 4, WIDTH, HEIGHT);
		player3_up[5] = player3_spritesheet.getSprite(6, 4, WIDTH, HEIGHT);
		player3_up[6] = player3_spritesheet.getSprite(7, 4, WIDTH, HEIGHT);
		player3_up[7] = player3_spritesheet.getSprite(8, 4, WIDTH, HEIGHT);
		
		player3_idle = new BufferedImage[6];
		player3_idle[0] = player3_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player3_idle[1] = player3_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player3_idle[2] = player3_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player3_idle[3] = player3_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player3_idle[4] = player3_spritesheet.getSprite(2, 5, WIDTH, HEIGHT);
		player3_idle[5] = player3_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		
		player3_win = new BufferedImage[5];
		player3_win[0] = player3_spritesheet.getSprite(3, 5, WIDTH, HEIGHT);
		player3_win[1] = player3_spritesheet.getSprite(4, 5, WIDTH, HEIGHT);
		player3_win[2] = player3_spritesheet.getSprite(5, 5, WIDTH, HEIGHT);
		player3_win[3] = player3_spritesheet.getSprite(4, 5, WIDTH, HEIGHT);
		player3_win[4] = player3_spritesheet.getSprite(3, 5, WIDTH, HEIGHT);
		
		player4_down = new BufferedImage[8];
		player4_down[0] = player4_spritesheet.getSprite(1, 1, WIDTH, HEIGHT);
		player4_down[1] = player4_spritesheet.getSprite(2, 1, WIDTH, HEIGHT);
		player4_down[2] = player4_spritesheet.getSprite(3, 1, WIDTH, HEIGHT);
		player4_down[3] = player4_spritesheet.getSprite(4, 1, WIDTH, HEIGHT);
		player4_down[4] = player4_spritesheet.getSprite(5, 1, WIDTH, HEIGHT);
		player4_down[5] = player4_spritesheet.getSprite(6, 1, WIDTH, HEIGHT);
		player4_down[6] = player4_spritesheet.getSprite(7, 1, WIDTH, HEIGHT);
		player4_down[7] = player4_spritesheet.getSprite(8, 1, WIDTH, HEIGHT);
		
		player4_right = new BufferedImage[8];
		player4_right[0] = player4_spritesheet.getSprite(1, 2, WIDTH, HEIGHT);
		player4_right[1] = player4_spritesheet.getSprite(2, 2, WIDTH, HEIGHT);
		player4_right[2] = player4_spritesheet.getSprite(3, 2, WIDTH, HEIGHT);
		player4_right[3] = player4_spritesheet.getSprite(4, 2, WIDTH, HEIGHT);
		player4_right[4] = player4_spritesheet.getSprite(5, 2, WIDTH, HEIGHT);
		player4_right[5] = player4_spritesheet.getSprite(6, 2, WIDTH, HEIGHT);
		player4_right[6] = player4_spritesheet.getSprite(7, 2, WIDTH, HEIGHT);
		player4_right[7] = player4_spritesheet.getSprite(8, 2, WIDTH, HEIGHT);
		
		player4_left = new BufferedImage[8];
		player4_left[0] = player4_spritesheet.getSprite(1, 3, WIDTH, HEIGHT);
		player4_left[1] = player4_spritesheet.getSprite(2, 3, WIDTH, HEIGHT);
		player4_left[2] = player4_spritesheet.getSprite(3, 3, WIDTH, HEIGHT);
		player4_left[3] = player4_spritesheet.getSprite(4, 3, WIDTH, HEIGHT);
		player4_left[4] = player4_spritesheet.getSprite(5, 3, WIDTH, HEIGHT);
		player4_left[5] = player4_spritesheet.getSprite(6, 3, WIDTH, HEIGHT);
		player4_left[6] = player4_spritesheet.getSprite(7, 3, WIDTH, HEIGHT);
		player4_left[7] = player4_spritesheet.getSprite(8, 3, WIDTH, HEIGHT);
		
		player4_up = new BufferedImage[8];
		player4_up[0] = player4_spritesheet.getSprite(1, 4, WIDTH, HEIGHT);
		player4_up[1] = player4_spritesheet.getSprite(2, 4, WIDTH, HEIGHT);
		player4_up[2] = player4_spritesheet.getSprite(3, 4, WIDTH, HEIGHT);
		player4_up[3] = player4_spritesheet.getSprite(4, 4, WIDTH, HEIGHT);
		player4_up[4] = player4_spritesheet.getSprite(5, 4, WIDTH, HEIGHT);
		player4_up[5] = player4_spritesheet.getSprite(6, 4, WIDTH, HEIGHT);
		player4_up[6] = player4_spritesheet.getSprite(7, 4, WIDTH, HEIGHT);
		player4_up[7] = player4_spritesheet.getSprite(8, 4, WIDTH, HEIGHT);
		
		player4_idle = new BufferedImage[6];
		player4_idle[0] = player4_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player4_idle[1] = player4_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player4_idle[2] = player4_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player4_idle[3] = player4_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		player4_idle[4] = player4_spritesheet.getSprite(2, 5, WIDTH, HEIGHT);
		player4_idle[5] = player4_spritesheet.getSprite(1, 5, WIDTH, HEIGHT);
		
		player4_win = new BufferedImage[5];
		player4_win[0] = player4_spritesheet.getSprite(3, 5, WIDTH, HEIGHT);
		player4_win[1] = player4_spritesheet.getSprite(4, 5, WIDTH, HEIGHT);
		player4_win[2] = player4_spritesheet.getSprite(5, 5, WIDTH, HEIGHT);
		player4_win[3] = player4_spritesheet.getSprite(4, 5, WIDTH, HEIGHT);
		player4_win[4] = player4_spritesheet.getSprite(3, 5, WIDTH, HEIGHT);
		
		player_death = new BufferedImage[5];
		player_death[0] = entities_spritesheet.getSprite(3, 5, WIDTH, HEIGHT); 
		player_death[1] = entities_spritesheet.getSprite(4, 5, WIDTH, HEIGHT); 
		player_death[2] = entities_spritesheet.getSprite(5, 5, WIDTH, HEIGHT); 
		player_death[3] = entities_spritesheet.getSprite(6, 5, WIDTH, HEIGHT); 
		player_death[4] = entities_spritesheet.getSprite(7, 5, WIDTH, HEIGHT); 
		
		bomb = new BufferedImage[3];
		bomb[0] = entities_spritesheet.getSprite(1, 7, WIDTH, HEIGHT);
		bomb[1] = entities_spritesheet.getSprite(2, 7, WIDTH, HEIGHT);
		bomb[2] = entities_spritesheet.getSprite(3, 7, WIDTH, HEIGHT);
		
		flame = new BufferedImage[5];
		flame[0] = entities_spritesheet.getSprite(1, 8, WIDTH, HEIGHT);
		flame[1] = entities_spritesheet.getSprite(2, 8, WIDTH, HEIGHT);
		flame[2] = entities_spritesheet.getSprite(3, 8, WIDTH, HEIGHT);
		flame[3] = entities_spritesheet.getSprite(4, 8, WIDTH, HEIGHT);
		flame[4] = entities_spritesheet.getSprite(5, 8, WIDTH, HEIGHT);
		
		grass = entities_spritesheet.getSprite(1, 6, WIDTH, HEIGHT);
		border = entities_spritesheet.getSprite(3, 6, WIDTH, HEIGHT);
		stone = entities_spritesheet.getSprite(4, 6, WIDTH, HEIGHT);
		
		bombUpgrade = entities_spritesheet.getSprite(4, 7, WIDTH, HEIGHT);
		powerUpgrade = entities_spritesheet.getSprite(5, 7, WIDTH, HEIGHT);
		speedUpgrade = entities_spritesheet.getSprite(6, 7, WIDTH, HEIGHT);
		
		box = entities_spritesheet.getSprite(2, 6, WIDTH, HEIGHT);
		tree = entities_spritesheet.getSprite(8, 5, WIDTH, 47);
		
		exit = new BufferedImage[2];
		exit[0] = text_spritesheet.getSprite(1, 1, WIDTH*3, HEIGHT);
		exit[1] = text_spritesheet.getSprite(4, 1, WIDTH*3, HEIGHT);
		
		start = new BufferedImage[2];
		start[0] = text_spritesheet.getSprite(1, 2, WIDTH*3, HEIGHT);
		start[1] = text_spritesheet.getSprite(4, 2, WIDTH*3, HEIGHT);
		
		settings = new BufferedImage[2];
		settings[0] = text_spritesheet.getSprite(1, 3, WIDTH*6, HEIGHT);
		settings[1] = text_spritesheet.getSprite(1, 4, WIDTH*6, HEIGHT);
		
		sounds = new BufferedImage[4];
		sounds[0] = text_spritesheet.getSprite(5, 5, WIDTH*5, HEIGHT);
		sounds[1] = text_spritesheet.getSprite(5, 6, WIDTH*5, HEIGHT);
		sounds[2] = text_spritesheet.getSprite(7, 1, WIDTH*5, HEIGHT);
		sounds[3] = text_spritesheet.getSprite(7, 2, WIDTH*5, HEIGHT);
		
		music = new BufferedImage[4];
		music[0] = text_spritesheet.getSprite(5, 7, WIDTH*4, HEIGHT);
		music[1] = text_spritesheet.getSprite(5, 8, WIDTH*4, HEIGHT);
		music[2] = text_spritesheet.getSprite(7, 3, WIDTH*4, HEIGHT);
		music[3] = text_spritesheet.getSprite(7, 4, WIDTH*4, HEIGHT);
		
		back = new BufferedImage[2];
		back[0] = text_spritesheet.getSprite(10, 5, WIDTH*3, HEIGHT);
		back[1] = text_spritesheet.getSprite(10, 6, WIDTH*3, HEIGHT);
		
		join = new BufferedImage[2];
		join[0] = text_spritesheet.getSprite(9, 7, WIDTH*3, HEIGHT);
		join[1] = text_spritesheet.getSprite(9, 8, WIDTH*3, HEIGHT);
		
		ready = new BufferedImage[2];
		ready[0] = text_spritesheet.getSprite(12, 1, WIDTH*3, HEIGHT);
		ready[1] = text_spritesheet.getSprite(12, 2, WIDTH*3, HEIGHT);
		
		player1ready = new BufferedImage[2];
		player1ready[0] = text_spritesheet.getSprite(11, 3, WIDTH, HEIGHT);
		player1ready[1] = text_spritesheet.getSprite(12, 3, WIDTH, HEIGHT);
		
		player2ready = new BufferedImage[2];
		player2ready[0] = text_spritesheet.getSprite(11, 4, WIDTH, HEIGHT);
		player2ready[1] = text_spritesheet.getSprite(12, 4, WIDTH, HEIGHT);
		
		
		player3ready = new BufferedImage[2];
		player3ready[0] = text_spritesheet.getSprite(13, 3, WIDTH, HEIGHT);
		player3ready[1] = text_spritesheet.getSprite(14, 3, WIDTH, HEIGHT);
		
		
		player4ready = new BufferedImage[2];
		player4ready[0] = text_spritesheet.getSprite(13, 4, WIDTH, HEIGHT);
		player4ready[1] = text_spritesheet.getSprite(14, 4, WIDTH, HEIGHT);
				
		
		lose = new BufferedImage[2];
		lose[0] = text_spritesheet.getSprite(10, 11, WIDTH, HEIGHT);
		lose[1] = text_spritesheet.getSprite(1, 9, WIDTH*10, HEIGHT*2);
		win = new BufferedImage[2];
		win[0] = text_spritesheet.getSprite(10, 11, WIDTH, HEIGHT);
		win[1] = text_spritesheet.getSprite(1, 11, WIDTH*9, HEIGHT*2);
		
		draw = new BufferedImage[2];
		draw[0] = text_spritesheet.getSprite(10, 11, WIDTH, HEIGHT);
		draw[1] = text_spritesheet.getSprite(11, 9, WIDTH*6, HEIGHT*2);
	}

	/**
	 * Zwraca obrazy chodzenia w dó³ gracza 1
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer1_down()
	{
		return player1_down.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w górê gracza 1
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer1_up()
	{
		return player1_up.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w lewo gracza 1
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer1_left()
	{
		return player1_left.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w prawo gracza 1
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer1_right()
	{
		return player1_right.clone();
	}

	/**
	 * Zwraca obrazy stania gracza 1
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer1_idle()
	{
		return player1_idle.clone();
	}

	/**
	 * Zwraca obrazy zwyciêstwa gracza 1
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer1_win()
	{
		return player1_win.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w dó³ gracza 2
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer2_down()
	{
		return player2_down.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w góre gracza 2
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer2_up()
	{
		return player2_up.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w lewo gracza 2
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer2_left()
	{
		return player2_left.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w prawo gracza 2
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer2_right()
	{
		return player2_right.clone();
	}

	/**
	 * Zwraca obrazy stania gracza 2
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer2_idle()
	{
		return player2_idle.clone();
	}

	/**
	 * Zwraca obrazy zwyciêstwa gracza 2
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer2_win()
	{
		return player2_win.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w dó³ gracza 3
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer3_down()
	{
		return player3_down.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w górê gracza 3
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer3_up()
	{
		return player3_up.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w lewo gracza 3
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer3_left()
	{
		return player3_left.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w prawo gracza 3
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer3_right()
	{
		return player3_right.clone();
	}

	/**
	 * Zwraca obrazy stania gracza 3
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer3_idle()
	{
		return player3_idle.clone();
	}

	/**
	 * Zwraca obrazy zwyciêstwa gracza 3
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer3_win()
	{
		return player3_win.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w dó³ gracza 4
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer4_down()
	{
		return player4_down.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w góre gracza 4
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer4_up()
	{
		return player4_up.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w lewo gracza 4
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer4_left()
	{
		return player4_left.clone();
	}

	/**
	 * Zwraca obrazy chodzenia w prawo gracza 4
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer4_right()
	{
		return player4_right.clone();
	}

	/**
	 * Zwraca obrazy stania gracza 4	
	 * @return tablica obrazów BufferedImage[] 
	 */
	public static BufferedImage[] getPlayer4_idle()
	{
		return player4_idle.clone();
	}

	/**
	 * Zwraca obrazy zwyciêstwa gracza 4
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer4_win()
	{
		return player4_win.clone();
	}

	/**
	 * Zwraca obrazy œmierci gracza
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer_death()
	{
		return player_death.clone();
	}

	/**
	 * Zwraca obrazy bomby
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getBomb()
	{
		return bomb.clone();
	}

	/**
	 * Zwraca obrazy ognia
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getFlame()
	{
		return flame.clone();
	}

	/**
	 * Zwraca obrazy napisu "DRAW"
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getDraw()
	{
		return draw.clone();
	}

	/**
	 * Zwraca  obrazy napisu "YOU WIN"
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getWin()
	{
		return win.clone();
	}

	/**
	 * Zwraca obrazy napisu "YOU LOST"
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getLose()
	{
		return lose.clone();
	}

	/**
	 * Zwraca obrazy napisu "EXIT"
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getExit()
	{
		return exit.clone();
	}

	/**
	 * Zwraca obrazy napisu "START"
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getStart()
	{
		return start.clone();
	}

	/**
	 * Zwraca obrazy napisu "SETTINGS"
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getSettings()
	{
		return settings.clone();
	}

	/**
	 * Zwraca obrazy napisu "SOUNDS"
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getSounds()
	{
		return sounds.clone();
	}

	/**
	 * Zwraca obrazy napisu "MUSIC"
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getMusic()
	{
		return music.clone();
	}

	/**
	 * Zwraca obrazy napisu "BACK
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getBack()
	{
		return back.clone();
	}

	/**
	 * Zwraca obrazy napisu "JOIN"
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getJoin()
	{
		return join.clone();
	}

	/**
	 * Zwraca obrazy napisu "JOIN"
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getReady()
	{
		return ready.clone();
	}

	/**
	 * Zwraca obrazy portretów gracza 1 w lobby
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer1ready()
	{
		return player1ready.clone();
	}

	/**
	 * Zwraca obrazy portretów gracza 2 w lobby
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer2ready()
	{
		return player2ready.clone();
	}

	/**
	 * Zwraca obrazy portretów gracza 3 w lobby
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer3ready()
	{
		return player3ready.clone();
	}

	/**
	 * Zwraca obrazy portretów gracza 4 w lobby
	 * @return tablica obrazów BufferedImage[]
	 */
	public static BufferedImage[] getPlayer4ready()
	{
		return player4ready.clone();
	}

	/**
	 * Zwraca obraz trawy
	 * @return obraz BufferedImage
	 */
	public static BufferedImage getGrass()
	{
		return grass;
	}

	/**
	 * Zwraca obraz obramowania mapy
	 * @return obraz BufferedImage
	 */
	public static BufferedImage getBorder()
	{
		return border;
	}

	/**
	 * Zwraca obraz ska³y
	 * @return obraz BufferedImage
	 */
	public static BufferedImage getStone()
	{
		return stone;
	}

	/**
	 * Zwraca obraz ulepszenia iloœci bomb
	 * @return obraz BufferedImage
	 */
	public static BufferedImage getBombUpgrade()
	{
		return bombUpgrade;
	}

	/**
	 * Zwraca obraz ulepszenia si³y bomby
	 * @return obraz BufferedImage
	 */
	public static BufferedImage getPowerUpgrade()
	{
		return powerUpgrade;
	}

	/**
	 * Zwraca obraz ulepszenia szybkoœci
	 * @return obraz BufferedImage
	 */
	public static BufferedImage getSpeedUpgrade()
	{
		return speedUpgrade;
	}

	/**
	 * Zwraca obraz skrzyni
	 * @return obraz BufferedImage
	 */
	public static BufferedImage getBox()
	{
		return box;
	}

	/**
	 * Zwraca obraz drzewa
	 * @return obraz BufferedImage
	 */
	public static BufferedImage getTree()
	{
		return tree;
	}

	/**
	 * Zwraca obraz tytu³owy
	 * @return obraz BufferedImage
	 */
	public static BufferedImage getMenuBackground()
	{
		return menuBackground;
	}


}
