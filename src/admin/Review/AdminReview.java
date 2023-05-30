package admin.Review;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import admin.Adminpage;

public class AdminReview {

	static Adminpage adminpage=new Adminpage();
	Scanner scanner=new Scanner(System.in);
	static Hotel.ConTest con=new Hotel.ConTest();
	Statement stmt;
	ResultSet rs;
	
	public void selReview() {
		System.out.println("모든 리뷰를 조회합니다");
		String query="select review_id,review_comment,user_num from reviews";//쿼리문작성 가져올값은 정해져있기떄문에 고정식으로 만듬
		//가져올값은 리뷰의제목 리뷰내용 리뷰의번호
		try {
		Connection conn=con.ConOn();
		stmt=conn.createStatement();
		rs=stmt.executeQuery(query);
		while(rs.next()){//쿼리문덥데이트후 값을 하나씩 저장한후 출력함 while 문을 사용하여 모든값을 출력할수있다
			String review_id=rs.getString("review_id");
			String review_comment=rs.getString("review_comment");
			String user_num=rs.getString("user_num");
			System.out.println(review_id);
			System.out.println(review_comment);
			System.out.println(user_num);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("리뷰를 삭제하시려면 1번 이전메뉴로 돌아가시려면 2번을 눌러주십시오");
		System.out.print("입력값:");
		int sel=scanner.nextInt();
		if(sel==1){//1번이면 리뷰삭제진행
		System.out.println("삭제할 리뷰의 게시글 번호를 입력해주십시오");
		System.out.print("입력:");//리뷰의 게시글 번호를 위에서 표기하기떄문에 그것을 조건으로 걸어서삭제할수있따
		int del=scanner.nextInt();
		String qurey="delete from reviews where review_id="+del;//입력받은 번호를 넣어서 삭제시작 번호가없어도 따로 오류가 발생하지않는다 문법상 틀린것은 없기떄문에
		try {
			Connection conn=con.ConOn();
			stmt=conn.createStatement();
			stmt.executeUpdate(query);//업데이트문을 사용하여 리턴받는값이없이 db에만 업데이트한다
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("정상적으로 삭제되었습니다 ");
		selReview();//재조회를 시켜서 삭제를 확인할수있다
		}else if(sel==2){
			System.out.println("이전메뉴로 돌아갑니다");
			adminpage.Reviewmodify();//2번을 누르면 바로 이전메뉴로 돌아간다
		}else {
			System.out.println("정확하지 않은 입력값입니다");//만약 입력값이 1~2번이 아니면 처음부터다시실행 반복시킨다 
			selReview();
		}
	}
	
	

}
