package ch.epfl.cs107.play.signal.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MultipleOr extends LogicSignal
{
	private List<Logic> signals;
	
	public MultipleOr(Logic ... signals)
	{
		this.signals=new LinkedList<>();
		
		for(Logic signal: signals)
		{
			this.signals.add(signal);
		}
	
	}
	
	public MultipleOr(Map<? ,? extends Logic>signals)
	{
		this.signals=new LinkedList<>();
		
		for(Logic signal: signals.values())
		{
			this.signals.add(signal);
		}
	}
	
	//Is on one of the signals is on
	public boolean isOn()
	{
		for(Logic signal: signals)
		{
			if (signal!=null && signal.isOn())
			{
				return true;
			}
		}
		return false;
	}
}
