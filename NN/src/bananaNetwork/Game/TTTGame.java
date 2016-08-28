package bananaNetwork.Game;

import java.util.ArrayList;

public class TTTGame 
{
	int winner;
	ArrayList<Integer[][]> boards = new ArrayList<Integer[][]>();
	public TTTGame(int w, ArrayList<Integer[][]> bds) 
	{
		winner = w;
		boards = bds;
	}
}
