package bananaNetwork.Core.TransportFunctions;

public class Sigmoid extends TransportFunction 
{
	private double slope = 1d; 
	public Sigmoid(double slop)
	{
		setSlope(slop);
	}
	@Override
	public double getOutput(double net) 
	{
		if(net > 100)
		{
			return 1;
		}
		else if(net < -100)
		{
			return 0;
		}
		double temp = 1d + Math.exp(-this.slope * net);
		this.output = (1d / temp);
		return this.output;
		
	}
	@Override
	public double getDV(double net) 
	{
		double derivative = this.slope * this.output * (1d - this.output) + 0.1;
		return derivative;
	}
	public double getSlope() {
		return slope;
	}
	public void setSlope(double slope) {
		this.slope = slope;
	}
}
