package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

//General definition of a Collectable
public abstract class Collectable extends AreaEntity
{
	
	private Boolean collected;
	private Sprite graphic;
	
	public Collectable(Area area, Orientation orientation, DiscreteCoordinates position,String graphic)
	{
		super(area, orientation, position);
		collected=false;
		this.graphic=new Sprite(graphic, 1, 1.f, this);
	}
	
	public Collectable(Area area,DiscreteCoordinates position,String graphic)
	{
		this(area,Orientation.DOWN,position,graphic);
	}

	//Collectables only take one cell, otherwise they are too big to be collectables
	@Override
	public List<DiscreteCoordinates> getCurrentCells()
	{	
		return Collections.singletonList(getCurrentMainCellCoordinates());	
	}
	
	
	//Every collectable accepts interactions
	@Override
	public void acceptInteraction(AreaInteractionVisitor v)
	{
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
	
	//Draw the collectable
	@Override
	public void draw(Canvas canvas)
	{
		graphic.draw(canvas);		
	}
	
	//If it is collected, this actor is unregistered from the area
	public void collect()
	{
		collected=true;
		getOwnerArea().unregisterActor(this);
	}
	
	
	//Returns true if the collectable has been collected 
	public Boolean isCollected()
	{
		return collected;
	}

	//A collectable is non traversable
	@Override
	public boolean takeCellSpace()
	{
		return true;
	}
	
	//A collectable isalwats viewInteractable
	@Override
	public boolean isViewInteractable()
	{
		return true;
	}

	//A collectable is never cell interactable
	@Override
	public boolean isCellInteractable()
	{
		return false;
	}
}
