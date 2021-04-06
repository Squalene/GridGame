package ch.epfl.cs107.play.game.enigme.area;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.GroundTorch;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.PushableRock;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.StaticRock;
import ch.epfl.cs107.play.game.enigme.actor.Teleporter;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.window.Window;

public class Enigme1 extends EnigmeArea
{
	
	//Initiliaze the area 
	@Override
	public boolean begin(Window window, FileSystem fileSystem) 
    {
		super.begin(window,fileSystem);
		
		//First part
		
		Map<DiscreteCoordinates,PressureSwitch> pressureSwitches=new HashMap<>();
		
		for (int x=14;x<24;++x)
		{
			for(int y=7;y<13;++y)
			{
				pressureSwitches.put(new DiscreteCoordinates(x,y),new PressureSwitch(this,new DiscreteCoordinates(x,y)));
			}
		}
		
		//Remove the pressure switches that are at the place of a wall
		pressureSwitches.remove(new DiscreteCoordinates(20,9));
		pressureSwitches.remove(new DiscreteCoordinates(19,9));
		pressureSwitches.remove(new DiscreteCoordinates(17,8));
		pressureSwitches.remove(new DiscreteCoordinates(17,9));
		pressureSwitches.remove(new DiscreteCoordinates(16,9));
		pressureSwitches.remove(new DiscreteCoordinates(15,9));
		pressureSwitches.remove(new DiscreteCoordinates(15,10));
		pressureSwitches.remove(new DiscreteCoordinates(15,11));
	
		Logic rock_13_5Signal=new MultipleAnd(pressureSwitches);
		
		GroundTorch torch22_2=new GroundTorch(this,new DiscreteCoordinates(22,2),false);
		GroundTorch torch24_4=new GroundTorch(this,new DiscreteCoordinates(23,4),false);
		GroundTorch torch24_5=new GroundTorch(this,new DiscreteCoordinates(23,5),false);
		
		Logic rock_14_5Signal=new MultipleAnd(torch22_2,torch24_4,torch24_5);

		for (PressureSwitch pSwitch : pressureSwitches.values()) 
		{ 
			registerActor(pSwitch);
		}
		
		registerActor(torch22_2);
		registerActor(torch24_4);
		registerActor(torch24_5);

		
		registerActor(new SignalRock(this,new DiscreteCoordinates(23,13),Logic.FALSE));
		registerActor(new SignalRock(this,new DiscreteCoordinates(13,5),rock_13_5Signal));
		registerActor(new SignalRock(this,new DiscreteCoordinates(14,5),rock_14_5Signal));
		
		//Second part
		
		PressurePlate plate_1_8=new PressurePlate(this,new DiscreteCoordinates(1,8));
		
		Logic rock_8_5Signal=plate_1_8;
		
		registerActor(plate_1_8);
		
		registerActor(new SignalRock(this,new DiscreteCoordinates(8,5),rock_8_5Signal));
		
		registerActor(new PushableRock(this,new DiscreteCoordinates(2,1)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(6,1)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(8,1)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(9,1)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(3,2)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(4,2)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(5,2)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(8,2)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(11,2)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(3,3)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(5,3)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(6,3)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(8,3)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(9,3)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(10,3)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(12,3)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(1,4)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(2,4)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(8,4)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(9,4)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(11,4)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(12,4)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(1,5)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(3,5)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(4,5)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(5,5)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(6,5)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(9,5)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(10,5)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(3,6)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(4,6)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(5,6)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(6,6)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(7,6)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(10,6)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(11,6)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(12,6)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(1,7)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(3,7)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(5,7)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(7,7)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(8,7)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(11,7)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(12,7)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(2,8)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(6,8)));
		registerActor(new PushableRock(this,new DiscreteCoordinates(7,8)));

		//Third part
		
		PressurePlate pressurePlate_1_16=new PressurePlate(this,new DiscreteCoordinates(12,16));
		
		Logic teleporterSignal=pressurePlate_1_16;
		
		registerActor(new StaticRock(this,new DiscreteCoordinates(5,11)));
		registerActor(new StaticRock(this,new DiscreteCoordinates(6,12)));
		registerActor(new StaticRock(this,new DiscreteCoordinates(7,12)));
		registerActor(new StaticRock(this,new DiscreteCoordinates(8,11)));
		registerActor(new StaticRock(this,new DiscreteCoordinates(6,16)));
		registerActor(new StaticRock(this,new DiscreteCoordinates(7,16)));
		
		registerActor(pressurePlate_1_16);
		
		registerActor(new Teleporter(this, getTitle(), new DiscreteCoordinates(1,14), 
						new DiscreteCoordinates(6,11), Logic.TRUE));
		
		registerActor(new Teleporter(this, getTitle(), new DiscreteCoordinates(6,19), 
				new DiscreteCoordinates(1,13), teleporterSignal));
		
		registerActor(new PushableRock(this,new DiscreteCoordinates(2,13)));
		
		Logic doorSignal=Logic.TRUE;
		
		registerActor(new Door(this, "Enigme2", new DiscreteCoordinates(7,1),
				new DiscreteCoordinates(6,32),doorSignal));
		

		return registerActor(new Background(this));
    }

	//Return the name of the level
	@Override
	public String getTitle()
	{	
		return "Enigme1";
	}

}
