package space.produce.lessonHistory;

import java.sql.Date;

import space.produce.lesson.Lesson;
import space.produce.trainee.Trainee;

public class LessonHistory {
	/*
	 * 1. 컬럼 변수화(private). 컬럼명은 DB와 똑같이, 캐멀 스타일.언더바X.
	 * 	  fk변수들은 참조한 테이블 클래스 형으로 만들고 임포트.(Lesson lessonId, Trainee traineeId)
	 * 	  Date 임포트(java.sql.Date)
	 * 2. set&get 
	 * 3. 생성자(디폴트, 컬럼 전부 밭는 것) 
	 * 4. toString 
	 * 5. varchar2는 String형, number는 int형으로.
	 */
	
	//1. 컬럼 변수화(private)
	private int id;
	private Date lessonDate;
	private Lesson lessonId;
	private Trainee traineeId;
	
	
	//2. set&get 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getLessonDate() {
		return lessonDate;
	}
	public void setLessonDate(Date lessonDate) {
		this.lessonDate = lessonDate;
	}
	public Lesson getLessonId() {
		return lessonId;
	}
	public void setLessonId(Lesson lessonId) {
		this.lessonId = lessonId;
	}
	public Trainee getTraineeId() {
		return traineeId;
	}
	public void setTraineeId(Trainee traineeId) {
		this.traineeId = traineeId;
	}
	
	
	//3. 생성자(디폴트, 컬럼 전부 밭는 것) 
	public LessonHistory() {
		
	}
	public LessonHistory(int id, Date lessonDate, Lesson lessonId, Trainee traineeId) {
		super();
		this.id = id;
		this.lessonDate = lessonDate;
		this.lessonId = lessonId;
		this.traineeId = traineeId;
	}
	
	
	//4. toString 
	@Override
	public String toString() {
		return "LessonHistory [id=" + id + ", lessonDate=" + lessonDate + ", lessonId=" + lessonId + ", traineeId="
				+ traineeId + "]";
	}
	 
	
}
