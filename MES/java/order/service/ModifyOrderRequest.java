package order.service;

import java.util.Date;

public class ModifyOrderRequest {
	
	private String order_no;
	private String order_status;
	private Date delivery_dt;
	private int order_qty;
	private String remark;
	
	public ModifyOrderRequest(String order_no, String order_status, Date delivery_dt, int order_qty, String remark) {
		this.order_no = order_no;
		this.order_status = order_status;
		this.delivery_dt = delivery_dt;
		this.order_qty = order_qty;
		this.remark = remark;
	}

	public String getOrder_no() {
		return order_no;
	}

	public String getOrder_status() {
		return order_status;
	}

	public Date getDelivery_dt() {
		return delivery_dt;
	}

	public int getOrder_qty() {
		return order_qty;
	}

	public String getRemark() {
		return remark;
	}
	
	
	

}
