package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.game.actor.Entity;

/**
 * Actors leaving in a grid
 */
public abstract class AreaEntity extends Entity implements Interactable
{

	// The area the areaEntity occupies
	private Area ownerArea;

	/// Orientation of the AreaEntity in the Area
	private Orientation orientation;

	/// Coordinate of the main Cell the entity occupies
	private DiscreteCoordinates currentMainCellCoordinates;

	/**
	 * Default AreaEntity constructor
	 * 
	 * @param area
	 *            (Area): Owner area. Not null
	 * @param orientation
	 *            (Orientation): Initial orientation of the entity in the Area. Not
	 *            null
	 * @param position
	 *            (DiscreteCoordinate): Initial position of the entity in the Area.
	 *            Not null
	 */
	public AreaEntity(Area area, Orientation orientation, DiscreteCoordinates position)
	{
		super(position.toVector());
		ownerArea = area;
		this.orientation = orientation;
		currentMainCellCoordinates = position;
	}

	/***************************** Getters **********************************/

	/**
	 * Getter for the coordinates of the main cell occupied by the AreaEntity
	 * 
	 * @return (DiscreteCoordinates)
	 */
	protected DiscreteCoordinates getCurrentMainCellCoordinates()
	{
		return currentMainCellCoordinates;
	}

	// returns the area the areaEntity is currently in
	protected Area getOwnerArea()
	{
		return ownerArea;
	}

	// returns the orientation of the areaEntity is currently in
	protected Orientation getOrientation()
	{
		return orientation;
	}

	/***************************** Setters **********************************/

	/*
	 * Update the current position (i.e. after motion) translate coordinates into
	 * discrete ones if they are close enough
	 */
	@Override
	protected void setCurrentPosition(Vector v)
	{
		if (DiscreteCoordinates.isCoordinates(v))
		{
			Vector roundedV = v.round();
			super.setCurrentPosition(roundedV);
			DiscreteCoordinates newCoordinates = new DiscreteCoordinates((int) roundedV.getX(),
					(int) roundedV.getY());
			currentMainCellCoordinates = newCoordinates;
		}

		else super.setCurrentPosition(v);
	}

	protected void setOrientation(Orientation orientation)
	{
		this.orientation = orientation;
	}

	protected void setOwnerArea(Area newArea)
	{
		ownerArea = newArea;
	}

}
