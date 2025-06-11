package com.javaex.vo;

public class PaymentVO {

	private int orderId;
	private String menuName;
	private int menuPrice;
	private int quantity;
	private int totalPrice;
	private int tableNumber;
	private String paymentYn;
	private String orderTime;
	private int orderCount;
	private int sales;
	
	
	public PaymentVO() {
		super();
	}

	public PaymentVO(int orderId, String menuName, int menuPrice, int quantity, int totalPrice, int tableNumber) {
		super();
		this.orderId = orderId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.tableNumber = tableNumber;
	}

	
	
	public PaymentVO(String orderTime, int orderCount, int sales) {
		super();
		this.orderTime = orderTime;
		this.orderCount = orderCount;
		this.sales = sales;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	@Override
	public String toString() {
		return "PaymentVO [orderId=" + orderId + ", menuName=" + menuName + ", menuPrice=" + menuPrice + ", quantity="
				+ quantity + ", totalPrice=" + totalPrice + ", tableNumber=" + tableNumber + "]";
	}
	
	
	
	
	
	
	
	
	
}