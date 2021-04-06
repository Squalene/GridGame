package ch.epfl.cs107.play.game.areagame;

import java.util.HashMap;
import java.util.Map;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaGame is a type of Game displayed in a (MxN) Grid called Area An AreaGame
 * has multiple Areas
 */
abstract public class AreaGame implements Game
{

	/// Context objects
	private Window window;
	private FileSystem fileSystem;

	/// A map containing all the Area of the Game
	private Map<String, Area> areas;

	/// A map indicating if an area ahs been used
	private Map<String, Boolean> usedAreas;

	/// The current area the game is played in
	private Area currentArea;

	/**
	 * Add an Area to the AreaGame list
	 * 
	 * @param a
	 *            (Area): The area to add, not null
	 */
	protected final void addArea(Area a)
	{
		areas.put(a.getTitle(), a);
		usedAreas.put(a.getTitle(), false);
	}

	/**
	 * Setter for the current area: Select an Area in the list from its key - the
	 * area is then begin or resume depending if the area is already started or not
	 * and if it is forced
	 * 
	 * @param key
	 *            (String): Key of the Area to select, not null
	 * @param forceBegin
	 *            (boolean): force the key area to call begin even if it was already
	 *            started
	 * @return (Area): after setting it, return the new current area
	 */
	protected final Area setCurrentArea(String key, boolean forceBegin)
	{
		if (currentArea != null)
		{
			currentArea.suspend();
			currentArea.end();
		}

		Area prochaine = areas.get(key);

		if (prochaine != null)
		{
			// If we have to restart the level or it is the
			// first time we start it
			if (forceBegin == true || !usedAreas.get(key))
			{
				prochaine.begin(window, fileSystem);
				usedAreas.replace(key, true);

			}

			// We resume the level
			else
			{
				prochaine.resume(window, fileSystem);
			}
			currentArea = prochaine;
		}

		else
		{
			throw new IllegalArgumentException("Next area is null");
		}

		return null;
	}

	// Initialise the AreaGame
	@Override
	public boolean begin(Window window, FileSystem fileSystem)
	{
		this.fileSystem = fileSystem;
		this.window = window;
		areas = new HashMap<>();
		usedAreas = new HashMap<>();
		return true;
	}

	// updating the AreaGame corresponds to updating the current area
	@Override
	public void update(float deltaTime)
	{
		currentArea.update(getFrameRate());
	}

	@Override
	public void end()
	{
		// do nothing
	}

	/***************************** Getters **********************************/

	// returns the currentArea
	protected Area getCurrentArea()
	{
		return currentArea;
	}

	// returns the area corresponding to the key given in argument
	protected Area getArea(String key)
	{
		return areas.get(key);
	}

	/** @return (Window) : the Graphic and Audio context */
	protected final Window getWindow()
	{
		return window;
	}

	/** @return (FIleSystem): the linked file system */
	protected final FileSystem getFileSystem()
	{
		return fileSystem;
	}

}
