import java.io.*;
import java.util.*;

public class Day5Task1 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		Set<Long> seeds = new HashSet<Long>();
		Set<Long> soils = new HashSet<Long>();
		Set<Long> fertilizers = new HashSet<Long>();
		Set<Long> waters = new HashSet<Long>();
		Set<Long> lights = new HashSet<Long>();
		Set<Long> temperatures = new HashSet<Long>();
		Set<Long> humidities = new HashSet<Long>();
		Set<Long> locations = new HashSet<Long>();
		Set<Long> remove = new HashSet<Long>();
		Long nbr1,nbr2,nbr3;
		
		while (s.hasNext()) {
			String a = s.next();
			if (a.equals("seeds:")) {
				a = s.next();
				while (!a.equals("seed-to-soil")) {
					seeds.add(Long.parseLong(a));
					a = s.next();
				}
			}
			System.out.println(seeds.size());
			if (a.equals("seed-to-soil")) {
				s.next();
				a = s.next();
				while (!a.equals("soil-to-fertilizer")) {
					nbr1 = Long.parseLong(a);
					nbr2 = s.nextLong();
					nbr3 = s.nextLong();
					for (Long p : seeds) {
						if (p < nbr2+nbr3 && p >= nbr2) {
							soils.add(nbr1+p-nbr2);
							remove.add(p);
						}
					}
					for (Long p : remove) {
						seeds.remove(p);
					}
					remove.clear();
					a = s.next();
				}
				for (Long p : seeds) {
					soils.add(p);
				}
			}
			System.out.println(soils.size());
			if (a.equals("soil-to-fertilizer")) {
				s.next();
				a = s.next();
				while (!a.equals("fertilizer-to-water")) {
					nbr1 = Long.parseLong(a);
					nbr2 = s.nextLong();
					nbr3 = s.nextLong();
					for (Long p : soils) {
						if (p < nbr2+nbr3 && p >= nbr2) {
							fertilizers.add(nbr1+p-nbr2);
							remove.add(p);
						}
					}
					for (Long p : remove) {
						soils.remove(p);
					}
					remove.clear();
					a = s.next();
				}
				for (Long p : soils) {
					fertilizers.add(p);
				}
			}
			System.out.println(fertilizers.size());
			if (a.equals("fertilizer-to-water")) {
				s.next();
				a = s.next();
				while (!a.equals("water-to-light")) {
					nbr1 = Long.parseLong(a);
					nbr2 = s.nextLong();
					nbr3 = s.nextLong();
					for (Long p : fertilizers) {
						if (p < nbr2+nbr3 && p >= nbr2) {
							waters.add(nbr1+p-nbr2);
							remove.add(p);
						}
					}
					for (Long p : remove) {
						fertilizers.remove(p);
					}
					remove.clear();
					a = s.next();
				}
				for (Long p : fertilizers) {
					waters.add(p);
				}
			}
			System.out.println(waters.size());
			if (a.equals("water-to-light")) {
				s.next();
				a = s.next();
				while (!a.equals("light-to-temperature")) {
					nbr1 = Long.parseLong(a);
					nbr2 = s.nextLong();
					nbr3 = s.nextLong();
					for (Long p : waters) {
						if (p < nbr2+nbr3 && p >= nbr2) {
							lights.add(nbr1+p-nbr2);
							remove.add(p);
						}
					}
					for (Long p : remove) {
						waters.remove(p);
					}
					remove.clear();
					a = s.next();
				}
				for (Long p : waters) {
					lights.add(p);
				}
			}
			System.out.println(lights.size());
			if (a.equals("light-to-temperature")) {
				s.next();
				a = s.next();
				while (!a.equals("temperature-to-humidity")) {
					nbr1 = Long.parseLong(a);
					nbr2 = s.nextLong();
					nbr3 = s.nextLong();
					for (Long p : lights) {
						if (p < nbr2+nbr3 && p >= nbr2) {
							temperatures.add(nbr1+p-nbr2);
							remove.add(p);
						}
					}
					for (Long p : remove) {
						lights.remove(p);
					}
					remove.clear();
					a = s.next();
				}
				for (Long p : lights) {
					temperatures.add(p);
				}
			}
			System.out.println(temperatures.size());
			if (a.equals("temperature-to-humidity")) {
				s.next();
				a = s.next();
				while (!a.equals("humidity-to-location")) {
					nbr1 = Long.parseLong(a);
					nbr2 = s.nextLong();
					nbr3 = s.nextLong();
					for (Long p : temperatures) {
						if (p < nbr2+nbr3 && p >= nbr2) {
							humidities.add(nbr1+p-nbr2);
							remove.add(p);
						}
					}
					for (Long p : remove) {
						temperatures.remove(p);
					}
					remove.clear();
					a = s.next();
				}
				for (Long p : temperatures) {
					humidities.add(p);
				}
			}
			System.out.println(humidities.size());
			if (a.equals("humidity-to-location")) {
				s.next();
				a = s.next();
				while (s.hasNext()) {
					nbr1 = Long.parseLong(a);
					nbr2 = s.nextLong();
					nbr3 = s.nextLong();
					for (Long p : humidities) {
						if (p < nbr2+nbr3 && p >= nbr2) {
							locations.add(nbr1+p-nbr2);
							remove.add(p);
						}
					}
					for (Long p : remove) {
						humidities.remove(p);
					}
					remove.clear();
					if (s.hasNext()) {
						a = s.next();
					} else break;
				}
				for (Long p : humidities) {
					locations.add(p);
				}
			}
			System.out.println(locations.size());
			Long sum = Long.MAX_VALUE;
			for (Long p : locations) {
				if (p<sum) {
					sum = p;
				}
			}
			System.out.println(sum);
		}
	}

}
