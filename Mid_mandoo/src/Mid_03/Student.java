package Mid_03;

public class Student {
	String name;
	int studentId;
	float score;
	
	public Student(String name, int studentId, float score) {
		this.name = name;
		this.studentId = studentId;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public float getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
