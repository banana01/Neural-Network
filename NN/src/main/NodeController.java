package main;

import java.util.ArrayList;
import java.io.File;
import java.nio.file.Path;

public class NodeController 
{

	int networkid = 3;
	ArrayList<Integer> layerids = new ArrayList<Integer>();
	public static ArrayList<ArrayList<Double>> in = new ArrayList<ArrayList<Double>>();
	public static ArrayList<ArrayList<Double>> out = new ArrayList<ArrayList<Double>>();
	public static int[] board1 = {0,0,0,0,0,0,0,0,0};
	File network = new File("src\\main\\network"+networkid);
	File networkState = new File("src\\main\\network"+networkid+"networkState.txt");
	ArrayList<Integer[]> construct = new ArrayList<Integer[]>();
	ArrayList<Layer> layers = new ArrayList<Layer>();
	NodeController()
	{
		if(!network.exists())
		{
			network.mkdir();
		}
		populateBoard();
		
	}
	NodeController(ArrayList<Integer[]> con, Path nk)
	{
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
			createLayer((int)con.get(i)[0], temp, (int)con.get(i)[1], nk);
		}
		populateBoard();
	}
	public void populateBoard()
	{	
		int k = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				k++;
				board1[k-1] = Main.g.board[i][j];
						
			}
		}
	}
	//public static void terminateNN(ArrayList<Double> out)
	//{
		
	//}
	public void createLayer(int layerid, char type, int nl)
	{
		layers.add(new Layer(layerid, type, nl));
		layerids.add(layerid);
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
	public void createLayer(int layerid, char type, int nl, Path nk)
	{
		layers.add(new Layer(layerid, type, nl, nk));
		layerids.add(layerid);
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
