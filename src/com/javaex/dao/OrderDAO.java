package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.OrderVO;

public class OrderDAO {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/pos_db";
	private String id = "pos";
	private String pw = "pos";

	// 생성자
	public OrderDAO() {
	}

	// 연결
	private void connect() {

		try {
			// 1. JDBC 드라이버 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 종료-클리어
	private void close() {
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

	// 주문등록
	public int orderInsert(int tableNumber, int quantity, int menuId ) {

		int count = -1;

		// db연결 드라이버 로딩
		connect();

		try {
			// 쿼리 준비 바인딩 실행
			String query = "";
			query += "insert into orders ";
			query += "values(null,?,?,now(),'N',?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, tableNumber);
			pstmt.setInt(2, quantity);
			pstmt.setInt(3, menuId);

			// 실행 후 담기
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("error:" + e);
		}
		// 자원정리
		close();

		return count;

	}

	// 주문수정
	public int orderUpdate(int orderId, int quantity) {

		int count = -1;

		// db연결 드라이버 로딩
		connect();

		try {
			// 쿼리 준비 바인딩 실행
			String query = "";
			query += "update orders ";
			query += "set quantity = ? ";
			query += "where order_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, quantity);
			pstmt.setInt(2, orderId);

			// 실행 후 담기
			count = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("error:" + e);
		}
		// 자원정리
		close();

		return count;

	}

	// 주문삭제
	public int orderDelete(int orderId) {

		int count = -1;
		System.out.println("삭제");

		// 0. import java.sql.*;
		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		connect();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "delete from orders ";
			query += "where order_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, orderId);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 5. 자원정리
		close();

		return count;
	}

	// 주문리스트
	public List<OrderVO> orderSelect() {

		List<OrderVO> posOrderList = new ArrayList<OrderVO>();

		// 연결
		connect();

		try {
			// 쿼리 준비 바인딩 실행
			String query = "";
			query += "select o.order_id ";
			query += "	  , m.menu_name ";
			query += "    , m.menu_price ";
			query += "    , o.quantity ";
			query += "    , m.menu_price*o.quantity total_price ";
			query += "    , o.table_number ";
			query += "from menu m ";
			query += "	, orders o ";
			query += "where m.menu_id = o.menu_id ";
			query += "and o.payment_yn = 'N' ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				// 쿼리 결과를 변수에 담는다
				int orderId = rs.getInt("order_id");
				String menuName = rs.getString("menu_name");
				int menuPrice = rs.getInt("menu_price");
				int quantity = rs.getInt("quantity");
				int totalPrice = rs.getInt("total_price");
				int tableNumber = rs.getInt("table_number");

				// posVO 로 묶는다
				OrderVO posVO = new OrderVO(orderId, menuName, menuPrice, quantity, totalPrice ,tableNumber);

				// 리스트에에 추가
				posOrderList.add(posVO);

			}

		} catch (Exception e) {
			System.out.println("error:" + e);
		}

		close();

		return posOrderList;

	}

	// 메뉴리스트
	public List<OrderVO> menuSelect() {

		System.out.println("메뉴");

		List<OrderVO> menuList = new ArrayList<OrderVO>();

		// 연결
		connect();

		try {
			// 쿼리 준비 바인딩 실행
			String query = "";
			query += "select m.menu_id ";
			query += "    , m.menu_name ";
			query += "    , m.menu_price ";
			query += "from category c ";
			query += "	, menu m ";
			query += "where c.category_id =m.category_id ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				// 쿼리 결과를 변수에 담는다
				int menuId = rs.getInt("menu_id");
				String menuName = rs.getString("menu_name");
				int menuPrice = rs.getInt("menu_price");

				// posVO 로 묶는다
				OrderVO posVO = new OrderVO(menuId, menuName, menuPrice);

				// 리스트에에 추가
				menuList.add(posVO);

			}

		} catch (Exception e) {
			System.out.println("error:" + e);
		}

		close();

		return menuList;

	}

}
