import java.util.*;
import java.io.*;

public class Day21Task2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		Map<Integer,ArrayList<int[]>> steps = new HashMap<>();
		String line = s.next();
		int rowlength = 131;
		int totalnbrsteps = 26501365;
		long nbrofareas = totalnbrsteps/131;
		long totalsum = 0;
		
		char[][] data = new char[rowlength][line.length()];
		data[0] = line.toCharArray();
		for (int i = 1; i<rowlength; i++) {
			line = s.next();
			data[i] = line.toCharArray();
		}
		int[][] starts = {{65,65,131},{65,65,130},{0,0,195},{0,130,195},{130,130,195},{130,0,195},{0,0,64},{0,130,64},{130,130,64},{130,0,64},{65,0,130},{65,130,130},{0,65,130},{130,65,130}};
		for (int j = 0; j<14; j++) {
			int[] start = starts[j];
			boolean[][] visited = new boolean[rowlength][line.length()];
			steps.put(0, new ArrayList<int[]>()); // Input read
			steps.get(0).add(start);
			visited[start[0]][start[1]] = true;
			for (int i = 0; i<start[2]; i++) {
				steps.put(i+1, new ArrayList<int[]>());
				for (int[] xy : steps.get(i)) {
					if (xy[0]-1 >= 0 && data[xy[0]-1][xy[1]] != '#' && !visited[xy[0]-1][xy[1]]) {
						int[] temp = new int[2];
						temp[0] = xy[0]-1;
						temp[1] = xy[1];
						steps.get(i+1).add(temp);
						visited[temp[0]][temp[1]] = true;
					}
					if (xy[0]+1 < data.length && data[xy[0]+1][xy[1]] != '#' && !visited[xy[0]+1][xy[1]]) {
						int[] temp = new int[2];
						temp[0] = xy[0]+1;
						temp[1] = xy[1];
						steps.get(i+1).add(temp);
						visited[temp[0]][temp[1]] = true;
						
					}
					if (xy[1]-1 >= 0 && data[xy[0]][xy[1]-1] != '#' && !visited[xy[0]][xy[1]-1]) {
						int[] temp = new int[2];
						temp[0] = xy[0];
						temp[1] = xy[1]-1;
						steps.get(i+1).add(temp);
						visited[temp[0]][temp[1]] = true;
						
					}
					if (xy[1]+1 < data[xy[0]].length && data[xy[0]][xy[1]+1] != '#' && !visited[xy[0]][xy[1]+1]) {
						int[] temp = new int[2];
						temp[0] = xy[0];
						temp[1] = xy[1]+1;
						steps.get(i+1).add(temp);
						visited[temp[0]][temp[1]] = true;
						
					}
				}
			}
			long sum = 0;
			if (start[2]%2 == 1) {
				for (int i = 0; i<(start[2]+1)/2;i++) {
					sum += steps.get(2*i+1).size();
				}
			} else {
				for (int i = 0; i<start[2]/2+1;i++) {
					sum += steps.get(2*i).size();
				}
			}
			if (j == 0) {
				sum *= (nbrofareas-1)*(nbrofareas-1);
			} else if (j == 1) {
				sum *= (nbrofareas*nbrofareas);
			} else if (j > 1 && j < 6) {
				sum *= nbrofareas-1;
			} else if (j > 5 && j < 10) {
				sum *= nbrofareas;
			}
			totalsum += sum;
		}
		System.out.println(totalsum);
	}

}

