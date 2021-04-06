package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Lever extends ViewSwitchable
{	
	public Lever(Area area, DiscreteCoordinates position)
	{
		super(area, position, false, "lever.big.left", "lever.big.right");
	}
}
