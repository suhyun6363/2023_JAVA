package Week07_02;

import java.io.*;
import java.util.Scanner;

public class UserInform_ver2 {
    public static void main(String[] args) throws IOException {
        int num2;
        String search;
        String num, name, tel, email;
        PrintWriter in = new PrintWriter(new FileWriter("user.txt"));
        Scanner s = new Scanner(System.in);

        // 사용자 정보를 입력받아 "user.txt" 파일에 저장
        while (true) {
            System.out.print("번호: ");
            num = s.next();
            System.out.print("이름: ");
            name = s.next();
            System.out.print("전화번호: ");
            tel = s.next();
            System.out.print("이메일: ");
            email = s.next();
            in.println(num + "," + name + "," + tel + "," + email);

            System.out.println("입력이 끝났으면 0, 계속하려면 1: ");
            num2 = s.nextInt();
            if (num2 == 0) {
            	in.close();
                break;
            } else if (num2 != 1) {
            	in.close();
            	throw new IllegalArgumentException("0 또는 1 값을 입력하세요.");
            }
        }

        // 사용자 번호를 검색하고 해당 사용자의 전화번호 출력
        Scanner fileScanner = new Scanner(new File("user.txt"));
        System.out.print("검색할 사용자 번호: ");
        search = s.next();

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 3 && parts[0].equals(search)) {
            	System.out.println("검색된 사용자의 전화번호: " + parts[2]);
                break; // 검색 완료 후 루프 종료
            }
        }
        s.close();
        fileScanner.close();
    }
}

