
import java.util.*;
import java.util.stream.Collectors;

public class TestDriver {
	public static void main(String args[]) {

		System.out.println("\n WELCOME TO THE MAZE GAME \n\n");

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
			c[i].setIsDestroyed(i+1 == 2 || i+1 == 14 || i+1 == 16 || i+1 == 18 || i+1 == 28 || i+1 == 32);


		Maze maze = new Maze(blocked,notblocked);
		
		Maze_Runner runner = new Maze_Runner();
		runner.setDestroyedBlock(c[1]);
		String[] moves = {"diagonal","right","right","right","down","down","diagonal","down"};
		runner.setMove(moves);

		LinkedList<Integer> runner_path = new LinkedList<>();
		for(Cells i : runner.calculatePath(c))
		{
			runner_path.add(i.getCellNo());
		}
		System.out.println("Runner's path : " + runner_path.stream().map(Object::toString).collect(Collectors.joining(" -> ")));

		System.out.println("");
		System.out.println("Distance moved from Start to Target : "+ runner.calculateDistance() + " metres");
		Maze_Specialist specialist = new Maze_Specialist();
		specialist.findPaths(blocked, notblocked);
		specialist.setRunnerData(runner);
		runner.setDifference(specialist);

	}
}
