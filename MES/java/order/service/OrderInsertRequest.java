package order.service;

import java.util.Date;
import java.util.Map;

import order.model.Order;

public class OrderInsertRequest {
	
	private Integer comp_cd;
	private Integer plant_cd;
	private String order_no;
	private String order_dt;
	private int item_cd;
	private String delivery_dt;
	private int order_qyt;
	private String order_status;
	private String remark;
	
	public OrderInsertRequest(Integer comp_cd, Integer plant_cd, String order_no, String order_dt, int item_cd,
			String delivery_dt, int order_qyt, String order_status, String remark) {
		super();
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		this.order_no = order_no;
		this.order_dt = order_dt;
		this.item_cd = item_cd;
		this.delivery_dt = delivery_dt;
		this.order_qyt = order_qyt;
		this.order_status = order_status;
		this.remark = remark;
	}

	public Integer getComp_cd() {
		return comp_cd;
	}





	public void setComp_cd(Integer comp_cd) {
		this.comp_cd = comp_cd;
	}





	public Integer getPlant_cd() {
		return plant_cd;
	}





	public void setPlant_cd(Integer plant_cd) {
		this.plant_cd = plant_cd;
	}





	public String getOrder_no() {
		return order_no;
	}





	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}





	public String getOrder_dt() {
		return order_dt;
	}





	public void setOrder_dt(String order_dt) {
		this.order_dt = order_dt;
	}





	public int getItem_cd() {
		return item_cd;
	}





	public void setItem_cd(int item_cd) {
		this.item_cd = item_cd;
	}





	public String getDelivery_dt() {
		return delivery_dt;
	}





	public void setDelivery_dt(String delivery_dt) {
		this.delivery_dt = delivery_dt;
	}





	public int getOrder_qyt() {
		return order_qyt;
	}





	public void setOrder_qyt(int order_qyt) {
		this.order_qyt = order_qyt;
	}





	public String getOrder_status() {
		return order_status;
	}





	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}





	public String getRemark() {
		return remark;
	}





	public void setRemark(String remark) {
		this.remark = remark;
	}





	public void validate(Map<String, Boolean> errors) {
		if(comp_cd == null) {
			errors.put("comp_cd", Boolean.TRUE);
		}
		if(plant_cd == null) {
			errors.put("plant_cd", Boolean.TRUE);
		}
	}

}
