package bananaNetwork.GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import bananaNetwork.Core.Network.Node;

public final class MyFactory {
	/**
	 * @wbp.factory
	 */
	public static GUINode createNODEButton(Node n) 
	{
		GUINode button = new GUINode(n);
		button.setForeground(Color.red);
		return button;
	}
	/**
	 * @throws IOException 
	 * @wbp.factory
	 */
	public static TTTButton createBoardPosButton(int t, Path e, Path o, Path x) throws IOException {
		TTTButton button = new TTTButton(t, e, o, x);
		return button;
	}
}