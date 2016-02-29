package bananaNetwork.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class ConnectionsLayer extends JPanel implements MouseListener
{
	private Point point;
	public ConnectionsLayer()
	{
		setOpaque(false);
		//setBackground(new Color(0,0,0,0));
	}

	
	
	
	
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
        int x = 10;
        int y = 10;
        int width = getWidth() - 20;
        int height = getHeight() - 20;
        g.drawArc(x, y, width, height, 0, 360);
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
