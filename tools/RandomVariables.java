package stats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RandomVariables {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\ezche\\OneDrive\\Desktop\\statsinput.txt"));

//		4
//		0 12 24 39
//		1/2 1/4 3/13 1/52
		
//		6
//		0 1 2 3 4 5
//		0.05 0.24 0.36 0.13 0.14 0.08

		int length = Integer.parseInt(in.readLine());
		int[] x = new int[length];
		double[] P = new double[length];
		
		String[] s = in.readLine().split(" ");
		for (int i = 0; i < x.length; i++) {
			x[i] = Integer.parseInt(s[i]);
		}
		
		s = in.readLine().split(" ");
		for (int i = 0; i < P.length; i++) {
			P[i] = fractionToDouble(s[i]);
		}
		
		
		double mean = 0;
		for (int i = 0; i < x.length; i++) {
			mean += x[i] * P[i];
		}
		System.out.println("μ: " + mean);
		
		double variance = 0;
		for (int i = 0; i < x.length; i++) {
			variance += Math.pow(x[i] - mean, 2) * P[i];
		}
		double stdDev = Math.sqrt(variance);
		System.out.println("σ: " + stdDev);

		in.close();
	}

	public static double fractionToDouble(String str) {
		if (!str.contains("/"))
			return Double.parseDouble(str);
		String[] s = str.split("/");
		double d1 = Double.parseDouble(s[0]);
		double d2 = Double.parseDouble(s[1]);
		return d1 / d2;
	}

}
