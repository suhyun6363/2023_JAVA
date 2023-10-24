package Week07_02;

import java.io.*;
import java.util.Scanner;

public class UserInform {
	public static void main(String[] args) throws IOException{
		int num2;
		String search;
		String num, name, tel, email;
		//Scanner scan= null;
		PrintWriter in = new PrintWriter(new FileWriter("user.txt"));
		Scanner s = new Scanner(System.in);
		
		while(true) {
			System.out.print("번호 : ");
			num = s.next();
			System.out.print("이름 : ");
			name = s.next();
			System.out.print("전화번호 : ");
			tel = s.next();
			System.out.print("이메일 : ");
			email = s.next();
			in.println(num + "," + name + "," + tel + "," + email);
			
			System.out.println("입력을 끝났으면 1, 계속하려면 0: ");
			num2 = s.nextInt();
			if(num2 == 1) {
				s.close();
				in.close();
				break;
			}
		}
	}
}
