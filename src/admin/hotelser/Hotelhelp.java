package admin.hotelser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import admin.Adminpage;

public class Hotelhelp {
	static Hotel.ConTest con=new Hotel.ConTest();
	Statement stmt;
	ResultSet rs;
	Adminpage admi=new Adminpage();
	Scanner scanner=new Scanner(System.in);
	
	public void Help() {
		System.out.println("변경할 호텔 설명을 처음부터 입력해주시길 바랍니다");
		String help=scanner.next();
		String query="update service set S_content='"+help+"'where s_name='호텔소개'";//입력받은값을 set값에 대입하여줌 호텔소개만 변경할것으기떄문에 where는 변경돼지않음
		try {
			Connection conn=con.ConOn();
			stmt=conn.createStatement();
			stmt.executeUpdate(query);
			System.out.println("업데이트가 정상적으로 완료 돼었습니다");//성공할경우 출력
			conn.close();
			stmt.close();//다쓴db는닫아줌
		}catch (Exception e) {
			System.out.println("최대 자리수가 초과하였습니다");//오류가 발생할상황은 자릿수초과밖에없기떄문에 사용자에게 오류가 난 이유를 알려줌
			Help();//처음부터 다시실행
		}
		admi.HotelSetting();
	}

}
