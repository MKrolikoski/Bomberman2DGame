package krolikowski.game.entities.mobs;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import krolikowski.game.Handler;
import krolikowski.game.entities.Entity;
import krolikowski.game.entities.items.Bomb;
import krolikowski.game.gfx.Animation;
import krolikowski.game.gfx.Textures;
import krolikowski.game.input.InputHandler;
import krolikowski.game.net.packet.Packet01Disconnect;
import krolikowski.game.net.packet.Packet02Move;
import krolikowski.game.net.packet.Packet03Bomb;
import krolikowski.game.net.packet.Packet11PlayerDied;
import krolikowski.game.net.packet.Packet13ResetPlayers;
import krolikowski.game.tiles.Tile;

/**
 * Klasa reprezentuj¹ca gracza
 *
 */
public class Player extends Mob
{
	protected Animation walkingDown;
	protected Animation walkingRight;
	protected Animation walkingLeft;
	protected Animation walkingUp;
	protected Animation idle;
	protected Animation winning;
	protected Animation dying;
	protected Animation currentAnimation = null;

	protected int animationSpeed = 100;

	protected int bombs;
	protected long timer, lastTime;
	protected int bombPower = 1;
	protected boolean alive, winner, invulnerable;
	protected int movDir;
	protected InputHandler input;

	protected int playerId;

	/**
	 * Konstruuje nowy obiekt Player, przypisuj¹c mu przekazany Handler, id i obiekt pobieraj¹cy input
	 * @param handler Handler
	 * @param playerId id gracza
	 * @param input obiekt obs³uguj¹cy input gracza
	 */
	public Player(Handler handler, int playerId, InputHandler input)
	{
		super(handler, 0, 0, Mob.DEFAULT_WIDTH, Mob.DEFAULT_HEIGHT);
		this.playerId = playerId;
		this.input = input;
		init();
	}

	/**
	 * Inicjalizuje zmienne i przypisuje im domyœlne wartoœci
	 */
	protected void init()
	{
		alive = true;
		winner = false;
		bombs = 1;
		bombPower = 1;
		speed = DEFAULT_SPEED * handler.getGame().getScale();
		bounds.x = 10 * handler.getGame().getScale();
		bounds.y = 20 * handler.getGame().getScale();
		bounds.width = 11 * handler.getGame().getScale();
		bounds.height = 6 * handler.getGame().getScale();
		setSpawnLocation();
		switch (playerId)
		{
		default:
		case 1:
			walkingDown = new Animation(animationSpeed, Textures.getPlayer1_down());
			walkingRight = new Animation(animationSpeed, Textures.getPlayer1_right());
			walkingLeft = new Animation(animationSpeed, Textures.getPlayer1_left());
			walkingUp = new Animation(animationSpeed, Textures.getPlayer1_up());
			idle = new Animation(500, Textures.getPlayer1_idle());
			winning = new Animation(250, Textures.getPlayer1_win());
			break;
		case 2:
			walkingDown = new Animation(animationSpeed, Textures.getPlayer2_down());
			walkingRight = new Animation(animationSpeed, Textures.getPlayer2_right());
			walkingLeft = new Animation(animationSpeed, Textures.getPlayer2_left());
			walkingUp = new Animation(animationSpeed, Textures.getPlayer2_up());
			idle = new Animation(500, Textures.getPlayer2_idle());
			winning = new Animation(250, Textures.getPlayer2_win());
			break;
		case 3:
			walkingDown = new Animation(animationSpeed, Textures.getPlayer3_down());
			walkingRight = new Animation(animationSpeed, Textures.getPlayer3_right());
			walkingLeft = new Animation(animationSpeed, Textures.getPlayer3_left());
			walkingUp = new Animation(animationSpeed, Textures.getPlayer3_up());
			idle = new Animation(500, Textures.getPlayer3_idle());
			winning = new Animation(250, Textures.getPlayer3_win());
			break;
		case 4:
			walkingDown = new Animation(animationSpeed, Textures.getPlayer4_down());
			walkingRight = new Animation(animationSpeed, Textures.getPlayer4_right());
			walkingLeft = new Animation(animationSpeed, Textures.getPlayer4_left());
			walkingUp = new Animation(animationSpeed, Textures.getPlayer4_up());
			idle = new Animation(500, Textures.getPlayer4_idle());
			winning = new Animation(250, Textures.getPlayer4_win());
			break;
		}
		winning.setLoopsLimit(3);
		dying = new Animation(100, Textures.getPlayer_death());
		setCurrentAnimation(idle);
	}

