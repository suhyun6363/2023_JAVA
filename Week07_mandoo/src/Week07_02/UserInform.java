package Week07_02;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInform {
	public static void main(String[] args) throws IOException{
		int num2;
		String search;
		//Scanner scan= null;
		PrintWriter outputStream = new PrintWriter(new FileWriter("user.txt"));
		Scanner s = new Scanner(System.in);
		List<User> userList = new ArrayList<User>();
		
		while(true) {
			User newUser = new User();
			
			System.out.print("번호 : ");
			newUser.setNum(s.nextInt());
			
			System.out.print("이름 : ");
			newUser.setName(s.next());
			
			System.out.print("전화번호 : ");
			newUser.setTel(s.next());
			
			System.out.print("이메일 : ");
			newUser.setEmail(s.next());
			
			userList.add(newUser);
			
			outputStream.println(newUser.getNum() + "," + newUser.getName() + "," + newUser.getTel() + "," + newUser.getEmail());
			outputStream.flush();
			
			System.out.println("입력이 끝났으면 0, 계속하려면 1: ");
			num2 = s.nextInt();
			if(num2 == 0) {
				break;
			}
		}
		
		System.out.println("모든 사용자 정보 입력이 완료 되었습니다.");
		System.out.print("검색하고 싶은 사용자 번호를 입력 : ");
		int userNum = s.nextInt();
		
		for(User userInfo : userList) {
			if(userInfo.getNum() == userNum) {
				System.out.println("해당 사용자 전화번호 : " + userInfo.getTel());
			}
		}
	}
}
