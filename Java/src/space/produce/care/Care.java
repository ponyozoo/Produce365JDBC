package space.produce.care;

public class Care {
	/*
	 * 1. 컬럼 변수화. 컬럼명은 DB와 똑같이하는 데 소문자 캐멀 스타일로.언더바X(private) 
	 * 2. set&get 
	 * 3. 생성자(디폴트, 컬럼 전부 밭는 것) 
	 * 4. toString 
	 * 5. varchar2는 String형, number는 int형으로.
	 */

	// 1. 컬럼 변수화
	private int id;
	private String category;
	private int cost;

	// 2. set&get
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}

	// 3. 생성자(디폴트, 컬럼 전부 밭는 것)
	public Care() {}

	public Care(String category) {
		this.category = category; 
	}
	
	public Care(int id, String category, int cost) {
		this.id = id;
		this.category = category;
		this.cost = cost;
	}

	//4. toString
	@Override
	public String toString() {
		return "➤ 케어 종류: " + this.category + " ➤ 가격: " + this.cost;
	}
}
