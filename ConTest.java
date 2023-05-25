package Hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConTest {

	private static Connection conn = null;
	static Statement stmt;

	static ResultSet rs;

	public Connection ConOn() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE", 
					"gp_java", // 사용자아이디
					"12345" // 사용자비번
			);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	void ConOff(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}

	}
	public String seid(String id) {
		String _id=null;
		try {
		Connection conn=ConOn();
		stmt=conn.createStatement();
		String query="SELECT user_id from users where user_id='"+id+"'";
		rs=stmt.executeQuery(query);
			while(rs.next()) {
			_id=rs.getString("user_id");
			}
			stmt.close();
			rs.close();
			ConOff(conn);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return _id;
	}
	
	public String sepw(String id) {
		String _pw=null;
		try {
		Connection conn=ConOn();
		stmt=conn.createStatement();
		String query="SELECT password from users where user_id='"+id+"'";
		rs=stmt.executeQuery(query);
		while(rs.next()) {
			_pw=rs.getString("password");
			}
		stmt.close();
		rs.close();
		ConOff(conn);
		}catch (Exception e) {
		e.printStackTrace();
		}

		return _pw;
	}
	public String senum(String id) {
		String _num=null;
		try {
		Connection conn=ConOn();
		stmt=conn.createStatement();
		String query="SELECT user_num from users where user_id='"+id+"'";
		rs=stmt.executeQuery(query);
		while(rs.next()) {
			_num=rs.getString("user_num");
			}
		stmt.close();
		rs.close();
		ConOff(conn);
		}catch (Exception e) {
		e.printStackTrace();
		}

		return _num;
	}
}