package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class PushableRock extends Pushable
{
	public PushableRock(Area area,DiscreteCoordinates position)
	{
		super(area, position, "rock.2");
	}
}
