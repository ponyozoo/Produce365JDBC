package space.produce.util;

import java.util.Scanner;

public class MyScanner {
	private Scanner scanner;
	
	public MyScanner (Scanner scanner) {
		this.scanner = scanner;
	}
	
	public int takeInt(int min, int max) {
		int num;
		
		try {
			num = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			return -1;
		}
		
		return num >= min && num <= max ? num : -1;
	}
	
	public String takeStr() {
		String str;
		
		try {
			str = scanner.nextLine();
		} catch (Exception e) {
			return "";
		}
		
		return str;
	}
	
	public void closeScanner() {
		scanner.close();
	}
}
