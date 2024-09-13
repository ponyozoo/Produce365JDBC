package space.produce.menu;

import java.util.List;
import java.util.Scanner;

import space.produce.care.Care;
import space.produce.care.CareDAO;
import space.produce.care.JDBCCareDao;
import space.produce.lesson.Lesson;
import space.produce.util.MyScanner;

public class CareMenu {
	
	private CareDAO dao = new JDBCCareDao();
	private MyScanner scanner = new MyScanner(new Scanner(System.in));
	

	public void selectCareMenu() {
		while (true) {
			System.out.println("메뉴를 선택해주세요 : 1. 연습생 케어 정보 조회 2. 연습생 케어 정보 추가 3. 연습생 케어 정보 삭제 4. 뒤로 가기");
			int selectMenu = scanner.takeInt(1, 6);

			if (selectMenu == -1) {
				// -1을 반환하면= "올바른 값을 입력해주세요" 를 출력하고 switch문을 실행하지 않고 반복문을 재실행.
				System.out.println("🚨 올바른 값을 입력해주세요");
				continue;
			}
			switch (selectMenu) {
				case 1: {
					readCare();
					break;
				}
				case 2: {
					addCare();
					break;
				}
				case 3: {
					deleteCare();
					break;
				}
				case 4: {
					return;
				}
			}

		}

	}
	
    public void readCare() {
        List<Care> cares = dao.selectAll(); 
       	 
       	for ( int i = 0; i < cares.size(); i++ ) {
       		System.out.println((i+1) + "번: " + cares.get(i) );
       	}
    }

    public void addCare() {
    	System.out.println("종류를 입력하세요 : ");
    	String category = scanner.takeStr();
    	
    	while (true) {
    		System.out.println("금액을 입력하세요 : ");
    		int cost = scanner.takeInt(0, Integer.MAX_VALUE);
    		
    		if (cost != -1) {
    			if ( dao.insert(new Care(0, category, cost)) ) {
    				System.out.println("✔️ 등록 완료");
    			} else {
    				System.out.println("❌ 등록 실패");
    			}
    			break;
    		} 
    		System.out.println("🚨 올바른 값을 입력해주세요");
    	}
    }

    public void deleteCare() {
    	
    	List<Care> cares = dao.selectAll();
    	
    	for (int i = 0; i < cares.size(); i++) {
    		System.out.println((i + 1) + ". " + cares.get(i));
    	}
    	
    	while (true) {
    		System.out.println("삭제하고 싶은 CARE 번호를 입력해주세요.");
    		int num = scanner.takeInt(1, cares.size());
    		if (num != -1) {
    			if (dao.deleteById(cares.get(num -1).getId())) {
    				System.out.println("✔️ 삭제 완료");
    			} else {
    				System.out.println("❌ 삭제 실패");
    			}
    			break;
    		}
    		System.out.println("🚨 올바른 값을 입력해주세요");	
    	}
    }
}
