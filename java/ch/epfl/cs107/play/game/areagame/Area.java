package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and
 * a List of Actors
 */
public abstract class Area implements Playable
{

	// Context objects
	private Window window;
	private FileSystem fileSystem;

	// Determines the behavior of the area
	private AreaBehavior areaBehavior;

	// If true, the area is on pause
	// (it doesn't move but continues redrawing the content)
	private Boolean pause;

	// List of actors inside the area
	private List<Actor> actors;

	// List of actors/interactors that are going to leave or enter the area at the
	// next update
	private List<Actor> registeredActors;
	private List<Actor> unregisteredActors;
	private List<Interactor> interactors;

	// Map of interactable that are going to enter or leave cells at the next update
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToEnter;
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToLeave;

	// Camera Parameter
	// actor on which the view is centered
	private Actor viewCandidate;

	// effective center of the view
	private Vector viewCenter;

	/**************************** Camera & View ******************************/

	/**
	 * @return (float): camera scale factor, assume it is the same in x and y
	 *         direction
	 */
	public abstract float getCameraScaleFactor();

	// Sets the view candidate of the area
	public final void setViewCandidate(Actor a)
	{
		this.viewCandidate = a;
	}

	// Updates the camera by choosing where it points to
	private void updateCamera()
	{

		if (viewCandidate != null)
		{
			viewCenter = viewCandidate.getPosition();
		}

		float scaleFactor = getCameraScaleFactor();

		// Compute new viewport, in order for the camera to be centered
		// at the view center and for it to be scaled to the window(scaleFactor)
		Transform viewTransform = Transform.I.scaled(scaleFactor).translated(viewCenter);

		window.setRelativeTransform(viewTransform);
	}

	/**************************** Actors update ***********************/

	/**
	 * Add an actor to the actors list
	 * 
	 * @param a
	 *            (Actor): the actor to add, not null
	 * @param forced
	 *            (Boolean): if true, the method ends
	 */

	// Must be changed accordingly to criterias we fixed
	private void addActor(Actor a, boolean forced)
	{
		// Here decisions at the area level to decide if an actor
		// must be added or not


		boolean errorOccured = !agreeToAdd(a);

		errorOccured = errorOccured || !actors.add(a);

		if (a instanceof Interactable)
		{
			// Verify if the actor can enter the cells he is aiming for
			errorOccured = errorOccured
					|| !enterAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());

		}

		if (a instanceof Interactor)
		{
			errorOccured = errorOccured || !interactors.add((Interactor) a);

		}

