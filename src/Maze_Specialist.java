import java.util.*;

public class Maze_Specialist {
	private Maze_Runner runner_data;
	private ArrayList<LinkedList<Cells>> shortest_paths;
	public static double shortest_distance;
	private boolean ispossible;
	private ArrayList<Cells> rearranged_blocks;
	

	
	public void setRunnerData(Maze_Runner runner_data) {
		this.runner_data = runner_data;
	}
	
	public boolean calculateIsPossible(ArrayList<Cells> a1, ArrayList<Cells> a2) {        /*function to check whether there
																							 is atleast one path to reach target*/
		boolean possible = true;
		ArrayList<Integer> blocked_cells = new ArrayList<>();      //creates two arraylists for cell_numbers
		ArrayList<Integer> path_cells = new ArrayList<>();
		ArrayList<Integer> dead_end = new ArrayList<>();
 		for(int i = 0; i < a1.size(); i++)
			blocked_cells.add(a1.get(i).getCellNo());
		for(int i = 0; i < a2.size(); i++)
			path_cells.add(a2.get(i).getCellNo());
		for(int i = 0; i < a1.size(); i++)
		{
			if((a1.get(i).getCoordinate().get(0) == 1 || a1.get(i).getCoordinate().get(0) == 6) || (a1.get(i).getCoordinate().get(1) == 1 || a1.get(i).getCoordinate().get(1) == 6))
				dead_end.add(a1.get(i).getCellNo());
		}
		for(int i = 0; i <a2.size();i++)
		{
			if((a2.get(i).getCoordinate().get(0) == 1 || a2.get(i).getCoordinate().get(0) == 6) || (a2.get(i).getCoordinate().get(1) == 1 || a2.get(i).getCoordinate().get(1) == 6))
				dead_end.add(a2.get(i).getCellNo());
		}


		if(blocked_cells.contains(36))
			possible = false;
		else
		{
			for(int i = 0; i < blocked_cells.size();i++)
			{
				int destroyed = blocked_cells.get(i);
				LinkedList<Integer> poss_path = new LinkedList<>();
				int x = 1;
				poss_path.add(x);
				while(x <= 36)
				{
					
					if((blocked_cells.contains(x+1) && destroyed != x+1) && (blocked_cells.contains(x+6) && destroyed != x+6))
						possible = false;
					else
					{
						possible = true;
						if(blocked_cells.contains(x+1) && path_cells.contains(x+6))
						{
							if(destroyed != x+1)
								x += 6;
							else
								x += 7;

						}
						else if(blocked_cells.contains(x+6) && path_cells.contains(x+1))
						{
							if(destroyed != x+6)
								x++;
							else
								x += 7;

						}
						else if((blocked_cells.contains(x+1) && destroyed == x+6) || (blocked_cells.contains(x+6) && destroyed == x+1))
						{
							x = destroyed;

						}
						else if((path_cells.contains(x+1) || destroyed == x+6) && (path_cells.contains(x+6) || destroyed == x+6))
						{
							if(path_cells.contains(x+7) || destroyed == x+7)
								x += 7;
							else {
							if (dead_end.contains(x + 1))
								x += 6;
							else
								x++;
							}

							
						}



						else if((path_cells.contains(x+1) || destroyed == x+1) && (path_cells.contains(x+6) || destroyed == x+6) && (blocked_cells.contains(x+7) && destroyed != x+7))
						{
							x++;

						}
						else
						{
							x+=6;

						}
						
					if(x < 36)
						poss_path.add(x);
					else if(x != 36)
						poss_path.add(36);

					}
					
				}
				System.out.println(poss_path.toString()); //printing poss_path
					
			}

		}
		return possible;
		
		
	}
	
	public void calculateScore(Maze m1, Maze_Runner runner) {
		
	}
	
	public void calculateDifference() {
		
	}
	
	public void setRearrangedBlocks(ArrayList<Cells> rearranged_blocks) {
		this.rearranged_blocks = rearranged_blocks;
	}
	
	public void findPaths() {
		
	}
	
	public Maze_Runner getRunnerData() {
		return runner_data;
	}
	
}
