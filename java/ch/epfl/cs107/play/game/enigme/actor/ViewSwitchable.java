
package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class ViewSwitchable extends Switchable
{

	public ViewSwitchable(Area area, DiscreteCoordinates position, Boolean isOn, String graphicOn,
			String graphicOff)
	{
		super(area, position, isOn, graphicOn, graphicOff);
	}

	//A viewswitchable always take CellSpace
	@Override
	public boolean takeCellSpace()
	{
		return true;
	}

	//A viewswitchable is always viewInteractable
	@Override
	public boolean isViewInteractable()
	{
		return true;
	}

	//A viewswitchable is neverCell interactable
	@Override
	public boolean isCellInteractable()
	{
		return false;
	}

}
