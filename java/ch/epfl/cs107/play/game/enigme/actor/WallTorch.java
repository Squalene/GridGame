package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class WallTorch extends Torch
{	
	public WallTorch(Area area, DiscreteCoordinates position, Boolean isOn)
	{
		super(area, position, isOn, "torch.wall.on.1", "torch.wall.on.2", "torch.wall.off");
	}
}
