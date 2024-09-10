package space.produce.lesson;

public class Lesson {
	/*1. 컬럼 변수화(private). 컬럼명은 DB와 똑같이하는 데 소문자 캐멀 스타일로.언더바X
	 *2. set&get
	 *3. 생성자(디폴트, 컬럼 전부 밭는 것)
	 *4. toString
	 *5. varchar2는 String, number는 int
	 */
	
	//1. 컬럼 변수화
	private int id;
	private String trainer;
	private String subject;
	private float time;
	
	//2. set&get
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTrainer() {
		return trainer;
	}
	
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public float getTime() {
		return time;
	}
	
	public void setTime(float time) {
		this.time = time;
	}
	
	
	//3. 생성자(디폴트, 컬럼 전부 밭는 것)
	public Lesson() {}
	
	public Lesson(int id) {
		this.id = id;
	}
	
	public Lesson(int id, String trainer, String subject, float time) {
		this.id = id;
		this.trainer = trainer;
		this.subject = subject;
		this.time = time;
	}
	
	
	//4. toString
	@Override
	public String toString() {
		return "Lesson [id=" + id + ", trainer=" + trainer + ", subject=" + subject + ", time=" + time + "]";
	}

}
