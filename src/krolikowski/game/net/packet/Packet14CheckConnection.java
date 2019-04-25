package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet wysy³any okresowo, sprawdzaj¹cy po³¹czenia serwera z graczami i gracza z serwerem.
 *
 */

public class Packet14CheckConnection extends Packet
{
	private int playerId;
	/**
	 * Konstruuje nowy Packet14CheckConnection i przypisuje wartoœci zmiennym odczytane z tablicy byte[]
	 * @param data dane
	 */
	public Packet14CheckConnection(byte[] data)
	{
		super(14);
		String[] dataArray = readData(data).split(":");
		this.playerId = Integer.parseInt(dataArray[0]);
	}
	/**
	 * Konstruuje nowy Packet14CheckConnection, przypisuj¹c mu przekazane id gracza
	 * @param playerId id
	 */
	public Packet14CheckConnection(int playerId)
	{
		super(14);
		this.playerId = playerId;
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
			return ("14" + getPlayerId()).getBytes("UTF-8");
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
}