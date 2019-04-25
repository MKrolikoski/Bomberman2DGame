package krolikowski.game.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Obiekt zawieraj¹cy JFrame i Canvas gry
 *
 */

public class Display
{
	private JFrame frame;
	private Canvas canvas;
	private String title;
	private int width, height, scale;
	
	/**
	 * Konstruuje nowy Display, przypisuj¹c mu przekazany tytu³, wymiary i skale
	 * @param title tytu³ okna
	 * @param width szerokoœæ okna
	 * @param height wysokoœæ okna
	 * @param scale skala
	 */
	public Display(String title, int width, int height, int scale)
	{
		this.title = title;
		this.width = width;
		this.height = height;
		this.scale = scale;
		
		createDisplay();
	}

	private void createDisplay()
	{
		frame = new JFrame(title);
		frame.setSize(width*scale, height*scale);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setMinimumSize(new Dimension(width*scale, height*scale));
		canvas.setMaximumSize(new Dimension(width*scale, height*scale));
		canvas.setPreferredSize(new Dimension(width*scale, height*scale));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();		
	}

	/**
	 * Zwraca frame gry
	 * @return JFrame gry
	 */
	public JFrame getFrame()
	{
		return frame;
	}

	/**
	 * Zwraca canvas gry
	 * @return Canvas gry
	 */
	public Canvas getCanvas()
	{
		return canvas;
	}
	
	/**
	 * Zwraca szerokoœæ okna
	 * @return szerokoœæ frame'a
	 */
	public int getWidth()
	{
		return frame.getWidth();
	}
	

	/**
	 * Zwraca wysokoœæ okna
	 * @return wysokoœæ frame'a
	 */
	
	public int getHeight()
	{
		return frame.getHeight();
	}
	
	
	
	
	

}
