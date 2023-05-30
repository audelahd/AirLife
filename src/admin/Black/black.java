package admin.Black;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import admin.Adminpage;

public class black {
	public Scanner scanner= new Scanner(System.in);
	public Adminpage admin=new Adminpage();
	static Hotel.ConTest con=new Hotel.ConTest();
	static Statement stmt;
	static ResultSet rs;
	
	public void plusblack() {
		System.out.println("추가할 id를 입력해주십시오");
		System.out.print("ID:");
		String id=scanner.next();//아이디를 입력받음
		try {
			String ab="update users set grade='BLACK' where user_id='"+id+"'";//업데이트쿼리문작성
			Connection conn=con.ConOn();//컨넥션 생성메소드실행 아이디비밀번호 드라이버 url를가져옴
			stmt=conn.createStatement();//stmt으로 db와연결
			stmt.executeUpdate(ab);//쿼리문을 업데이트함 
			System.out.println("정상적으로 업데이트 되었습니다 ");//오류가 나지않으면 업데이트후 출력
		}catch(Exception e) {//오류가 나면 catch문을 출력 하여 오류확인
			System.out.println("존재하지않는 아이디입니다");
			plusblack();//처음부터 입력하게만듬
		}
		admin.Blacklist();//오류가발생하지않으면 아래로 내려와서 상위페이지로 되돌림
	}
	
	
	public void delblack() {
		System.out.println("삭제할 id를 입력해주십시오");
		System.out.print("ID:");
	 	String id=scanner.next();
	 	try {
		 	String ab="update users set grade='NORMAL' where user_id='"+id+"'";
		 	Connection conn=con.ConOn();
		 	stmt=conn.createStatement();
		 	stmt.executeUpdate(ab);
		 	System.out.println("정상적으로 업데이트 되었습니다 ");//오류가 나지않으면 업데이트후 콘솔에결과출력
		 	conn.close();//사용끝난 db는 닫아줌
		 	stmt.close();//사용끝난 db는 닫아줌
 	 	}catch(Exception e) {//오류가 나면 catch문을 출력 하여 오류확인
 	 		System.out.println("존재하지않는 아이디입니다");
 	 		delblack();//처음부터 입력하게만듬
 	 	}
	 	admin.Blacklist();//오류가발생하지않으면 아래로 내려와서 상위페이지로 되돌림
	}
	
	
	
	public void selblack() {
		try {
			String ac="select user_id,username,age from users where grade='BLACK'";
			Connection conn=con.ConOn();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(ac);//쿼리문을 업데이트하고 select문이므로 ResultSet으로 값을 받아옴 
			if(rs==null) {//not을 넣어 값이없을경우 ture로 만들어서 아래문자 출력 값이있을경우 false이므로 실행하지않고 넘어감
				System.out.println("블랙리스트가 없습니다.");
			}else{
				System.out.println("아이디|이름|나이|");//값이 있을경우 출력 없을경우 출력하지않음 
				while(rs.next()) {//값이 여러개일경우 모두 출력하기위해 next를 사용하여 rs에 값이없을떄까지 출력 
					String id=rs.getString("user_id");//컬럼이름이 user_id인값을 가져옴
					String name=rs.getString("username");//컬럼이름이 username값을 가져옴
					String age=rs.getString("age");//컬럼이름이 age인값을 가져옴
					System.out.print(id+"|");//컬럼이름이 user_id인값을 출력
					System.out.print(name+"|");//컬럼이름이 username인값을 출력
					System.out.println(age+"|");//컬럼이름이 age인값을 출력
				}
			}
			conn.close();//사용끝난 db는 닫아줌
		 	stmt.close();//사용끝난 db는 닫아줌
		 	rs.close();//사용한 db는 닫아줌 
		}catch (Exception e) {
			e.printStackTrace();
		}
		admin.Blacklist();
	}

}
