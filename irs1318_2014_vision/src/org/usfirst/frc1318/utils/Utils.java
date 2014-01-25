package org.usfirst.frc1318.utils;

import static java.lang.Math.*;

public class Utils {
	public static boolean compareDoubles(double a, double b, double eps) {
		return Math.abs(a - b) < eps;
	}

	public static double mod(double dividend, double divisor, int precision) {
		long scaledDividend = (long) (dividend * pow(10, precision));
		long scaledDivisor = (long) (divisor * pow(10, precision));
		return (scaledDividend % scaledDivisor) / pow(10, precision);
	}

	/**
	 * @deprecated Use com.sun.squawk.util.Arrays class
	 * @param arr
	 * @param arr2
	 * @return
	 */
	public static boolean equals(Object[] arr, Object[] arr2) {
		if(arr == arr2)
			return true;
		if(onlyOneNull(arr, arr2))
			return false;
		if(arr.length != arr2.length)
			return false;
		for(int i = 0; i < arr.length; i++) {
			if(onlyOneNull(arr[i], arr2[i]))
				return false;
			else if(!arr[i].equals(arr2[i]))
					return false;
		}
		return true;
	}
	
	private static boolean onlyOneNull(Object one, Object two) {
		return one == null ^ two == null;
	}

}
