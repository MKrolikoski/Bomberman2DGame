package krolikowski.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import krolikowski.game.display.Display;
import krolikowski.game.display.WindowHandler;
import krolikowski.game.entities.mobs.PlayerMP;
import krolikowski.game.gfx.Textures;
import krolikowski.game.input.InputHandler;
import krolikowski.game.levels.Map;
import krolikowski.game.net.GameClient;
import krolikowski.game.net.GameServer;
import krolikowski.game.net.packet.Packet00Login;
import krolikowski.game.net.packet.Packet05JoinRequest;
import krolikowski.game.net.packet.Packet14CheckConnection;
import krolikowski.game.sounds.SoundClip;
import krolikowski.game.states.GameState;
import krolikowski.game.states.LobbyState;
import krolikowski.game.states.MenuState;
import krolikowski.game.states.SettingsState;
import krolikowski.game.states.State;
import krolikowski.game.ui.UIManager;
import krolikowski.game.utils.Utils;

/**
 * G³ówna klasa gry odpowiedzialna za jej aktualizacjê i renderowanie
 * 
 * @author Micha³ Królikowski
 *
 */

public class Game implements Runnable, GameInterface
{
	/**
	 * Skala ekranu odpowiedzialna za odpowiednie skalowanie wszystkich renderowanych obiektow w grze
	 */
	public final static int SCALE = 3;
	private int width = 320;
	private int height = width / 12 * 9;
	private String title = "Bomberman 2D";

	private Display display;
	private Graphics g;
	private Thread thread;
	private boolean running = false;
	private String myIp;

	private PlayerMP player;
	private int playerId;

	private State gameState;
	private State menuState;
	private State settingsState;
	private State lobbyState;

	private Handler handler;
	private WindowHandler windowHandler;

	private boolean musicOn;
	private boolean soundsOn;

	private int gameStatus;
	// SETTINGS: -3 MENU: -2 GAMEEND: -1 DRAW: 0 GAMEON: 1 INLOBBY: 2

	private GameClient socketClient;
	private GameServer socketServer;
	private InputHandler input;

	private SoundClip menuMusic;
	private SoundClip gameMusic;

	private String map;


