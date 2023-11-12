package Week09_ex;

import java.awt.Font;

import javax.swing.*;

public class CountDownTest_exer extends JFrame{
	private JLabel label;
	
	Thread t;
	
	class Counter extends Thread {
		public void run() {
			for (int i = 0; i <= 100; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					return;
				}
				label.setText(i + "");
			}
		}
	}
	
	public CountDownTest_exer() {
		setTitle("카운트다운");
		setSize(400, 150);
		getContentPane().setLayout(null);
		
		label = new JLabel("0");
		label.setBounds(0, 0, 384, 111);
		label.setFont(new Font("Serif", Font.BOLD, 100));
		getContentPane().add(label);
		
		JButton btnNewButton = new JButton("카운트 중지");
		btnNewButton.setBounds(247, 25, 125, 23);
		btnNewButton.addActionListener(e -> t.interrupt());
		getContentPane().add(btnNewButton);
		setVisible(true);
		
		t = new Counter();
		t.start();
	}
	
	public static void main(String[] args) {
		CountDownTest_exer t = new CountDownTest_exer();
	}
}
