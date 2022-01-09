
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TestDriver {
	public static void main(String args[]) throws IOException {

		System.out.println("\n--- WELCOME TO THE MAZE GAME ---\n\n");

		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\HP\\OneDrive - Amrita Vishwa Vidyapeetham (1)\\Desktop\\mazegame.txt"));
		String f = br.readLine();
		String t;
		System.out.println("Number of Cells : 36\n");
		System.out.println(f);
		ArrayList<Integer> block_cells = new ArrayList<>();
		while(!((t = br.readLine()).equals("*")))
		{
			block_cells.add(Integer.parseInt(t.trim()));
			System.out.print(t + " ");
		}
		System.out.println("\n");
		System.out.println("Path Cells ");
		for(int i = 1; i <= 36; i++)
		{
			if(!block_cells.contains(i))
				System.out.print(i + " ");
		}
		System.out.println("\n");

		Cells c[] = new Cells[36];
		int j = 1;
		for(int i = 0; i < 36; i++)												//cell objects created along with co-ordinates
		{
			ArrayList<Integer> pairs = new ArrayList<>();
			pairs.add(i/6+1);
			pairs.add(j);
			if(!block_cells.contains(i))
				
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
		f = br.readLine();
		System.out.println("\n" + f);

		while(!((t = br.readLine()).equals("*")))
		{
			runner.setDestroyedBlock(c[Integer.parseInt(t)-1]);
			System.out.println(t+"\n");
		}

		ArrayList<String> moves = new ArrayList<>();
		f = br.readLine();
		while(!((t = br.readLine()).equals("*")))
		{
			moves.add(t.trim());
		}
		



		runner.setMove(moves);

		LinkedList<Integer> runner_path = new LinkedList<>();
		for(Cells i : runner.calculatePath(c))
		{
			runner_path.add(i.getCellNo());
		}
		System.out.println("Runner's path : " + runner_path.stream().map(Object::toString).collect(Collectors.joining(" -> ")));

		System.out.println("");
		System.out.println("Distance moved by the Runner from Start to Target : "+ runner.calculateDistance() + " metres");
		Maze_Specialist specialist = new Maze_Specialist();
		specialist.findPaths(blocked, notblocked);
		specialist.setRunnerData(runner);
		runner.setDifference(specialist);

	}
}
