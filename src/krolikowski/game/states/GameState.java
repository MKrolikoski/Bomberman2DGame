package krolikowski.game.states;

import java.awt.Graphics;

import krolikowski.game.Handler;
import krolikowski.game.gfx.Animation;
import krolikowski.game.gfx.Textures;
import krolikowski.game.levels.Map;
import krolikowski.game.net.packet.Packet13ResetPlayers;
import krolikowski.game.sounds.SoundClip;

/**
 * Klasa reprezentuj¹ca g³ówny stan gry
 *
 */
public class GameState extends State
{
	private Map map;
	private Animation youWin, youLose, draw;
	
	/**
	 * Konstruuje nowy GameState, przypisuj¹c mu przekazany Handler
	 * @param handler Handler
	 */
	public GameState(Handler handler)
	{
		super(handler);	
		map = new Map(handler, handler.getGame().getMap());
		handler.setMap(map);
		handler.getGame().setGameMusic(new SoundClip("/sounds/map_1_music.wav"));
		youWin = new Animation(500, Textures.getWin());
		youLose = new Animation(500, Textures.getLose());
		draw = new Animation(500, Textures.getDraw());
		draw.setLoopsLimit(4);
	}


	@Override
	public void tick()
	{
		map.tick();
		if(handler.getGame().getGameStatus() == -1)
		{
			if(handler.getGame().getPlayer().isWinner())
			{
				youWin.tick();
			}else
			{
				youLose.tick();
			}
		}
		if(handler.getGame().getGameStatus() == 0)
		{
			draw.tick();
			if(draw.didLoop())
			{
				Packet13ResetPlayers p = new Packet13ResetPlayers();
				p.writeData(handler.getGame().getSocketClient());
			}
		}
		if(handler.getGame().getMenuMusic().isPlaying())
		{
			handler.getGame().getMenuMusic().resetSound();
			handler.getGame().getMenuMusic().stopSound();
		}
		if(handler.getGame().isMusicOn() && handler.getGame().getGameStatus() == 1 && !handler.getGame().getGameMusic().isPlaying())
		{
			handler.getGame().getGameMusic().loopSound();
		}
	}

	@Override
	public void render(Graphics g)
	{
		map.render(g);
		if(handler.getGame().getGameStatus() == -1)
		{
			if(handler.getGame().getPlayer().isWinner())
			{
				g.drawImage(youWin.getCurrentFrame(), 64*handler.getGame().getScale(), 64*handler.getGame().getScale(), 192*handler.getGame().getScale(), 64*handler.getGame().getScale(), null);
			}else
			{
				g.drawImage(youLose.getCurrentFrame(), 64*handler.getGame().getScale(), 64*handler.getGame().getScale(), 192*handler.getGame().getScale(), 64*handler.getGame().getScale(), null);
			}
		}
		if(handler.getGame().getGameStatus() == 0)
		{
			g.drawImage(draw.getCurrentFrame(), 64*handler.getGame().getScale(), 64*handler.getGame().getScale(), 192*handler.getGame().getScale(), 64*handler.getGame().getScale(), null);
		}
	}

}
