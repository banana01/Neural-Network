package bananaNetwork.Core.ErrorFunctions;


public interface ErrorFunction 
{
	 	public double getTotalError();

	
	    public void reset();


	    public double[]  calculatePatternError(double[] predictedOutput, double[] targetOutput);

}
