package krolikowski.game.entities;

import java.awt.Graphics;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import krolikowski.game.Handler;
import krolikowski.game.entities.mobs.Player;
import krolikowski.game.entities.mobs.PlayerMP;
import krolikowski.game.input.InputHandler;

/**
 * Mened¿er odpowiedzialny za obs³ugê bytów
 *
 */

public class EntityManager
{
	private List<Entity> entities;
	private static Comparator<Entity> renderOrderComparator = new Comparator<Entity>()
	{
		@Override
		public int compare(Entity a, Entity b)
		{
			if (Float.compare(a.getY() + a.getHeight(), b.getY() + b.getHeight()) == -1)
				return -1;
			else if(Float.compare(a.getY() + a.getHeight(), b.getY() + b.getHeight()) == 1)
				return 1;
			else
				return 0;
		}
	};

	/**
	 * Konstruuje nowy EntityManager i inicjalizuje listê bytów
	 */
	public EntityManager()
	{
		entities = new ArrayList<Entity>();
	}

	/**
	 * updatuje wszystkie bytu znajduj¹ce siê w liœcie entities, po czym sortuje je w zale¿noœci od iœæ po³o¿enia Y
	 */
	public void tick()
	{
		for (int i = 0; i < getEntities().size(); i++)
		{
			Entity e = getEntities().get(i);
			e.tick();
		}
		try
		{
			Collections.sort(getEntities(), renderOrderComparator);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * renderuje wszystkie bytu znajduj¹ce siê w liœcie entities
	 * @param g obiekt Graphics
	 */
	public synchronized void render(Graphics g)
	{
		for(Iterator<Entity> it = getEntities().iterator(); it.hasNext();)
		{
			Entity entity = it.next();
			entity.render(g);
		}
	}

	/**
	 * Zwraca listê bytów
	 * @return List z bytami
	 */
	public synchronized List<Entity> getEntities()
	{
		return entities;
	}

	/**
	 * dodaje byt do listy
	 * @param e byt
	 */
	public synchronized void addEntity(Entity e)
	{
		getEntities().add(e);
	}

	/**
	 * usuwa byt z listy
	 * @param e byt
	 */
	public synchronized void removeEntity(Entity e)
	{
		getEntities().remove(e);
	}

	/**
	 * @param playerId Id poszukiwanego gracza
	 * @return PlayerMP którego id zosta³o przekazane w argumencie, jeœli lista nie zawiera gracza o tym Id - null
	 */
	public PlayerMP getPlayerMP(int playerId)
	{
		for (int i = 0; i < getEntities().size(); ++i)
		{
			Entity e = getEntities().get(i);
			if (e instanceof PlayerMP)
			{
				PlayerMP p = (PlayerMP) e;
				if (p.getPlayerId() == playerId)
				{
					return p;
				}
			}
		}
		return null;
	}

	/**
	 * usuwa z listy gracza o podanym Id
	 * @param playerId Id gracza
	 */
	public synchronized void removePlayerMP(int playerId)
	{
		int i = 0;
		boolean found = false;
		for (Entity e : getEntities())
		{
			if (e instanceof PlayerMP && ((PlayerMP) e).getPlayerId() == playerId)
			{
				found = true;
				break;
			}
			i++;
		}
		if (found)
			getEntities().remove(i);
	}

	/**
	 * przesuwa gracza o podanym Id na wkazan¹ pozycjê
	 * @param playerId Id gracza
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @param movDir kierunek ruchu
	 * @param isMoving czy gracz jest w ruchu
	 */
	public synchronized void movePlayer(int playerId, float x, float y, int movDir, boolean isMoving)
	{
		/*
		 * if(playerId == handler.getGame().getPlayer().getPlayerId()) return;
		 */
		for (int i = 0; i < getEntities().size(); ++i)
		{
			Entity e = getEntities().get(i);
			if (e instanceof PlayerMP)
			{
				PlayerMP p = (PlayerMP) e;
				if (p.getPlayerId() == playerId)
				{
					p.setX(x);
					p.setY(y);
					p.setMovDir(movDir);
					p.setMoving(isMoving);
				}
			}
		}
	}
	
	/**
	 * Informuje czy gracz jest gotowy rozpocz¹æ rozgrywkê
	 * @param playerId Id gracza
	 * @return true - ready, false w przeciwnym wypadku
	 */
	public synchronized boolean getPlayerReadyStatus(int playerId)
	{
		for(int i = 0; i < getEntities().size(); ++i)
		{
			Entity e = getEntities().get(i);
			if (e instanceof PlayerMP)
			{
				PlayerMP p = (PlayerMP) e;
				if (p.getPlayerId() == playerId)
				{
					return p.isReady();
				}
			}
		}
		return false;
	}

	/**
	 * usuwa wszystkich graczy z listy bytów
	 */
	public synchronized void removePlayers()
	{
		for(int i = 0; i < getEntities().size(); ++i)
		{
			Entity e = getEntities().get(i);
			if (e instanceof Player)
			{
				getEntities().remove(i);
			}
		}
	}
	
	/**
	 * tworzy i zwraca nowego gracza z inputem
	 * @param handler Handler gry
	 * @param ipAddress adress Ip klienta
	 * @param port port serwera
	 * @param playerId Id gracza
	 * @param input input
	 * @param ready gotowoœæ do rozpoczêcia rozgrywki
	 * @return PlayerMP lub null jeœli gracz o podanym Ip ju¿ istnieje
	 */
	public synchronized PlayerMP createNewPlayer(Handler handler, InetAddress ipAddress, int port, int playerId, InputHandler input, boolean ready)
	{
		if(!idTaken(playerId))
		{
			return new PlayerMP(handler, ipAddress, port, playerId, input, ready);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * tworzy i zwraca nowego gracza bez inputu
	 * @param handler Handler gry
	 * @param ipAddress adress Ip klienta
	 * @param port port serwera
	 * @param playerId Id gracza
	 * @param ready gotowoœæ do rozpoczêcia rozgrywki
	 * @return PlayerMP lub null jeœli gracz o podanym Ip ju¿ istnieje
	 */
	public synchronized PlayerMP createNewPlayer(Handler handler, InetAddress ipAddress, int port, int playerId, boolean ready)
	{
		if(!idTaken(playerId))
		{
			return new PlayerMP(handler, ipAddress, port, playerId, ready);
		}
		else
		{
			return null;
		}
	}

	/**
	 * sprawdza czy podane Id jest ju¿ w u¿yciu
	 * @param playerId Id gracza
	 * @return true Id ju¿ zajête, false Id wolne
	 */
	private boolean idTaken(int playerId)
	{
		for(int i = 0; i < getEntities().size(); ++i)
		{
			Entity e = getEntities().get(i);
			if (e instanceof Player)
			{
				if(((Player) e).getPlayerId() == playerId)
					return true;
			}
		}
		return false;
	}
}
