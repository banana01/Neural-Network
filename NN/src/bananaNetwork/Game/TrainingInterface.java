package bananaNetwork.Game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TrainingInterface
{

	File					Games;
	ArrayList<Object[][]>	game	= new ArrayList<Object[][]>();
	ArrayList<TTTGame>		games	= new ArrayList<TTTGame>();

	public TrainingInterface(Path gameHistory, int amtToProp)
	{
		Games = new File("src\\main\\Games");
		if (!Games.exists())
		{
			Games.mkdir();
		}
		parseGameData(gameHistory);
		for (int i = 0; i < amtToProp; i++)
		{
			backProp(games.get(i).winner, games.get(i).boards);
		}
	}

	public void parseGameData(Path gameHistory)
	{
		try
		{
			Scanner sc = new Scanner(gameHistory.toFile());
			int position = 0;

			String line;
			int winner = 0;
			String[] moves;
			Integer[][] board = { { 5, 5, 5 }, { 5, 5, 5 }, { 5, 5, 5 } };
			Integer[][] baseBoard = { { 5, 5, 5 }, { 5, 5, 5 }, { 5, 5, 5 } };
			while (sc.hasNextLine())
			{

				line = sc.nextLine();
				if (line.contains("@"))
				{
					System.out.println(Arrays.deepToString(game.get(2)) + "AT2");
					System.out.println(Arrays.deepToString(game.get(3)) + "AT3");
					switch (line.charAt(line.indexOf("@") + 1))
					{
					case '0':
						winner = 1;
						break;
					case 'X':
						winner = -1;
						break;
					case 'C':
						winner = 0;
						break;
					default:
						break;
					}
					System.out.println(Arrays.deepToString(game.get(2)) + "AT2");
					System.out.println(Arrays.deepToString(game.get(3)) + "AT3");
					serializeWriteGames(new TTTGame(winner, game));
					// writeGame(game);
					games.add(new TTTGame(winner, game));
					game.clear();
				} else
				{

					for (int j = 0; j < 3; j++)
					{

						if (line.contains("-"))
							break;

						moves = line.split("");

						for (int i = 0; i < moves.length; i++)
						{
							switch (moves[i])
							{
							case "0":
								board[j][i] = 1;
								break;
							case "_":
								board[j][i] = 0;
								break;
							case "X":
								board[j][i] = -1;
								break;

							default:
								break;
							}

						}
						line = sc.nextLine();

					}
					game.add(board);
					board = baseBoard;
					System.out.println(Arrays.deepToString(game.get(position)) + "AT" + position);
					position++;
					writeGame(game);

				}
			}
			sc.close();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	public int grabIndex()
	{
		File[] files = Games.listFiles();
		int largest = 0;
		for (int i = 0; i < files.length; i++)
		{
			int value = Integer.parseInt(
					files[i].getName().substring(files[i].getName().indexOf("_") + 1, files[i].getName().indexOf(".")));
			if (value > largest)
			{
				largest = value;
			}

		}
		return largest + 1;

	}

	public ArrayList<Object[][][]> backProp(int winner, ArrayList<Object[][]> gameBoard)
	{
		Integer[][] Mboard = null, Sboard, diffBoard;

		int gameLength = gameBoard.size();
		ArrayList<Object[][][]> gameOut = new ArrayList<Object[][][]>();
		Double[][] outputBoardX = { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } };
		Double[][] outputBoardO = { { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0 } };
		Object[][][] out = { outputBoardX, outputBoardO, outputBoardO };
		if (winner == 0)
		{
			return null;
		}
		for (int i = 0; i < gameLength; i++)
		{
			Mboard = (Integer[][]) gameBoard.get(gameLength - i - 1);
			Sboard = (Integer[][]) gameBoard.get(gameLength - i);
			diffBoard = addBoards(Mboard, Sboard);

			for (int j = 0; j < Mboard.length; j++)
			{
				for (int j2 = 0; j2 < Mboard[j].length; j2++)
				{
					if (Mboard[j][j2] != 0)
					{
						outputBoardX[j][j2] = -1.0;
						outputBoardO[j][j2] = -1.0;
					} else
					{
						if (winner == -1)
						{
							if (diffBoard[j][j2] == -1)
							{
								outputBoardX[j][j2] = (double) (1 / (gameLength - i));
							} else
							{
								outputBoardX[j][j2] = (double) (-1 / (gameLength - i));
							}
							if (Sboard[j][j2] == 0)
							{
								outputBoardX[j][j2] = (double) (-1 / (gameLength - i));
							}
							if (Sboard[j][j2] == 0)
							{
								outputBoardO[j][j2] = (double) (1 / (gameLength - i));
							}

						}

						if (winner == 1)
						{
							if (diffBoard[j][j2] == 1)
							{
								outputBoardO[j][j2] = (double) (1 / (gameLength - i));
							} else
							{
								outputBoardO[j][j2] = (double) (-1 / (gameLength - i));
							}
							if (Sboard[j][j2] == 0)
							{
								outputBoardO[j][j2] = (double) (-1 / (gameLength - i));
							}
							if (Sboard[j][j2] == 0)
							{
								outputBoardX[j][j2] = (double) (1 / (gameLength - i));
							}
						}

					}

				}
			}
			out[0] = (winner == -1) ? outputBoardX : outputBoardO;
			out[1] = Mboard;
			out[2] = Sboard;
			gameOut.add(out);

		}
		return gameOut;

	}

	private Integer[][] addBoards(Integer[][] board1, Integer[][] board2)
	{
		Integer[][] out = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
		for (int i = 0; i < board2.length; i++)
		{
			for (int j = 0; j < board2[i].length; j++)
			{
				if (Math.abs(board1[i][j] + board2[i][j]) == 1)
				{
					out[i][j] = board2[i][j];
				}
			}
		}

		return out;
	}

	private void serializeWriteGames(TTTGame game)
	{
		try
		{
			FileOutputStream fileout = new FileOutputStream(Games + "\\Game_" + grabIndex() + ".gam");
			ObjectOutputStream out = new ObjectOutputStream(fileout);
			out.writeObject(game);
			out.close();
			fileout.close();
			System.out.println("wrote a game to " + Games.toString());

		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private TTTGame deserializeGame(Path p)
	{
		TTTGame in = null;

		try
		{
			FileInputStream FIO = new FileInputStream(p.toFile());
			ObjectInputStream OIS = new ObjectInputStream(FIO);
			in = (TTTGame) OIS.readObject();
			OIS.close();
			FIO.close();
			return in;
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			System.out.println("game not found");
			return null;
		}
		return null;

	}

	// Needs to be replaced with serializable TTTGames
	private void writeGame(ArrayList<Object[][]> test)
	{
		try
		{
			BufferedWriter BF = new BufferedWriter(new PrintWriter(Games + "\\Game_" + grabIndex() + ".txt"));
			for (int i = 0; i < test.size(); i++)
			{
				BF.write(Arrays.deepToString(test.get(i)));
				BF.newLine();

				BF.newLine();
			}
			BF.flush();
			BF.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
