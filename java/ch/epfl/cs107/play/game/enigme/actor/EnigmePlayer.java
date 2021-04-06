package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.game.enigme.actor.MovingCharacterSprite;

public abstract class EnigmePlayer extends MovableAreaEntity implements Interactor
{

	private final static int ANIMATION_DURATION = 8;
	
	private Translocator passedTranslocator;
	private Boolean isPassingTranslocator;


	//Sprite that will represent the animated character
	private final MovingCharacterSprite graphic;



	// Inventory of the items collected by the player
	private List<Collectable> inventory;

	// Corresponds to the interaction handler
	private final EnigmePlayerInteractionHandler handler;

	private class EnigmePlayerInteractionHandler implements EnigmeInteractionVisitor
	{
		
		// Interact with a translcator by remembering it in order to pass it
		// during the update
		@Override
		public void interactWith(Translocator translocator)
		{
			setIsPassingTranslocator(translocator);
		}
		

		// Interact with a collectable by pressing the L key and collecting
		// (it is the added in the inventory)
		@Override
		public void interactWith(Collectable item)
		{
			if (getOwnerArea().getKeyboard().get(Keyboard.L).isPressed())
			{
				inventory.add(item);
				item.collect();
			}
		}

		// Interact with a pressureSwitch by reversing it
		@Override
		public void interactWith(PressureSwitch pressureSwitch)
		{
			pressureSwitch.reverse();
		}

		// Interact with a switchable by pressing the L key and reverse its state
		@Override
		public void interactWith(Switchable switchable)
		{
			if (getOwnerArea().getKeyboard().get(Keyboard.L).isPressed())
			{
				switchable.reverse();
			}
		}

		// Interact with a pressure plate activating it
		@Override
		public void interactWith(PressurePlate plate)
		{
			plate.activate();
		}

		// Interact with a pushable by pressing the L key and pushing it
		@Override
		public void interactWith(Pushable object)
		{
			if (getOwnerArea().getKeyboard().get(Keyboard.L).isDown())
			{
				object.push(getOrientation());
			}
		}

		// Accept interaction by allowing the interactor to interact with it
		public void acceptInteraction(AreaInteractionVisitor v)
		{
			((EnigmeInteractionVisitor) v).interactWith(EnigmePlayer.this);
		}
	}

	
	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates,
			String image)
	{
		super(area, orientation, coordinates);

		handler = new EnigmePlayerInteractionHandler();
		passedTranslocator=null;
		isPassingTranslocator=false;
		inventory = new LinkedList<>();
		
		graphic=new MovingCharacterSprite(image,this);
	}
	
	public EnigmePlayer(Area area, DiscreteCoordinates coordinates, String image)
	{
		this(area, Orientation.DOWN, coordinates, image);
	}

	/****************************** EnigmePlayer Flow **********************************/

	// Update the player corresponding to key pressed (initiate or not moving)
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

	// Draw the player corresponding to its orientation
	@Override
	public void draw(Canvas canvas)
	{
		graphic.draw(canvas, getOrientation());
	}

	// Enables a player to enter a new area by register it to its new Area
	// and setting the player new position
	public void enterArea(Area area, DiscreteCoordinates position)
	{

		// Register the actor in the new area
		area.registerActor(this);
		setCurrentPosition(position.toVector());

		// Sets the new owner area
		setOwnerArea(area);

		resetMotion();

		isPassingTranslocator = false;

	}

	// Unregister a player from the area he is leaving
	public void leaveArea(Area area)
	{
		area.unregisterActor(this);
	}

	/***************************** Interactions **********************************/

	// Let the handler deal with the interaction
	@Override
	public void acceptInteraction(AreaInteractionVisitor v)
	{
		handler.acceptInteraction(v);
	}

	// Let the handler deal with the interaction
	public void interactWith(Interactable other)
	{
		other.acceptInteraction(handler);
	}

	/***************************** Setters **********************************/

	
	// Set the translocator the player is passing in order for him to
	// pass it at the update
	private void setIsPassingTranslocator(Translocator translocator)
	{
		passedTranslocator = translocator;
		isPassingTranslocator = true;
	}

	/***************************** Getters **********************************/
	
	// returns the translocator the player is passing
	public Translocator passedTranslocator()
	{
		return passedTranslocator;
	}
	
	// return true if the player is currently passing a translocator
	public Boolean isPassingTranslocator()
	{
		return isPassingTranslocator;
	}

	// returns the cell the player occupies
	@Override
	public List<DiscreteCoordinates> getCurrentCells()
	{
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	// returns the cell in front of the player
	public List<DiscreteCoordinates> getFieldOfViewCells()
	{
		Vector orientV = getOrientation().toVector();

		return Collections.singletonList(getCurrentMainCellCoordinates().jump(orientV));
	}

	// The player is non traversable
	@Override
	public boolean takeCellSpace()
	{
		return true;
	}

	// A player is always view interactable
	@Override
	public boolean isViewInteractable()
	{
		return true;
	}

	// A player is always cell interactable
	@Override
	public boolean isCellInteractable()
	{
		return true;
	}

	// The player always wants contact interaction
	@Override
	public boolean wantsCellInteraction()
	{
		return true;
	}

	// The always wants view interaction
	@Override
	public boolean wantsViewInteraction()
	{
		return true;
	}

}
