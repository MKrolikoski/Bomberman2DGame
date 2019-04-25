package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet wysy³any przez serwer  w zakoñczenia siê rozgrywki
 *
 */

public class Packet12GameEnd extends Packet
{

	private int playerId;

	/**
	 * Konstruuje nowy Packet12GameEnd i przypisuje wartoœci zmiennym odczytane z tablicy byte[]
	 * @param data dane
	 */
	public Packet12GameEnd(byte[] data)
	{
		super(12);
		String[] dataArray = readData(data).split(":");
		this.playerId = Integer.parseInt(dataArray[0]);
	}
	/**
	 * Konstruuje nowy Packet12GameEnd, przypisuj¹c mu przekazane id gracza
	 * @param playerId id
	 */
	public Packet12GameEnd(int playerId)
	{
		super(12);
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
			return ("12" + getPlayerId()).getBytes("UTF-8");
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