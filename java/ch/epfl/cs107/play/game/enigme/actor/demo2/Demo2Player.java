package ch.epfl.cs107.play.game.enigme.actor.demo2;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Demo2Player extends MovableAreaEntity
{

	/// Animation duration in frame number
	private final static int ANIMATION_DURATION = 8;

	private boolean onDoor;
	private Sprite graphic;

	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates coordinates)
	{
		super(area, orientation, coordinates);

		onDoor = false;
		graphic = new Sprite("ghost.1", 1, 1.f, this);
	}

	public Demo2Player(Area area, DiscreteCoordinates coordinates)
	{
		this(area, Orientation.DOWN, coordinates);
	}

	// Update the player by changing its position, or orientation depending
	// on which key is pressed
	@Override
	public void update(float deltaTime)
	{
		Keyboard keyboard = getOwnerArea().getKeyboard();

		Button leftArrow = keyboard.get(Keyboard.LEFT);
		Button rightArrow = keyboard.get(Keyboard.RIGHT);
		Button upArrow = keyboard.get(Keyboard.UP);
		Button downArrow = keyboard.get(Keyboard.DOWN);

		if (leftArrow.isDown())
		{
			if (getOrientation().equals(Orientation.LEFT))
			{
				move(ANIMATION_DURATION);
			}

			else
			{
				setOrientation(Orientation.LEFT);
			}
		}

		else if (rightArrow.isDown())
		{
			if (getOrientation().equals(Orientation.RIGHT))
			{
				move(ANIMATION_DURATION);
			}

			else
			{
				setOrientation(Orientation.RIGHT);
			}
		}

		else if (upArrow.isDown())
		{
			if (getOrientation().equals(Orientation.UP))
			{
				move(ANIMATION_DURATION);
			}

			else
			{
				setOrientation(Orientation.UP);
			}
		}

		else if (downArrow.isDown())
		{
			if (getOrientation().equals(Orientation.DOWN))
			{
				move(ANIMATION_DURATION);
			}

			else
			{
				setOrientation(Orientation.DOWN);
			}
		}

		super.update(deltaTime);

	}

	// Verifies if the player is on door and changes its attribute if so
	@Override
	public boolean move(int framesForMove)
	{
		if (getOwnerArea().isDoor(getCurrentMainCellCoordinates()))
		{
			onDoor = true;
		}

		else onDoor = false;

		return super.move(framesForMove);
	}

	// Draws the character
	@Override
	public void draw(Canvas canvas)
	{
		graphic.draw(canvas);

	}

	// When a player enters an area, he registers in it, changes its position and
	// resets its motion
	public void enterArea(Area area, DiscreteCoordinates position)
	{
		area.registerActor(this);

		// Sets the new owner area
		setOwnerArea(area);

		// Changes the Player's position
		setCurrentPosition(position.toVector());

		resetMotion();

		onDoor = false;
	}

	// Unregisters the player from the area he leaves
	public void leaveArea(Area area)
	{
		area.unregisterActor(this);
	}

	// Used for demo3
	@Override
	public void acceptInteraction(AreaInteractionVisitor v)
	{
		// does nothing in demo2
	}

	/***************************** Getters **********************************/

	// Returns true if the player is on a door
	public Boolean isOnDoor()
	{
		return onDoor;
	}

	// Returns the coordinates of the cell the player is on
	@Override
	public List<DiscreteCoordinates> getCurrentCells()
	{
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	// This interactable is non traversable
	@Override
	public boolean takeCellSpace()
	{
		return true;
	}

	// In this demo, the player is not view interactable
	@Override
	public boolean isViewInteractable()
	{
		return false;
	}

	// In this demo, the player is not cell interactable
	@Override
	public boolean isCellInteractable()
	{
		return false;
	}

}
