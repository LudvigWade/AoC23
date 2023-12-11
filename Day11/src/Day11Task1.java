import java.util.*;
import java.io.*;

public class Day11Task1 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		char[] line = s.nextLine().toCharArray();
		char[][] ogdata = new char[140][line.length];
		boolean[] rows = new boolean[140];
		boolean[] columns = new boolean[line.length];
		ArrayList<ArrayList<Character>> data = new ArrayList<ArrayList<Character>>();
		ArrayList<int[]> galaxies = new ArrayList<>();
		for (int i = 0; i<150; i++) {
			data.add(new ArrayList<Character>());
		}
		ogdata[0] = line;
		for (int i = 1; i<140; i++) {
			ogdata[i] = s.nextLine().toCharArray();
		}
		for (int i = 0; i<140; i++) {
			boolean galaxyRow = false;
			boolean galaxyColum = false;
			for (int j = 0; j<140; j++) {
				if (ogdata[i][j] == '#') {
					galaxyRow = true;
				}
				if (ogdata[j][i] == '#') {
					galaxyColum = true;
				}
			}
			if (!galaxyRow) {
				rows[i] = true;
			}
			if (!galaxyColum) {
				columns[i] = true;
			}
		}
		int k = 0;
		for (int i = 0; i<140; i++) {
			if (k == data.size()) {
				data.add(new ArrayList<Character>());
			}
			for (int j = 0; j<140; j++) {
				data.get(k).add(ogdata[i][j]);
				if (columns[j]) {
					data.get(k).add(ogdata[i][j]);
				}
			}
			if (rows[i]) {
				k++;
				if (k == data.size()) {
					data.add(new ArrayList<Character>());
				}
				for (int j = 0; j<140; j++) {
					data.get(k).add(ogdata[i][j]);
					if (columns[j]) {
						data.get(k).add(ogdata[i][j]);
					}
				}
			}
			k++;
		}
		for (int i = 0; i<data.size(); i++) {
			for (int j = 0; j<data.get(i).size(); j++) {
				if (data.get(i).get(j) == '#') {
					int[] temp = {i,j};
					galaxies.add(temp);
				}
			}
		}
		int sum = 0;
		for (int i = 0; i<galaxies.size(); i++) {
			int[] temp = galaxies.get(i);
			for (int j = i; j<galaxies.size(); j++) {
				int[] temp2 = galaxies.get(j);
				sum += Math.abs(temp[0]-temp2[0]) + Math.abs(temp[1]-temp2[1]);
			}
		}
		System.out.println(sum);

	}

}
