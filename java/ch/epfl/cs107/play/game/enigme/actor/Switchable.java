package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
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

public abstract class Switchable extends AreaEntity implements Logic
{
	
	protected Sprite graphicOn;
	protected Sprite graphicOff;
	protected Boolean isOn;
	
	public Switchable(Area area, DiscreteCoordinates position,
			  Boolean isOn, String graphicOn,String graphicOff )
	{
		super(area, Orientation.DOWN, position);
		
		this.graphicOn=new Sprite(graphicOn,1, 1.f, this);
		this.graphicOff=new Sprite(graphicOff,1, 1.f, this);
		
		this.isOn=isOn;
	}
	
	//Enables the interactor to interact with the switchable 
	@Override
	public void acceptInteraction(AreaInteractionVisitor v)
	{
		((EnigmeInteractionVisitor)v).interactWith(this);	
	}

	//A switchable only takes one cellSpace
	@Override
	public List<DiscreteCoordinates> getCurrentCells()
	{
		return Collections.singletonList(getCurrentMainCellCoordinates());	
	}

	//Draw the switchable according to its state
	@Override
	public void draw(Canvas canvas)
	{
		if (isOn)
		{
			graphicOn.draw(canvas);
		}
		else graphicOff.draw(canvas);
	}
	
	//Reverse the state of the switchable 
	public void reverse()
	{
		isOn=!isOn;
	}
	
	//Return true if the state of the switchable is on
	@Override 
	public boolean isOn()
	{
		return isOn;
	}

}
