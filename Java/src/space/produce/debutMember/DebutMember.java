package space.produce.debutMember;

import space.produce.debut.Debut;
import space.produce.trainee.Trainee;

public class DebutMember {
	private int idx;
	private Debut group;
	private Trainee trainee;

	public DebutMember() {}

	public DebutMember(int idx) {
		this.idx = idx;
	}

	public DebutMember(int idx, Debut group, Trainee trainee) {
		this.idx = idx;
		this.group = group;
		this.trainee = trainee;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public Debut getGroup() {
		return group;
	}

	public void setGroup(Debut group) {
		this.group = group;
	}

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	@Override
	public String toString() {
		return "DebutMember [idx=" + idx + ", group=" + group + ", trainee=" + trainee + "]";
	}
}