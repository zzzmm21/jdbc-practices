package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest02 {

	public static void main(String[] args) {
		insert("시스템2");
		insert("마케팅2");
		insert("운영2");
	}

	private static Boolean insert(String name) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. JDBC Driver Class Loading
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/employees?charset=utf8";
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			//3. Statement 준비
			String sql = 
					" insert" +
					"   into dept" +
					" values (null, ?)";
			pstmt = conn.prepareStatement(sql);
			//4. Binding
			pstmt.setString(1, name);
			
			//5. SQL 실행
		
			
			int count = pstmt.executeUpdate();
			
			//6. 결과 처리
			
			result = count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
