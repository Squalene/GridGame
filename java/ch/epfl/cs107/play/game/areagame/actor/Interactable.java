package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

//Responsable:Daniel

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with them)
 * @see Interactor
 * This interface makes sense only in the "AreaGame" context with Actor contained into Area Cell
 */
public interface Interactable
{    
	
	/*
	 * Deals with the interaction of an interactable 
	 * with an interactor 
	 */
	
	void acceptInteraction(AreaInteractionVisitor v);
	
	/*
	 * Returns the space that this object occupies
	 * as a list of DiscreteCoordinates
	 */
	abstract List<DiscreteCoordinates> getCurrentCells();
	
	/*
	 * Indicates if this object can deny other objects from occupying the same space
	 * returns true if it is "non-traversable"
	 */
	abstract boolean takeCellSpace();
	
	/*
	 * Indicates if this object can be interacted with at a distance
	 * returns true if it is "interactable at distance"
	 */
	abstract boolean isViewInteractable();
	
	/*
	 * Indicates if this object can be interacted with on the same cell
	 * returns true if it is "interactable on contact"
	 */
	abstract boolean isCellInteractable();
}
