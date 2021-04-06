package ch.epfl.cs107.play.game.enigme.area;


import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class Level2 extends EnigmeArea
{
	
	//Initialize the area
	@Override
	public boolean begin(Window window, FileSystem fileSystem) 
    {
		super.begin(window,fileSystem);
		
		
		registerActor(new Door(this,"LevelSelector",new DiscreteCoordinates(2,6),
				  	  new DiscreteCoordinates(5,0),Logic.TRUE));
		
		registerActor(new Apple(this,new DiscreteCoordinates(5,6)));
		
		
		
		return registerActor(new Background(this));
    }

	//The title of the level
	@Override
	public String getTitle()
	{
		return "Level2";
	}

}
