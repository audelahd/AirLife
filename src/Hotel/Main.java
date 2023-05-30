package Hotel;




import java.util.Scanner;

public class Main {
	static Scanner scanner=new Scanner(System.in);
	
	
	public static void page() {
		
		
		Login login = new Login();
		Join join = new Join();
		Endpage endpage = new Endpage();
		Revieww review = new Revieww();
		memberinformation mem = new memberinformation();
		Service ser = new Service();
		
		System.out.println("======================================\n");
		
		System.out.println("      호텔 페이지에 오신 것을 환영합니다.\n");
		
		System.out.println("========= 원하시는 메뉴를 선택해 주세요 =========\n");
		
		System.out.println("========      1     호텔 소개      =======");
		System.out.println("========      2      로그인       =======");
		System.out.println("========      3     회원가입       =======");
		System.out.println("========      4     호텔 리뷰      =======");
		System.out.println("========      5   호텔 공용 서비스   =======");
		System.out.println("========      6    프로그램 종료    =======\n");
	
		System.out.println(" ◎ 메뉴 번호 입력 :  ");

		
		int sele=scanner.nextInt();
		switch (sele) {
		case 1:mem.introduction();;break;
		case 2:login.Loginpage();break;
		case 3:join.m_Join();break;
		case 4:review.all_Review();break;
		case 5:ser.service();break;
		case 6:endpage.End(1);break;
		default :
		System.out.println("값을 잘못 입력하였습니다. ");//이외의 값 입력시 반복시킨다 
		
		}
		page();
	}
	

	
	public static void main(String[] args) {
		page();
	}
	
}
		