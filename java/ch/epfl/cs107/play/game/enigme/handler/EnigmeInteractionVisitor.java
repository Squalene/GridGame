package ch.epfl.cs107.play.game.enigme.handler;


import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Collectable;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.Pushable;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Switchable;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.game.enigme.actor.Translocator;



//Interface that MUST code every default interaction with ANY interactable in the game
//The methode dealing with general Interactables is in AreaInteractionVisitor

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor
{
	
	/**
	* Simulate and interaction between a enigme Interactors
	and an enigme Apple
	* @param apple (Apple), not null */
	default void interactWith(Apple apple)
	{
	// by default the interaction is empty
	}
	
	default void interactWith(Door door)
	{
	// by default the interaction is empty
	}
	
	default void interactWith(EnigmePlayer player)
	{
	// by default the interaction is empty
	}
	
	default void interactWith(Collectable item) 
	{
	// by default the interaction is empty
	}
	
	default void interactWith(EnigmeBehavior.EnigmeCell cell)
	{ 
		// by default the interaction is empty
	}
	
	default void interactWith(Switchable switchable)
	{ 
		// by default the interaction is empty
	}
	
	default void interactWith(PressurePlate plate)
	{ 
		// by default the interaction is empty
	}
	
	default void interactWith(SignalRock rock)
	{
		// by default the interaction is empty
	}
	
	default void interactWith(Pushable object) 
	{
		// by default the interaction is empty
	}
	
	default void interactWith(PressureSwitch pressureSwitch)
	{
		// by default the interaction is empty
	}
	
	default void interactWith(Lever lever)
	{
		// by default the interaction is empty
	}
	
	default void interactWith(Torch torch)
	{
		// by default the interaction is empty
	}
	
	default void interactWith(Translocator translocator)
	{
		// by default the interaction is empty
	} 
	

	
	

}
