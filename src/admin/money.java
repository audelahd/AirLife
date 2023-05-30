package admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class money {

	static Adminpage adminpage=new Adminpage();
	static Hotel.ConTest con=new Hotel.ConTest();
	static Statement stmt;
	static ResultSet rs;
	
	public void summonet(){
		String money="0";
		long _money=0;//저장받은값들이 초기화되어야하기떄문에 맨위에서 선언  
		String query="SELECT mlg FROM users";//고객테이블의 마일리지의 값을 받아온다 나중에 sum(mlg)의 값을 받아오고싶은데 모르겟다지금은
		try {
		Connection conn=con.ConOn();
		stmt=conn.createStatement();
		rs=stmt.executeQuery(query);
		while(rs.next()) {
			money=rs.getString("mlg");//String타입으로 받아와서 money에저장함
			_money+=Integer.parseInt(money);//값을 강제 타입변화 파싱 하여 long타입에 +=연사자를 사용하여 증감 연산시킨다
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("+------------------+");
		System.out.println("총매출:"+_money*100);//마일리지는 1%적립이기떄문에 적립받은값에 곱하기 100을 넣어서 총매출을 출력한다
		System.out.println("+------------------+");
		System.out.println("관리자 메인 페이지로 이동합니다 ");//출력이성공햇으면출력후 메인페이지로 이동한다 
		adminpage.page();
	}
}
