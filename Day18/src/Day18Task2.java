import java.util.*;
import java.io.*;

public class Day18Task2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		Set<Duple> edges = new HashSet<>();
		long x = 0;
		long y = 0;
		long size = 0;
		long edge = 0;
		char prevdir = 'a';
		while (s.hasNext()) {
			s.next();
			s.nextInt();
			String line = s.next();
			line = line.substring(2, line.length()-1);
			char d = line.charAt(line.length()-1);
			long steps = Integer.parseInt(line.substring(0, line.length()-1),16);
			switch (d) {
			case '0': // R
				edges.add(new Duple(x,x,y,y+steps));
				y += steps;
				break;
			case '2': // L
				edges.add(new Duple(x,x,y,y-steps));
				y -= steps;
				break;
			case '3': // U
				edges.add(new Duple(x,x+steps,y,y));
				x += steps;
				break;
			case '1': // D
				edges.add(new Duple(x,x-steps,y,y));
				x -= steps;
				break;
			}
		}
		for (Duple current : edges) {
			size += (current.x.start*current.y.end - current.x.end*current.y.start);
			edge += (Math.abs(current.x.end-current.x.start)+Math.abs(current.y.end-current.y.start));
		}
		size = Math.abs(size)/2+edge/2+1;
		System.out.println(size);
	}
	
	private static class Duple {
		Range x;
		Range y;
		
		public Duple(long xstart, long xend, long ystart, long yend) {
			x = new Range(xstart, xend);
			y = new Range(ystart, yend);
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
			return (int) ((int) 110*x.start+y.end);
		}
	}
	
	private static class Range {
		long start;
		long end;
		
		public Range(long start, long end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			
			if (!(o instanceof Duple)) {
				return false;
			}
			
			if (this.start == ((Range) o).start && this.end == ((Range) o).end) {
				return true;
			}
			
			return false;
		}
	}
}

