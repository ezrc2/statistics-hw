package stats;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class StatsTool {

	private ArrayList<Double> list;
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
	
	public StatsTool(ArrayList<Double> l) {
		list = l;
		calculate();
	}
	
	private void calculate() {
		Collections.sort(list);
		
		if (list.size() % 2 == 0) {
			double n1 = list.get(list.size() / 2);
			double n2 = list.get(list.size() / 2 - 1);
			median = (n1 + n2) / 2;
		}
		else median = list.get(list.size() / 2);
		
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
	}
	
	public String print() {
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
		
		return "<html><&nbsp><&nbsp>count:  " + list.size()
			 + "<br/><br/><&nbsp><&nbsp>mean:  " + mean 
			 + "<br/><br/><&nbsp><&nbsp>stdDev:  " + stdDev
			 + "<br/><br/><&nbsp><&nbsp>median:  " + median 
			 + "<br/><br/><&nbsp><&nbsp>mode(s):  " + m
			 + "<br/><br/><&nbsp><&nbsp>max:  " + maxNum
			 + "<br/><br/><&nbsp><&nbsp>min:  " + minNum
			 + "<br/><br/><&nbsp><&nbsp>Q1:  " + lowerQ
			 + "<br/><br/><&nbsp><&nbsp>Q3:  " + upperQ
			 + "<br/><br/><&nbsp><&nbsp>IQR:  " + IQR
			 + "<br/><br/><&nbsp><&nbsp>lower fence:  " + lowerFence
			 + "<br/><br/><&nbsp><&nbsp>upper fence:  " + upperFence
			 + "<br/><br/><&nbsp><&nbsp>outliers:  " + o + "</html>";
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\ezche\\OneDrive\\Desktop\\statsinput.txt"));
		
		ArrayList<Double> list = new ArrayList<Double>();
		String line = in.readLine();
		while (line != null) {
			list.add(Double.parseDouble(line.trim()));
			line = in.readLine();
		}
		
		JFrame f = new JFrame("Stats Tool");
		f.setBounds(200, 50, 600, 600);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel j = new JLabel();
		j.setFont(new Font("roboto", Font.BOLD, 14));
		j.setVisible(true);
		f.add(j);
		
		
		StatsTool st = new StatsTool(list);
		in.close();
		
		j.setText(st.print());
		
		String s = "";
		for (Double d : list) {
			s += Math.round((d) * 100.0) / 100.0 + ", ";
		}
		System.out.println(s);
	}

}
