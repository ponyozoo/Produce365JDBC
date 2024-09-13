package space.produce.util;

import java.util.Random;

public class Util {

	public int generateRandomNumber() {
		Random random = new Random();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			sb.append(random.nextInt(10));
		} 
		
		return Integer.parseInt(sb.toString());
	}
	
	public String checkGrade(String grade) {
		String rslt = grade.toUpperCase(); 
		boolean isBoolean = false; 
		
		switch ( rslt ) {
			case "A": 
				isBoolean = true; 
				break;
			case "B": 
				isBoolean = true; 
				break;
			case "C": 
				isBoolean = true; 
				break;
			case "D": 
				isBoolean = true; 
				break;
			case "E": 
				isBoolean = true; 
				break;
			case "F": 
				isBoolean = true; 
				break;
			default: 
				break; 
		}
		
		if (!isBoolean) {
			rslt = ""; 
		}
		
		return rslt; 
	}

}
