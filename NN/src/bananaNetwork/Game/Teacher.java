package bananaNetwork.Game;
public class Teacher 
{
	//dumbass teacher random dumbass
	public int[] move()
	{
		Game g = new Game();
		int temp = (int) Math.floor(g.GetRandom());
		int col = -1, row = 0;
		if(temp > 3)
		{
			row++;
			temp -= 3;
			if(temp > 3)
			{
				row++;
				temp -=3;
				if(temp == 3)
				{
					col = 2;
				}
				else
				{
					col += temp;
				}
				
			}
			else
			{
				col += temp;
			}
		}
		else
		{
			col += temp;
		}
		
		int[] temp1= {row,col};
			return temp1;

		
	}
	
}
