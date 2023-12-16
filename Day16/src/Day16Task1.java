import java.util.*;
import java.io.*;

public class Day16Task1 {
	char[][] data;
	Map<Duple, Integer> map;
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		Map<Duple, Integer> map = new HashMap<>();
		char[] line = s.nextLine().toCharArray();
		char[][] data = new char[110][line.length];
		data[0] = line;
		for (int i = 1; i<110; i++) {
			data[i] = s.nextLine().toCharArray();
		}
		Day16Task1 d = new Day16Task1(data, map);
		
		d.solve();
		
		System.out.println(map.size());
	}
	
	public Day16Task1(char[][] data, Map<Duple, Integer> map) {
		this.data = data;
		this.map = map;
	}
	
	public void solve() {
		map.put(new Duple(0,0), 3);
		solve(new Duple(0,0));
	}
	
	private void solve(Duple coord) { // 0 = up; 1 = down; 2 = right; 3 = left; (from)
		Duple temp = null;
		switch (data[coord.x][coord.y]) {
		case '.':
			temp = findNext(coord,map.get(coord));
			if (temp != null) {
				map.put(temp, map.get(coord));
				solve(temp);
			}
			break;
		case '/':
			switch (map.get(coord)) {
			case 0:
				temp = findNext(coord,2);
				if (temp != null) {
					map.put(temp, 2);
					solve(temp);
				}
				break;
			case 1:
				temp = findNext(coord,3);
				if (temp != null) {
					map.put(temp, 3);
					solve(temp);
				}
				break;
			case 2:
				temp = findNext(coord,0);
				if (temp != null) {
					map.put(temp, 0);
					solve(temp);
				}
				break;
			case 3:
				temp = findNext(coord,1);
				if (temp != null) {
					map.put(temp, 1);
					solve(temp);
				}
				break;
			}
			break;
		case '\\':
			switch (map.get(coord)) {
			case 0:
				temp = findNext(coord,3);
				if (temp != null) {
					map.put(temp, 3);
					solve(temp);
				}
				break;
			case 1:
				temp = findNext(coord,2);
				if (temp != null) {
					map.put(temp, 2);
					solve(temp);
				}
				break;
			case 2:
				temp = findNext(coord,1);
				if (temp != null) {
					map.put(temp, 1);
					solve(temp);
				}
				break;
			case 3:
				temp = findNext(coord,0);
				if (temp != null) {
					map.put(temp, 0);
					solve(temp);
				}
				break;
			}
			break;
		case '|':
			switch (map.get(coord)) {
			case 0:
				temp = findNext(coord,0);
				if (temp != null) {
					map.put(temp, 0);
					solve(temp);
				}
				break;
			case 1:
				temp = findNext(coord,1);
				if (temp != null) {
					map.put(temp, 1);
					solve(temp);
				}
				break;
			case 2:
				temp = findNext(coord,0);
				if (temp != null) {
					map.put(temp, 0);
					solve(temp);
				}
				temp = findNext(coord,1);
				if (temp != null) {
					map.put(temp, 1);
					solve(temp);
				}
				break;
			case 3:
				temp = findNext(coord,0);
				if (temp != null) {
					map.put(temp, 0);
					solve(temp);
				}
				temp = findNext(coord,1);
				if (temp != null) {
					map.put(temp, 1);
					solve(temp);
				}
				break;
			}
			break;
		case '-':
			switch (map.get(coord)) {
			case 0:
				temp = findNext(coord,2);
				if (temp != null) {
					map.put(temp, 2);
					solve(temp);
				}
				temp = findNext(coord,3);
				if (temp != null) {
					map.put(temp, 3);
					solve(temp);
				}
				break;
			case 1:
				temp = findNext(coord,2);
				if (temp != null) {
					map.put(temp, 2);
					solve(temp);
				}
				temp = findNext(coord,3);
				if (temp != null) {
					map.put(temp, 3);
					solve(temp);
				}
				break;
			case 2:
				temp = findNext(coord,2);
				if (temp != null) {
					map.put(temp, 2);
					solve(temp);
				}
				break;
			case 3:
				temp = findNext(coord,3);
				if (temp != null) {
					map.put(temp, 3);
					solve(temp);
				}
				break;
			}
			break;
		}
	}
	
	public Duple findNext(Duple current, int direction) { // 0 = up; 1 = down; 2 = right; 3 = left; (from)
		Duple temp = null;
		switch (direction) {
		case 0:
			if (current.x == data.length-1) {
				return null;
			}
			temp = new Duple(current.x+1,current.y);
			if (map.containsKey(temp) && map.get(temp) == direction) {
				return null;
			}
			return temp;
		case 1:
			if (current.x == 0) {
				return null;
			}
			temp = new Duple(current.x-1,current.y);
			if (map.containsKey(temp) && map.get(temp) == direction) {
				return null;
			}
			return temp;
		case 2:
			if (current.y == 0) {
				return null;
			}
			temp = new Duple(current.x,current.y-1);
			if (map.containsKey(temp) && map.get(temp) == direction) {
				return null;
			}
			return temp;
		case 3: 
			if (current.y == data[current.x].length-1) {
				return null;
			}
			temp = new Duple(current.x,current.y+1);
			if (map.containsKey(temp) && map.get(temp) == direction) {
				return null;
			}
			return temp;
		}
		return null;
	}
	
	
	private class Duple {
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
