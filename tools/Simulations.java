package stats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Simulations {

	public void drawCards(ArrayList<Double> list) {
		double total = 0;
		double sum = 0;
		int count = 0;
		for (Double d : list) {
			if (sum > 400) {
				total += count;
				count = 0;
				sum = 0;
			} else {
				if (d <= 5)
					sum += 20;
				else if (d <= 7)
					sum += 50;
				else if (d == 8)
					sum += 100;
				else
					sum += 200;
				count++;
			}
		}
		System.out.println(total / 10);
	}

	public void diceRoll(ArrayList<Double> list) {
		double total = 0;
		double sum = 0;
		int count = 0;
		for (Double b : list) {
			if ( b > 0 && b < 7) {
				if (sum == 10) {
					total += count;
					sum = 0;
					count = 0;
				}
				if (sum + b > 10) {
					count++;
				} else if (sum + b <= 10) {
					sum += b;
					count++;
				}
			}
		}
		System.out.println(total / 10);
	}

	public void randomPairs(int trials) {
		int count = 0;
		HashMap<Integer, Integer> combinations = new HashMap<Integer, Integer>();
		for (int i = 1; i < 8; i += 2) {
			combinations.put(i, i + 1);
			combinations.put(i + 1, i);
		}
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
		Random r = new Random();
		int i = 0; 
		while (i++ < trials) {
			for (int j = arr.length - 1; j >= 0; j--) {
				int index = r.nextInt(j + 1);
				int temp = arr[j];
				arr[j] = arr[index];
				arr[index] = temp;
			}
			HashMap<Integer, Integer> check = new HashMap<Integer, Integer>();
			for (int j = 0; j < 7; j += 2) {
				check.put(arr[j], arr[j + 1]);
			}
			boolean b = true;
			for (int k : check.keySet()) {
				for (int x : combinations.keySet()) {
					if (k == x && check.get(k) == combinations.get(x)) {
						b = false;
					}
				}
			}
			String s = "";
			for (int j = 0; j < 8; j++) {
				s += arr[j];
				if (j % 2 == 1)
					s += "  ";
			}
			if (b) {
				s += " - ok";
				count++;
			}
			System.out.println(s);
		}
		System.out.println(count + " / " + trials);
	}
	
	public void findTheAce(int trials) {
		double sum = 0;
		for (int i = 0; i < trials; i++) {
			int n = (int)(Math.random() * 5 + 1);
			switch (n) {
			case 1:
				sum += 100;
				break;
			case 2:
				sum += 50;
				break;
			case 3:
				sum += 20;
				break;
			case 4:
				sum += 10;
				break;
			case 5:
				sum += 5;
				break;
			}
			System.out.print(n + " ");
		}
		System.out.println("\n" + sum);
		System.out.println(sum / trials);
	}
	
	public void esp() {
		int[] original = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		Random r = new Random();
		int i = 0;
		while (i++ < 20) {
			for (int j = arr.length - 1; j >= 0; j--) {
				int index = r.nextInt(j + 1);
				int temp = arr[j];
				arr[j] = arr[index];
				arr[index] = temp;
			}
			int count = 0;
			String s = "";
			for (int k = 0; k < original.length; k++) {
				if (original[k] == arr[k])
					count++;
				s += arr[k] + "  ";
			}
			System.out.println("trial " + i + ":   " + s + "   matches: " + count);
		}
	}
	
	public void bloodSugarLevels(int trials) {
		int[] arr = {185, 163, 125, 151, 101, 190, 175, 178, 135, 201};
		for (int i = 0; i < trials; i++) {
			for (int j = arr.length - 1; j >= 0; j--) {
				int index = (int)(Math.random() * j);
				int temp = arr[j];
				arr[j] = arr[index];
				arr[index] = temp;
			}
			double sum1 = 0, sum2 = 0;
			for (int j = 0; j < arr.length / 2; j++) {
				sum1 += arr[j];
				sum2 += arr[arr.length - j - 1];
			}
			System.out.print(Math.round(Math.abs((sum1 - sum2) / 5.0) * 100.0) / 100.0  + ", ");
		}
	}
	
	private int[] shuffle(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < arr.length; i++)
			arr[i] = i;
		for (int i = arr.length - 1; i >= 0; i--) {
			int index = (int)(Math.random() * i);
			int temp = arr[i];
			arr[i] = arr[index];
			arr[index] = temp;
		}
		return arr;
	}
	
	public void ironDeficiency() {
		int[] group1 = shuffle(280);
		int[] group2 = shuffle(200);
		String s1 = "", s2 = "";
		for (int i = 0; i < group1.length; i++) {
			if (i < group1.length / 2)
				s1 += group1[i] + "  ";
			else
				s2 += group1[i] + "  ";
		}
		System.out.println("1A:  " + s1 + "\n1B:  " + s2 + "\n");
		s1 = ""; 
		s2 = "";
		for (int i = 0; i < group2.length; i++) {
			if (i < group2.length / 2)
				s1 += group2[i] + "  ";
			else
				s2 += group2[i] + "  ";
		}
		System.out.println("2A:  " + s1 + "\n2B:  " + s2);	
	}
	
	public void diceRoll2() {
		for (int i = 0; i < 30; i++) {
			int count = 1;
			int n = (int)(Math.random() * 6 + 1);
			while (n != 1) {
				n = (int)(Math.random() * 6 + 1);
				count++;
			}
			System.out.print(count + "  ");
		}
	}
	
	public void seatBelts() {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < 30; i++) {
			int count = 0;
			int n = (int)(Math.random() * 4);
			for (int j = 0; j < 5; j++) {
				if (n == 0)
					count++;
				n = (int)(Math.random() * 4);
			}
			count = 5 - count;
			if (map.containsKey(count))
				map.put(count, map.get(count) + 1);
			else
				map.put(count, 1);
			System.out.print(count + "  ");
		}
		System.out.println();
		for (int k : map.keySet()) {
			System.out.println(k + ": " + map.get(k));
		}
	}
	
	public void simulatedCoins(int trials) {
		double sum = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < trials; j++) {
				sum += (int)(Math.random() * 2);
			}
			//System.out.println("trial: " + (i + 1) + " \tevens: " + even + "\todds: " + odd);
			System.out.print(sum / trials + ", ");
			sum = 0;
		}
	}
	
	public void whyNP10(int size) {
		final double p = 0.8;
		String s = "v <- c(";
		for (int i = 0; i < 100; i++) {
			double sum = 0;
			for (int j = 0; j < size; j++) {
				if ((int)(Math.random() * 10) < 8)
					sum++;
			}
			s += (sum / size) + ", ";
			sum = 0;
		}
		//copy past to r console
		s = s.substring(0, s.length() - 2) + ")\nhist(v, main = \"" + size + " people\", xlab = \"mean positive responses\", xlim = c(0.4, 1.2), col = \"blue\")";
		System.out.println(s + "\n");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\ezche\\OneDrive\\Desktop\\statsinput.txt"));

//		ArrayList<Double> list = new ArrayList<Double>();
//		String line = in.readLine();
//		while (line != null) {
//			list.add(Double.parseDouble(line.trim()));
//			line = in.readLine();
//		}

		Simulations s = new Simulations();
		//s.drawCards(list);
		//s.diceRoll(list);
		//s.randomPairs(1000);
		//s.findTheAce(100);
		//s.esp();
		//s.bloodSugarLevels(25);
		//s.ironDeficiency();
		//s.diceRoll2();
		//s.seatBelts();
		//s.simulatedCoins(25);
		//s.simulatedCoins(100);
		int[] arr = {5, 10, 20, 50, 100, 1000, 10000}; for (int x : arr) s.whyNP10(x);
		in.close();
	}

}
