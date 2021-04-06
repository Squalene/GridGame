package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehavior extends AreaBehavior
{
	
	public EnigmeBehavior(Window window, String fileName)
	{
		super(window, fileName);
		
		for (int x=0;x<getWidth();++x)
		{
			for (int y=0;y<getHeight();++y)
			{
				EnigmeCellType cellType = EnigmeCellType.
						toType(getBehaviorMap().getRGB(getHeight()-1-y, x));
				
				setCell(x, y, new EnigmeCell(x, y, cellType));
			}
		}
	}
	
	public class EnigmeCell extends Cell
	{
		EnigmeCellType type;
		
		private EnigmeCell(int x, int y, EnigmeCellType type)
		{
			super(x, y);
			this.type=type;
		}

		@Override
		//In this demo, Wall and Water areas are not traversable 
		
		public boolean takeCellSpace()
		{
			return (type==EnigmeCellType.WALL || type==EnigmeCellType.WATER);
		}

		@Override
		//In this demo, cell do not accept interaction at a distance
		public boolean isViewInteractable()
		{
			return false;
		}

		@Override
		//In this demo, cell accept interaction on contact
		public boolean isCellInteractable()
		{
			return true;
		}


		@Override
		//For now, an interactable can enter as long as the cell is traversable (is not 
		//taken by a non-traversable Interactable)
		protected boolean canEnter(Interactable entity)
		{	
			return (super.canEnter(entity)&&!this.takeCellSpace()&&!super.takeCellSpace());
			
		}

		@Override
		//Any cell of this demo can be left
		protected boolean canLeave(Interactable entity)
		{
			return true;
		}

		//Cell permits the interaction by enabling the interactor to interact with it
		@Override
		public void acceptInteraction(AreaInteractionVisitor v)
		{
			((EnigmeInteractionVisitor)v).interactWith(this);
		}
		
	}

	//Enumeration of of the types a cell can have
	public enum EnigmeCellType 
	{ 
		NULL(0),
		WALL(-16777216),	//RGB code of black
		DOOR(-65536),	//RGB code of red
		WATER(-16776961),	//RGB code of blue
		INDOOR_WALKABLE(-1),
		OUTDOOR_WALKABLE(-14112955);
	
		final int type;
		
		EnigmeCellType(int type)
		{ 
			this.type = type;
		}
		
		static EnigmeCellType toType(int type) 
		{
			for(EnigmeCellType elem : EnigmeCellType.values())
			{
			      if(elem.type==type)
			      {
			    	  return elem;
			      }  	  
			}
			
			return NULL;
		}
		
	}

}
