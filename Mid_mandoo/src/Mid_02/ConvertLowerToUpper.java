package Mid_02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ConvertLowerToUpper {
	public static void main(String[] args) throws IOException{
		try (
			BufferedReader inputStream = new BufferedReader(new FileReader("obama.txt"));
			PrintWriter outputStream = new PrintWriter(new FileWriter("output.txt"));) {
			int ch;
            while ((ch = inputStream.read()) != -1) {
                // 소문자를 대문자로 변환하고 출력 파일에 쓴다.
                if (Character.isLowerCase(ch)) {
                    ch = Character.toUpperCase(ch);
                }
                outputStream.write(ch);
            }
		}
	}
}
