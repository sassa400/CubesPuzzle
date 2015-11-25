package foo;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * The class <code>EdgeTest</code> contains tests for the class <code>{@link Edge}</code>.
 *
 * @author Sassa
 * @version $Revision: 1.0 $
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class EdgeTest
{
	/**
	 * Run the Edge test 1.
	 *
	 * @throws Exception
	 */
	@Test
	public void testEdge_1()
		throws Exception
	{
		Piece piece = new Piece(0, 3, 21, 26, 4); //RED 0
		int side = 0;
		boolean flipped = false;

		Edge result = new Edge(piece, side, flipped);
		Edge prevEdge = new Edge(piece, 3, flipped); //previous of 0 is 3 
		Edge nextEdge = new Edge(piece, 1, flipped); //next of 0 is 1
		result.setPrevEdge(prevEdge); 
		result.setNextEdge(nextEdge);

		assertNotNull(result);
		assertEquals(3, result.getValue());
		assertEquals(0, result.getSide());
		assertEquals(false, result.isFlipped());
		assertEquals(prevEdge, result.getPrevEdge());
		assertEquals(nextEdge, result.getNextEdge());
	}

	/**
	 * Run the Edge test 2 - flipped edge.
	 *
	 * @throws Exception
	 */
	@Test
	public void testEdge_2()
		throws Exception
	{
		Piece piece = new Piece(0, 3, 21, 26, 4); //RED 0
		int side = 1;
		boolean flipped = true;

		Edge result = new Edge(piece, side, flipped);
		Edge prevEdge = new Edge(piece, 2, flipped); //it is flipped, therefore 2 is previous
		Edge nextEdge = new Edge(piece, 0, flipped); //it is flipped, therefore 0 is previous
		result.setPrevEdge(prevEdge); 
		result.setNextEdge(nextEdge);

		assertNotNull(result);
		assertEquals(21, result.getValue());
		assertEquals(1, result.getSide());
		assertEquals(true, result.isFlipped());
		assertEquals(prevEdge, result.getPrevEdge());
		assertEquals(nextEdge, result.getNextEdge());
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
		new org.junit.runner.JUnitCore().run(EdgeTest.class);
	}
}