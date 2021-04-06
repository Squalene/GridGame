package ch.epfl.cs107.play.game.enigme.area;



import java.util.Collections;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.GroundTorch;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Teleporter;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Not;
import ch.epfl.cs107.play.window.Window;

public class Enigme2 extends EnigmeArea
{
	
	//Initialize the area 
	@Override
	public boolean begin(Window window, FileSystem fileSystem) 
    {
		super.begin(window,fileSystem);
		
		GroundTorch torch1=new GroundTorch(this,new DiscreteCoordinates(3,8),false);
		GroundTorch torch2=new GroundTorch(this,new DiscreteCoordinates(3,6),false);
		GroundTorch torch3=new GroundTorch(this,new DiscreteCoordinates(3,4),false);
		GroundTorch torch4=new GroundTorch(this,new DiscreteCoordinates(7,4),false);
		GroundTorch torch5=new GroundTorch(this,new DiscreteCoordinates(10,4),false);
		GroundTorch torch6=new GroundTorch(this,new DiscreteCoordinates(10,6),false);
		GroundTorch torch7=new GroundTorch(this,new DiscreteCoordinates(10,8),false);
		
		registerActor(new SignalRock(this,new DiscreteCoordinates(4,10),new And(new Not(torch5),torch4)));
		registerActor(new SignalRock(this,new DiscreteCoordinates(5,10),new And(new Not(torch2),new Not(torch3))));
		registerActor(new SignalRock(this,new DiscreteCoordinates(6,10),new And(torch4,torch6)));
		registerActor(new SignalRock(this,new DiscreteCoordinates(7,10),new And(torch7,torch1)));
		registerActor(new SignalRock(this,new DiscreteCoordinates(8,10),new And(torch6,new Not(torch2))));
		registerActor(new SignalRock(this,new DiscreteCoordinates(9,10),new And(new Not(torch3),torch7)));
		registerActor(new SignalRock(this,new DiscreteCoordinates(10,10),new And(new Not(torch5),torch1)));
		
		registerActor(torch1);
		registerActor(torch2);
		registerActor(torch3);
		registerActor(torch4);
		registerActor(torch5);
		registerActor(torch6);
		registerActor(torch7);
		
		registerActor(new Teleporter(this,"LevelSelector",new DiscreteCoordinates(6,6),
			  	  	  new DiscreteCoordinates(7,11),new MultipleAnd(torch1,new Not(torch2),new Not(torch3),
			  			torch4,new Not(torch5),torch6,torch7)));
		
		return registerActor(new Background(this));
    }

	//Return the name of the level
	@Override
	public String getTitle()
	{
		return "Enigme2";
	}

}
