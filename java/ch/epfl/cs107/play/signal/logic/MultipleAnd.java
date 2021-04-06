package ch.epfl.cs107.play.signal.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MultipleAnd extends LogicSignal
{
	private List<Logic> signals;
	
	public MultipleAnd(Logic ... signals)
	{
		this.signals=new LinkedList<>();
	
		for(Logic signal: signals)
		{
			this.signals.add(signal);
		}
	
	}
	
	public MultipleAnd(Map<? ,? extends Logic>signals)
	{
		this.signals=new LinkedList<>();
		
		for(Logic signal: signals.values())
		{
			this.signals.add(signal);
		}
	}
	
	//Is on if all the signals are on
	public boolean isOn()
	{
		for(Logic signal: signals)
		{
			if (signal==null || !signal.isOn())
			{
				return false;
			}
		}
		return true;
	}
}
