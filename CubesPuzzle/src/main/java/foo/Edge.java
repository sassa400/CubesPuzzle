package foo;

/**
 * This class holds an edge of a piece. Algorithm keeps a collection of unused edges in particular iteration to find another fit.
 * Basically these are just 'pointers' to sides of pieces. 
 * 
 * @author Sassa
 * @version $Revision: 1.0 $
 */
public class Edge
{
	private Piece piece; //original piece edge belongs to
	private int side;    //side of original piece where this edge belongs
	private boolean flipped; //is the edge flipped
	private Edge prevEdge;  //edge that precedes this edge (counterclockwise neighbour)
	private Edge nextEdge; //edge that follows this edge (clockwise neighbour)

	/**
	 * Constructor for Edge.
	 * @param piece - original piece edge belongs to
	 * @param side - side of original piece where this edge belongs
	 * @param flipped - is the edge flipped
	 */
	public Edge(Piece piece, int side, boolean flipped)
	{
		super();
		this.piece = piece;
		this.side = side;
		this.flipped = flipped;

	}

	/**
	 * Returns value of the edge
	
	 * @return int */
	public int getValue()
	{
		int value = piece.getEdgeValue(side); 
		
		return flipped ? Util.reverseBinary(value) : value;
	}
	
	/**
	 * Checks if edge is flipped.
	
	 * @return boolean */
	public boolean isFlipped()
	{
		return flipped;
	}

	/**
	 * Returns original piece which is edge part of
	
	 * @return Piece */
	public Piece getPiece()
	{
		return piece;
	}

	/**
	 * Returns side of original piece where the edge belongs
	
	 * @return int */
	public int getSide()
	{
		return side;
	}

	/**
	 * Returns edge that precedes this edge (counterclockwise neighbour)
	
	 * @return Edge */
	public Edge getPrevEdge()
	{
		return prevEdge;
	}

	/**
	 * Set previous edge (counterclockwise)
	 * @param prevEdge Edge
	 */
	public void setPrevEdge(Edge prevEdge)
	{
		this.prevEdge = prevEdge;
	}

	/**
	 * Returns edge that succedes this edge (clockwise neighbour)
	
	 * @return Edge */
	public Edge getNextEdge()
	{
		return nextEdge;
	}

	/**
	 * Set next edge (clockwise)
	 * @param nextEdge Edge
	 */
	public void setNextEdge(Edge nextEdge)
	{
		this.nextEdge = nextEdge;
	}
}
