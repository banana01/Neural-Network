package bananaNetwork.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bananaNetwork.Core.Network.Node;

public class GUINode extends JButton
{
	private Node nd;
	private String TT;
	public GUINode(Node n)
	{
		setNd(n);
		test();
		init();
	}
	public Node getNd() {
		return nd;
	}
	public void setNd(Node nd) {
		this.nd = nd;
	}
	
	//====================================================================//
	public void test()
	{
		this.setText("Node:"+nd.getID());
		//JLabel t = new JLabel("Node:"+nd.getID());
		//add(t);
		
	}
	public Point getLoc()
	{
		return new Point(getX(), getY());
	}
	public void init()
	{
		TT = "<html>"+"Node ID:: "+nd.getID()+"<br>"+"Node Value:: "+nd.getValue()+"</html>";
		this.setBackground(Color.gray);
		setToolTipRecursively(this, TT);
	}
	public static void setToolTipRecursively(JComponent c, String text) {

	    c.setToolTipText(text);

	    for (Component cc : c.getComponents())
	            setToolTipRecursively((JComponent) cc, text);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
}
