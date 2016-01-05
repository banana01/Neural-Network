package main;

import java.io.IOException;

public class Main 
{
	int[] teacher1 = {0,0};
	int[] teacher2 = {0,0};
	float[] HNV = new float[27];
	public static Game g = new Game();
	Teacher t = new Teacher();
	classConstructor cc = new classConstructor();
	public static NodeController nc;
	public static void main(String args[])
	{
		Main ma = new Main();
		nc = new NodeController(ma.cc.createClass(), ma.cc.grabNetwork());
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
		//g.modifyLoc(0, 2, 1);
		//g.modifyLoc(1, 1, -1);
		//g.modifyLoc(2, 0, 1);
		//nc.populateBoard();
		//nc.createLayer(0, 'I', 9);
		//nc.createLayer(1, 'H', 27);
		//nc.createLayer(2, 'O', 9);
		//g.printBoard(g.board);
		
		//System.out.println(t.GetRandom());
		//m.printBoard(m.board);
		//m.modifyLoc(1, 0, 1);
		//m.modifyLoc(1, 1, 1);
		//m.modifyLoc(1, 2, 1);
		//m.printBoard(m.board);
		//t.updateBoard(m.board);
		//System.out.println(t.GetRandom());
		/*System.out.println(m.evaluate());
		m.clearBoard();
		m.printBoard();
		m.modifyLoc(0, 2, 4);
		m.modifyLoc(1, 1, 4);
		m.modifyLoc(2, 0, 4);
		m.printBoard();
		System.out.println(m.evaluate());
		m.clearBoard();
		m.printBoard();
		m.modifyLoc(0, 0, 8);
		m.modifyLoc(1, 1, 8);
		m.modifyLoc(2, 2, 8);
		m.printBoard();
		System.out.println(m.evaluate());
		m.clearBoard();
		m.printBoard();
		m.modifyLoc(0, 0, 1);
		m.modifyLoc(1, 0, 2);
		m.modifyLoc(2, 0, 3);
		m.modifyLoc(0, 1, 4);
		m.modifyLoc(1, 1, 5);
		m.modifyLoc(2, 1, 6);
		m.modifyLoc(0, 2, 7);
		m.modifyLoc(1, 2, 8);
		m.modifyLoc(2, 2, 9);
		m.printBoard();
		System.out.println(m.evaluate());*/
		
		
	}
	public void runGame()
	{
		do
		{
			checkMove();
			g.printBoard(g.board);
			if(g.evaluate(g.board) != 0)
			{
				break;
			}
			checkMoveB();
			g.printBoard(g.board);
		}
		while(g.leagalMove() >= 0 && g.evaluate(g.board) == 0);
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
		System.out.println("WinCheck:: "+Main.g.evaluate(Main.g.board));
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
		System.out.println("WinCheck:: "+Main.g.evaluate(Main.g.board));
	}
}
