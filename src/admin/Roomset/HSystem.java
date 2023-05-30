package admin.Roomset;

import java.util.Scanner;

import admin.Adminpage;

public class HSystem {
	Scanner scanner=new Scanner(System.in);
	static Adminpage adminpage=new Adminpage();
	
	public void HeatingSystem() {
		System.out.println("전체 객실온도 설정");
		System.out.print("온도:");
		int ondo=scanner.nextInt();
		System.out.println("현재온도는"+ondo+"입니다");
		adminpage.page();
	}

}
