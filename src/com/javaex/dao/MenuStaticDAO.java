package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.MenuStaticVO;

public class MenuStaticDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/pos_db";
	private String id = "pos";
	private String pw = "pos";

	public MenuStaticDAO() {

	}

	// DB연결 메소드
	private void connect() { // 메인에서는 사용하지 못함

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			this.conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 자원정리 메소드 - 공통
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
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
	
	public List<MenuStaticVO> salesByMenuList() {
		List<MenuStaticVO> salesMenuList = new ArrayList<MenuStaticVO>();
		
		this.connect();
		
		try {
			
			String query = "";
			query += " select m.menu_name, ";
			query += " 		  sum(o.quantity) as total_quantity, ";
			query += " 		  sum(m.menu_price*o.quantity) as sales ";
			query += " from orders o, menu m ";
			query += " where o.menu_id = m.menu_id ";
			query += " group by m.menu_name ";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String menuName = rs.getString("menu_name");
				int totalQuantity = rs.getInt("total_quantity");
				int sales = rs.getInt("sales");
				
				MenuStaticVO menuSales = new MenuStaticVO(menuName, totalQuantity, sales);
				
				salesMenuList.add(menuSales);
			}
			
		} catch (SQLException e) {
			
		}
		
		
		
		this.close();
		return salesMenuList;
	}
	
	

}
