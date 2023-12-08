import java.io.*;
import java.util.*;

public class Day8Task2_2 implements Threads {

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
		int steps = 0;
		boolean finished = false;
		while (!finished) {
			if (steps%10000 == 0) {
				System.out.println(steps);
			}
			if (instructions[steps%instructions.length] == 'L') {
				for (Node n : current) {
					next.add(list.get(list.indexOf(new Node(n.left,null,null))));
				}
			} else if (instructions[steps%instructions.length] == 'R') {
				for (Node n : current) {
					next.add(list.get(list.indexOf(new Node(n.right,null,null))));
				}
			}
			steps++;
			finished = true;
			for (Node n : next) {
				if (n.name.charAt(2) != 'Z') {
					finished = false;
				}
			}
			current.clear();
			current.addAll(next);
			next.clear();
		}
		System.out.println(steps);
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

