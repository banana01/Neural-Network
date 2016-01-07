package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Layer
{
	private int layerid, amount;
	private char type;
	private Network parent;
	private Layer in, out;
	private List<Node> Nodes = new ArrayList<Node>();
	private Path layerp, from;
	Layer(int id, char t, int a, Network p)
	{
		layerid = id;
		type = t;
		amount = a;
		parent = p;
		
	}
	Layer(int id, char t, int a,  Network p, Path f)
	{
		layerid = id;
		type = t;
		amount = a;
		parent = p;
		from = f;
		
	}
	public void init()
	{
		initFolders();
		getIn();
		getOut();
		
		getLayerPath();
	}
	public void run(double[] orgin)
	{
		createNode(amount);
		if(type != 'I')
		{
			passNodesIn(in.getNodesOut());
		}
		else
		{
			inputNetworkValues(orgin);
		}
		for(Node i : Nodes)
		{
			i.runNode();
		}
		writeValueFile(layerid);
	}
	public void run(Path from,double[] orgin)
	{
		createNode(amount, from);
		if(layerid != 0)
		{
			passNodesIn(in.getNodesOut());
		}
		else
		{
			inputNetworkValues(orgin);
		}
		for(Node i : Nodes)
		{
			i.initNode();
		}
		for(Node i : Nodes)
		{
			i.runNode();
		}
		writeValueFile(layerid);
	}
	public void inputNetworkValues(double[] networkInput)
	{
		if(in == null)
		{
			passNodesIn(networkInput);
		}
	}
	public Network getParent()
	{
		return parent;
	}
	public int getLayerId()
	{
		return layerid;
	}
	public double[] getNodesOut()
	{
		double[] temp = new double[amount];
				
		for(int i = 0; i < Nodes.size(); i++) 
		{
			temp[i] = Nodes.get(i).getValue();
		}
		return temp;
		
	}
	public void passNodesIn(double[] input)
	{
		for (int j = 0; j < Nodes.size(); j++) 
		{
			Nodes.get(j).setInputValues(input);
		}
	}
	public Layer getInLayer()
	{
		return in;
	}
	public Layer getoutLayer()
	{
		return out;
	}
	public int getAmount()
	{
		return amount;
	}
	public char getType()
	{
		return type;
	}
	public void getIn()
	{
		if(parent.getLayerP(layerid) != null)
		{
			in=parent.getLayerP(layerid);
		}
		
	}
	public void getOut()
	{
		if(parent.getLayerN(layerid) != null)
		{
		out=parent.getLayerN(layerid);
		}
	}
	
	public void initFolders()
	{
		
		if(!Paths.get("src\\main\\network"+parent.getNetworkid()+"\\"+ layerid +"_layer_"+type).toFile().exists())
		{
			Paths.get("src\\main\\network"+parent.getNetworkid()+"\\"+ layerid +"_layer_"+type).toFile().mkdir();
		}
	}
 	
	
	public void constructNodes()
	{
		
	}
	
	
	public void getLayerPath()
	{
		layerp = Paths.get(parent.getPath()+"\\"+layerid+"_layer_"+type);
	}
	public void createNode(int a)
	{
		//created node based on args
		for(int i = 0; i < a; i++)
		{
			Nodes.add(new Node(i, this));
		}
	}
	public void createNode(int a, Path from)
	{
		//created node based on args
		for(int i = 0; i < a; i++)
		{
			Nodes.add(new Node(i, this, from));
		}
	}
	public void writeValueFile(int layer)
	{
		try
		{
			//writes the output of the layer to the outvalue file
			File file = new File("src\\main\\network"+parent.getNetworkid()+"\\"+ layerid +"_layer_"+type+"\\outvalue.txt");
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter out = new BufferedWriter(fw);
			out.write(getNodesOut().toString());
			out.newLine();
			out.flush();
			fw.flush();
			out.close();
			fw.close();
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
