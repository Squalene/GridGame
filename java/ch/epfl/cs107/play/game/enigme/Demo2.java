package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.demo2.Demo2Area;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame
{

	private final static float CAMERASCALEFACTOR = 22.0f;

	// This game is defined by its mainCharacter and its levels
	Demo2Player mainCharacter;
	Demo2Area levelSelector;
	Demo2Area level1;

	// Initializes the game
	@Override
	public boolean begin(Window window, FileSystem fileSystem)
	{
		super.begin(window, fileSystem);

		levelSelector = new Room0();
		level1 = new Room1();

		// Add areas to AreaGame
		addArea(levelSelector);
		addArea(level1);

		// Set levelSelector as the currentArea, we use the force begin because we don't
		// have a resume option ready yet
		setCurrentArea(levelSelector.getTitle(), true);

		// Creates and add the character to the area
		mainCharacter = new Demo2Player(getCurrentArea(), new DiscreteCoordinates(5, 5));
		mainCharacter.enterArea(getCurrentArea(), new DiscreteCoordinates(5, 5));

		// Sets the camera of the currentArea to follow the player
		getCurrentArea().setViewCandidate(mainCharacter);

		return true;
	}

	// Updates the area by calling the super.update
	// and swapping areas if the character is on a door
	@Override
	public void update(float deltaTime)
	{

		// Swap areas if the character is on a door
		if (mainCharacter.isOnDoor())
		{
			if (getCurrentArea() instanceof Room0)
			{
				mainCharacter.leaveArea(levelSelector);
				setCurrentArea(level1.getTitle(), false);
				mainCharacter.enterArea(level1, new DiscreteCoordinates(5, 2));
			}

			else if (getCurrentArea() instanceof Room1)
			{
				mainCharacter.leaveArea(level1);
				setCurrentArea(levelSelector.getTitle(), false);
				mainCharacter.enterArea(levelSelector, new DiscreteCoordinates(5, 5));
			}
			
			getCurrentArea().setViewCandidate(mainCharacter);
		}
		super.update(deltaTime);
	}

	/***************************** Getters **********************************/

	// Returns the frameRate
	@Override
	public int getFrameRate()
	{
		return 24;
	}

	// Returns the title of the game
	@Override
	public String getTitle()
	{
		return "Demo2";
	}

}
