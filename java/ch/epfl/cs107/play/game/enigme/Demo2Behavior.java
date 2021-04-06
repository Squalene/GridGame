package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior
{

	public enum Demo2CellType
	{
		NULL(0), WALL(-16777216), // RGB code of black
		DOOR(-65536), // RGB code of red
		WATER(-16776961), // RGB code of blue
		INDOOR_WALKABLE(-1), OUTDOOR_WALKABLE(-14112955);

		final int type;

		Demo2CellType(int type)
		{
			this.type = type;
		}

		static Demo2CellType toType(int type)
		{
			for (Demo2CellType elem : Demo2CellType.values())
			{
				if (elem.type == type)
				{
					return elem;
				}
			}

			return NULL;
		}

	}

	public class Demo2Cell extends Cell
	{
		Demo2CellType type;

		private Demo2Cell(int x, int y, Demo2CellType type)
		{
			super(x, y);
			this.type = type;
		}

		// In this demo, Wall and Null areas are not traversable
		@Override
		public boolean takeCellSpace()
		{
			return (type == Demo2CellType.WALL || type == Demo2CellType.NULL);
		}

		// In this demo, cell do not accept interaction at a distance
		@Override
		public boolean isViewInteractable()
		{
			return false;
		}

		// In this demo, cell do not accept interaction on contact
		@Override
		public boolean isCellInteractable()
		{
			return false;
		}

		// Returns true if this cellType is door
		public boolean isDoor()
		{
			return type == Demo2CellType.DOOR;
		}

		// Returns true if this cellType is water
		public boolean isWater()
		{
			return type == Demo2CellType.WATER;
		}

		// For now, an interactable can enter as long as the cell is traversable (is not
		// taken by a non-traversable Interactable)
		@Override
		protected boolean canEnter(Interactable entity)
		{
			return !takeCellSpace();
		}

		// Any cell of this demo can be left
		@Override
		protected boolean canLeave(Interactable entity)
		{
			return true;
		}

		// Used for demo3
		@Override
		public void acceptInteraction(AreaInteractionVisitor v)
		{
			// TODO Auto-generated method stub

		}

	}

	public Demo2Behavior(Window window, String fileName)
	{
		super(window, fileName);

		for (int x = 0; x < getWidth(); ++x)
		{
			for (int y = 0; y < getHeight(); ++y)
			{
				Demo2CellType cellType = Demo2CellType
						.toType(getBehaviorMap().getRGB(getHeight() - 1 - y, x));

				setCell(x, y, new Demo2Cell(x, y, cellType));
			}
		}
	}

	/***************************** Getters **********************************/

	// Returns true if there is a door at the coordinates in parameter
	public boolean isDoor(DiscreteCoordinates position)
	{
		return ((Demo2Cell) getCell(position.x, position.y)).isDoor();
	}

}
