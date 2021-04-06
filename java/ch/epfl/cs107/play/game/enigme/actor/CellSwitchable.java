
package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class CellSwitchable extends Switchable 
{

	public CellSwitchable(Area area, DiscreteCoordinates position, Boolean isOn, String graphicOn,
			String graphicOff)
	{
		super(area, position, isOn, graphicOn, graphicOff);
	}

	//A cellSwitchable never takes CellSpace
	@Override
	public boolean takeCellSpace()
	{
		return false;
	}

	//A cellSwitchable is never view interactable
	@Override
	public boolean isViewInteractable()
	{
		return false;
	}

	//A cellSwitchable is always cell interactable
	@Override
	public boolean isCellInteractable()
	{
		return true;
	}

}
