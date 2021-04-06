package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

//Responsable:Daniel

/**
 * Models objects asking for interaction (i.e. can interact with some Interactable)
 * @see Interactable
 * This interface makes sense only in the "Area Context" with Actor contained into Area Cell
 */
public interface Interactor {

	/*
	 * Returns the list of cells occupied by this object
	 * as a list of coordinates
	 */
	List<DiscreteCoordinates> getCurrentCells();
	
	/*
	 * Returns the list of cells that this object can see
	 * as a list of coordinates
	 */
	List<DiscreteCoordinates> getFieldOfViewCells();
	
	/*
	 * Returns true if the Interactor want interaction on contact
	 */
	boolean wantsCellInteraction();
	
	/*
	 * Returns true if the Interactor want interaction at a distance
	 */
	boolean wantsViewInteraction();
	
	/*
	 * This method defines what an interaction with an interactable does
	 */
	void interactWith(Interactable other);

}
