package Week03_03;

import javax.swing.*;
import java.awt.*;

import java.awt.BorderLayout;

public class CarProgram extends JFrame{
	private JPanel panel, imgPanel;
	private Image carImage;
	private JLabel label;
	private JButton leftBtn, rightBtn;
	int img_x = 180, img_y = 0;
	
	
	public CarProgram() {
		setSize(600, 300);
		setTitle("Car");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		
        // Toolkit을 사용하여 이미지 파일을 로드합니다.
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        carImage = toolkit.getImage("car.png");

        // 이미지가 로드될 때까지 기다립니다.
        MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(carImage, 0);

        try {
            mediaTracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ImageObserver를 구현하는 더미 컴포넌트를 생성합니다.
        Component imageObserverComponent = new Component() {};

        // 이미지를 화면에 표시하기 위한 JPanel을 생성합니다.
        imgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // 이미지를 그립니다.
                g.drawImage(carImage, img_x, img_y, imageObserverComponent);
            }
        };
		
		leftBtn = new JButton("LEFT");
		leftBtn.addActionListener(e -> {
			img_x -= 10;
			repaint();
		});
		
		rightBtn = new JButton("RIGHT");
		rightBtn.addActionListener(e -> {
			img_x += 10;
			repaint();
		});
		
		leftBtn.setLocation(200, 270);
		leftBtn.setSize(10, 10);
		
		panel.add(leftBtn);
		panel.add(rightBtn);
		
		add(imgPanel, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
				
		setVisible(true);
	}
	
	public static void main(String argv[]) {
		CarProgram c = new CarProgram();
	}
}

