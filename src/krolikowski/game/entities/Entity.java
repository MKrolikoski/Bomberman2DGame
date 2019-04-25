package krolikowski.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * interfejs bytu
 *
 */

public interface Entity
{
	/**
	 * update zmiennych
	 */
	void tick();
	/**
	 * renderuje byt
	 * @param g obiekt Graphics
	 */
	void render(Graphics g);
	/**
	 * sprawdza kolizjê z innych bytami
	 * @param xOffset offset od zmiennej X po³o¿enia bytu
	 * @param yOffset offset od zmiennej Y po³o¿enia bytu
	 * @return true jeœli wystêpuje kolizja, false jeœli kolizji nie ma
	 */
	boolean checkEntityCollision(float xOffset, float yOffset);
	/**
	 * Zwraca hitbox bytu
	 * @param xOffset offset od zmiennej X po³o¿enia bytu
	 * @param yOffset offset od zmiennej Y po³o¿enia bytu
	 * @return hitbox bytu
	 */
	Rectangle getCollisionBounds(float xOffset, float yOffset);
	/**
	 * sprawdza kolizjê z tile'em na okreœlonej pozycji
	 * @param x po³o¿enie x
	 * @param y po³o¿enie y
	 * @return true jeœli dany tile jest solid, false w przeciwnym wypadku
	 */
	boolean collisionWithTile(int x, int y);
	/**
	 * Zwraca po³ozenie Y
	 * @return po³o¿enie Y
	 */
	float getY();
	/**
	 * Zwraca po³ozenie X
	 * @return po³o¿enie X
	 */
	float getX();
	/**
	 * Zwraca wysokoœæ bytu
	 * @return wysokoœæ
	 */
	int getHeight();
	/**
	 * Zwraca szerokoœæ bytu
	 * @return szerokoœæ
	 */
	int getWidth();
}
