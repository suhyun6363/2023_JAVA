//	CardPile class

import java.awt.Color;
import java.awt.Graphics;

public class CardPile {
	final static int MAXCARDS = 52;
	public int x;
	public int y;
	public Card thePile[];
	public int count;

	// 생성자
	public CardPile(int xl, int yl) {
		x = xl; y = yl;
		thePile = new Card[MAXCARDS];
		count = 0;
	}
	
	// 카드 더미에 카드 추가
	public void addCard(Card aCard) {
		if(count < MAXCARDS) {
			thePile[count] = aCard;
			aCard.moveTo(x, y);
			count = count + 1;
		}
	}
	
	public Card topCard() {
		if(count > 0) {
			count = count - 1;
			return thePile[count];
		}
		return null;
	}

	public boolean includes(int tx, int ty) {
		return x <= tx && tx <= x + Card.cardWidth &&
			y <= ty && ty <= y + Card.cardHeight;
	}
	
	public void select(int tx, int ty) {
		// do nothing
	}

	public void display(Graphics g) {
		g.setColor(Color.orange);
		if(count == 0)
			g.drawRect(x, y, Card.cardWidth, Card.cardHeight);
		else
			thePile[count-1].draw(g);
	}

	public boolean canTake(Card aCard) {
		return false; 
	}
}