package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.window.Canvas;

public class AnimatedSprite
{
	private final Sprite sprites[];
	
	private int iterator;
	
	AnimatedSprite(Positionable parent, String ... sprites)
	{

		this.sprites=new Sprite[sprites.length];
		
		iterator=0;
		
		int i=0;
		for(String sprite:sprites)
		{
			this.sprites[i]=new Sprite(sprite, 1, 1.f, parent);
			++i;
		}
	}

	//Iterate at each update on different sprites in order 
	//to give an impression of animation
	public void draw(Canvas canvas)
	{
		iterator%=sprites.length;
		sprites[iterator].draw(canvas);
		iterator++;
	}
	
}
