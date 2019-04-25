package krolikowski.game.display;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import krolikowski.game.Handler;
import krolikowski.game.net.packet.Packet01Disconnect;

/**
 * Obs�uguj� zdarzenia kiedy klient zamyka okno aplikacji
 *
 */

public class WindowHandler implements WindowListener
{
	private Handler handler;

	/**
	 * Konstruuje nowy WindowHandler przypisuj�c mu przekazany Handler gry
	 * @param handler Handler gry
	 */
	public WindowHandler(Handler handler)
	{
		this.handler = handler;
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{

	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{

	}

	/**
	 * W momencie zamkni�cia okna przez klienta zostaje wys�any komunikat na serwer o roz��czeniu si� tego gracza
	 */
	@Override
	public void windowClosing(WindowEvent arg0)
	{
		if (handler.getGame().getPlayer() != null)
		{
			Packet01Disconnect packet = new Packet01Disconnect(handler.getGame().getPlayer().getPlayerId());
			packet.writeData(handler.getGame().getSocketClient());
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{

	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{

	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{

	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{

	}

}
