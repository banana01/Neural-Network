package bananaNetwork.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ConnectionsLayer extends Component implements MouseListener
{
	private Point point;
	public ConnectionsLayer()
	{
		
	}

	
	
	
	
	protected void paintComponent(Graphics g) 
	{
	        g.setColor(Color.red);
	        g.fillOval(point.x - 10, point.y - 10, 20, 20);
	}
	    
	 
	    
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		point.setLocation(e.getX(), e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
