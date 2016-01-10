package bananaNetwork.Core.Network;

public class WeightBalancer 
{
	public double findError(int[] target, double[] out)
	{
		double error = 0;
		for (int i = 0; i < out.length; i++) 
		{
			error += (Math.pow(out[i] - target[i], 2))/2.0;
		}
		return error;
	}
	public void weightCorrector()
	{
		
	}
}
