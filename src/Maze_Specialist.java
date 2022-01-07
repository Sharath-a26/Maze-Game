import java.util.*;

public class Maze_Specialist {
	private Maze_Runner runner_data;
	private ArrayList<LinkedList<Integer>> shortest_paths = new ArrayList<>();
	public static double shortest_distance;
	private boolean ispossible;
	private ArrayList<Cells> rearranged_blocks;
	

	
	public void setRunnerData(Maze_Runner runner_data) {
		this.runner_data = runner_data;
	}
	
	public boolean calculateIsPossible(Maze m1) {
		if(m1.getBlockCells().size() > 6)
			this.ispossible=  false;
		else
			this.ispossible =  true;
		return this.ispossible;
		
	}
	
	public void calculateScore(Maze m1, Maze_Runner runner) {
		
	}
	
	public double calculateDifference() {

		return runner_data.calculateDistance() - Maze_Specialist.shortest_distance;
	}
	
	public void setRearrangedBlocks(ArrayList<Cells> rearranged_blocks) {
		this.rearranged_blocks = rearranged_blocks;
	}
	
	public void findPaths(ArrayList<Cells> a1, ArrayList<Cells> a2) {

		ArrayList<Integer> blocked_cells = new ArrayList<>();      //creates two arraylists for cell_numbers
		ArrayList<Integer> path_cells = new ArrayList<>();
		ArrayList<Integer> dead_end = new ArrayList<>();
		HashMap<Integer,Cells> h = new HashMap<>();
		for(int i = 0; i < a1.size(); i++)
		{
			blocked_cells.add(a1.get(i).getCellNo());
			h.put(a1.get(i).getCellNo(),a1.get(i));
		}
		for(int i = 0; i < a2.size(); i++) {
			path_cells.add(a2.get(i).getCellNo());
			h.put(a2.get(i).getCellNo(),a2.get(i));
		}
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




			for(int i = 0; i < blocked_cells.size();i++)
			{
				int destroyed = blocked_cells.get(i);
				LinkedList<Integer> poss_path = new LinkedList<>();

				int x = 1;
				poss_path.add(x);
				while(x <= 36)
				{



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



								if(destroyed == (x + 12) + 7)
									x += 6;
								else if(destroyed == (x + 2) + 7)
									x++;
								else if(path_cells.contains(x + 12 + 7))
									x += 6;
								else if(path_cells.contains(x + 2 + 7))
									x++;
								else if(!dead_end.contains(x))
									x++;
								else
									x += 6;
							}


						}

						else
							x++;




						if(x < 36)
							poss_path.add(x);
						else if(x != 36)
							poss_path.add(36);

					}


				this.shortest_paths.add(poss_path); //printing poss_path

			}

			for(int i = 0; i < 6;i++)
				System.out.println("Destroyed Block : " + a1.get(i).getCellNo() + " " + "Path : " + this.shortest_paths.get(i).toString());
		System.out.println("\n");

			ArrayList<Double> distances = new ArrayList<>();
			for(int i = 0; i < this.shortest_paths.size(); i++)
			{
				double sum1 = 0.0;
				for(int j = 0; j < this.shortest_paths.get(i).size()-1; j++)
				{
						Integer k = this.shortest_paths.get(i).get(j+1);
						Integer t = this.shortest_paths.get(i).get(j);
						sum1 += Math.pow(Math.pow(h.get(k).getCoordinate().get(0)- h.get(t).getCoordinate().get(0),2.0) + Math.pow(h.get(k).getCoordinate().get(1)- h.get(t).getCoordinate().get(1),2.0),0.5);
				}
				System.out.println("Destroyed Block : " + a1.get(i).getCellNo() + " " + "Distance : " + sum1);
				distances.add(sum1);
			}
			Collections.sort(distances);
		System.out.println("\n Shortest Distance : " + distances.get(0));
		Maze_Specialist.shortest_distance = distances.get(0);

		}



	
	public Maze_Runner getRunnerData() {
		return runner_data;
	}
	
}
