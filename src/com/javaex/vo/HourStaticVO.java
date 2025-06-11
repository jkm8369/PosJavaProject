package com.javaex.vo;

public class HourStaticVO {
	
	//private String menu;
	//private String hourOrderTime;
	//private int hourOrderCount;
	//private int hourSales;
	private String orderTime;
	private int orderCount;
	private int sales;
	//private int menuOrderCount;
	//private int categoryOrderCount;
	//private int menuSales;
	//private int categorySales;
	
	public HourStaticVO() {
		super();
	}
	
	public HourStaticVO(String orderTime, int orderCount, int sales) {
		super();
		this.orderTime = orderTime;
		this.orderCount = orderCount;
		this.sales = sales;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}
	
	
	
	
	
}