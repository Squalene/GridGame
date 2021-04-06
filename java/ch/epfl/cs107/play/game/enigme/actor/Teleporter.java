package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Teleporter extends Translocator
{

	public Teleporter(Area area, String nextArea, DiscreteCoordinates arrivalPosition,
			DiscreteCoordinates coordinates, Logic signal, DiscreteCoordinates... otherOccupyingCells)
	{
		super(area, nextArea, arrivalPosition, coordinates, "teleporter.on", 
				"teleporter.off", signal,otherOccupyingCells);
	}
	
	@Override
	public boolean takeCellSpace()
	{
		return false;
	}

}
