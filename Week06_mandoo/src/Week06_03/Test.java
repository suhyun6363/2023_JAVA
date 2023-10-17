package Week06_03;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

enum Type {
	MEAT, FISH, OTHER
}

public class Test {
	public static void main(String[] args) {
		List<Food> foodList = new ArrayList<Food>();
		
        Food food1 = new Food("스테이크", false, 400, Type.MEAT);
        Food food2 = new Food("연어", false, 250, Type.FISH);
        Food food3 = new Food("스파게티", true, 350, Type.OTHER);
        Food food4 = new Food("치킨", false, 300, Type.MEAT);
        Food food5 = new Food("참치 샌드위치", false, 200, Type.FISH);
        Food food6 = new Food("피자", false, 500, Type.OTHER);
        Food food7 = new Food("브로콜리", true, 55, Type.OTHER);
        Food food8 = new Food("돼지고기", false, 350, Type.MEAT);
        
        foodList.add(food1);
        foodList.add(food2);
        foodList.add(food3);
        foodList.add(food4);
        foodList.add(food5);
        foodList.add(food6);
        foodList.add(food7);
        foodList.add(food8);
        
        List<String> foodNameList = foodList.stream()
        		.filter(food -> food.getCalories() <= 300)
        		.map(food -> food.name)
        		.collect(Collectors.toList());
        
       System.out.println(foodNameList);
	}
}