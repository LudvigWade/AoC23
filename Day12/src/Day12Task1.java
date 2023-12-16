import java.util.*;
import java.io.*;


public class Day12Task1 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		int sum = 0;
		s.useDelimiter("(\\s|,)");
		int datalength = 1000;		
		for (int i = 0; i<datalength; i++) {
			char[] line = s.next().toCharArray();
			ArrayList<Integer> groups = new ArrayList<Integer>();
			while (s.hasNextInt()) {
				groups.add(s.nextInt());
			}
			boolean done = false;
			boolean[] questarr = new boolean[line.length];
			ArrayList<Integer> questnbr = new ArrayList<>();
			for (int j = 0; j<line.length; j++) {
				if (line[j] == '?') {
					questarr[j] = true;;
					questnbr.add(j);
				}
			}
			int steps = 0;
			while (steps < Math.pow(2, questnbr.size())) {
				char[] temp = line;
				String temp33 = Integer.toBinaryString(steps);
				steps++;
				for (int j = temp33.length(); j<questnbr.size(); j++) {
					temp33 = "0" + temp33;
				}
				char[] instructs = temp33.toCharArray();
				for (int j = 0; j<questnbr.size(); j++) {
					int k = questnbr.get(j);
					if (instructs[j] == '0') {
						temp[k] = '#';
					} else {
						temp[k] = '.';
					}
				}
				boolean solution = true;
				int together = 0;
				int group = 0;
				for (int j = 0; j<temp.length; j++) {
					if (temp[j] == '#') {
						together++;
					} else {
						if (together != 0) {
							if (group >= groups.size()) {
								solution = false;
								break;
							}
							if (together != groups.get(group)) {
								solution = false;
								break;
							}
							group++;
							together = 0;
						}
					}
				}
				if ((together == 0 && group == groups.size()) || (group == groups.size()-1 && together == groups.get(group))) {
					if (solution) {
						sum++;
					}
				}
			}
			if (s.hasNext()) {
				s.next();
			}
		}
		System.out.println(sum);
	}

}
