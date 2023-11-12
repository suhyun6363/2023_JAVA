package Week09_ex;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

public class CarGame extends JFrame{
	class MyThread extends Thread {
		private JLabel label;
		private int x, y;
		private boolean reachedFinishLine = false;
		private static int finishOrder = 1;
		
		public MyThread(String fname, int x, int y) {
			this.x = x;
			this.y = y;
			label = new JLabel();
			label.setIcon(new ImageIcon(fname));
			label.setBounds(x, y, 100, 100);
			add(label);
		}
		
		public void run() {
			for (int i = 0; i < 200; i++) {
				x += 10 * Math.random();
				label.setBounds(x, y, 100, 100);
				repaint();
				
				if (x >= 500 && !reachedFinishLine) {
                    synchronized (MyThread.class) {
                        System.out.println(Thread.currentThread().getName() + " 스레드가 " + finishOrder + "등으로 들어왔습니다.");
                        finishOrder++;
                        reachedFinishLine = true;
                    }
                }
                
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public CarGame() {
		setTitle("CarRace");
		setSize(600, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 600, 350);
		
		MyThread t1 = new MyThread("car1.gif", 100, 0);
		MyThread t2 = new MyThread("car2.gif", 100, 50);
		MyThread t3 = new MyThread("car3.gif", 100, 100);
		MyThread t4 = new MyThread("car2.gif", 100, 150);
		MyThread t5 = new MyThread("car3.gif", 100, 200);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
			
		// 결승선 생성
		JLabel finishLine = new JLabel();
        finishLine.setBounds(500, 0, 2, 300);
        finishLine.setOpaque(true);
        finishLine.setBackground(Color.red);
        add(finishLine);

		setVisible(true);
	}
	
	public static void main(String[] args) {        
		CarGame t = new CarGame();
	}
}
