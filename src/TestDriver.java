
import java.util.*;
public class TestDriver {
	public static void main(String args[]) {
		Cells c[] = new Cells[36];
		int j = 1;
		for(int i = 0; i < 36; i++)												//cell objects created along with co-ordinates
		{
			ArrayList<Integer> pairs = new ArrayList<>();
			pairs.add(i/6+1);
			pairs.add(j);
			if(i+1 != 2 && i+1 != 14 && i+1 != 16 && i+1 != 18 && i+1 != 28 && i+1 != 32)
				
				c[i] = new Cells(pairs,false,i+1);
			else
				c[i] = new Cells(pairs,true,i+1);
			if(j < 6)
				j++;
			else
				j = 1;
			
		}
		
		
		ArrayList<Cells> blocked = new ArrayList<>();				//blocked cells
		ArrayList<Cells> notblocked = new ArrayList<>(); 			//unblocked cells
		for(int i = 0; i < 36; i++)
		{
			if(c[i].getIsBlocked())
				blocked.add(c[i]);
			else
				notblocked.add(c[i]);
		}
		
		for(int i = 0; i < 36; i++)
		{
			if(i == 1)											
				c[i].isDestroyed(true);
			else
				c[i].isDestroyed(false);
		}
		Maze maze = new Maze(blocked,notblocked);
		
		Maze_Runner runner = new Maze_Runner();
		runner.setDestroyedBlock(c[1]);
		String[] moves = {"diagonal","right","right","right","down","down","diagonal","down"};
		runner.setMove(moves);
		
		
		for(Cells i : runner.calculatePath(c))
		{
			System.out.print(i.getCellNo()+ " " + i.getCoordinate() + " ");
		}
		System.out.println("");
		System.out.println(runner.calculateDistance() + " meters");
		Maze_Specialist specialist = new Maze_Specialist();
		System.out.println(specialist.calculateIsPossible(blocked, notblocked));
	}
}
