package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class GhostPlayer extends EnigmePlayer
{

	public GhostPlayer  (Area area, Orientation orientation, DiscreteCoordinates coordinates)
	{
		super(area, orientation, coordinates,"max.ghost");
	}
	
	public GhostPlayer (Area area, DiscreteCoordinates coordinates)
	{
		super(area, coordinates,"max.ghost");
	}
}
