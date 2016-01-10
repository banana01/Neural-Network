package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Node 
{
	private File file;
	private Layer parent;
	private int num = 0;
	private double baseWeight = 0.5f, baseOffset = 0f, value;
	private Path fromLoc,self;
	private ArrayList<Double> inputValues = new ArrayList<Double>();
	private ArrayList<Double> weights = new ArrayList<Double>();
	//blank constructor
	Node()
	{
		
	}
	// constructs from layer
	Node(int name, Layer lay)
	{
		//var decleration
		setID(name);
		setParentLayer(lay);
		
		
		
		
		
		
		setWeights();

	}
	// clones node from file
	Node(int name, Layer lay, Path from)
	{
		setID(name);
		setParentLayer(lay);
		
		
		
		
		
		setWeightsFromFile(from);
		writeWeights();
	}
	
	public void initNode()
	{
		initFile();
	}
	public void runNode()
	{
		calculateValue();
		writeWeights();
	}
	public void initFile()
	{
		baseWeights();
		file = new File("src\\main\\network"+parent.getParent().getNetworkid()+"\\"+ parent.getLayerId() +"_layer_"+parent.getType()+"\\"+num+"_weights.txt");
		try {
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter out = new BufferedWriter(fw);
			for(int i = 0; i < weights.size(); i++)
			{
				out.write(weights.get(i).toString());
				out.newLine();
				
			}
			out.flush();
			fw.flush();
			out.close();
			fw.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	
	public void setParentLayer(Layer par)
	{
		this.parent=par;
	}
	public void setID(int n)
	{
		this.num=n;
	}
	public void setInputValues(double[] input)
	{
		inputValues.clear();
		for (int i = 0; i < input.length; i++) 
		{
			inputValues.add(input[i]);
		}
	}
	public void setValue(double v)
	{
		this.value = v;
	}
	
	
	
	
	
	public double getValue()
	{
		return this.value;
	}

	//gives raw sum of inputs before normalized to 0-1
	
	public void baseWeights()
	{
		weights.clear();
		if(parent.getType()!='I')
		{
			for (int i = 0; i < parent.getInLayer().getAmount(); i++) 
			{
				
				weights.add(baseWeight);
			}	
		}
		else
		{
			for (int i = 0; i < parent.getAmount(); i++) 
			{
				weights.add(baseWeight);
			}	
		}
		
	}

	//stops a bug we had
	
	
	public void setWeightsFromFile(Path from)
	{
		fromLoc = Paths.get(from.toString()+parent.getLayerId()+"\\"+num+"_weights.txt");
		readWeightsFromFile();
		
	}

	
	//writes weight values to files for editing and persistance
	public void writeWeights()
	{
		try
		{
			file = new File("src\\main\\network"+parent.getParent().getNetworkid()+"\\"+parent.getLayerId() +"_layer_"+parent.getType()+"\\"+num+"_weights.txt");
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter out = new BufferedWriter(fw);
			for(int i = 0; i < weights.size(); i++)
			{
				out.write(weights.get(i).toString());
				out.newLine();
				
			}
			out.flush();
			fw.flush();
			out.close();
			fw.close();
			
		}
		catch(FileNotFoundException e)
		{
			//e.printStackTrace();
		} catch (IOException e) 
		{
			//e.printStackTrace();
		}
	}
	
	//reads weight values for usage from files
	public void setWeights()
	{
		weights.clear();
			Scanner readweights;
			try {
				readweights = new Scanner(new File("src\\main\\network"+parent.getParent().getNetworkid()+"\\"+ parent.getLayerId() +"_layer_"+parent.getType()+"\\"+num+"_weights.txt"));
				if(readweights.hasNextLine())
				{
					while(readweights.hasNextLine())
					{
							
						weights.add(Double.parseDouble(readweights.nextLine()));
					
					}
					readweights.close();
				}
				else
				{
					initFile();
					writeWeights();
				}
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
			}

	}
	public void readWeightsFromFile()
	{
			weights.clear();
			Scanner readweights;
			try {
				readweights = new Scanner(fromLoc.toFile());
				if(readweights.hasNextLine())
				{
					while(readweights.hasNextLine())
					{
							
							weights.add(Double.parseDouble(readweights.nextLine()));
					
					}
					readweights.close();
				}
				else
				{
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto_generated catch block
				//e.printStackTrace();
			}
			
				
				
			
			
		
		
	}
	//debug for weight data
	public void printWeights()
	{
		for(int i = 0; i < weights.size(); i++)
		{
			System.out.print(weights.get(i)+" ");
		}
		System.out.println("\n");
	}
	public void calculateValue()
	{
		double temp2 = baseOffset;
		for(int i = 0; i < weights.size(); i++)
		{
			temp2 = (inputValues.get(i) * weights.get(i)) + temp2;
		}
		value = (1/(1 + Math.exp(-temp2)));
	}
	
	
	
}

