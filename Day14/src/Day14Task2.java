import java.util.*;
import java.io.*;

public class Day14Task2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		int datalength = 100;
		int highest = 0;
		ArrayList<Integer> sums = new ArrayList<Integer>();
		int sum = 0;
		Queue<int[]> rocksN = new PriorityQueue<>(Comparator.comparing((int[] e) -> e[0])
				.thenComparing((e) -> e[1])
				);
		Queue<int[]> rocksW = new PriorityQueue<>(Comparator.comparing((int[] e) -> e[1])
				.thenComparing((e) -> e[0])
				);
		Queue<int[]> rocksS = new PriorityQueue<>(Comparator.comparing((int[] e) -> -e[0])
				.thenComparing((e) -> e[1])
				);
		Queue<int[]> rocksE = new PriorityQueue<>(Comparator.comparing((int[] e) -> -e[1])
				.thenComparing((e) -> e[0])
				);
		Set<int[]> tempSet = new HashSet<>();
		Set<int[]> starting = new HashSet<>();
		char[][] data = new char[datalength][datalength];
		for (int i = 0; i<datalength; i++) {
			data[i] = s.nextLine().toCharArray();
			for (int j = 0; j<datalength; j++) {
				if (data[i][j] == 'O') {
					int[] temp = {i,j};
					rocksS.add(temp);
					rocksE.add(temp);
					rocksN.add(temp);
					rocksW.add(temp);
				}
			}
		}
		for (long k = 0; k<1000; k++) {
			if (k == 50) {
				for (int i = 0; i<datalength; i++) {
					for (int j = 0; j<datalength; j++) {
						if (data[i][j] == 'O') {
							int[] temp2 = {i,j};
							starting.add(temp2);
						}
					}
				}
			}
			for (int[] rock : rocksN) { // North
				while (rock[0]-1 >= 0 && data[rock[0]-1][rock[1]] == '.') {
						data[rock[0]][rock[1]] = '.';
						data[rock[0]-1][rock[1]] = 'O';
						rock[0] -= 1;
				}
				tempSet.add(rock);
			}
			rocksS.clear();
			rocksS.addAll(tempSet);
			tempSet.clear();
			for (int[] rock : rocksW) { // West
				while (rock[1]-1 >= 0 && data[rock[0]][rock[1]-1] == '.') {
						data[rock[0]][rock[1]] = '.';
						data[rock[0]][rock[1]-1] = 'O';
						rock[1] -= 1;
				}
				tempSet.add(rock);
			}
			rocksE.clear();
			rocksE.addAll(tempSet);
			tempSet.clear();
			for (int[] rock : rocksS) { // South
				while (rock[0]+1 < datalength && data[rock[0]+1][rock[1]] == '.') {
						data[rock[0]][rock[1]] = '.';
						data[rock[0]+1][rock[1]] = 'O';
						rock[0] += 1;
				}
				tempSet.add(rock);
			}
			rocksN.clear();
			rocksN.addAll(tempSet);
			tempSet.clear();
			for (int[] rock : rocksE) { // East
				while (rock[1]+1 < datalength && data[rock[0]][rock[1]+1] == '.') {
						data[rock[0]][rock[1]] = '.';
						data[rock[0]][rock[1]+1] = 'O';
						rock[1] += 1;
				}
				tempSet.add(rock);
			}
			rocksW.clear();
			rocksW.addAll(tempSet);
			tempSet.clear();
			boolean same = true;
			for (int[] rock : rocksN) {
				if (starting.isEmpty()) {
					same = false;
				}
				for (int[] rock2 : starting) {
					if (Arrays.equals(rock, rock2)) {
						same = true;
						break;
					}
					same = false;
				}
				if (!same) {
					break;
				}
			}
			if (same) {
				System.out.println(k);
			}
		}
		sum = 0;
		for (int i = 0; i<datalength; i++) {
			for (int j = 0; j<datalength; j++) {
				if (data[i][j] == 'O') {
					sum += datalength-i;
				}
			}
		}
		System.out.println(sum);
	}
}

