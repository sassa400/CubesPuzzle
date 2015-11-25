package foo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Program finds a solution for HappyCube puzzle.
 * Solution is found by selecting piece that fits and iterate until solution is confirmed or rejected. <br>
 * Pieces are put in order from 0 to 5:<br><br>
 * __2__  <br>
 * 1_0_3  <br>
 * __4__  <br>
 * __5__  <br><br>
 * If solution exists, it is saved in the file "solution.txt".
 * @param (not required) puzzle collection - BLUE, RED or PURPLE, any other input gives no solution
 * @author Sassa
 * @version $Revision: 1.0 $
 */
public class CubesPuzzleApp 
{
    public static final int TOP = 0;
	public static final int RIGHT = 1;
	public static final int BOTTOM = 2;
	public static final int LEFT = 3;

	protected enum PUZZLE {BLUE, RED, PURPLE, UNSOLVABLE};

	private static Piece cube[] = new Piece[6];      //cube to solve
	private static Piece solPieces[] = new Piece[6]; //pieces that makes solution

	/**
	 * Method main.
	 * @param args String[]
	 */
	public static void main( String[] args )
    {
		PUZZLE whichPuzzle = PUZZLE.BLUE;  //default is blue collection ;
		
		if (args.length > 1) 
		{
			System.out.println( "Only one parameter allowed.");
			return;
		}
		else if (args.length == 1) 
		{
			if (args[0].equalsIgnoreCase("BLUE"))
			{
				whichPuzzle = PUZZLE.BLUE;
			}
			else if (args[0].equalsIgnoreCase("RED"))
			{
				whichPuzzle = PUZZLE.RED;
			}
			else if (args[0].equalsIgnoreCase("PURPLE"))
			{
				whichPuzzle = PUZZLE.PURPLE;
			}
			else 
			{
				whichPuzzle = PUZZLE.UNSOLVABLE;
			}
		}

        init(whichPuzzle); //change to test different puzzles
        solve();
    }
	
	protected static void solve()
	{
		//collection of edges available to connect
		ArrayList<Edge> edges1 = new ArrayList<Edge>(); 
		
		//skipping first piece because it is already fixed
		//so there are 5 pieces * 4 edges * 2 flipped = 40 edges for first step
		for (int i = 1; i < cube.length; i++)
		{
			final Edge tEdges[] = new Edge[4];
			final Edge fEdges[] = new Edge[4];
			for (int j = 0; j < 4; j++)
			{
				tEdges[j] = new Edge(cube[i], j, false);
				fEdges[j] = new Edge(cube[i], j, true);
				edges1.add(tEdges[j]);
				edges1.add(fEdges[j]);
			}
			//keep 'pointers' to neighbouring edges in the same piece 
			for (int j = 0; j < 4; j++)
			{
				tEdges[(j+1) % 4].setPrevEdge(tEdges[j]);
				tEdges[j].setNextEdge(tEdges[(j+1) % 4]);
				fEdges[(j+1) % 4].setNextEdge(fEdges[j]);
				fEdges[j].setPrevEdge(fEdges[(j+1) % 4]);
			}
		}
		
		solPieces[0] = cube[0]; //fixing first piece as the solution of step 0

		//step 1, search for first edge to connect
		if (solve1(edges1))
		{
			//solution is found, print it
			printSolutionT();
//			printSolutionCross(); //for printing in Cross shape
		}
		else
		{
			//no choice of edge leads to solution, so there is not any
			System.out.println( "There is no solution." );
		}
	}

