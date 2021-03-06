package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet tworzony w momencie wypadnięcia ulepszenia ze zniszczonej skrzyni
 *
 */

public class Packet04Upgrade extends Packet
{

	private float x, y;
	private int upgradeType;
	/**
	 * Konstruuje nowy Packet04Upgrade i przypisuje wartości zmiennym odczytane z tablicy byte[]
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
	 * Konstruuje nowy Packet04Upgrade, przypisując mu przekazane koordynaty x,y oraz typ ulepszenia
	 * @param x położenie x
	 * @param y położenie y
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
	 * Zwraca położenie x ulepszenia
	 * @return położenie x
	 */
	public float getX()
	{
		return x;
	}

	/**
	 * Zwraca położenie y ulepszenia
	 * @return położenie y
	 */
	public float getY()
	{
		return y;
	}

}