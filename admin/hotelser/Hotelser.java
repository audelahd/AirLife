package admin.hotelser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import admin.Adminpage;

public class Hotelser {

	static Adminpage adminpage=new Adminpage();
	static Scanner scanner=new Scanner(System.in);
	static Hotel.ConTest con=new Hotel.ConTest();
	Statement stmt;
	ResultSet rs;
	
	public void Hotelservice() {
		int j=1;
		String[] service=new String[7];//문자열을 셋팅해줌
		service[0]="룸서비스";
		service[1]="레스토랑예약";
		service[2]="수영장";
		service[3]="Bar";
		service[4]="스파";
		service[5]="헬스클럽";
		service[6]="바베큐";
		String selser=null;
		System.out.println("호텔 서비스관리 모드");
		for (int i = 0; i < service.length; i++) {//저장된 문자열의값을 출력해줌 사용자가 인식할수있다 consol
			System.out.println(j+"번서비스="+service[i]);
			j++;//j의값을 증감시켜 값을 출력시 사용자가 인식하기쉽게만들어줌
		}
		System.out.println("수정할 항목을 선택해주십시오");
		System.out.print("입력값:");int sel=scanner.nextInt();//값을입력받음
		switch (sel) {//바꿀항목에 값을 대입시켜줌 
			case 1: selser=service[0];break;
			case 2: selser=service[1];break; 
			case 3: selser=service[2];break;
			case 4: selser=service[3];break;
			case 5: selser=service[4];break;
			case 6: selser=service[5];break;
			case 7: selser=service[6];break;
			default: 
				System.out.println("선택값이 없습니다 재입력해주십시오");//입력받은값이 1~7까지에 값이없으면 실행 오류문출력후 처음으로 돌려보냄
				Hotelservice();
		}
		System.out.println("사용 등급을 입력해주십시오 (1~4등급)");//입력값을 알려줌
		System.out.print("입력값:");int OX=scanner.nextInt();
		if(OX>=0&&OX<5) {
			String query="update service set serox="+OX+" WHERE s_name='"+selser+"'";//앞에 ox는 숫자타입이기떄문에 ''을 제외 뒤에 selser은 문자열이기떄문에 ''을 넣어주었다.
			sql(query);
			System.out.println("설정 변경성공 관리자 화면으로 돌아갑니다");
			adminpage.page();
		}else {
			System.out.println("올바른 값이 아닙니다 다시입력해주시길 바랍니다");
			Hotelservice();//위항목의 최상단으로 돌려보냄
		}
		
//		if(OX.equals("O")||OX.equals("o")) {//대문자o와 소문자o를 둘중하나가 맞으면실행 하게만들어둠
//			String query="update service set serox='O' WHERE s_name='"+selser+"'";
//			sql(query);//x의상황과같은 업데이트문을 사용하기떄문에 메소드에 쿼리만 대입시켜서 실행시켜줌
//			System.out.println("설정 변경성공 관리자화면으로 돌아갑니다 ");//오류가발생되지않으면 출력
//			adminpage.page();//처음으로 돌려보냄 
//		}else if(OX.equals("X")|| OX.equals("x")) {//대문자x와 소문자x를 둘중하나가 맞으면실행 하게만들어둠
//			String query="update service set serox='X' WHERE s_name='"+selser+"'";
//			sql(query);//o와 상황이같은 업데이트문을 사용하기떄문에 메소드에서 쿼리만 대입시켜서 실행
//			System.out.println("설정 변경성공 관리자화면으로 돌아갑니다 ");
//			adminpage.page();
//		}else {
//			System.out.println("선택한값이없습니다 재입력 바랍니다.");//입력한값이 o,O,x,X와다른값이므로 사용자에게 오류를 인식시킴
//			Hotelservice();//위항목의 최상단으로 돌려보냄
//		}
		
	}
	
	public void sql(String query) {//메소드 실핸시킨곳에서 업데이트문을 받아옴 
		try {
			Connection conn=con.ConOn();
			stmt=conn.createStatement();
			stmt.executeUpdate(query);//대입받은 업데이트문을 쿼리에 업데이트시킴 
			conn.close();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
