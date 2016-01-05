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
	int networkid = 3;
	int layerid;
	char type;
	int amount;
	ArrayList<Double> inl = new ArrayList<Double>();
	ArrayList<Double> outl = new ArrayList<Double>();
	List<Node> Nodes = new ArrayList<Node>();
	Path nk, layerp;
	Layer(int id, char t, int a)
	{
		layerid = id;
		type = t;
		amount = a;
		proccessIN();
		createNode(type, amount);
		getOutValues();
		appendIO();
		writeValueFile(layerid);
	}
	Layer(int id, char t, int a, Path n)
	{
		layerid = id;
		type = t;
		amount = a;
		nk = n;
		
		proccessIN();
		getLayerPath(nk);
		createNode(type, amount, layerp);
		getOutValues();
		appendIO();
		writeValueFile(layerid);
	}
	public void constructNodes()
	{
		
	}
	public void proccessIN()
	{
		//gets input values from master list of inputs from previous layer
		if(layerid != 0)
		{
			if(NodeController.out.get(layerid-1).size() == 0 )
			{
				
			}
			else
			{
				inl = NodeController.out.get(layerid-1);
			}
		}
		
		
	}
	public void appendIO()
	{
		//adds out and in to master list
		NodeController.in.add(layerid, inl);
		NodeController.out.add(layerid, outl);
	}
	public void getLayerPath(Path ntwrk)
	{
		layerp = Paths.get(nk+"\\"+layerid+"_layer_"+type);
	}
	public void createNode(char t, int a)
	{
		//created node based on args
		switch(t)
		{
		case 'H':
			createHidden(a, inl);
			break;
		case 'O':
			createOutput(a, inl);
			break;
		case 'I':
			createInput(a, inl);
			break;
		}
	}
	public void createNode(char t, int a, Path k)
	{
		//created node based on args
		switch(t)
		{
		case 'H':
			createHidden(a, inl, k);
			break;
		case 'O':
			createOutput(a, inl, k);
			break;
		case 'I':
			createInput(a, inl, k);
			break;
		}
	}
	public void getOutValues()
	{
		//retreives proccesed out data from nodes
		outl.clear();
		for(int i = 0; i < Nodes.size(); i++)
		{
			outl.add(Nodes.get(i).getValue());
			//System.out.println(Nodes.get(i).getValue());
		}
		if(type == 'O')
		{
			//NodeController.terminateNN(outl);
		}

	}
	//
	public void writeValueFile(int layer)
	{
		try
		{
			//writes the output of the layer to the outvalue file
			File file = new File("src\\main\\network"+networkid+"\\"+ layerid +"_layer_"+type+"\\outvalue.txt");
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter out = new BufferedWriter(fw);
			for(int i = 0; i < outl.size(); i++)
			{
				out.write(outl.get(i).toString());
				out.newLine();
				
			}
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
	public void createHidden(int a, ArrayList<Double> in)
	{
		for(int i = 0; i < a; i++)
		{
			Nodes.add(new HiddenNode(i,layerid, in));
		}
	}
	public void createOutput(int a, ArrayList<Double> in)
	{
		for(int i = 0; i < a; i++)
		{
		Nodes.add(new OutputNode(i,layerid, in));
		}
	}
	public void createInput(int a, ArrayList<Double> in)
	{
		for(int i = 0; i < a; i++)
		{
			
			Nodes.add(new InputNode(i,layerid, in));
		}
	}
	public void createHidden(int a, ArrayList<Double> in, Path k)
	{
		for(int i = 0; i < a; i++)
		{
			Nodes.add(new HiddenNode(i,layerid, in, k));
		}
	}
	public void createOutput(int a, ArrayList<Double> in, Path k)
	{
		for(int i = 0; i < a; i++)
		{
		Nodes.add(new OutputNode(i,layerid, in, k));
		}
	}
	public void createInput(int a, ArrayList<Double> in, Path k)
	{
		for(int i = 0; i < a; i++)
		{
			Nodes.add(new InputNode(i,layerid, in, k));
		}
	}
	/*public void appendValues(double f, int ind)
	{
		HNValues[ind] = f;
		
	}
	public double[] HNV()
	{
		return HNValues;
	}*/
}
