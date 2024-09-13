package space.produce.menu;

import java.util.List;
import java.util.Scanner;

import space.produce.care.Care;
import space.produce.care.CareDAO;
import space.produce.care.JDBCCareDao;
import space.produce.util.MyScanner;

public class CareMenu {
	
	private CareDAO dao = new JDBCCareDao();
	private MyScanner scanner = new MyScanner(new Scanner(System.in));
	

	public void selectCareMenu() {
		while (true) {
			System.out.println("""
					
			__________________________________________________🕺💃_________________________________________________
			
			                                               [ 케어 관리 ]
			
			                   1. 전체 케어 정보 조회    2. 케어 정보 추가    3. 케어 정보 삭제    4. 뒤로 가기            
			_______________________________________________________________________________________________________
					                
			""");
			
			int selectMenu = scanner.takeInt(1, 4);
			if (selectMenu == -1) {
				System.out.println("🚨 올바른 값을 입력해주세요");
				continue;
			}
			
			System.out.println("");			
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
       		System.out.println(cares.get(i));
       	}
    }

    public void addCare() {
    	String category;
    	while (true) {
    		System.out.print("케어 종류를 입력해주세요 : ");
    		category = scanner.takeStr();
    		
    		if (category != "")
    			break ;
    		System.out.println("🚨 올바른 값을 입력해주세요\n");
    	}
    	
    	while (true) {
    		System.out.print("금액을 입력해주세요 : ");
    		int cost = scanner.takeInt(0, Integer.MAX_VALUE);
    		
    		if (cost != -1) {
    			if ( dao.insert(new Care(0, category, cost)) ) {
    				System.out.println("✔️ 등록 완료");
    			} else {
    				System.out.println("❌ 등록 실패");
    			}
    			break;
    		} 
    		System.out.println("🚨 올바른 값을 입력해주세요\n");
    	}
    }

    public void deleteCare() {
    	
    	List<Care> cares = dao.selectAll();
    	
    	for (int i = 0; i < cares.size(); i++) {
    		System.out.println((i + 1) + ". " + cares.get(i));
    	}
    	
    	while (true) {
    		System.out.print("\n삭제할 케어 정보를 선택해주세요 : ");
    		int num = scanner.takeInt(1, cares.size());
    		if (num != -1) {
    			if (dao.deleteById(cares.get(num -1).getId())) {
    				System.out.println("✔️ 삭제 완료");
    			} else {
    				System.out.println("❌ 삭제 실패");
    			}
    			break;
    		}
    		System.out.println("🚨 올바른 값을 입력해주세요\n");	
    	}
    }
}
