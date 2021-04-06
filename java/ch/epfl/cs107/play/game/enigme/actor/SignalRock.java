package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalRock extends AreaEntity
{
	
	private Sprite graphic;
	private Logic signal;
	
	public SignalRock(Area area,DiscreteCoordinates position,Logic signal)
	{
		super(area, Orientation.DOWN, position);
		
		this.signal=signal;
		graphic=new Sprite ("rock.3", 1, 1.f, this);
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v)
	{
	 //does nothing
	}

	//the rock only occupies one spot
	@Override
	public List<DiscreteCoordinates> getCurrentCells()
	{
		return Collections.singletonList(getCurrentMainCellCoordinates());	
	}

	//The rock takes cell space when its signal is off
	@Override
	public boolean takeCellSpace()
	{
		return !signal.isOn();
	}
	
	//A Signalrock is never view interactable
	@Override
	public boolean isViewInteractable()
	{
		return false;
	}

	//A Signalrock is never cell interactable
	@Override
	public boolean isCellInteractable()
	{
		return false;
	}

	//Draw the rock
	@Override
	public void draw(Canvas canvas)
	{
		
		if(!signal.isOn())
		{
			graphic.draw(canvas);
		}
		
	}

}
