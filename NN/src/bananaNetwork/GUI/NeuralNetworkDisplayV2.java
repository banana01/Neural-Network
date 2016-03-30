package bananaNetwork.GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import bananaNetwork.Core.Network.Layer;
import bananaNetwork.Core.Network.Network;
import bananaNetwork.Core.Network.Node;

public class NeuralNetworkDisplayV2 
{
	JSplitPane splitPane;
	JPanel NNMap;
	JPanel layerContainer;
	JPanel[] layerPanels;
	
	ArrayList<Layer> layers = new ArrayList<Layer>();
	ArrayList<Node[]> nodes = new ArrayList<Node[]>();
	
	public NeuralNetworkDisplayV2(Network ntk)
	{
		splitPane = new JSplitPane();
		NNMap = new JPanel();
		layerContainer = new JPanel();
		parseNetworkStruct(ntk);
		drawLayers();
		drawNodes();
		/*
		 * VERY IMPORTANT MILES LOOK AT THIS COMMENT YOU NERD
		 * THE LAYERUI CLASS FROM THE DEMO NEEDS!! TO BE THE CONNECTIONS LAYER AS IT CAN BE TRANSPARENT AND DETECT MOUSE STUFF
		 * SO USE THAT IN THAT AND ALSO THE LAYERUI IS A THING THAT GOES OVERTOP THE NODES AND IS NOT OPAIQUE REMEMBER ALL OF THIS SO YOU DONT HAVE 
		 * TO READ IT LATER YOU ARE DUMB LMAO.
		 */
		
		
	}
	private void drawMap()
	{
		
	}
	private void parseNetworkStruct(Network ntk)
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
	private void drawNodes()
	{
		int nod = 0;
		for (int i = 0; i < getNodes().size(); i++) 
		{
			nod += getNodes().get(i).length;
		}
		for (int i = 0; i < getLayersSize(); i++) 
		{
			
			for (int j = 0; j < getNodes().get(i).length; j++) 
			{
				layerPanels[i].add(MyFactory.createNODEButton(getNodes().get(i)[j]));
			}
			
		}
	}
	private void drawLayers()
	{
		layerPanels = new JPanel[getLayersSize()];
		for (int i = 0; i < layerPanels.length; i++) 
		{
			layerPanels[i] = new JPanel();
			layerPanels[i].setLayout(new FlowLayout());
			layerPanels[i].add(new JLabel("Layer::"+i));
			layerContainer.add(layerPanels[i]);
		}
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
}
