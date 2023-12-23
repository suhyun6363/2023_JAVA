//=========================================================
//	디스카드 파일에서 테이블 파일로 카드 이동 5점
//	테이블 파일 간 카드 이동 5점
//	디스카드 파일이나 테이블 파일에서 수트 파일로 카드 이동 10점
//	단, 테이블 파일 간 카드 이동에서 의미 없는 카드 이동엔 카드를 이동시키지 않고 콘솔창 메세지 출력
//	테이블 파일 간 그룹 이동은 이동 카드 개수만큼 점수 부여
// 	테이블의 각 파일 제일 위 카드 위에 겹쳐지는 카드는 색깔이 다르고, 숫자는 1 작은 카드만 올라갈 수 있다.
//	Thread를 이용해서 10초에 2점씩 감점
//========================================================

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import java.util.Random;
import java.util.Stack;

import javax.swing.*;

// 52장 카드더미
class DeckPile extends CardPile {

	DeckPile(int x, int y) {
		// 부모 생성자 실행
		super(x, y);
		// 52장의 카드 생성, thePile에 저장
		for(int i = 0; i < 4; i++)
			for (int j = 0; j <= 12; j++)
				addCard(new Card(0, 0, i, j));	
		shuffle();
	}
	
	// 카드 섞기
	public void shuffle() {
		Random generator = new Random();
		for(int i = 0; i < 52; i++) {
			int j = Math.abs(generator.nextInt() % 52);
			// 카드 치환
			Card tempCard = thePile[i];
			thePile[i] = thePile[j];
			thePile[j] = tempCard;
		}
	}
	
	// 데크파일 소진시 디스카드 파일 카드 재삽입
	public void select(int tx, int ty) {
		if(count == 0) {
			int cardCount = GameAppPanel.discardPile.count;
			for(int i = 0; i < cardCount; i++) {
				Card c = GameAppPanel.discardPile.topCard();
				c.faceup = false;
				this.addCard(c);
			}
		}
		else {
			Card tempCard = topCard();
			tempCard.flip();
			GameAppPanel.discardPile.addCard(tempCard);
		}
	}
}

class SuitPile extends CardPile {

	SuitPile(int x, int y) {
		super(x, y);
	}

	// 카드더미 위에 올릴 수 있는지 판단하는 메소드
	public boolean canTake(Card aCard) {		
		if(count == 0)
			return aCard.rank == 0;
		Card tempCard = thePile[count-1];
		return (aCard.suit == tempCard.suit) &&
				(aCard.rank == tempCard.rank+1);
	}
}

class DiscardPile extends CardPile {
	
	DiscardPile(int x, int y) {
		super(x, y);
	}

	public void addCard(Card aCard) {
		if(!aCard.faceup)
			aCard.flip();
		super.addCard(aCard);
	}
	
	public void select(int tx, int ty) {
		if(count == 0)
			return;
		// 제일 위 카드를 빼서 이 카드를 수트 파일이 받을 수 있는지 조사
		Card tempCard = topCard();
		for(int i = 0; i < 4; i++) {
			if(GameAppPanel.suitPile[i].canTake(tempCard)) {
				GameAppPanel.scoreManager.plusScore(10);
				GameAppPanel.suitPile[i].addCard(tempCard);
				return;
			}
		}
		// 테이블 파일이 받을 수 있는지 조사
		for(int i = 0; i < 7; i++) {
			if(GameAppPanel.tablePile[i].canTake(tempCard)) {
				GameAppPanel.scoreManager.plusScore(5);
				GameAppPanel.tablePile[i].addCard(tempCard);
				return;
			}
		}
		// 받을 파일이 없으면 카드를 다시 디스카드 파일에 삽입
		addCard(tempCard);
	}
}

class TablePile extends CardPile {

	TablePile(int x, int y, int c) {
		super(x, y);
		// 테이블 파일에 카드 배분
		for(int i = 0; i < c; i++)
			addCard(GameAppPanel.deckPile.topCard());
		// 제일 위 카드를 뒤집어서 앞면으로 보이게 함
		thePile[count-1].flip();
	}

	public boolean canTake(Card aCard) {
		if(count == 0)
			return aCard.rank == 12;
		Card tempCard = thePile[count-1];	
		return (aCard.color() != tempCard.color()) &&
			(aCard.rank == tempCard.rank-1) && tempCard.faceup;		// 제일 위 카드 위에 색깔이 다르고 숫자가 1 작은 카드가 위에 올라올 수 있음
	}

	public boolean includes(int tx, int ty) {
		// 테이블 파일이 소유한 모든 카드들이 차지하는 영역 내인지 판단
		return x <= tx && tx <= x + Card.cardWidth && y <= ty;
	}

