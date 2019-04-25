package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet tworzony w momencie wejœcia gracza do lobby
 *
 */
public class Packet06EnterLobby extends Packet
{
	private int playerId;
	/**
	 * Konstruuje nowy Packet06EnterLobby i przypisuje wartoœci zmiennym odczytane z tablicy byte[]
	 * @param data dane
	 */
	public Packet06EnterLobby(byte[] data)
	{
		super(06);
		String[] dataArray = readData(data).split(":");
        this.playerId = Integer.parseInt(dataArray[0]);
	}
	/**
	 * Konstruuje nowy Packet06EnterLobby, przypisuj¹c mu przekazane id gracza
	 * @param playerId id
	 */
	public Packet06EnterLobby(int playerId)
	{
		super(06);
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
			return ("06" + getPlayerId()).getBytes("UTF-8");
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