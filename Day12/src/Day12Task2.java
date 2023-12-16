import java.util.*;
import java.io.*;


public class Day12Task2 {
	HashMap<Truple,Long> map;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		Long sum = (long) 0;
		s.useDelimiter("(\\s|,)");
		int datalength = 1000;
		for (int i = 0; i<datalength; i++) {
			String sline = s.next();
			String line = sline + "?" + sline + "?" + sline + "?" + sline + "?" + sline + ".";
			ArrayList<Integer> groups = new ArrayList<>();
			ArrayList<Integer> groupss = new ArrayList<>();
			while (s.hasNextInt()) {
				groupss.add(s.nextInt());
			}
			for (int j = 0; j<5;j++) {
				groups.addAll(groupss);
			}
			Day12Task2 d = new Day12Task2();
			sum += d.recursive(line,groups,0);
			if (s.hasNext()) {
				s.next();
			}
		}
		System.out.println(sum);
	}
	
	public Day12Task2() {
		map = new HashMap<>();
	}
	
	public Long recursive(String s, ArrayList<Integer> groups, int alreadyRead) {
		Truple temp = new Truple(s,groups,alreadyRead);
		if (this.map.containsKey(temp)) {
			return map.get(temp);
		}
		
		if (s.length() == 0) {
			if (groups.isEmpty() && alreadyRead == 0) {
				return (long) 1;
			} else {
				return (long) 0;
			}
		}
		if (groups.isEmpty() && alreadyRead != 0) {
			return (long) 0;
		}
		
		if (s.charAt(0) == '.') {
			if (alreadyRead == 0) {
				Long res = recursive(s.substring(1), groups, 0);
				map.put(new Truple(s, groups, alreadyRead),res);
				return res;
			} else if (alreadyRead == groups.get(0)) {
				ArrayList<Integer> newGroup = new ArrayList<>(groups);
				newGroup.remove(0);
				Long res = recursive(s, newGroup, 0);
				map.put(new Truple(s, groups, alreadyRead),res);
				return res;
			} else if (alreadyRead > groups.get(0)) {
				return (long) 0;
			} else if (alreadyRead < groups.get(0)){
				return (long) 0;
			} else {
				System.out.println("Error");
			}
		}
		if (groups.isEmpty() && alreadyRead != 0) {
			return (long) 0;
		}
		if (s.charAt(0) == '#') {
			if (!groups.isEmpty() && alreadyRead > groups.get(0)) {
				return (long) 0;
			}
			Long res = recursive(s.substring(1), groups, alreadyRead+1);
			map.put(new Truple(s, groups, alreadyRead),res);
			return res;
		}
		if (s.charAt(0) == '?') {
			String temp1 = "." + s.substring(1);
			Long dot = recursive(temp1,groups,alreadyRead);
			temp1 = "#" + s.substring(1);
			Long pound = recursive(temp1,groups,alreadyRead);
			map.put(new Truple(s, groups, alreadyRead), dot+pound);
			return dot + pound;
		}
		return (long) 0;
	}
	
	private class Truple {
		String remaining;
		ArrayList<Integer> groups;
		int alreadyRead;
		
		public Truple(String remaining, ArrayList<Integer> groups, int alreadyRead) {
			this.remaining = remaining;
			this.groups = groups;
			this.alreadyRead = alreadyRead;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			
			if (!(o instanceof Truple)) {
				return false;
			}
			
			if (this.remaining.equals(((Truple) o).remaining) && this.groups.equals(((Truple) o).groups) && this.alreadyRead == ((Truple) o).alreadyRead) {
				return true;
			}
			
			return false;
		}
		
		@Override
		public int hashCode() {
			if (remaining != "") {
				return alreadyRead + (int) remaining.charAt(0)*10 + remaining.length()*53;
			} else {
				return alreadyRead;
			}
		}
	}

}

