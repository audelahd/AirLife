package Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

class Join {
	
	static Review rev = new Review();
	static Reser res = new Reser();
	static Room room = new Room();
	static User user = new User();
	
	public static String m_Join() { // 회원가입
		ConTest contest = new ConTest();
		Connection conn = contest.ConOn();

		String id = null;
		String pwd = null; // while안에 들어가기 전 초기화
		int i = -1;
		Scanner scan = new Scanner(System.in); // 입력
		String user_num = "";

		try {

			String sql = "" + "INSERT INTO users(user_num, user_id, password, username, age)"
					+ "VALUES(SEQ_UNUM.nextval, ?, ?, ?, ?)"; // 값 입력용

			String sql2 = "" + "INSERT INTO users (user_num,user_id,password,username,age)"
					+ "VALUES( '0', ? ,'00000000', '중복확인용', 10)"; // 아이디 중복 확인용

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
						pstmt3.executeUpdate(); // 바로 삭제

					} catch (Exception e) { // 그래도 에러 잡아줌
						e.printStackTrace();
						System.out.println("중복된 아이디입니다.");
						System.out.println(id);
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

			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setInt(4, age);

			// 값 입력 완료

			int rows = pstmt.executeUpdate();
			if (rows != 0) { // 회원가입 성공 확인
				System.out.println("회원가입이 완료 되었습니다.");
				System.out.println("아이디 : " + id + " | 이름 : " + name);
				
			}
			
			String unumSQL = "SELECT USER_NUM FROM USERS WHERE USER_ID = "+"'"+id+"'";
			
			
//			PreparedStatement unumps = conn.prepareStatement(unumSQL);
//			System.out.println("1");
//			unumps.setString(1,user.getUserid() );
//			ResultSet unumrs = unumps.executeQuery();
//			System.out.println("2");
//			while(unumrs.next()) {
//				System.out.println("3");
//				user.setUsernum(unumrs.getString("USER_NUM"));
//				System.out.println(user.getUsernum());
//			}
			
			PreparedStatement unumps = conn.prepareStatement(unumSQL);
			ResultSet unumrs = unumps.executeQuery();
			while(unumrs.next()) {
				user.setUsernum(unumrs.getString("USER_NUM"));
				//System.out.println(user.getUsernum());
			}
			
			
			user_num = user.getUsernum();
			Member.m_Info(user_num);
			
			contest.ConOff(conn);
			pstmt.close();
			unumps.close();
			unumrs.close();
			return user_num;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user_num;

	}
}