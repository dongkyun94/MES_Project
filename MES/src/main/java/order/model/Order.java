package order.model;

import java.util.Date;

public class Order {
	
	private Integer comp_cd;
	private Integer plant_cd;
	private String order_no;
	private Date order_dt;
	private int item_cd;
	private Date delivery_dt;
	private int order_qty;
	private String order_status;
	private String remark;
	
	public Order() {
		
	}
	public Order(Integer comp_cd, Integer plant_cd, String order_no, Date order_dt, int item_cd, 
			Date delivery_dt, int order_qty, String order_status, String remark) {
		super();
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		this.order_no = order_no;
		this.order_dt = order_dt;
		this.item_cd = item_cd;
		this.delivery_dt = delivery_dt;
		this.order_qty = order_qty;
		this.order_status = order_status;
		this.remark = remark;
	}

	public Integer getComp_cd() {
		return comp_cd;
	}

	public Integer getPlant_cd() {
		return plant_cd;
	}

	public String getOrder_no() {
		return order_no;
	}

	public Date getOrder_dt() {
		return order_dt;
	}

	public int getItem_cd() {
		return item_cd;
	}

	public Date getDelivery_dt() {
		return delivery_dt;
	}

	public int getOrder_qty() {
		return order_qty;
	}

	public String getOrder_status() {
		return order_status;
	}

	public String getRemark() {
		return remark;
	}
	
	
	
	

}
