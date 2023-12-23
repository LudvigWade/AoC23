import java.util.*;
import java.io.*;

public class Day21Task1 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		Map<Integer,ArrayList<int[]>> steps = new HashMap<>();
		String line = s.next();
		int rowlength = 131;
		int nbrofsteps = 64;
		char[][] data = new char[rowlength][line.length()];
		boolean[][] visited = new boolean[rowlength][line.length()];
		data[0] = line.toCharArray();
		int[] start = new int[2];
		for (int i = 1; i<rowlength; i++) {
			line = s.next();
			if (line.indexOf('S') != -1) {
				start[0] = i;
				start[1] = line.indexOf('S');
			}
			data[i] = line.toCharArray();
		}
		steps.put(0, new ArrayList<int[]>()); // Input read
		steps.get(0).add(start);
		visited[start[0]][start[1]] = true;
		for (int i = 0; i<nbrofsteps; i++) {
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
				if (xy[1]+1 < data[i].length && data[xy[0]][xy[1]+1] != '#' && !visited[xy[0]][xy[1]+1]) {
					int[] temp = new int[2];
					temp[0] = xy[0];
					temp[1] = xy[1]+1;
					steps.get(i+1).add(temp);
					visited[temp[0]][temp[1]] = true;
					
				}
			}
		}
		int sum = 0;
		for (int i = 0; i<nbrofsteps/2+1;i++) {
			sum += steps.get(2*i).size();
		}
		System.out.println(sum);
	}

}
