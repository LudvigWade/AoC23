import java.io.*;
import java.util.*;

public class Day13Task1 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		int sum = 0;
		while(s.hasNext()) {
			ArrayList<String> data = new ArrayList<>();
			String line = s.nextLine();
			ArrayList<Integer> indexes = new ArrayList<>();
			while (line != "") {
				data.add(line);
				if (s.hasNextLine()) {
					line = s.nextLine();
					if (data.get(data.size()-1).equals(line)) {
						indexes.add(data.size()-1);
					}
				} else {
					break;
				}
			}
			boolean matchfound = true;
			int matchind = -1;
			for (Integer ind : indexes) { //Check if a string is equal to it's mirror
				matchfound = true;
				for (int i = 0; i<=ind; i++) {
					if (ind+1+i > data.size()-1) {
						break;
					}
					if (!data.get(ind-i).equals(data.get(ind+1+i))) {
						matchfound = false;
						break;
					}
				}
				if (matchfound) {
					matchind = ind;
					break;
				}
			}
			if (matchfound && indexes.size() != 0) {
				sum += (matchind+1)*100;
				continue;
			}
			indexes.clear();
			String current = data.get(0);
			for (int j = 0; j<data.get(0).length()-1; j++) { // Possible mirrors in first string
				if (current.charAt(j) == current.charAt(j+1)) {
					StringBuilder sb;
					if (j < current.length()/2) {
						sb = new StringBuilder(current.substring(0,j+1));
						sb.reverse();
						if (sb.toString().equals(current.substring(j+1, j+1+sb.length()))) {
							indexes.add(j);
						}
					} else {
						sb = new StringBuilder(current.substring(j+1, current.length()));
						sb.reverse();
						if (sb.toString().equals(current.substring(j+1-sb.length(), j+1))) {
							indexes.add(j);
						}
					}
					
				}
			}
			for (Integer ind : indexes) { // Check if the letters in each string is mirrored around indexes
				matchfound = true;
				for (int i = 1; i<data.size(); i++) {
					current = data.get(i);
					if (current.charAt(ind) == current.charAt(ind+1)) {
						StringBuilder sb;
						if (ind < current.length()/2) {
							sb = new StringBuilder(current.substring(0,ind+1));
							sb.reverse();
							if (!sb.toString().equals(current.substring(ind+1, ind+1+sb.length()))) {
								matchfound = false;
								break;
							}
						} else {
							sb = new StringBuilder(current.substring(ind+1, current.length()));
							sb.reverse();
							if (!sb.toString().equals(current.substring(ind+1-sb.length(), ind+1))) {
								matchfound = false;
								break;
							}
						}
					} else {
						matchfound = false;
						break;
					}
				}
				if (matchfound) {
					matchind = ind;
					break;
				}
			}
			if (matchfound) {
				sum += (matchind+1);
				continue;
			} else {
				System.out.println("Error");
			}
		}
		System.out.println(sum);
	}
}
