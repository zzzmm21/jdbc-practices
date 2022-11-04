package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SelectTest02 {
	

		public static void main(String[] args) {
			search("pat");
		}

		private static void search(String keyword) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				//1. JDBC Driver Class Loading
				Class.forName("org.mariadb.jdbc.Driver");
				
				//2. 연결하기
				String url = "jdbc:mysql://127.0.0.1:3306/employees?charset=utf8";
				conn = DriverManager.getConnection(url, "hr", "hr");
				
				//3. Statement 준비
				String sql = 
						"select emp_no, first_name" +
						"  from employees" +
						" where first_name like ?" ;
					
				pstmt = conn.prepareStatement(sql);
				
				//4. Binding
				pstmt.setString(1, '%'+ keyword + '%');
				//5. SQL 실행

				
				rs = pstmt.executeQuery(); // () 은 바인딩된 값이 아니라서 sql쓰면 안됨 ㅇㅇ
				
				//5. 결과 (ResultSet)처리
				while(rs.next()) {
					Long empNo = rs.getLong(1);
					String firstName = rs.getString(2);
					
					System.out.println(empNo + ":" + firstName);
				}
				
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패:" + e);
			} catch (SQLException e) {
				System.out.println("Error:" + e);
			} finally {
				try {
					if(rs != null) {
						rs.close();
					}
					
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
		}

	}