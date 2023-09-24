package Week03_02;

import javax.swing.*;

public class Converter extends JFrame{
	public Converter() {
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Mile->Km");
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel label1 = new JLabel("마일을 입력하시오");
		JTextField field1 = new JTextField(15);
		JLabel label2 = new JLabel("->");
		JTextField field2 = new JTextField(15);
		JButton button = new JButton("변환");
		
		button.addActionListener(e -> {
			String mileStr = field1.getText();
			Double mile = Double.valueOf(mileStr);
			Double km = mile * 1.609344;
			field2.setText(km + "km");
		} );
		
		panel.add(label1);
		panel.add(field1);
		panel.add(label2);
		panel.add(field2);
		panel.add(button);
		
		setVisible(true);
	}
	
	public static void main(String argv[]) {
		Converter c = new Converter();
	}
}
