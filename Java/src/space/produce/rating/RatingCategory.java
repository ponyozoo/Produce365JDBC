package space.produce.rating;

public class RatingCategory {
	private String total = "정보 없음";
	private String vocal = "정보 없음";
	private String dance = "정보 없음";
	private String rap = "정보 없음";
	
	public RatingCategory() {}
	
	public RatingCategory(String total, String vocal, String dance, String rap) {
		this.total = total;
		this.vocal = vocal;
		this.dance = dance;
		this.rap = rap;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getVocal() {
		return vocal;
	}

	public void setVocal(String vocal) {
		this.vocal = vocal;
	}

	public String getDance() {
		return dance;
	}

	public void setDance(String dance) {
		this.dance = dance;
	}

	public String getRap() {
		return rap;
	}

	public void setRap(String rap) {
		this.rap = rap;
	}

	@Override
	public String toString() {
		return "➤ 종합 등급: " + total + " ➤ 보컬 등급: " + vocal + " ➤ 댄스 등급: " + dance + " ➤ 랩 등급: " + rap;
	}
}
