package bananaNetwork.Core.Network;

import java.nio.file.Path;
import java.util.ArrayList;

public class DefaultNetwork extends Network
{
	DefaultNetwork(int netid, double[] maininput)
	{
		super(netid, maininput);
	}
	DefaultNetwork(int netid, double[] maininput, String setting) 
	{
		super(netid, maininput, setting);
		initNetwork(setting);
		connectNetwork();
		findOutputNodes();
		findInputNodes();
		sendNetworkInput(maininput);
		runNetwork();
	}
	DefaultNetwork(ArrayList<Integer[]> con, Path from, int netid, double[] maininput)
	{
		super(con, from, netid, maininput);
		
	}
	public void initNetwork(String s)
	{
		String[] layersST = s.split("_");
		for (int i = 0; i < layersST.length; i++) 
		{
			createLayer(i, Integer.parseInt(layersST[i]));
		}
		
	}
	public void connectNetwork()
	{
		defaultStructure(super.getLayers().get(0), super.getLayers().get(1));
		defaultStructure(super.getLayers().get(1), super.getLayers().get(2));
	}
	public void defaultStructure(Layer in, Layer out)
	{
		for (int i = 0; i < in.getNodes().size(); i++) 
		{
			for (int j = 0; j < out.getNodes().size(); j++) 
			{
				createConnection(in.getNodes().get(i),out.getNodes().get(j));
			}
		}
	}
}
