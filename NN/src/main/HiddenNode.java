package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class HiddenNode extends Node
{	
	int num = 0;
	double value = 0f;
	double baseWeight = 0.5f;
	double baseOffset = 0f;
	int layer = 0;
	Path layerp;
	Path self;
	ArrayList<Double> inputValues = new ArrayList<Double>();
	ArrayList<Double> weights = new ArrayList<Double>();
	HiddenNode(int name, int l, ArrayList<Double> in)
	{
		//var decleration
		num = name;
		layer = l;
		inputValues = in;
		//read weights if exists other wise populate with baseweights
		readWeights(num, weights, layer, getType());
		if(weights.size() == 0)
		{
			for(int i = 0; i < 9; i++)
			{
				weights.add(baseWeight);
			}
		}
		//calculate value of node
		value = output(calculateValue(weights, inputValues, baseOffset));
		//write weights used
		writeWeights(num, weights, layer, getType());
		//printWeights();
		
	}
	HiddenNode(int name, int l, ArrayList<Double> in, Path k)
	{
		//var decleration
		num = name;
		layer = l;
		inputValues = in;
		layerp = k;
		//read weights if exists other wise populate with baseweights
		readWeights(num, weights, layer, getType());
		getWeightsFromFile(layerp, num);
		if(weights.size() == 0)
		{
			for(int i = 0; i < 9; i++)
			{
				weights.add(baseWeight);
			}
		}
		//calculate value of node
		value = output(calculateValue(weights, inputValues, baseOffset));
		//write weights used
		writeWeights(num, weights, layer, getType());
		//printWeights();
		
	}
	@Override
	public void getWeightsFromFile(Path layer, int nid)
	{
		self = Paths.get(layer+"\\"+nid+"weights.txt");
		readWeightsFromFile(weights ,self);
		
	}
	@Override
	public double getValue()
	{
		return value;
	}
	@Override
	public char getType()
	{
		return 'H';
	}
	
}
