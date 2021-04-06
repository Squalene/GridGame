package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class GrassTeleporter extends Translocator
{

	public GrassTeleporter(Area area, String nextArea, DiscreteCoordinates arrivalPosition,
			DiscreteCoordinates coordinates, DiscreteCoordinates ... otherOccupyingCells)
	{
		super(area, nextArea, arrivalPosition, coordinates, "dark.grass", "", Logic.TRUE,
				otherOccupyingCells);
	}

	@Override
	public boolean takeCellSpace()
	{
		return false;
	}

}
