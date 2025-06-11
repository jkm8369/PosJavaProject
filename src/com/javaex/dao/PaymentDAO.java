package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PaymentVO;

public class PaymentDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/pos_db";
	private String id = "pos";
	private String pw = "pos";

	public PaymentDAO() {

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

	// 주문번호로 결제
	public int payOrderId(int orderId) {
		int count = -1;

		this.connect();

		try {
			// SQL문
			String query = "";
			query += " update orders ";
			query += " set payment_yn = 'Y' ";
			query += " where order_id = ? ";
			query += " and payment_yn = 'N' ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderId);

			// 실행
			count = pstmt.executeUpdate();

			// 결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	}

	public int payTableNumber(int tableNumber) {
		int count = -1;

		this.connect();

		try {
			// SQL준비
			String query = "";
			query += " update orders ";
			query += " set payment_yn = 'Y' ";
			query += " where table_number = ? ";
			query += " and payment_yn = 'N' ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tableNumber);

			// 실행
			count = pstmt.executeUpdate();

			// 결과 처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	}

	public List<PaymentVO> orderSelectList() {

		List<PaymentVO> orderList = new ArrayList<PaymentVO>();

		this.connect();

		try {

			String query = "";
			query += " select o.order_id, ";
			query += " 		  m.menu_name, ";
			query += " 		  m.menu_price, ";
			query += " 		  o.quantity, ";
			query += " 		  (menu_price*quantity) as total_price, ";
			query += " 		  o.table_number ";
			query += " from category c, menu m , orders o ";
			query += " where c.category_id = m.category_id ";
			query += " and m.menu_id = o.menu_id ";
			query += " and o.payment_yn = 'N' ";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int orderId = rs.getInt("o.order_id");
				String menuName = rs.getString("m.menu_name");
				int menuPrice = rs.getInt("m.menu_price");
				int quantity = rs.getInt("o.quantity");
				int totalPrice = rs.getInt("total_price");
				int tableNumber = rs.getInt("o.table_number");

				PaymentVO paymentVO = new PaymentVO(orderId, menuName, menuPrice, quantity, totalPrice, tableNumber);

				orderList.add(paymentVO);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return orderList;
	}

	
	



}
