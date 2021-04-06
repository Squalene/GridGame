package ch.epfl.cs107.play.game.enigme.area;

import java.util.Collections;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Teleporter;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class LevelSelector extends EnigmeArea
{
	//Initialize the area with doors
	@Override
	public boolean begin(Window window, FileSystem fileSystem) 
    {
		super.begin(window,fileSystem);
		
		registerActor(new Door(this,"Level1",new DiscreteCoordinates(5,1),
					  new DiscreteCoordinates(1,7),Logic.TRUE));
		
		registerActor(new Door(this,"Level2",new DiscreteCoordinates(5,1),
				  	  new DiscreteCoordinates(2,7),Logic.TRUE));
		
		registerActor(new Door(this,"Level3",new DiscreteCoordinates(5,1),
					  new DiscreteCoordinates(3,7),Logic.TRUE));
		
		registerActor(new Door(this,"Enigme0",new DiscreteCoordinates(1,11),
					  new DiscreteCoordinates(4,7),Logic.TRUE));
		
		registerActor(new Door(this,"Enigme1",new DiscreteCoordinates(16,0),
			  	   	  new DiscreteCoordinates(5,7),Logic.FALSE));
		
		registerActor(new Door(this,"Enigme2",new DiscreteCoordinates(7,1),
					  new DiscreteCoordinates(6,7),Logic.FALSE));
		
		registerActor(new Door(this,"",new DiscreteCoordinates(5,5),
				  	  new DiscreteCoordinates(7,7),Logic.FALSE));
		
		registerActor(new Door(this,"",new DiscreteCoordinates(5,5),
					  new DiscreteCoordinates(8,7),Logic.FALSE));
		
		
		return registerActor(new Background(this));
    }

	//Returns the name of the level
	@Override
	public String getTitle()
	{
		return "LevelSelector";
	}
}
