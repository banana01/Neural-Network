package bananaNetwork.Core.Network;

import java.io.IOException;
import java.util.ArrayList;

import bananaNetwork.Game.Game;
import bananaNetwork.Game.Teacher;
import bananaNetwork.Util.Writer;

public class Main 
{
	int[] teacher1 = {0,0};
	int[] teacher2 = {0,0};
	float[] HNV = new float[27];
	public static Game g = new Game();
	public static ArrayList<Double> finalout = new ArrayList<Double>();
	Teacher t = new Teacher();
	NetworkConstructor ncr = new NetworkConstructor();
	Writer wri;
	public static Network nc;

	public static void main(String args[])
	{
		Main ma = new Main();
		g.modifyLoc(0, 2, 1);
		g.modifyLoc(1, 1, -1);
		g.modifyLoc(2, 0, 1);
		g.modifyLoc(2, 1, 1);
		g.printBoard(g.getBoard());
		nc = new DefaultNetwork(1, g.convertTo1xN(), "9_27_9");
		try {
			ma.wri = new Writer(nc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < nc.networkOutput().length; i++) 
		{
			System.out.println();
		}
		g.printBoard(g.roundBoard(g.convertTo3x3(nc.networkOutput())));

		
		//g.printBoard(g.getBoard());
		//nc = new Network(ma.ncr.createClass(), ma.ncr.grabFromNetwork(), 7, g.convertTo1xN());
		//nc = new Network(5, g.convertTo1xN());
		//System.out.println(finalout);
		/*g.init();
		int count = 240000;
		long startTime = System.nanoTime();
		for(int i = 0; i < count; i++)
		{
			ma.runGame();
		}
		long endTime = System.nanoTime();
		try {
			g.dump();
		} catch (IOException e) {
		}
		System.out.println("Took "+(endTime - startTime) + " ns");
		System.out.println(g.owin+"::O wins");
		System.out.println(g.xwin+"::X wins");
		System.out.println(g.cats+"::cats");*/
		
		
	}
	public void runGame()
	{
		do
		{
			checkMove();
			g.printBoard(g.getBoard());
			if(g.evaluate(g.getBoard()) != 0)
			{
				break;
			}
			checkMoveB();
			g.printBoard(g.getBoard());
		}
		while(g.leagalMove() >= 0 && g.evaluate(g.getBoard()) == 0);
		g.clearBoard();
	}
	public void checkMove()
	{
		boolean loc = true;
		while(loc)
		{
			teacher1 = t.move();
			if(Main.g.checkLoc(teacher1) != 0)
			{
			}
			else
			{
				loc = false;
				Main.g.modifyLoc(teacher1[0], teacher1[1], 1);
			}
			
		
		}
		System.out.println("WinCheck:: "+Main.g.evaluate(Main.g.getBoard()));
	}
	public void checkMoveB()
	{
		boolean loc = true;
		while(loc)
		{
			teacher2 = t.move();
			if(Main.g.checkLoc(teacher2) != 0)
			{
			}
			else
			{
				loc = false;
				Main.g.modifyLoc(teacher2[0], teacher2[1], -1);
			}
			
		
		}
		System.out.println("WinCheck:: "+Main.g.evaluate(Main.g.getBoard()));
	}
}
