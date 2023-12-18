import java.util.*;

import java.io.*;


public class Day17Task2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		Map<Node,Integer> map = new HashMap<>();
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator
				.comparing((Node e1) -> map.get(e1))
				.thenComparing((Node e1) -> e1.x)
				.thenComparing((Node e1) -> e1.y)
				.thenComparing((Node e1) -> e1.direction)
				.thenComparing((Node e1) -> e1.sameDir)
				);
		int nbrofrows = 141;
		int nbrofcols = 141;
		int[][] data = new int[nbrofrows][nbrofcols]; //other data
		int i = 0;
		String line;
		while (s.hasNext() && i<nbrofrows) {
			line = s.nextLine();
			for (int j = 0; j<line.length(); j++) {
				data[i][j] = line.charAt(j)-48;
			}
			i++;
		}
		Node n = new Node(0,0,0,0);
		map.put(n, 0);
		Node temp;
		while (!(n.x == nbrofrows-1 && n.y == nbrofcols-1 && n.sameDir >= 3)) { //direction: 1 = going up, 2 = going down, 3 = going right, 4 = going left
			if (n.x+1 < nbrofrows && !(n.sameDir == 9 && n.direction == 2) && (!(n.sameDir < 3 && n.direction != 2) || n.direction == 0) && n.direction != 1) {
				if (n.direction == 2) {
					temp = new Node(n.x+1,n.y,2,n.sameDir+1);
					if (map.containsKey(temp) && map.get(n)+data[n.x+1][n.y] < map.get(temp)) {
						map.put(temp, map.get(n)+data[n.x+1][n.y]);
						pq.add(temp);
					} else if (!map.containsKey(temp)) {
						map.put(temp, map.get(n)+data[n.x+1][n.y]);
						pq.add(temp);
					}
				} else {
					temp = new Node(n.x+1,n.y,2,0);
					if (map.containsKey(temp) && map.get(n)+data[n.x+1][n.y] < map.get(temp)) {
						map.put(temp, map.get(n)+data[n.x+1][n.y]);
						pq.add(temp);
					} else if (!map.containsKey(temp)) {
						map.put(temp, map.get(n)+data[n.x+1][n.y]);
						pq.add(temp);
					}
				}
				
			}
			if (n.x-1 >= 0 && !(n.sameDir == 9 && n.direction == 1) && (!(n.sameDir < 3 && n.direction != 1) || n.direction == 0) && n.direction != 2) {
				if (n.direction == 1) {
					temp = new Node(n.x-1,n.y,1,n.sameDir+1);
					if (map.containsKey(temp) && map.get(n)+data[n.x-1][n.y] < map.get(temp)) {
						map.put(temp, map.get(n)+data[n.x-1][n.y]);
						pq.add(temp);
					} else if (!map.containsKey(temp)) {
						map.put(temp, map.get(n)+data[n.x-1][n.y]);
						pq.add(temp);
					}
				} else {
					temp = new Node(n.x-1,n.y,1,0);
					if (map.containsKey(temp) && map.get(n)+data[n.x-1][n.y] < map.get(temp)) {
						map.put(temp, map.get(n)+data[n.x-1][n.y]);
						pq.add(temp);
					} else if (!map.containsKey(temp)) {
						map.put(temp, map.get(n)+data[n.x-1][n.y]);
						pq.add(temp);
					}
				}
			}
			if (n.y+1 < nbrofcols && !(n.sameDir == 9 && n.direction == 3) && (!(n.sameDir < 3 && n.direction != 3) || n.direction == 0) && n.direction != 4) {
				if (n.direction == 3) {
					temp = new Node(n.x,n.y+1,3,n.sameDir+1);
					if (map.containsKey(temp) && map.get(n)+data[n.x][n.y+1] < map.get(temp)) {
						map.put(temp, map.get(n)+data[n.x][n.y+1]);
						pq.add(temp);
					} else if (!map.containsKey(temp)) {
						map.put(temp, map.get(n)+data[n.x][n.y+1]);
						pq.add(temp);
					}
				} else {
					temp = new Node(n.x,n.y+1,3,0);
					if (map.containsKey(temp) && map.get(n)+data[n.x][n.y+1] < map.get(temp)) {
						map.put(temp, map.get(n)+data[n.x][n.y+1]);
						pq.add(temp);
					} else if (!map.containsKey(temp)) {
						map.put(temp, map.get(n)+data[n.x][n.y+1]);
						pq.add(temp);
					}
				}
			}
			if (n.y-1 >= 0 && !(n.sameDir == 9 && n.direction == 4) && !((n.sameDir < 3 && n.direction != 4) || n.direction == 0) && n.direction != 3) {
				if (n.direction == 4) {
					temp = new Node(n.x,n.y-1,4,n.sameDir+1);
					if (map.containsKey(temp) && map.get(n)+data[n.x][n.y-1] < map.get(temp)) {
						map.put(temp, map.get(n)+data[n.x][n.y-1]);
						pq.add(temp);
					} else if (!map.containsKey(temp)) {
						map.put(temp, map.get(n)+data[n.x][n.y-1]);
						pq.add(temp);
					}
				} else {
					temp = new Node(n.x,n.y-1,4,0);
					if (map.containsKey(temp) && map.get(n)+data[n.x][n.y-1] < map.get(temp)) {
						map.put(temp, map.get(n)+data[n.x][n.y-1]);
						pq.add(temp);
					} else if (!map.containsKey(temp)) {
						map.put(temp, map.get(n)+data[n.x][n.y-1]);
						pq.add(temp);
					}
				}
			}
			n = pq.poll();
		}
		System.out.println(map.get(n));
	}
	
	private static class Node {
		int x;
		int y;
		int direction;
		int sameDir;
		
		public Node(int x, int y, int direction, int sameDir) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.sameDir = sameDir;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			
			if (!(o instanceof Node)) {
				return false;
			}
			
			if (this.x == ((Node) o).x && this.y == ((Node) o).y && this.direction == ((Node) o).direction && this.sameDir == ((Node) o).sameDir) {
				return true;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return 141*141*x+141*y+4*direction+sameDir;
		}
	}

}

