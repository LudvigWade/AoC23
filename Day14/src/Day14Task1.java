import java.util.*;
import java.io.*;

public class Day14Task1 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = new Scanner(new File("data"));
		int datalength = 100;
		int sum = 0;
		char[][] data = new char[datalength][datalength];
		for (int i = 0; i<datalength; i++) {
			data[i] = s.nextLine().toCharArray();
		}
		
		for (int i = 1; i<datalength; i++) {
			for (int j = 0; j<datalength; j++) {
				if (data[i][j] == 'O') {
					if (data[i-1][j] == '.') {
						data[i-1][j] = 'O';
						data[i][j] = '.';
						i = Math.max(0, i-2);
						break;
					}
				}
			}
		}
		for (int i = 0; i<datalength; i++) {
			for (int j = 0; j<datalength; j++) {
				if (data[i][j] == 'O') {
					sum += datalength-i;
				}
			}
		}
		System.out.println(sum);
	}

}
