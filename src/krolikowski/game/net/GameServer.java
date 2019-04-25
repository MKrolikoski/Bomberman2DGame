package krolikowski.game.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import krolikowski.game.Handler;
import krolikowski.game.entities.mobs.PlayerMP;
import krolikowski.game.net.packet.Packet;
import krolikowski.game.net.packet.Packet.PacketTypes;
import krolikowski.game.net.packet.Packet00Login;
import krolikowski.game.net.packet.Packet01Disconnect;
import krolikowski.game.net.packet.Packet02Move;
import krolikowski.game.net.packet.Packet03Bomb;
import krolikowski.game.net.packet.Packet04Upgrade;
import krolikowski.game.net.packet.Packet05JoinRequest;
import krolikowski.game.net.packet.Packet06EnterLobby;
import krolikowski.game.net.packet.Packet07ReadyStatus;
import krolikowski.game.net.packet.Packet10GameStart;
import krolikowski.game.net.packet.Packet11PlayerDied;
import krolikowski.game.net.packet.Packet12GameEnd;
import krolikowski.game.net.packet.Packet13ResetPlayers;
import krolikowski.game.net.packet.Packet14CheckConnection;
import krolikowski.game.net.packet.Packet15SendMap;

/**
 * Serwer gry
 */
public class GameServer extends Thread
{
	private DatagramSocket socket;
	private Handler handler;
	private List<PlayerMP> connectedPlayers;
	private boolean gameStarted;
	private long lastTime, timer;
	private Map<Integer, Boolean> playerResponse;

	/**
	 * Konstruuje nowy GameServer, przypisuj¹c mu przekazany Handler
	 * @param handler Handler
	 */
	public GameServer(Handler handler)
	{
		connectedPlayers = new ArrayList<PlayerMP>();
		playerResponse = new HashMap<Integer, Boolean>();
		this.handler = handler;
		try
		{
			this.socket = new DatagramSocket(null);
			socket.setReuseAddress(true);
			socket.bind(new InetSocketAddress(handler.getGame().getMyIp(), 4444));
		} catch (SocketException e)
		{
			e.printStackTrace();
		}
		gameStarted = false;
	}
	
