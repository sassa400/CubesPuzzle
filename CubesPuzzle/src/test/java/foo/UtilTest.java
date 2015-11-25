package foo;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * The class <code>UtilTest</code> contains tests for the class <code>{@link Util}</code>.
 *
 * @author Sassa
 * @version $Revision: 1.0 $
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class UtilTest
{
	/**
	 * Run the String bin5(int) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testBin5_1()
		throws Exception
	{
		int number = 13;

		String result = Util.bin5(number);

		assertEquals("01101", result);
	}

	/**
	 * Run the String bin5(int) method test.
	 *
	 * @throws Exception
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testBin5_2()
		throws Exception
	{
		int number = -1;

		Util.bin5(number);
	}

	/**
	 * Run the String bin5(int) method test.
	 *
	 * @throws Exception
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testBin5_3()
		throws Exception
	{
		int number = 32;

		Util.bin5(number);
	}

	/**
	 * Run the int getVertex1(int) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetVertex1_1()
		throws Exception
	{
		int value = 1;

		int result = Util.getVertex1(value);

		assertEquals(0, result);
	}

	/**
	 * Run the int getVertex1(int) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetVertex1_2()
		throws Exception
	{
		int value = 16;

		int result = Util.getVertex1(value);

		assertEquals(1, result);
	}

	/**
	 * Run the int getVertex2(int) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetVertex2_1()
		throws Exception
	{
		int value = 1;

		int result = Util.getVertex2(value);

		assertEquals(1, result);
	}

	/**
	 * Run the int getVertex2(int) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetVertex2_2()
		throws Exception
	{
		int value = 16;

		int result = Util.getVertex2(value);

		assertEquals(0, result);
	}

	/**
	 * Run the int reverseBinary(int) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testReverseBinary_1()
		throws Exception
	{
		int number = 1;

		int result = Util.reverseBinary(number);

		assertEquals(16, result);
	}

	/**
	 * Run the int reverseBinary(int) method test.
	 *
	 * @throws Exception
	 */
	@Test
	public void testReverseBinary_2()
		throws Exception
	{
		int number = 13;

		int result = Util.reverseBinary(number);

		assertEquals(22, result);
	}

	/**
	 * Run the int reverseBinary(int) method test.
	 *
	 * @throws Exception
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testReverseBinary_3()
		throws Exception
	{
		int number = -1;

		int result = Util.reverseBinary(number);

		assertEquals(0, result);
	}

	/**
	 * Run the int reverseBinary(int) method test.
	 *
	 * @throws Exception
	 */
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testReverseBinary_4()
		throws Exception
	{
		int number = 32;

		int result = Util.reverseBinary(number);

		assertEquals(0, result);
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
		new org.junit.runner.JUnitCore().run(UtilTest.class);
	}
}