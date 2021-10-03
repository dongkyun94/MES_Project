package item.model;

import java.util.Date;

public class Item {
	
	private int comp_cd;
	private int plant_cd;
	private String Acct_id;
	private int item_cd;
	private String item_nm;
	private String item_spec;
	private String item_spec2;
	private String item_color;
	private String cust_cd;
	private int acct_price;
	private String currency;
	private String unit_cd;
	private String remark;
	private String in_usr_id;
	private Date in_date;
	private String up_usr_id;
	private Date up_date;
	
	public Item(int comp_cd, int plant_cd, String acct_id, int item_cd, String item_nm, String item_spec,
			String item_spec2, String item_color, String cust_cd, int acct_price, String currency, String unit_cd,
			String remark, String in_usr_id, Date in_date, String up_usr_id, Date up_date) {
		this.comp_cd = comp_cd;
		this.plant_cd = plant_cd;
		Acct_id = acct_id;
		this.item_cd = item_cd;
		this.item_nm = item_nm;
		this.item_spec = item_spec;
		this.item_spec2 = item_spec2;
		this.item_color = item_color;
		this.cust_cd = cust_cd;
		this.acct_price = acct_price;
		this.currency = currency;
		this.unit_cd = unit_cd;
		this.remark = remark;
		this.in_usr_id = in_usr_id;
		this.in_date = in_date;
		this.up_usr_id = up_usr_id;
		this.up_date = up_date;
	}

	public int getComp_cd() {
		return comp_cd;
	}

	public int getPlant_cd() {
		return plant_cd;
	}

	public String getAcct_id() {
		return Acct_id;
	}

	public int getItem_cd() {
		return item_cd;
	}

	public String getItem_nm() {
		return item_nm;
	}

	public String getItem_spec() {
		return item_spec;
	}

	public String getItem_spec2() {
		return item_spec2;
	}

	public String getItem_color() {
		return item_color;
	}

	public String getCust_cd() {
		return cust_cd;
	}

	public int getAcct_price() {
		return acct_price;
	}

	public String getCurrency() {
		return currency;
	}

	public String getUnit_cd() {
		return unit_cd;
	}

	public String getRemark() {
		return remark;
	}

	public String getIn_usr_id() {
		return in_usr_id;
	}

	public Date getIn_date() {
		return in_date;
	}

	public String getUp_usr_id() {
		return up_usr_id;
	}

	public Date getUp_date() {
		return up_date;
	}
	
}
