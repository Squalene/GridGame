package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Room0 extends Demo2Area
{
	
	//Initialize the area
	@Override
	public boolean begin(Window window, FileSystem fileSystem) 
    {
		super.begin(window,fileSystem);
		
		return registerActor(new Background(this));
    }

	//Returns the title of the level
	@Override
	public String getTitle()
	{
		return "LevelSelector";
	}
}