	/**
	 * Cycle through available edges and for those which fits continue on step 2 
	 * @param edges - 40 edges (5 pieces + flipped)
	
	 * @return TRUE if this branch leads to solution, FALSE otherwise */
	private static boolean solve1(ArrayList<Edge> edges)
	{
		//looking for piece 1 which is left of piece 0
		for (Edge edge : edges)
		{
			if (solPieces[0].edgeFitsTo(LEFT, edge))
			{
				//found the edge that fits, so I declare its piece a part of solution
				addPieceToSolution(1, edge, RIGHT);

				//continue search for next piece with remaining edges
				if (solve2(getNextStepEdges(edges, edge)))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Cycle through available edges and for those which fits continue on step 3 
	 * @param edges - 32 edges (4 pieces + flipped)
	
	 * @return TRUE if this branch leads to solution, FALSE otherwise */
	private static boolean solve2(ArrayList<Edge> edges)
	{
		//looking for piece 2 which is above piece 0 and piece 1		
		for (Edge edge : edges)
		{
			//checks if edges fits and there is exactly one that fits to vertex
			if (solPieces[0].edgeFitsTo(TOP, edge) &&
				solPieces[1].edgeFitsTo(TOP, edge.getNextEdge()) &&
				Util.getVertex1(solPieces[0].getEdgeValue(TOP)) +
				Util.getVertex2(solPieces[1].getEdgeValue(TOP)) +
				Util.getVertex2(edge.getValue()) == 1)
			{
				addPieceToSolution(2, edge, BOTTOM);
				
				//continue search for next piece with remaining edges
				if (solve3(getNextStepEdges(edges, edge)))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Cycle through available edges and for those which fits continue on step 4 
	 * @param edges - 24 edges (3 pieces + flipped)
	
	 * @return TRUE if this branch leads to solution, FALSE otherwise */
	private static boolean solve3(ArrayList<Edge> edges)
	{
		//looking for piece 3 which is right of piece 0 and piece 2		
		for (Edge edge : edges)
		{
			//checks if edges fits and there is exactly one that fits to vertex
			if (solPieces[0].edgeFitsTo(RIGHT, edge) &&
				solPieces[2].edgeFitsTo(RIGHT, edge.getNextEdge()) &&
				Util.getVertex1(solPieces[0].getEdgeValue(RIGHT)) +
				Util.getVertex2(solPieces[2].getEdgeValue(RIGHT)) +
				Util.getVertex2(edge.getValue()) == 1)
			{
				addPieceToSolution(3, edge, LEFT);

				//continue search for next piece with remaining edges
				if (solve4(getNextStepEdges(edges, edge)))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Cycle through available edges and for those which fits continue on step 5 
	 * @param edges - 16 edges (2 pieces + flipped)
	
	 * @return TRUE if this branch leads to solution, FALSE otherwise */
	private static boolean solve4(ArrayList<Edge> edges)
	{
		//looking for piece 4 which is below piece 0, piece 1 and piece 3		
		for (Edge edge : edges)
		{
			//checks if edges fits and there is exactly one that fits to each vertex
			if (solPieces[0].edgeFitsTo(BOTTOM, edge) &&
				solPieces[1].edgeFitsTo(BOTTOM, edge.getPrevEdge()) &&
				solPieces[3].edgeFitsTo(BOTTOM, edge.getNextEdge()) &&
				Util.getVertex2(solPieces[0].getEdgeValue(BOTTOM)) +
				Util.getVertex1(solPieces[1].getEdgeValue(BOTTOM)) +
				Util.getVertex1(edge.getValue()) == 1 &&
				Util.getVertex1(solPieces[0].getEdgeValue(BOTTOM)) +
				Util.getVertex2(solPieces[3].getEdgeValue(BOTTOM)) +
				Util.getVertex2(edge.getValue()) == 1)
			{
				addPieceToSolution(4, edge, TOP);

				ArrayList<Edge> edges5 = getNextStepEdges(edges, edge);
				
				//continue search for next piece with remaining edges
				if (solve5(edges5))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if edges from remaining piece can fit into solution
	 * @param edges - 8 edges (1 piece + flipped) 
	
	 * @return TRUE if fits, FALSE otherwise */
	private static boolean solve5(ArrayList<Edge> edges)
	{
		//looking for piece 5 which is below piece 4, above piece 2, left of piece 1 and right of piece 3		
		for (Edge edge : edges)
		{
			//checks if edges fits and there is exactly one that fits to each vertex
			if (solPieces[4].edgeFitsTo(BOTTOM, edge) &&
				solPieces[1].edgeFitsTo(LEFT, edge.getPrevEdge()) &&
				solPieces[3].edgeFitsTo(RIGHT, edge.getNextEdge()) &&
				solPieces[2].edgeFitsTo(TOP, edge.getNextEdge().getNextEdge()) &&
				Util.getVertex2(solPieces[4].getEdgeValue(BOTTOM)) +
				Util.getVertex1(solPieces[1].getEdgeValue(LEFT)) +
				Util.getVertex1(edge.getValue()) == 1 &&
				Util.getVertex1(solPieces[4].getEdgeValue(BOTTOM)) +
				Util.getVertex2(solPieces[3].getEdgeValue(RIGHT)) +
				Util.getVertex2(edge.getValue()) == 1 &&
				Util.getVertex1(solPieces[2].getEdgeValue(TOP)) +
				Util.getVertex2(solPieces[1].getEdgeValue(LEFT)) +
				Util.getVertex2(edge.getNextEdge().getNextEdge().getValue()) == 1 &&
				Util.getVertex2(solPieces[2].getEdgeValue(TOP)) +
				Util.getVertex1(solPieces[3].getEdgeValue(RIGHT)) +
				Util.getVertex1(edge.getNextEdge().getNextEdge().getValue()) == 1 )
			{
				addPieceToSolution(5, edge, TOP);
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds the piece that belongs to the edge in the solution step. The piece should be rotated so the chosen edge comes to right place to fit.  
	 * 
	 * @param step int - step of the process of the solving
	 * @param edge Edge - edge which piece is added to the solution
	 * @param side int - side of piece where edge should be fitted. 
	 */
	private static void addPieceToSolution(int step, Edge edge, int side)
	{
		//found the edge that fits, so I declare its piece a part of solution
		//the piece is rotated and flipped if needed so it looks exactly like it should in the solution 
		solPieces[step] = edge.getPiece().clone();
		solPieces[step].rotate(edge.getSide(), side);
		if (edge.isFlipped())
		{
			if (side == TOP || side == BOTTOM)
			{
				solPieces[step].flipLeftRight(); // fit to top or bottom, so flip left-right not to change fitted side								
			}
			else
			{
				solPieces[step].flipTopBottom(); // fit to left or right, so flip top-bottom not to change fitted side		
			}
		}
	}
	
	/**
	 * Returns collection of edges for next step by removing edges from recently fitted piece
	 * @param edges ArrayList<Edge> 
	 * @param edge - recently fitted edge which piece should be removed
	
	 * @return edges ArrayList<Edge> - new collection of edges without ones from used pieces */
	protected static ArrayList<Edge> getNextStepEdges( ArrayList<Edge> edges, Edge edge)
	{
		//clone list of edges in every step to ensure each branch has its own correct list
		@SuppressWarnings("unchecked")
		ArrayList<Edge> edgesNext = (ArrayList<Edge>) edges.clone(); 
		Piece piece = edge.getPiece(); //piece that contains this edge
		
		//explicitly use iterator so active item could be removed in process
		Iterator<Edge> it = edgesNext.iterator();
		while (it.hasNext()) 
		{
		    Edge edgeNext = it.next();
			if (piece.equals(edgeNext.getPiece()))
			{
				it.remove(); //remove all edges which originate from that piece 
			}
		}	
		return edgesNext;
	}
	
	public static Piece[] getSolPieces()
	{
		return solPieces;
	}

	/**
	 * Initialization of cube pieces 
	 * @param puzzle
	 */
	protected static void init(PUZZLE puzzle)
	{
		switch (puzzle)
		{
		case BLUE:
			cube[0] = new Piece(0, 4, 4, 4, 4);
			cube[1] = new Piece(1, 21, 27, 21, 27);
			cube[2] = new Piece(2, 4, 10, 4, 4);
			cube[3] = new Piece(3, 10, 5, 27, 26);
			cube[4] = new Piece(4, 10, 11, 20, 10);
			cube[5] = new Piece(5, 10, 11, 26, 4);
			break;

		case RED:
			cube[0] = new Piece(0, 3, 21, 26, 4);
			cube[1] = new Piece(1, 10, 4, 2, 10);
			cube[2] = new Piece(2, 13, 27, 25, 26);
			cube[3] = new Piece(3, 4, 4, 4, 10);
			cube[4] = new Piece(4, 6, 10, 5, 10);
			cube[5] = new Piece(5, 12, 5, 27, 20);
			break;

		case PURPLE:
			cube[0] = new Piece(0, 11, 24, 11, 20);
			cube[1] = new Piece(1, 13, 28, 5, 28);
			cube[2] = new Piece(2, 10, 5, 27, 24);
			cube[3] = new Piece(3, 4, 10, 8, 4);
			cube[4] = new Piece(4, 10, 5, 24, 12);
			cube[5] = new Piece(5, 4, 7, 26, 2);
			break;

		default: //UNSOLVABLE, purple with error
			cube[0] = new Piece(0, 12, 24, 11, 20);
			cube[1] = new Piece(1, 13, 28, 5, 28);
			cube[2] = new Piece(2, 10, 5, 27, 24);
			cube[3] = new Piece(3, 4, 10, 8, 4);
			cube[4] = new Piece(4, 10, 5, 24, 12);
			cube[5] = new Piece(5, 4, 7, 26, 2);
			break;
		}
		
//		for (int i = 0; i < cube.length; i++)
//		{
//			System.out.println( cube[i].toString() );
//		}

	}
	
	/**
	 * Prints solution to a file in Ascii format (Cross shape)
	 * @param ind int
	 */
	@SuppressWarnings("unused")
	private static void printSolutionCross()
	{
		PrintWriter writer = null;
		try	{
			writer = new PrintWriter("solution.txt", "UTF-8");
		}
		catch (FileNotFoundException e)	{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		writer.println(printSolution1(2));
		int sol[] = {1, 0, 3};
		writer.println(printSolution3(sol));
		writer.println(printSolution1(4));
		writer.println(printSolution1(5));
		writer.close();
	}
	
	/**
	 * Prints solution to a file in Ascii format (T shape)
	 * @param ind int
	 */
	private static void printSolutionT()
	{
		PrintWriter writer = null;
		try	{
			writer = new PrintWriter("solution.txt", "UTF-8");
		}
		catch (FileNotFoundException e)	{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		int sol[] = {1, 0, 3};
		writer.println(printSolution3(sol));
		writer.println(printSolution1(4));
		writer.println(printSolution1(5));
		writer.println(printSolution1(2));
		writer.close();
	}

	/**
	 * Prints one piece in a row
	 * @param ind int
	 */
	private static String printSolution1(int ind)
	{
		String edgeString[] = new String[4];
		for (int i = 0; i < edgeString.length; i++)
		{
			edgeString[i] = Util.bin5(solPieces[ind].getEdgeValue(i));
			edgeString[i] = edgeString[i].replace('1', 'o');
			edgeString[i] = edgeString[i].replace('0', ' ');
		}

		String binPiece[] = new String[5];
		binPiece[0] = edgeString[0];
		binPiece[1] = edgeString[3].charAt(3) + "ooo" + edgeString[1].charAt(1);
		binPiece[2] = edgeString[3].charAt(2) + "ooo" + edgeString[1].charAt(2);
		binPiece[3] = edgeString[3].charAt(1) + "ooo" + edgeString[1].charAt(3);
		binPiece[4] = new StringBuffer(edgeString[2]).reverse().toString(); 
			
		final StringBuffer string = new StringBuffer("");
		final String NEW_LINE = System.getProperty("line.separator");

		for (int row = 0; row < 5; row++) 
		{
//			string.append(row==0 ? "(" + origNo + ") " : "    ");
			string.append("     ");
			string.append(binPiece[row]);
			if (row<4)
			{
				string.append(NEW_LINE);
			}
		}
		return string.toString() ;
	}
	
	/**
	 * Prints three pieces in a row
	 * @param ind int[] 
	 */
	private static String printSolution3(int ind[])
	{
		String edgeString[][] = new String[3][4];
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				edgeString[j][i] = Util.bin5(solPieces[ind[j]].getEdgeValue(i));
				edgeString[j][i] = edgeString[j][i].replace('1', 'o');
				edgeString[j][i] = edgeString[j][i].replace('0', ' ');
			}
		}

		String space = "";
		String binPiece[] = new String[5];
		binPiece[0] = edgeString[0][0] + space + edgeString[1][0] + space + edgeString[2][0];
		binPiece[1] = edgeString[0][3].charAt(3) + "ooo" + edgeString[0][1].charAt(1) + space +
		              edgeString[1][3].charAt(3) + "ooo" + edgeString[1][1].charAt(1) + space +
		              edgeString[2][3].charAt(3) + "ooo" + edgeString[2][1].charAt(1);
		binPiece[2] = edgeString[0][3].charAt(2) + "ooo" + edgeString[0][1].charAt(2) + space +
					  edgeString[1][3].charAt(2) + "ooo" + edgeString[1][1].charAt(2) + space +
		              edgeString[2][3].charAt(2) + "ooo" + edgeString[2][1].charAt(2);
		binPiece[3] = edgeString[0][3].charAt(1) + "ooo" + edgeString[0][1].charAt(3) + space +
		              edgeString[1][3].charAt(1) + "ooo" + edgeString[1][1].charAt(3) + space +
		              edgeString[2][3].charAt(1) + "ooo" + edgeString[2][1].charAt(3);
		binPiece[4] = new StringBuffer(edgeString[0][2]).reverse().toString() + space +
				      new StringBuffer(edgeString[1][2]).reverse().toString() + space +
				      new StringBuffer(edgeString[2][2]).reverse().toString(); 
			
		final StringBuffer string = new StringBuffer("");
		final String NEW_LINE = System.getProperty("line.separator");

		for (int row = 0; row < 5; row++) 
		{
//			string.append("    ");
			string.append(binPiece[row]);
			if (row<4)
			{
				string.append(NEW_LINE);
			}
		}
		return string.toString() ;
	}

}
