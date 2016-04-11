package bananaNetwork.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.plaf.LayerUI;

import bananaNetwork.Core.Network.Layer;
import bananaNetwork.Core.Network.Network;
import bananaNetwork.Core.Network.Node;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;

public class NeuralNetworkDisplayV2 extends JPanel
{
	JSplitPane splitPane;
	JPanel NNMap;
	JPanel layerContainer, toplevelPanel;
	JPanel[] layerPanels;
	JPanel[] layerSubPanels;
	ToolBar tb;
	ConnectionsLayer cl;
	
	ArrayList<Layer> layers = new ArrayList<Layer>();
	ArrayList<Node[]> nodes = new ArrayList<Node[]>();
	
	public NeuralNetworkDisplayV2(Network ntk)
	{
		//Set layout of NND to store the splitPane
		setLayout(new BorderLayout(0, 0));
		
		//init stuff to stop null pointers and serve as debug in case of failure
		splitPane = new JSplitPane();
		NNMap = new JPanel();
		layerContainer = new JPanel();
		tb = new ToolBar();
		cl = new ConnectionsLayer();
		toplevelPanel = new JPanel();
		
		
		//add split pane
		add(splitPane);
		
		//parse the input network and prepare JPanels
		parseNetworkStruct(ntk);
		drawLayers();
		drawNodes();
		
		//setup lower level jpanels layouts
		NNMap.setLayout(new BorderLayout(0, 0));
		layerContainer.setLayout(new GridLayout(3,5,5,5));
		toplevelPanel.setLayout(new BorderLayout(0,0));
		
		
		//add the comonents to the window
		NNMap.add(layerContainer);
		toplevelPanel.add(createLayer(NNMap));
		splitPane.setRightComponent(toplevelPanel);
		splitPane.setLeftComponent(tb);
		/*
		 * VERY IMPORTANT MILES LOOK AT THIS COMMENT YOU NERD
		 * THE LAYERUI CLASS FROM THE DEMO NEEDS!! TO BE THE CONNECTIONS LAYER AS IT CAN BE TRANSPARENT AND DETECT MOUSE STUFF
		 * SO USE THAT IN THAT AND ALSO THE LAYERUI IS A THING THAT GOES OVERTOP THE NODES AND IS NOT OPAIQUE REMEMBER ALL OF THIS SO YOU DONT HAVE 
		 * TO READ IT LATER YOU ARE DUMB LMAO.
		 */
		
		
	}
	//dont remember what this does might be unessasarry will test soon might be used to clean up code
	public void paintComponent(Graphics w)
	{
		NNMap.setSize(toplevelPanel.getSize());
		lineDraw();
	}
	public void lineDraw()
	{
	}
	// parse the network into its layers and nodes
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
	//put nodes in sublayers
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
				layerSubPanels[i].add(MyFactory.createNODEButton(getNodes().get(i)[j]));
			}
			
		}
	}
	//create layers that hold labels and sublayers and fill them
	private void drawLayers()
	{
		layerPanels = new JPanel[getLayersSize()];
		layerSubPanels = new JPanel[getLayersSize()];
		for (int i = 0; i < layerPanels.length; i++) 
		{
			layerPanels[i] = new JPanel();
			layerSubPanels[i] = new JPanel();
			layerPanels[i].setLayout(new FlowLayout());
			layerSubPanels[i].setLayout(new GridLayout(3,5,5,5));
			layerPanels[i].add(new JLabel("Layer::"+i));
			layerPanels[i].add(layerSubPanels[i]);
			layerContainer.add(layerPanels[i]);
		}
	}
	public JLayer<JComponent> createLayer(JPanel map)
	{
		LayerUI<JComponent> layerUI = new ConnectionsLayer();
		return new JLayer<JComponent>(map, layerUI);
	}
	
	
	
	
	
	
	
	//=========================================================GETTERS/SETTERS
	
	
	
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
