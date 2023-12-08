import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Day8Task2_2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		s.useDelimiter("\\W+");
		char[] instructions = s.nextLine().toCharArray();
		ConcurrentHashMap<String,Dual> map = new ConcurrentHashMap<String,Dual>();
		ConcurrentHashMap<Long,Integer> containsZ = new ConcurrentHashMap<Long,Integer>();
		
		List<String> start = new ArrayList<String>();
		while (s.hasNext()) {
			String a = s.next();
			String b = s.next();
			String c = s.next();
			if (a.charAt(2) == 'A') {
				start.add(a);
			}
			map.put(a,new Dual(b,c));
		}
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i<start.size(); i++) {
			final int k = i;
			threads.add(new Thread(() -> {
				Long steps = (long) 0;
				String current = start.get(k);
				String next = null;
				while(true) {
					if (instructions[(int) (steps%instructions.length)] == 'L') {
						next = map.get(current).left;
					} else {
						next = map.get(current).right;
					}
					steps++;
					current = next;
					if (current.charAt(2) == 'Z') {
						if (containsZ.containsKey(steps)) {
							containsZ.put(steps, containsZ.get(steps)+1);
						} else {
							containsZ.put(steps, 1);
						}
					}
				}
			}));
			threads.get(i).start();
		}
		Thread t = new Thread(() -> {
			boolean finished = false;
			Long prevPrint = (long) 0;
			try {
				while (!finished) {
					if (containsZ.isEmpty()) {
						Thread.sleep(100);
					}
					Long minkey = Collections.min(containsZ.keySet());
					if (containsZ.get(minkey) == 6) {
						for (Thread t1 : threads) {
							t1.interrupt();
						}
						System.out.println(minkey);
						finished = true;
					} else {
						containsZ.remove(minkey);
					}
					if (minkey-prevPrint > 1000000) {
						System.out.println(minkey);
						prevPrint = minkey;
					}
				}
			} catch (InterruptedException e) {
				for (Thread t1 : threads) {
					t1.interrupt();
				}
				System.out.println("Interrupted");
			}
		});
		t.start();
	}
	
	private static class Dual {
		String left;
		String right;
		
		public Dual(String one, String two) {
			left = one;
			right = two;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof Dual)) {
				return false;
			}
			if (this.left.equals(((Dual) o).left) && this.right.equals(((Dual) o).right)) {
				return true;
			}
			return false;
		}
	}

}

