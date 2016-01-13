package bananaNetwork.Util;

import java.awt.List;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import bananaNetwork.Core.Network.Layer;
import bananaNetwork.Core.Network.Network;
import bananaNetwork.Core.Network.Node;

public class NetworkReader 
{
	Scanner NFile;
	Path copyFrom;
	ArrayList<Path> layers = new ArrayList<Path>();
	ArrayList<ArrayList<Path>> nodes = new ArrayList<ArrayList<Path>>();
	public NetworkReader(Network ntk, Path clone) throws FileNotFoundException
	{
		setCopyFrom(clone);
		getLayerStructure();
		for (int i = 0; i < layers.size(); i++) 
		{
			nodes.add(getNodeStructure(layers.get(i)));
		}
		for (int i = 0; i < layers.size(); i++) 
		{
			ntk.createLayer(Integer.parseInt(layers.get(i).toString().split("_")[0]));
			for (int j = 0; j < ntk.getLayers().size(); j++) 
			{
				if(ntk.getLayers().get(j).getID() == Integer.parseInt(layers.get(i).toString().split("_")[0]))
				{
					for(int k = 0; k < nodes.get(i).size(); k++)
					{
						ntk.getLayers().get(j).createNode(Integer.parseInt(nodes.get(i).get(k).toString().split("_")[0]));
					}
					
					
				}
			}
			
		}
		//runReader(ntk);
	}

	public Path getCopyFrom() {
		return copyFrom;
	}
	public void setCopyFrom(Path copyFrom) {
		this.copyFrom = copyFrom;
	}
	





	public boolean getLayerStructure()
	{
		ArrayList<Path> LF = new ArrayList<Path>();
		for (int i = 0; i < copyFrom.toFile().listFiles().length; i++) 
		{
			if(copyFrom.toFile().listFiles()[i].toString().contains("layer"))
			{
				LF.add(copyFrom.toFile().listFiles()[i].toPath());
			}
			
		}
		layers = LF;
		return true;
	}
	public ArrayList<Path> getNodeStructure(Path lp)
	{
		ArrayList<Path> NF = new ArrayList<Path>();
		for (int i = 0; i < lp.toFile().listFiles().length; i++) 
		{
			if(lp.toFile().listFiles()[i].toString().contains("weights"))
			{
				NF.add(lp.toFile().listFiles()[i].toPath());
			}
			
		}
		return NF;
	}
	public boolean runReader(Network ntk) throws FileNotFoundException
	{
		for (int i = 0; i < ntk.getLayers().size(); i++) 
		{
			Layer temp = ntk.getLayers().get(i);
			for (int j = 0; j < temp.getNodes().size(); j++) 
			{
				readFromNodeFile(temp.getNodes().get(j));
			}
		}
		return true;
	}
	public boolean readFromNodeFile(Node n) throws FileNotFoundException
	{
		String[] lineData;
		NFile = new Scanner(n.getPath().toFile());
		while(NFile.hasNextLine())
		{
			lineData = NFile.nextLine().split("_");
			n.getInputConnectionToNode(Integer.parseInt(lineData[1]),Integer.parseInt(lineData[0])).setWeight(Double.parseDouble(lineData[2]));
		}
		return true;
	}
	public void dump()
	{
		NFile.close();
	}
}
