package space.produce.menu;

import java.util.Scanner;

import space.produce.util.MyScanner;

public class MainMenu {
	private MyScanner scanner = new MyScanner(new Scanner(System.in));
	private TraineeMenu traineeMenu = new TraineeMenu();
	private DebutMenu debutMenu = new DebutMenu();
	private CareMenu careMenu = new CareMenu();
	private LessonMenu lessonMenu = new LessonMenu();
	private HistoryMenu historyMenu = new HistoryMenu();
	
	public MainMenu() {
		System.out.println("""
		_____________________________________________________________________________________________________
		    ____      ____        __      _____     _     _      __      _____      __         ___      ____ 
		    /    )    /    )    /    )    /    )    /    /     /    )    /    '   /    )     /         /     
		---/____/----/___ /----/----/----/----/----/----/-----/---------/__-------- _ /-----/___------/___---
		  /         /    |    /    /    /    /    /    /     /         /              )    /    )         )  
		_/_________/_____|___(____/____/____/____(____/_____(____/____/____ ____(____/____(____/____(____/___
		
		기획사 인재 관리 시스템 : PRODUCE365                                                      By. 소연 민정 민서 상석
		""");
	}
	
	public void printMenu() {
		while (true) {
			System.out.println("""
					
			__________________________________________________🕺💃_________________________________________________
			
			                                       반갑습니다! 주메뉴를 선택해주세요.                                        
			
			                1. 연습생 관리    2. 데뷔조 관리    3. 매니지먼트 관리    4. 매니징 아카이브    5. 종료                 
			_______________________________________________________________________________________________________
					                
			""");
			
			int selectedNum = scanner.takeInt(1, 5);
			if (selectedNum == -1) {
				System.out.println("🚨 올바른 값을 입력해주세요");
				continue ;
			}
			
			switch (selectedNum) {
				case 1 :
					traineeMenu.selectTraineeMenu();
					break ;
				case 2 :
					debutMenu.selectDebutMenu();
					break ;
				case 3 :
					this.selectManageMenu();
					break ;
				case 4 :
					historyMenu.selectHistoryMenu();
					break ;
				case 5 :
					System.out.println("오늘도 수고하셨습니다 👋");
					return ;
			}
		}
	}
	
	public void selectManageMenu() {
		while (true) {
			System.out.println("""
					
			__________________________________________________🕺💃_________________________________________________
			
			                                             [ 매니지먼트 관리 ]
			
			                              1. 케어 관리       2. 수업 관리       3. 뒤로 가기                 
			_______________________________________________________________________________________________________
					                
			""");
			
			int selectedNum = scanner.takeInt(1, 3);
			if (selectedNum == -1) {
				System.out.println("🚨 올바른 값을 입력해주세요");
				continue ;
			}
			
			switch (selectedNum) {
				case 1 :
					careMenu.selectCareMenu();
					break ;
				case 2 :
					lessonMenu.selectLessonMenu();
					break ;
				case 3 :
					return ;
			}
		}
	}
}
