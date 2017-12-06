/**
 * 
 */
package gcd;

import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

/**
 * @author easai
 *
 */
public class GCD {

	public static Integer[] divisor(int x) {
		int n = (int) Math.floor(Math.sqrt(x)) + 1;
		int[] res = new int[n];
		for (int i = 0; i < n; i++) {
			res[i] = 0;
		}
		for (int i = 0; i < n; i++) {
			if (res[i] == 0 && 1 < i) {
				if ((x % i) != 0) {
					for (int j = i; j < n; j += i) {
						res[j] = 1;
					}
				} else {
					res[i] = -1;
				}
			}
		}
		SortedSet<Integer> set = new TreeSet<Integer>();

		for (int i = 0; i < n; i++) {
			if (res[i] == -1) {
				set.add(i);
				if (i != x / i) {
					set.add(x / i);
				}
			}
		}
/*
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Object element = it.next();
			System.out.print(element.toString()+" ");
		}
		System.out.println();
*/				
		return set.toArray();
	}

	public static int gcd(int x, int y) {
		int res;
		int r = x % y;
		if (r == 0) {
			res = y;
		} else {
			res = gcd(y, r);
		}
		return res;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String OPTION_USAGE = "usage";
		final String OPTION_X = "x";
		final String OPTION_Y = "y";
		Options opt = new Options();

		opt.addOption("?", OPTION_USAGE, false, "print this message");
		opt.addOption("x", OPTION_X, true, "argument x");
		opt.addOption("y", OPTION_Y, true, "argument y");

		try {
			CommandLineParser parser = new DefaultParser();
			CommandLine cmd = parser.parse(opt, args);

			if (cmd.hasOption(OPTION_USAGE)) {
				throw new Exception();
			}
			if (cmd.hasOption(OPTION_X) && cmd.hasOption(OPTION_Y)) {
				String strX = cmd.getOptionValue(OPTION_X);
				String strY = cmd.getOptionValue(OPTION_Y);

				int x = Integer.parseInt(strX);
				int y = Integer.parseInt(strY);

				int gcd = (x > y) ? GCD.gcd(x, y) : GCD.gcd(y, x);

				System.out.println(String.format("gcd (%d, %d) = %d", x, y, gcd));

				GCD.divisor(100);

			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			HelpFormatter help = new HelpFormatter();
			help.printHelp("GCD", opt);

		}

	}

}
