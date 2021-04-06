package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class StaticRock extends SignalRock
{
	public StaticRock(Area area, DiscreteCoordinates position)
	{
		super(area, position, Logic.FALSE);
	}
}
