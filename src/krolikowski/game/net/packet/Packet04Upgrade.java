package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet tworzony w momencie wypadni�cia ulepszenia ze zniszczonej skrzyni
 *
 */

public class Packet04Upgrade extends Packet
{

	private float x, y;
	private int upgradeType;
	/**
	 * Konstruuje nowy Packet04Upgrade i przypisuje warto�ci zmiennym odczytane z tablicy byte[]
	 * @param data dane
	 */
	public Packet04Upgrade(byte[] data)
	{
		super(04);
		String[] dataArray = readData(data).split(":");
		this.x = Float.parseFloat(dataArray[0]);
		this.y = Float.parseFloat(dataArray[1]);
		this.upgradeType = Integer.parseInt(dataArray[2]);
	}

	/**
	 * Konstruuje nowy Packet04Upgrade, przypisuj�c mu przekazane koordynaty x,y oraz typ ulepszenia
	 * @param x po�o�enie x
	 * @param y po�o�enie y
	 * @param upgradeType typ ulepszenia
	 */
	public Packet04Upgrade(float x, float y, int upgradeType)
	{
		super(04);
		this.x = x;
		this.y = y;
		this.upgradeType = upgradeType;
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
			return ("04" + getX() + ":" + getY() + ":" + getUpgradeType()).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return new byte[0];
	}

	/**
	 * Zwraca typ ulepszenia
	 * @return typ ulepszenia
	 */
	public int getUpgradeType()
	{
		return upgradeType;
	}

	/**
	 * Zwraca po�o�enie x ulepszenia
	 * @return po�o�enie x
	 */
	public float getX()
	{
		return x;
	}

	/**
	 * Zwraca po�o�enie y ulepszenia
	 * @return po�o�enie y
	 */
	public float getY()
	{
		return y;
	}

}