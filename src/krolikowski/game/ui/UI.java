package krolikowski.game.ui;

import java.awt.Graphics;

import krolikowski.game.ui.objects.UIObject;

/**
 * interfejs reprezentuj¹cy UI
 *
 */
public interface UI
{
	/**
	 * update elementów UI
	 */
	void tick();
	/**
	 * rendering elementów UI
	 * @param g obiekt Graphics
	 */
	void render(Graphics g);
	/**
	 * Dodaje obiekt do UI
	 * @param o obiekt
	 */
	void addObject(UIObject o);
	/**
	 * Usuwa obiekt z UI
	 * @param o obiekt
	 */
	void removeObject(UIObject o);
}
