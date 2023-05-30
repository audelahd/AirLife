package Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Revieww {
	
	static Review rev = new Review();
	static Reser res = new Reser();
	static Room room = new Room();
	
	public static void all_Review() {
		ConTest contest = new ConTest();
		Connection conn = contest.ConOn();
		try {
			String sql = "SELECT REVIEW_ID, REVIEW_COMMENT, RATING, REVIEW_DATE FROM REVIEWS ORDER BY review_id DESC ";
//			String sql2 = "SELECT USERNAME FROM USERS";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
//			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			ResultSet rs = pstmt.executeQuery();
//			ResultSet rs2 = pstmt2.executeQuery();
//			rs2.next();
			while (rs.next()) {
				// System.out.println("아예안들어감??");
				rev.setReview_id(rs.getInt("REVIEW_ID"));
				rev.setComment(rs.getString("REVIEW_COMMENT"));
				rev.setRating(rs.getFloat("RATING"));
				rev.setReview_date(rs.getDate("REVIEW_DATE")); 	
//				rev.setUsername(rs2.getString("USERNAME"));
				
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

				System.out.printf("%s번 | 작성일자 : %-10s | 별점 : %-5s  %-5s\n", rev.getReview_id(), /*rev.getUsername(),*/
						 rev.getReview_date(), starstr, rev.getComment());
			}
			
			
		}catch (Exception e) {
			System.out.println("오류");
		}
	}
	
	public static void m_Review(int num, String user_num) {

		ConTest contest = new ConTest();
		Connection conn =contest.ConOn();
		try {
//			System.out.println(user_num);
		
		if(num==1) {
		String insert_sql= "INSERT INTO REVIEWS(REVIEW_ID, REVIEW_COMMENT,RATING, REVIEW_DATE, USER_NUM) "
				+ "VALUES (SEQ_RNUM.NEXTVAL,?,?,SYSDATE, "+user_num +") ";
		
		
		System.out.println("==============[ 리뷰 작성 ]==============");
		
		Scanner insert = new Scanner(System.in);
		
		System.out.print("내용 : ");
		rev.setComment(insert.next());
		System.out.print("별점(숫자로 입력) : ");
		rev.setRating(insert.nextFloat());
		
		
		PreparedStatement pstmt1 = conn.prepareStatement(insert_sql);
		//pstmt1.setString(1, board.getBtitle());

		pstmt1.setString(1, rev.getComment());
		pstmt1.setFloat(2,rev.getRating());
		//pstmt1.setString(5, board.getBwriter());
		pstmt1.executeUpdate();
		pstmt1.close();
		}
		
		else if(num==2) {
			ResultSet rs;
			System.out.println("리뷰 수정하는 페이지 입니다.");
			String user_select_sql = "SELECT * FROM reviews WHERE user_num=?";
			String update_sql = "UPDATE REVIEWS SET REVIEW_COMMENT=?, RATING=? WHERE REVIEW_ID =? ";
			Scanner update = new Scanner(System.in);
//			System.out.print("제목 : ");
//			board.setBtitle(update.next());
			System.out.println("입력하신 리뷰 작성글입니다.");
			
			
			PreparedStatement pstmt9 = conn.prepareStatement(user_select_sql);
			pstmt9.setString(1,user_num);
			rs=pstmt9.executeQuery();
			while(rs.next()) {	
				String frs1=rs.getString("review_id");
				String frs2 = "";
				float frs0=rs.getFloat("rating");
				if (frs0 != 0.0f) {
					double starnum2 = frs0 % 1.0;
					double starnum3 = frs0 / 1.0;
						if (starnum2 == 0.5) {
							for (int i = 0; i < starnum3-1; i++) {
								frs2 += "★";
							}
							frs2 += "☆";
						} else {
							for (int i = 0; i < starnum3; i++) {
						frs2 += "★";
							}
						}
				}
			String frs3=rs.getString("review_comment");
			System.out.print(frs1);
			System.out.print(" "+frs2);
			System.out.println(" "+frs3);
		}
		pstmt9.close();
		
			System.out.print("\n수정할 내용 : ");
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
			
			String s_rd=String_.sqlS_string(4); //시퀀스 초기화
			PreparedStatement pstmt4_1 = conn.prepareStatement(s_rd);
			System.out.println(s_rd);
			try {
			pstmt4_1.executeUpdate(); //없는데 또 드랍하려고 할 때의 에러 잡아주기 
			}catch (Exception e) {
				
			}
			
			String s_rc=String_.sqlS_string(2); //시퀀스 생성
			PreparedStatement pstmt4_2 = conn.prepareStatement(s_rc);
			pstmt4_2.executeUpdate();
			
		}
		
		
		String sql = "SELECT REVIEW_ID, REVIEW_COMMENT, RATING, REVIEW_DATE FROM REVIEWS WHERE user_num ='"+user_num+"' ";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			// System.out.println("아예안들어감??");
			rev.setReview_id(rs.getInt("REVIEW_ID"));
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

			System.out.printf("%-6s%-16s%-10s%s\n", rev.getReview_id(), 
					 rev.getReview_date(), starstr, rev.getComment());
		}
		rs.close();
		pstmt.close(); //여기까지 게시글 출력
		
		}catch(Exception e) {
//			System.out.println("오류");
			e.printStackTrace();
		}
		
}
}
