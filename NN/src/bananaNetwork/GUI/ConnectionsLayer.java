package bananaNetwork.GUI;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.RepaintManager;
import javax.swing.plaf.LayerUI;

public class ConnectionsLayer extends LayerUI<JComponent>
{
	private Map<Point, Point> links;
	private JButton last;

	public ConnectionsLayer()
	{
		//setBackground(new Color(0,0,0,0));
		links = new HashMap<Point, Point> ();
	}
	
	public void paint(Graphics g, JComponent c) {
        // paint the layer as is
        super.paint(g, c);
        // fill it with the translucent green
        g.setColor(new Color(0, 128, 0, 128));
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint ( Color.BLACK );
        for ( Point c1 : links.keySet () )
        {
            Point p1 = c1;
            Point p2 = links.get(c1);
            g2d.drawLine ( p1.x, p1.y, p2.x, p2.y );
        }
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        // enable mouse motion events for the layer's subcomponents
        //((JLayer) c).setLayerEventMask(AWTEvent.MOUSE_MOTION_EVENT_MASK);
        ((JLayer) c).setLayerEventMask(AWTEvent.MOUSE_EVENT_MASK);
        
    }

    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        // reset the layer event mask
        ((JLayer) c).setLayerEventMask(0);
    }
    public void link(JButton link)
    {
    	if(last == null)
    	{
    		last = link;
    	}
    	else
    	{
    		link(last.getLocation(), link.getLocation());
    		System.out.println("LINKED"+last.getName()+"AND:"+link.getName());
    		last = null;
    	}
    }
    public void link(Point p1, Point p2)
    {
    	links.put(p1, p2);
    	
    }
    public void drawConnection(Graphics g, GUINode GN)
    {
    	Point p = MouseInfo.getPointerInfo().getLocation();
    	g.drawLine(GN.getX(),GN.getY(),(int)Math.ceil(p.getX()),(int)Math.ceil(p.getY()));
    }
    
    
    
    // overridden method which catches MouseMotion events
    public void eventDispatched(AWTEvent e, JLayer<? extends JComponent> l) 
    {
        //System.out.println("AWTEvent detected: " + e);
    }
}
