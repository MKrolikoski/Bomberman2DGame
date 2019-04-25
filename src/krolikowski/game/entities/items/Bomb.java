package krolikowski.game.entities.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import krolikowski.game.Handler;
import krolikowski.game.entities.Entity;
import krolikowski.game.entities.mobs.Player;
import krolikowski.game.entities.statics.Box;
import krolikowski.game.entities.statics.StaticEntity;
import krolikowski.game.gfx.Animation;
import krolikowski.game.gfx.Textures;
import krolikowski.game.sounds.SoundClip;
import krolikowski.game.tiles.Tile;

/**
 * Klasa reprezentuj¹ca bombê w grze
 *
 */

public class Bomb extends Item
{
	private Animation bombTicking;
	private Animation bombExploding;
	private Animation currentAnimation;
	
	private SoundClip explosion;

	private Player player;

	private int animationSpeed = 100;
	private long timer, lastTime;
	private int explosionDelay = 2000;
	private int flamesTime = 1000;

	private boolean exploded;
	private int rightEnd, leftEnd, downEnd, upEnd;
	private List<Entity> entitiesInside;

	/**
	 * Konstruuje nowy obiekt Bomb, przypisuj¹c mu przekazany Handler, gracza i koordynaty x,y spawnu bomby
	 * @param handler Handler
	 * @param player gracz
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 */
	public Bomb(Handler handler, Player player, float x, float y)
	{
		super(handler, x, y, Item.DEFAULT_WIDTH, Item.DEFAULT_HEIGHT);

		bounds.x = 4 * handler.getGame().getScale();
		bounds.y = 4 * handler.getGame().getScale();
		bounds.width = 8 * handler.getGame().getScale();
		bounds.height = 8 * handler.getGame().getScale();

		this.player = player;
		rightEnd = player.getBombPower();
		leftEnd = player.getBombPower();
		downEnd = player.getBombPower();
		upEnd = player.getBombPower();

		bombTicking = new Animation(animationSpeed, Textures.getBomb());
		bombExploding = new Animation(animationSpeed, Textures.getFlame());
		setCurrentAnimation(bombTicking);

		explosion = new SoundClip("/sounds/explosion.wav");
		
		lastTime = System.currentTimeMillis();
		timer = 0;

		exploded = false;

		entitiesInside = new ArrayList<Entity>();
		checkEntitiesInsideBounds();
	}
	
	/**
	 * Update zmiennych bomby i jej animacji
	 */
	@Override
	public void tick()
	{
		updateBomb();
		currentAnimation.tick();
	}

	private void updateBomb()
	{
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if (!exploded && timer >= explosionDelay)
		{
			if(handler.getGame().areSoundsOn())
				explosion.playSound();
			explodeBomb();
		} else if (exploded && timer >= flamesTime)
		{
			explosion.remove();
			handler.getMap().getEntityManager().removeEntity(this);
		}
	}

