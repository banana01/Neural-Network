package bananaNetwork.Core.Network;

import java.util.Random;

public class Connection 
{
	private Node in, out;
	private double weight;
	Connection(Node i, Node o)
	{
		setIn(i);
		setOut(o);
		randomWeight();
	}
	Connection(Node i, Node o, double w)
	{
		setIn(i);
		setOut(o);
		setWeight(w);
	}
	public Node getIn() {
		return in;
	}
	public void setIn(Node in) {
		this.in = in;
	}
	public Node getOut() {
		return out;
	}
	public void setOut(Node out) {
		this.out = out;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void shiftWeight(double offset)
	{
		this.weight += offset;
	}
	
	public void randomWeight()
	{
		Random rnd = new Random();
		setWeight(rnd.nextDouble()-0.5);
	}
}
