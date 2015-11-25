package foo;

/**
 * @author Sassa
 * @version $Revision: 1.0 $
 */
public class Util
{

	/**
	 * Returns a string representation of the integer argument as an unsigned integer in base 2.
	 * 
	 * @param number integer between 0 and 31
	
	 * @return 5 char long string - binary representation with leading zeros */
	public static String bin5(int number)
	{
		if (number < 0 || number > 31)
		{
			throw new IllegalArgumentException("Number must be between 0 and 31");
		}
		String s = "00000" + Integer.toBinaryString(number);
		return s.substring(Math.max(0, s.length() - 5));
	}

	/**
	 * Returns integer which is given when binary representation of original number is reversed
	 * @param number integer between 0 and 31
	
	 * @return int */
	public static int reverseBinary(int number)
	{
		if (number < 0 || number > 31)
		{
			throw new IllegalArgumentException("Number must be between 0 and 31");
		}
		String str = bin5(number);
		int result=0;
		int pot2 = 1;
		
		//calculate from left to right results in flipped value 
		for (int i = 0; i < str.length(); i++)
		{
			if (str.charAt(i) == '1')
			{
				result += pot2;
			}
			pot2 *= 2;
		}
		return result;
	}
	
	/**
	 * Returns 1 if first (left) vertex of the Edge exists (is full), 0 otherwise
	 * @param value integer value of the Edge
	
	 * @return int */
	public static int getVertex1(int value)
	{
		return ( value >= 16) ? 1 : 0;
	}

	/**
	 * Returns 1 if second (right) vertex of the Edge exists (is full), 0 otherwise
	 * @param value integer value of the Edge
	
	 * @return int */
	public static int getVertex2(int value)
	{
		return ( value % 2 );
	}
}
