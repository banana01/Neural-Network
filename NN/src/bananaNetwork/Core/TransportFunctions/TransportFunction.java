package bananaNetwork.Core.TransportFunctions;

public abstract class TransportFunction 
{
	protected double output = 1d;
	public abstract double getOutput(double net);
	public double getDV(double net)
	{
		return 1;
	}
}