	/**
	 * odbiera i odpowiednio obs³uguje pakiety wysy³ane przez pod³¹czonych klientów
	 */
	public void run()
	{
		while (true)
		{
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try
			{
				socket.receive(packet);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}

	private void parsePacket(byte[] data, InetAddress address, int port)
	{
		String message;
		try
		{
			message = new String(data, "UTF-8").trim();
			PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
			Packet packet = null;
			switch (type)
			{
			default:
			case INVALID:
				break;
			case LOGIN:
				packet = new Packet00Login(data);
				PlayerMP player = new PlayerMP(handler, address, port, ((Packet00Login) packet).getPlayerId(),
						((Packet00Login) packet).isReady());
				addConnection(player, (Packet00Login) packet);
				break;
			case DISCONNECT:
				packet = new Packet01Disconnect(data);
				handleDisconnect((Packet01Disconnect) packet);
				checkGameEndConditions();
				break;
			case MOVE:
				packet = new Packet02Move(data);
				handleMove(((Packet02Move) packet));
				break;
			case BOMB:
				packet = new Packet03Bomb(data);
				handleBomb(((Packet03Bomb) packet));
				break;
			case UPGRADE:
				packet = new Packet04Upgrade(data);
				handleUpgrade(((Packet04Upgrade) packet));
				break;
			case JOINREQUEST:
				packet = new Packet05JoinRequest(data);
				handleRequest(((Packet05JoinRequest) packet), address, port);
				break;
			case ENTERLOBBY:
				packet = new Packet06EnterLobby(data);
				player = getPlayerMP(((Packet06EnterLobby) packet).getPlayerId());
				handleEnterLobby(player, ((Packet06EnterLobby) packet));
				break;
			case READYSTATUS:
				packet = new Packet07ReadyStatus(data);
				handleReadyStatusChange(((Packet07ReadyStatus) packet));
				break;
			case PLAYERDIED:
				packet = new Packet11PlayerDied(data);
				handlePlayerDead(((Packet11PlayerDied) packet));
				break;
			case RESETPLAYERS:
				packet = new Packet13ResetPlayers(data);
				handlePlayersReset(((Packet13ResetPlayers) packet));
				break;
			case CHECKCONNECTION:
				packet = new Packet14CheckConnection(data);
				handleClientConnectionCheck(((Packet14CheckConnection) packet), address, port);
				break;
			}
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}

	private void handleClientConnectionCheck(Packet14CheckConnection packet, InetAddress address, int port)
	{
		sendData(packet.getData(), address, port);
		playerResponse.put(packet.getPlayerId(), true);
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if (timer >= 5000)
		{
			timer = 0;
			checkConnection();
		}
	}

	private void checkConnection()
	{
		for (Map.Entry<Integer, Boolean> entry : playerResponse.entrySet())
		{
			int key = entry.getKey();
			boolean value = entry.getValue();
			if (!value)
			{
				
				Packet01Disconnect packet = new Packet01Disconnect(key);
				packet.writeData(handler.getGame().getSocketClient());
			} else
			{
				playerResponse.put(key, false);
			}
		}
	}

	private void handlePlayersReset(Packet13ResetPlayers packet)
	{
		gameStarted = false;
		sendDataToAllClients(packet.getData());
		if (!getConnectedPlayers().isEmpty())
			for (int i = getConnectedPlayers().size() - 1; i >= 0; i--)
			{
				getConnectedPlayers().remove(i);
			}

	}

	private void handlePlayerDead(Packet11PlayerDied packet)
	{
		PlayerMP player = getPlayerMP(packet.getPlayerId());
		if (player != null)
			player.playerDied();
		checkGameEndConditions();
	}

	private void checkGameEndConditions()
	{
		int alivePlayersNumber = getAlivePlayersNumber();
		if ((alivePlayersNumber == 1 || alivePlayersNumber == 0) && gameStarted)
		{
			Packet12GameEnd pac = new Packet12GameEnd(getAlivePlayerId());
			sendDataToAllClients(pac.getData());
		}
	}

	private int getAlivePlayerId()
	{
		for (PlayerMP p : getConnectedPlayers())
		{
			if (p.isAlive())
			{
				return p.getPlayerId();
			}
		}
		return -1;
	}

	private int getAlivePlayersNumber()
	{
		{
			int alivePlayersNumber = 0;
			for (PlayerMP p : getConnectedPlayers())
			{
				if (p.isAlive())
				{
					alivePlayersNumber++;
				}
			}
			return alivePlayersNumber;
		}
	}

	private void handleReadyStatusChange(Packet07ReadyStatus packet)
	{
		for (PlayerMP p : connectedPlayers)
		{
			if (p.getPlayerId() == packet.getPlayerId())
			{
				p.setReady(packet.isReady());
				continue;
			}
			sendData(packet.getData(), p.getIpAddress(), p.getPort());
		}
		if (checkPlayersReadyStatus())
		{
			gameStarted = true;
			Packet10GameStart pac = new Packet10GameStart();
			sendDataToAllClients(pac.getData());
		}
	}

	private boolean checkPlayersReadyStatus()
	{
		boolean ready = true;
		for (PlayerMP p : getConnectedPlayers())
		{
			if (!p.isReady())
			{
				ready = false;
				break;
			}
		}
		return ready;
	}

	private void handleEnterLobby(PlayerMP player, Packet06EnterLobby packet)
	{
		Packet06EnterLobby packetTmp = packet;
		for (PlayerMP p : getConnectedPlayers())
		{
			if (p.getPlayerId() == player.getPlayerId())
				continue;
			sendData(packet.getData(), p.getIpAddress(), p.getPort());
			packet = new Packet06EnterLobby(p.getPlayerId());
			sendData(packet.getData(), player.getIpAddress(), player.getPort());
			packet = packetTmp;
		}
	}

	private void handleRequest(Packet05JoinRequest packet, InetAddress address, int port)
	{
		int id = getFreePlayerId();
		packet.setId(id);
		Packet15SendMap p = new Packet15SendMap(handler.getGame().getMap());
		sendData(p.getData(), address, port);
		sendData(packet.getData(), address, port);
	}

	private void handleUpgrade(Packet04Upgrade packet)
	{
		if (gameStarted)
		{
			for (PlayerMP p : getConnectedPlayers())
			{
				sendData(packet.getData(), p.getIpAddress(), p.getPort());
			}
		}
	}

	private void handleBomb(Packet03Bomb packet)
	{
		if (gameStarted)
		{
			for (PlayerMP p : getConnectedPlayers())
			{
				if (p.getPlayerId() == packet.getPlayerId())
					continue;
				sendData(packet.getData(), p.getIpAddress(), p.getPort());
			}
		}
	}

	private void handleMove(Packet02Move packet)
	{
		if (gameStarted && getPlayerMP(packet.getPlayerId()) != null)
		{
			int i = getPlayerMPIndex(packet.getPlayerId());
			PlayerMP player = getConnectedPlayers().get(i);
			player.setX(packet.getX() * handler.getGame().getScale());
			player.setY(packet.getY() * handler.getGame().getScale());
			player.setMovDir(packet.getMovDir());
			player.setMoving(packet.isMoving());
			for (PlayerMP p : getConnectedPlayers())
			{
				if (p.getPlayerId() == player.getPlayerId())
					continue;
				sendData(packet.getData(), p.getIpAddress(), p.getPort());
			}
		}
	}

	private PlayerMP getPlayerMP(int playerId)
	{
		for (PlayerMP p : getConnectedPlayers())
		{
			if (p.getPlayerId() == playerId)
				return p;
		}
		return null;
	}

	private void handleDisconnect(Packet01Disconnect packet)
	{
		int playerIndex = getPlayerMPIndex(packet.getPlayerId());
		if(playerIndex != -1 && (playerIndex < getConnectedPlayers().size()))
			getConnectedPlayers().remove(playerIndex);
		playerResponse.remove(packet.getPlayerId());
		packet.writeData(this);
		if (packet.getPlayerId() == 1)
		{
			gameStarted = false;
			for(int i = getConnectedPlayers().size()-1; i >= 0; --i)
			{
				PlayerMP player = getConnectedPlayers().get(i);
				getConnectedPlayers().remove(player);
			}
		}
	}

	private int getPlayerMPIndex(int playerId)
	{
		for (int i = 0; i < getConnectedPlayers().size(); ++i)
		{
			if (getConnectedPlayers().get(i).getPlayerId() == playerId)
				return i;
		}
		return -1;
	}

	/**
	 * Dodaje nowego gracza do serwera na podstawie danych z otrzymanego pakietu
	 * i informuje o tym graczy ju¿ obecnych na serwerze
	 * 
	 * @param player
	 *            nowy gracz
	 * @param packet
	 *            pakiet Login
	 */
	public synchronized void addConnection(PlayerMP player, Packet00Login packet)
	{
		boolean alreadyConnected = false;
		Packet00Login packetTmp = packet;
		for (PlayerMP p : getConnectedPlayers())
		{
			if (player.getPlayerId() == p.getPlayerId())
			{
				if (p.getIpAddress() == null)
				{
					p.setIpAddress(player.getIpAddress());
				}
				if (p.getPort() == -1)
				{
					p.setPort(player.getPort());
				}
				alreadyConnected = true;
			} else
			{
				sendData(packet.getData(), p.getIpAddress(), p.getPort());

				packet = new Packet00Login(p.getPlayerId(), p.isReady());
				sendData(packet.getData(), player.getIpAddress(), player.getPort());
			}
			packet = packetTmp;
		}
		if (!alreadyConnected)
		{
			this.getConnectedPlayers().add(player);
			playerResponse.put(packet.getPlayerId(), true);
		}
	}

	private void sendData(byte[] data, InetAddress ipAddress, int port)
	{
		if (data != null)
		{
			DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
			try
			{
				this.socket.send(packet);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * wysy³a pakiet z danymi to wszystkich klientów po³¹czonych z serwerem
	 * 
	 * @param data
	 *            dane do wys³ania
	 */
	public void sendDataToAllClients(byte[] data)
	{
		if (data != null)
			for (PlayerMP p : getConnectedPlayers())
			{
				sendData(data, p.getIpAddress(), p.getPort());
			}
	}

	private synchronized int getFreePlayerId()
	{
		if (gameStarted)
			return -2;
		for (int i = 1; i < 5; ++i)
		{
			boolean idTaken = false;
			for (PlayerMP p : getConnectedPlayers())
			{
				if (p.getPlayerId() == i)
				{
					idTaken = true;
					break;
				}
			}
			if (!idTaken)
			{
				if (i == 1)
					return 0;
				else
					return i;
			}
		}
		return -1;
	}

	private synchronized List<PlayerMP> getConnectedPlayers()
	{
		return connectedPlayers;
	}
}
