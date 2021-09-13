package order.model;

import java.util.Date;

public class Order {
	
	private int comp_cd;
	private int plant_cd;
	private String order_no;
	private Date order_dt;
	private int item_cd;
	private String barcode;
	private String manu_no;
	private Date delivery_dt;
	private int order_qyt;
	private String order_status;
	private String remark;
	
	public Order(int comp_cd, int plant_cd, String order_no, Date order_dt, int item_cd, String barcode, String manu_no,
			Date delivery_dt, int order_qyt, String order_status, String remark) {
		super();
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		this.order_no = order_no;
		this.order_dt = order_dt;
		this.item_cd = item_cd;
		this.barcode = barcode;
		this.manu_no = manu_no;
		this.delivery_dt = delivery_dt;
		this.order_qyt = order_qyt;
		this.order_status = order_status;
		this.remark = remark;
	}

	public int getComp_cd() {
		return comp_cd;
	}

	public int getPlant_cd() {
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

	public String getBarcode() {
		return barcode;
	}

	public String getManu_no() {
		return manu_no;
	}

	public Date getDelivery_dt() {
		return delivery_dt;
	}

	public int getOrder_qyt() {
		return order_qyt;
	}

	public String getOrder_status() {
		return order_status;
	}

	public String getRemark() {
		return remark;
	}
	
	
	
	

}
