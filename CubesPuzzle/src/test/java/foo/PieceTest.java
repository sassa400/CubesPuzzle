package foo;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * The class <code>PieceTest</code> contains tests for the class <code>{@link Piece}</code>.
 *
 * @author Sassa
 * @version $Revision: 1.0 $
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class PieceTest
{
	/**
	 * Run the Piece(int,int,int,int,int) constructor test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testPiece_1()
		throws Exception
	{
		int origNo = 0;
		int up = 3;
		int right = 21;
		int down = 26;
		int left = 4;

		Piece result = new Piece(origNo, up, right, down, left);

		assertNotNull(result);
		assertEquals("(0) ...oo\r\n    .ooo.\r\n    ooooo\r\n    .ooo.\r\n    .o.oo\r\n", result.toString());
	}

	/**
	 * Run the Piece clone() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testClone_1()
		throws Exception
	{
		Piece piece = new Piece(0, 3, 21, 26, 4);

		Piece result = piece.clone();

		assertNotNull(result);
		assertEquals("(0) ...oo\r\n    .ooo.\r\n    ooooo\r\n    .ooo.\r\n    .o.oo\r\n", result.toString());
	}

	/**
	 * Run the boolean edgeFitsTo(int,Edge) method test on all sides of RED 0.
	 *
	 * @throws Exception
	 */
	@Test
	public void testEdgeFitsTo_1()
		throws Exception
	{
		Piece piece = new Piece(0, 3, 21, 26, 4); //RED 0
		Edge edge1 = new Edge(new Piece(5, 12, 5, 27, 20), 2, true); //RED 5 bottom flipped
		Edge edge2 = new Edge(new Piece(4, 6, 10, 5, 10), 0, false); //RED 4 top
		Edge edge3 = new Edge(new Piece(1, 10, 4, 2, 10), 0, false); //RED 1 top
		Edge edge4 = new Edge(new Piece(3, 4, 4, 4, 10), 0, false); //RED 3 top

		assertEquals(true, piece.edgeFitsTo(3, edge1));
		assertEquals(true, piece.edgeFitsTo(0, edge2));
		assertEquals(true, piece.edgeFitsTo(1, edge3));
		assertEquals(true, piece.edgeFitsTo(2, edge4));
	}


	/**
	 * Run the void flipLeftRight() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testFlipLeftRight_1()
		throws Exception
	{
		Piece fixture = new Piece(0, 3, 21, 26, 4); //RED 0

		fixture.flipLeftRight();

		assertEquals(24, fixture.getEdgeValue(0));
		assertEquals(4, fixture.getEdgeValue(1));
		assertEquals(11, fixture.getEdgeValue(2));
		assertEquals(21, fixture.getEdgeValue(3));
	}

	/**
	 * Run the void flipTopBottom() method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testFlipTopBottom_1()
		throws Exception
	{
		Piece fixture = new Piece(0, 3, 21, 26, 4); //RED 0

		fixture.flipTopBottom();

		assertEquals(11, fixture.getEdgeValue(0));
		assertEquals(21, fixture.getEdgeValue(1));
		assertEquals(24, fixture.getEdgeValue(2));
		assertEquals(4, fixture.getEdgeValue(3));
	}

	/**
	 * Run the int getEdgeValue(int) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetEdgeValue_1()
		throws Exception
	{
		Piece fixture = new Piece(0, 3, 21, 26, 4); //RED 0
		int side = 1;

		int result = fixture.getEdgeValue(side);

		assertEquals(21, result);
	}

	/**
	 * Run the void rotate(int,int) test clockwise.
	 *
	 * @throws Exception
	 */
	@Test
	public void testRotate_1()
		throws Exception
	{
		Piece fixture = new Piece(0, 3, 21, 26, 4); //RED 0
		int from = 1;
		int to = 2;

		fixture.rotate(from, to);

		assertEquals(new Piece(0, 4, 3, 21, 26).toString(), fixture.toString());
	}

	/**
	 * Run the void rotate(int,int) test counterclockwise.
	 *
	 * @throws Exception
	 */
	@Test
	public void testRotate_2()
		throws Exception
	{
		Piece fixture = new Piece(0, 3, 21, 26, 4); //RED 0
		int from = 2;
		int to = 1;

		fixture.rotate(from, to);

		assertEquals(new Piece(0, 21, 26, 4, 3).toString(), fixture.toString());
	}

	/**
	 * Run the void rotate(int,int) test for two moves.
	 *
	 * @throws Exception
	 */
	@Test
	public void testRotate_3()
		throws Exception
	{
		Piece fixture = new Piece(0, 3, 21, 26, 4); //RED 0
		int from = 2;
		int to = 0;

		fixture.rotate(from, to);

		assertEquals(new Piece(0, 26, 4, 3, 21).toString(), fixture.toString());
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 */
	@Before
	public void setUp()
		throws Exception
	{
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 */
	@After
	public void tearDown()
		throws Exception
	{
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		new org.junit.runner.JUnitCore().run(PieceTest.class);
	}
}