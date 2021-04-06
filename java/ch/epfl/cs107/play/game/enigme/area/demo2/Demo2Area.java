package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public abstract class Demo2Area extends Area
{

	//Initialize the area
	public boolean begin(Window window, FileSystem fileSystem) 
    {
		super.begin(window,fileSystem);
		
		//Associate with it an area behavior initialised by the image of 
		setBehavior(new Demo2Behavior(window, getTitle()));
		
		return true;
    }

	@Override
	public abstract String getTitle();

	//Returns the cameraScalefactor
	@Override
	public float getCameraScaleFactor()
	{
		return 22.0f;
	}
}
