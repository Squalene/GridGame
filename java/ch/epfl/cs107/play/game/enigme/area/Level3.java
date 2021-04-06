package ch.epfl.cs107.play.game.enigme.area;


import java.util.Collections;


import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.GroundTorch;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Not;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea
{
	//Initialize the area 
	@Override
	public boolean begin(Window window, FileSystem fileSystem) 
    {
		super.begin(window,fileSystem);
		
		Key key=new Key(this,new DiscreteCoordinates(1,3));
		
		//A light on torch
		GroundTorch groundTorch=new GroundTorch(this,new DiscreteCoordinates(7,5),true);
		
		PressurePlate pressurePlate=new PressurePlate(this,new DiscreteCoordinates(9,8));
		
		PressureSwitch pressureSwitch1=new PressureSwitch(this,new DiscreteCoordinates(4,4));
		PressureSwitch pressureSwitch2=new PressureSwitch(this,new DiscreteCoordinates(5,4));
		PressureSwitch pressureSwitch3=new PressureSwitch(this,new DiscreteCoordinates(6,4));
		PressureSwitch pressureSwitch4=new PressureSwitch(this,new DiscreteCoordinates(5,5));
		PressureSwitch pressureSwitch5=new PressureSwitch(this,new DiscreteCoordinates(4,6));
		PressureSwitch pressureSwitch6=new PressureSwitch(this,new DiscreteCoordinates(5,6));
		PressureSwitch pressureSwitch7=new PressureSwitch(this,new DiscreteCoordinates(6,6));
		
		Lever lever1=new Lever(this,new DiscreteCoordinates(10,5));
		Lever lever2=new Lever(this,new DiscreteCoordinates(9,5));
		Lever lever3=new Lever(this,new DiscreteCoordinates(8,5));
		
		
		Logic doorSignal=key;
		
		Logic rock1Signal=pressurePlate;
		
		Logic rock2Signal=new MultipleAnd(pressureSwitch1,pressureSwitch2,pressureSwitch3,pressureSwitch4,
										  pressureSwitch5,pressureSwitch6,pressureSwitch7);
		
		Logic rock3Signal=new Or(new MultipleAnd(lever1,new Not(lever2),lever3),groundTorch);
		
		
		SignalRock rock1=new SignalRock(this,new DiscreteCoordinates(6,8),rock1Signal);
		SignalRock rock2=new SignalRock(this,new DiscreteCoordinates(5,8),rock2Signal);
		SignalRock rock3=new SignalRock(this,new DiscreteCoordinates(4,8),rock3Signal);
		
		
		registerActor(key);
		registerActor(groundTorch);
		registerActor(pressurePlate);
		registerActor(pressureSwitch1);
		registerActor(pressureSwitch2);
		registerActor(pressureSwitch3);
		registerActor(pressureSwitch4);
		registerActor(pressureSwitch5);
		registerActor(pressureSwitch6);
		registerActor(pressureSwitch7);
		registerActor(lever1);
		registerActor(lever2);
		registerActor(lever3);
		registerActor(rock1);
		registerActor(rock2);
		registerActor(rock3);
		registerActor(new Door(this, "LevelSelector", new DiscreteCoordinates(3,6),
					  new DiscreteCoordinates(5,9),doorSignal));
		
	
		return registerActor(new Background(this));
    }

	//Return the name of the level
	@Override
	public String getTitle()
	{
		return "Level3";
	}
}
