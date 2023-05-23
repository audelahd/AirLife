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
	private String guestname;
	private String comment;
	private float rating;
	private Date review_date;

}
@Data
class Reser{
	private Date checkin; 
	private Date checkout;
	private int total_guests;
}

@Data
class Room{
	private int roomnum;
	private String roomtype;
	private String roomocc;
	private int roomprice;
}

public class Testmethod {
	
	static Review rev = new Review();
	static Reser res = new Reser();
	static Room room = new Room();
	
	private static Connection conn;
	
	public static String sqlS_string(int i) {
		String cs_unum = "Create SEQUENCE SEQ_UNUM NOCACHE";
		String cs_rnum = "Create SEQUENCE SEQ_RNUM NOCACHE";
		
		String ds_unum = "DROP SEQUENCE SEQ_UNUM";
		String ds_rnum = "DROP SEQUENCE SEQ_RNUM";
		
		String s_error = "오류";
		
		if (i==1) {
			return cs_unum;
		}
		else if(i==2) {
			return cs_rnum;
		}
		else if(i==3) {
			return ds_unum;
		}
		else if(i==4) {
			return ds_rnum;
		}
		return s_error;
	}
	
	
	public static String Test_Join() { //회원가입

		String id = null;
		String pwd = null; // while안에 들어가기 전 초기화
		int i = -1;
		Scanner scan = new Scanner(System.in); // 입력

		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "gp_java", // 사용자아이디
					"12345" // 사용자비번
			);

			String sql = "" + "INSERT INTO users(user_num, user_id, password, username, age)"
					+ "VALUES(SEQ_UNUM.nextval, ?, ?, ?, ?)"; // 값 입력용

			String sql2 = "" + "INSERT INTO users (user_num,user_id,password,username,age)" + "VALUES( '0', ? ,'00000000', '중복확인용', 10)"; // 아이디 중복 확인용

			String sql3 = "" + "DELETE FROM users " + "WHERE user_id = ?"; // 중복 확인용으로 넣은거 지우기

			PreparedStatement pstmt = conn.prepareStatement(sql);
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);

			System.out.println("==============[ 회원 정보 입력 ]==============");

			while (i < 0) {

				while (true) {
					System.out.print("아이디 : ");
					id = scan.next();
					System.out.println();

					if (id.length() < 5) {
						System.out.println("아이디는 5자리 이상으로 입력해주세요.");
					} else {
						break;
					}
				}

				pstmt2.setString(1, id); // id 넣기

				int count = 0;
				while (true) {

					try {

						pstmt2.executeQuery(); // 테이블에 아이디만 넣어보기
						pstmt3.setString(1, id);
						pstmt3.executeQuery(); // 바로 삭제

					} catch (Exception e) { // 그래도 에러 잡아줌
						// e.printStackTrace();
						System.out.println("중복된 아이디입니다.");
						count++;
					}
					break; // while문 빠져나오기
				}

				if (count > 0) { // 중복이었을시 다시 루프
					System.out.println("다시 입력해주세요");
					System.out.println("==========================================");
				} else { // 아닐시 비밀번호 입력으로 넘어감 -> break로 아이디 while문 빠져나옴
					break;
				}

			}

			while (true) {
				System.out.print("비밀번호 : ");
				pwd = scan.next();
				System.out.println();

				if (pwd.length() < 8) {
					System.out.println("비밀번호는 8자리 이상으로 입력해주세요.");
				} else {
					break;
				}
			}
			System.out.print("이름 : ");
			String name = scan.next();
			System.out.println();

			System.out.print("나이 : ");
			int age = scan.nextInt();
			System.out.println();

