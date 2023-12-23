// Score Manager class

import java.awt.Graphics;
import java.awt.Color;

public class ScoreManager {
	public int score;
	
	public ScoreManager() {
		score = 0;
	}
	
	public void plusScore(int s) {
		score += s;
	}

	public void minusScore(int s) {
		score -= s;
	}
	
	public int getScore() {
		return score;
	}

	public void printScore(Graphics g) {
		String str = "점수 : " + score;
		g.setColor(Color.black);
		g.drawString(str, 450, 70);
	}
}