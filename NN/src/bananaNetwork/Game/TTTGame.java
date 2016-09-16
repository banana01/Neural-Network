package bananaNetwork.Game;

import java.util.ArrayList;

public class TTTGame implements java.io.Serializable
{
	int winner;
	ArrayList<Object[][]> boards = new ArrayList<Object[][]>();
	public TTTGame(int w, ArrayList<Object[][]> bds) 
	{
		winner = w;
		boards = bds;
	}
}