	// 카드가 선택된 경우
	public void select(int tx, int ty) {
		if(count == 0)
			return;
		// 제일 위 카드가 클릭됐으면
		if(thePile[count-1].includes(tx, ty)) {
			// 제일 위 카드가 뒷면이면 앞면으로
			Card tempCard = thePile[count-1];
			if(! tempCard.faceup) {
				tempCard.flip();
				return;
			}
			// 제일 위 카드가 앞면이면 이 카드를 수트 파일이 받을 수 있는지 조사
			for(int i = 0; i < 4; i++)
				if(GameAppPanel.suitPile[i].canTake(tempCard)) {
					// App7
					GameAppPanel.scoreManager.plusScore(10);
					GameAppPanel.suitPile[i].addCard(topCard());
					return;
				}
			// 테이블 파일이 받을 수 있는지 조사
			for(int i = 0; i < 7; i++)
				if(GameAppPanel.tablePile[i].canTake(tempCard)) {
					// 의미 없는 이동 경우 콘솔 출력
					if(count >= 2 && thePile[count-2].faceup) {
						System.out.println("moving the card is meaningless!");
						return;
					}
					else if(count == 1 && tempCard.rank == 12) {
						System.out.println("moving the card is meaningless!");
						return;
					}
					else { // 의미 있다면 다른 테이블 파일 제일 위로 이동
						GameAppPanel.scoreManager.plusScore(5);
						GameAppPanel.tablePile[i].addCard(topCard());
					}
					return;
				}
		}
		// 테이블 파일의 카드 그룹이 선택되었을때,
		else {	
			for(int k =	count; k > 0; k--) {
				if(thePile[k-1].includes(tx, ty) && thePile[k-1].faceup) {
					for(int i = 6; i <= 12; i++) {
						if(GameAppPanel.allPiles[i].canTake(thePile[k-1])) {
							int cnt = count;
							if(k-2 >= 0 &&  thePile[k-2].faceup) {
								System.out.println("moving the " + Integer.toString(cnt - k + 1) +" cards is meaningless!");
								return;
							}
							else if(k-1 == 0 && thePile[k-1].rank == 12) {
								System.out.println("moving the " + Integer.toString(cnt - k + 1) +" cards is meaningless!");
								return;
							}
							// 이동 카드 개수만큼 점수 부여
							else {
								GameAppPanel.scoreManager.plusScore(cnt - k + 1);
								Stack<Card> cardStack = new Stack<Card>();
								for(int j = cnt; j > k-1; j--)
									cardStack.push(topCard());
								for(int m = cnt; m > k-1; m--)
									GameAppPanel.allPiles[i].addCard(cardStack.pop());
							}
							break;
						}
					}
					break;
				}
			}
		}
	}

	public void display(Graphics g) {
		int localy = y;
		for(int i = 0; i < count; i++) {
			thePile[i].moveTo(x, localy);
			thePile[i].draw(g);
			localy += 35;
		}
	}
}

public class GameApp extends JFrame {
	public static GameAppPanel panel;
	
	public static void main(String [ ] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		GameApp window = new GameApp();
		window.setVisible(true);
	}
	
	public GameApp() {
		setSize(600, 500);
		setTitle("Card Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new GameAppPanel();
		setContentPane(panel);
	}

}

class GameAppPanel extends JPanel {
	public static DeckPile deckPile;
	public static DiscardPile discardPile;
	public static TablePile tablePile[ ];
	public static SuitPile suitPile[ ];
	public static CardPile allPiles[ ];
	public static ScoreManager scoreManager;
	public JLabel scoreLabel;

	public GameAppPanel() {
		setBackground(Color.white);
		
		MouseKeeper mouse = new MouseKeeper();
		addMouseListener(mouse);
		init();
		scoreManager = new ScoreManager();
		scoreLabel = new JLabel("점수: " + scoreManager.getScore());
		add(scoreLabel);
		// 10초당 2점씩 감점
		Thread timerThread = new Thread(() -> {
			while(true) {
				try {
					Thread.sleep(10000);
				}
				catch(InterruptedException e) {
					return;
				}
				scoreManager.minusScore(2);
				GameApp.panel.repaint();
			}
		});
		timerThread.start();
	}

	public void init() {
		// 배열 할당
		allPiles = new CardPile[13];
		suitPile = new SuitPile[4];
		tablePile = new TablePile[7];
		// 배열 채우기
		allPiles[0] = deckPile = new DeckPile(15, 50);
		allPiles[1] = discardPile = new DiscardPile(82, 50);
		for(int i = 0; i < 4; i++)
			allPiles[2+i] = suitPile[i] =
				new SuitPile(160 + (Card.cardWidth+10) * i, 50);
		for(int i = 0; i < 7; i++)
			allPiles[6+i] = tablePile[i] =
				new TablePile(15 + (Card.cardWidth+5) * i,
									Card.cardHeight + 60, i+1); 
	}

	private class MouseKeeper extends MouseAdapter {

		public void mousePressed(MouseEvent e) { 
			int x = e.getX();
			int y = e.getY();
			for(int i = 0; i < 13; i++) {
				if(allPiles[i].includes(x, y)) {
					allPiles[i].select(x, y);
					repaint();
					break;
				}
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < 13; i++) 
			allPiles[i].display(g);
		scoreLabel.setText("점수: " + scoreManager.getScore());
	}
}