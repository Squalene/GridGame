package ch.epfl.cs107.play.game.enigme.actor;


import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;


public class Apple extends Collectable implements Logic
{
	
	public Apple (Area area, DiscreteCoordinates coordinates)
	{
		super(area, coordinates,"apple.1");
	}
	
	//An apple signals is on when it is collected
	@Override
	public boolean isOn()
	{
		return isCollected();
	}

}
