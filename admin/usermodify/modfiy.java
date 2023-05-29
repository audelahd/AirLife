package admin.usermodify;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import admin.Adminpage;

public class modfiy {
	static Hotel.ConTest con=new Hotel.ConTest();
	static Hotel.Endpage end=new Hotel.Endpage();
	static Adminpage adminpage=new Adminpage();
	static Statement stmt;
	static ResultSet rs;
	Scanner scanner =new Scanner(System.in);
	
	public void useridmodfiy() {
		System.out.println("전체 아이디를 조회합니다");
		String query="select user_id,user_num from users";//고정값이기떄문에 쿼리문을 그냥 저장시킨다 
		try {
			Connection conn=con.ConOn();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()) {//반환받은값을 String 변수에저장한후  출력한다
				String user_id=rs.getString("user_id");
				String user_num=rs.getString("user_num");
				System.out.print("고객번호"+user_num);
				System.out.println("   "+user_id);
			}
			conn.close();
			stmt.close();
			rs.close();
		}catch (Exception e) {
			System.out.println("예상치 못한 오류발생 처음화면으로 돌아갑니다");
		}
		System.out.println("아이디를 수정하시려면 1번 이전으로 돌아가실려면 2번을 눌러주세요");
		System.out.print("입력값:"); int sel=scanner.nextInt();
		switch (sel) {
		case 1:break;//1번을 누르면 switch 문을 빠져나가기떄문에 바로 switch 문아래에있는 값을 출력한다
		case 2: adminpage.guestmodify();break;
		default :
			System.out.println("값이정확하지않습니다");
			adminpage.guestmodify();break;
		}
		System.out.println("변경할 아이디의 번호를 입력해주십시오");
		System.out.print("입력값:");sel=scanner.nextInt();//위에서 출력받은 user_num을 입력하여 조건문에사용
		System.out.println("변경할 아이디를 입력해주십시오:");String id=scanner.next();//아이디는 자유롭게 지정가능하다
		query="update users set user_id='"+id+"' where user_num='"+sel+"'";//쿼리문에 대입시킨다
		try {
			Connection conn=con.ConOn();
			stmt=conn.createStatement();
			stmt.executeQuery(query);
			System.out.println("정상적으로 업데이트돼었습니다");//업데이트후  정상적으로 업데이트됏따면 출력한다
			conn.close();
			stmt.close();
			adminpage.page();
		}catch (Exception e) {
			System.out.println("아이디가 너무 깁니다 재입력 해주십시오");//아이디의 문자열이너무길어 오류가발생시 오류문을 알려준다음 재실행시킨다
			useridmodfiy();
		}
	}
}
