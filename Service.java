package Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Service {
	static ConTest con = new ConTest();
	
	public static void service() {
		try {
			System.out.println("====== 호텔의 공용 서비스 목록입니다. ======");
		Connection conn = con.ConOn();
		String sql = "SELECT S_NAME, S_CONTENT FROM SERVICE WHERE not serox in 10";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			
			System.out.printf("   %6s   %-6s \n",rs.getString("S_NAME"), rs.getString("S_CONTENT") );
		}
		rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
