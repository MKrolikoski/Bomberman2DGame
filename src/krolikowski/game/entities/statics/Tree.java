package krolikowski.game.entities.statics;

import java.awt.Graphics;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Textures;

/**
 * Klasa reprezentuj¹ca drzewo
 *
 */
public class Tree extends StaticEntity
{

	/**
	 * Konstruuje nowy obiekt Tree, przypisuj¹c mu przekazany Handler oraz koordynaty x,y
	 * @param handler Handler
	 * @param x po³o¿enie x
	 * @param y po³ozenie y
	 */
	public Tree(Handler handler, float x, float y)
	{
		super(handler, x, y, 18, 28);
		bounds.x = 3 * handler.getGame().getScale();
		bounds.y = 15 * handler.getGame().getScale();
		bounds.width = 12 * handler.getGame().getScale();
		bounds.height = 10 * handler.getGame().getScale();
	}

	@Override
	public void tick()
	{

	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Textures.getTree(), (int) x, (int) y, width, height, null);
	}

	@Override
	public boolean checkEntityCollision(float xOffset, float yOffset)
	{
		return false;
	}

	@Override
	public boolean collisionWithTile(int x, int y)
	{
		return false;
	}

}
