package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class GroundTorch extends Torch
{
	public GroundTorch(Area area, DiscreteCoordinates position, Boolean isOn)
	{
		super(area, position, isOn, "torch.ground.on.1", "torch.ground.on.2", "torch.ground.off");
	}

}
