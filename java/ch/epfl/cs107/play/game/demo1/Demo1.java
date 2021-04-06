package ch.epfl.cs107.play.game.demo1;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Demo1 implements Game
{

	private Actor circle;
	private Actor rock;
	private TextGraphics boom;
	private float radius = 0.2f;
	private Window window;
	private FileSystem fileSystem;
	private static boolean boomIsActive = false;

	/**
	 * 
	 * @param window
	 *            (Window), not null
	 * @param fileSystem
	 *            (FileSystem),not null
	 */
	@Override
	public boolean begin(Window window, FileSystem fileSystem)
	{
		this.window = window;
		this.fileSystem = fileSystem;

		float lineWidth = 0.005f;

		boomIsActive = false;

		// Initialise the 3 drawable entities
		circle = new GraphicsEntity(Vector.ZERO,
				new ShapeGraphics(new Circle(radius), null, Color.RED, lineWidth));

		rock = new MovingRock(new Vector(0.4f, 0.4f), "Rocks can move");

		boom = new TextGraphics("BOOM", 0.06f, Color.RED);

		// Game can begin
		return true;
	}

	/**
	 * 
	 */
	@Override
	public void end()
	{
		// do nothing
	}

	/**
	 * Updates demo1
	 * 
	 * @param deltaTime(float)
	 */
	@Override
	public void update(float deltaTime)
	{
		Keyboard keyboard = window.getKeyboard();
		Button downArrow = keyboard.get(Keyboard.DOWN);

		if (downArrow.isDown())
		{
			rock.update(deltaTime);
		}

		// displays a boom when the rock is in the circle
		if (circle.getPosition().sub(rock.getPosition()).getLength() <= radius)
		{
			if (boomIsActive == false)
			{
				boom.setAnchor(rock.getPosition());
			}

			boom.draw(window);
			boomIsActive = true;
		}

		else boomIsActive = false;

		circle.draw(window);
		rock.draw(window);

	}

	/**
	 * @return title of the game
	 */

	@Override
	public String getTitle()
	{
		return "Demo1";
	}

	/**
	 * @return frameRate
	 */
	@Override
	public int getFrameRate()
	{
		return 24;
	}
}
