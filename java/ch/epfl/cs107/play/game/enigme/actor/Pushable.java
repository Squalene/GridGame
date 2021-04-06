package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;

import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;


public abstract class Pushable extends MovableAreaEntity implements Interactor
{
	
	private PushableInteractionHandler handler;
	
	private final static int ANIMATION_DURATION = 8;
	
	Sprite graphic;
	
	
	private class PushableInteractionHandler implements EnigmeInteractionVisitor
	{ 
		//interact with a plate by activating it
		@Override
		public void interactWith(PressurePlate plate) 
		{
			plate.activate();
		}
		
		//interact with a switch by pressing on it
		public void interactWith(PressureSwitch pressureswitch) 
		{
			pressureswitch.reverse();
		}
		
		//enables the interactor to interact with it
		public void acceptInteraction(AreaInteractionVisitor v)
		{
			((EnigmeInteractionVisitor)v).interactWith(Pushable.this);
		}
	}
	

	public Pushable(Area area, Orientation orientation, DiscreteCoordinates position, String graphic)
	{
		super(area, orientation, position);
		
		this.graphic=new Sprite(graphic, 1, 1.f, this);
		handler=new PushableInteractionHandler();
	}
	
	public Pushable(Area area,DiscreteCoordinates position, String graphic)
	{
		this(area, Orientation.DOWN, position,graphic);
	}
	
	//Draw the pushable
	@Override
	public void draw(Canvas canvas)
	{
		graphic.draw(canvas);
	}
	
	//Let the handler handle the interaction
	public void interactWith(Interactable other)
	{ 
		other.acceptInteraction(handler);
	}
	
	//Let the handler handle the interaction
	@Override
	public void acceptInteraction(AreaInteractionVisitor v)
	{
		handler.acceptInteraction(v);		
	}
	
	//A pushable always want cell interaction 
	//(in order to switch a pressure switch for example) 
	@Override
	public boolean wantsCellInteraction()
	{
		return true;
	}
	
	//A pushable never wants view interaction
	@Override
	public boolean wantsViewInteraction()
	{	
		return false;
	}

	//A pushable always take cell space
	@Override
	public boolean takeCellSpace()
	{
		return true;
	}

	//A pushable is always view interactable
	@Override
	public boolean isViewInteractable()
	{
		return true;
	}

	//A pushable is never cell interactable
	@Override
	public boolean isCellInteractable()
	{
		return false;
	}
	
	//A pushable onlu takes one cell, otherwise it is too big to be pushed
	@Override
	public List<DiscreteCoordinates> getCurrentCells()
	{
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
	
	//Never used in the case of a pushable
	public List <DiscreteCoordinates>getFieldOfViewCells()
	{
		return null;
	}
	
	//Pushes the rock in a direction 
	public void push(Orientation orientation)
	{
		setOrientation(orientation);
		move(ANIMATION_DURATION);
	}
}
