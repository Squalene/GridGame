package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;


public class Key extends Collectable implements Logic
{

	public Key(Area area,DiscreteCoordinates position)
	{
		super(area,position,"key.1");
	}

	@Override
	public boolean isOn()
	{
		return isCollected();
	}
}
