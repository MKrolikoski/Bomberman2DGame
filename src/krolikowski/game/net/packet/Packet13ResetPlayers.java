package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet tworzony w momencie powrotu do lobby po zakoñczonej rozgrywce i wymaganym resecie graczy i œwiata
 *
 */

public class Packet13ResetPlayers extends Packet
{
	/**
	 * Konstruuje nowy Packet13ResetPlayers
	 * @param data dane
	 */
	public Packet13ResetPlayers(byte[] data)
	{
		super(13);
	}
	/**
	 * Konstruuje nowy Packet13ResetPlayers
	 */
	public Packet13ResetPlayers()
	{
		super(13);
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
			return ("13").getBytes("UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return new byte[0];
	}
}