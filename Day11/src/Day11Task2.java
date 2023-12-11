import java.util.*;
import java.io.*;

public class Day11Task2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		char[] line = s.nextLine().toCharArray();
		char[][] ogdata = new char[140][line.length];
		boolean[] rows = new boolean[140];
		boolean[] columns = new boolean[line.length];
		ArrayList<int[]> galaxies = new ArrayList<>();
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
		for (int i = 0; i<ogdata.length; i++) {
			for (int j = 0; j<ogdata[i].length; j++) {
				if (ogdata[i][j] == '#') {
					int[] temp = {i,j};
					galaxies.add(temp);
				}
			}
		}
		Long sum = (long) 0;
		for (int i = 0; i<galaxies.size(); i++) {
			int[] temp = galaxies.get(i);
			for (int j = i+1; j<galaxies.size(); j++) {
				int[] temp2 = galaxies.get(j);
				int tempsum = 0;
				if (temp[0] <= temp2[0]) {
					for (int k = temp[0]; k < temp2[0]; k++) {
						if (rows[k]) {
							tempsum += 1000000;
						} else {
							tempsum += 1;
						}
					}
				} else {
					for (int k = temp2[0]; k < temp[0]; k++) {
						if (rows[k]) {
							tempsum += 1000000;
						} else {
							tempsum += 1;
						}
					}
				}
				if (temp[1] <= temp2[1]) {
					for (int k = temp[1]; k < temp2[1]; k++) {
						if (columns[k]) {
							tempsum += 1000000;
						} else {
							tempsum += 1;
						}
					}
				} else {
					for (int k = temp2[1]; k < temp[1]; k++) {
						if (columns[k]) {
							tempsum += 1000000;
						} else {
							tempsum += 1;
						}
					}
				}
				sum += tempsum;
			}
		}
		System.out.println(sum);

	}

}

