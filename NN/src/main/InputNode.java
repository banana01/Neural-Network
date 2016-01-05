package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthSplitPaneUI;


public class InputNode extends Node
{
	double value = 0f;
	double baseWeight = 0.5f;
	int layer;
	int num;
	Path layerp;
	Path self;
	ArrayList<Double> weights = new ArrayList<Double>();
	ArrayList<Double> inputValues = new ArrayList<Double>();
	InputNode(int n, int l, ArrayList<Double> in)
	{
		num = n;
		layer = l;
		inputValues = in;
		
		readWeights(num, weights, layer,getType());
		if(weights.size() == 0)
		{
			for(int i = 0; i < 9; i++)
			{
				weights.add(baseWeight);
			}
		}
		writeWeights(num, weights, layer,getType());
		parseBoard();
	}
	InputNode(int n, int l, ArrayList<Double> in, Path k)
	{
		num = n;
		layer = l;
		inputValues = in;
		layerp = k;
		readWeights(num, weights, layer,getType());
		getWeightsFromFile(layerp, num);
		if(weights.size() == 0)
		{
			for(int i = 0; i < 9; i++)
			{
				weights.add(baseWeight);
			}
		}
		writeWeights(num, weights, layer,getType());
		parseBoard();
	}
	public void setValue(double v)
	{
		value = v;
		//System.out.println(value);
	}
	@Override
	public void getWeightsFromFile(Path layer, int nid)
	{
		self = Paths.get(layer+"\\"+nid+"_weights.txt");
		readWeightsFromFile(weights ,self);
		
	}
	@Override
	public double getValue()
	{
		return value;
	}
	public void parseBoard()
	{
		
		setValue(NodeController.board1[num]);
	}
	@Override
	public char getType()
	{
		return 'I';
	}
}
