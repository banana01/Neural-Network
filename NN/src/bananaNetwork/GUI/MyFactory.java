package bananaNetwork.GUI;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import bananaNetwork.Core.Network.Node;

public final class MyFactory {
	/**
	 * @wbp.factory
	 */
	public static JButton createNODEButton(Node n) 
	{
		JButton button = new JButton("NODE" + n.getID());
		button.setForeground(Color.red);
		return button;
	}
	/**
	 * @wbp.factory
	 */
	public static JButton createBoardPosButton(ImageIcon t) {
		JButton button = new JButton(t);
		return button;
	}
}