	/**
	 * Update gracza
	 */
	@Override
	public void tick()
	{
		if (alive)
		{
			currentAnimation.tick();
			if (!winner)
			{
				getInput();
				move();
				if (isMoving())
				{
					Packet02Move packet = new Packet02Move(getPlayerId(), getX() / handler.getGame().getScale(),
							getY() / handler.getGame().getScale(), getMovDir(), isMoving());
					packet.writeData(handler.getGame().getSocketClient());
				}
			} else
			{
				setInvulnerability(true);
				winning.tick();
				if (winning.didLoop())
				{
					if (handler.getGame().getPlayerId() == 1)
					{
						handler.getMap().getEntityManager().removeEntity(this);
						Packet13ResetPlayers packet = new Packet13ResetPlayers();
						packet.writeData(handler.getGame().getSocketClient());
					} else
					{
						handler.getMap().getEntityManager().removeEntity(this);
					}
				}
			}
		} else
		{
			if (!dying.didLoop())
			{
				dying.tick();
			} else
			{
				if (handler.getGame().getPlayerId() == 1)
				{
					handler.getMap().getEntityManager().removeEntity(this);
					Packet11PlayerDied packet = new Packet11PlayerDied(getPlayerId());
					packet.writeData(handler.getGame().getSocketClient());
				} else
				{
					handler.getMap().getEntityManager().removeEntity(this);
				}
			}
		}
	}

	/**
	 * Zabija gracza
	 */
	public void playerDied()
	{
		alive = false;
	}

	/**
	 * Renderuje gracza
	 */
	@Override
	public void render(Graphics g)
	{
		if (alive)
		{
			if (!winner)
			{
				g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
			} else
			{
				g.drawImage(winning.getCurrentFrame(), (int) x, (int) y, width, height, null);
			}
		} else
		{
			if (!dying.didLoop())
				g.drawImage(dying.getCurrentFrame(), (int) x, (int) y, width, height, null);
		}
	}

	private BufferedImage getCurrentAnimationFrame()
	{
		if (movDir == 1)
		{
			setCurrentAnimation(walkingRight);
		}
		if (movDir == -1)
		{
			setCurrentAnimation(walkingLeft);
		}
		if (movDir == -2)
		{
			setCurrentAnimation(walkingDown);
		}
		if (movDir == 2)
		{
			setCurrentAnimation(walkingUp);
		}
		if (movDir == 0)
		{
			setCurrentAnimation(idle);
		}
		return currentAnimation.getCurrentFrame();
	}

	private void getInput()
	{
		xMove = 0;
		yMove = 0;
		if (!isMoving)
			movDir = 0;

		if (input != null)
		{
			if (input.isUp())
			{
				yMove = -speed;
				movDir = 2;
			}
			if (input.isDown())
			{
				yMove = speed;
				movDir = -2;
			}
			if (input.isLeft())
			{
				xMove = -speed;
				movDir = -1;
			}
			if (input.isRight())
			{
				xMove = speed;
				movDir = 1;
			}
			if (input.isSpace())
			{
				timer += System.currentTimeMillis() - lastTime;
				lastTime = System.currentTimeMillis();
				if (bombs > 0 && timer > 250)
				{
					timer = 0;
					int[] spawnLocation;
					spawnLocation = getClosestTileCoords();
					if (!bombHere(spawnLocation[0], spawnLocation[1]))
					{
						handler.getMap().getEntityManager().addEntity(
								new Bomb(handler, this, Tile.getWidth() * spawnLocation[0], Tile.getHeight() * spawnLocation[1]));
						Packet03Bomb packet = new Packet03Bomb(getPlayerId(),
								Tile.getWidth() * spawnLocation[0] / (float)handler.getGame().getScale(),
								Tile.getHeight() * spawnLocation[1] / (float)handler.getGame().getScale());
						packet.writeData(handler.getGame().getSocketClient());
						bombs--;
					}
				}
			}
			if (input.isBackspace())
			{
				input.toggleKey(KeyEvent.VK_BACK_SPACE, false);
				Packet01Disconnect packet = new Packet01Disconnect(handler.getGame().getPlayerId());
				packet.writeData(handler.getGame().getSocketClient());
				handler.getGame().goToMenu();
			}
		}
	}

