package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Klasa abstrakcyjna odpowiedzialna za utworzenie pakietu z danymi
 *
 */
public abstract class Packet
{
	/**
	 * typ wyliczeniowy okreœlaj¹cy typ pakietu
	 *
	 */
	public static enum PacketTypes
	{
		INVALID(-1), LOGIN(00), DISCONNECT(01), MOVE(02), BOMB(03), UPGRADE(04), JOINREQUEST(05), ENTERLOBBY(06), READYSTATUS(07),
		GAMESTART(10), PLAYERDIED(11), GAMEEND(12), RESETPLAYERS(13), CHECKCONNECTION(14), SENDMAP(15);

		private int packetId;

		private PacketTypes(int packetId)
		{
			this.packetId = packetId;
		}

		/**
		 * Zwraca id typu pakietu
		 * @return id
		 */
		public int getId()
		{
			return packetId;
		}
	}

	private byte packetId;

	public Packet(int packetId)
	{
		this.packetId = (byte) packetId;
	}

	/**
	 * wysy³a pakiet na serwer od okreœlonego klienta
	 * @param client klient
	 */
	public abstract void writeData(GameClient client);

	/**
	 * wysy³a pakiet do wszystkich po³¹czonych klientów z okreœlonym serwerem
	 * @param server serwer
	 */
	public abstract void writeData(GameServer server);

	/**
	 * @param data dane pakietu
	 * @return wiadomoœæ z pakietu z pominiêciem jego typu lub null
	 */
	public String readData(byte[] data)
	{
		String message;
		try
		{
			message = new String(data, "UTF-8").trim();
			return message.substring(2);
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Zwraca informacje przekazywan¹ przez pakiet
	 * @return tablica byte[]
	 */
	public abstract byte[] getData();

	/**
	 * Zwraca typ pakietu w zale¿noœci od przekezanego numeru w postaci napisu
	 * @param packetId String z id pakietu
	 * @return typ pakietu
	 */
	public static PacketTypes lookupPacket(String packetId)
	{
		try
		{
			return lookupPacket(Integer.parseInt(packetId));
		} catch (NumberFormatException e)
		{
			return PacketTypes.INVALID;
		}
	}

	/**
	 * Zwraca typ pakietu w zale¿noœci od przekezanego numeru
	 * @param id id pakietu
	 * @return typ pakietu
	 */
	public static PacketTypes lookupPacket(int id)
	{
		for (PacketTypes p : PacketTypes.values())
		{
			if (p.getId() == id)
			{
				return p;
			}
		}
		return PacketTypes.INVALID;
	}
	
	/**
	 * Zwraca id pakietu
	 * @return id
	 */
	public byte getPacketId()
	{
		return packetId;
	}
}
