package com.javaex.vo;

public class MenuVO {
	
	
	//필드
	private int categoryId;
	private String categoryName;
	private String categoryEmoji;
	private String categoryRegDate;
	
	private int menuId;
	private String menuName;
	private int menuPrice;
	private String menuRegDate;
	
	
	private int orderId;
	private int tableNumber;
	private int tableQuantity;
	private String orderTime;
	private String paymentYN;
	
	
	//생성자
	public MenuVO() {
		
	}
		

	

	public MenuVO( int menuId, String menuName, int menuPrice, String categoryName, int categoryId) {

		this.menuId = menuId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
	
	}



	public MenuVO(int categoryId, String categoryName, String categoryEmoji, String categoryRegDate,
			int menuId, String menuName, int menuPrice, String menuRegDate, int orderId, int tableNumber,
			int tableQuantity, String orderTime, String paymentYN) {

		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryEmoji = categoryEmoji;
		this.categoryRegDate = categoryRegDate;
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuRegDate = menuRegDate;
		this.orderId = orderId;
		this.tableNumber = tableNumber;
		this.tableQuantity = tableQuantity;
		this.orderTime = orderTime;
		this.paymentYN = paymentYN;
	}


	
	//메소드gs
	public int getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public String getCategoryEmoji() {
		return categoryEmoji;
	}



	public void setCategoryEmoji(String categoryEmoji) {
		this.categoryEmoji = categoryEmoji;
	}



	public String getCategoryRegDate() {
		return categoryRegDate;
	}



	public void setCategoryRegDate(String categoryRegDate) {
		this.categoryRegDate = categoryRegDate;
	}



	public int getMenuId() {
		return menuId;
	}



	public void setMenuId(int menuId) {
		this.menuId = menuId;
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



	public String getMenuRegDate() {
		return menuRegDate;
	}



	public void setMenuRegDate(String menuRegDate) {
		this.menuRegDate = menuRegDate;
	}



	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public int getTableNumber() {
		return tableNumber;
	}



	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}



	public int getTableQuantity() {
		return tableQuantity;
	}



	public void setTableQuantity(int tableQuantity) {
		this.tableQuantity = tableQuantity;
	}



	public String getOrderTime() {
		return orderTime;
	}



	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}



	public String getPaymentYN() {
		return paymentYN;
	}



	public void setPaymentYN(String paymentYN) {
		this.paymentYN = paymentYN;
	}

	

	//메소드 일반
	@Override
	public String toString() {
		return "CategoryMenuOrdersVO [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryEmoji="
				+ categoryEmoji + ", categoryRegDate=" + categoryRegDate + ", menuId=" + menuId + ", menuName="
				+ menuName + ", menuPrice=" + menuPrice + ", menuRegDate=" + menuRegDate + ", orderId=" + orderId
				+ ", tableNumber=" + tableNumber + ", tableQuantity=" + tableQuantity + ", orderTime=" + orderTime
				+ ", paymentYN=" + paymentYN + "]";
	}

}
