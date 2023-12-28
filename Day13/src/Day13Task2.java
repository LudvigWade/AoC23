import java.io.*;
import java.util.*;

public class Day13Task2 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		int sum = 0;
		while(s.hasNext()) {
			ArrayList<String> data = new ArrayList<>();
			String line = s.nextLine();
			while (line != "") {
				data.add(line);
				if (s.hasNextLine()) {
					line = s.nextLine();
				} else {
					break;
				}
			}
			int error;
			int matchind = -1;
			for (int k = 0; k<data.size(); k++) { // Compare each string to it's "mirror", character by character. If more than 1 error found or no errors found, go to next.
				error = 0;
				for (int i = 0; i<=k; i++) {
					if (k+1+i > data.size()-1) {
						break;
					}
					for (int j = 0; j<data.get(k).length(); j++) {
						if (!(data.get(k-i).charAt(j) == data.get(k+1+i).charAt(j))) {
							error++;
						}
					}
					if (error > 1) {
						break;
					}
				}
				if (error > 1 || error == 0) {
					continue;
				}
				matchind = k;
				break;
			}
			if (matchind != -1) {
				sum += (matchind+1)*100;
				continue;
			}
			for (int k = 0; k<data.get(0).length(); k++) { // Compare each string to it's "mirror", character by character. If more than 1 error found or no errors found, go to next.
				error = 0;
				for (int i = 0; i<=k; i++) {
					if (k+1+i > data.get(0).length()-1) {
						break;
					}
					for (int j = 0; j<data.size(); j++) {
						if (!(data.get(j).charAt(k-i) == data.get(j).charAt(k+1+i))) {
							error++;
						}
					}
					if (error > 1) {
						break;
					}
				}
				if (error > 1 || error == 0) {
					continue;
				}
				matchind = k;
				break;
			}
			if (matchind != -1) {
				sum += (matchind+1);
			} else {
				System.out.println("Error");
			}
		}
		System.out.println(sum);
	}
}