//			System.out.print("전화번호 : ");
//			String pnum = scan.next();
//			System.out.println();

			// 데이터 입력 완료
			// 데이터 전달

			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setInt(4, age);
			//pstmt.setString(5, pnum);

			// 값 입력 완료

			int rows = pstmt.executeUpdate();
			if (rows != 0) { // 회원가입 성공 확인
				System.out.println("회원가입이 완료 되었습니다.");
				System.out.println("아이디 : " + id + " | 이름 : " + name);
			}

			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return id;

	}

	public static void Test_MemberInfo() { //회원 정보
		
		Scanner scan = new Scanner(System.in); // 입력
		int i = -1;

		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE", 
					"gp_java", // 사용자아이디
					"12345" // 사용자비번
			);
			while (i < 0) {
				System.out.println();
				System.out.println("==============[ 회원 페이지 ]==============");
				System.out.println("=== 1. 예약하기 2. 리뷰 3. 회원 정보 4. 종료 ===");

				int memnum = scan.nextInt();

				if (memnum == 1) {


					while (i < 0) {
						System.out.println();
						System.out.println("==============[ 예약 페이지 ]==============");
						System.out.println("=== 1. 예약정보 입력 2. 예약 조회 3. 뒤로가기 ===");
						int resvnum = scan.nextInt();

						if (resvnum == 1) {
							Test_Reservation(1);
						}
						else if (resvnum == 2) {
							Test_Reservation(2);
						}

						else if (resvnum == 3) {
							Test_Reservation(3);
							System.out.println("회원 페이지로 돌아갑니다.");
							break;
						}

						else {
							System.out.print("다시 입력 : ");
						}
					}
				}

				else if (memnum == 2) {

					while (i < 0) {
						System.out.println();
						System.out.println("==============[ 리뷰 페이지 ]==============");
						System.out.println("=== 1. 리뷰 작성 2. 리뷰 수정 3. 리뷰 삭제 ===");
						int revnum = scan.nextInt();

						if (revnum == 1) {
							Test_review(1);
						}

						else if (revnum == 2) {
							Test_review(2);
						}

						else if (revnum == 3) {
							Test_review(3);
						} else if (revnum==4){
							Test_review(4);
						}
						else {
							System.out.println("회원 페이지로 돌아갑니다.");
							break;
						}
					}

				}

				else if (memnum == 3) {
					System.out.println("회원 정보를 표시합니다.");
				}

				else if (memnum == 4) {
					System.out.println("메인 페이지로 돌아갑니다.");
					break;
				}

				else {
					System.out.println("숫자를 제대로 입력해주세요.");
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}
	
	public static void Test_Reservation(int num) {
		Scanner resrv = new Scanner(System.in);
		
		Connection conn = null;
		try {
		Class.forName("oracle.jdbc.OracleDriver");

		conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521/XE", 
				"gp_java", // 사용자아이디
				"12345" // 사용자비번
		);
		
		if(num ==1) { //예약 하기
			System.out.println("체크인 날짜를 입력해주세요 (형식 : YYYY-MM-DD, -는 제외하고 8자리로 작성) ");
			String tem = resrv.next();
			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
			Date formatDate = dtFormat.parse(tem);
			res.setCheckin(formatDate);
			
			System.out.println(formatDate);
			
			System.out.println("체크아웃 날짜를 입력해주세요 (형식 : YYYY-MM-DD, -는 제외하고 8자리로 작성) ");
			String tem2 = resrv.next();
			SimpleDateFormat dtFormat2 = new SimpleDateFormat("yyyyMMdd");
			Date formatDate2 = dtFormat2.parse(tem2);
			res.setCheckin(formatDate2);
			
			System.out.println(formatDate2);
			
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
			
		}
		
		else if(num==2) { //예약 조회
			
		}
		
		else if(num==3){ //뒤로가기
			System.out.println("뒤로 갑니다...");
		}
		else {
			System.out.println("제대로 된 번호를 입력해주세요.");
		}
		}catch (Exception e){
			System.out.println("오류오류");
		}
	}

	public static void Test_review(int num) {

			Connection conn = null;
			try {
			Class.forName("oracle.jdbc.OracleDriver");

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE", 
					"gp_java", // 사용자아이디
					"12345" // 사용자비번
			);

			if(num==1) {
			String insert_sql= "INSERT INTO REVIEWS(REVIEW_ID, GUEST_NAME, REVIEW_COMMENT,RATING, REVIEW_DATE) "
					+ "VALUES (SEQ_RNUM.NEXTVAL,?,?,?,SYSDATE) ";
			
			
			System.out.println("==============[ 리뷰 작성 ]==============");
			
			Scanner insert = new Scanner(System.in);
			
			rev.setGuestname("게스트이름");
			System.out.print("내용 : ");
			rev.setComment(insert.next());
			System.out.print("별점(숫자로 입력) : ");
			rev.setRating(insert.nextFloat());
			
			
			PreparedStatement pstmt1 = conn.prepareStatement(insert_sql);
			//pstmt1.setString(1, board.getBtitle());
			pstmt1.setString(1, rev.getGuestname());
			pstmt1.setString(2, rev.getComment());
			pstmt1.setFloat(3,rev.getRating());
			//pstmt1.setString(5, board.getBwriter());
			pstmt1.executeUpdate();
			pstmt1.close();
			}
			
			else if(num==2) {
				String update_sql = "UPDATE REVIEWS SET REVIEW_COMMENT=?, RATING=? WHERE REVIEW_ID =? ";
				Scanner update = new Scanner(System.in);
//				System.out.print("제목 : ");
//				board.setBtitle(update.next());
				System.out.print("내용 : ");
				rev.setComment(update.next());
				System.out.print("별점(숫자로 입력) : ");
				rev.setRating(update.nextFloat());
				System.out.print("글 번호 : ");
				rev.setReview_id(update.nextInt());
				PreparedStatement pstmt2 = conn.prepareStatement(update_sql);
				pstmt2.setString(1,rev.getComment());
				pstmt2.setFloat(2, rev.getRating());
				pstmt2.setInt(3, rev.getReview_id());
				pstmt2.executeUpdate();
				pstmt2.close();
				
			}
			
			else if(num==3) {
				String delete_sql = "DELETE FROM REVIEWS WHERE REVIEW_ID =?";
				Scanner delete = new Scanner(System.in);
				System.out.print("삭제할 게시글의 번호 : ");
				int delNum= delete.nextInt();
				PreparedStatement pstmt3 = conn.prepareStatement(delete_sql);
				pstmt3.setInt(1,delNum);
				pstmt3.executeUpdate();
				pstmt3.close();
				
			}
			else if (num ==4) {
				
				String tr_sql="TRUNCATE TABLE REVIEWS"; //리뷰테이블 초기화
				PreparedStatement pstmt4 = conn.prepareStatement(tr_sql);
				pstmt4.executeUpdate();
				
				String s_rd=sqlS_string(4); //시퀀스 초기화
				PreparedStatement pstmt4_1 = conn.prepareStatement(s_rd);
				System.out.println(s_rd);
				try {
				pstmt4_1.executeUpdate(); //없는데 또 드랍하려고 할 때의 에러 잡아주기 
				}catch (Exception e) {
					
				}
				
				String s_rc=sqlS_string(2); //시퀀스 생성
				PreparedStatement pstmt4_2 = conn.prepareStatement(s_rc);
				pstmt4_2.executeUpdate();
				
			}
			
			
			String sql = "SELECT REVIEW_ID, GUEST_NAME, REVIEW_COMMENT, RATING, REVIEW_DATE FROM REVIEWS ORDER BY REVIEW_ID DESC";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// System.out.println("아예안들어감??");
				rev.setReview_id(rs.getInt("REVIEW_ID"));
				rev.setGuestname(rs.getString("GUEST_NAME"));
				rev.setComment(rs.getString("REVIEW_COMMENT"));
				rev.setRating(rs.getFloat("RATING"));
				rev.setReview_date(rs.getDate("REVIEW_DATE")); 

				double starnum = rev.getRating();
				String starstr = "";

				if (starnum != 0.0f) {
					double starnum2 = starnum % 1.0;
					double starnum3 = starnum / 1.0;

					if (starnum2 == 0.5) {
						for (int i = 0; i < starnum3-1; i++) {
							starstr += "★";
						}
						starstr += "☆";
					} else {
						for (int i = 0; i < starnum3; i++) {
							starstr += "★";
						}
					}
				}

				System.out.printf("%-6s%-12s%-16s%-10s%s\n", rev.getReview_id(), rev.getGuestname(), 
						 rev.getReview_date(), starstr, rev.getComment());
			}
			rs.close();
			pstmt.close(); //여기까지 게시글 출력
			
			}catch(Exception e) {
				System.out.println("으아악에러");
			}
			
	}

