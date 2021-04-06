package ch.epfl.cs107.play.game.enigme.area;


import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.GrassTeleporter;
import ch.epfl.cs107.play.game.enigme.actor.PushableRock;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.StaticRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.window.Window;

public class Enigme0 extends EnigmeArea
{

	//Initialize the area it by adding the actors in place
	@Override
	public boolean begin(Window window, FileSystem fileSystem)
	{
		super.begin(window, fileSystem);

		Apple apple_5_11 = new Apple(this, new DiscreteCoordinates(5, 11));

		Apple apple_14_11 = new Apple(this, new DiscreteCoordinates(14, 11));

		Apple apple_12_21 = new Apple(this, new DiscreteCoordinates(12, 21));

		Apple apple_6_22 = new Apple(this, new DiscreteCoordinates(6, 22));

		Logic rockSignal = new MultipleAnd(apple_5_11, apple_14_11, apple_12_21, apple_6_22);

		registerActor(new GrassTeleporter(this, "Enigme1", new DiscreteCoordinates(16,0),
						new DiscreteCoordinates(10, 29)));
		
		registerActor(new StaticRock(this,new DiscreteCoordinates(9,28)));
		registerActor(new SignalRock(this,new DiscreteCoordinates(10,28),rockSignal));
		registerActor(new StaticRock(this,new DiscreteCoordinates(11,28)));
		
		
		registerActor(apple_5_11);
		registerActor(apple_14_11);
		registerActor(apple_12_21);
		registerActor(apple_6_22);
		registerActor(new PushableRock(this, new DiscreteCoordinates(5, 22)));
		registerActor(new PushableRock(this, new DiscreteCoordinates(7, 22)));
		registerActor(new PushableRock(this, new DiscreteCoordinates(6, 21)));
		registerActor(new PushableRock(this, new DiscreteCoordinates(6, 23)));

		return registerActor(new Background(this));
	}

	//Returns the name of the level
	@Override
	public String getTitle()
	{
		return "Enigme0";
	}

}
