package krolikowski.game.ui.objects.buttons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import krolikowski.game.Handler;
import krolikowski.game.ui.objects.UIObject;

/**
 * Klasa abstrakcyjna reprezentuj¹ca przycisk w UI
 */
public abstract class UIButton extends UIObject
{
	protected BufferedImage[] buttonImage;
	protected boolean highlighted;
	
	/**
	 * Konstruuje nowy UIButton, przypisuj¹c mu przekazany Handler, koordynaty x,y, szerokoœæ, wysokoœæ, a tak¿e tablicê obrazów BufferedImage[]
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³ozenie y
	 * @param width szerokoœæ
	 * @param height wysokoœæ
	 * @param buttonImage tablice obrazów BufferedImage[]
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
	 * Zwraca informacje, czy przycisk jest podœwietlony, czy nie
	 * @return true jeœli jest podœwietlony, false w przeciwnym wypadku
	 */
	public boolean isHighlighted()
	{
		return highlighted;
	}
	
	/**
	 * Ustawia czy przycisk jest podœwietlony, czy nie
	 * @param status status podœwietlenia
	 */
	public void setHighlightedStatus(boolean status)
	{
		highlighted = status;
	}
	
	/**
	 * aktywuje przycisk i wykonuje akcje zale¿n¹ od jego rodzaju
	 */
	public abstract void pressButton();
}