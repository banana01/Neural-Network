package bananaNetwork.Core.Network;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class NetworkConstructor 
{
	int fromnetworkid = 5;
	File fromnetwork = new File("src\\main\\network"+fromnetworkid);
	File[] layers;
	public NetworkConstructor()
	{
		
	}
	public ArrayList<Integer[]> createClass()
	{
		layers = fromnetwork.listFiles();
		ArrayList<Integer[]> temp = new ArrayList<Integer[]>();
		for (int i = 0; i < layers.length; i++) 
		{
			String[] name = layers[i].getName().split("_");
			temp.add(new Integer[3]);
			temp.get(i)[0] = Integer.parseInt(name[0]);
			temp.get(i)[1] = layers[i].listFiles().length - 1;
		}

		return orderLayers(temp);
	}
	
	public  ArrayList<Integer[]> orderLayers( ArrayList<Integer[]> in)
	{
		ArrayList<Integer[]> temp=in;
		ArrayList<Integer[]> temp2 = new ArrayList<Integer[]>();
		int smallest= Integer.MAX_VALUE;
		while (temp.size() > 0) 
		{
			for(int i=0;i<temp.size();i++)
			{
				if(temp.get(i)[0] < smallest)
				{
					smallest = temp.get(i)[0];
				}
			}
			for (int i = 0; i < temp.size(); i++) 
			{
				if(temp.get(i)[0] == smallest)
				{
					temp2.add(temp.get(i));
					temp.remove(i);
				}
			}
		}
		return temp2;
	
	}
	public Path grabFromNetwork()
	{
		return Paths.get("src\\main\\network"+fromnetworkid);
	}
}
