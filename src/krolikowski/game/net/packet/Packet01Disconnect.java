package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet tworzony w momencie roz³¹czenia siê z serwerem
 *
 */
public class Packet01Disconnect extends Packet
{

	private int playerId;

	/**
	 * Konstruuje nowy Packet01Disconnect i przypisuje wartoœci zmiennym odczytane z tablicy byte[]
	 * @param data dane
	 */
	public Packet01Disconnect(byte[] data)
	{
		super(01);
		this.playerId = Integer.parseInt(readData(data));
	}

	/**
	 * Konstruuje nowy Packet01Disconnect i przypisuje mu przekazane id gracza
	 * @param playerId id
	 */
	public Packet01Disconnect(int playerId)
	{
		super(01);
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
			return ("01" + getPlayerId()).getBytes("UTF-8");
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