	private boolean bombHere(int x, int y)
	{
		for (Entity e : handler.getMap().getEntityManager().getEntities())
		{
			if (e instanceof Bomb)
			{
				if (e.getCollisionBounds(0f, 0f)
						.intersects(new Rectangle(Tile.getWidth() * x, Tile.getHeight() * y, Tile.getWidth(), Tile.getHeight())))
					return true;
			}
		}
		return false;
	}

	private int[] getClosestTileCoords()
	{
		int[] coordinates = new int[2];
		int tempX = (int) x;
		while (tempX > 0)
		{
			tempX -= Tile.getWidth();
			coordinates[0]++;
		}
		int tempY = (int) y + bounds.height;
		while (tempY > 0)
		{
			tempY -= Tile.getHeight();
			coordinates[1]++;
		}
		if (handler.getMap().getTile(coordinates[0], coordinates[1]).isSolid())
			coordinates[1]--;
		return coordinates;
	}

	private void setCurrentAnimation(Animation a)
	{
		currentAnimation = a;
	}

	/**
	 * Zwraca si³ê bomb gracza
	 * @return si³a bomb gracza
	 */
	public int getBombPower()
	{
		return bombPower;
	}

	/**
	 * Zwraca informacjê, czy gracz jest ¿ywy
	 * @return true gracz jest ¿ywy, false w przeciwnym wypadku
	 */
	public boolean isAlive()
	{
		return alive;
	}

	/**
	 * zwiêksza liczbê bomb gracza
	 */
	public void addBomb()
	{
		bombs++;
	}
	
	/**
	 * Zwraca liczbê bomb gracza
	 * @return liczba bomb gracza
	 */
	public int getBombs()
	{
		return bombs;
	}

	/**
	 * zwiêksza si³ê bomb gracza
	 */
	public void addPower()
	{
		bombPower++;
	}

	/**
	 * zwiêksza szybkoœæ gracza
	 */
	public void addSpeed()
	{
		speed += 0.2 * handler.getGame().getScale();
	}

	/**
	 * Zwraca id gracza
	 * @return id gracza
	 */
	public int getPlayerId()
	{
		return playerId;
	}

	/**
	 * Ustawia id gracza
	 * @param playerId id gracza
	 */
	public void setPlayerId(int playerId)
	{
		this.playerId = playerId;
	}

	/**
	 * ustawia domyœln¹ lokalizacjê gracza w zale¿noœci od jego id
	 */
	public void setSpawnLocation()
	{
		switch (playerId)
		{
		case 1:
			x = Tile.getWidth() / (float)2;
			y = Tile.getHeight() / (float)2;
			break;
		case 2:
			x = handler.getWidth() - Tile.getWidth() * 4;
			y = Tile.getHeight() / (float)4;
			break;
		case 3:
			x = Tile.getWidth() / (float)2;
			y = handler.getHeight() - Tile.getHeight() / 2 * 7;
			break;
		case 4:
			x = handler.getWidth() - Tile.getWidth() * 4;
			y = handler.getHeight() - Tile.getHeight() / 2 * 7;
			break;
		default:
			x = 0;
			y = 0;
		}
	}

	/**
	 * Zwraca kierunek ruchu gracza (2 do góry, -2 do do³u, 1 w prawo, -1 w lewo, 0 nie porusza siê)
	 * @return kierunek ruchu gracza
	 */
	public int getMovDir()
	{
		return movDir;
	}

	/**
	 * Ustawia kierunek ruchu gracza (2 do góry, -2 do do³u, 1 w prawo, -1 w lewo, 0 nie porusza siê)
	 * @param movDir kierunek ruchu gracza
	 */
	public void setMovDir(int movDir)
	{
		if(movDir <= 2 && movDir >= -2)
			this.movDir = movDir;
		else
			this.movDir = 0;
	}

	/**
	 * gracz wygrywa
	 */
	public void playerWon()
	{

		winner = true;
	}

	/**
	 * Zwraca informacjê, czy gracz wygra³ rozgrywkê
	 * @return true jeœli wygra³, false w przeciwnym wypadku
	 */
	public boolean isWinner()
	{
		return winner;
	}

	/**
	 * Zwraca informacjê czy gracz jest niewra¿liwy na obra¿enia
	 * @return true jeœli jest niewra¿liwy, false w przeciwnym wypadku
	 */
	public boolean isInvulnerable()
	{
		return invulnerable;
	}

	/**
	 * Ustawia niewra¿liwoœæ gracza na obra¿enia
	 * @param invulnerable niewra¿liwoœæ gracza
	 */
	public void setInvulnerability(boolean invulnerable)
	{
		this.invulnerable = invulnerable;
	}

}
