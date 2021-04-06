package ch.epfl.cs107.play.signal.logic;

import java.util.LinkedList;
import java.util.List;

public class LogicNumber extends LogicSignal
{
	private float nb;
	
	private List<Logic> signals;
	
	
	public LogicNumber (List<Logic> signals)
	{
		this.signals=new LinkedList<>();
		
		for(Logic signal: signals)
		{
			this.signals.add(signal);
		}
	
	}
	
	//Return true if they are no more than 12 signals and the sum of the signals 
	//has value nb
	@Override
	public boolean isOn()
	{
		float sum=0;
		int i=0;
		
		for(Logic signal: signals)
		{
			if(signal.isOn())
			sum+=Math.pow(2,i);
			++i;
		}
		//We don't take into into account if the number is negative 
		//or superior to 2^e.length because the condition will return false
		return (i<=12 && sum==nb); 
	
	}

}
