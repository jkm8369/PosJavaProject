package com.javaex.vo;

public class OrderVO {
	
	//필드
	private int orderId;
	private String menuName;
	private int menuPrice;
	private int quantity;
	private int	menuId;
	private int totalPrice;
	private int tableNumber;
	
	//생성자
	public OrderVO() {
	}
	public OrderVO(int menuId, String menuName, int menuPrice) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}
	public OrderVO(int orderId, String menuName, int menuPrice, int quantity,int totalPrice,int tableNumber) {
		super();
		this.orderId = orderId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.tableNumber = tableNumber;
	}
	public OrderVO(int orderId, String menuName, int menuPrice, int quantity, int menuId, int totalPrice,int tableNumber) {
		super();
		this.orderId = orderId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.quantity = quantity;
		this.menuId = menuId;
		this.totalPrice = totalPrice;
		this.tableNumber = tableNumber;
	}
	
	//메소드-gs
	public int getOrderId() {
		return orderId;
	}
	public String getMenuName() {
		return menuName;
	}
	public int getMenuPrice() {
		return menuPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getMenuId() {
		return menuId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public int getTableNumber() {
		return tableNumber;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	
	@Override
	public String toString() {
		return "OrderVO [orderId=" + orderId + ", menuName=" + menuName + ", menuPrice=" + menuPrice + ", quantity="
				+ quantity + ", totalPrice=" + totalPrice + ", tableNumber=" + tableNumber + "]";
	}
	
	

}
