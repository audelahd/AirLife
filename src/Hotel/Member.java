package Hotel;

import java.sql.Connection;
import java.util.Scanner;

public class Member {
	public static String m_Info(String user_num) { //회원 정보
		ConTest contest = new ConTest();
		Connection conn =contest.ConOn();
		Scanner scan = new Scanner(System.in); // 입력
		int i = -1;

		
		try {
			
			while (i < 0) {
				System.out.println();
				System.out.println("==============[ 회원 페이지 ]==============");
				System.out.println("=== 1. 예약하기 2. 리뷰 3. 회원 정보 4. 종료 ===");

				Reservation t_reser = new Reservation();
				int memnum = scan.nextInt();

				if (memnum == 1) {


					while (i < 0) {
						System.out.println();
						System.out.println("==============[ 예약 페이지 ]==============");
						System.out.println("=== 1. 예약정보 입력 2. 예약 조회 3. 뒤로가기 ===");
						int resvnum = scan.nextInt();

						if (resvnum == 1) {
							t_reser.m_Reservation(1, user_num);
						}
						else if (resvnum == 2) {
							t_reser.m_Reservation(2, user_num);
						}

						else if (resvnum == 3) {
							t_reser.m_Reservation(3, user_num);
							System.out.println("회원 페이지로 돌아갑니다.");
							break;
						}

						else {
							System.out.print("다시 입력 : ");
						}
					}
				}

				else if (memnum == 2) {
					Revieww t_review = new Revieww();
					while (i < 0) {
						System.out.println();
						System.out.println("==============[ 리뷰 페이지 ]==============");
						System.out.println("=== 1. 리뷰 작성 2. 리뷰 수정 3. 리뷰 삭제 4. 회원 페이지===");
						int revnum = scan.nextInt();

						if (revnum == 1) {
							t_review.m_Review(1, user_num);
						}

						else if (revnum == 2) {
							t_review.m_Review(2, user_num);
						}

						else if (revnum == 3) {
							t_review.m_Review(3, user_num);
						} 
			//				else if (revnum==4){
//							t_review.m_Review(4, user_num);
//						}
						else {
							System.out.println("회원 페이지로 돌아갑니다.");
							break;
						}
					}

				}

				else if (memnum == 3) {
					System.out.println("회원 정보를 표시합니다.");
					memberinformation mem = new memberinformation();
					mem.memberinformation(user_num);
				}

				else if (memnum == 4) {
					System.out.println("메인 페이지로 돌아갑니다.");
					Main.page();
				}

				else {
					System.out.println("숫자를 제대로 입력해주세요.");
				}
			}
			contest.ConOff(conn);

		} catch (Exception e) {
			System.out.println("SQL오류인듯");
		}
		
		return user_num;

	}
	

}
