package foo;

/**
 * This class holds a piece from the collection in HappyCube Puzzle. The piece is defined by its 4 edges clockwise from the top one. 
 * Edge values are calculated as decimal representation of the edge as a binary number. Binary digits are always read clockwise.
 * 
 *                       <br><br>
 *         -------->     <br>
 *         0 1 0 1 1     <br> 
 *         0 1 1 1 1     <br> 
 *         1 1 1 1 0     <br> 
 *         0 1 1 1 0     <br> 
 *         1 1 0 1 0     <br> 
 *         <--------     <br> 
 *
 *  top 01011 = 11, right 11000 = 24, bottom 01011 = 11, left 10100 = 20   

 * @author Sassa
 * @version $Revision: 1.0 $
 */
public class Piece
{
		private int edgeValue[] = new int[4];  //value of edges clockwise ordered
		private int origNo;  //original number of piece from collection (0-5)
		
		/**
		 * Constructor for Piece.
		 * @param origNo int
		 * @param up int
		 * @param right int
		 * @param down int
		 * @param left int
		 */
		public Piece(int origNo, int up, int right, int down, int left)
		{
			super();
			this.origNo = origNo;
			this.edgeValue[0] = up;
			this.edgeValue[1] = right;
			this.edgeValue[2] = down;
			this.edgeValue[3] = left;
		}		
		
		/**
		 * Returns edge value for a side of the piece
		 * @param side
		
		 * @return int */
		public int getEdgeValue(int side)
		{
			return edgeValue[side];
		}
		
		/**
		 * Rotates the piece so many steps clockwise that position 'from' becomes position 'to'
		 * @param from int
		 * @param to int
		 */
		public void rotate(int from, int to)
		{
			int rot = from < to ? to - from : 4 + to - from;
			for (int i = 1; i <= rot; i++)
			{
				rotate1();
			}
		}

		/**
		 * Rotates the piece one step clockwise
		 */
		public void rotate1()
		{
			int sav = edgeValue[3];
			edgeValue[3] = edgeValue[2];
			edgeValue[2] = edgeValue[1];
			edgeValue[1] = edgeValue[0];
			edgeValue[0] = sav;
		}

		/**
		 * Flips the piece by swapping left and right edge. All edge values are reverted.
		 */
		public void flipLeftRight()
		{
			for (int i = 0; i < 4; i++)
			{
				edgeValue[i] = Util.reverseBinary(edgeValue[i]);
			}
			
			int sav = edgeValue[1];
			edgeValue[1] = edgeValue[3];
			edgeValue[3] = sav;
		}

		/**
		 * Flips the piece by swapping top and bottom edge. All edge values are reverted.
		 */
		public void flipTopBottom()
		{
			for (int i = 0; i < 4; i++)
			{
				edgeValue[i] = Util.reverseBinary(edgeValue[i]);
			}
			
			int sav = edgeValue[0];
			edgeValue[0] = edgeValue[2];
			edgeValue[2] = sav;
		}

		/**
		 * Checks if <b>side</b> of the piece fits to <b>edge</b>. Fitting means that middle elements are complements and vertices do not collide. 
		 * @param side int
		 * @param edge Edge
		
		 * @return boolean */
		public boolean edgeFitsTo(int side, Edge edge)
		{
			String e1 = Util.bin5(this.edgeValue[side]);
			String e2 = Util.bin5(edge.getValue());
			
			// if it is anything from the below, it doesn't fit
			return !(e1.charAt(1) == e2.charAt(3) ||
					 e1.charAt(2) == e2.charAt(2) ||	
					 e1.charAt(3) == e2.charAt(1) ||	
					 (e1.charAt(0) == '1' && e2.charAt(4) == '1') ||
					 (e1.charAt(4) == '1' && e2.charAt(0) == '1'));
		}
		
		/**
		 * Clones piece.
		
		 * @return Piece */
		public Piece clone()
		{
			Piece piece = new Piece(origNo, edgeValue[0], edgeValue[1], edgeValue[2], edgeValue[3]);
			return piece;
		}

		/**
		 * Method toString, used for representation.
		
		 * @return String */
		public String toString()
		{
			String edgeString[] = new String[4];
			for (int i = 0; i < edgeString.length; i++)
			{
				edgeString[i] = Util.bin5(edgeValue[i]);
				edgeString[i] = edgeString[i].replace('1', 'o');
				edgeString[i] = edgeString[i].replace('0', '.');
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
				string.append(row==0 ? "(" + origNo + ") " : "    ");
				string.append(binPiece[row]);
				string.append(NEW_LINE);
			}
			return string.toString();
		}
}
