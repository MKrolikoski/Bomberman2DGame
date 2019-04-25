package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet tworzony w momencie zmiany pozycji gracza
 *
 */

public class Packet02Move extends Packet
{

	private int playerId;
	private float x, y;
	private int movDir;
	private boolean isMoving;

	/**
	 * Konstruuje nowy Packet02Move i przypisuje wartoœci zmiennym odczytane z tablicy byte[]
	 * @param data dane
	 */
	public Packet02Move(byte[] data)
	{
		super(02);
		String[] dataArray = readData(data).split(":");
		this.playerId = Integer.parseInt(dataArray[0]);
		this.x = Float.parseFloat(dataArray[1]);
		this.y = Float.parseFloat(dataArray[2]);
		this.movDir = Integer.parseInt(dataArray[3]);
		this.isMoving = Integer.parseInt(dataArray[4]) == 1;
	}

	/**
	 * Konstruuje nowy packet02Move, przypisuj¹c mu przekazane id gracza, koordynaty x,y, kierunek ruchu i informacje o tym czy gracz siê porusza
	 * @param playerId id gracza
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @param movDir kierunek ruchu
	 * @param isMoving czy gracz siê porusza
	 */
	public Packet02Move(int playerId, float x, float y, int movDir, boolean isMoving)
	{
		super(02);
		this.playerId = playerId;
		this.x = x;
		this.y = y;
		this.movDir = movDir;
		this.isMoving = isMoving;
	}

	@Override
	public void writeData(GameClient client)
	{
		client.sendData(getData());
	}

	@Override
	public void writeData(GameServer server)
	{
		server.sendDataToAllClients(getData());
	}

	@Override
	public byte[] getData()
	{
		try
		{
			return ("02" + getPlayerId() + ":" + getX() + ":" + getY() + ":" + getMovDir() + ":"
					+ (isMoving ? 1 : 0)).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return new byte[0];
	}

	/**
	 * Zwraca id gracza
	 * @return id
	 */
	public int getPlayerId()
	{
		return playerId;
	}

	/**
	 * Zwraca po³o¿enie x gracza
	 * @return po³o¿enie x
	 */
	public float getX()
	{
		return x;
	}
	/**
	 * Zwraca po³o¿enie y gracza
	 * @return po³o¿enie y
	 */
	public float getY()
	{
		return y;
	}

	/**
	 * Zwraca kierunek ruchu gracza (2 do góry, -2 do do³u, 1 w prawo, -1 w lewo, 0 nie porusza siê)
	 * @return kierunek ruchu
	 */
	public int getMovDir()
	{
		return movDir;
	}

	/**
	 * Zwraca informacje czy gracz jest w fazie ruchu
	 * @return true jeœli gracz porusza siê, false w przeciwnym wypadku
	 */
	public boolean isMoving()
	{
		return isMoving;
	}

}