		if (errorOccured && !forced)
		{
			System.out.println("Actor " + a + " cannot be completely added, "
					+ "so remove it from where it was");

			removeActor(a, true);
		}

	}

	/**
	 * Remove an actor form the actor list
	 * 
	 * @param a
	 *            (Actor): the actor to remove, not null
	 * @param forced
	 *            (Boolean): if true, the method ends
	 */
	private void removeActor(Actor a, boolean forced)
	{

		boolean errorOccured = !agreeToRemove(a);

		if (a instanceof Interactable)
		{
			// Verify if the actor can leave the cells he is in
			errorOccured = errorOccured
					|| !leaveAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());

		}

		if (a instanceof Interactor)
		{
			errorOccured = errorOccured || !interactors.remove((Interactor) a);
		}

		// Peut etre pas necessaire mais on remove l'actor apres le reste
		errorOccured = errorOccured || !actors.remove(a);

		if (errorOccured && !forced)
		{
			System.out.println("Actor " + a + " cannot be removed added, " + "so add it from where it was");

			addActor(a, true);
		}
	}

	// This method decides if a certain actor can be added to the area
	private final boolean agreeToAdd(Actor a)
	{
		return true;
	}

	// This method decides if a certain actor can be removed from the area
	private final boolean agreeToRemove(Actor a)
	{
		return true;
	}

	/**
	 * Register an actor : will be added at next update
	 * 
	 * @param a
	 *            (Actor): the actor to register, not null
	 * @return (boolean): true if the actor is correctly registered
	 */
	public final boolean registerActor(Actor a)
	{
		registeredActors.add(a);
		return true;
	}

	/**
	 * Unregister an actor : will be removed at next update
	 * 
	 * @param a
	 *            (Actor): the actor to unregister, not null
	 * @return (boolean): true if the actor is correctly unregistered
	 */
	public final boolean unregisterActor(Actor a)
	{
		unregisteredActors.add(a);
		return true;
	}

	// Update registered and unregistered actors as well as
	// interactables allowed to enter or leave cells
	final private void purgeRegistration()
	{

		// Remove unregistered actors from the Area
		for (Actor ua : unregisteredActors)
		{
			removeActor(ua, false);
		}

		// Add registered actors to the Area
		for (Actor ra : registeredActors)
		{
			addActor(ra, false);
		}

		// Add interactables (allowed to enter) to their cells
		for (Entry<Interactable, List<DiscreteCoordinates>> pair : interactablesToEnter.entrySet())
		{
			areaBehavior.enter(pair.getKey(), pair.getValue());
		}

		// Remove interactables (allowed to leave) from their cells
		for (Entry<Interactable, List<DiscreteCoordinates>> pair : interactablesToLeave.entrySet())
		{
			areaBehavior.leave(pair.getKey(), pair.getValue());
		}

		// Empty the lists and maps
		registeredActors.clear();
		unregisteredActors.clear();
		interactablesToEnter.clear();
		interactablesToLeave.clear();
	}

	/**************************** Interactable update ***********************/

	// If an interactable can leave its areaCell, it will leave the area cell at the
	// next update
	public final boolean leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates)
	{

		if (areaBehavior.canLeave(entity, coordinates))
		{
			interactablesToLeave.put(entity, coordinates);
			return true;
		}

		else return false;
	}

	// If an interactable can enter the areaCell he is aiming for, it will enter
	// this area cell at the next update
	public final boolean enterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates)
	{

		if (areaBehavior.canEnter(entity, coordinates))
		{
			interactablesToEnter.put(entity, coordinates);
			return true;
		}

		else return false;
	}

	/***************************** Area flow **********************************/

	// Initialize the area
	@Override
	public boolean begin(Window window, FileSystem fileSystem)
	{

		System.out.println("Begin the area: " + getTitle());
		this.window = window;
		this.fileSystem = fileSystem;

		viewCenter = Vector.ZERO;
		viewCandidate = null;

		actors = new LinkedList<>();
		registeredActors = new LinkedList<>();
		unregisteredActors = new LinkedList<>();
		interactors = new LinkedList<>();
		interactablesToEnter = new HashMap<>();
		interactablesToLeave = new HashMap<>();

		pause = false;

		return true;
	}

	// Updates the area by updating its camera, entities

	@Override
	public void update(float deltaTime)
	{
		if (getKeyboard().get(Keyboard.SPACE).isPressed())
		{
			pause = !pause;
		}

		// updates the camera
		updateCamera();

		// update registered and unregistered actors
		purgeRegistration();

		if (!pause)
		{
			for (Actor actor : actors)
			{
				actor.update(deltaTime);
				// System.out.println(actor.toString());
			}

			for (Interactor interactor : interactors)
			{
				if (interactor.wantsCellInteraction())
				{
					areaBehavior.cellInteractionOf(interactor);
				}

				if (interactor.wantsViewInteraction())
				{
					areaBehavior.viewInteractionOf(interactor);
				}
			}
		}

		for (Actor actor : actors)
		{
			actor.draw(window);
		}
	}

	/**
	 * Resume method: Can be overridden
	 * 
	 * @param window
	 *            (Window): display context, not null
	 * @param fileSystem
	 *            (FileSystem): given file system, not null
	 * @return (boolean) : if the resume succeed, true by default
	 */
	public boolean resume(Window window, FileSystem fileSystem)
	{
		System.out.println("Resume: " + getTitle());
		return true;
	}

	/**
	 * Suspend method: Can be overridden, called before resume other
	 */
	public void suspend()
	{
		purgeRegistration();
	}

	@Override
	public void end()
	{
		// does nothing
	}

	/***************************** Getters **********************************/

	/**
	 * Getter for the area width
	 * 
	 * @return (int) : the width in number of cols
	 */
	public final int getWidth()
	{
		return areaBehavior.getWidth();
	}

	/**
	 * Getter for the area height
	 * 
	 * @return (int) : the height in number of rows
	 */
	public final int getHeight()
	{
		return areaBehavior.getHeight();
	}

	/** @return the Window Keyboard for inputs */
	public final Keyboard getKeyboard()
	{
		return window.getKeyboard();
	}

	// Method only used for demo2, not all areas should have a door
	public final Boolean isDoor(DiscreteCoordinates position)
	{
		return ((Demo2Behavior) areaBehavior).isDoor(position);
	}

	/***************************** Setters **********************************/

	// Sets the areaBehavior of this area
	final protected void setBehavior(AreaBehavior ab)
	{
		areaBehavior = ab;
	}

}
