import java.util.*;
import java.io.*;

public class Day18Task1 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		Set<Duple> edges = new HashSet<>();
		Duple current = new Duple(0,0);
		edges.add(current);
		while (s.hasNext()) {
			char d = s.next().charAt(0);
			int steps = s.nextInt();
			s.next();
			switch (d) {
			case 'R':
				for (int i = 0; i<steps; i++) {
					current = new Duple(current.x,current.y+1);
					edges.add(current);
				}
				break;
			case 'L':
				for (int i = 0; i<steps; i++) {
					current = new Duple(current.x,current.y-1);
					edges.add(current);
				}
				break;
			case 'U':
				for (int i = 0; i<steps; i++) {
					current = new Duple(current.x-1,current.y);
					edges.add(current);
				}
				break;
			case 'D': 
				for (int i = 0; i<steps; i++) {
					current = new Duple(current.x+1,current.y);
					edges.add(current);
				}
				break;
			}
		}
//		boolean[][] vis = new boolean[10][7]; // Can visualize if data never negative
//		for (Duple d : edges) {
//			vis[d.x][d.y] = true;
//		}
//		for (int i = 0; i<vis.length; i++) {
//			for (int j = 0; j<vis[i].length-1; j++) {
//				if (vis[i][j]) {
//					System.out.print("#");
//				} else {
//					System.out.print(".");
//				}
//			}
//			if (vis[i][vis[i].length-1]) {
//				System.out.println("#");
//			} else {
//				System.out.println(".");
//			}
//		}
		ArrayList<Duple> nodes = new ArrayList<>();
		Set<Duple> checked = new HashSet<>();
		nodes.add(new Duple(1,1));
		checked.add(new Duple(1,1));
		int size = 0;
		while (!nodes.isEmpty()) {
			Duple temp = nodes.remove(0);
			size++;
			Duple temp1;
			for (int i = -1; i<2; i++) {
				for (int j = -1; j<2; j++) {
					if (i == 0 && j == 0) {
						continue;
					}
					temp1 = new Duple(temp.x+i, temp.y+j);
					if (!checked.contains(temp1)) {
						if (edges.contains(temp1)) {
							checked.add(temp1);
							size++;
						} else {
							nodes.add(temp1);
							checked.add(temp1);
						}
					}
				}
			}
		}
		System.out.println(size);
	}
	
	private static class Duple {
		int x;
		int y;
		
		public Duple(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			
			if (!(o instanceof Duple)) {
				return false;
			}
			
			if (this.x == ((Duple) o).x && this.y == ((Duple) o).y) {
				return true;
			}
			
			return false;
		}
		
		@Override
		public int hashCode() {
			return 110*x+y;
		}
		
	}
}