//	public static void main(String[] args) {
//		int right = 0; 
//
//		while (right == 0) {
//			System.out.println();
//			System.out.println("==============[ 메인 페이지 ]==============");
//			System.out.println("=== 1. 회원가입 2. 로그인 3. 호텔정보 4. 종료 ===");
//
//			Scanner scanum = new Scanner(System.in);
//			int num = scanum.nextInt();
//
//			if (num == 1) {
//				Test_Join();
//			} else if (num == 2) {
//				System.out.println("로그인 완료했다는 가정 하에 진행");
//				Test_MemberInfo();
//			} else if (num == 3) {
//				System.out.println("호텔 정보~");
//
//			} else if (num == 4) {
//				System.out.println("정말 종료하시겠습니까? 1. yes / 2. no");
//
//				while (num == 4) {
//					int num2 = scanum.nextInt();
//					if (num2 == 1) {
//						System.out.println("프로그램이 종료됩니다.");
//						System.exit(0);
//					} else if (num2 == 2) {
//						System.out.println("메인 화면으로 돌아갑니다.");
//						System.out.println();
//						System.out.println();
//						break;
//					} else {
//						System.out.println("정확한 값을 입력해주세요 : 1. yes / 2. no");
//					}
//				}
//			}
//		}
//
//	}

}
