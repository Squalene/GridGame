package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class PressureSwitch extends CellSwitchable
{

	private Boolean askedInteraction;
	
	private Boolean acceptInteraction;

	public PressureSwitch(Area area, DiscreteCoordinates position)
	{
		super(area, position, false, "GroundLightOn", "GroundLightOff");
		askedInteraction=false;
		acceptInteraction=true;
	}	
	
	//Must only accept interaction when the movable area entity has just arrived on the spot
	//=> accept interaction if they wan't an interaction asked at the previous update
	@Override 
	public void acceptInteraction(AreaInteractionVisitor v)
	{
		askedInteraction=true;
		
		if(acceptInteraction)
		{
			((EnigmeInteractionVisitor)v).interactWith(this);
			acceptInteraction=false;
		}
	}
	
	//Update, the two boolean so that if there was no asked interaction before, the 
	//cell now accepts interaction
	@Override
	public void update(float deltaTime)
	{
		if(askedInteraction==false)
		{
			acceptInteraction=true;
		}
		super.update(deltaTime);
		
		askedInteraction=false;
		
	}
	

}
