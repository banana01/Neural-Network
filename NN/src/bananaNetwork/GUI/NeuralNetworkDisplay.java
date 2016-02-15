package bananaNetwork.GUI;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import bananaNetwork.Core.Network.Layer;
import bananaNetwork.Core.Network.Network;
import bananaNetwork.Core.Network.Node;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NeuralNetworkDisplay extends JPanel implements ActionListener
{
	private Network ntk;
	JPanel[] layerPanels;
	JButton[] nodeButtons;
	ArrayList<Layer> layers = new ArrayList<Layer>();
	ArrayList<Node[]> nodes = new ArrayList<Node[]>();
	public NeuralNetworkDisplay(Network ntk)
	{
		setNtk(ntk);
		parseNetworkDesign();
		setLayout(new GridLayout(layers.size(),1,0,0));
	}
	public void init()
	{
		drawLayers();
		drawNodes();
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
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}
	
	
	
	
	
	
	public void drawLayers()
	{
		layerPanels = new JPanel[getLayersSize()];
		for (int i = 0; i < layerPanels.length; i++) 
		{
			layerPanels[i] = new JPanel();
			layerPanels[i].setLayout(new GridLayout(3,5,0,0));
			add(layerPanels[i]);
		}
	}
	public void drawNodes()
	{
		int nod = 0;
		for (int i = 0; i < getNodes().size(); i++) 
		{
			nod += getNodes().get(i).length;
		}
		nodeButtons = new JButton[nod];
		for (int i = 0; i < getLayersSize(); i++) 
		{
			
			for (int j = 0; j < getNodes().get(i).length; j++) 
			{
				//nodeButtons[j]
				layerPanels[i].add(MyFactory.createNODEButton(getNodes().get(i)[j]));
			}
			
		}
		
	}
	
}
