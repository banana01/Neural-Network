package bananaNetwork.Core.Network;

import bananaNetwork.Core.Functions.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Node 
{
	private Layer parent;
	private int ID = 0;
	private double baseOffset = 0f, value,raw;
	private Path path,Clone;
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
	// clones node from file
	Node(int name, Layer lay, Path from)
	{
		this(name,lay);
		setClone(from);
	}
	
	public double getRaw() {
		return raw;
	}
	public void setRaw(double raw) {
		this.raw = raw;
	}
	public Path getClone() {
		return Clone;
	}
	public void setClone(Path clone) {
		Clone = clone;
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
	public double getBaseOffset() {
		return baseOffset;
	}
	public void setBaseOffset(double baseOffset) {
		this.baseOffset = baseOffset;
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
	public void runNode(Function function)
	{
		if(!inputConnections.isEmpty())
		{
			calculateRaw();
			setValue(function.getOutput(raw));
		}
		
	}
	


	public void calculateRaw()
	{
		double temp2 = baseOffset;
		for(int i = 0; i < inputConnections.size(); i++)
		{
			temp2 = (inputConnections.get(i).getIn().getValue() * inputConnections.get(i).getWeight()) + temp2;
		}
		raw=temp2;
	}

}

