package admin;

import java.util.Scanner;

import admin.Black.black;
import admin.Reservations.Rv;
import admin.Review.AdminReview;
import admin.Roomset.Check;
import admin.Roomset.HSystem;
import admin.Roomset.RoomHelp;
import admin.hotelser.Hotelhelp;
import admin.hotelser.Hotelser;
import admin.usermodify.modfiy;

public class Adminpage {
	static Scanner scanner=new Scanner(System.in);
	static Hotel.ConTest con=new Hotel.ConTest();
	static Adminpage adminpage=new Adminpage();
	static AdminReview adminre=new AdminReview();
	static Hotelser hotelser=new Hotelser();
	static Hotelhelp hotelhelp=new Hotelhelp();
	static Rv rv=new Rv();
	static money mo=new money();
	static modfiy mod=new modfiy();
	static Hotel.Main main = new Hotel.Main();
	
	public void page() {
		System.out.println("아래 메뉴에서 관리하실 항목을 선택해주십시오");
		System.out.println("+-------관리자 모드-----+");
		System.out.println("|1. 객실 설정 관리 모드   |");
		System.out.println("|2. 호텔 설명 관리 모드   |");
		System.out.println("|3. 총 매출 전표 관리표   |");
		System.out.println("|4. 호텔고객 관리 모드    |");
		System.out.println("|5. 호텔관리 모드 종료    |");
		System.out.println("+--------------------+");
		System.out.print("입력값:");
		int sele=scanner.nextInt();
		switch (sele) {
			case 4:guestmodify();break;
			case 1:Roomsetting();break;
			case 2:HotelSetting();break;
			case 3:Sales();break;
			case 5:main.page();break;
			default :
				System.out.println("값을 잘못 입력하셧습니다 ");//이외의 값 입력시 반복시킨다 
				page();
		}
	}
	
	
	public void Blacklist() {
		black bla=new black();
		System.out.println("아래 메뉴에서 관리하실 항목을 선택해주십시오");
		System.out.println("+----블랙 고객 관리----+");
		System.out.println("|1. 블랙 고객 추가     |");
		System.out.println("|2. 블랙 고객 삭제     |");
		System.out.println("|3. 블랙 고객 조회     |");
		System.out.println("|4. 블랙 고객 종료     |");
		System.out.println("|5. 메인 메뉴 이동     |");
		System.out.println("+------------------+");
		System.out.print("입력값:");
		int sele=scanner.nextInt();
		switch (sele) {
			case 1:bla.plusblack();;break;
			case 2:bla.delblack();;break;
			case 3:bla.selblack();;break;
			case 4:adminpage.guestmodify();;break;
			case 5:adminpage.page();break;
			default :
				System.out.println("값을 잘못 입력하셧습니다 ");//이외의 값 입력시 반복시킨다 
				Blacklist();
		}
	}

	
	public void Roomsetting() {
		Check check=new Check();
		HSystem hs=new HSystem();
		RoomHelp RH=new RoomHelp();
		Adminpage page= new Adminpage();
		System.out.println("아래 메뉴에서 관리하실 항목을 선택해주십시오");
		System.out.println("+--객실 설정 관리모드--+");
		System.out.println("|1. 객실 온도 조절    |");
		System.out.println("|2. 객실 체크  인    |");
		System.out.println("|3. 객실 체크 아웃    |");
		System.out.println("|4. 객실 금액 변경    |");
		System.out.println("|5. 객실 모드 종료    |");
		System.out.println("|6. 메인 메뉴 이동    |");
		System.out.println("+-----------------+");
		System.out.print("입력값:");
		int sele=scanner.nextInt();
		switch (sele) {
			case 1:hs.HeatingSystem();;
			case 2:check.CheckIn();break;
			case 3:check.CheckOut();break;
			case 4:RH.Helper();
			case 5:page.page();
			case 6:adminpage.page();break;
			default :
				System.out.println("값을 잘못 입력하셧습니다 ");
				Roomsetting();
		}
	}
	
	public void Reviewmodify() {
		System.out.println("아래 메뉴에서 관리하실 항목을 선택해주십시오");
		System.out.println("+-----리뷰 관리 모드-----+");
		System.out.println("|1. 고객 리뷰 조회및 삭제 |");
		System.out.println("|2. 고객 리뷰 관리종료   |");
		System.out.println("|3. 메인 메뉴 이동      |");
		System.out.println("+--------------------+");
		System.out.print("입력값:");
		int sele=scanner.nextInt();
		switch (sele) {
			case 1:adminre.selReview();
			case 2:adminpage.HotelSetting();break;
			case 3:adminpage.page();break;
			default :
				System.out.println("값을 잘못 입력하셧습니다 ");//이외의 값 입력시 반복시킨다 
				Reviewmodify();
		}
	}
	
	public void HotelSetting() {
		System.out.println("아래 메뉴에서 관리하실 항목을 선택해주십시오");
		System.out.println("+----호텔 서비스 관리 모드---+");
		System.out.println("|1. 호텔 소개글 변경        |");
		System.out.println("|2. 호텔 서비스 관리       |");
		System.out.println("|3. 호텔 리뷰 관리모드      |");
		System.out.println("|4. 호텔 서비스 관리모드종료 |");
		System.out.println("+----------------------+");
		System.out.print("입력값:");
		int sele=scanner.nextInt();
		switch (sele) {
			case 1:hotelhelp.Help();break;
			case 2:hotelser.Hotelservice();break;
			case 3:Reviewmodify();break;
			case 4:adminpage.page();break;
			default :
				System.out.println("값을 잘못 입력하셧습니다 ");//이외의 값 입력시 반복시킨다 
				HotelSetting();
		}
	}
	
	
	public void Sales() {
		System.out.println("아래 메뉴에서 관리하실 항목을 선택해주십시오");
		System.out.println("+-----매출 관리 모드-----+");
		System.out.println("|1. 호텔 총 매출 관리 조회 |");
		System.out.println("|2. 관리자 메뉴로 이동    |");
		System.out.println("+---------------------+");
		System.out.print("입력값:");
		int sele=scanner.nextInt();
		switch (sele) {
			case 1:mo.summonet();break;
			case 2:adminpage.page();break;
			default :
				System.out.println("값을 잘못 입력하셧습니다 ");//이외의 값 입력시 반복시킨다 
		}
		
	}
	
	public void guestmodify() {
		System.out.println("아래 메뉴에서 관리하실 항목을 선택해주십시오");
		System.out.println("+-----고객 관리 모드----+");
		System.out.println("|1. 블랙 고객 관리 모드  |");
		System.out.println("|2. 고객 정보 변경      |");
		System.out.println("|3. 고객 예약 현황 확인  |");
		System.out.println("|4. 호텔관리 모드 종료   |");
		System.out.println("+-------------------+");
		System.out.print("입력값:");
		int sele=scanner.nextInt();
		switch (sele) {
			case 1:adminpage.Blacklist();break;
			case 2:mod.useridmodfiy();break;
			case 3:rv.rv();break;
			case 4:adminpage.page();break;
			default :
				System.out.println("값을 잘못 입력하셧습니다 ");//이외의 값 입력시 반복시킨다 
		}
		
	}
}
