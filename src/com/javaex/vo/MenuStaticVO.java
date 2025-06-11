package com.javaex.vo;

public class MenuStaticVO {

	private String menuName;
	private int totalQuantity;
	private int sales;
	
	public MenuStaticVO() {
		super();
	}

	public MenuStaticVO(String menuName, int totalQuantity, int sales) {
		super();
		this.menuName = menuName;
		this.totalQuantity = totalQuantity;
		this.sales = sales;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}
	
	
	
}
