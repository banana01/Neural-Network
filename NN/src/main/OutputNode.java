package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class OutputNode extends Node
{
	int num;
	double value = 0;
	double output = 0f;
	double baseWeight = 0.5f;
	double temp;
	Path layerp;
	Path self;
	double baseOffset = 0f;
	int layer = 0;
	ArrayList<Double> weights = new ArrayList<Double>();
	ArrayList<Double> inputValues = new ArrayList<Double>();
	OutputNode(int name, int l, ArrayList<Double> in)
	{
		//var decleration
		num = name;
		layer = l;
		inputValues = in;
		//read weights if exists other wise populate with baseweights
		readWeights(num, weights, layer,getType());
		if(weights.size() == 0)
		{
			for(int i = 0; i < 9; i++)
			{
				weights.add(baseWeight);
			}
		}
		//calculate value of node
		temp = calculateValue(weights, inputValues, baseOffset);
		value = output(temp);
		//write weights used
		writeWeights(num, weights, layer,getType());
		//printWeights();
	}
	OutputNode(int name, int l, ArrayList<Double> in, Path k)
	{
		//var decleration
		num = name;
		layer = l;
		inputValues = in;
		layerp = k;
		//read weights if exists other wise populate with baseweights
		readWeights(num, weights, layer,getType());
		getWeightsFromFile(layerp, num);
		if(weights.size() == 0)
		{
			for(int i = 0; i < 9; i++)
			{
				weights.add(baseWeight);
			}
		}
		//calculate value of node
		temp = calculateValue(weights, inputValues, baseOffset);
		value = output(temp);
		//write weights used
		writeWeights(num, weights, layer,getType());
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
		return 'O';
	}
	
}
