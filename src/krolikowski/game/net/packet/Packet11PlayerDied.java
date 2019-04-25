package krolikowski.game.net.packet;

import java.io.UnsupportedEncodingException;

import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;

/**
 * Pakiet wysy³any gdy któryœ z graczy umiera
 *
 */

public class Packet11PlayerDied extends Packet {

    private int playerId;
	/**
	 * Konstruuje nowy Packet11PlayerDied i przypisuje wartoœci zmiennym odczytane z tablicy byte[]
	 * @param data dane
	 */
    public Packet11PlayerDied(byte[] data) {
        super(11);
        String[] dataArray = readData(data).split(":");
        this.playerId = Integer.parseInt(dataArray[0]);
    }

	/**
	 * Konstruuje nowy Packet11PlayerDied, przypisuj¹c mu przekazane id gracza
	 * @param playerId id
	 */
    public Packet11PlayerDied(int playerId) {
        super(11);
        this.playerId = playerId;
    }

    @Override
    public void writeData(GameClient client) {
        client.sendData(getData());
    }

    @Override
    public void writeData(GameServer server) {
        server.sendDataToAllClients(getData());
    }

    @Override
    public byte[] getData() {
        try
		{
			return ("11" + getPlayerId()).getBytes("UTF-8");
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