package space.produce.careHistory;

import java.sql.Date;

import space.produce.care.Care;
import space.produce.trainee.Trainee;

public class CareHistory {
	/*
	 * 1. 컬럼 변수화. 컬럼명은 DB와 똑같이하는 데 소문자 캐멀 스타일로.언더바X(private) 
	 * 2. set&get 
	 * 3. 생성자(디폴트, 컬럼 전부 밭는 것) 
	 * 4. toString 
	 * 5. varchar2는 String형, number는 int형으로. date형은 임포트(java.sql.Date)
	 * 6. Trainee 형 traineeId(Trainee클래스 임포트)
	 */
	
	//1. 컬럼 변수화.
	private int idx;
	private Date careDate;
	private Care care;
	private Trainee trainee;
	
	
	//2. set&get 
	public int getIdx() {
		return idx;
	}
	
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public Date getCareDate() {
		return careDate;
	}
	
	public void setCareDate(Date careDate) {
		this.careDate = careDate;
	}
	
	public Care getCare() {
		return care;
	}
	
	public void setCare(Care care) {
		this.care = care;
	}
	
	public Trainee getTrainee() {
		return trainee;
	}
	
	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}
	
	
	//3. 생성자(디폴트, 컬럼 전부 밭는 것) 
	public CareHistory() {}
	
	public CareHistory(int idx, Date careDate, Care care, Trainee trainee) {
		super();
		this.idx = idx;
		this.careDate = careDate;
		this.care = care;
		this.trainee = trainee;
	}
	
	
	//4. toString 
	@Override
	public String toString() {
		return "➤ 날짜: " + this.careDate + " ➤ 종류: " + this.care.getCategory() + " ➤ 연습생: " + this.trainee.getName();

	}
	
}
