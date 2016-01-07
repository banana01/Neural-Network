package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Path;

public class Network
{

	int networkid;
	File network;
	ArrayList<Layer> layers = new ArrayList<Layer>();
	Network(int netid, double[] maininput)
	{
		networkid = netid;
		network = new File("src\\main\\network"+networkid);
		if(!network.exists())
		{
			network.mkdir();
		}
		createLayer(0, 'I', 9);
		createLayer(1, 'H', 27);
		createLayer(2, 'O', 9);
		for(Layer i : layers)
		{
			
			i.init();
		}
		for(Layer i : layers)
		{
			i.run(maininput);
		}
		for (int i = 0; i < layers.get(layers.size()-1).getNodesOut().length; i++) 
		{
			Main.finalout.add(layers.get(layers.size()-1).getNodesOut()[i]);
		}
		
	}
	Network(ArrayList<Integer[]> con, Path from, int netid, double[] maininput)
	{
		
		networkid = netid;
		network = new File("src\\main\\network"+networkid);
		if(!network.exists())
		{
			network.mkdir();
		}
		char temp = 'E';
		for (int i = 0; i < con.size(); i++) 
		{
			if(con.get(i)[2] == 1)
			{
				temp = 'I';
			}
			else if(con.get(i)[2] == 2)
			{
				temp = 'H';
			}
			else if(con.get(i)[2] == 3)
			{
				temp = 'O';
			}
			
			createLayer((int)con.get(i)[0], temp, (int)con.get(i)[1], from);
		}
		
		for(Layer i : layers)
		{
			i.init();
		}
		
		for(Layer i : layers)
		{
			i.run(from, maininput);
		}
		for (int i = 0; i < layers.get(layers.size()-1).getNodesOut().length; i++) 
		{
			Main.finalout.add(layers.get(layers.size()-1).getNodesOut()[i]);
		}
	}
	public int getNetworkid()
	{
		return networkid;
	}
	public Path getPath()
	{
		return network.toPath();
	}

 	public Layer getLayerP(int layerid)
 	{
 		if(layerid != 0)
 		{
 			return layers.get(layerid-1);
 		}
 		else 
 		{
 			return null;
 		}
 		
 	}
 	public Layer getLayerN(int layerid)
 	{
 		if(layerid+1 < layers.size())
 		{
 			return layers.get(layerid+1);
 		}
 		else
 		{
 			return null;
 		}
 	}
	public void createLayer(int layerid, char type, int nl)
	{
		layers.add(new Layer(layerid, type, nl, this));
		File layerdir = new File("src\\main\\network"+networkid+"\\"+layerid+"_layer_"+type);
		if(!layerdir.exists())
		{
			try
			{
				layerdir.mkdir();
			}
			catch(SecurityException se)
			{
				
			}
		}
	}
	public void createLayer(int layerid, char type, int nl, Path from)
	{
		layers.add(new Layer(layerid, type, nl, this, from));
		File layerdir = new File("src\\main\\network"+networkid+"\\"+layerid+"_layer_"+type);
		if(!layerdir.exists())
		{
			try
			{
				layerdir.mkdir();
			}
			catch(SecurityException se)
			{
				
			}
		}
	}
	

}
