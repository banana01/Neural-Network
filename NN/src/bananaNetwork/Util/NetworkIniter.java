package bananaNetwork.Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import bananaNetwork.Core.Network.Layer;
import bananaNetwork.Core.Network.Network;
import bananaNetwork.Core.Network.Node;

public class NetworkIniter 
{
	BufferedWriter BFW;
	FileWriter FW;
	public NetworkIniter(Network ntk) throws IOException
	{
		buildNetworkFolders(ntk);
		for (int i = 0; i < ntk.getLayers().size(); i++) 
		{
			Layer temp = ntk.getLayers().get(i);
			buildLayerFolder(temp);
			for (int j = 0; j < temp.getNodes().size(); j++) 
			{
				buildNodeFile(temp.getNodes().get(j));
			}
		}
	}
	public boolean buildNetworkFolders(Network ntk)
	{
		
		if(!ntk.getPath().toFile().exists())
		{
			ntk.getPath().toFile().mkdir();
		}
		return true;
	}
	public boolean buildLayerFolder(Layer lay)
	{
		
		if(!lay.getPath().toFile().exists())
		{
			lay.getPath().toFile().mkdir();
		}
		return true;
	}
	public boolean buildNodeFile(Node n) throws IOException
	{
		if(!n.getPath().toFile().exists())
		{
			initFile(n.getPath());
		}
		return true;
	}
	public boolean initFile(Path p) throws IOException
	{
		FW = new FileWriter(p.toFile());
		FW.flush();
		return true;
		
	}
}
