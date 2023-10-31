package Mid_03;

import java.io.*;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame {
	public Main(GradeBook gradeBook) {
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("덕성여대 화이팅");
		//setLayout(null);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel title = new JLabel("학생 등록하기");
		
		JLabel label1 = new JLabel("이름");
		JLabel label2 = new JLabel("학번");
		JLabel label3 = new JLabel("성적");
		JTextField field1 = new JTextField(15);
		JTextField field2 = new JTextField(15);
		JTextField field3 = new JTextField(15);
		JButton button1 = new JButton("등록하기");
		JButton button2 = new JButton("취소");
		
		button1.addActionListener(e -> {
			gradeBook.save(new Student(field1.getText(), Integer.parseInt(field2.getText()), Float.parseFloat(field3.getText())));
		});
			
		panel.add(title, "North");
		panel.add(label1);
		panel.add(field1);
		panel.add(label2);
		panel.add(field2);
		panel.add(label3);
		panel.add(field3);
		panel.add(button1);
		panel.add(button2);
		
		setVisible(true);
	}
	
	public static void main(String[] args) throws IOException{
		Scanner s = new Scanner(System.in);
		GradeBook gradeBook = new GradeBook();
		while(true) {
		System.out.println("번호를 입력하세요. (1. 학생 정보를 입력받고 저장, 2. 특정 학번을 가진 학생을 검색하여 학생 정보 화면 표시, 3. 모든 학생들의 정보 파일에 출력): ");
		int inputNum = s.nextInt();
		switch (inputNum) {
			case 1:
				Main f = new Main(gradeBook);
				break;
			case 2:
				System.out.print("검색하고 싶은 학생 ID를 입력 : ");
				int studentId = s.nextInt();
				gradeBook.getStdInfoById(studentId);
				break;
			case 3:
				PrintWriter in = new PrintWriter(new FileWriter("stu_out.txt"));
				gradeBook.writeStdInfo(in);
				break;
		}
		}
	}
}
