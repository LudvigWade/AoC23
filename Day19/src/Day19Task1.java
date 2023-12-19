import java.util.*;
import java.io.*;

public class Day19Task1 {
	
	
	public static void main(String[] args) throws FileNotFoundException {
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
		while (true) {
			String part = line;
			int x = 0;
			int m = 0;
			int a = 0;
			int ss = 0;
			int startnbr = part.indexOf('=')+1;
			int endnbr = part.indexOf(',');
			x = Integer.valueOf(part.substring(startnbr,endnbr));
			part = part.substring(endnbr+1);
			startnbr = part.indexOf('=')+1;
			endnbr = part.indexOf(',');
			m = Integer.valueOf(part.substring(startnbr,endnbr));
			part = part.substring(endnbr+1);
			startnbr = part.indexOf('=')+1;
			endnbr = part.indexOf(',');
			a = Integer.valueOf(part.substring(startnbr,endnbr));
			part = part.substring(endnbr+1);
			startnbr = part.indexOf('=')+1;
			ss = Integer.valueOf(part.substring(startnbr, part.length()-1));
			parts.add(new Quadruple(x,m,a,ss));
			
			if (s.hasNext()) {
				line = s.next();
			} else {
				break;
			}
		}
		for (Quadruple part : parts) {
			String current = "in";
			while (!current.equals("A") && !current.equals("R")) {
				Workflow wf = workflows.get(current);
				String prev = current;
				for (int i = 0; i<wf.order.size(); i++) {
					int order = wf.order.get(i);
					int nbr = wf.xmas.get(i);
					if (nbr < 0) {
						if (nbr > -part.xmas[order]) {
							current = wf.inst.get(i);
							break;
						}
					} else if (nbr > 0) {
						if (nbr > part.xmas[order]) {
							current = wf.inst.get(i);
							break;
						}
					}
				}
				if (current == prev) {
					current = wf.definst;
				}
			}
			if (current.equals("A")) {
				accepted.add(part);
			}
		}
		int sum = 0;
		for (Quadruple part : accepted) {
			for (int i = 0; i<4; i++) {
				sum += part.xmas[i];
			}
		}
		System.out.println(sum);
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
		int[] xmas;
		
		public Quadruple(int x, int m, int a, int s) {
			xmas = new int[4];
			xmas[0] = x;
			xmas[1] = m;
			xmas[2]= a;
			xmas[3] = s;
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
			 return 101*xmas[0]+53*xmas[1]+23*xmas[2]+7*xmas[3];
		 }
	}
}
