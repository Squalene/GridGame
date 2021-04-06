package ch.epfl.cs107.play.game.areagame;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior
{
	/// The behavior is an Image of size height x width
	private final Image behaviorMap;

	// Width and height in number of cells of the areaBehavior
	private final int width, height;

	/// We will convert the image into an array of cells
	private final Cell[][] cells;

	// A cell is a discrete elements of the areaBehavior that can contain
	// interactables in it and is defined by its discreteCoordinates

	public abstract class Cell implements Interactable
	{
		private DiscreteCoordinates coord;
		private Set<Interactable> interactables;

		public Cell(int x, int y)
		{
			coord = new DiscreteCoordinates(x, y);
			interactables = new HashSet<>();
		}

		// Returns the discrete coordinates of the cell as an Array List
		@Override
		public List<DiscreteCoordinates> getCurrentCells()
		{
			return Arrays.asList(coord);
		}

		// Adds an interactables to the cell
		private void enter(Interactable e)
		{
			interactables.add(e);
		}

		// Removes an interactable from the cell
		private void leave(Interactable e)
		{
			interactables.remove(e);
		}

		// Deals with the contact interaction of an interactor
		// with all the interactables entities of the cell
		private void cellInteractionOf(Interactor interactor)
		{
			for (Interactable interactable : interactables)
			{
				if (interactable.isCellInteractable())
				{
					interactor.interactWith(interactable);

				}

			}
		}

		// Deals with the distance interaction of an interactor
		// with all the interactables entities of the cell
		private void viewInteractionOf(Interactor interactor)
		{
			for (Interactable interactable : interactables)
			{
				if (interactable.isViewInteractable())
				{
					interactor.interactWith(interactable);
				}
			}
		}

		// This cell is non traversable if it contains at least
		// one non traversable interactable
		@Override
		public boolean takeCellSpace()
		{
			for (Interactable interactable : interactables)
			{
				if (interactable.takeCellSpace())
				{
					return true;
				}
			}

			return false;
		}

		// An interactable can enter in the cell if its
		// space is not taken
		protected boolean canEnter(Interactable entity)
		{
			return (!takeCellSpace());

		}

		// Determines if an interactable can leave the cell,
		// has to be redefined
		protected abstract boolean canLeave(Interactable entity);

	}

	/**
	 * Default AreaBehavior Constructor
	 * 
	 * @param window
	 *            (Window): graphic context, not null
	 * @param fileName
	 *            (String): name of the file containing the behavior image, not null
	 */
	public AreaBehavior(Window window, String fileName)
	{
		behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
		width = behaviorMap.getWidth();
		height = behaviorMap.getHeight();
		cells = new Cell[width][height];
	}

	/****************************** Enter/leave cells **********************************/

	// An entity can leave if it can leave from all of the cells it occupies
	public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates)
	{
		for (DiscreteCoordinates position : coordinates)
		{
			if (!cells[position.x][position.y].canLeave(entity))
			{
				return false;
			}
		}
		return true;
	}

	// An entity can leave if it can enter in all the cells it wants to occupy
	public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates)
	{
		for (DiscreteCoordinates position : coordinates)
		{
			if (position.x >= width || position.x < 0 || position.y >= height || position.y < 0
					|| !cells[position.x][position.y].canEnter(entity))

			{
				return false;
			}
		}
		return true;
	}

	// The entity leaves from all the cells it occupied
	protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates)
	{
		for (DiscreteCoordinates position : coordinates)
		{
			cells[position.x][position.y].leave(entity);
		}

	}

	// The entity enters in all the cells it wants to occupy
	protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates)
	{
		for (DiscreteCoordinates position : coordinates)
		{
			cells[position.x][position.y].enter(entity);
		}

	}

	/***************************** Interactions **********************************/

	// For every cell the interactor occupies, it contact interacts with
	// all the interactables in the cell
	public void cellInteractionOf(Interactor interactor)
	{

		List<DiscreteCoordinates> interactorCells = interactor.getCurrentCells();

		for (DiscreteCoordinates position : interactorCells)
		{
			cells[position.x][position.y].cellInteractionOf(interactor);
		}

	}

	// For every cell the interactor occupies, it distance interacts with
	// all the interactables in the cell
	public void viewInteractionOf(Interactor interactor)
	{

		List<DiscreteCoordinates> interactorCells = interactor.getFieldOfViewCells();

		for (DiscreteCoordinates position : interactorCells)
		{
			if (position.x > 0 && position.x < width && position.y > 0 && position.y < height)
			{
				cells[position.x][position.y].viewInteractionOf(interactor);
			}
		}

	}

	/***************************** Setters **********************************/

	// Set a cell at position (x,y) to be a certain cell
	protected void setCell(int x, int y, Cell cell)
	{
		cells[x][y] = cell;
	}

	/***************************** Getters **********************************/

	// returns the cell at position (x,y)
	protected Cell getCell(int x, int y)
	{
		return cells[x][y];
	}

	// returns the height of the grid
	public int getHeight()
	{
		return height;
	}

	// returns the width of the grid
	public int getWidth()
	{
		return width;
	}

	// returns the image of the behaviorMap
	public Image getBehaviorMap()
	{
		return behaviorMap;
	}
}
