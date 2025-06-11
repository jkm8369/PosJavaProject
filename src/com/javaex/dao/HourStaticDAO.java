package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.HourStaticVO;

public class HourStaticDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/pos_db";
	private String id = "pos";
	private String pw = "pos";
	
	public HourStaticDAO() {
		
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
	
	public List<HourStaticVO> salesByHourList() {    // 시간대별 매출 통계 목록
		List<HourStaticVO> salesHourList = new ArrayList<HourStaticVO>();
		
		this.connect();
		
		try {
			
			String query = "";
			query += " select date_format(o.order_time, '%Y-%m-%d %H:00:00') as order_time, ";
			query += " 		  count(*) as order_count, ";
			query += " 		  sum(m.menu_price*o.quantity) as sales ";
			query += " from orders o, menu m ";
			query += " where o.menu_id = m.menu_id ";
			query += " and date(o.order_time) = date_format(now(), '%Y-%m-%d') ";
			query += " group by date_format(o.order_time, '%Y-%m-%d %H:00:00') ";
			query += " order by order_time asc ";
			
			pstmt = conn.prepareStatement(query);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String orderTime = rs.getString("order_time");
				int orderCount = rs.getInt("order_count");
				int sales = rs.getInt("sales");
				
				HourStaticVO hourSales = new HourStaticVO(orderTime, orderCount, sales);
				salesHourList.add(hourSales);
			}
			
		} catch (SQLException e) {
		}
		
		
		this.close();
		return salesHourList;
	}
	
	
	
	
	
}
