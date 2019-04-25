package krolikowski.game.ui.objects.buttons;

import java.awt.event.KeyEvent;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;
import krolikowski.game.input.InputHandler;
import krolikowski.game.net.packet.Packet01Disconnect;
import krolikowski.game.net.packet.Packet07ReadyStatus;

/**
 * Button odpowiedzialny za zmianê statusu gotowoœci gracza i powiadomienie o tym serwera
 *
 */

public class ReadyButton extends UIButton
{

	private InputHandler input;
	/**
	 * Konstruuje nowy ReadyButton, przypisuj¹c mu przekazany Handler, koordynaty x,y, szerokoœæ, wysokoœæ oraz obiekt pobieraj¹cy input
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @param width szerokoœæ
	 * @param height wysokoœæ
	 * @param input InputHandler
	 */
	public ReadyButton(Handler handler, float x, float y, int width, int height, InputHandler input)
	{
		super(handler, x, y, width, height, Textures.getReady());
		this.input = input;
	}

	@Override
	public void tick()
	{
		getInput();
	}

	private void getInput()
	{
		if (input.isEnter())
		{
			timer += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
			if (timer > 250)
			{
				timer = 0;
				pressButton();
			}
		}
		if (input.isBackspace() || input.isEscape())
		{
			timer += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
			if (timer > 250)
			{
				timer = 0;
				input.toggleKey(KeyEvent.VK_BACK_SPACE, false);
				Packet01Disconnect packet = new Packet01Disconnect(handler.getGame().getPlayerId());
				packet.writeData(handler.getGame().getSocketClient());
				handler.getGame().goToMenu();
			}
		}
		
		
	}
	
	/**
	 * Zmienia status gotowoœci gracza
	 */
	@Override
	public void pressButton()
	{
		if (highlighted)
		{
			highlighted = false;
			handler.getGame().getPlayer().setReady(false);
		} else
		{
			highlighted = true;
			handler.getGame().getPlayer().setReady(true);
		}
		Packet07ReadyStatus packet = new Packet07ReadyStatus(handler.getGame().getPlayerId(), handler.getGame().getPlayer().isReady());
		packet.writeData(handler.getGame().getSocketClient());
	}
}
