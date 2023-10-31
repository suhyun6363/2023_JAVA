package Mid_01;

import javax.swing.*;

public class StudentRegister extends JFrame {
	public StudentRegister() {
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
			System.out.println("이름: " + field1.getText() + " 학번: " + field2.getText() + " 성적: " + field3.getText());
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
	
	public static void main(String argv[]) {
		StudentRegister f = new StudentRegister();
	}
}
