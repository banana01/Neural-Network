package bananaNetwork.GUI;

import java.util.ArrayList;

import bananaNetwork.Core.Network.Layer;
import bananaNetwork.Core.Network.Network;
import bananaNetwork.Core.Network.Node;

public class DisplayManager 
{
	private Network ntk;
	ArrayList<Layer> layers = new ArrayList<Layer>();
	ArrayList<Node[]> nodes = new ArrayList<Node[]>();
	public DisplayManager(Network ntk)
	{
		setNtk(ntk);
		parseNetworkDesign();
		
	}
	public void parseNetworkDesign()
	{
		for (int i = 0;i < ntk.getLayers().size(); i++) 
		{
			layers.add(ntk.getLayers().get(i));
		}
		for (int i = 0; i < layers.size(); i++) 
		{
			nodes.add(layers.get(i).getNodes().toArray(new Node[layers.get(i).getNodes().size()]));
		}
	}
	public Network getNtk() {
		return ntk;
	}
	public void setNtk(Network ntk) {
		this.ntk = ntk;
	}
	public ArrayList<Layer> getLayers() {
		return layers;
	}
	public void setLayers(ArrayList<Layer> layers) {
		this.layers = layers;
	}
	public ArrayList<Node[]> getNodes() {
		return nodes;
	
	}
	public int getNodesSize() {
		return nodes.size();
	}
	public int getLayersSize() {
		return layers.size();
	}
	public void setNodes(ArrayList<Node[]> nodes) {
		this.nodes = nodes;
	}
	
}
