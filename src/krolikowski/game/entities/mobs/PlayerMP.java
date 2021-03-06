package krolikowski.game.entities.mobs;

import java.net.InetAddress;

import krolikowski.game.Handler;
import krolikowski.game.input.InputHandler;

/**
 * Klasa reprezentująca gracza do rozgrywki sieciowej
 *
 */
public class PlayerMP extends Player
{
	private InetAddress ipAddress;
    private int port;
	private boolean ready;
    
	/**
	 * Konstruuje obiekt PlayerMP, przypisując mu przekazany Handler, adres IPv4, port, id gracza, obiekt pobierający input i status gotowości do rozpoczęcia rozgrywki
	 * @param handler Handler
	 * @param ipAddress adres IPv4
	 * @param port port
	 * @param playerId id gracza
	 * @param input obiekt obsługujący input gracza
	 * @param ready status gotowości
	 */
	public PlayerMP(Handler handler, InetAddress ipAddress, int port, int playerId, InputHandler input, boolean ready)
	{
		super(handler, playerId, input);
		this.ipAddress = ipAddress;
		this.port = port;
		this.ready = ready;
	}
	/**
	 * Konstruuje obiekt PlayerMP, przypisując mu przekazany Handler, adres IPv4, port, id gracza i status gotowości do rozpoczęcia rozgrywki
	 * @param handler Handler
	 * @param ipAddress adres IPv4
	 * @param port port
	 * @param playerId id gracza
	 * @param ready status gotowości
	 */
	public PlayerMP(Handler handler, InetAddress ipAddress, int port, int playerId, boolean ready)
	{
		super(handler, playerId, null);
		this.ipAddress = ipAddress;
		this.port = port;
		this.ready = ready;
	}
	
	/**
	 * Zwraca adres IPv4 gracza
	 * @return adres IPv4
	 */
	public InetAddress getIpAddress()
	{
		return ipAddress;
	}
	
	/**
	 * Ustawia adres IPv4 gracza
	 * @param ipAddress adres IPv4
	 */
	public void setIpAddress(InetAddress ipAddress)
	{
		this.ipAddress = ipAddress;
	}
	
	/**
	 * Zwraca port gracza
	 * @return numer portu
	 */
	public int getPort()
	{
		return port;
	}
	
	/**
	 * Ustawia port gracza
	 * @param port numer portu
	 */
	public void setPort(int port)
	{
		this.port = port;
	}
	
	/**
	 * Ustawia status gotowości gracza do rozpoczęcia rozgrywki
	 * @param ready status gotowości gracza
	 */
	public void setReady(boolean ready)
	{
		this.ready = ready;
	}
	/**
	 * Zwraca status gotowości gracza do rozpoczęcia rozgrywki
	 * @return true jeśli gotowy, false w przeciwnym wypadku
	 */
	public boolean isReady()
	{
		return ready;
	}
	
	/**
	 * resetuje gracza, przywracając wartości zmiennych do domyślnych
	 */
	public void resetPlayer()
	{
		init();
		ready = false;
	}

}
