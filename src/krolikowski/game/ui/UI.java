package krolikowski.game.ui;

import java.awt.Graphics;

import krolikowski.game.ui.objects.UIObject;

/**
 * interfejs reprezentuj�cy UI
 *
 */
public interface UI
{
	/**
	 * update element�w UI
	 */
	void tick();
	/**
	 * rendering element�w UI
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
