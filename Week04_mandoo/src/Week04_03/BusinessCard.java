package Week04_03;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

class MyFrame extends JFrame {
    public MyFrame() {
        setTitle("Business Card");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BusinessCardPanel cardPanel = new BusinessCardPanel();
        add(cardPanel);

        setVisible(true);
    }
}

class BusinessCardPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        // 텍스트
        g.drawString("김덕성", 300, 120);
        g.drawString("프로젝트 매니저", 300, 150);
        g.drawString("덕성주식회사", 300, 180);
        
        // 사진
        ImageIcon icon = new ImageIcon("cat.png");
        Image img = icon.getImage();
        g.drawImage(img, 20, 50, null);
    }
}

public class BusinessCard {
	public static void main(String[] args) {
		MyFrame f = new MyFrame();
	}
}
