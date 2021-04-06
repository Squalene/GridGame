package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class BoyPlayer extends EnigmePlayer
{

	public BoyPlayer (Area area, Orientation orientation, DiscreteCoordinates coordinates)
	{
		super(area, orientation, coordinates,"max.new.3");
	}
	
	public BoyPlayer (Area area, DiscreteCoordinates coordinates)
	{
		super(area, coordinates,"max.new.3");
	}

}
