package bananaNetwork.Core.Functions;

public abstract class Function 
{
	protected double output = 1d;
	public abstract double getOutput(double net);
	public double getDV(double net)
	{
		return 1;
	}
}
