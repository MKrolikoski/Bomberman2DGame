package krolikowski.game.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import krolikowski.game.Handler;
import krolikowski.game.entities.items.Bomb;
import krolikowski.game.entities.items.upgrades.BombUpgrade;
import krolikowski.game.entities.items.upgrades.PowerUpgrade;
import krolikowski.game.entities.items.upgrades.SpeedUpgrade;
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
import krolikowski.game.net.packet.Packet12GameEnd;
import krolikowski.game.net.packet.Packet13ResetPlayers;
import krolikowski.game.net.packet.Packet15SendMap;
import krolikowski.game.tiles.Tile;
import krolikowski.game.ui.objects.PlayerStatus;
import krolikowski.game.ui.objects.UIObject;

/**
 * Klient gry
 *
 */

public class GameClient extends Thread
{
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Handler handler;
	private String hostResponse;

	/**
	 * Zwraca odpowiedü serwera
	 * @return odpowiedü serwera
	 */
	public String getHostResponse()
	{
		return hostResponse;
	}

	/**
	 * Ustawia odpowiedü serwera
	 * @param hostResponse odpowiedü serwera
	 */
	public void setHostResponse(String hostResponse)
	{
		this.hostResponse = hostResponse;
	}

	/**
	 * Konstruuje nowy GameClient, przypisujπc mu przekazany Handler i adres IP serwera
	 * @param handler Handler
	 * @param ipAddress adres IP serwera
	 * @throws UnknownHostException jeúli podany adres IP serwera by≥ b≥Ídny
	 */
	public GameClient(Handler handler, String ipAddress) throws UnknownHostException
	{
		this.handler = handler;
		this.hostResponse = "PONG";
		try
		{
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (SocketException e)
		{
			e.printStackTrace();
		} catch (UnknownHostException e)
		{
			throw e;
		}
	}

	/**
	 * odbiera i odpowiednio obs≥uguje pakiety otrzymane z serwera
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
		try
		{
			String message;
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
				handleLogin((Packet00Login) packet, address, port);
				break;
			case DISCONNECT:
				packet = new Packet01Disconnect(data);
				handleDisconnect(((Packet01Disconnect) packet));
				break;
			case MOVE:
				packet = new Packet02Move(data);
				handleMove((Packet02Move) packet);
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
				handleRequest(((Packet05JoinRequest) packet));
				break;
			case ENTERLOBBY:
				packet = new Packet06EnterLobby(data);
				handleEnterLobby(((Packet06EnterLobby) packet));
				break;
			case READYSTATUS:
				packet = new Packet07ReadyStatus(data);
				handleReadyStatusChange(((Packet07ReadyStatus) packet));
				break;
			case GAMESTART:
				handleGameStart();
				break;
			case GAMEEND:
				packet = new Packet12GameEnd(data);
				handleGameEnd(((Packet12GameEnd) packet));
				break;
			case RESETPLAYERS:
				packet = new Packet13ResetPlayers(data);
				handlePlayersReset(((Packet13ResetPlayers) packet));
				break;
			case CHECKCONNECTION:
				handleHostResponse();
				break;
			case SENDMAP:
				packet = new Packet15SendMap(data);
				handleSentMap(((Packet15SendMap) packet));
				break;
			}
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}


	private void handleSentMap(Packet15SendMap packet)
	{
		handler.getGame().setMap(packet.getMap());		
	}

	private void handleHostResponse()
	{
		hostResponse = "PONG";
	}

	private void handlePlayersReset(Packet13ResetPlayers packet)
	{
		handler.getGame().setGameStatus(2);
		handler.getGame().getPlayer().resetPlayer();
		handler.getGame().goToLobby();
	}

	private void handleGameEnd(Packet12GameEnd packet)
	{
		PlayerMP winner = handler.getMap().getEntityManager().getPlayerMP(packet.getPlayerId());
		if (winner != null && winner.isAlive())
		{
			winner.playerWon();
			handler.getGame().setGameStatus(-1);
		} else
		{
			handler.getGame().setGameStatus(0);
		}
	}

	private void handleGameStart()
	{
		handler.getGame().getPlayer().setReady(false);
		handler.getGame().startGame();
	}

	private void handleReadyStatusChange(Packet07ReadyStatus packet)
	{
		PlayerMP player = handler.getMap().getEntityManager().getPlayerMP(packet.getPlayerId());
		player.setReady(packet.isReady());
	}

	private void handleEnterLobby(Packet06EnterLobby packet)
	{
		handler.getLobbyManager().addObject(new PlayerStatus(handler, packet.getPlayerId()));
	}

	private void handleDisconnect(Packet01Disconnect packet)
	{
		if (packet.getPlayerId() == 1) // host left
		{
			handler.getGame().goToMenu();
		}
		handler.getMap().getEntityManager().removePlayerMP(packet.getPlayerId());
		for (int i = 0; i < handler.getLobbyManager().getUIObjects().size(); ++i)
		{
			UIObject o = handler.getLobbyManager().getUIObjects().get(i);
			if (o instanceof PlayerStatus)
			{
				if (((PlayerStatus) o).getPlayerId() == packet.getPlayerId())
				{
					handler.getLobbyManager().removeObject(i);
				}
			}
		}
	}

	private void handleRequest(Packet05JoinRequest packet)
	{
		handler.getGame().setPlayerId(packet.getId());
	}

	private void handleUpgrade(Packet04Upgrade packet)
	{
		switch (packet.getUpgradeType())
		{
		case 1:
			handler.getMap().getEntityManager()
					.addEntity(new BombUpgrade(handler, packet.getX() * handler.getGame().getScale() + Tile.getWidth() / (float)8,
							packet.getY() * handler.getGame().getScale() + Tile.getHeight() / (float)8));
			break;
		case 2:
			handler.getMap().getEntityManager()
					.addEntity(new SpeedUpgrade(handler, packet.getX() * handler.getGame().getScale() + Tile.getWidth() / (float)8,
							packet.getY() * handler.getGame().getScale() + Tile.getHeight() / (float)8));
			break;
		case 3:
			handler.getMap().getEntityManager()
					.addEntity(new PowerUpgrade(handler, packet.getX() * handler.getGame().getScale() + Tile.getWidth() / (float)8,
							packet.getY() * handler.getGame().getScale() + Tile.getHeight() / (float)8));
			break;
		default:
			break;
		}
	}

	private void handleBomb(Packet03Bomb packet)
	{
		PlayerMP p = handler.getMap().getEntityManager().getPlayerMP(packet.getPlayerId());
		if (p != null)
			handler.getMap().getEntityManager().addEntity(new Bomb(handler, p,
					packet.getX() * handler.getGame().getScale(), packet.getY() * handler.getGame().getScale()));
	}

	private synchronized void handleMove(Packet02Move packet)
	{
		handler.getMap().getEntityManager().movePlayer(packet.getPlayerId(),
				packet.getX() * handler.getGame().getScale(), packet.getY() * handler.getGame().getScale(),
				packet.getMovDir(), packet.isMoving());
	}

	private void handleLogin(Packet00Login packet, InetAddress address, int port)
	{
		PlayerMP player = handler.getMap().getEntityManager().createNewPlayer(handler, address, port,
				packet.getPlayerId(), packet.isReady());
		if (player != null)
			handler.getMap().getEntityManager().addEntity(player);
	}

	/**
	 * Wysy≥a pakiet z danymi do serwera
	 * @param data wysy≥ane dane
	 */
	public void sendData(byte[] data)
	{
		if (data != null)
		{
			DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 4444);
			try
			{
				socket.send(packet);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}
