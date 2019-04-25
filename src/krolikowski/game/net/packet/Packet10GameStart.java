package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet wysy³any przez serwer w momencie startu rozgrywki
 *
 */

public class Packet10GameStart extends Packet
{
	/**
	 * Konstruuje nowy Packet10GameStart
	 * @param data dane
	 */
	public Packet10GameStart(byte[] data)
	{
		super(10);
	}

	/**
	 * Konstruuje nowy Packet10GameStart
	 */
	public Packet10GameStart()
	{
		super(10);
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
			return ("10").getBytes("UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return new byte[0];
	}
}