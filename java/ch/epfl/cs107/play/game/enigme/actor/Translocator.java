package ch.epfl.cs107.play.game.enigme.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;


//Generlisation of a device that permits teleportation between areas or 
//in the same area, could be a door or a teleporter
public abstract class Translocator extends AreaEntity
{
	
	private Sprite graphicOff; 
	private Sprite graphicOn; 
	
	protected Logic signal;
	
	//Name of the area he leads the actor to
	private String nextArea;

	private DiscreteCoordinates arrivalPosition;
	
	
	List <DiscreteCoordinates> occupyingCells;
	
	
	public Translocator(Area area,String nextArea,DiscreteCoordinates arrivalPosition,
			DiscreteCoordinates coordinates,String graphicOn,String graphicOff,Orientation orientation, Logic signal,
			DiscreteCoordinates ...otherOccupyingCells)
	{
		super(area, orientation, coordinates);
		
		this.occupyingCells=new LinkedList<DiscreteCoordinates>();
		
		
		//Initialize the doors occupying cells with new DiscreteCoordinates in memory
		for (DiscreteCoordinates cell:otherOccupyingCells)
		{
			this.occupyingCells.add(new DiscreteCoordinates(cell));
		}
		
		this.occupyingCells.add(coordinates);
		
		this.nextArea=nextArea;
		this.arrivalPosition=arrivalPosition;
		
		this.signal=signal;
		
		this.graphicOff=new Sprite(graphicOff, 1, 1.f, this);
		this.graphicOn=new Sprite(graphicOn, 1, 1.f, this);
	}	
	
	public Translocator(Area area,String nextArea,DiscreteCoordinates arrivalPosition,
			DiscreteCoordinates coordinates,String graphicOn,String graphicOff, Logic signal,
			DiscreteCoordinates ...otherOccupyingCells)
	{
		this(area,nextArea,arrivalPosition,coordinates, graphicOn,graphicOff,
				Orientation.DOWN,signal,otherOccupyingCells);
	}
	


	//returns the cells the door occupies
	@Override
	public List<DiscreteCoordinates> getCurrentCells()
	{
		return occupyingCells;
	}

	//A translocator is never view interactable
	@Override
	public boolean isViewInteractable()
	{
		return false;
	}
	
	//A translocator is always view interactable
	@Override
	public boolean isCellInteractable()
	{
		return true;
	}
	
	//Returns the name of the area this translocator leads to
	public String getNextArea()
	{
		return nextArea;
	}
	
	//Returns the coordinates of the arrival position this door leads to
	public DiscreteCoordinates getArrivalPosition()
	{
		return arrivalPosition;
	}
	
	//Draw the translocator according to its state (on or off)
	@Override
	public void draw(Canvas canvas)
	{
		if(signal.isOn())
		{
			graphicOn.draw(canvas);
		}
			
		else graphicOff.draw(canvas);
	}

	
	//A translocator accepts an interaction if its signal is on
	@Override
	public void acceptInteraction(AreaInteractionVisitor v)
	{
		if(signal.isOn())
		{
			((EnigmeInteractionVisitor)v).interactWith(this);
		}
	}
	
	
		
}
