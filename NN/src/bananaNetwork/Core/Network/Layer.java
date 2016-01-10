package bananaNetwork.Core.Network;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Layer
{
	private int ID, amount;
	private Network parent;
	private List<Node> Nodes = new ArrayList<Node>();
	private Path path, clone;
	Layer(int id, int a, Network p)
	{
		setID(id);
		setAmount(a);
		setParent(p);
		setPath();
		initLayer();
	}
	Layer(int id, int a,  Network p, Path f)
	{
		setID(id);
		setAmount(a);
		setParent(p);
		setPath();
		setClone(f);
		initLayer(clone);
		
		
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Network getParent() {
		return parent;
	}
	public void setParent(Network parent) {
		this.parent = parent;
	}
	public List<Node> getNodes() {
		return Nodes;
	}
	public void setNodes(List<Node> nodes) {
		Nodes = nodes;
	}
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public Path getClone() {
		return clone;
	}
	public void setClone(Path clone) {
		this.clone = clone;
	}
	public void setPath()
	{
		this.path = Paths.get(parent.getPath()+"\\"+ID+"_layer");
	}
	
	/*---------------------------------------------------------*/
	public void initLayer()
	{
		createNode();
	}
	public void initLayer(Path f)
	{
		createNode(clone);
	}
	
	
	
	public void createNode()
	{
		//created node based on args
		for(int i = 0; i < amount; i++)
		{
			Nodes.add(new Node(i, this));
		}
	}
	public void createNode(Path from)
	{
		//created node based on args
		for(int i = 0; i < amount; i++)
		{
			Nodes.add(new Node(i, this, from));
		}
	}
}
