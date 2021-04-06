package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class GirlPlayer extends EnigmePlayer
{

	public GirlPlayer (Area area, Orientation orientation, DiscreteCoordinates coordinates)
	{
		super(area, orientation, coordinates,"girl.3");
	}
	
	public GirlPlayer (Area area, DiscreteCoordinates coordinates)
	{
		super(area, coordinates,"girl.3");
	}
	
}
