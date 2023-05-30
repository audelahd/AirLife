package Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.sql.Date;
import java.util.Scanner;

import oracle.sql.DATE;

public class Reservation {
	static Review rev = new Review();
	static Reser res = new Reser();
	static Room room = new Room();
	
	public static void m_Reservation(int num, String user_num) {
		Scanner resrv = new Scanner(System.in);
		System.out.println(user_num);
		ConTest contest = new ConTest();
		Connection conn =contest.ConOn();
		try {
		
		
		if(num ==1) { //예약 하기
			
			String resdup = "SELECT USER_NUM ='"+user_num+"'";
			
			System.out.println("체크인 날짜를 입력해주세요 (형식 : YYYY-MM-DD, -는 제외하고 8자리로 작성) ");
			res.setCheckin(resrv.next());
			
			System.out.println("체크아웃 날짜를 입력해주세요 (형식 : YYYY-MM-DD, -는 제외하고 8자리로 작성) ");
			res.setCheckout(resrv.next());
			
			System.out.println("=========[ 예약할 수 있는 방의 정보입니다. ]=========");
			
			String com = "commit";
			String sql = "SELECT room_num, room_type, price FROM rooms WHERE checkio='O'";
			
			PreparedStatement ps_commit = conn.prepareStatement(com);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ps_commit.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				room.setRoomnum(rs.getInt("room_num"));
				room.setRoomtype(rs.getString("room_type"));
				room.setRoomprice(rs.getInt("price"));
				
				System.out.printf("%-6d%-12s%-16d\n", room.getRoomnum(), room.getRoomtype(), room.getRoomprice());
			}
			System.out.print("원하시는 방의 번호를 입력해주세요 : ");
			int w_rnum = resrv.nextInt();
			System.out.println("선택하신 "+w_rnum+"번 방에서 제공되는 서비스는 다음과 같습니다.");
			System.out.println("-------------------------------------------------");
			//System.out.println("sql문으로 서비스의 OX를 업데이트해서 가져온다음 제공");
			String roomsql = "SELECT room_type FROM rooms where room_num ='"+w_rnum+"'";
			PreparedStatement rompst = conn.prepareStatement(roomsql);
			ResultSet romrs = rompst.executeQuery();
			String r_t="";
			while(romrs.next()) {
				r_t=romrs.getString("room_type");
			}
			int sernum=0;
			
			
			if(r_t.equals("그랜드디럭스")) {
				sernum=3;
			}
			
			else if(r_t.equals("프리미어")) {
				sernum=5;
			}
			else if(r_t.equals("디럭스스위트")) {
				sernum=2;
			}
			else if(r_t.equals("프리미어 스위트")) {
				sernum=4;
			}
			else if(r_t.equals("프레지덴셜스위트")) {
				sernum=1;
			}
			else {
				System.out.println("오류");
			}
			
			rompst.close(); romrs.close();
			String sersql = "SELECT S_NAME FROM SERVICE where serox>='"+sernum+"'";
			
			PreparedStatement serpst = conn.prepareStatement(sersql);
			ResultSet serrs = serpst.executeQuery();
			while(serrs.next()) {
				System.out.println(serrs.getString("S_NAME"));
			}
			
			
//			String RprcSql = "SELECT price FROM rooms where room_num=' "+w_rnum+"'";
//		
//			PreparedStatement prcpst = conn.prepareStatement(RprcSql);
//			ResultSet prcrs = prcpst.executeQuery();
//			
//			int price=0;
//			while(prcrs.next()) {
//				price = (prcrs.getInt("price"));
//			}
			
			
			System.out.println();
			System.out.println("============================================");
			System.out.println(w_rnum+"번 방으로 예약 하시겠습니까? 1. yes  2. no ");
			while (true) {
				int reserox = resrv.nextInt();
				
				if (reserox==1) {
					String resSql = "INSERT INTO reservations (check_in_date, check_out_date, user_num, room_num) VALUES( ?, ?, ? ,?)";
					String roxSql = "UPDATE rooms SET checkio='X' WHERE room_num ='"+w_rnum+"'";
					String mlgSql = "UPDATE users SET mlg = (mlg+ ? ) WHERE user_num= '"+user_num+"'";
					String prcSql = "SELECT price FROM rooms where room_num=' "+w_rnum+"'";
					PreparedStatement respst = conn.prepareStatement(resSql);
					respst.setString(1,res.getCheckin());
					respst.setString(2,res.getCheckout());
					respst.setString(3, user_num);
					respst.setInt(4, w_rnum);
					
					respst.executeUpdate();
					respst.close();
					
					System.out.println("예약 되었습니다.");
					
					//방 번호 예약 막아두기
					PreparedStatement roxpst = conn.prepareStatement(roxSql);
					roxpst.executeUpdate();
					roxpst.close();
					
					
					//방 가격
					PreparedStatement prcpst = conn.prepareStatement(prcSql);
					ResultSet prcrs = prcpst.executeQuery();
					
					int price=0;
					while(prcrs.next()) {
						price = (prcrs.getInt("price"));
					}
					
					//마일리지 올라가게 하기
					PreparedStatement mlgpst = conn.prepareStatement(mlgSql);
					
					int mlg = price/100;
					mlgpst.setInt(1, mlg);
					mlgpst.executeUpdate();
					mlgpst.close();
					
					break;
					
				}
				else if (reserox==2) {
					System.out.println("예약을 취소합니다.");
					break;
				}
				else
					System.out.println("다시 입력");
			}
			
		}
		
		else if(num==2) { //예약 조회
			System.out.println("======가장 최근 예약 내역입니다.======");
			String sql = "SELECT CHECK_IN_DATE, CHECK_OUT_DATE, ROOM_NUM FROM RESERVATIONS WHERE USER_NUM= '"+user_num+"'";
			
			String checkin=null,checkout=null,room_type=null;
			int room_num=0,price=0;
			PreparedStatement ps1 = conn.prepareStatement(sql);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				checkin=rs1.getString("CHECK_IN_DATE");
				checkout=rs1.getString("CHECK_OUT_DATE");
				room_num=rs1.getInt("ROOM_NUM");
				
			}
			String sql2 = "SELECT ROOM_TYPE, PRICE FROM ROOMS WHERE ROOM_NUM = "+room_num;
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()) {
				room_type = rs2.getString("ROOM_TYPE");
				price=rs2.getInt("PRICE");
				
			}
			