	@Override
	public void init()
	{
		display = new Display(title, width, height, SCALE);
		Textures.init();
		handler = new Handler(this);
		input = new InputHandler(handler);
		menuState = new MenuState(handler);
		settingsState = new SettingsState(handler);
		State.setState(menuState);
		gameStatus = -2;
		musicOn = true;
		soundsOn = true;
		windowHandler = new WindowHandler(handler);
		setMap("");
		getDisplay().getFrame().addWindowListener(windowHandler);
		while (myIp == null)
			try
			{
				myIp = Utils.getLocalAddress().getHostAddress();
/*				 myIp =
				 JOptionPane.showInputDialog(handler.getGame().getDisplay().getFrame(),
				 "Enter your IP:", "192.168.");*/
			} catch (Exception e)
			{
				int noConnection = JOptionPane.showConfirmDialog(null, "Please verify your internet connection", null,
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (noConnection == JOptionPane.CLOSED_OPTION || noConnection == JOptionPane.CANCEL_OPTION)
				{
					System.exit(0);
				}
			}
	}

	/**
	 * Startuje w¹tek gry
	 */
	public synchronized void start()
	{
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Zatrzymuje w¹tek gry
	 */
	public synchronized void stop()
	{
		running = false;
		try
		{
			if (thread != null)
				thread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Metoda uruchamiana przy stacie w¹tku, jest odpowiedzialna za prawid³owy przebieg gry 
	 */
	public synchronized void run()
	{
		init();

		int fps = 60;
		double nsPerTick = 1000000000D / fps;
		double delta = 0;

		long lastTime = System.nanoTime();
		long lastTimer = System.currentTimeMillis();

		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			while (delta >= 1)
			{
				tick();
				delta -= 1;
				shouldRender = true;
			}
			try
			{
				thread.sleep(3);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			if (shouldRender)
			{
				render();
			}

			if (System.currentTimeMillis() - lastTimer >= 2000)
			{
				lastTimer += 2000;

				// every 2 seconds check connection with host
				if (getGameStatus() != -2 && getGameStatus() != -3)
				{
					if (socketClient != null && getSocketClient().getHostResponse().equals("PONG"))
					{
						getSocketClient().setHostResponse("PING");
						Packet14CheckConnection pac = new Packet14CheckConnection(getPlayerId());
						pac.writeData(handler.getGame().getSocketClient());
					} else
					{
						handler.getGame().goToMenu();
						JOptionPane.showMessageDialog(null, "Lost connection with the host", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
		stop();
	}

	@Override
	public void tick()
	{
		if (State.getState() != null)
			State.getState().tick();
	}

	@Override
	public void render()
	{
		BufferStrategy bs = display.getCanvas().getBufferStrategy();
		if (bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width * SCALE, height * SCALE);

		if (State.getState() != null)
			State.getState().render(g);

		bs.show();
		g.dispose();

	}

	/**
	 * Zwraca szerokoœæ ekranu
	 * @return szerokoœæ ekranu
	 */
	public int getWidth()
	{
		return display.getWidth();
	}

	/**
	 * Zwraca wysokoœæ ekranu
	 * @return wysokoœæ ekranu
	 */
	public int getHeight()
	{
		return display.getHeight();
	}

	/**
	 * Zwraca skalê ekranu
	 * @return skalê ekranu
	 */
	public int getScale()
	{
		return SCALE;
	}

	/**
	 * koñczy rozgrywkê i zamyka program
	 */
	public synchronized void endGame()
	{
		running = false;
		System.exit(0);
	}

	/**
	 * rozpoczyna mecz
	 */
	public void startGame()
	{
		handler.getGame().getPlayer().setInvulnerability(false);
		handler.getGame().setGameStatus(1);
		State.setState(gameState);
	}

	/**
	 * Ustawia obecny status gry
	 * 
	 * @param gameStatus
	 *            obecny status gry
	 */
	public void setGameStatus(int gameStatus)
	{
		this.gameStatus = gameStatus;
	}

	/**
	 * Zwraca obecny status gry
	 * @return obecny status gry
	 */
	public int getGameStatus()
	{
		return gameStatus;
	}

	/**
	 * tworzy serwer i do³¹cza pierwszego klienta, który zostaje hostem gry
	 * (przypisuje mu ID = 1), zmienia stan gry na lobby
	 */
	public void createServer()
	{
		try
		{
		socketClient = new GameClient(handler, getMyIp());
		socketClient.start();
		Packet05JoinRequest packet = new Packet05JoinRequest(0);
		packet.writeData(getSocketClient());
		try
		{
			thread.sleep(500);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		if (getPlayerId() == 0)
		{
			socketServer = new GameServer(handler);
			socketServer.start();
			player = new PlayerMP(handler, null, -1, 1, handler.getInput(), false);
			setPlayerId(1);
			setPlayer(player);
			goToLobby();
		} else
		{
			JOptionPane.showMessageDialog(null, "Server already running", "", JOptionPane.INFORMATION_MESSAGE);
			setPlayerId(0);
		}
		}catch(UnknownHostException e)
		{
			JOptionPane.showMessageDialog(null, "Couldn't start a server", "", JOptionPane.INFORMATION_MESSAGE);
			setPlayerId(0);
		}
	}

	/**
	 * wysy³a na serwer pakiet z danymi do³¹czanego gracza
	 */
	private void sendLoginPacket()
	{
		Packet00Login loginPacket = new Packet00Login(handler.getGame().getPlayer().getPlayerId(),
				handler.getGame().getPlayer().isReady());
		if (getPlayerId() == 1 && handler.getGame().getSocketServer() != null)
		{

			handler.getGame().getSocketServer().addConnection(handler.getGame().getPlayer(), loginPacket);
		}
		loginPacket.writeData(handler.getGame().getSocketClient());
	}

	/**
	 * po podaniu adresu IP istniej¹cego serwera, wysy³a do niego pakiet, który
	 * zwraca nr. ID, jeœli Serwer jest pe³en (max 4 osoby), ID: -1 Rozgrywka na
	 * serwerze zosta³a rozpoczêta, ID: -2 Serwer nie udziela odpowiedzi: ID: 0
	 * W ka¿dym innym przypadku (ID 2-4) gracz zostaje dodany do serwera, po
	 * czym przechodzi do lobby
	 */
	public void joinGame()
	{
		String serverIp = JOptionPane.showInputDialog(handler.getGame().getDisplay().getFrame(), "Enter server IP:",
				getMyIp());
		if (serverIp != null)
		{
			try
			{
				socketClient = new GameClient(handler, serverIp);
				socketClient.start();
				Packet05JoinRequest packet = new Packet05JoinRequest(0);
				packet.writeData(getSocketClient());
				try
				{
					thread.sleep(1000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				if (getPlayerId() == -1)
				{
					JOptionPane.showMessageDialog(null, "Server is full", "", JOptionPane.INFORMATION_MESSAGE);
					setPlayerId(0);
					return;
				} else if (getPlayerId() == -2)
				{
					setPlayerId(0);
					JOptionPane.showMessageDialog(null, "Game already started", "", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else if (getPlayerId() == 0 || getMap().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Couldn't connect to the server", "",
							JOptionPane.INFORMATION_MESSAGE);
					setPlayerId(0);
					return;
				} else
				{
					player = new PlayerMP(handler, null, -1, getPlayerId(), handler.getInput(), false);
					setPlayer(player);
					goToLobby();
				}
			} catch (UnknownHostException e)
			{
				JOptionPane.showMessageDialog(null, "Couldn't connect to the server", "",
						JOptionPane.INFORMATION_MESSAGE);
				setPlayerId(0);
			}
		}
	}

	/**
	 * ustawia stan gry na Menu
	 */
	public void goToMenu()
	{
		if (getGameStatus() != -2)
			setGameStatus(-2);
		if (getMap() != null)
			setMap("");
		setPlayerId(0);
		State.setState(menuState);
	}

	/**
	 * ustawia stan gry na Settings
	 */
	public void goToSettings()
	{
		setGameStatus(-3);
		State.setState(settingsState);
	}

	/**
	 * ustawia stan gry na Lobby, ponadto resetuje graczy na mapie
	 */
	public void goToLobby()
	{
		if (handler.getMap() != null)
			handler.getMap().getEntityManager().removePlayers();
		stopGameMusic();
		createGame();
		sendLoginPacket();
		lobbyState = new LobbyState(handler);
		setGameStatus(2);
		State.setState(lobbyState);
	}

	private void createGame()
	{
		if (getPlayerId() == 1 && getMap().equals(""))
		{
			InputStream map = Map.class.getResourceAsStream("/maps/grassMap.txt");
			String fileInput = Utils.loadFileAsString(map);
			setMap(fileInput);
			gameState = new GameState(handler);
			try
			{
				map.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} else
		{
			gameState = new GameState(handler);
		}
	}

	private void stopGameMusic()
	{
		if (getGameMusic() != null && getGameMusic().isPlaying())
		{
			getGameMusic().resetSound();
			getGameMusic().stopSound();
		}
	}

	/**
	 * Informuje czy muzyka jest w³¹czona, czy nie
	 * @return true jeœli w³¹czona, false w przeciwnym wypadku
	 */
	public boolean isMusicOn()
	{
		return musicOn;
	}

	/**
	 * w³¹cza muzykê w grze
	 */
	public void setMusicOn()
	{
		musicOn = true;
	}

	/**
	 * wy³¹cza muzykê w grze
	 */
	public void setMusicOff()
	{
		musicOn = false;
	}

	/**
	 * Informuje czy dŸwiêki s¹ w³¹czone, czy nie
	 * @return true jeœli w³¹czone, false w przeciwnym wypadku
	 */
	public boolean areSoundsOn()
	{
		return soundsOn;
	}

	/**
	 * w³¹cza dŸwiêki w grze
	 */
	public void setSoundsOn()
	{
		soundsOn = true;
	}

	/**
	 * wy³¹cza dŸwiêki w grze
	 */
	public void setSoundsOff()
	{
		soundsOn = false;
	}

	/**
	 * Zwraca clip z muzyk¹ z menu
	 * @return clip z muzyk¹ z menu
	 */
	public SoundClip getMenuMusic()
	{
		return menuMusic;
	}

	/**
	 * Ustawia muzykê w menu
	 * @param menuMusic
	 *            clip z muzyk¹
	 */
	public void setMenuMusic(SoundClip menuMusic)
	{
		this.menuMusic = menuMusic;
	}

	/**
	 * Zwraca clip z muzyk¹ z gry
	 * @return clip z muzyk¹ z gry
	 */
	public SoundClip getGameMusic()
	{
		return gameMusic;
	}

	/**
	 * Ustawia muzykê podczas rozgrywki
	 * @param gameMusic
	 *            clip z muzyk¹
	 */
	public void setGameMusic(SoundClip gameMusic)
	{
		this.gameMusic = gameMusic;
	}

	/**
	 * Zwraca socket klienta
	 * @return socket klienta
	 */
	public GameClient getSocketClient()
	{
		return socketClient;
	}

	/**
	 * Ustawia socket klienta
	 * @param socketClient
	 *            socket klienta
	 */
	public void setSocketClient(GameClient socketClient)
	{
		this.socketClient = socketClient;
	}

	/**
	 * Zwraca socket serwera
	 * @return socket serwera
	 */
	public GameServer getSocketServer()
	{
		return socketServer;
	}

	/**
	 * Ustawia socket serwera
	 * @param socketServer
	 *            socket serwera
	 */
	public void setSocketServer(GameServer socketServer)
	{
		this.socketServer = socketServer;
	}

	/**
	 * Zwraca obiekt klasy Display zawieraj¹cy JFrame i Canvas gry
	 * @return obiekt klasy Display
	 */
	public Display getDisplay()
	{
		return display;
	}

	/**
	 * Zwraca gracza sieciowego danego klienta
	 * @return gracz danego klienta
	 */
	public PlayerMP getPlayer()
	{
		return player;
	}

	/**
	 * Ustawia gracza sieciowego danego klienta
	 * @param player
	 *            gracz sieciowy
	 */
	public void setPlayer(PlayerMP player)
	{
		this.player = player;
	}

	/**
	 * Zwraca obiekt obs³uguj¹cy input gracza
	 * @return obiekt pobieraj¹cy input
	 */
	public InputHandler getInput()
	{
		return input;
	}

	/**
	 * Zwraca id gracza klienta
	 * @return id gracza klienta
	 */
	public int getPlayerId()
	{
		return playerId;
	}

	/**
	 * Ustawia id gracza klienta
	 * @param playerId
	 *            id gracza
	 */
	public void setPlayerId(int playerId)
	{
		this.playerId = playerId;
	}

	/**
	 * Zwraca mened¿er interfejsu ze stanu Lobby
	 * @return mened¿er interfejsu
	 */
	public UIManager getLobbyManager()
	{
		if (lobbyState != null)
			return ((LobbyState) lobbyState).getLobbyManager();
		return null;
	}

	/**
	 * Zwraca IPv4 klienta
	 * @return IPv4 klienta
	 */
	public String getMyIp()
	{
		return myIp;
	}

	/**
	 * Ustawia mapê do rozgrywki
	 * @param map
	 *            mapa wczytana z pliku tekstowego
	 */
	public void setMap(String map)
	{
		this.map = map;
	}

	/**
	 * Zwraca mapê do rozgrywki
	 * @return mapa wczytana z pliku tekstowego
	 */
	public String getMap()
	{
		return map;
	}

}
