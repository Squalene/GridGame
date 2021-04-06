package ch.epfl.cs107.play.signal.logic;

public class Not extends LogicSignal
{

	private Logic s;
	
	public Not(Logic s)
	{
		this.s=s;
	}
	
	//Is on if the signal is off
	@Override
	public boolean isOn()
	{
		return (s!=null && !s.isOn());
	}

}
