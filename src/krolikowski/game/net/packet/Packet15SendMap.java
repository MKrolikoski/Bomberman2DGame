package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet wysy³aj¹cy mapê graczom wchodz¹cym do lobby
 *
 */

public class Packet15SendMap extends Packet
{
	private String map;
	/**
	 * Konstruuje nowy Packet15SendMap i przypisuje wartoœci zmiennym odczytane z tablicy byte[]
	 * @param data dane
	 */
	public Packet15SendMap(byte[] data)
	{
		super(15);
		this.map = readData(data);
	}
	/**
	 * Konstruuje nowy Packet15SendMap, przypisuj¹c mu przekazan¹ mapê w postaci String
	 * @param map String z informacjami o mapie
	 */
	public Packet15SendMap(String map)
	{
		super(15);
		this.map = map;
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
			return ("15" + getMap()).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return new byte[0];
	}

	/**
	 * Zwraca mapê gry
	 * @return String z informacjami o mapie
	 */
	public String getMap()
	{
		return map;
	}
}