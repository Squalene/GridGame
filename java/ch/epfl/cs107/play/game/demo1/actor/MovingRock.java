package ch.epfl.cs107.play.game.demo1.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class MovingRock extends GraphicsEntity
{
	private final TextGraphics text;

	public MovingRock(Vector position, String text)
	{
		// initialise the superconstructor of graphics entity
		super(position, new ImageGraphics(ResourcePath.getSprite("rock.3"), 0.1f, 0.1f, null,
				Vector.ZERO, 1.0f, -Float.MAX_VALUE));

		// Sets: the message,linewidth,and color of the text
		this.text = new TextGraphics(text, 0.04f, Color.BLUE);

		// Sets the position of the text compared to the object
		this.text.setAnchor(new Vector(-0.3f, 0.1f));

		// Attaches the text to the actor(moving rock)
		// This anchor can be moved with
		// this.text.setAnchor(new Vector(-0.3f, 0.1f));
		this.text.setParent(this);
	}

	/**
	 * Redefines draw to draw a rock and its text attached to it
	 * 
	 * @param window
	 *            (Canvas):the canvas to draw in
	 */
	@Override
	public void draw(Canvas window)
	{
		super.draw(window);
		text.draw(window);
	}

	/**
	 * Updates the rock's position, by moving toward the bottom left
	 * 
	 * @param delta
	 *            time (float)
	 */
	@Override
	public void update(float deltaTime)
	{
		setCurrentPosition(getPosition().sub(0.005f, 0.005f));
	}
}
