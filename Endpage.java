package Hotel;



import java.util.Scanner;

public class Endpage {

	public void End(int num) {
		Scanner scanner=new Scanner(System.in);
		int retry;
		System.out.println("프로그램을 종료하시겠습니까?");
		do {
		System.out.println("(1)종료 \n(2)초기화면");
		System.out.print("입력:");retry=scanner.nextInt();
		if(retry==1 ) {
			System.out.println("프로그램을 종료합니다");
			System.exit(1);
		}else if(retry==3) {
			System.out.println("메인메뉴로 이동합니다");
			Main.page();
		}
		}while(retry!=1&&retry!=2);
	}
}