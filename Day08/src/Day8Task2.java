import java.io.*;
import java.util.*;

public class Day8Task2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		s.useDelimiter("\\W+");
		char[] instructions = s.nextLine().toCharArray();
		List<Node> list = new ArrayList<Node>();
		List<Node> current = new ArrayList<Node>();
		List<Node> next = new ArrayList<Node>();
		while (s.hasNext()) {
			String a = s.next();
			String b = s.next();
			String c = s.next();
			if (a.charAt(2) == 'A') {
				current.add(new Node(a,b,c));
			}
			list.add(new Node(a,b,c));
		}
		int[] indSteps = new int[current.size()];
		for (int i = 0; i<current.size(); i++) {
			Node a = current.get(i);
			int steps = 0;
			while (a.name.charAt(2) != 'Z') {
				if (instructions[steps%instructions.length] == 'L') {
					steps++;
					a = list.get(list.indexOf(new Node(a.left,null,null)));
					continue;
				}
				if (instructions[steps%instructions.length] == 'R') {
					steps++;
					a = list.get(list.indexOf(new Node(a.right,null,null)));
					continue;
				}
			}
			indSteps[i] = steps; // Use this to find LCM or use internet to find LCM of numbers.
			System.out.println(steps);
		}
	}
	
	private static class Node {
		String name;
		String left;
		String right;
		
		public Node(String one, String two, String three) {
			name = one;
			left = two;
			right = three;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if ((o instanceof String)) {
				if (this.name.equals((String) o)) {
					return true;
				}
				return false;
			}
			if (!(o instanceof Node)) {
				return false;
			}
			if (this.name.equals(((Node) o).name)) {
				return true;
			}
			return false;
		}
	}

}

