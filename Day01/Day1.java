import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		String line;
		int sum = 0;
		while(s.hasNext()) {
			line = s.nextLine();
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i<line.length(); i++) {
				if (Character.isDigit(line.charAt(i))) {
					list.add(Character.getNumericValue(line.charAt(i)));
				}
				if (line.charAt(i) == 'o') {
					if (i+1 < line.length() && line.charAt(i+1) == 'n') {
						if (i+2 < line.length() && line.charAt(i+2) == 'e') {
							list.add(1);
						}
					}
				}
				if (line.charAt(i) == 't') {
					if (i+1 < line.length() && line.charAt(i+1) == 'w') {
						if (i+2 < line.length() && line.charAt(i+2) == 'o') {
							list.add(2);
						}
					}
					if (i+1 < line.length() && line.charAt(i+1) == 'h') {
						if (i+2 < line.length() && line.charAt(i+2) == 'r') {
							if (i+3 < line.length() && line.charAt(i+3) == 'e') {
								if (i+4 < line.length() && line.charAt(i+4) == 'e') {
									list.add(3);
								}
							}
						}
					}
				}
				if (line.charAt(i) == 'f') {
					if (i+1 < line.length() && line.charAt(i+1) == 'o') {
						if (i+2 < line.length() && line.charAt(i+2) == 'u') {
							if (i+3 < line.length() && line.charAt(i+3) == 'r') {
								list.add(4);
							}
						}
					}
					if (i+1 < line.length() && line.charAt(i+1) == 'i') {
						if (i+2 < line.length() && line.charAt(i+2) == 'v') {
							if (i+3 < line.length() && line.charAt(i+3) == 'e') {
								list.add(5);
							}
						}
					}
				}
				if (line.charAt(i) == 's') {
					if (i+1 < line.length() && line.charAt(i+1) == 'i') {
						if (i+2 < line.length() && line.charAt(i+2) == 'x') {
							list.add(6);
						}
					}
					if (i+1 < line.length() && line.charAt(i+1) == 'e') {
						if (i+2 < line.length() && line.charAt(i+2) == 'v') {
							if (i+3 < line.length() && line.charAt(i+3) == 'e') {
								if (i+4 < line.length() && line.charAt(i+4) == 'n') {
									list.add(7);
								}
							}
						}
					}
				}
				if (line.charAt(i) == 'e') {
					if (i+1 < line.length() && line.charAt(i+1) == 'i') {
						if (i+2 < line.length() && line.charAt(i+2) == 'g') {
							if (i+3 < line.length() && line.charAt(i+3) == 'h') {
								if (i+4 < line.length() && line.charAt(i+4) == 't') {
									list.add(8);
								}
							}
						}
					}
				}
				if (i < line.length() && line.charAt(i) == 'n') {
					if (i+1 < line.length() && i < line.length() && line.charAt(i+1) == 'i') {
						if (i+2 < line.length() && line.charAt(i+2) == 'n') {
							if (i+3 < line.length() && line.charAt(i+3) == 'e') {
								list.add(9);
							}
						}
					}
				}
			}
			sum += 10*list.get(0)+list.get(list.size()-1);
		}
		System.out.println(sum);

	}

}
