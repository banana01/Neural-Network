package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Node 
{
	File file;
	double value;
	int networkid = 3;
	
	//sigmoid function
	public double output(double v)
	{
		return (1/(1 + Math.exp(-v)));
		
	}
	//gives raw sum of inputs before normalized to 0_1
	public double calculateValue( ArrayList<Double> w, ArrayList<Double> iv, double bo)
	{
		double temp2 = bo;
		for(int i = 0; i < w.size(); i++)
		{
			temp2 = (iv.get(i) * w.get(i)) + temp2;
		}
		return temp2;
	}
	//stops a bug we had
	public void initFile(int n, int layerid,char type)
	{
		file = new File("src\\main\\network"+networkid+"\\"+ layerid +"_layer_"+type+"\\"+n+"_weights.txt");
		try {
			PrintWriter writer = new PrintWriter("src\\main\\network"+networkid+"\\"+ layerid +"_layer_"+type+"\\"+n+"_weights.txt", "ASCII");
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto_generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto_generated catch block
			e.printStackTrace();
		}
		
	}
	
	//writes weight values to files for editing and persistance
	public void writeWeights(int n, ArrayList<Double> w, int layerid, char type)
	{
		try
		{
			file = new File("src\\main\\network"+networkid+"\\"+ layerid +"_layer_"+type+"\\"+n+"_weights.txt");
			FileWriter fw = new FileWriter(file, false);
			BufferedWriter out = new BufferedWriter(fw);
			for(int i = 0; i < w.size(); i++)
			{
				out.write(w.get(i).toString());
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
	public void getWeightsFromFile(Path layer, int nid)
	{
				
	}
	public char getType()
	{
		return 'E';
	}
	//overrided by subnodes sets computed value
	public void setValue(double v)
	{
		value = v;
	}
	//overrided by subnodes returns computed value
	public double getValue()
	{
		return value;
	}
	//reads weight values for usage from files
	public void readWeights(int n, ArrayList<Double> w, int layerid, char type)
	{
		w.clear();
			Scanner readweights;
			try {
				readweights = new Scanner(new File("src\\main\\network"+networkid+"\\"+ layerid +"_layer_"+type+"\\"+n+"_weights.txt"));
				if(readweights.hasNextLine())
				{
					while(readweights.hasNextLine())
					{
							
							w.add(Double.parseDouble(readweights.nextLine()));
					
					}
					readweights.close();
				}
				else
				{
					initFile(n, layerid, type);
					writeWeights(n, w, layerid, type);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto_generated catch block
				//e.printStackTrace();
			}
			
				
				
			
			
		
		
	}
	public void readWeightsFromFile(ArrayList<Double> w, Path weg)
	{
			w.clear();
			Scanner readweights;
			try {
				readweights = new Scanner(weg.toFile());
				if(readweights.hasNextLine())
				{
					while(readweights.hasNextLine())
					{
							
							w.add(Double.parseDouble(readweights.nextLine()));
					
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
	public void printWeights(ArrayList<Double> w)
	{
		for(int i = 0; i < w.size(); i++)
		{
			System.out.print(w.get(i)+" ");
		}
		System.out.println("\n");
	}
}
