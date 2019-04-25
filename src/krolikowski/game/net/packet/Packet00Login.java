package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet tworzony podczas do³¹czania do serwera
 *
 */
public class Packet00Login extends Packet
{

	private int playerId;
	private boolean ready;

	/**
	 * Konstruuje nowy Packet00Login i przypisuje wartoœci zmiennym odczytane z tablicy byte[]
	 * @param data dane
	 */
	public Packet00Login(byte[] data)
	{
		super(00);
		String[] dataArray = readData(data).split(":");
		this.playerId = Integer.parseInt(dataArray[0]);
		this.ready = Integer.parseInt(dataArray[1]) == 1;
	}

	/**
	 * Konstruuje nowy Packet00Login i przypisuje mu przekazane id gracza i status gotowoœci
	 * @param playerId id
	 * @param ready status gotowoœci
	 */
	public Packet00Login(int playerId, boolean ready)
	{
		super(00);
		this.playerId = playerId;
		this.ready = ready;
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
			return ("00" + getPlayerId() + ":" + (isReady() ? 1 : 0)).getBytes("UTF-8");
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
	 * Zwraca status gotowoœci gracza
	 * @return true jeœli gotowy, false w przeciwnym wypadku
	 */
	public boolean isReady()
	{
		return ready;
	}

}