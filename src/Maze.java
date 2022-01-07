import java.util.*;

public class Maze {
	private final int noOfCells = 36;
	private ArrayList<Cells> block_cells;
	private ArrayList<Cells> path_cells;
	private ArrayList<Cells> changed_blocks;
	
	public Maze(ArrayList<Cells> block_cells,ArrayList<Cells> path_cells) 
	{
	
		this.block_cells = block_cells;
		this.path_cells = path_cells;
		
	}
	
	public int getnoOfCells() {
		return noOfCells;
	}
	
	public ArrayList<Cells> getBlockCells() {
		return block_cells;
	}
	
	public ArrayList<Cells> getPathCells() {
		return path_cells;
	}
	
	public ArrayList<Cells> getChangedBlocks() {
		return changed_blocks;
	}
	
	public void setAll(ArrayList<Cells> block_cells,ArrayList<Cells> path_cells) {

		this.block_cells = block_cells;
		this.path_cells = path_cells;
	}
	
	public void setChangedBlock(ArrayList<Cells> changed_blocks) {
		if(!(new Maze_Specialist().calculateIsPossible(this.getBlockCells(),this.getPathCells())))
		{
			System.out.println("Blocks position is invalid");
			this.changeBlock();
		}
		
	}
	
	public void changeBlock() {
		
		
	}
}
