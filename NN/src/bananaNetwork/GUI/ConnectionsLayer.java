package bananaNetwork.GUI;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.plaf.LayerUI;

public class ConnectionsLayer extends LayerUI<JComponent>
{
	private Point point;
	public ConnectionsLayer()
	{
		//setBackground(new Color(0,0,0,0));
	}
	public void paint(Graphics g, JComponent c) {
        // paint the layer as is
        super.paint(g, c);
        // fill it with the translucent green
        g.setColor(new Color(0, 128, 0, 128));
        g.fillRect(0, 0, 150, 150);
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        // enable mouse motion events for the layer's subcomponents
        ((JLayer) c).setLayerEventMask(AWTEvent.MOUSE_MOTION_EVENT_MASK);
    }

    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        // reset the layer event mask
        ((JLayer) c).setLayerEventMask(0);
    }

    // overridden method which catches MouseMotion events
    public void eventDispatched(AWTEvent e, JLayer<? extends JComponent> l) {
        System.out.println("AWTEvent detected: " + e);
    }
}
