package bananaNetwork.GUI;

import java.awt.AWTEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
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
	private HashMap<Point, Point> links;
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

       // g.drawLine(5, 500, 500, 500);
        Graphics2D g2d = (Graphics2D)g;
        //g.setPaint ( Color.BLACK );
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.BLACK);
        // TODO: WTF IS GOING ON HERE IT WONT GO THROUGH THE ITORATOR AND I HAVE NO IDEA WY IT WONT DO THE WHILE LOOP NORE A FOR LOOP
       // System.out.println(links.entrySet());
       // Iterator it = links.entrySet().iterator();
        //System.out.println(it.hasNext());
        /*while (it.hasNext()) {
        	Map.Entry pair = (Map.Entry)it.next();
        	System.out.println(pair.getKey());
            Point p1 = (Point) pair.getKey();
            Point p2 = (Point) pair.getValue();
            g2d.drawLine( (int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY() );
            System.out.println("DEBUG2");
        }*/
        //links.put(new Point(50, 500), new Point(50, 500));
        System.out.println(links.keySet());
        for(Point e:links.keySet())
        {
        	Point p1 = e;
            Point p2 = links.get(e);
            System.out.println(p1+"TOTATO");
        }
        //drawConnection(g, new Point(500, 50));
       
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
    		System.out.println(links.values());
    	}
    	
    }
    public void link(Point p1, Point p2)
    {
    	links.put(p1, p2);
    	System.out.println("THIS HAPPENDED");
    	
    	
    }
    public void drawConnection(Graphics g, Point GN)
    {
    	Point p = MouseInfo.getPointerInfo().getLocation();
    	g.drawLine((int)GN.getX(),(int)GN.getY(),(int)Math.ceil(p.getX()),(int)Math.ceil(p.getY()));
    }
    
    
    
    // overridden method which catches MouseMotion events
    public void eventDispatched(AWTEvent e, JLayer<? extends JComponent> l) 
    {
        //System.out.println("AWTEvent detected: " + e);
    }
}
