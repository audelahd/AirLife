package admin.Roomset;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import admin.Adminpage;

public class RoomHelp {
	static Scanner scanner=new Scanner(System.in);
	static Adminpage adminpage=new Adminpage();
	static Hotel.ConTest con=new Hotel.ConTest();
	Statement stmt;
	ResultSet rs;
	
	public void Helper() {
		String help=null;//사용할값들을 먼저 선언해줌 값은 try문밖에서도 사용될수있고 값이 매번 초기화 되어야 하기떄문에 
		String Room=null;
		String qeury=null;
		System.out.println("설명을 변경할 객실을 선택해주십시오");
		System.out.println("1. 그랜드디럭스");
		System.out.println("2. 프리미어");
		System.out.println("3. 디럭스 스위트");
		System.out.println("4. 프리미어 스위트");
		System.out.println("5. 프레지덴셜 스위트");
		System.out.print("입력:");//변경할수있는 조건을 설정시킴
		int number=scanner.nextInt();
		switch (number) {
			case 1:Room="그랜드디럭스";break; 
			case 2:Room="프리미어";break;
			case 3:Room="디럭스스위트";break;
			case 4:Room="프리미어디럭스";break;
			case 5:Room="프레지덴셜스위트";break;
			}//쿼리에서 사용할 변수에 값을 대입시킴 
		try {
			System.out.println("변경할 금액을 입혁해주십시오 ");
			System.out.print("입력값:");//쿼리문을 재사용함 입력받는값은 바로 변수에저장되어 쿼리문에 일부로들어감
			qeury="update rooms set price='"+(help=scanner.next())+"' where room_type='"+Room+"'";
			Connection conn=con.ConOn();
			stmt=conn.createStatement();
			stmt.executeUpdate(qeury);
			System.out.println("정상적으로 업데이트 되었습니다 ");//업데이트가 오류없이 성공했으면 사용자에게 인식시켜준다
			stmt.close();
			conn.close();
			adminpage.Roomsetting();//마지막으로 성공했으면 원래대로 돌아감
		}catch (Exception e) {
			System.out.println("입력받은 값이 글자수 제한에 걸립니다 재입력 바랍니다.");
			Helper();//오류가 발생하면 자릿수 오류이기떄문에 자릿수로유를 표시해준후 페이지의 첫부분으로 돌아간다
		}
	}
}