			System.out.println();
			System.out.printf("체크인 : %-6s 체크아웃 : %-6s  %-10s %-3d호  %-5d원", checkin, checkout, room_type, room_num, price);
			
			System.out.println();
//			System.out.println("예약을 취소하시려면 방 번호를 입력해주세요.");
//			Scanner rescan = new Scanner(System.in);
//			int io = rescan.nextInt();
//			
//			if(io==room_num) {
//				
//				String delSql = "DELETE FROM RESERVATIONS WHERE room_num= '"+room_num+"'";
//				String updSql2= "UPDATE rooms SET checkio='O' WHERE room_num= '"+room_num+"'";
//				String mlgSql = "UPDATE users SET mlg = (mlg-?) WHERE user_num= '"+user_num+"'";
//				String prcSql = "SELECT price FROM rooms where room_num= '"+room_num+"'";
//				
//				//방 가격
//				PreparedStatement prcpst = conn.prepareStatement(prcSql);
//				ResultSet prcrs = prcpst.executeQuery();
//				
//				int pprice=0;
//				while(prcrs.next()) {
//					pprice = (prcrs.getInt("price"));
//				}
//				
//				//마일리지 올라가게 하기
//				PreparedStatement mlgpst = conn.prepareStatement(mlgSql);
//				
//				int mlg = pprice/100;
//				mlgpst.setInt(1, mlg);
//				mlgpst.executeUpdate();
//				mlgpst.close();
//				
//				PreparedStatement pps1 = conn.prepareStatement(delSql);
//				PreparedStatement pps2 = conn.prepareStatement(mlgSql);
//				PreparedStatement pps3 = conn.prepareStatement(updSql2);
//				pps1.executeUpdate();
//				pps2.executeUpdate();
//				pps3.executeUpdate();
//				
//				pps1.close();
//				pps2.close();
//				pps3.close();
//				
//				
//				System.out.println("예약이 취소되었습니다.");
//				
//			}
//			
//			else {
//				System.out.println("뒤로 갑니다...");
//			}
			
		}
		
//		else if(num==3){ //뒤로가기
//			System.out.println("==========예약 취소 페이지입니다.==========");
//			System.out.println("정말로 예약을 취소하시겠습니까? 1. yes 그 외. no");
//			int point =resrv.nextInt();
//			if( point==1) {
//				//String delSql = "DELETE FROM RESERVATIONS WHERE room_num= '"+room_num+"'";
//				//String updSql2= "UPDATE rooms SET checkio='O' WHERE room_num= '"+room_num+"'";
//				String mlgSql = "UPDATE users SET mlg=(mlg-?) WHERE user_num= '"+user_num+"'";
//				//String prcSql = "SELECT price FROM rooms where room_num= '"+room_num+"'";
//				
//				//방 가격
//				//PreparedStatement prcpst = conn.prepareStatement(prcSql);
//				//ResultSet prcrs = prcpst.executeQuery();
//				
//				int pprice=1000000;
////				while(prcrs.next()) {
////					pprice = (prcrs.getInt("price"));
////				}
////				
//				//마일리지 올라가게 하기
//				PreparedStatement mlgpst = conn.prepareStatement(mlgSql);
//				
//				int mlg = pprice/100;
//				mlgpst.setInt(1, mlg);
//				mlgpst.executeUpdate();
//				mlgpst.close();
//				
//				//PreparedStatement pps1 = conn.prepareStatement(delSql);
//				PreparedStatement pps2 = conn.prepareStatement(mlgSql);
//				//PreparedStatement pps3 = conn.prepareStatement(updSql2);
//				//pps1.executeUpdate();
//				pps2.executeUpdate();
//				//pps3.executeUpdate();
//				
//				//pps1.close();
//				pps2.close();
//				//pps3.close();
//				
//				
//				System.out.println("예약이 취소되었습니다.");
//			}
//			else {
//				System.out.println("예약 페이지로 돌아갑니다...");
//			}
//		}
		else {
			System.out.println("제대로 된 번호를 입력해주세요.");
		}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
