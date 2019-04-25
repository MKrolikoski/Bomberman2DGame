package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet tworzony w momencie pr�by po��czenia si� z serwerem, za jego udzia�em klientowi zostaje przypisany numer Ip, je�li jaki� jest dostepny
 *
 */
public class Packet05JoinRequest extends Packet
{
	private int id;
	/**
	 * Konstruuje nowy Packet05JoinRequest i przypisuje warto�ci zmiennym odczytane z tablicy byte[]
	 * @param data dane
	 */
	public Packet05JoinRequest(byte[] data)
	{
		super(05);
		String[] dataArray = readData(data).split(":");
        this.id = Integer.parseInt(dataArray[0]);
	}
	/**
	 * Konstruuje nowy Packet05JoinRequest, przypisuj�c mu przekazane id gracza
	 * @param id id gracza
	 */
	public Packet05JoinRequest(int id)
	{
		super(05);
        this.id = id;
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
			return ("05" + getId()).getBytes("UTF-8");
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
	public int getId()
	{
		return id;
	}
	
	/**
	 * Ustawia id gracza
	 * @param id id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

}