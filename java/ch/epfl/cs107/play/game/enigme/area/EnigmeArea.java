package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.enigme.Enigme;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public abstract class EnigmeArea extends Area
{
	public boolean begin(Window window, FileSystem fileSystem) 
    {
		super.begin(window,fileSystem);
		
		setBehavior(new EnigmeBehavior(window, getTitle()));
		
		return true;
    }

	@Override
	public abstract String getTitle();

	//return the cameraScaleFactor
	@Override
	public float getCameraScaleFactor()
	{
		return Enigme.CAMERA_SCALE_FACTOR;
	}
}
