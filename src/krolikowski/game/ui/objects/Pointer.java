package krolikowski.game.ui.objects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Animation;
import krolikowski.game.gfx.Textures;
import krolikowski.game.input.InputHandler;
import krolikowski.game.ui.objects.buttons.UIButton;

/**
 * Obiekt UI wskazuj¹cy na poszczególne UIButton'y i umo¿liwiaj¹ce ich aktywowanie
 *
 */
public class Pointer extends UIObject
{
	private Animation pointerAnimation;
	private List<UIButton> buttons;
	private UIButton currentButton;
	private int currentButtonPosition;
	private InputHandler input;
	
	/**
	 * Konstruuje nowy Pointer, przypisuj¹c mu przekazany Handler, po³o¿enie x, listê obiektów UI na które mo¿e wskazywaæ, a tak¿e obiekt pobieraj¹cy input
	 * @param handler Handler
	 * @param x po³o¿nie x
	 * @param uiObjects lista obiektów UI
	 * @param input InputHandler
	 */
	public Pointer(Handler handler, float x, List<UIObject> uiObjects, InputHandler input)
	{
		super(handler, x, 0, 16, 16);
		pointerAnimation = new Animation(250, Textures.getBomb());
		buttons = new ArrayList<UIButton>();
		getButtons(uiObjects);
		currentButtonPosition = 0;
		setCurrentButton();
		this.input = input;
	}
		
	
	private void getButtons(List<UIObject> uiObjects)
	{
		for(UIObject o : uiObjects)
		{
			if(o == this || !(o instanceof UIButton))
				continue;
			UIButton b = (UIButton) o;
			buttons.add(b);
		}
	}
	
	private void setCurrentButton()
	{
		currentButton = buttons.get(currentButtonPosition);	
	}
	
	
	@Override
	public void tick()
	{
			pointerAnimation.tick();
			getInput();
			highlightCurrentButton();
	}
	
	private void getInput()
	{		
		if (input.isUp())
		{
			unHighlightCurrentButton();
			timer += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
			if (timer > 250)
			{
				timer = 0;
				if(currentButtonPosition == 0)
				{
					currentButtonPosition = buttons.size()-1;
				}
				else
				{
					currentButtonPosition = --currentButtonPosition % buttons.size();
				}
			}
			setCurrentButton();
		}
		if (input.isDown())
		{
			unHighlightCurrentButton();
			timer += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
			if (timer > 250)
			{
				timer = 0;
				currentButtonPosition = ++currentButtonPosition % buttons.size();
			}
			setCurrentButton();
		}
		if (input.isEnter())
		{
			timer += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
			if (timer > 250)
			{
				timer = 0;
				input.toggleKey(KeyEvent.VK_ENTER, false);
				pressCurrentButton();
			}
		}
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(pointerAnimation.getCurrentFrame(), (int)x, (int)getCurrentButtonY()-2*handler.getGame().getScale(), width, height, null);
	}
	
	private float getCurrentButtonY()
	{
		return currentButton.getY();
	}
	
	private void pressCurrentButton()
	{
			currentButton.pressButton();
	}
	
	private void highlightCurrentButton()
	{
		currentButton.setHighlightedStatus(true);
	}
	private void unHighlightCurrentButton()
	{
		currentButton.setHighlightedStatus(false);
	}

}
