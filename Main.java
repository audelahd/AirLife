package Hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
	static Scanner scanner=new Scanner(System.in);
	
	
	public static void page() {
		
		
		Login login = new Login();
		Endpage endpage = new Endpage();
		Testmethod test = new Testmethod();
		
		System.out.println("환영합니다. ~~호텔 입니다.\n");		
		System.out.println("1. 호텔 소개");
		System.out.println("2. 로그인");
		System.out.println("3. 회원가입");
		System.out.println("4. 호텔 리뷰");
		System.out.println("5. 호텔 공용 서비스");
		System.out.println("6. 프로그램 종료\n");
		System.out.print("원하시는 메뉴를 선택해 주세요: ");
		
		int sele=scanner.nextInt();
		switch (sele) {
//		case 1:introduction();break;
		case 2:login.Loginpage();break;
		case 3:test.Test_Join();break;
//		case 4:review();break;
//		case 5:service();break;
		case 6:endpage.End(1);break;
		default :
		System.out.println("값을 잘못 입력하였습니다. ");//이외의 값 입력시 반복시킨다 
		}
		
	}
	

	
	public static void main(String[] args) {
		page();
	}
	
}
		