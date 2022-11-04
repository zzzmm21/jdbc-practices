package com.bitacademy.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitacademy.bookshop.vo.BookVo;

import test.DeptVo;

public class BookDao {

	public boolean insert(BookVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql = "insert into book values(null, ?, ?, ?)";			
			pstmt = conn.prepareStatement(sql);
			
			//4. Binding
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getStatus());
			pstmt.setLong(3, vo.getAuthorNo());
			
			//5. SQL 실행
			int count = pstmt.executeUpdate();
			
			//6. 결과 처리
			result = count == 1;
			
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

	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = 
				"   select a.no, a.title, b.name, a.status" +
				"     from book a, author b" +
				"    where a.author_no = b.no" +
				" order by a.no asc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			//6. 결과(ResultSet) 처리
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String authorName = rs.getString(3);
				String status = rs.getString(4);
				
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setAuthorName(authorName);
				vo.setStatus(status);
				
				result.add(vo);
			}
			
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
		
		return result;
	}
	public boolean updateStatus(long no ,String status) {
		boolean result = false;
		System.out.println(result ? "성공" : "실패");
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			
			conn = getConnection();
			
			//3. Statement 생성
			String sql = 
					"update book" +
					"   set stauts = ?" +
				    " where no = ?";
				
			pstmt = conn.prepareStatement(sql);
			
			//4. SQL 실행
			pstmt.setString(1,status);
			pstmt.setLong(2, no);
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			

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
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found:" + e);
		} 
		
		return conn;
	}

	
}