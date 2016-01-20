package bananaNetwork.Core.Network;

import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import bananaNetwork.Core.TransportFunctions.Sigmoid;;


public abstract class Network
{

	private int ID;
	private Path path;
	private ArrayList<Layer> layers = new ArrayList<Layer>();
	private ArrayList<Node> inputNodes = new ArrayList<Node>();
	private ArrayList<Node> outputNodes = new ArrayList<Node>();
	Network(int netid)
	{
		setID(netid);
		setPath();
		
	}
	Network(ArrayList<Integer[]> con, int netid)
	{
		setID(netid);
		setPath();
		initNetwork(con);
	}
	Network(int netid, String setting)
	{
		setID(netid);
		setPath();
		initNetwork(setting);
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public ArrayList<Layer> getLayers() {
		return layers;
	}
	public void setLayers(ArrayList<Layer> layers) {
		this.layers = layers;
	}
	public void setPath()
	{
		this.path = Paths.get("src\\main\\network"+ID);
	}

	//=====================================================//
	public void inputUpdate(double[] maininput)
	{
		sendNetworkInput(maininput);
		runNetwork();
	}
	public void createConnection(Node in, Node out)
	{
		in.addOutputConnection(out);
	}
	public void runNetwork()
	{
		for (int i = 0; i < layers.size(); i++) 
		{
			for (int j = 0; j < layers.get(i).getNodes().size(); j++) 
			{
				layers.get(i).getNodes().get(j).runNode(new Sigmoid(1d));
			}
		}
	}
	public void findInputNodes()
	{
		for (int i = 0; i < layers.size(); i++) 
		{
			for (int j = 0; j < layers.get(i).getNodes().size(); j++) 
			{
				if(layers.get(i).getNodes().get(j).getInputConnections().isEmpty())
				{
					inputNodes.add(layers.get(i).getNodes().get(j));
				}
			}
		}
	}
	public void findOutputNodes()
	{
		for (int i = 0; i < layers.size(); i++) 
		{
			for (int j = 0; j < layers.get(i).getNodes().size(); j++) 
			{
				if(layers.get(i).getNodes().get(j).getOutputConnections().isEmpty())
				{
					outputNodes.add(layers.get(i).getNodes().get(j));
				}
			}
		}
	}
	public void sendNetworkInput(double[] maininput)
	{
		for (int i = 0; i < inputNodes.size(); i++) 
		{
			inputNodes.get(i).setValue(maininput[i]);
		}
	}
	public double[] networkOutput()
	{
		
		double[] temp = new double[this.outputNodes.size()];
		for (int i = 0; i < this.outputNodes.size(); i++) 
		{
			temp[i] = this.outputNodes.get(i).getValue();
		}
		return temp;
	}

	public void createLayer(int layerid, int nl)
	{
		this.layers.add(new Layer(layerid, nl, this));
	}
	public void createLayer(int layerid)
	{
		this.layers.add(new Layer(layerid, this));
	}
	public void removeLayer(int i)
	{
		layers.remove(i);
	}
	public void removeLayer(Layer l)
	{
		layers.remove(l);
	}
	public void addlayer(Layer l)
	{
		l.getParent().removeLayer(l);
		l.setParent(this);
		layers.add(l);
	}
	public void freeConnctions()
	{
		while(layers.size()>0)
		{
			layers.get(0).free();
		}
		
	}
	public void removeNodeIn(int i)
	{
		inputNodes.remove(i);
	}
	public void removeNodeIn(Node n)
	{
		inputNodes.remove(n);
	}
	public void removeNodeOut(int i)
	{
		outputNodes.remove(i);
	}
	public void removeNodeOut(Node n)
	{
		outputNodes.remove(n);
	}
	public void connectAllLayer(Layer in, Layer out)
	{
		for (int i = 0; i < in.getNodes().size(); i++) 
		{
			for (int j = 0; j < out.getNodes().size(); j++) 
			{
				createConnection(in.getNodes().get(i),out.getNodes().get(j));
			}
		}
	}

	public void initNetwork(ArrayList<Integer[]> con)
	{
		for (int i = 0; i < con.size(); i++) 
		{
			createLayer(con.get(i)[0], con.get(i)[1]);
		}
		
	}	
	public void initNetwork(String s)
	{
		String[] layersST = s.split("_");
		for (int i = 0; i < layersST.length; i++) 
		{
			createLayer(i, Integer.parseInt(layersST[i]));
		}
		
	}

}
