import java.util.*;

import java.io.*;

public class Day19Task2 {
	Map<String, Workflow> workflows;
	Set<Quadruple> parts;
	Set<Quadruple> accepted;
	
	
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(new File("data"));
		Map<String, Workflow> workflows = new HashMap<>();
		Set<Quadruple> parts = new HashSet<>();
		Set<Quadruple> accepted = new HashSet<>();
		String line = s.next();
		while (line.charAt(0) != '{') { //Reading data
			Scanner p = new Scanner(line);
			p.useDelimiter("(\\{|,|\\})");
			String name = p.next();
			ArrayList<Integer> xmas = new ArrayList<>();
			ArrayList<String> insts = new ArrayList<>();
			ArrayList<Integer> orders = new ArrayList<>();
			String definst = null;
			while(p.hasNext()) {
				String inst = p.next();
				if (inst.length() > 1 && (inst.charAt(1) == '<' || inst.charAt(1) == '>')) {
					switch (inst.charAt(0)) {
					case 'x':
						int endnbr = inst.indexOf(':');
						if (inst.charAt(1) == '<') {
							int startnbr = inst.indexOf('<')+1;
							xmas.add(Integer.valueOf(inst.substring(startnbr, endnbr)));
						} else if (inst.charAt(1) == '>') {
							int startnbr = inst.indexOf('>')+1;
							xmas.add(-Integer.valueOf(inst.substring(startnbr, endnbr)));
						}
						orders.add(0);
						insts.add(inst.substring(endnbr+1));
						break;
					case 'm':
						endnbr = inst.indexOf(':');
						if (inst.charAt(1) == '<') {
							int startnbr = inst.indexOf('<')+1;
							xmas.add(Integer.valueOf(inst.substring(startnbr, endnbr)));
						} else if (inst.charAt(1) == '>') {
							int startnbr = inst.indexOf('>')+1;
							xmas.add(-Integer.valueOf(inst.substring(startnbr, endnbr)));
						}
						orders.add(1);
						insts.add(inst.substring(endnbr+1));
						break;
					case 'a':
						endnbr = inst.indexOf(':');
						if (inst.charAt(1) == '<') {
							int startnbr = inst.indexOf('<')+1;
							xmas.add(Integer.valueOf(inst.substring(startnbr, endnbr)));
						} else if (inst.charAt(1) == '>') {
							int startnbr = inst.indexOf('>')+1;
							xmas.add(-Integer.valueOf(inst.substring(startnbr, endnbr)));
						}
						orders.add(2);
						insts.add(inst.substring(endnbr+1));
						break;
					case 's':
						endnbr = inst.indexOf(':');
						if (inst.charAt(1) == '<') {
							int startnbr = inst.indexOf('<')+1;
							xmas.add(Integer.valueOf(inst.substring(startnbr, endnbr)));
						} else if (inst.charAt(1) == '>') {
							int startnbr = inst.indexOf('>')+1;
							xmas.add(-Integer.valueOf(inst.substring(startnbr, endnbr)));
						}
						orders.add(3);
						insts.add(inst.substring(endnbr+1));
						break;
					default:
						definst = inst;
					}
				} else {
					definst = inst;
				}
			}
			workflows.put(name, new Workflow(xmas, insts, orders, definst));
			p.close();
			line = s.next();
		}
		System.out.println("Data read");
		Day19Task2 d = new Day19Task2(workflows, parts, accepted);
		Range[] r = {new Range(1,4000),new Range(1,4000),new Range(1,4000),new Range(1,4000)};
		Quadruple start = new Quadruple(r);
		d.depthfirst(start, "in");
		
		long sum = 0;
		for (Quadruple part : d.accepted) {
			sum += (part.xmas[0].end-part.xmas[0].start+1)*(part.xmas[1].end-part.xmas[1].start+1)*(part.xmas[2].end-part.xmas[2].start+1)*(part.xmas[3].end-part.xmas[3].start+1);
		}
		System.out.println(sum);
	}
	
	public Day19Task2(Map<String, Workflow> workflows, Set<Quadruple> parts, Set<Quadruple> accepted) {
		this.workflows = workflows;
		this.parts = parts;
		this.accepted = accepted;
	}
	
	public void depthfirst(Quadruple q, String key) throws Exception {
		if (key.equals("A")) {
			accepted.add(q);
			return;
		}
		if (key.equals("R")) {
			return;
		}
		Workflow wf = workflows.get(key);
		String prev = key;
		for (int i = 0; i<wf.order.size(); i++) {
			int order = wf.order.get(i);
			int nbr = wf.xmas.get(i);
			if (nbr < 0) {
				if (q.xmas[order].inrange(-nbr) > 0 && q.xmas[order].inrange(-nbr) != 3) {
					Range[] xmas2 = new Range[4];
					for (int j = 0; j<4; j++) {
						xmas2[j] = new Range(q.xmas[j].start,q.xmas[j].end);
					}
					xmas2[order] = new Range(-nbr+1,xmas2[order].end);
					q.xmas[order] = new Range(q.xmas[order].start,-nbr);
					depthfirst(new Quadruple(xmas2),wf.inst.get(i));
				}
			} else if (nbr > 0) {
				if (q.xmas[order].inrange(nbr) > 0 && q.xmas[order].inrange(nbr) != 2) {
					Range[] xmas2 = new Range[4];
					for (int j = 0; j<4; j++) {
						xmas2[j] = new Range(q.xmas[j].start,q.xmas[j].end);
					}
					xmas2[order] = new Range(xmas2[order].start,nbr-1);
					q.xmas[order] = new Range(nbr,q.xmas[order].end);
					depthfirst(new Quadruple(xmas2),wf.inst.get(i));
				}
			}
		}
		depthfirst(q, wf.definst);
	}
	
	
	private static class Workflow {
		ArrayList<Integer> xmas;
		ArrayList<Integer> order;
		ArrayList<String> inst;
		String definst;
		
		public Workflow(ArrayList<Integer> xmas, ArrayList<String> inst, ArrayList<Integer> order, String definst) {
			this.xmas = xmas;
			this.inst = inst;
			this.order = order;
			this.definst = definst;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof Workflow)) {
				return false;
			}
			if (this.xmas.equals(((Workflow) o).xmas) && this.inst.equals(((Workflow) o).inst)) {
				return true;
			}
			return false;
		}
		
		 @Override
		 public int hashCode() {
			 return xmas.hashCode()+inst.hashCode();
		 }
	}
	
	
	private static class Quadruple {
		Range[] xmas;
		
		public Quadruple(Range[] xmas) {
			this.xmas = xmas;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof Quadruple)) {
				return false;
			}
			if (this.xmas[0] == ((Quadruple) o).xmas[0] && this.xmas[1] == ((Quadruple) o).xmas[1] && this.xmas[2] == ((Quadruple) o).xmas[2] && this.xmas[3] == ((Quadruple) o).xmas[3]) {
				return true;
			}
			return false;
		}
		
		 @Override
		 public int hashCode() {
			 return 101*xmas[0].hashCode()+53*xmas[1].hashCode()+23*xmas[2].hashCode()+7*xmas[3].hashCode();
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
		
		public int inrange(long value) {
			if (this.start < value && this.end > value) { //Value inside
				return 1;
			}
			if (this.start == value) { // Value equal to lower bound
				return 2;
			}
			if (this.end == value) { // Value equal to upper bound
				return 3;
			}
			return 0; // Value not in range
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

