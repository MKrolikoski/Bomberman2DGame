package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet tworzony w momencie utworzenia bomby
 *
 */
public class Packet03Bomb extends Packet
{

	private int playerId;
	private float x, y;
	/**
	 * Konstruuje nowy Packet03Bomb i przypisuje wartoœci zmiennym odczytane z tablicy byte[]
	 * @param data dane
	 */
	public Packet03Bomb(byte[] data)
	{
		super(03);
		String[] dataArray = readData(data).split(":");
		this.playerId = Integer.parseInt(dataArray[0]);
		this.x = Float.parseFloat(dataArray[1]);
		this.y = Float.parseFloat(dataArray[2]);
	}
	/**
	 * Konstruuje nowy Packet03Bomb, przypisuj¹c mu przekazane id gracza oraz koordynaty x,y
	 * @param playerId id gracza
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 */
	public Packet03Bomb(int playerId, float x, float y)
	{
		super(03);
		this.playerId = playerId;
		this.x = x;
		this.y = y;
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
			return ("03" + getPlayerId() + ":" + getX() + ":" + getY()).getBytes("UTF-8");
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
	 * Zwraca po³o¿enie x bomby
	 * @return po³o¿enie x
	 */
	public float getX()
	{
		return x;
	}
	/**
	 * Zwraca po³o¿enie y bomby
	 * @return po³o¿enie y
	 */
	public float getY()
	{
		return y;
	}

}