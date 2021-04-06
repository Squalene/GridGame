package ch.epfl.cs107.play.game.areagame.actor;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity
{

	/// Indicate if the actor is currently moving
	private boolean isMoving;

	/// Indicate how many frames the current move is supposed to take
	private int framesForCurrentMove;

	/// The target cell (i.e. where the mainCell will be after the motion)
	private DiscreteCoordinates targetMainCellCoordinates;

	/**
	 * Default MovableAreaEntity constructor
	 * 
	 * @param area
	 *            (Area): Owner area. Not null
	 * @param position
	 *            (Coordinate): Initial position of the entity. Not null
	 * @param orientation
	 *            (Orientation): Initial orientation of the entity. Not null
	 */
	public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position)
	{
		super(area, orientation, position);
		resetMotion();
	}

	/**
	 * Initialize or reset the current motion information
	 */
	protected void resetMotion()
	{
		isMoving = false;
		framesForCurrentMove = 0;
		targetMainCellCoordinates = getCurrentMainCellCoordinates();
	}

	/**
	 * 
	 * @param frameForMove
	 *            (int): number of frames used for simulating motion
	 * @return (boolean): returns true if motion can occur
	 */

	protected boolean move(int framesForMove)
	{
		if (!isMoving || targetMainCellCoordinates.equals(getCurrentMainCellCoordinates()))
		{
			if (getOwnerArea().enterAreaCells(this, getEnteringCells())
					&& getOwnerArea().leaveAreaCells(this, getLeavingCells()))
			{
				framesForCurrentMove = Math.max(framesForMove, 1);

				Vector orientation = getOrientation().toVector();
				targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
				isMoving = true;

				return true;
			}

			else return false;
		}

		else return false;
	}

	/// MovableAreaEntity implements Actor

	@Override
	public void update(float deltaTime)
	{
		if (isMoving && !targetMainCellCoordinates.equals(getCurrentMainCellCoordinates()))
		{
			Vector distance = getOrientation().toVector();
			distance = distance.mul(1.0f / framesForCurrentMove);
			setCurrentPosition(getPosition().add(distance));
		}

		else resetMotion();
	}

	/***************************** Getters **********************************/

	// returns the velocity of the entity
	@Override
	public Vector getVelocity()
	{
		// the velocity must be computed as the orientation vector
		// (getOrientation().toVector() multiplied by framesForCurrentMove
		return getOrientation().toVector().mul(framesForCurrentMove);
	}

	// returns true if the entity is currently moving
	public Boolean isMoving()
	{
		return isMoving;
	}

	// returns the cells the entity is aiming to go to
	final protected List<DiscreteCoordinates> getEnteringCells()
	{
		Vector orientV = getOrientation().toVector();

		List<DiscreteCoordinates> enteringCells = new ArrayList<DiscreteCoordinates>();

		for (DiscreteCoordinates position : getCurrentCells())
		{
			enteringCells.add(position.jump(orientV));
		}

		return enteringCells;
	}

	// returns the cells the entity is leaving
	final protected List<DiscreteCoordinates> getLeavingCells()
	{
		List<DiscreteCoordinates> leavingCells = getCurrentCells();

		return leavingCells;
	}

	/***************************** Setters **********************************/

	// Sets the orientation of the entity
	@Override
	protected void setOrientation(Orientation orientation)
	{
		if (!isMoving)
		{
			super.setOrientation(orientation);
		}
	}

}
