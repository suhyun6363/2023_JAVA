package Mid_03;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GradeBook extends IOException {
	private List<Student> studentList;
	float scoreSum = 0;
	float scoreAverage;
	
	public GradeBook() {
		studentList = new ArrayList<Student>();
	}
	
	public void save(Student std) {
		studentList.add(std);
	}
	
	public float getScoreAverage() {
		for (Student std : studentList) {
			scoreSum += std.getScore();
		}
		return scoreSum / studentList.size(); 
	}
	
	public void getStdInfoById(int studentId) {
		for(Student std : studentList) {
			if(std.getStudentId() == studentId) {
				System.out.println("이름: " + std.getName() + " 학번: " + std.getStudentId() + " 성적: " + std.getScore());
			}
		}
	}
	
	public void writeStdInfo(PrintWriter in) {
		int index = 1;
		in.println("- 전체 학생은 총 " + studentList.size() +"명이고, 학생들의 전체 평균 점수는 " + String.format("%.2f", getScoreAverage()) + "점입니다.");
		for (Student std : studentList)	{
			in.println(index + ". " + std.getName() + ", " + std.getStudentId() + ", " + std.getScore());
			in.flush();
			index++;
		}
		in.close();
	}
}
