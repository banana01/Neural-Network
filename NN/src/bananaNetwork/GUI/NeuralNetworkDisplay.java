package bananaNetwork.GUI;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bananaNetwork.Core.Network.Layer;
import bananaNetwork.Core.Network.Network;
import bananaNetwork.Core.Network.Node;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NeuralNetworkDisplay extends JPanel  implements MouseListener
{
	private Network ntk;
	JPanel[] layerPanels;
	JPanel[] layerSubPanels;
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
	public void mouseClicked(MouseEvent e) {
		
		
	}
	
	
	
	public void drawLayers()
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
				layerSubPanels[i].add(MyFactory.createNODEButton(getNodes().get(i)[j]));
			}
			
		}
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
