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
	 * sprawdza kolizj� z innych bytami
	 * @param xOffset offset od zmiennej X po�o�enia bytu
	 * @param yOffset offset od zmiennej Y po�o�enia bytu
	 * @return true je�li wyst�puje kolizja, false je�li kolizji nie ma
	 */
	boolean checkEntityCollision(float xOffset, float yOffset);
	/**
	 * Zwraca hitbox bytu
	 * @param xOffset offset od zmiennej X po�o�enia bytu
	 * @param yOffset offset od zmiennej Y po�o�enia bytu
	 * @return hitbox bytu
	 */
	Rectangle getCollisionBounds(float xOffset, float yOffset);
	/**
	 * sprawdza kolizj� z tile'em na okre�lonej pozycji
	 * @param x po�o�enie x
	 * @param y po�o�enie y
	 * @return true je�li dany tile jest solid, false w przeciwnym wypadku
	 */
	boolean collisionWithTile(int x, int y);
	/**
	 * Zwraca po�ozenie Y
	 * @return po�o�enie Y
	 */
	float getY();
	/**
	 * Zwraca po�ozenie X
	 * @return po�o�enie X
	 */
	float getX();
	/**
	 * Zwraca wysoko�� bytu
	 * @return wysoko��
	 */
	int getHeight();
	/**
	 * Zwraca szeroko�� bytu
	 * @return szeroko��
	 */
	int getWidth();
}
