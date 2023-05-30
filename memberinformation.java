
package Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

class memberinformation {
	

	public static void introduction() {
		ConTest contest = new ConTest(); //연결을 위해 contest 생성함 
		Connection conn = contest.ConOn(); //연결함
		try { //db 연결할때는 오류가 많이 나서 무조건 필요함
		String sql = "SELECT S_CONTENT FROM SERVICE WHERE S_NAME='호텔소개'";
		PreparedStatement ps = conn.prepareStatement(sql); //sql을 실행하려면 pre어쩌고가 필요함
		String intro = ""; // 쓰기 전에 초기화 시켜야 됨
		ResultSet rs = ps.executeQuery(); // 업데이트 시키려고 쓰는 거
		while(rs.next()) { // 값을 저장하기 위해 사용
			intro=rs.getString("S_CONTENT");
		}
		System.out.println(intro);
		ps.close();
		rs.close();
		}catch(Exception e) {
			e.printStackTrace(); 
		}
	}
	public static void memberinformation(String user_num) {
		System.out.println("============[ 회원 정보 페이지 ]============");
		System.out.println("----------------------------------------");
		ConTest contest = new ConTest(); // 연결을 위해 contest 생성함
		Connection conn = contest.ConOn(); // 연결함
		try { // db 연결할때는 오류가 많이 나서 무조건 필요함
			String sql = "SELECT user_id, username, registration_date, grade, mlg FROM USERS WHERE user_num=?";
			PreparedStatement ps = conn.prepareStatement(sql); // sql을 실행하려면 pre어쩌고가 필요함
			String num = user_num;
			ps.setString(1, num);
			String user_id = ""; // 쓰기 전에 초기화 시켜야 됨
			String username = ""; // 쓰기 전에 초기화 시켜야 됨
			String registration_date = ""; // 쓰기 전에 초기화 시켜야 됨
			String grade = ""; // 쓰기 전에 초기화 시켜야 됨
			String mlg = ""; // 쓰기 전에 초기화 시켜야 됨
			ResultSet rs = ps.executeQuery(); // 업데이트 시키려고 쓰는 거
			while (rs.next()) { // 값을 저장하기 위해 사용
				user_id = rs.getString("user_id");
				username = rs.getString("username");
				registration_date = rs.getString("registration_date");
				grade = rs.getString("grade");
				mlg = rs.getString("mlg");
			}
			System.out.print("이름: " + username);
			System.out.println("   아이디: " + user_id);
			System.out.println("회원가입 날짜: " + registration_date);
			System.out.print("등급: NORMAL");
			System.out.println("   마일리지: " + mlg);
			System.out.println("----------------------------------------");
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}