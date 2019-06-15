package tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class StatsTool {

	private ArrayList<Double> list;
	private Scanner scan;
	private double mean;
	private double stdDev;
	private double median;
	private ArrayList<Double> modes;
	private double maxNum;
	private double minNum;
	private double IQR;
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
		
		double sum = 0;
		for (Double d : list)
			sum += d;
		mean = sum / list.size();
		
		sum = 0;
		for (Double d : list)
			sum += Math.pow(d - mean, 2);
		stdDev = Math.sqrt(sum / (list.size() - 1));
		
		HashMap<Double, Integer> map = new HashMap<Double, Integer>();
		for (Double d : list) {
			if (!map.containsKey(d)) map.put(d, 1);
			else map.put(d, map.get(d) + 1);
		}
		
		int count = 0;
		for (Double d : map.keySet()) {
			if (map.get(d) > count) {
				count = map.get(d);
			}
		}
		modes = new ArrayList<Double>();
		for (Double d : map.keySet()) {
			if (map.get(d) == count)
				modes.add(d);
		}
		
		maxNum = list.get(list.size() - 1);
		minNum = list.get(0);
		lowerQ = list.get(list.size() / 4);
		upperQ = list.get(list.size() * 3 / 4);
	
		IQR = upperQ - lowerQ;
		lowerFence = lowerQ - 1.5 * IQR;
		upperFence = upperQ + 1.5 * IQR;
		
		outliers = new ArrayList<Double>();
		int i = 0;
		while (list.get(i) < lowerFence)
			outliers.add(list.get(i++));
		i = list.size() - 1;
		while (list.get(i) > upperFence)
			outliers.add(list.get(i--));
		
		print();
	}
	
	private void print() {
		String m = "";
		if (modes.size() > 1) {
			for (Double d : modes)
				m += d + ", ";
			m = m.substring(0, m.length() - 2);
		}
		else
			m = String.valueOf(modes.get(0));
		String o = "";
		if (outliers.size() > 0) {
			for (Double d : outliers)
				o += d + ", ";
			o = o.substring(0, o.length() - 2);
		}
		else o = "none";
		
		System.out.println("count:\t\t" + list.size()
						 + "\nmean:\t\t" + mean 
						 + "\nstdDev:\t\t" + stdDev
				         + "\nmedian:\t\t" + median 
				         + "\nmode(s):\t" + m
				         + "\nmax:\t\t" + maxNum
				         + "\nmin:\t\t" + minNum
				         + "\nQ1:\t\t" + lowerQ
				         + "\nQ3:\t\t" + upperQ
				         + "\nIQR:\t\t" + IQR
				         + "\nlower fence:\t" + lowerFence
				         + "\nupper fence:\t" + upperFence
				         + "\noutliers:\t" + o);
	}
	
	
	public static void main(String[] args) {
		new StatsTool();
	}

}
