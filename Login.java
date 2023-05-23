package Hotel;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

//import Endpage;
//import admin.Adminpage;

public class Login {
	
	static Scanner scanner=new Scanner(System.in);
//	static Adminpage adminPage=new Adminpage();
	static 	String[][] limit_id=new String[10][2];
	static 	Calendar cal = Calendar.getInstance();
	static SimpleDateFormat sdformat = new SimpleDateFormat("HH:mm:ss");
	static 	Endpage end=new Endpage();
	
	public static void Loginpage() {
		int limit=0;
		String _id;
		do {
			System.out.println("로그인화면");
			System.out.println("이전메뉴로 돌아가실려면 1번을 눌러주세요");
			System.out.print("아  이  디:");
			_id=scanner.next();
			if(_id.equals("1")) {//아이디에  1을 입력할시
				System.out.println("메인으로");
			}else {//1이아니면 else문을 실행
				for (int i = 0; i <10; i++) {//제한시간이 걸려있는아이디이 인지 체크후 비밀번호를 입력받음
					if(_id.equals(limit_id[i][0])) {
						LimitTime(limit_id[i][1], i);
					}	
				}
			}
			System.out.print("비 밀 번 호:");
			String _pwd=scanner.next();
			//데이터베이스 호출문 비밀번호와 아이디를 받아옴
			String id="";
			String pwd="";
			if(!_id.equals(id)||!_pwd.equals(pwd)){//입력받은값과 db의 값을 비교
				System.out.println("로그인 정보가 틀립니다 재입력 바랍니다.");
				limit++;
			}else if(_id.equals(id)||_pwd.equals(pwd)) {
				if(id.equals("admin")) {//관리자의 경우 아이디가 같으면 관리자모드로 이동
					System.out.println("관리자 모드로 진입합니다");
//					adminPage.page();
				}else {
				System.out.println(id+"회원님 방문을 환영합니다");//일반회원
				//회원의 정보 조회 페이지 링크
				}
			}
		}while(limit<3);//아이디와 비밀번호가 3회이상 오류가발생시 제한
		Date today = new Date ();//현재시간을 구함
		String day=sdformat.format(today);//현재시간을 형식에맞게 문자열로변환
		for(int i=0;i<=10;i++) {
			if(limit_id[i][0]==null) {//빈 문자열을 찾음 값이 null이면 값을 저장 아니면 다음으로 넘어감 
				limit_id[i][0]=_id;//아이디를 저장
				limit_id[i][1]=day;//현재시간을 저장시킴
				LimitTime(day,i);//제한 프로그램으로 값을 넘김 
			}
		}
	}
	public static void LimitTime(String time,int i) {
		try {
		Date _nowTime=new Date();//현재시간을 가져온다
		String _nowTime1=sdformat.format(_nowTime);//현재시간의형식을 hhmmss형식 문자열로 변환 
		Date d1=sdformat.parse(_nowTime1);//형식이바뀐 문자열을 데이터타입으로변경
		Date date = sdformat.parse(time);//가져온 문자열 time을 date타입으로 변환
		cal.setTime(date);//데이터 타입을 입력시켜줌
		cal.add(Calendar.HOUR, 1); //입력받은 타임을 1시간후로 바꿈
		String limitTime=sdformat.format(cal.getTime());// 그값을 문자열로 저장함
		System.out.println("차단 시작시간:"+time);//차단시작시간 출력
		System.out.println("차단 해제시간:"+limitTime);//1시간 이후의 시간을출력
		Date d2=sdformat.parse(limitTime);//형식이바뀐 문자열을 데이터타입으로변경
		System.out.println("d1"+d1);
		System.out.println("d2"+d2);
		if(d1.after(d2)) {//두개를 비교 after과 before 둘중하나사용 after의경우 현재시간보다 제한된시간이 크다면 false 반대의경우 true before의경우 현재시간보다 제한된시간이 작다면 true 크다면 false을 반환
			limit_id[i][1]=null;//값을 초기화시킴
			limit_id[i][0]=null;//값을 초기화시킴
			System.out.println("아이디 로그인 차단이 해제되었습니다 ");
			Loginpage();//다시로그인페이지로 이동 
		}else {
			System.out.println("제한시간이 아직남았습니다");
			end.End(1);
		}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
		
}
