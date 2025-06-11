package com.javaex.pos;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.javaex.dao.HourStaticDAO;
import com.javaex.dao.MenuStaticDAO;
import com.javaex.dao.OrderDAO;
import com.javaex.dao.PaymentDAO;
import com.javaex.vo.HourStaticVO;
import com.javaex.vo.MenuStaticVO;
import com.javaex.vo.OrderVO;
import com.javaex.vo.PaymentVO;

public class PosApp {

	public static void main(String[] args) {

		OrderDAO orderDAO = new OrderDAO();
		PaymentDAO paymentDAO = new PaymentDAO();
		HourStaticDAO hourStaticDAO = new HourStaticDAO();
		MenuStaticDAO menuStaticDAO = new MenuStaticDAO();

		Scanner sc = new Scanner(System.in);

		int mainNum;
		int orderNum;
		int tableNum;
		int menuNum;
		int count;
		int editOrderNum;
		int editCount;
		int orderIdNum;
		int paymentNum01;
		int paymentNum02;
		int orderTableNum;
		int staticMenuNum;

		while (true) {
			System.out.println("--------------------------hi-pos--------------------------");
			System.out.println("1.주문결제  2.카테고리관리  3.메뉴관리  4.매출통계  5.포스종료");
			System.out.println("----------------------------------------------------------");

			System.out.print(">");
			mainNum = sc.nextInt();

			if (mainNum == 5) {
				break;
			}

			switch (mainNum) {
			case 1:
				while (true) {
					System.out.println("");
					System.out.println("■테이블 주문 현황");
					System.out.println("----------------------------------------------------------");
					System.out.println("주문번호	메뉴명	가격  수량  금액  테이블번호");

					// 오더리스트
					List<OrderVO> posOrderList = orderDAO.orderSelect();

					for (int i = 0; i < posOrderList.size(); i++) {
						System.out.println(posOrderList.get(i).getOrderId() + "\t" + posOrderList.get(i).getMenuName()
								+ "  " + posOrderList.get(i).getMenuPrice() + "  " + posOrderList.get(i).getQuantity()
								+ "  " + posOrderList.get(i).getTotalPrice() + "  "
								+ posOrderList.get(i).getTableNumber());
					}
					System.out.println("----------------------------------------------------------");
					System.out.println("1.주문하기  2.주문수정  3.주문삭제  4.주문결제  5.테이블결제  0.메인홈");

					System.out.print(">");
					orderNum = sc.nextInt();

					if (orderNum == 0) {
						break;
					} else if (orderNum == 1) {

						System.out.println("");
						System.out.println("▶주문하기");
						System.out.println("----------------------------------------------------------");

						// 메뉴리스트
						List<OrderVO> menuList = orderDAO.menuSelect();

						for (int i = 0; i < menuList.size(); i++) {
							System.out.println(menuList.get(i).getMenuId() + ". " + menuList.get(i).getMenuName()
									+ " - " + menuList.get(i).getMenuPrice() + "원");
						}

						System.out.println("----------------------------------------------------------");
						System.out.println("*99.나가기");

						while (true) {
							System.out.println();
							System.out.print("테이블번호>");
							tableNum = sc.nextInt();
							if (tableNum == 99) {
								break;
							}
							System.out.print("메뉴번호>");
							menuNum = sc.nextInt();
							if (menuNum == 99) {
								break;
							}
							System.out.print("수량>");
							count = sc.nextInt();
							if (count == 99) {
								break;
							}
							// 주문
							int oIn = orderDAO.orderInsert(tableNum, count, menuNum);
						}

					} else if (orderNum == 2) {

						System.out.println("");
						System.out.println("▶주문수정");
						System.out.println("----------------------------------------------------------");

						for (int i = 0; i < posOrderList.size(); i++) {
							System.out.println(posOrderList.get(i).getOrderId() + "\t"
									+ posOrderList.get(i).getMenuName() + "  " + posOrderList.get(i).getMenuPrice()
									+ "  " + posOrderList.get(i).getQuantity() + "  "
									+ posOrderList.get(i).getTotalPrice() + "  "
									+ posOrderList.get(i).getTableNumber());
						}
						System.out.println("----------------------------------------------------------");
						System.out.println("99.나가기");

						while (true) {
							System.out.println();
							System.out.print("수정할 주문번호>");
							editOrderNum = sc.nextInt();
							if (editOrderNum == 99) {
								break;
							}
							System.out.print("수량>");
							editCount = sc.nextInt();
							if (editCount == 99) {
								break;
							}

							// 주문
							orderDAO.orderUpdate(editOrderNum, editCount);
						}

					} else if (orderNum == 4) {// 결제 강민
						System.out.println("");
						System.out.println("▶주문결제");
						System.out.println(
								"-----------------------------------------------------------------------------------------------------");
						System.out.println("주문번호\t 메뉴명\t\t가격\t\t 수량\t\t 총금액\t\t 테이블번호");
						List<PaymentVO> orderList = paymentDAO.orderSelectList();

						for (int i = 0; i < orderList.size(); i++) {
							System.out.println(orderList.get(i).getOrderId() + "\t\t" + orderList.get(i).getMenuName()
									+ "\t\t" + orderList.get(i).getMenuPrice() + "\t\t" + orderList.get(i).getQuantity()
									+ "\t\t" + orderList.get(i).getTotalPrice() + "\t\t"
									+ orderList.get(i).getTableNumber());
						}

						System.out.println(
								"-----------------------------------------------------------------------------------------------------");
						System.out.println("");
						System.out.print("결제할 주문번호> ");
						orderIdNum = sc.nextInt();

						for (int i = 0; i < orderList.size(); i++) {

							if (orderIdNum == orderList.get(i).getOrderId()) {
								System.out.println("결제하시겠습니까?");
								System.out.println("1.예   2.아니오");
								paymentNum01 = sc.nextInt();

								if (paymentNum01 == 1) {

									int c01 = paymentDAO.payOrderId(orderIdNum);

									if (c01 > 0) {
										System.out.println("+++결제완료+++");
										break;
									} else if (c01 < 0) {
										System.out.println("알 수 없는 오류 발생");
									} else {
										System.out.println("결제되지 않았습니다.");
									}

								} else {
									break;
								}

							}
						}

					} else if (orderNum == 5) { // 테이블 결제

						System.out.println("");
						System.out.println("▶주문결제");
						System.out.println(
								"-----------------------------------------------------------------------------------------------------");
						System.out.println("주문번호\t 메뉴명\t\t가격\t\t 수량\t\t 총금액\t\t 테이블번호");
						List<PaymentVO> orderList = paymentDAO.orderSelectList();

						for (int i = 0; i < orderList.size(); i++) {
							System.out.println(orderList.get(i).getOrderId() + "\t\t" + orderList.get(i).getMenuName()
									+ "\t\t" + orderList.get(i).getMenuPrice() + "\t\t" + orderList.get(i).getQuantity()
									+ "\t\t" + orderList.get(i).getTotalPrice() + "\t\t"
									+ orderList.get(i).getTableNumber());
						}

						System.out.println(
								"-----------------------------------------------------------------------------------------------------");
						System.out.println("");
						System.out.print("결제할 테이블번호> ");
						orderTableNum = sc.nextInt();

						for (int i = 0; i < orderList.size(); i++) {

							if (orderTableNum == orderList.get(i).getTableNumber()) {
								System.out.println("결제하시겠습니까?");
								System.out.println("1.예   2.아니오");

								paymentNum02 = sc.nextInt();

								if (paymentNum02 == 1) {
									int c01 = paymentDAO.payTableNumber(orderTableNum);

									if (c01 > 0) {
										System.out.println("+++결제완료+++");
										break;
									} else if (c01 < 0) {
										System.out.println("알 수 없는 오류 발생");
									} else {
										System.out.println("결제되지 않았습니다.");
									}
								} else {
									break;
								}

							}

						}

					}

				}

				break;
			case 2:// 카테고리 유빈

				break;
			case 3:// 메뉴수진

				break;

			case 4:
				while (true) {
					System.out.println("■매출통계");
					System.out.println("----------------------------------------------------------------");
					System.out.println("1.일별 매출 조회\t\t 2.시간대별 매출 조회\t\t 3.메뉴별 매출 조회\t\t 4.카테고리별 매출 조회\t\t 0.메인 홈");
					System.out.println("----------------------------------------------------------------");
					System.out.println("");
					System.out.print("> ");

					staticMenuNum = sc.nextInt();
					if (staticMenuNum == 0) {
						break;
					} else if (staticMenuNum == 2) {
						System.out.println("▶ 2. 시간대별 매출 조회 ");
						System.out.println("<시간대별 매출 조회>");

						LocalDate today = LocalDate.now();
						System.out.println(today);

						System.out
								.println("--------------------------------------------------------------------------");
						System.out.println("시간대 \t\t 건수 \t\t 금액");
						List<HourStaticVO> salesHourList = hourStaticDAO.salesByHourList();

						for (int i = 0; i < salesHourList.size(); i++) {
							System.out.println(salesHourList.get(i).getOrderTime() + "\t\t"
									+ salesHourList.get(i).getOrderCount() + "\t\t" + salesHourList.get(i).getSales());
						}
						System.out
								.println("--------------------------------------------------------------------------");

					} else if (staticMenuNum == 3) {
						System.out.println("▶ 3.메뉴별 매출 조회");
						System.out.println("<메뉴별 매출 조회>");

						LocalDate today = LocalDate.now();
						System.out.println(today);

						System.out
								.println("--------------------------------------------------------------------------");
						System.out.println("시간대 \t\t 건수 \t\t 금액");
						List<MenuStaticVO> salesMenuList = menuStaticDAO.salesByMenuList();

						for (int i = 0; i < salesMenuList.size(); i++) {
							System.out.println(salesMenuList.get(i).getMenuName() + "\t\t"
									+ salesMenuList.get(i).getTotalQuantity() + "\t\t"
									+ salesMenuList.get(i).getSales());
						}
						System.out
								.println("--------------------------------------------------------------------------");

					}

				}
				break;
			case 5:
				break;

//			default:
			}

		}

		sc.close();

//		//수정
//		int oUp = orderDAO.orderUpdate(1,3);
//		//삭제
//		int oDel = orderDAO.orderDelete(3);

// 메뉴리스트
//		List<OrderVO> menuList = orderDAO.menuSelect();
//		
//		System.out.println("--------------------------------");
//		for(int i=0; i<menuList.size(); i++) {
//			System.out.println(menuList.get(i).getMenuId()+". "
//								+menuList.get(i).getMenuName()+" - "
//								+menuList.get(i).getMenuPrice()+"원");
//		}
//		System.out.println("--------------------------------");

	}

}
