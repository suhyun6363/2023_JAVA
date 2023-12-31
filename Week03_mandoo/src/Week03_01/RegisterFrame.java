package Week03_01;

import javax.swing.*;

public class RegisterFrame extends JFrame{
	public RegisterFrame () {
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Register Frame");
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel title = new JLabel("회원 등록하기");
		
		JLabel label1 = new JLabel("이름");
		JLabel label2 = new JLabel("패스워드");
		JLabel label3 = new JLabel("이메일 주소");
		JLabel label4 = new JLabel("전화번호");
		JTextField field1 = new JTextField(15);
		JPasswordField field2 = new JPasswordField(15);
		JTextField field3 = new JTextField(15);
		JTextField field4 = new JTextField(15);
		JButton button1 = new JButton("등록하기");
		JButton button2 = new JButton("취소");
		
		panel.add(title, "North");
		panel.add(label1);
		panel.add(field1);
		panel.add(label2);
		panel.add(field2);
		panel.add(label3);
		panel.add(field3);
		panel.add(label4);
		panel.add(field4);
		panel.add(button1);
		panel.add(button2);
		
		setVisible(true);
	}
	
	public static void main(String argv[]) {
		RegisterFrame f = new RegisterFrame();
	}
}
