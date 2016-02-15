package bananaNetwork.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

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
import java.awt.CardLayout;

public class Window {

	private JFrame frame;
	NeuralNetworkDisplay DM;
	JPanel mainPanel = new JPanel();

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
		
		//JButton btnNewButton = MyFactory.createNODEButton();
		//frame.getContentPane().add(btnNewButton);
		//===========================================================================
		nc = new DefaultNetwork(5, "9_27_9");
		try {
			mainPanel.setLayout(new CardLayout(0, 0));
			JTabbedPane JTP = new JTabbedPane();
			mainPanel.add(JTP);
			NeuralNetworkDisplay NND = new NeuralNetworkDisplay(nc);
			TicTacToeBoard TTTB= new TicTacToeBoard(Paths.get("src\\bananaNetwork\\Assets\\iconb.png"),Paths.get("src\\bananaNetwork\\Assets\\icono.png"),Paths.get("src\\bananaNetwork\\Assets\\iconx.png"));
			JTP.addTab("Board!", TTTB);
			JTP.addTab("NN!", NND);
			//mainPanel.add(TTTB, "name_22006726515848");
			NND.init();
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
	
}
