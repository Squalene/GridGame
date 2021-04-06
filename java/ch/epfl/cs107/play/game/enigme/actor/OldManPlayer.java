package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class OldManPlayer extends EnigmePlayer
{
	public OldManPlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates)
	{
		super(area, orientation, coordinates,"old.man.1");
	}
	
	public OldManPlayer(Area area, DiscreteCoordinates coordinates)
	{
		super(area, coordinates,"old.man.1");
	}
}
