package bananaNetwork.Core.Network;

import bananaNetwork.Core.TransportFunctions.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Node 
{
	private Layer parent;
	private int ID = 0;
	private double bias = 0f, value,raw;
	private Path path;
	private ArrayList<Connection> inputConnections = new ArrayList<Connection>();
	private ArrayList<Connection> outputConnections = new ArrayList<Connection>();
	//private ArrayList<Double> weights = new ArrayList<Double>();
	//blank constructor
	Node()
	{
		
	}
	// constructs from layer
	Node(int name, Layer lay)
	{
		//var decleration
		setID(name);
		setParent(lay);
		setPath();
	}
	
	
	public double getRaw() {
		return raw;
	}
	public void setRaw(double raw) {
		this.raw = raw;
	}
	public Layer getParent() {
		return parent;
	}
	public void setParent(Layer parent) {
		this.parent = parent;
	}
	public int getID() {
		return ID;
	}
	public void setID(int num) {
		this.ID = num;
	}
	public double getBias() {
		return bias;
	}
	public void setBias(double baseOffset) {
		this.bias = baseOffset;
	}
	public Path getPath() {
		return path;
	}
	public void setPath()
	{
		setPath(Paths.get(parent.getPath()+"\\"+ID+"_weights.txt"));
	}
	public void setPath(Path self) {
		this.path = self;
	}
	public void setValue(double v)
	{
		this.value = v;
	}
	public double getValue()
	{
		return this.value;
	}
	public ArrayList<Connection> getInputConnections() {
		return inputConnections;
	}
	public void setInputConnections(ArrayList<Connection> inputConnections) {
		this.inputConnections = inputConnections;
	}
	public ArrayList<Connection> getOutputConnections() {
		return outputConnections;
	}
	public void setOutputConnections(ArrayList<Connection> outputConnections) {
		this.outputConnections = outputConnections;
	}
	
	/*----------------------------------------------------*/

	public void freeConnctions()
	{
		while(inputConnections.size()>0)
		{
			inputConnections.get(0).free();
		}
		while(outputConnections.size()>0)
		{
			outputConnections.get(0).free();
		}
	}
	public boolean isEmpty()
	{
		if(inputConnections==null && outputConnections==null)
		{
			return true;
		}else return false;
	}
	public void free()
	{
		while(!this.isEmpty())
		{
			this.freeConnctions();
			System.out.println("Im dying"+this.getID());
		}
		parent.removeNode(this);
		parent.getParent().removeNodeIn(this);
		parent.getParent().removeNodeOut(this);
		this.setParent(null);
	}
	public void addInputConnection(Node inNode)
	{
		Connection temp = new Connection(inNode, this);
		inputConnections.add(temp);
		inNode.hasOutputConnection(temp);
		
		
	}
	public void hasOutputConnection(Connection con)
	{
		outputConnections.add(con);
	}
	public void hasInputConnection(Connection con)
	{
		inputConnections.add(con);
	}
	public void addOutputConnection(Node outNode)
	{
		Connection temp = new Connection(this, outNode);
		outputConnections.add(temp);
		outNode.hasInputConnection(temp);
		
	}
	public void removeOut(int i)
	{
		this.outputConnections.remove(i);
	}
	public void removeOut(Connection c)
	{
		this.outputConnections.remove(c);
	}
	public void removeIn(int i) 
	{
		this.inputConnections.remove(i);
	}
	public void removeIn(Connection c) 
	{
		this.inputConnections.remove(c);
	}

	//++++++Does not work as intented must return to
	public Connection getInputConnectionToNode(int n, int l)
	{
		for (int i = 0; i < inputConnections.size(); i++) 
		{
			if(inputConnections.get(i).getIn().getID() == n && inputConnections.get(i).getIn().parent.getID() == l)
			{
				return inputConnections.get(i);
			}
		}
		return null;
	}
	public void runNode(TransportFunction function)
	{
		if(!inputConnections.isEmpty())
		{
			calculateRaw();
			setValue(function.getOutput(raw));
		}
		
	}
	


	public void calculateRaw()
	{
		double temp2 = bias;
		for(int i = 0; i < inputConnections.size(); i++)
		{
			temp2 = (inputConnections.get(i).getIn().getValue() * inputConnections.get(i).getWeight()) + temp2;
		}
		raw=temp2;
	}
	
	
}

