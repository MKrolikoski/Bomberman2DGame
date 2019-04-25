package krolikowski.game.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import krolikowski.game.Handler;
import krolikowski.game.ui.objects.UIObject;

/**
 * Klasa abstrakcyjna odpowiedzialna za obs³ugê elementów UI
 */
public abstract class UIManager implements UI
{
	protected Handler handler;
	protected List<UIObject> uiObjects;

	/**
	 * Konstruuje nowy UIManager, przypisuj¹c mu przekazany Handler
	 * @param handler Handler
	 */
	public UIManager(Handler handler)
	{
		this.handler = handler;
		uiObjects = new ArrayList<UIObject>();
		addObjects();
	}

	/**
	 * Dodaje pocz¹tkowe obiekty UI
	 */
	protected abstract void addObjects();

	@Override
	public void tick()
	{
			for (UIObject o : getUIObjects())
			{
				o.tick();
			}
	}

	@Override
	public synchronized void render(Graphics g)
	{
		try
		{
			for (Iterator<UIObject> it = getUIObjects().iterator(); it.hasNext();)
			{
				UIObject o = it.next();
				o.render(g);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void addObject(UIObject o)
	{
		getUIObjects().add(o);
	}

	@Override
	public void removeObject(UIObject o)
	{
		if (getUIObjects().contains(o))
			getUIObjects().remove(o);
	}

	/**
	 * Zwraca listê z obiektami UI
	 * @return lista
	 */
	public synchronized List<UIObject> getUIObjects()
	{
		return uiObjects;
	}

	public void removeObject(int i)
	{
		getUIObjects().remove(i);
	}

}
