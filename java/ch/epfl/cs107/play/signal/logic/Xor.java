package ch.epfl.cs107.play.signal.logic;

public class Xor extends LogicSignal
{
	private Logic s1;
	private Logic s2;
	
	
	public Xor(Logic s1,Logic s2)
	{
		this.s1=s1;
		this.s2=s2;
	}

	//is on if both signals are different
	@Override
	public boolean isOn()
	{
		return (s1!=null && s2!=null && (s1.isOn() !=s2.isOn()));
	}
}
