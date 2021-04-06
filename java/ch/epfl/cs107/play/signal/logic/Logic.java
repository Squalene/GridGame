package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public interface Logic extends Signal
{	
	
	Logic TRUE = new Logic() 
	{ 
		@Override
		public boolean isOn() 
		{ 
			return true;
		} 
	};
	
	Logic FALSE = new Logic() 
	{ 
		@Override
		public boolean isOn() 
		{ 
			return false;
		} 
	};
	
	
	@Override
	default float getIntensity(float t) 
	{
		return getIntensity(); 
	}
	
	//Returns 1.0f, or 0.0f
	default float getIntensity() 
	{
		if(isOn())
		{
			return 1.0f;
		}
		
		else return 0.0f;
	}
	//Must be redefined
	public boolean isOn();
}
