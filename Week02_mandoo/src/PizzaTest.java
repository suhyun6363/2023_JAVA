class Circle {
	protected int radius;
	public Circle(int r) {radius = r;};
};

class Pizza extends Circle {
	protected String pizza_type;
	
	public Pizza(String s, int r) {
		super(r);
		pizza_type = s;
	};
	
	public void print() {
		System.out.println("이 피자는 반지름이 " + radius +"인 " + pizza_type+ "피자입니다.");
	};
};

public class PizzaTest {
	public static void main(String args[]) {
		Pizza obj = new Pizza("Pepperoni", 20);
		obj.print();
	};
};
