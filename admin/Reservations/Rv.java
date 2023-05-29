package admin.Reservations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Rv {
	
	static Hotel.ConTest con=new Hotel.ConTest();
	Statement stmt;
	ResultSet rs;
	String[] user_id=new String[10];
	String[] username=new String[10];
	
	public void rv() {
		int j=0;//처음실행시 증감된 j의값을 초기화시켜줌

		System.out.println("현재 예약 현황조회");
		String query1="SELECT DISTINCT user_num FROM reservations";//쿼리문을입력해줌 구할값은 예약테이블에 들어가있는 사람의 아이디 중복값은 제거된다
		try {//쿼리문에 업데이트
			Connection conn=con.ConOn();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(query1);
			while(rs.next()) {
				user_id[j]=rs.getString("user_num");//얻은값을 배열에 저장함 배열은 증감식을 사용하여 배열 값을 올려줌
				j++;//증감식
			}
			rs.close();//사용이끝난 rs는 닫아줌
			for (int i = 0; i < user_id.length-1; i++) {//중복값이없이 구해진 num을 대입하여 사람의 그아이디의 주인의 이름을 구함 횟수는  user_id의 길이이기떄문에 위에서 구한 값만큼 횟수반복 0부터시작이기떄문에 -1의 값을줌
				String query2="SELECT username FROM users where user_num='"+user_id[i]+"'";//반복문을 사용하여 쿼리의 값을 계속 변경시켜줌
				rs=stmt.executeQuery(query2);//대입
				while(rs.next()) {
				username[i]=rs.getString("username");//반복하여 실행 한다 값은 username배열에 저장 마찬가지로 반복문의 i 를사용하여 배열의값을 올려줌
				}
			}
			rs.close();//사용이끝난 rs는 닫아줌
			for (int i = 0; i <=j-1; i++) {//for문을 실행 증감제한은 글자수로주면 저장된 null값이 불러와지기떄문에 위에서 사용한 j를 이용하여 횟수를 저장된값까지만 사용하게만듬 물론 0부터시작이기떄문에 -1을 넣어줌
				String query3="select check_in_date,check_out_date,room_num from reservations where user_num='"+user_id[i]+"'";
				rs=stmt.executeQuery(query3);
				System.out.println("예약인:"+username[i]);//맨우에서 제일먼저 예약인의 이름을 먼저 출력해줌 반복하지않는다
				while (rs.next()) {//동일 예약인이 여러번 예약을 할떄 예약인 한명에 여러개의 예약이 조회될수있도록 만든다
					String date1=rs.getString("check_in_date");
					String date2=rs.getString("check_out_date");
					String num=rs.getString("room_num");
					System.out.print("방번호:"+num);
					System.out.println("     예약일:"+date1+"~"+date2);
				}
			}
			System.out.println();
			conn.close();
			rs.close();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
