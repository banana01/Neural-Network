package bananaNetwork.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import bananaNetwork.Core.Network.Connection;
import bananaNetwork.Core.Network.Layer;
import bananaNetwork.Core.Network.Network;
import bananaNetwork.Core.Network.Node;

public class Writer
{
	File file;
	BufferedWriter BFW;
	FileWriter FW;
	ArrayList<Path> layers = new ArrayList<Path>();
	public Writer(Network ntk) throws IOException
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
		runWriter(ntk);
		dump();
	}
	public boolean runWriter(Network ntk) throws IOException
	{
		for (int i = 0; i < ntk.getLayers().size(); i++) 
		{
			Layer temp = ntk.getLayers().get(i);
			for (int j = 0; j < temp.getNodes().size(); j++) 
			{
				writeToNodeFile(temp.getNodes().get(j));
			}
		}
		return true;
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
	public boolean writeToNodeFile(Node n) throws IOException
	{
		
		BFW = new BufferedWriter(new FileWriter(n.getPath().toFile(), false));
		for (int i = 0; i < n.getInputConnections().size(); i++) 
		{
			Connection temp = n.getInputConnections().get(i);
			BFW.write(temp.getIn().getParent().getID()+"L_"+temp.getIn().getID()+"_"+temp.getWeight());
			BFW.newLine();
		}
		//FW.flush();
		BFW.flush();
		return true;
	}
	public void dump() throws IOException
	{
		//FW.close();
		BFW.close();
		
	}
}