	/**
	 * renderuje bombê
	 */
	@Override
	public void render(Graphics g)
	{
		if (handler.getGame().getGameStatus() == 1)
		{
			g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);

			if (exploded)
			{
				renderRightFlame(g);
				renderLeftFlame(g);
				renderDownFlame(g);
				renderUpFlame(g);
			}
		}
	}

	private void renderRightFlame(Graphics g)
	{
		for (int i = 0; i <= rightEnd; i++)
		{
			int nextTileX = (int) (x + i * width) / Tile.getWidth();
			int nextTileY = (int) ((y) / Tile.getHeight());
			if (!collisionWithTile(nextTileX, nextTileY)
					&& !collisionWithStaticEntity(nextTileX * Tile.getWidth(), nextTileY * Tile.getHeight()))
			{
				g.drawImage(getCurrentAnimationFrame(), nextTileX * Tile.getWidth(), nextTileY * Tile.getHeight(), width, height,
						null);
				checkFlameImpact(nextTileX * Tile.getWidth(), nextTileY * Tile.getHeight());
			} else
			{
				if (tileDestructible(nextTileX, nextTileY))
					handler.getMap().changeTileType(nextTileX, nextTileY, 1);
				rightEnd = i;
			}
		}
	}

	private void renderLeftFlame(Graphics g)
	{
		for (int i = 1; i <= leftEnd; i++)
		{
			int nextTileX = (int) (x - i * width) / Tile.getWidth();
			int nextTileY = (int) ((y) / Tile.getHeight());
			if (!collisionWithTile(nextTileX, nextTileY)
					&& !collisionWithStaticEntity(nextTileX * Tile.getWidth(), nextTileY * Tile.getHeight()))
			{
				g.drawImage(getCurrentAnimationFrame(), nextTileX * Tile.getWidth(), nextTileY * Tile.getHeight(), width, height,
						null);
				checkFlameImpact(nextTileX * Tile.getWidth(), nextTileY * Tile.getHeight());
			} else
			{
				if (tileDestructible(nextTileX, nextTileY))
					handler.getMap().changeTileType(nextTileX, nextTileY, 1);
				leftEnd = i;
			}
		}
	}

	private void renderDownFlame(Graphics g)
	{
		for (int i = 1; i <= downEnd; i++)
		{
			int nextTileX = (int) (x) / Tile.getWidth();
			int nextTileY = (int) (y + i * height) / Tile.getHeight();
			if (!collisionWithTile(nextTileX, nextTileY)
					&& !collisionWithStaticEntity(nextTileX * Tile.getWidth(), nextTileY * Tile.getHeight()))
			{
				g.drawImage(getCurrentAnimationFrame(), nextTileX * Tile.getWidth(), nextTileY * Tile.getHeight(), width, height,
						null);
				checkFlameImpact(nextTileX * Tile.getWidth(), nextTileY * Tile.getHeight());
			} else
			{
				if (tileDestructible(nextTileX, nextTileY))
					handler.getMap().changeTileType(nextTileX, nextTileY, 1);
				downEnd = i;
			}
		}
	}

	private void renderUpFlame(Graphics g)
	{
		for (int i = 1; i <= upEnd; i++)
		{
			int nextTileX = (int) (x) / Tile.getWidth();
			int nextTileY = (int) (y - i * height) / Tile.getHeight();
			if (!collisionWithTile(nextTileX, nextTileY)
					&& !collisionWithStaticEntity(nextTileX * Tile.getWidth(), nextTileY * Tile.getHeight()))
			{
				g.drawImage(getCurrentAnimationFrame(), nextTileX * Tile.getWidth(), nextTileY * Tile.getHeight(), width, height,
						null);
				checkFlameImpact(nextTileX * Tile.getWidth(), nextTileY * Tile.getHeight());
			} else
			{
				if (tileDestructible(nextTileX, nextTileY))
					handler.getMap().changeTileType(nextTileX, nextTileY, 1);
				upEnd = i;
			}
		}
	}

	private BufferedImage getCurrentAnimationFrame()
	{
		return currentAnimation.getCurrentFrame();
	}

	/**
	 * Zwraca informacje czy tile na podanej pozycji jest zniszczalny
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @return true jeœli tile jest zniszczalny, false w przeciwnym wypadku
	 */
	public boolean tileDestructible(int x, int y)
	{
		return handler.getMap().getTile(x, y).isDestructible();
	}

	private void setCurrentAnimation(Animation a)
	{
		currentAnimation = a;
	}

	private boolean collisionWithStaticEntity(float xOffset, float yOffset)
	{
		for (Entity entity : handler.getMap().getEntityManager().getEntities())
		{
			if (entity instanceof StaticEntity)
				if (entity.getCollisionBounds(0f, 0f)
						.intersects(new Rectangle((int) xOffset, (int) yOffset, Tile.getWidth(), Tile.getHeight())))
					if (entity instanceof Box)
					{
						if (entity.getCollisionBounds(0f, 0f)
								.intersects(new Rectangle((int) xOffset, (int) yOffset, Tile.getWidth(), Tile.getHeight())))
						{
							Box b = (Box) entity;
							b.destroy();
							return true;
						}
					} else
						return true;
		}
		return false;
	}

	private void checkFlameImpact(float xOffset, float yOffset)
	{
		for (Entity entity : handler.getMap().getEntityManager().getEntities())
		{
			if (!(entity instanceof Player) && !(entity instanceof Bomb))
				continue;
			if (entity.getCollisionBounds(0f, 0f)
					.intersects(new Rectangle((int) xOffset, (int) yOffset, Tile.getWidth(), Tile.getHeight())))
			{
				if (entity instanceof Player)
				{
					Player p = (Player) entity;
					if (!p.isInvulnerable() && p.isAlive())
						p.playerDied();
				} else
				{
					Bomb b = (Bomb) entity;
					b.explodeBomb();
				}
			}
		}
	}

	private void checkEntitiesInsideBounds()
	{
		for(Iterator<Entity> it = handler.getMap().getEntityManager().getEntities().iterator(); it.hasNext();)
		{
			Entity e = it.next();
			if (!(e instanceof Player))
				continue;
			if (new Rectangle((int) x, (int) y, 2 * bounds.width, 2 * bounds.height)
					.intersects(e.getCollisionBounds(0f, 0f)))
				entitiesInside.add(e);
		}
	}

	private boolean entityStillInsideBounds(Entity e)
	{
		if (!new Rectangle((int) x, (int) y, 2 * bounds.width, 2 * bounds.height)
				.intersects(e.getCollisionBounds(0f, 0f)))
		{
			entitiesInside.remove(e);
			return false;
		}
		return true;
	}

	/**
	 * @param e byt
	 * @return true jeœli dany byt znajduje siê w hitboxie bomby, false w przeciwnym wypadku
	 */
	public boolean isInside(Entity e)
	{
		if (entitiesInside.contains(e))
		{
			if (entityStillInsideBounds(e))
				return true;
		}
		return false;
	}

	/**
	 * Metoda obs³uguj¹ca zmianê stanu bomby w momencie jej eksplozji
	 */
	public void explodeBomb()
	{
		if (!exploded)
		{
			timer = 0;
			setCurrentAnimation(bombExploding);
			exploded = true;
			player.addBomb();
		}
	}

}
