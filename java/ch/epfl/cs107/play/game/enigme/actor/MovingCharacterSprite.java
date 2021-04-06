package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class MovingCharacterSprite
{
	
	private final Sprite spritesDOWN[];
	private final Sprite spritesLEFT[];
	private final Sprite spritesUP[];
	private final Sprite spritesRIGHT[];

	private int spriteToUse;
	
	MovableAreaEntity character;
	
	public MovingCharacterSprite(String image,MovableAreaEntity character)
	{
		this.spritesDOWN = new Sprite[4];
		this.spritesLEFT = new Sprite[4];
		this.spritesUP = new Sprite[4];
		this.spritesRIGHT = new Sprite[4];
		
		spriteToUse = 0;
		
		this.character=character;
		
		Vector anchor = new Vector(0.25f, 0.32f);

		// Initialize the arrays of Sprite to draw the animated character
		for (int i = 0; i < 4; ++i)
		{
			spritesDOWN[i] = new Sprite(image, 0.5f, 0.65625f, character,
					new RegionOfInterest(0, i * 21, 16, 21), anchor);
			spritesLEFT[i] = new Sprite(image, 0.5f, 0.65625f, character,
					new RegionOfInterest(17, i * 21, 16, 21), anchor);
			spritesUP[i] = new Sprite(image, 0.5f, 0.65625f, character,
					new RegionOfInterest(33, i * 21, 16, 21), anchor);
			spritesRIGHT[i] = new Sprite(image, 0.5f, 0.65625f, character,
					new RegionOfInterest(48, i * 21, 16, 21), anchor);
		}
	}
	
	
	public void draw(Canvas canvas,Orientation orientation)
	{

		if (orientation.equals(Orientation.DOWN))
		{
			animatedDraw(spritesDOWN, canvas);
		}

		if (orientation.equals(Orientation.LEFT))
		{
			animatedDraw(spritesLEFT, canvas);
		}

		if (orientation.equals(Orientation.UP))
		{
			animatedDraw(spritesUP, canvas);
		}

		if (orientation.equals(Orientation.RIGHT))
		{
			animatedDraw(spritesRIGHT, canvas);
		}
	}
	
	// Animate the drawing of the player by alternating sprites at each draw
		private void animatedDraw(Sprite tab[], Canvas canvas)
		{
			if (character.isMoving())
			{
				tab[spriteToUse].draw(canvas);
				spriteToUse = (spriteToUse + 1) % 4;
			}

			else
			{
				tab[0].draw(canvas);
				spriteToUse = 1;
			}
		}

}
