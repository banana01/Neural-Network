package bananaNetwork.Util;

import java.io.FileNotFoundException;
import java.util.Scanner;

import bananaNetwork.Core.Network.Layer;
import bananaNetwork.Core.Network.Network;
import bananaNetwork.Core.Network.Node;

public class NetworkReader 
{
	Scanner NFile;
	public NetworkReader(Network ntk) throws FileNotFoundException
	{
		runReader(ntk);
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
			System.out.println(lineData[0]);
			n.getInputConnectionToNode(Integer.parseInt(lineData[1]),Integer.parseInt(lineData[0])).setWeight(Double.parseDouble(lineData[2]));
		}
		return true;
	}
	public void dump()
	{
		NFile.close();
	}
}
