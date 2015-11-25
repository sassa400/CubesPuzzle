package foo;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * The class <code>CubesPuzzleAppTest</code> contains tests for the class <code>{@link CubesPuzzleApp}</code>.
 *
 * @author Sassa
 * @version $Revision: 1.0 $
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class CubesPuzzleAppTest
{
	/**
	 * Run the CubesPuzzleApp() constructor test.
	 */
	@Test
	public void testCubesPuzzleApp_1()
		throws Exception
	{
		CubesPuzzleApp result = new CubesPuzzleApp();
		assertNotNull(result);
	}

	/**
	 * Run the complete test solving RED puzzle.
	 *
	 * @throws Exception
	 */
	@Test
	public void testSolveRed()
		throws Exception
	{

		CubesPuzzleApp.init(CubesPuzzleApp.PUZZLE.RED);
		CubesPuzzleApp.solve();
		Piece solPieces[] = CubesPuzzleApp.getSolPieces();
		assertEquals(new Piece(0,  3, 21, 26,  4).toString(), solPieces[0].toString());
		assertEquals(new Piece(5,  5, 27, 20,  6).toString(), solPieces[1].toString());
		assertEquals(new Piece(4,  5, 26,  6, 10).toString(), solPieces[2].toString());
		assertEquals(new Piece(1,  4,  2, 10, 10).toString(), solPieces[3].toString());
		assertEquals(new Piece(3,  4,  4,  4, 10).toString(), solPieces[4].toString());
		assertEquals(new Piece(2, 27, 22, 11, 19).toString(), solPieces[5].toString());
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
		new org.junit.runner.JUnitCore().run(CubesPuzzleAppTest.class);
	}
}