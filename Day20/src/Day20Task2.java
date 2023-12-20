import java.util.*;
import java.io.*;

public class Day20Task2 {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		Map<String, Node> nodes = new HashMap<String, Node>();
		while (s.hasNext()) {
			String line = s.nextLine();
			Scanner p = new Scanner(line);
			p.useDelimiter("( -> |, )");
			String node = p.next();
			ArrayList<String> output = new ArrayList<String>();
			while (p.hasNext()) {
				output.add(p.next());
			}
			if (node.charAt(0) == '&') {
				nodes.put(node.substring(1), new Conjunction(node.substring(1), output));
			} else if (node.charAt(0) == '%') {
				nodes.put(node.substring(1), new Flipflop(node.substring(1), output));
			} else {
				nodes.put(node, new Broadcaster("broadcaster", output));
			}
			p.close();
		}
		for (Node n : nodes.values()) {
			for (Node p : nodes.values()) {
				if (n.equals(p)) {
					continue;
				}
				if (p.getNodes().contains(n.toString())) {
					n.addInput(p);
				}
			}
		}
		long lowSignals = 0;
		long highSignals = 0;
		int presses = 0;
		boolean started = false;
		while (!started) {
			presses++;
			ArrayList<Truple> queue = new ArrayList<Truple>();
			queue.add(new Truple("broadcaster",false,null));
			while(!queue.isEmpty()) {
				Truple temp = queue.remove(0);
				Node n = nodes.get(temp.n);
				if (temp.high) {
					if (n != null) {
						n.highSignal(queue, temp.sender);
						if (n.toString().equals("hp")) {
							System.out.println(temp.sender.toString() + " has sent: " + presses);
						}
					}
					highSignals++;
				} else {
					if (n != null) {
						n.lowSignal(queue, temp.sender);
					}
					lowSignals++;
				}
			}
		}
		System.out.println(lowSignals);
		System.out.println(highSignals);
		System.out.println(lowSignals*highSignals);
		System.out.println(presses);
	}
	
	
	
	
	private interface Node {
		public void lowSignal(ArrayList<Truple> queue, Node sender);
		public void highSignal(ArrayList<Truple> queue, Node sender);
		public void addInput(Node n);
		public ArrayList<String> getNodes();
	}
	
	private static class Flipflop implements Node{
		String name;
		boolean on;
		ArrayList<String> nodes;
		
		public Flipflop(String name, ArrayList<String> nodes) {
			on = false;
			this.name = name;
			this.nodes = nodes;
		}

		@Override
		public void lowSignal(ArrayList<Truple> queue, Node sender) {
			on = !on;
			if (on) {
				for (String n : nodes) {
					queue.add(new Truple(n, true,this));
				}
			} else {
				for (String n : nodes) {
					queue.add(new Truple(n, false,this));
				}
			}
			return;
		}

		@Override
		public void  highSignal(ArrayList<Truple> queue, Node sender) {
			return;
		}
		
		@Override
		public void addInput(Node n) {
			return;
		}
		
		@Override
		public ArrayList<String> getNodes() {
			return nodes;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof Node)) {
				return false;
			}
			return this.toString().equals(((Node) o).toString());
		}
		
		@Override
		public int hashCode() {
			return name.hashCode();
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	private static class Conjunction implements Node{
		Map<Node,Boolean> input;
		ArrayList<String> nodes;
		String name;
		
		public Conjunction(String name, ArrayList<String> nodes) {
			this.nodes = nodes;
			this.name = name;
			input = new HashMap<>();
		}

		@Override
		public void lowSignal(ArrayList<Truple> queue, Node sender) {
			if (input.get(sender)) {
				input.put(sender,false);
			}
			for (String n : nodes) {
				queue.add(new Truple(n,true,this));
			}
		}

		@Override
		public void highSignal(ArrayList<Truple> queue, Node sender) {
			if (!input.get(sender)) {
				input.put(sender,true);
			}
			boolean allHigh = !input.containsValue(false);
			if (allHigh) {
				for (String n : nodes) {
					queue.add(new Truple(n,false,this));
				}
			} else {
				for (String n : nodes) {
					queue.add(new Truple(n, true,this));
				}
			}
		}
		
		@Override
		public void addInput(Node n) {
			input.put(n,false);
		}
		
		@Override
		public ArrayList<String> getNodes() {
			return nodes;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof Node)) {
				return false;
			}
			return this.toString().equals(((Node) o).toString());
		}
		
		@Override
		public int hashCode() {
			return name.hashCode();
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	private static class Broadcaster implements Node{
		ArrayList<String> nodes;
		String name;
		
		public Broadcaster(String name, ArrayList<String> nodes) {
			this.nodes = nodes;
			this.name = name;
		}

		@Override
		public void lowSignal(ArrayList<Truple> queue, Node sender) {
			for (String n : nodes) {
				queue.add(new Truple(n,false,this));
			}
			
		}

		@Override
		public void highSignal(ArrayList<Truple> queue, Node sender) {
			for (String n : nodes) {
				queue.add(new Truple(n,false,this));
			}
		}
		
		@Override
		public void addInput(Node n) {
			return;
		}
		
		@Override
		public ArrayList<String> getNodes() {
			return nodes;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof Node)) {
				return false;
			}
			return this.toString().equals(((Node) o).toString());
		}
		
		@Override
		public int hashCode() {
			return name.hashCode();
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	private static class Truple {
		String n;
		boolean high;
		Node sender;
		
		public Truple(String n, boolean high, Node sender) {
			this.n = n;
			this.high = high;
			this.sender = sender;
		}
	}
}

