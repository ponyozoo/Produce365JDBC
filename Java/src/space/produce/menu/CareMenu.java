package space.produce.menu;

import java.util.List;
import java.util.Scanner;

import space.produce.care.Care;
import space.produce.care.CareDAO;
import space.produce.care.JDBCCareDao;

public class CareMenu {

    public void readCare() {
        // JDBCCareDAO.selectAll 사용해서 받아온 list 한 줄씩 출력
    	CareDAO careDao = new JDBCCareDao(); 
       	
       	List<Care> cares = careDao.selectAll(); 
       	 
       	for ( int i = 0; i < cares.size(); i++ ) {
       		System.out.println((i+1) + "번: " + cares.get(i) );
       	}
    }

    public boolean addCare() {
    	
    	JDBCCareDao jdbcCareDao = new JDBCCareDao();
    	Care care = new Care(); 
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("종류를 입력하세요 : ");
    	care.setCategory(sc.nextLine());
    	
    	System.out.println("금액을 입력하세요 : ");
    	care.setCost(sc.nextInt());
    	
    	boolean rslt = jdbcCareDao.insert(care);
    	
    	if ( rslt ) {
    		System.out.println("CARE 정보가 등록되었습니다.");
    	} else {
    		System.out.println("CARE 정보가 등록되지 않았습니다.");
    	}
    	
    	sc.close();
    	
    	return rslt; 
    }

    public boolean deleteCare() {
    	JDBCCareDao jdbcCareDao = new JDBCCareDao();
    	CareDAO careDao = new JDBCCareDao(); 
    	Scanner sc = new Scanner(System.in);
    	
    	readCare();
    	
      	System.out.println("삭제하고 싶은 번호를 입력해주세요.");
      	int num = sc.nextInt();
      	
      	List<Care> cares = careDao.selectAll(); 
      	
      	int pickId = cares.get(num-1).getId();
      	
    	boolean rslt = jdbcCareDao.deleteById(pickId);
      	
      	if ( rslt ) {
    		System.out.println(num+"번 정보가 삭제되었습니다.");
    	} else {
    		System.out.println("CARE 정보가 삭제되지 않았습니다.");
    	}
    	
      	sc.close();
      	
    	return rslt;	
    }
}
