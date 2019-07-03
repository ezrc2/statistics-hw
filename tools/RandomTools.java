package stats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomTools {

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
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\ezche\\OneDrive\\Desktop\\statsinput.txt"));

		ArrayList<Double> list = new ArrayList<Double>();
		String line = in.readLine();
		while (line != null) {
			list.add(Double.parseDouble(line.trim()));
			line = in.readLine();
		}

		RandomTools rt = new RandomTools();
		// rt.drawCards(list);
		//rt.diceRoll(list);
		rt.randomPairs(1000);

		in.close();
	}

}
