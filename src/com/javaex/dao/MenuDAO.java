package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.MenuVO;



public class MenuDAO {

	
	
	// 필드
		private Connection conn = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;

		private String driver = "com.mysql.cj.jdbc.Driver";
		private String url = "jdbc:mysql://localhost:3306/web_db";
		private String id = "web";
		private String pw = "web";

	
	//생성자
	public MenuDAO() {
		
	}
	
	
	// DB연결 메소드 - 공통
	private void connect() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 자원 정리 메소드 -공통
	private void close() {
		// 5. 자원정리
		try {

			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	
	
	//메뉴 등록
	public int menuInsert(String menuName, int menuPrice, int categoryId) {

		int count = -1;

		// 0. import java.sql.*;
		// Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;

		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		this.connect();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행

			// SQL문 준비
			String query = "";
			query += " insert into menu ";
			query += "values(null, ?, ?, ?,now()) ";
			//System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, menuName);
			pstmt.setInt(2, menuPrice);
			pstmt.setInt(3, categoryId);

		

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
		} catch (SQLException e) {
			System.out.println("error:" + e);
		
		}
		
		// 5. 자원정리
		this.close();
		//System.out.println(count + "건이 등록되었습니다.");
		return count;
		
	}//메뉴등록끝
	
	
	
	//메뉴수정
	public int menuUpdate(int menuId, String menuName, int menuPrice) {

		int count = -1;

		// 0. import java.sql.*;
		// 1. JDBC 드라이버 (mysql) 로딩
		// 2. Connection 얻어오기
		this.connect();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행

			// SQL문 준비
			String query = "";
			query += " update menu ";
			query += " set 	menu_name = ? ";
			query += "     ,menu_price = ? ";
			query += " where menu_id = ? ";
			//System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, menuName);
			pstmt.setInt(2, menuPrice);
			pstmt.setInt(3, menuId);
			

			// 실행
			count = pstmt.executeUpdate();

		// 4.결과처리		
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		// 5. 자원정리
		this.close();
		System.out.println("메뉴가 수정되었습니다.");
		return count;
	}// 메뉴수정 끝
	
	
	//메뉴 삭제
		public int menuDelete(int menuId) {
			
			int count = -1;

			// 0. import java.sql.*;
			// 1. JDBC 드라이버 (mysql) 로딩
			// 2. Connection 얻어오기
			this.connect();
			
			
			try {

				// 3. SQL문 준비 / 바인딩 / 실행

				// SQL문 준비
				String query = "";
				query += " delete from menu ";
				query += " where menu_id = ? ";
				//System.out.println(query);
				
				
				//바인딩
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,menuId);  
				

				// 실행
				count = pstmt.executeUpdate();

			// 4.결과처리
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			//자원정리
			this.close();
			System.out.println(count + "건 삭제");
			return count;
			
		} //delete
		
		
		//메뉴 리스트
		public List<MenuVO> menuSelect(){
			
			//리스트
			List<MenuVO> menuList = new ArrayList<MenuVO>();
			
			// 0. import java.sql.*;
			
			// 1. JDBC 드라이버 (MySQL) 로딩
			// 2. Connection 얻어오기
			this.connect();	

			try {
				
				// 3. SQL문 준비 / 바인딩 / 실행
				// SQL문 준비
				String query = "";
				query +="select 	m.menu_id ";
				query +="          ,m.menu_name ";
				query +="      	   ,m.menu_price ";
				query +="          ,c.category_name ";
				query +="          ,c.category_id ";
				query +=" from menu m, category c ";
				query +=" where m.category_id = c.category_id; ";
				
		
				
				// 바인딩 
				pstmt = conn.prepareStatement(query);
				
				//실행
				rs = pstmt.executeQuery();

			    // 4.결과처리 (java 리스트로 만든다)
				while(rs.next()) {
					
					int menuId = rs.getInt("menu_id");
					String menuName = rs.getString("menu_name");
					int menuPrice = rs.getInt("menu_price");
					String categoryName = rs.getString("category_name");
					int categoryId = rs.getInt("category_id");
		
					
					//데이터 객체로 만들기(묶기)
					MenuVO menuVO = new MenuVO(menuId, menuName, menuPrice, categoryName, categoryId);
					
					//묶은 데이터를 리스트에 달기
					menuList.add(menuVO);
				}

				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			
			// 5. 자원정리
			this.close();
			
			return menuList;
			
		}



		
		
		
		
	
}
