package main;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class NetworkConstructor 
{
	int fromnetworkid = 5;
	File fromnetwork = new File("src\\main\\network"+fromnetworkid);
	ArrayList<Integer[]> values = new ArrayList<Integer[]>();
	File[] layers;
	NetworkConstructor()
	{
		
	}
	public ArrayList<Integer[]> createClass()
	{
		layers = fromnetwork.listFiles();
		for (int i = 0; i < layers.length; i++) 
		{
			String[] name = layers[i].getName().split("_");
			values.add(new Integer[3]);
			values.get(i)[0] = Integer.parseInt(name[0]);
			values.get(i)[1] = layers[i].listFiles().length - 1;
			if(name[2].contains("I"))
			{
				values.get(i)[2] = 1;
			}
			else if(name[2].contains("H"))
			{
				values.get(i)[2] = 2;
			}
			else if(name[2] .contains("O"))
			{
				values.get(i)[2] = 3;
			}
			
		}
		return values;
		
	}
	public Path grabFromNetwork()
	{
		return Paths.get("src\\main\\network"+fromnetworkid);
	}
}
