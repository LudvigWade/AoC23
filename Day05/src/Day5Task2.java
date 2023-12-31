
import java.io.*;
import java.util.*;

public class Day5Task2 {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("data"));
		Set<Range> seeds = new HashSet<Range>();
		Set<Range> soils = new HashSet<Range>();
		Set<Range> fertilizers = new HashSet<Range>();
		Set<Range> waters = new HashSet<Range>();
		Set<Range> lights = new HashSet<Range>();
		Set<Range> temperatures = new HashSet<Range>();
		Set<Range> humidities = new HashSet<Range>();
		Set<Range> locations = new HashSet<Range>();
		Long nbr1,nbr2,nbr3,sum;
		
		String a = s.next();
		String b = s.next();
		a = b;
		while (!a.equals("seed-to-soil")) {
			b = s.next();
			seeds.add(new Range(Long.parseLong(a), Long.parseLong(a)+Long.parseLong(b)-1));
			a = s.next();
		}
		s.next();
		a = s.next();
		while (!a.equals("soil-to-fertilizer")) {
			nbr1 = Long.parseLong(a);
			nbr2 = s.nextLong();
			nbr3 = s.nextLong();
			nbr3--;
			newRange(seeds,soils,nbr1,nbr2,nbr3);
			a = s.next();
		}
		for (Range p : seeds) {
			soils.add(p);
		}
		s.next();
		a = s.next();
		while (!a.equals("fertilizer-to-water")) {
			nbr1 = Long.parseLong(a);
			nbr2 = s.nextLong();
			nbr3 = s.nextLong();
			nbr3--;
			newRange(soils,fertilizers,nbr1,nbr2,nbr3);
			a = s.next();
		}
		for (Range p : soils) {
			fertilizers.add(p);
		}
		s.next();
		a = s.next();
		while (!a.equals("water-to-light")) {
			nbr1 = Long.parseLong(a);
			nbr2 = s.nextLong();
			nbr3 = s.nextLong();
			nbr3--;
			newRange(fertilizers,waters,nbr1,nbr2,nbr3);
			a = s.next();
		}
		for (Range p : fertilizers) {
			waters.add(p);
		}
		s.next();
		a = s.next();
		while (!a.equals("light-to-temperature")) {
			nbr1 = Long.parseLong(a);
			nbr2 = s.nextLong();
			nbr3 = s.nextLong();
			nbr3--;
			newRange(waters,lights,nbr1,nbr2,nbr3);
			a = s.next();
		}
		for (Range p : waters) {
			lights.add(p);
		}
		s.next();
		a = s.next();
		while (!a.equals("temperature-to-humidity")) {
			nbr1 = Long.parseLong(a);
			nbr2 = s.nextLong();
			nbr3 = s.nextLong();
			nbr3--;
			newRange(lights,temperatures,nbr1,nbr2,nbr3);
			a = s.next();
		}
		for (Range p : lights) {
			temperatures.add(p);
		}
		s.next();
		a = s.next();
		while (!a.equals("humidity-to-location")) {
			nbr1 = Long.parseLong(a);
			nbr2 = s.nextLong();
			nbr3 = s.nextLong();
			nbr3--;
			newRange(temperatures,humidities,nbr1,nbr2,nbr3);
			a = s.next();
		}
		for (Range p : temperatures) {
			humidities.add(p);
		}
		s.next();
		a = s.next();
		while (s.hasNext()) {
			nbr1 = Long.parseLong(a);
			nbr2 = s.nextLong();
			nbr3 = s.nextLong();
			nbr3--;
			newRange(humidities,locations,nbr1,nbr2,nbr3);
			if (s.hasNext()) {
				a = s.next();
			} else break;
		}
		for (Range p : humidities) {
			locations.add(p);
		}
		sum = Long.MAX_VALUE;
		for (Range p : locations) {
			if (p.start<sum) {
				sum = p.start;
			}
		}
		System.out.println(sum);
	}
	
	private static void newRange(Set<Range> oldSet, Set<Range> newSet, Long nbr1, Long nbr2, Long nbr3) throws Exception {
		Set<Range> add = new HashSet<Range>();
		Set<Range> remove = new HashSet<Range>();
		for (Range p : oldSet) {
			if (p.inrange(nbr2, nbr2+nbr3) == 1) {
				newSet.add(new Range(nbr1,nbr1+nbr3));
				remove.add(p);
				if (p.start != nbr2 && p.end != nbr2+nbr3) {
					add.add(new Range(p.start,nbr2-1));
					add.add(new Range(nbr2+nbr3+1,p.end));
				} else if (p.start == nbr2 && p.end != nbr2+nbr3) {
					add.add(new Range(nbr2+nbr3+1,p.end));
				} else if (p.start != nbr2 && p.end == nbr2+nbr3) {
					add.add(new Range(p.start,nbr2-1));
				}
			} else if (p.inrange(nbr2, nbr2+nbr3) == 2) {
				newSet.add(new Range(nbr1,nbr1 + p.end - nbr2));
				remove.add(p);
				add.add(new Range(p.start,nbr2-1));
			} else if (p.inrange(nbr2, nbr2+nbr3) == 3) {
				newSet.add(new Range(nbr1 + p.start - nbr2,nbr1+nbr3));
				remove.add(p);
				add.add(new Range(nbr2+nbr3+1,p.end));
			} else if (p.inrange(nbr2, nbr2+nbr3) == 4) {
				newSet.add(new Range(nbr1 + p.start - nbr2,nbr1 + p.end - nbr2));
				remove.add(p);
			}
		}
		for (Range p : remove) {
			oldSet.remove(p);
		}
		for (Range p : add) {
			oldSet.add(p);
		}
	}
	
	
	private static class Range {
		private long start;
		private long end;
		
		public Range(long start, long end) throws Exception {
			this.start = start;
			this.end = end;
			if (end < start) {
				throw new Exception();
			}
			if (end < 0 || start < 0) {
				throw new Exception();
			}
		}
		
		public int inrange(long start, long end) {
			if (this.start <= start && this.end >= end) { // Outer or equal
				return 1;
			}
			if (this.start < start && this.end > start) { // Lower
				return 2;
			}
			if (this.end > end && this.start < end) { // Upper
				return 3;
			}
			if (this.start >= start && this.end <= end) { // Inner or equal
				return 4;
			}
			return 0;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			
			if (!(o instanceof Range)) {
				return false;
			}
			
			if (this.start == ((Range) o).start && this.end == ((Range) o).end) {
				return true;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return (int) (start%65536 + end%256);
		}
	}

}

