package bananaNetwork.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JSplitPane;

import bananaNetwork.Core.Network.DefaultNetwork;
import bananaNetwork.Core.Network.Network;
import bananaNetwork.Core.Network.NetworkConstructor;
import bananaNetwork.Game.Game;
import bananaNetwork.Game.Teacher;
import bananaNetwork.Util.NetworkIniter;
import bananaNetwork.Util.NetworkReader;
import bananaNetwork.Util.NetworkWriter;

import java.awt.GridLayout;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.FlowLayout;

public class Window {

	private JFrame frame;
	DisplayManager DM;
	JPanel mainPanel = new JPanel();
	JPanel[] layerPanels;
	JButton[] nodeButtons;
	int[] teacher1 = {0,0};
	int[] teacher2 = {0,0};
	float[] HNV = new float[27];
	public static Game g = new Game();
	public static ArrayList<Double> finalout = new ArrayList<Double>();
	Teacher t = new Teacher();
	NetworkConstructor ncr = new NetworkConstructor();
	NetworkWriter wri;
	NetworkReader rea;
	NetworkIniter ini;
	public static Network nc;
	//private ArrayList<JPanel> layerPanels = new ArrayList<JPanel>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Window window = new Window();
					window.frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 760, 784);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		//JButton btnNewButton = MyFactory.createNODEButton();
		//frame.getContentPane().add(btnNewButton);
		//===========================================================================
		
		try {
			TicTacToeBoard TTTB= new TicTacToeBoard(Paths.get("src\\bananaNetwork\\Assets\\iconx.png"),Paths.get("src\\bananaNetwork\\Assets\\icono.png"),Paths.get("src\\bananaNetwork\\Assets\\iconb.png"));
			mainPanel.add(TTTB);
			TTTB.initButtons();
			//System.out.println(TTTB.getButtons()[1].getX());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		
		
		
		/*
		g.modifyLoc(1, 2, -1);
		g.modifyLoc(2, 2, -1);
		g.modifyLoc(1, 1, -1);
		g.printBoard(g.getBoard());
		nc = new DefaultNetwork(5, "9_27_9");
		try {
			ini = new NetworkIniter(nc);
			//rea = new NetworkReader(nc, Paths.get("src\\main\\network1"));
			
			//System.out.println(nc.getLayers().get(0).getNodes().get(0).getOutputConnections().get(0).getWeight());
			nc.inputUpdate(g.convertTo1xN());
			wri = new NetworkWriter(nc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DM = new DisplayManager(nc);
		drawLayers();
		drawNodes();*/
		
	}
	public void drawLayers()
	{
		layerPanels = new JPanel[DM.getLayersSize()];
		for (int i = 0; i < layerPanels.length; i++) 
		{
			layerPanels[i] = new JPanel();
			mainPanel.add(layerPanels[i]);
		}
	}
	public void drawNodes()
	{
		int nod = 0;
		for (int i = 0; i < DM.getNodes().size(); i++) 
		{
			nod += DM.getNodes().get(i).length;
		}
		nodeButtons = new JButton[nod];
		for (int i = 0; i < DM.getLayersSize(); i++) 
		{
			
			for (int j = 0; j < DM.getNodes().get(i).length; j++) 
			{
				layerPanels[i].add(MyFactory.createNODEButton(DM.getNodes().get(i)[j]));
			}
			
		}
		
	}
}
