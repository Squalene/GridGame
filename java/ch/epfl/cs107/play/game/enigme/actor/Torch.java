package ch.epfl.cs107.play.game.enigme.actor;


import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;


public abstract class Torch extends ViewSwitchable
{
	
	private Sprite graphicNoFlame;
	private Sprite graphicLeftFlame;
	private Sprite graphicRightFlame;
	
	private Boolean leftFlame;

	public Torch(Area area, DiscreteCoordinates position, Boolean isOn,String graphicOn1,String graphicOn2,String graphicOff)
	{
		super(area, position, isOn, "torch.ground.on.1","torch.ground.off");
		
		graphicLeftFlame=new Sprite(graphicOn1,1, 1.f, this);
		graphicRightFlame=new Sprite(graphicOn2,1, 1.f, this);
		graphicNoFlame=new Sprite(graphicOff,1, 1.f, this);
		
		leftFlame=true;

	}
	
	//Draw the flame by alternating the flame image in order to make it move 
	@Override
	public void draw(Canvas canvas)
	{
		if (isOn())
		{
			if(leftFlame)
			{
				graphicLeftFlame.draw(canvas);
			}
			
			else graphicRightFlame.draw(canvas);
				
			leftFlame=!leftFlame;
		}
		
		else graphicNoFlame.draw(canvas);
	}
	
}
