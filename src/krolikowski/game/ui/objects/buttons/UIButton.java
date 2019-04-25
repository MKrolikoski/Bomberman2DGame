package krolikowski.game.ui.objects.buttons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import krolikowski.game.Handler;
import krolikowski.game.ui.objects.UIObject;

/**
 * Klasa abstrakcyjna reprezentuj�ca przycisk w UI
 */
public abstract class UIButton extends UIObject
{
	protected BufferedImage[] buttonImage;
	protected boolean highlighted;
	
	/**
	 * Konstruuje nowy UIButton, przypisuj�c mu przekazany Handler, koordynaty x,y, szeroko��, wysoko��, a tak�e tablic� obraz�w BufferedImage[]
	 * @param handler Handler
	 * @param x po�o�enie x
	 * @param y po�ozenie y
	 * @param width szeroko��
	 * @param height wysoko��
	 * @param buttonImage tablice obraz�w BufferedImage[]
	 */
	public UIButton(Handler handler, float x, float y, int width, int height, BufferedImage[] buttonImage)
	{
		super(handler, x, y, width, height);
		this.buttonImage = buttonImage.clone();
		highlighted = false;
	}


	@Override
	public abstract void tick();

	@Override
	public void render(Graphics g)
	{
		if(!isHighlighted())
		{
			g.drawImage(buttonImage[0], (int)x, (int)y, width, height, null);
		}
		else
		{
			g.drawImage(buttonImage[1], (int)x, (int)y, width, height, null);
		}
	}
	
	/**
	 * Zwraca informacje, czy przycisk jest pod�wietlony, czy nie
	 * @return true je�li jest pod�wietlony, false w przeciwnym wypadku
	 */
	public boolean isHighlighted()
	{
		return highlighted;
	}
	
	/**
	 * Ustawia czy przycisk jest pod�wietlony, czy nie
	 * @param status status pod�wietlenia
	 */
	public void setHighlightedStatus(boolean status)
	{
		highlighted = status;
	}
	
	/**
	 * aktywuje przycisk i wykonuje akcje zale�n� od jego rodzaju
	 */
	public abstract void pressButton();
}