package bananaNetwork.Game;

import java.util.ArrayList;

public class TTTGame 
{
	int winner;
	ArrayList<Object[][]> boards = new ArrayList<Object[][]>();
	public TTTGame(int w, ArrayList<Object[][]> bds) 
	{
		winner = w;
		boards = bds;
	}
}
