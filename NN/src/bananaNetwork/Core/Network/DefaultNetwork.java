package bananaNetwork.Core.Network;

import java.util.ArrayList;

public class DefaultNetwork extends Network
{
	public DefaultNetwork(int netid)
	{
		super(netid);
	}
	public DefaultNetwork(int netid, String setting) 
	{
		super(netid, setting);
		connectNetwork();
		findOutputNodes();
		findInputNodes();
	}
	DefaultNetwork(ArrayList<Integer[]> con, int netid)
	{
		super(con, netid);
		connectNetwork();
		findOutputNodes();
		findInputNodes();
		
	}
	//Needs cheaking
	public void connectNetwork()
	{
		int min=0;
		Layer temp = null;
		Layer temp1 = null;
		while(min<super.getLayers().size()-1)
		for(int index=0; index < super.getLayers().size();index++)
		{
			if(super.getLayers().get(index).getID()==min)
			{
				temp=super.getLayers().get(index);
			}
			if(super.getLayers().get(index).getID()==min+1)
			{
				temp1=super.getLayers().get(index);
			}
			if(temp!=null && temp1!=null)
			{
				connectAllLayer(temp,temp1);
				min++;
			}
		}
	}
}
