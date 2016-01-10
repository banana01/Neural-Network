package bananaNetwork.Game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import bananaNetwork.Core.Network.Main;

public class Game 
{
	File gameStore;
	File gameFolder;
	FileWriter fwg;
	BufferedWriter bfg;
	int owin=0, xwin= 0,cats=0;
	int gameindex = 0;
	private int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
	public Game()
	{
		
		gameFolder = new File("src\\main\\gameHistory");
		if(!gameFolder.exists())
		{
			gameFolder.mkdir();
		}
		gameindex = grabIndex()+1;
		gameStore = new File("src\\main\\gameHistory\\run_"+gameindex+".txt");
		
	}
	public double[] convertTo1xN()
	{
		int k = 0;
		double[] temp = new double[9];
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				k++;
				temp[k-1] = (double)Main.g.getBoard()[i][j];
						
			}
		}
		return temp;
	}
	public double[][] convertTo3x3(double[] in)
	{
		double[][] temp = new double[3][3];
		int k = 0;
		for(int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++) 
			{
				temp[i][j] = in[k];
				k++;
			}
		}
		return temp;
		
	}
	public int[][] roundBoard(double[][] dbb)
	{
		int[][] temp1 = {{0,0,0},{0,0,0},{0,0,0}};
		double biggest = -5;
		
		for(int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++) 
			{
				if(dbb[i][j] > biggest)
				{
					biggest = dbb[i][j];
				}
			}
		}
		for(int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++) 
			{
				if(biggest == dbb[i][j])
				{
					temp1[i][j] = 1;
				}
			}
		}
		return temp1;
	}
	//prints board
	public void printBoard(int[][] bd)
	{
		for(int[] i : bd)
		{
			for(int j : i)
			{
				
				System.out.print(j);
			}
			System.out.println("\n");
		}
	}
	//clears board
	public void clearBoard()
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				getBoard()[j][i] = 0;
			}
		}
	}
	//checks number of blanks on board
	public int leagalMove()
	{
		int zeroCount = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(getBoard()[i][j] == 0)
				{
					zeroCount++;
				}
			}
		}
		return zeroCount;
	}
	//changes location on board if legal, also calls update
	public int modifyLoc(int r, int c, int change)
	{
		if (getBoard()[r][c] == 0)
		{
			getBoard()[r][c] = change;
			//boardUpdate();
			return 0;
		}
		else
		{
			return -2;
		}
	}
	//used for dumbass teachers
	public float GetRandom()
	{
		Random r = new Random();
		return r.nextFloat() * leagalMove() + 1;
	}
	/**
	 * @return the board
	 */
	public int[][] getBoard() {
		return board;
	}
	/**
	 * @param board the board to set
	 */
	public void setBoard(int[][] board) {
		this.board = board;
	}
	//checks location on board for state
	public int checkLoc(int[] loc)
	{
		return getBoard()[loc[0]][loc[1]];
	}
	//called when board is changed
	public void boardUpdate()
	{
		
		storeGame();
		
	}
	//gets index of previous run
	public int grabIndex()
	{
		File[] files = gameFolder.listFiles();
		int largest = 0;
		for(int i = 0; i < files.length; i++)
		{
			int value = Integer.parseInt(files[i].getName().substring(files[i].getName().indexOf("_")+1, files[i].getName().indexOf(".")));
			if (value > largest)
			{
				largest = value;
			}
			
		}
		return largest;
		
	}
	//outputs game data to text file
	public void storeGame()
	{
		char[][] parsedBoard = {{'_','_','_'},{'_','_','_'},{'_','_','_'}};
		try {
			
			for(int i = 0; i < getBoard().length; i++)
			{
				for(int j = 0; j < getBoard().length; j++)
				{
					if(getBoard()[i][j] == -1)
					{
						parsedBoard[i][j] = '0';
					}
					else if(getBoard()[i][j] == 1)
					{
						parsedBoard[i][j] = 'X';
					}
					else if(getBoard()[i][j] != 0)
					{
						parsedBoard[i][j] = 'E';
					}
				}
			}
			for(int i = 0; i < parsedBoard.length; i++)
			{
				bfg.newLine();
				bfg.write(parsedBoard[i]);
				
			}
			bfg.newLine();
			if(evaluate(getBoard()) != 0)
			{
				
				bfg.newLine();
				if(evaluate(getBoard()) == 1)
				{
					xwin++;
					bfg.write("@X@");
				}
				else if(evaluate(getBoard()) == -1)
				{
					owin++;
					bfg.write("@0@");
				}
				else
				{
					cats++;
					bfg.write("@C@");
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//checks for winner or cats game
	public int evaluate(int[][] bd)
	{
		//vertical win test
		int[] temp = {0,0,0};
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				//System.out.println("debug V"+i+":"+j);
				temp[j] = bd[j][i];
			}
			if(temp[0] == temp[1] && temp[1] ==temp[2] && temp[0] != 0)
			{
				//System.out.println("terminate V"+i+":");
				return temp[1];
			}
			
		}
		//horizontal win test
		int[] temp1 = {0,0,0};
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				temp1[j] = bd[i][j];
			}
			if(temp1[0] == temp1[1] && temp1[1] ==temp1[2] && temp1[0] != 0)
			{
				return temp1[1];
			}
			
		}
		//diagonal left right win test
		int[] temp2 = {0,0,0};
		for(int i = 0; i < 3; i++)
		{
			
			temp2[i] = bd[i][i];
		}
		if(temp2[0] == temp2[1] && temp2[1] == temp2[2] && temp2[0] != 0)
		{
			return temp2[1];
		}
		//diagonal right left win test
		int[] temp3 = {0,0,0};
		for(int i = 0; i < 3; i++)
		{
			temp3[i] = bd[i][2-i];
		}
		if(temp3[0] == temp3[1] && temp3[1] == temp3[2] && temp3[0] != 0)
		{
			return temp3[1];
		}
		//still can play win test
		int temp4 = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if (bd[i][j] == 0)
				{
					temp4++;
					if(temp4 > 0)
					{
						return 0;
					}

				}
				
			}
		}
		//cats game
		return 10000;
		
	}
	//inits file io
	public void init()
	{
		try 
		{
			fwg = new FileWriter(gameStore, true);
		} 
		catch (IOException e) 
		{
		}
		bfg = new BufferedWriter(fwg);
	}
	//cleans up
	public void dump() throws IOException
	{
		bfg.flush();
		fwg.flush();
		fwg.close();
		bfg.close();
	}
	
}
