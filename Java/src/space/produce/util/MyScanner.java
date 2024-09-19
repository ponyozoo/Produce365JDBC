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
	
	public int takeIntCycle(String str, int min, int max) {
		while (true) {
			System.out.print(str);
			
			int num;
			try {
				num = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				num = -1;
			}
			
			if (num < min || num > max || num == -1)
				System.out.println("🚨 올바른 값을 입력해주세요\n");
			else
				return num;	
		}
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
	
	public String takeStrCycle(String str) {
		while (true) {
			System.out.print(str);
			
			String res;
			try {
				res = scanner.nextLine();
			} catch (Exception e) {
				res = "";
			}
			
			if (res.equals(""))
				System.out.println("🚨 올바른 값을 입력해주세요\n");
			else
				return res;
		}
	}
	
	public void closeScanner() {
		scanner.close();
	}
}
