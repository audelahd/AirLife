package admin.Roomset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import admin.Adminpage;

public class Check {
	
	int number=0;
	String query=null;
	static Adminpage adminpage=new Adminpage();
	static Scanner scanner=new Scanner(System.in);
	static Hotel.ConTest con=new Hotel.ConTest();
	Statement stmt;
	ResultSet rs;
	
	public void CheckIn() {
		String _io=null;
		System.out.println("체크 인을 할 객실의 호수를 입력해주십시오");
		System.out.print("호수:");number=scanner.nextInt();
		query="SELECT checkio FROM rooms where room_num='"+number+"'";//호수를 입력받는다
		_io=sesql(query);//쿼리에서 반환하는값이 없다면 기본값이 null이기떄문에 
			if(_io==null) {//if문에서 제일처음에 걸러진다
				System.out.println("존재하지 않는 방입니다");
				CheckIn();//이메뉴의 처음으로 돌아간다
			}else if(_io.equals("X")) {
				//값이 이미 x라면 다시 체크인 을 할필요가없기때문에 이전메뉴로돌아감 
				System.out.println("이미 체크인된 방입니다 ");
				adminpage.Roomsetting();
			}else if(_io.equals("O")) {//만약 o를 리턴받는다면 체크인이 가능하기떄문에 값을 x로바꿔준다
				query="update rooms set checkio='X' where room_num='"+number+"'";//방번호는 위에서받아온값을 그대로 사용 검증이끝났기떄문에
				sql(query);//쿼리 업데이트문
				System.out.println("체크인이 완료되었습니다");
				adminpage.Roomsetting();
			}
	}

	public void CheckOut() {//위에 주석과 똑같음 
		String _io=null;
		System.out.println("체크 아웃을 할 객실의 호수를 입력해주십시오");
		System.out.print("호수:");number=scanner.nextInt();
		query="SELECT checkio FROM rooms where room_num='"+number+"'";
		_io=sesql(query);
		if(_io==null) {
			System.out.println("존재하지 않는 방입니다");
			CheckOut();
		}else if(_io.equals("O")) {
			System.out.println("이미 체크아웃된 방입니다 ");
			adminpage.page();
		}else if(_io.equals("X")) {
				System.out.println("존재하지 않는 방입니다");
				CheckIn();
				adminpage.page();
				query="update rooms set checkio='O' where room_num='"+number+"'";
				sql(query);
				System.out.println("체크 아웃이 완료되었습니다");
				adminpage.page();
		}
	}
	
	public void sql(String query) {//메소드 실핸시킨곳에서 업데이트문을 받아옴 
		try {
			Connection conn=con.ConOn();
			stmt=conn.createStatement();
			stmt.executeUpdate(query);//대입받은 업데이트문을 쿼리에 업데이트시킴 
			conn.close();
			stmt.close();
		}catch (Exception e) {
			System.out.println("존재하지않는 방입니다");
			adminpage.page();
		}
		
	}
	public String sesql(String query) {
		String _io=null;
		try {
			Connection conn=con.ConOn();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query);
			while(rs.next()) {
				_io=rs.getString("checkio");
			}
			conn.close();
			rs.close();
			stmt.close();
		}catch (Exception e) {
			return null;
		}
		return _io;
	}
	
	
}
