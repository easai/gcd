/**
 * 
 */
package gcd;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author easai
 *
 */
public class GCDTest {
	int x = 128;
	int y = 72;

	@Test
	public void test() {
		int gcd = GCD.gcd(x, y);
		assertTrue(x % gcd == 0);
		assertTrue(y % gcd == 0);
	}

	int gcd_test(int x, int y) {
		int res = y - 1;
		while ((((x % res) != 0) || ((y % res) != 0)) && 0 < --res)
			;
		return res;
	}

	@Test
	public void testMax() {
		assertEquals(gcd_test(x, y), GCD.gcd(x, y));
	}

	@Test
	public void testGCD() {
		Integer[] listX = GCD.divisor(x);
		Integer[] listY = GCD.divisor(y);
		int divisor = 0;
		for (int i = listX.length - 1; 0 <= i; i--) {
			int j = listY.length - 1;
			while (listY[j] != listX[i] && listX[i] < listY[j] && 0 <= --j)
				;
			if (listY[j] == listX[i]) {
				divisor = listY[j];
				break;
			}
		}
		if (divisor != 0) {
			assertEquals(divisor, GCD.gcd(x, y));
		}
	}

}
