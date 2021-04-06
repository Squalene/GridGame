package ch.epfl.cs107.play.game.enigme;

import java.util.Collections;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.BoyPlayer;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.OldManPlayer;
import ch.epfl.cs107.play.game.enigme.area.Enigme0;
import ch.epfl.cs107.play.game.enigme.area.Enigme1;
import ch.epfl.cs107.play.game.enigme.area.Enigme2;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.game.enigme.area.Level1;
import ch.epfl.cs107.play.game.enigme.area.Level2;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the
 * notion of Player When initializing the player is added to the current area
 */
public class Enigme extends AreaGame
{

	public final static float CAMERA_SCALE_FACTOR = 22.0f;

	// An enigme is defined by levels and a mainCharacter
	EnigmePlayer mainCharacter;
	EnigmeArea levelSelector;
	EnigmeArea level1;
	EnigmeArea level2;
	EnigmeArea level3;
	EnigmeArea enigme0;
	EnigmeArea enigme1;
	EnigmeArea enigme2;

	// Initialize the new game with the mainCharacter and all the area we will use
	@Override
	public boolean begin(Window window, FileSystem fileSystem)
	{
		super.begin(window, fileSystem);

		levelSelector = new LevelSelector();
		level1 = new Level1();
		level2 = new Level2();
		level3 = new Level3();
		enigme0 = new Enigme0();
		enigme1 = new Enigme1();
		enigme2 = new Enigme2();

		addArea(levelSelector);
		addArea(level1);
		addArea(level2);
		addArea(level3);
		addArea(enigme0);
		addArea(enigme1);
		addArea(enigme2);

		setCurrentArea(levelSelector.getTitle(), false);

		mainCharacter = new OldManPlayer(getCurrentArea(), new DiscreteCoordinates(5, 5));

		getCurrentArea().registerActor(mainCharacter);

		getCurrentArea().setViewCandidate(mainCharacter);

		return true;
	}

	// If the character is passing a door, he goes to the next area
	// change its area and update the concerned areas
	@Override
	public void update(float deltaTime)
	{
		super.update(deltaTime);
		
		if (mainCharacter.isPassingTranslocator())
		{
			String nextArea = mainCharacter.passedTranslocator().getNextArea();
			DiscreteCoordinates arrivalPosition = mainCharacter.passedTranslocator().getArrivalPosition();

			mainCharacter.leaveArea(getCurrentArea());
			setCurrentArea(nextArea, false);	
			mainCharacter.enterArea(getCurrentArea(), arrivalPosition);
			getCurrentArea().setViewCandidate(mainCharacter);
		}
	}

	/***************************** Getters **********************************/

	// Returns the title of the game
	@Override
	public String getTitle()
	{
		return "Enigme";
	}

	// Returns the framerate of the game
	@Override
	public int getFrameRate()
	{
		return 24;
	}
}
