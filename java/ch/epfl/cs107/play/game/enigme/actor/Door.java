package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Door extends Translocator
{

	public Door(Area area, String nextArea, DiscreteCoordinates arrivalPosition,
			DiscreteCoordinates coordinates, Logic signal,
			DiscreteCoordinates... otherOccupyingCells)
	{
		super(area, nextArea, arrivalPosition, coordinates, "door.open.2", "door.close.2", signal,
				otherOccupyingCells);
	}
	
	public Door(Area area, String nextArea, DiscreteCoordinates arrivalPosition,
			DiscreteCoordinates coordinates,Orientation orientation, Logic signal,
			DiscreteCoordinates... otherOccupyingCells)
	{
		super(area, nextArea, arrivalPosition, coordinates, "door.open.2", "door.close.2", orientation,signal,
				otherOccupyingCells);
	}

	@Override
	public boolean takeCellSpace()
	{
		return !signal.isOn();
	}

}
