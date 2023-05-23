package Hotel;

import java.util.Scanner;


//import admin.Adminpage;

public class Endpage {

	public static Scanner scanner=new Scanner(System.in);
	
	public void End(int num) {
		Login login=new Login();
//		Adminpage adminpage=new Adminpage();
		int retry;
	    
		
		
		for (int i = 0; i < 3; i++) {
			
		
		System.out.println("프로그램을 종료하시겠습니까?");
		
		do {
		System.out.println("(1)종료 \n(2)이전메뉴실행\n(3)초기화면");
		System.out.print("입력:");retry=scanner.nextInt();
		if(retry==1 ) {
			
			System.out.println("프로그램을 종료합니다");
			System.exit(1);
		}else if(retry==2) {
			switch (num) {
			case 1:login.Loginpage();break;
//			case 2:adminpage.page();break;
			case 3:;break;
			case 4:;break;
			case 5:;break;
			case 6:;break;
			case 7:;break;
			}
		}else if(retry==3) {
			System.out.println("메인메뉴로 이동합니다");
			//초기화면 메소드 
		
		
		}else if(i==0) {
			
			System.out.println((i +1) +"회 잘못입력하셨습니다. 재입력 해주시길 바랍니다");
			break;
		}else if(i==1) {
			
			System.out.println((i+1)+"회 잘못입력하셨습니다. 재입력 해주시길 바랍니다");
			break;
		}else if(i==2) {
			
			System.out.println((i+1) +"회 입력오류로 강제종료하겠습니다.");
			System.exit(0);
		
		}
		
	
	}while(true);
	}
}
}