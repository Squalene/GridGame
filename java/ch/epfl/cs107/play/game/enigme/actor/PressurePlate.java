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

public class PressurePlate extends AreaEntity implements Logic
{
	
	private Sprite graphicOn;
	private Sprite graphicOff;
	private Sprite graphicPlate;
	private final float ACTIVATIONTIME=8*100f;//2sec
	
	private float timer;


	public PressurePlate(Area area,DiscreteCoordinates position)
	{
		super(area, Orientation.DOWN, position);
		
		graphicOn=new Sprite("GroundLightOn",1, 1.f, this);
		graphicOff=new Sprite("GroundLightOff",1, 1.f, this);
		graphicPlate=new Sprite("GroundPlateOff",1, 1.f, this);

		timer=0;
		
	}

	//Accept interaction by allowing the interactor to interact with it
	@Override
	public void acceptInteraction(AreaInteractionVisitor v)
	{
		((EnigmeInteractionVisitor)v).interactWith(this);
	}

	//A pressure plate only takes on place
	@Override
	public List<DiscreteCoordinates> getCurrentCells()
	{
		return Collections.singletonList(getCurrentMainCellCoordinates());	
	}

	//A pressurePlate is never view Interactable	
	@Override
	public boolean isViewInteractable()
	{
		return false;
	}

	//A pressurePlate is always cell Interactable
	@Override
	public boolean isCellInteractable()
	{
		return true;
	}
	
	//A pressure plate never takes Cell space
	@Override
	public boolean takeCellSpace()
	{
		return false;
	}

	//Draw the plate 
	@Override
	public void draw(Canvas canvas)
	{
		graphicPlate.draw(canvas);
		
		if (isOn())
		{
			graphicOn.draw(canvas);
		}
		else graphicOff.draw(canvas);
		
	}
	
	//At each update, substract deltaTime to the timer
	@Override
	public void update(float deltaTime)
	{
		super.update(deltaTime);
		timer=Math.max(0, timer-deltaTime);
	}

	//The plate is on as long as the timer is not null
	@Override
	public boolean isOn()
	{
		return timer!=0;
	}
	
	//activate the plate by reseting its "cooldown" time
	public void activate()
	{
		timer=ACTIVATIONTIME;
	}
}
