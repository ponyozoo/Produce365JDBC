package space.produce.rating;

import space.produce.trainee.Trainee;

public class Rating {

	private String category;
	private Trainee trainee; // TRAINEE TABLE ->TRAINEE_ID NUMBER REFERENCES TRAINEE(ID)의미 , 나중에 객체를 불러와야 한다
	private String grade;

	Rating() {}

	public Rating(String category, Trainee trainee, String grade) {
		this.category = category;
		this.trainee = trainee;
		this.grade = grade;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Rating [category=" + category + ", trainee=" + trainee + ", grade=" + grade + "]";
	}

}
