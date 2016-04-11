package bananaNetwork.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bananaNetwork.Core.Network.Node;

public class GUINode extends JPanel implements MouseListener
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
		JLabel t = new JLabel("Node:"+nd.getID());
		add(t);
		
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
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("THIS IS AN EVENT"+e);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}
