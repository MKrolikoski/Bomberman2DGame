package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet tworzony w momencie zmiany statusu gotowœci klienta w lobby
 *
 */
public class Packet07ReadyStatus extends Packet
{

	private int playerId;
	private boolean ready;
	/**
	 * Konstruuje nowy Packet07ReadyStatus i przypisuje wartoœci zmiennym odczytane z tablicy byte[]
	 * @param data dane
	 */
	public Packet07ReadyStatus(byte[] data)
	{
		super(07);
		String[] dataArray = readData(data).split(":");
		this.playerId = Integer.parseInt(dataArray[0]);
		this.ready = Integer.parseInt(dataArray[1]) == 1;
	}

	/**
	 * Konstruuje nowy Packet07ReadyStatus, przypisuj¹c mu przekazane id gracza i jego status gotowoœci
	 * @param playerId id
	 * @param ready status gotowoœci
	 */
	public Packet07ReadyStatus(int playerId, boolean ready)
	{
		super(07);
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
			return ("07" + getPlayerId() + ":" + (isReady() ? 1 : 0)).getBytes("UTF-8");
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