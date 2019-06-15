package tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class StatsTool {

	private ArrayList<Double> list;
	private Scanner scan;
	private double mean;
	private double median;
	private double mode;
	private double maxNum;
	private double minNum;
	private double lowerQ;
	private double upperQ;
	private double lowerFence;
	private double upperFence;
	private ArrayList<Double> outliers;
	
	public StatsTool() {
		list = new ArrayList<Double>();
		scan = new Scanner(System.in);
		System.out.println("Input number and press 'enter' to input next number. 'q' to stop");
		String input = scan.nextLine();
		while (!input.equals("q")) {
			list.add(Double.parseDouble(input));
			input = scan.nextLine();
		}
		calculate();
	}
	
	private void calculate() {
		Collections.sort(list);
		
		if (list.size() % 2 == 0) {
			double n1 = list.get(list.size() / 2);
			double n2 = list.get(list.size() / 2 - 1);
			median = (n1 + n2) / 2;
		}
		else median = list.get(list.size() / 2 - 1);
		
		int sum = 0;
		for (Double d : list) sum += d;
		mean = sum / list.size();
		
		HashMap<Double, Integer> map = new HashMap<Double, Integer>();
		for (Double d : list) {
			if (!map.containsKey(d)) map.put(d, 0);
			else map.put(d, map.get(d) + 1);
		}
		Double max = 0.0;
		int count = 0;
		for (Double d : map.keySet()) {
			if (map.get(d) > count) {
				count = map.get(d);
				max = d;
			}
		}
		mode = max;
		
		maxNum = list.get(list.size() - 1);
		minNum = list.get(0);
		lowerQ = list.get(list.size() / 4);
		upperQ = list.get(list.size() * 3 / 4);
	
		double IQR = upperQ - lowerQ;
		lowerFence = lowerQ - 1.5 * IQR;
		upperFence = upperQ + 1.5 * IQR;
		
		outliers = new ArrayList<Double>();
		for (int i = 0; i < list.size() / 2; i++) {
			if (list.get(i) < lowerFence)
				outliers.add(list.get(i));
			else
				break;
		}
		for (int i = list.size() - 1; i > list.size() / 2; i--) {
			if (list.get(i) > upperFence)
				outliers.add(list.get(i));
			else
				break;
		}
		
		
		print();
	}
	
	private void print() {
		String s = "";
		if (outliers.size() > 0) {
			for (Double d : outliers)
				s += d + ", ";
			s = s.substring(0, s.length() - 2);
		}
		else s = "none";
		
		System.out.println("count:\t" + list.size()
						 + "\nmean:\t" + mean 
				         + "\nmedian:\t" + median 
				         + "\nmode:\t" + mode
				         + "\nmax:\t" + maxNum
				         + "\nmin:\t" + minNum
				         + "\nlower quartile: " + lowerQ
				         + "\nupper quartile: " + upperQ
				         + "\nlower fence: " + lowerFence
				         + "\nupper fence: " + upperFence
				         + "\noutliers: " + s);
	}
	
	
	public static void main(String[] args) {
		new StatsTool();
	}

}
