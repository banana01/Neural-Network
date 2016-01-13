package bananaNetwork.Core.Network;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Layer
{
	private int ID, amount;
	private Network parent;
	private List<Node> nodes = new ArrayList<Node>();
	private Path path;
	Layer(int id, int a, Network p)
	{
		setID(id);
		setAmount(a);
		setParent(p);
		setPath();
		initLayer();
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
		return nodes;
	}
	public void setNodes(List<Node> nodess) {
		nodes = nodess;
	}
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	
	// Default path
	public void setPath()
	{
		this.path = Paths.get(parent.getPath()+"\\"+ID+"_layer");
	}
	
	/*---------------------------------------------------------*/
	public void initLayer()
	{
		createNodes();
	}
	public void createNodes()
	{
		//created node based on args
		
		for(int i = 0; i < amount; i++)
		{
			nodes.add(new Node(i, this));
		}
	}
	public void removeNode(int i)
	{
		nodes.remove(i);
		amount--;
	}
	public void removeNode(Node n)
	{
		nodes.remove(n);
		amount--;
	}
	public void addNode(Node n)
	{
		if(n.getParent()!=null);
		{
			n.getParent().removeNode(n);
		}
		n.setParent(this);
		nodes.add(n);
		amount++;
	}
	public void freeNodes()
	{
		while(nodes.size()>0)
		{
			nodes.get(0).free();
		}
		
	}
	public void free()
	{
		while(!this.isEmpty())
		{
			this.freeNodes();
			System.out.println("Im dieing(node)");
		}
		parent.removeLayer(this);
		this.setParent(null);
	}
	private boolean isEmpty()
	{
		if(this.nodes.size()>0)
		{
			return true;
		}
		return false;
	}
}
