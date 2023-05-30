package Hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import lombok.Data;

@Data
class Review {
	private int review_id;
	private String comment;
	private float rating;
	private Date review_date;
	private String username;

}
@Data
class Reser{
	private String checkin; 
	private String checkout;
	private String user_num;
	private int roomnum;
	//private int total_guests;
}

@Data
class Room{
	private int roomnum;
	private String roomtype;
	private String roomocc;
	private int roomprice;
}

@Data 
class User{
	private String usernum;
	private String userid;
	private int mlg;
	private String grade;
}

public class Test_Main {
	
	static String user_num = "";
	public static void main(String[] args) {
		
		
		while (true) {
			System.out.println();
			System.out.println("==============[ 메인 페이지 ]==============");
			System.out.println("=== 1. 회원가입 2. 로그인 3. 종료 ===");

			Scanner scanum = new Scanner(System.in);
			Member mem = new Member();
			Join join = new Join();
			int num = scanum.nextInt();
			

			if (num == 1) {
				user_num=join.m_Join(); //나중에는 return값을 로그인한 user_num으로 고쳐야 함!!
			} else if (num == 2) {
				
//				System.out.println("방금 회원가입한 번호로 진행");
//				mem.m_Info(user_num);
				Login login = new Login();
				login.Loginpage();
			} else if (num == 3) {
				System.out.println("정말 종료하시겠습니까? 1. yes / 2. no");

				while (true) {
					int num2 = scanum.nextInt();
					if (num2 == 1) {
						System.out.println("프로그램이 종료됩니다.");
						System.exit(0);
					} else if (num2 == 2) {
						System.out.println("메인 화면으로 돌아갑니다.");
						System.out.println();
						System.out.println();
						break;
					} else {
						System.out.println("정확한 값을 입력해주세요 : 1. yes / 2. no");
					}
				}
			}else {
				System.out.println("제대로된 값을 입력하세요");
			}
		}

	}

}
